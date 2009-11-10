package rpg.v4.server.state.impl.function;

import rpg.v4.server.state.Function;
import rpg.v4.server.state.State;
import rpg.v4.server.state.impl.IntegerState;

import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;

/**
 * Multiplies Max(0, (lvl - 1)) * (hit points pr lvl gained).
 */
public class HpPrLvlMultiplier extends Observable implements Function, Observer, Serializable
{
    private int propagatedValue = 0;
    private State owner;
    private State levelState, hpPrLvlGainedState;

    public void addStateToObserve(State state)
    {
        if (! (state instanceof IntegerState))
        {
            throw new IllegalArgumentException("Only Integer states are accepted. " + state.getClass() + " is not a legal state.");
        }

        if (state.getStateID().equals("Level"))
        {
            levelState = state;
        } else
        {
            hpPrLvlGainedState = state;
        }

        state.addObserver(this);
    }

    public void setOwner(State owner)
    {
        this.owner = owner;
    }

    public void update(Observable o, Object arg)
    {
        int lvl = ( (Integer) levelState.getTotal()) - 1;
        int multiplier = Math.max(0, lvl);
        int hpPrLvlGained = (Integer) hpPrLvlGainedState.getTotal();

        propagatedValue = multiplier * hpPrLvlGained;
        owner.add("Untyped", this, propagatedValue);
    }
}
