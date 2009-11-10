package rpg.v4.server.state.impl.function;

import rpg.v4.middleware.jaxb.V4GenericAction;
import rpg.v4.middleware.util.xml.XMLActionKit;
import rpg.v4.server.state.Function;
import rpg.v4.server.state.State;
import rpg.v4.server.state.impl.IntegerState;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

/**
 * Propagates the hal of the total of the one state observed.
 */
public class ActionAbilitySelector extends Observable implements Function, Observer, Serializable
{
    private Integer propagatedValue = 0;
    private State propagatedState = null;
    private State owner;

    private State actionState;
    private Map<String, IntegerState> abilityMap = new HashMap<String, IntegerState>(6);

    public void addStateToObserve(State stateToObserve)
    {
        if (actionState == null)
            actionState = stateToObserve;
        else if (abilityMap.size() <= 6)
        {
            if (!abilityMap.keySet().contains(stateToObserve.getStateID()))
            {
                abilityMap.put(stateToObserve.getStateID(), (IntegerState) stateToObserve);
            }
        } else
            throw new UnsupportedOperationException("Too many states to observe.");

        stateToObserve.addObserver(this);
    }

    public void setOwner(State owner)
    {
        this.owner = owner;
    }

    public void update(Observable o, Object arg)
    {
        State state = (State) o;
        if (state == actionState)
        {
            // The action has changed somehow: 1) A new action has been selected.
            // 2) Another action has been selected in favour of the first one.
            // 3) The SelectedAction state has been cleared.

            String actionName = (String) state.getTotal();

            if (null == actionName || "".equals(actionName))
            {
                propagatedValue = 0;
                propagatedState = null;
            } else
            {

                V4GenericAction actionSelected = XMLActionKit.instance().getGenericAction(actionName);
                String ability = actionSelected.getAttack().getAttackerStateID();

                if (null == ability || "".equals(ability))
                {
                    propagatedValue = 0;
                    propagatedState = null;
                } else if (abilityMap.containsKey(ability))
                {
                    propagatedState = abilityMap.get(ability);
                    propagatedValue = (Integer) propagatedState.getTotal();
                } else {
                    throw new UnsupportedOperationException("Ability state could not be found.");
                }
            }

            owner.add("Untyped", this, propagatedValue);
        } else
        {
            // An ability has updated. If a value is being propagated then update
            // this value.
            if (propagatedState == state)
            {
                propagatedValue = (Integer) state.getTotal();
                owner.add("Untyped", this, propagatedValue);
            }
        }
    }

    @Override
    public String toString()
    {
        if (propagatedState == null)
        {
            return "";
        }

        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(propagatedValue).append(" ActionAbilitySelector ");
        return stringBuffer.toString();
    }
}
