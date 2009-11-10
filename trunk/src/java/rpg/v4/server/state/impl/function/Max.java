package rpg.v4.server.state.impl.function;

import rpg.v4.server.state.Function;
import rpg.v4.server.state.State;
import rpg.v4.server.state.impl.IntegerState;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Propagates the maximum total of all the states observed.
 */
public class Max extends Observable implements Function, Observer, Serializable
{
    private List<State> stateList = new ArrayList<State>();
    private Integer propagatedValue = 0;
    private State propagatedState = null;
    private State owner;

    public void addStateToObserve(State state)
    {
        if (!stateList.contains(state) && state instanceof IntegerState)
        {
            state.addObserver(this);
            stateList.add(state);
        }
    }

    public void setOwner(State owner)
    {
        this.owner = owner;
    }
    
    public void update(Observable o, Object arg)
    {
        IntegerState state = (IntegerState) o;

        if (((Integer) state.getTotal()) > propagatedValue)
        {
            // A larger value has been given
            propagatedState = state;
            propagatedValue = (Integer) state.getTotal();
            // This will adde the new value or override any existing value as the source is the same.
            owner.add(propagatedState.getSubType(), this, propagatedValue);
        } else if (state == propagatedState)
        {
            // The current state has been reduced, need to find the max of all states and propagate it.
            // It's at least guaranteed to be one state. Initialise to good defaults before exhaustive check.
            propagatedValue = (Integer) state.getTotal();
            propagatedState = state;

            for (State aState : stateList)
            {
                Integer aValue = (Integer) aState.getTotal();
                if (aValue > propagatedValue)
                {
                    propagatedValue = aValue;
                    propagatedState = aState;
                }
            }

            owner.add(propagatedState.getSubType(), this, propagatedValue);
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
        stringBuffer.append(propagatedValue).append(" Max ");

        for (State state : stateList)
        {
            stringBuffer.append("[").append(state.getTotal()).append(" ").append(state.getStateID()).append("] ");
        }

        return stringBuffer.toString();
    }
}
