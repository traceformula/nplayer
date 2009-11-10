package rpg.v4.middleware.util.xml;

import rpg.v4.middleware.jaxb.V4GenericAction;
import rpg.v4.middleware.jaxb.V4GenericActionList;
import rpg.v4.middleware.util.FileKit;
import rpg.v4.middleware.util.collection.ObservableArrayList;
import rpg.v4.middleware.util.deepcopy.FastDeepCopy;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.util.Collections;
import java.util.List;

/**
 * Loads and saves powers, actions an feats into seperate files.
 */
public class XMLActionKit extends XMLKit
{
    private static final String POWER = "Power", ACTION = "Action", FEAT = "Feat";
    private static final String POWER_FILE_NAME = "Powers",
            ACTION_FILE_NAME = "Actions",
            FEATS_FILE_NAME = "Feats";
    private static XMLActionKit instance = new XMLActionKit();
    private static FileKit fileKit = FileKit.getInstance();
    private static ObservableArrayList<String> powerNames, actionNames, featNames;
    private V4GenericActionList powerList, actionList, featList;

    public static XMLActionKit instance()
    {
        return instance;
    }

    private XMLActionKit()
    {
        try
        {
            powerList = (V4GenericActionList) um.unmarshal(getFile(POWER_FILE_NAME));
            actionList = (V4GenericActionList) um.unmarshal(getFile(ACTION_FILE_NAME));
            featList = (V4GenericActionList) um.unmarshal(getFile(FEATS_FILE_NAME));
        } catch (JAXBException e)
        {
            e.printStackTrace();
        }

        powerNames = extractNames(powerList);
        actionNames = extractNames(actionList);
        featNames = extractNames(featList);
    }

    private ObservableArrayList<String> extractNames(V4GenericActionList genericActionrList)
    {
        ObservableArrayList<String> nameList = new ObservableArrayList<String>(genericActionrList.getV4GenericAction().size());
        for (V4GenericAction genericAction : genericActionrList.getV4GenericAction())
        {
            nameList.add(genericAction.getName());
        }
        Collections.sort(nameList);
        return nameList;
    }

    /**
     * Loops through all lists in the following order returning the first element found:
     * Powers, Actions, Feats.
     * @param name
     * @return
     */
    public V4GenericAction getGenericAction(String name)
    {
        V4GenericAction v4GenericAction = getActionFromAllLists(name, powerList.getV4GenericAction());
        if (v4GenericAction != null)
        {
            return (V4GenericAction) FastDeepCopy.deepCopy(v4GenericAction);
        }

        v4GenericAction = getActionFromAllLists(name, actionList.getV4GenericAction());
        if (v4GenericAction != null)
        {
            return (V4GenericAction) FastDeepCopy.deepCopy(v4GenericAction);
        }

        v4GenericAction = getActionFromAllLists(name, featList.getV4GenericAction());
        if (v4GenericAction != null)
        {
            return (V4GenericAction) FastDeepCopy.deepCopy(v4GenericAction);
        }

        throw new IllegalStateException("Generic action '" + name + "' not found.");
    }

    private V4GenericAction getActionFromAllLists(String name, List<V4GenericAction> v4GenericActionList)
    {
        for (V4GenericAction v4GenericAction : v4GenericActionList)
        {
            if (v4GenericAction.getName().equals(name))
            {
                return (V4GenericAction) FastDeepCopy.deepCopy(v4GenericAction);
            }
        }

        return null;
    }

    public boolean exists(String armorName)
    {
        return powerNames.contains(armorName);
    }

    /**
     * Write power/action/feat to file, overwriting existing file if nessecary. Also, if new,
     * add it to the corresponding names list.
     *
     * @param v4GenericAction The new power/action/feat to save
     */
    public void save(V4GenericAction v4GenericAction)
    {
        File saveFile;
        V4GenericActionList listToSave;
        if (POWER.equals(v4GenericAction.getType()))
        {
            manageItemEntry(v4GenericAction, powerNames, powerList);
            saveFile = fileKit.createFile("misc/" + POWER_FILE_NAME + ".xml");
            listToSave = powerList;
        } else if (ACTION.equals(v4GenericAction.getType()))
        {
            manageItemEntry(v4GenericAction, actionNames, actionList);
            saveFile = fileKit.createFile("misc/" + ACTION_FILE_NAME + ".xml");
            listToSave = actionList;
        } else if (FEAT.equals(v4GenericAction.getType()))
        {
            manageItemEntry(v4GenericAction, featNames, featList);
            saveFile = fileKit.createFile("misc/" + FEATS_FILE_NAME + ".xml");
            listToSave = featList;
        } else
        {
            throw new UnsupportedOperationException("Can not save generic action of type " + v4GenericAction.getType() + ". It must be " + POWER + ", " + FEAT + " or " + ACTION);
        }

        saveFile(saveFile, listToSave);
    }

    private void manageItemEntry(V4GenericAction item, ObservableArrayList<String> namesList, V4GenericActionList v4GenericActionList)
    {
        if (!namesList.contains(item.getName()))
        {
            v4GenericActionList.getV4GenericAction().add(item);
            namesList.add(item.getName());
        } else {
            V4GenericAction actionToRemove = null;
            for (V4GenericAction existingAction : v4GenericActionList.getV4GenericAction())
            {
                if (existingAction.getName().equals(item.getName()))
                {
                    actionToRemove = existingAction;
                }
            }

            v4GenericActionList.getV4GenericAction().remove(actionToRemove);
            v4GenericActionList.getV4GenericAction().add(item);
        }
    }

    public static ObservableArrayList<String> getActionNames()
    {
        return actionNames;
    }

    public static ObservableArrayList<String> getFeatNames()
    {
        return featNames;
    }

    public static ObservableArrayList<String> getPowerNames()
    {
        return powerNames;
    }

    public ObservableArrayList<String> getAvailableFeats()
    {
        return featNames;
    }

    public ObservableArrayList<String> getAvailablePowers()
    {
        return powerNames;
    }

    public ObservableArrayList<String> getAvailableActions()
    {
        return actionNames;
    }
}
