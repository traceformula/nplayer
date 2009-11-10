package rpg.v4.middleware.util.xml;

import rpg.v4.middleware.jaxb.*;
import rpg.v4.middleware.util.FileKit;
import rpg.v4.middleware.util.collection.ObservableArrayList;
import rpg.v4.middleware.util.deepcopy.FastDeepCopy;
import rpg.v4.server.entity.Entity;
import rpg.v4.server.entity.slot.Slot;
import rpg.v4.server.state.State;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.util.List;

/**
 * Saves and loads entities
 */
public class XMLEntityKit extends XMLKit
{
    private static final String SAVE_LOCATION = "entities";
    private static final String DIVIDER = "__";
    private static final String XML_SUFFIX = ".xml";

    private static FileKit fileKit = FileKit.getInstance();

    private static XMLEntityKit instance = new XMLEntityKit();
    private ObservableArrayList<V4EntityNameKeyPair> availableEntities;

    private XMLEntityKit()
    {
        availableEntities = new ObservableArrayList<V4EntityNameKeyPair>();

        File entitiesDirectory = fileKit.createFile(SAVE_LOCATION);
        String[] entities = entitiesDirectory.list();

        for (String entityFileName : entities)
        {
            int lastDividePos = entityFileName.lastIndexOf(DIVIDER);
            String name = entityFileName.substring(0, lastDividePos);
            // Do not include the divider.
            String key = entityFileName.substring(lastDividePos);
            key = key.replaceFirst(DIVIDER, "").replace(XML_SUFFIX, "");
            V4EntityNameKeyPair capsule = XMLFactory.createV4EntityNameKeyPair(name, key);
            System.out.println(entityFileName + ", " + name + ", " + key);
            availableEntities.add(capsule);
        }
    }

    public static XMLEntityKit instance()
    {
        return instance;
    }

    public ObservableArrayList<V4EntityNameKeyPair> getAvailableEntities()
    {
        return availableEntities;
    }

    public void save(Entity entity)
    {
        V4Entity v4Entity = XMLFactory.createEntity();

        // Set states
        List<V4State> stateList = v4Entity.getV4State();
        for (State state : entity.getStateMap().values())
        {
            V4State v4State = state.toV4State();
            stateList.add(v4State);
        }

        // Set Inventory
        V4Entity.V4Inventory inventory = v4Entity.getV4Inventory();
        for (V4ItemCapsule itemCapsule : entity.getInventoryList())
        {
            inventory.getV4ItemCapsule().add(itemCapsule);
        }

        // Set equipped objects
        List<V4BodyPart> bodyPartList = entity.getRace().getV4BodyPart();
        V4Entity.V4EquippedObjects equippedObjects = v4Entity.getV4EquippedObjects();
        for (Slot slot : entity.getEquipmentSlotManager().getEquippedSlotList())
        {
            String slotName = slot.getName();
            System.out.println("SlotName: " + slotName);

            for (V4BodyPart bodyPart : bodyPartList)
            {
                if (bodyPart.getName().equals(slotName))
                {
                    V4BodyPart cleanBodyPart = (V4BodyPart) FastDeepCopy.deepCopy(bodyPart);
                    cleanBodyPart.setV4ItemCapsule(slot.getEquippedObject());
                    equippedObjects.getV4BodyPart().add(cleanBodyPart);
                }
            }
        }

        File saveFile = fileKit.createFile(SAVE_LOCATION + '/' + entity.getName() + DIVIDER + entity.getID() + XML_SUFFIX);
        saveFile(saveFile, v4Entity);
        availableEntities.add(entity.getNameKeyPair());
    }

    public V4Entity loadEntity(V4EntityNameKeyPair nkp)
    {
        File entitySaveFile = fileKit.getFile(SAVE_LOCATION + '/' + nkp.getName() + DIVIDER + nkp.getKey() + XML_SUFFIX);

        if (!entitySaveFile.exists())
        {
            throw new IllegalAccessError("File could not be loaded: " + entitySaveFile);
        }

        try
        {
            return (V4Entity) um.unmarshal(entitySaveFile);
        } catch (JAXBException e)
        {
            e.printStackTrace();
        }

        return null;
    }
}
