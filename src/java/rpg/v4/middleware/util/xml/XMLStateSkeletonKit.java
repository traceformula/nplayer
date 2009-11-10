package rpg.v4.middleware.util.xml;

import rpg.v4.middleware.jaxb.V4State;
import rpg.v4.middleware.jaxb.V4StateSkeleton;
import rpg.v4.server.state.State;

import javax.xml.bind.JAXBException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * Loads the XML state skeleton and allows users to retrieve clones of it.
 */
public class XMLStateSkeletonKit extends XMLKit
{
    private static final String FILE_NAME = "EntityStateSkeleton";
    private static XMLStateSkeletonKit instance = new XMLStateSkeletonKit();
    private static List<String> availableStates, availableTargetableStates;

    private V4StateSkeleton stateList;

    private XMLStateSkeletonKit()
    {
        try
        {
            stateList = (V4StateSkeleton) um.unmarshal(XMLKit.getFile(FILE_NAME));
        } catch (JAXBException e)
        {
            e.printStackTrace();
        }

        availableStates = new ArrayList<String>(stateList.getV4State().size());
        availableTargetableStates = new ArrayList<String>(stateList.getV4State().size());
        for (V4State state : stateList.getV4State())
        {
            availableStates.add(state.getStateID());
            if (!("String".equals(state.getType())))
            {
                availableTargetableStates.add(state.getStateID());
            }
        }
        Collections.sort(availableStates);
        availableStates = Collections.unmodifiableList(availableStates);
        Collections.sort(availableTargetableStates);
        availableTargetableStates = Collections.unmodifiableList(availableTargetableStates);
    }

    private void initStates(Map<String, State> stateMap)
    {
        for (V4State v4State : stateList.getV4State())
        {
            String canonicalName = "rpg.v4.server.state.impl." + v4State.getType() + "State";
            try
            {
                Class stateClass = ClassLoader.getSystemClassLoader().loadClass(canonicalName);
                Constructor stateConstructor = stateClass.getConstructor(v4State.getClass(), Map.class);
                Object[] args = new Object[] {v4State, stateMap};
                State state = (State) stateConstructor.newInstance(args);
                stateMap.put(state.getStateID(), state);
            } catch (InstantiationException e)
            {
                e.printStackTrace();
            } catch (IllegalAccessException e)
            {
                e.printStackTrace();
            } catch (ClassNotFoundException e)
            {
                throw new IllegalArgumentException(v4State.getStateID() + " - Unknown type '" + v4State.getType() + "', can not create state.", e);
            } catch (NoSuchMethodException e)
            {
                e.printStackTrace();
            } catch (InvocationTargetException e)
            {
                e.printStackTrace();
            }
        }

        // Now that the observer event network between states is set up, add initial values.
        for (V4State v4State : stateList.getV4State())
        {
            if (! v4State.getInitialValue().equals("0"))
            {
                State state = stateMap.get(v4State.getStateID());

                Object value = getInitialValue(v4State.getType(), v4State.getInitialValue());
                state.add("Initial", "Initial value", value);
            }
        }

        stateMap.get("Name").add("Initial", "Initial value", "New character");
    }

    public Map<String, State> getStateSkeletonClone()
    {
        Map<String, State> stateMap = new HashMap<String, State>(stateList.getV4State().size());
        initStates(stateMap);
        return stateMap;
    }

    public static XMLStateSkeletonKit instance()
    {
        return instance;
    }

    public static List<String> getAvailableStates()
    {
        return availableStates;
    }

    public static List<String> getAvailableTargetableStates()
    {
        return availableTargetableStates;
    }

    public static Object getInitialValue(String className, String value)
    {
        if (className.contains("Integer"))
        {
            return Integer.valueOf(value);
        }

        return value;
    }
}
