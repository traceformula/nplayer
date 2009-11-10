package rpg.v4.server.state.impl;

import rpg.v4.middleware.jaxb.V4State;
import rpg.v4.server.state.AbstractState;
import rpg.v4.server.state.State;

import java.util.List;
import java.util.Map;
import java.util.Observable;

/**
 * A classic numerical state and the most widely used state in the application.
 */
public class IntegerState extends AbstractState
{
    protected Integer total;

    public IntegerState(V4State v4State, Map<String, State> stateMap)
    {
        super(v4State, stateMap);
        total = 0;
    }

    public IntegerState(V4State v4State, Map<String, State> stateMap, boolean b)
    {
        this(v4State, stateMap);

        // Get the intial values of the states we're observing.
        for (String stateIDToObserve : v4State.getV4StateID())
        {
            update((Observable) stateMap.get(stateIDToObserve), null);
        }
    }

    protected void initiateSubStateList(List<String> modifierTypes)
    {
        for (String modifierType : modifierTypes)
        {
            if ("Untyped".equals(modifierType))
            {
                subStateList.put(modifierType,  new IntegerStackableSubState(this, modifierType));
            } else
            {
                subStateList.put(modifierType, new IntegerSubState(this, modifierType));
            }
        }
    }

    public Object getTotal()
    {
        return total;
    }

    protected void changeModifierSilently(Integer oldModifier, Integer newModifier)
    {
        total = total - oldModifier + newModifier;
    }

    protected void removeModifier(Integer oldModifer)
    {
        total = total - oldModifer;
    }
}
