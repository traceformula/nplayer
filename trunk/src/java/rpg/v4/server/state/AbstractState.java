package rpg.v4.server.state;

import rpg.v4.middleware.jaxb.V4Function;
import rpg.v4.middleware.jaxb.V4State;
import rpg.v4.middleware.util.deepcopy.FastDeepCopy;
import rpg.v4.middleware.util.xml.XMLModifierTypeKit;

import java.io.Serializable;
import java.util.*;

/**
 * Contains basic state information such as ID, display name, etc.
 */
public abstract class AbstractState extends Observable implements State, Observer, Serializable
{
    private String displayName;
    private String category;
    private String stateID;
    protected Map<String, SubState> subStateList;
    private String type;
    private String subType;
    private Boolean userEntry;
    private Boolean hidden;
    protected V4State v4State;

    public AbstractState(V4State v4State, Map<String, State> stateMap)
    {
        Object obj = FastDeepCopy.deepCopy(v4State);
        this.v4State = (V4State) obj;
        stateID = v4State.getStateID();
        displayName = v4State.getDisplayName();
        type = v4State.getType();
        subType = v4State.getSubType();
        category = v4State.getCategory();
        userEntry = v4State.isUserEntry();
        hidden = v4State.isHidden();

        List<String> modifierTypes = XMLModifierTypeKit.instance().getTypes();
        subStateList = new HashMap<String, SubState>(modifierTypes.size());

        initiateSubStateList(modifierTypes);
        initiateObserving(v4State, stateMap);
        initiateFunctions(v4State, stateMap);
    }

    private void initiateFunctions(V4State v4State, Map<String, State> stateMap)
    {
        for (V4Function v4Function : v4State.getV4Function())
        {
            String canonicalName = "rpg.v4.server.state.impl.function." + v4Function.getType();

            try
            {
                Function function = (Function) ClassLoader.getSystemClassLoader().loadClass(canonicalName).newInstance();
                function.setOwner(this);

                for (String stateIDToObserve : v4Function.getV4StateID())
                {
                    State stateToObserve = stateMap.get(stateIDToObserve);
                    function.addStateToObserve(stateToObserve);
                }
            } catch (ClassNotFoundException e)
            {
                e.printStackTrace();
            } catch (IllegalAccessException e)
            {
                e.printStackTrace();
            } catch (InstantiationException e)
            {
                e.printStackTrace();
            }
        }
    }

    private void initiateObserving(V4State v4State, Map<String, State> stateMap)
    {
        for (String stateIDToObserve : v4State.getV4StateID())
        {
            if (!stateMap.containsKey(stateIDToObserve))
            {
                throw new UnsupportedOperationException("Can not add " + stateID + " as an observer to " + stateIDToObserve + " because " + stateIDToObserve + " can not be found in state list.");
            }

            stateMap.get(stateIDToObserve).addObserver(this);
        }
    }

    protected abstract void initiateSubStateList(List<String> modifierTypes);

    public String getStateID()
    {
        return stateID;
    }

    public String getType()
    {
        return type;
    }

    public String getSubType()
    {
        return subType;
    }

    public String getCategory()
    {
        return category;
    }

    public boolean isUserEntry()
    {
        return userEntry != null ? userEntry : false;
    }

    public boolean isHidden()
    {
        return hidden != null && hidden;
    }

    public Object getSubTotal(String modifierType)
    {
        return subStateList.get(modifierType).getActiveModifier();
    }

    public Set<String> getSupportedModifierTypes()
    {
        return subStateList.keySet();
    }

    public Object remove(String modifierType, Object source)
    {
        if (!subStateList.containsKey(modifierType))
        {
            throw new UnsupportedOperationException(getStateID() + ": ModifierType does not exist -> " + modifierType);
        }

        SubState subState = subStateList.get(modifierType);
        Object removedModifier = subState.removeModifier(source);

        setChanged();
        notifyObservers();

        return removedModifier;
    }

    public void add(String modifierType, Object source, Object value)
    {
        if (!subStateList.containsKey(modifierType))
        {
            throw new UnsupportedOperationException(getStateID() + ": ModifierType does not exist -> " + modifierType);
        }

        SubState subState = subStateList.get(modifierType);
        subState.addModifier(source, value);

        //logger.info(getStateID() + ": Total -> " + getTotal() + ", old total -> " + oldTotal);
        setChanged();
        notifyObservers();
    }

    public void update(Observable o, Object arg)
    {
        State state = (State) o;
        add(state.getSubType(), state, state.getTotal());
    }

    @Override
    public String toString()
    {
        StringBuffer buffer = new StringBuffer();
        buffer.append(getStateID()).append(" ").append(getTotal()).append(" ( ");

        for (SubState subState : subStateList.values())
        {
            String subStateDescription = subState.toString();

            if (!"".equals(subStateDescription))
            {
                buffer.append("(").append(subStateDescription).append(") ");
            }
        }

        buffer.append(") ");        
        return buffer.toString();
    }

    public V4State toV4State()
    {
        String initialValue = "";
        Object initialValueObj = getSubTotal("Initial");
        if (null != initialValueObj)
        {
            initialValue = initialValueObj.toString();
        }

        v4State.setInitialValue(initialValue);
        return v4State;
    }
}
