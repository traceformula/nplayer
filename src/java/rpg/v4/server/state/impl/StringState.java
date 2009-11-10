package rpg.v4.server.state.impl;

import rpg.v4.middleware.jaxb.V4State;
import rpg.v4.server.state.AbstractState;
import rpg.v4.server.state.State;

import java.util.List;
import java.util.Map;

/**
 * Holds a String, can change. Upon consecutive changes, the string held are replaced with the new one. Don't
 * care about sub states in this state, so just ignore it.
 */
public class StringState extends AbstractState
{
    private String total;

    public StringState(V4State v4State, Map<String, State> stateMap)
    {
        super(v4State, stateMap);
        total = "";
    }

    /**
     * Simply sets the given value to the total, overwriting what the previous total is when set.
     * @param modifierType - Ignored
     * @param source - Ignored
     * @param value - Value which overwrites current total
     */
    @Override
    public void add(String modifierType, Object source, Object value)
    {
        total = (String) value;
        setChanged();
        notifyObservers();
    }

    /**
     * Simply blanks the total the the moment.
     * @param modifierType - Ignored
     * @param source - Ignored
     * @return - Returns old value.
     */
    @Override
    public Object remove(String modifierType, Object source)
    {
        String oldVal = total;
        total = "";
        setChanged();
        notifyObservers();
        return oldVal;
    }

    protected void initiateSubStateList(List<String> modifierTypes)
    {
        // Don't care about sub states in this.
    }

    public Object getTotal()
    {
        return total;
    }

    @Override
    public V4State toV4State()
    {
        // Because string states don't care about substates, the initial value will
        // probably have been null. Get the total anyways as that's the proper value.
        Object total = getTotal();
        v4State.setInitialValue(total.toString());
        return v4State;
    }

}
