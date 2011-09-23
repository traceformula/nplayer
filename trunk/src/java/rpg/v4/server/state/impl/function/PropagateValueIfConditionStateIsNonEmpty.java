package rpg.v4.server.state.impl.function;

import com.sun.deploy.util.LoggerTraceListener;
import org.apache.log4j.Logger;
import rpg.v4.server.state.Function;
import rpg.v4.server.state.State;
import rpg.v4.server.state.impl.IntegerState;
import rpg.v4.server.state.impl.StringState;

import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;

/**
 * Propagates the state total of the propagationState if the conditionState is non empty.
 * E.g. an integer state greater than zero, or string state is non empty.
 * <p/>
 * Sets the condition state first, then the propagation state.
 */
public class PropagateValueIfConditionStateIsNonEmpty extends Observable implements Function, Observer, Serializable
{
    private State conditionState = null;
    private State propagatedState = null;
    private State owner;
    private Object previousConditionValue = null;
    private boolean isInteger = false;
    private boolean hasPropagated = false;

    /**
     * Sets the condition state first, then the propagation state so be sure to get the XML right.
     *
     * @param state
     */
    public void addStateToObserve(State state)
    {
        if (null == conditionState)
        {
            conditionState = state;
            conditionState.addObserver(this);
        } else if (null == propagatedState)
        {
            propagatedState = state;
            isInteger = propagatedState.getTotal() instanceof Integer;
        } else
        {
            throw new UnsupportedOperationException("Can only accept two states but more than two " +
                    "states were attempted to be added. Please check XML.");
        }
    }

    public void setOwner(State owner)
    {
        this.owner = owner;
    }

    public void update(Observable o, Object arg)
    {
        Object propagatedValue = propagatedState.getTotal();
        Object conditionStateTotal = conditionState.getTotal();

        // Check if value should be propagated if condition is non empty.
        if (isPropagating())
        {
            // Only bother to propagate if propagate value if different from last propagated value
            if (!propagatedValue.equals(previousConditionValue))
            {
                // This will add the new value or override any existing value as the source is the same.
                owner.add(propagatedState.getSubType(), this, propagatedValue);
                previousConditionValue = conditionStateTotal;
                hasPropagated = true;
            }
        } else if (hasPropagated)
        {
            // If value was set, unset it.
            propagatedValue = isInteger ? 0 : "";
            owner.add(propagatedState.getSubType(), this, propagatedValue);
            hasPropagated = false;
            previousConditionValue = propagatedValue;
        }
    }

    private boolean isPropagating()
    {
        Object conditionStateTotal = conditionState.getTotal();
        return conditionStateTotal instanceof Integer ? ((Integer) conditionStateTotal) > 0 : !"".equals(conditionStateTotal);
    }

    @Override
    public String toString()
    {
        if (propagatedState == null)
        {
            return "";
        }

        return conditionState.getStateID() + " (" + conditionState.getTotal() + ") propagating " +
                propagatedState.getStateID() + " (" + propagatedState.getTotal() + "): " + isPropagating();
    }
}
