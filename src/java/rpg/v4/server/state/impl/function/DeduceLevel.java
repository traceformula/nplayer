package rpg.v4.server.state.impl.function;

import rpg.v4.server.state.Function;
import rpg.v4.server.state.State;
import rpg.v4.server.state.impl.StringState;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Deduces the level when Experience Points state changes and sets it in the Level state.
 */
public class DeduceLevel extends Observable implements Function, Observer, Serializable
{
    private List<State> stateList = new ArrayList<State>();
    private Integer propagatedValue = 0;
    private State owner;

    public void addStateToObserve(State state)
    {
        if (!stateList.contains(state) && state instanceof StringState)
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
        State xpState = (State) o;
        String xpString = (String) xpState.getTotal();
        int experiencePoints = Integer.valueOf(xpString);
        int newLevel;
        if (experiencePoints >= 1000000) newLevel = 30;
        else if (experiencePoints >= 825000) newLevel = 29;
        else if (experiencePoints >= 675000) newLevel = 28;
        else if (experiencePoints >= 550000) newLevel = 27;
        else if (experiencePoints >= 450000) newLevel = 26;
        else if (experiencePoints >= 375000) newLevel = 25;
        else if (experiencePoints >= 310000) newLevel = 24;
        else if (experiencePoints >= 255000) newLevel = 23;
        else if (experiencePoints >= 210000) newLevel = 22;
        else if (experiencePoints >= 175000) newLevel = 21;
        else if (experiencePoints >= 143000) newLevel = 20;
        else if (experiencePoints >= 119000) newLevel = 19;
        else if (experiencePoints >= 99000) newLevel = 18;
        else if (experiencePoints >= 83000) newLevel = 17;
        else if (experiencePoints >= 69000) newLevel = 16;
        else if (experiencePoints >= 57000) newLevel = 15;
        else if (experiencePoints >= 47000) newLevel = 14;
        else if (experiencePoints >= 39000) newLevel = 13;
        else if (experiencePoints >= 32000) newLevel = 12;
        else if (experiencePoints >= 26000) newLevel = 11;
        else if (experiencePoints >= 20500) newLevel = 10;
        else if (experiencePoints >= 16500) newLevel = 9;
        else if (experiencePoints >= 13000) newLevel = 8;
        else if (experiencePoints >= 10000) newLevel = 7;
        else if (experiencePoints >= 7500) newLevel = 6;
        else if (experiencePoints >= 5500) newLevel = 5;
        else if (experiencePoints >= 3750) newLevel = 4;
        else if (experiencePoints >= 2250) newLevel = 3;
        else if (experiencePoints >= 1000) newLevel = 2;
        else newLevel = 1;

        if (newLevel != propagatedValue)
        {
            propagatedValue = newLevel;
            owner.add("Initial", this, propagatedValue);
        }
    }
}
