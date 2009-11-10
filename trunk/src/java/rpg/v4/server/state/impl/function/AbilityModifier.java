package rpg.v4.server.state.impl.function;

import rpg.v4.server.state.Function;
import rpg.v4.server.state.State;
import rpg.v4.server.state.impl.IntegerState;

import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;

/**
 * Propagates the total of the one state observed.
 */
public class AbilityModifier extends Observable implements Function, Observer, Serializable
{
    private Integer propagatedValue = 0;
    private State propagatedState = null;
    private State owner;

    public void addStateToObserve(State stateToObserve)
    {
        if (propagatedState != null)
        {
            throw new UnsupportedOperationException("AbilityModifier function only observes one state");
        }
        this.propagatedState = stateToObserve;
        propagatedState.addObserver(this);
    }

    public void setOwner(State owner)
    {
        this.owner = owner;
    }

    public void update(Observable o, Object arg)
    {
        IntegerState state = (IntegerState) o;
        propagatedValue = (Integer) state.getTotal();
        owner.add(propagatedState.getSubType(), this, propagatedValue);
    }

    @Override
    public String toString()
    {
        if (propagatedState == null)
        {
            return "";
        }

        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(propagatedValue).append(" ").append(this.getClass().getSimpleName()).append(" ");
        stringBuffer.append(propagatedState.toString());
        return stringBuffer.toString();
    }
}
