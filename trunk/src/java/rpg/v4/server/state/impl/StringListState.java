package rpg.v4.server.state.impl;

import rpg.v4.middleware.jaxb.V4State;
import rpg.v4.server.state.AbstractState;
import rpg.v4.server.state.State;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: fjelds
 * Date: Sep 8, 2009
 * Time: 9:34:27 PM
 * <p/>
 * Contains a comma seperated list of possible values. A String value can be added many times
 * but it should only show up once. Internally, the source is used to keep a list of which
 * strings the source had added. Upon add and remove, the total is recomputed.
 */
public class StringListState extends AbstractState
{
    // Map of strings a source has set, keyed on the string and with values
    // representing the sources that have set it. When a source list becomes
    // empty it means the string cna safely be removed.
    private Map<String, List<Object>> stringMap;
    private String total;

    public StringListState(V4State v4State, Map<String, State> stateMap)
    {
        super(v4State, stateMap);
        stringMap = new HashMap<String, List<Object>>(10);
        recompute();
    }

    private void recompute()
    {
        List<String> keysToRemove = new ArrayList<String>(5);

        for (String key : stringMap.keySet())
        {
            List list = stringMap.get(key);
            if (list.size() == 0)
            {
                keysToRemove.add(key);
            }
        }

        for (String key : keysToRemove)
        {
            stringMap.remove(key);
        }

        total = "";
        String seperator = "";
        for (String string : this.stringMap.keySet())
        {
            List sources = stringMap.get(string);
            if (sources.size() > 0)
            {
                total += seperator + string;
                seperator = ", ";
            }
        }
    }

    /**
     * Adds the value to the string map for the given source, ignoring modifierType.
     * If a modifier type is already present for the source, ignore the addition.
     *
     * @param modifierType - Ignored
     * @param source       - Used as a key to get the previously added strings
     * @param value        - Value to be added
     */
    @Override
    public void add(String modifierType, Object source, Object value)
    {
        String string = (String) value;
        List<Object> sourceList;
        if (stringMap.containsKey(string))
        {
            sourceList = stringMap.get(string);
        } else
        {
            sourceList = new ArrayList<Object>(10);
            stringMap.put(string, sourceList);
        }


        // Find out if we need to add the value, i.e. if the source list
        // does NOT contain the source we need to add the source and recompute.
        if (!sourceList.contains(source))
        {
            sourceList.add(source);

            boolean isFirstAddition = sourceList.size() == 1;
            if (isFirstAddition)
            {
                recompute();
                setChanged();
                notifyObservers();
            }
        }

    }

    /**
     * For all the strings added, remove the source from the associated source list. If
     * the source list size reached 0 it means no-ones added the string so the total
     * needs to be recomputed and distributed to the observers.
     *
     * @param modifierType - Ignored
     * @param source       - Ignored
     * @return - Returns old value.
     */
    @Override
    public Object remove(String modifierType, Object source)
    {
        boolean isDirty = false;

        for(String string : stringMap.keySet())
        {
            List sources = stringMap.get(string);
            boolean doRemove = false;
            for(Object aSource : sources)
            {
                doRemove = doRemove || source.equals(aSource);
            }

            if (doRemove)
            {
                sources.remove(source);
                isDirty = isDirty || sources.size() == 0;
            }
        }

        if (isDirty)
        {
            recompute();
            setChanged();
            notifyObservers();
        }

        return isDirty;
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
        // String states like these shouldn't be edited manually in a text box
        // so it does not make sense to save the total as races, feats, powers and
        // items will add this automatically.
        return v4State;
    }

    public Map<String, List<Object>> getStringMap()
    {
        return stringMap;
    }

}
