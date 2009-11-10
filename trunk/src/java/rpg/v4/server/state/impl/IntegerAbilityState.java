package rpg.v4.server.state.impl;

import rpg.v4.middleware.jaxb.V4State;
import rpg.v4.server.state.State;

import static java.lang.Math.floor;
import java.util.Map;

/**
 * A special implementation of an integer state. It returns the special ability modifier as opposed to the total
 * generated. This ability modifier is a function of the total.
 */
public class IntegerAbilityState extends IntegerState
{
    private Integer abilityModifier;

    public IntegerAbilityState(V4State v4State, Map<String, State> stateMap)
    {
        super(v4State, stateMap);
        updateModifier();
    }

    @Override
    public Object getTotal()
    {
        return abilityModifier;
    }

    private void updateModifier()
    {
        abilityModifier = (int) floor((Integer) total / 2) - 5;
    }

    @Override
    protected void changeModifierSilently(Integer oldModifier, Integer newModifier)
    {
        super.changeModifierSilently(oldModifier, newModifier);
        updateModifier();
    }

    @Override
    protected void removeModifier(Integer oldModifer)
    {
        super.removeModifier(oldModifer);
        updateModifier();
    }
}
