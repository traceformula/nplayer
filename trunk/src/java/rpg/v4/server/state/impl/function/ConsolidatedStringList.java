package rpg.v4.server.state.impl.function;

import rpg.v4.server.state.Function;
import rpg.v4.server.state.State;
import rpg.v4.server.state.impl.StringListState;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by IntelliJ IDEA.
 * User: fjelds
 * Date: Sep 13, 2009
 * Time: 12:36:21 PM
 * <p/>
 * Takes two lists. Based on the last one added, only propagates the totals in the first
 * state and not present in the second.
 */
public class ConsolidatedStringList extends Observable implements Function, Observer, Serializable
{
    private StringListState addState, removeState;
    private State owner;

    public void addStateToObserve(State state)
    {
        if (null == addState)
        {
            state.addObserver(this);
            addState = (StringListState) state;
        } else if (null == removeState)
        {
            state.addObserver(this);
            removeState = (StringListState) state;
        }
    }

    public void setOwner(State owner)
    {
        this.owner = owner;
    }

    public void update(Observable observable, Object o)
    {
        Map<String, List<Object>> addedMap = addState.getStringMap();
        Map<String, List<Object>> removeMap = removeState.getStringMap();

        String propagatedValue = "";
        String seperator = "";
        for (String string : addedMap.keySet())
        {
            boolean excludeIt = false;
            for (String excludedString : removeMap.keySet())
            {
                excludeIt = excludeIt || excludedString.equals(string);
            }

            if (!excludeIt)
            {
                propagatedValue = propagatedValue + seperator + string;
                seperator = ", ";
            }
        }

        owner.add(addState.getSubType(), this, propagatedValue);
    }
}
