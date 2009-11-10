package rpg.v4.middleware.util.xml;

import rpg.v4.middleware.jaxb.V4Item;
import rpg.v4.middleware.jaxb.V4ItemCapsule;
import rpg.v4.middleware.jaxb.V4ItemList;
import rpg.v4.middleware.util.FileKit;
import rpg.v4.middleware.util.collection.ObservableArrayList;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.util.Collections;

/**
 * Loads and saves the armors in the armor XML file.
 */
public class XMLItemKit extends XMLKit
{
    private static final String FILE_NAME = "Item";
    private static XMLItemKit instance = new XMLItemKit();
    private static ObservableArrayList<String> itemNames;
    private static FileKit fileKit = FileKit.getInstance();
    private V4ItemList itemList;

    public static XMLItemKit instance()
    {
        return instance;
    }

    private XMLItemKit()
    {
        try
        {
            itemList = (V4ItemList) um.unmarshal(getFile(FILE_NAME));
        } catch (JAXBException e)
        {
            e.printStackTrace();
        }

        itemNames = new ObservableArrayList<String>(itemList.getV4Item().size());
        for (V4Item weapon : itemList.getV4Item())
        {
            itemNames.add(weapon.getName());
        }

        Collections.sort(itemNames);
    }

    public V4Item getItem(String armorID)
    {
        for (V4Item item : itemList.getV4Item())
        {
            if (item.getName().equals(armorID))
            {
                return item;
            }
        }

        throw new IllegalStateException("Armor '" + armorID + "' not found.");
    }

    public boolean exists(String armorName)
    {
        return itemNames.contains(armorName);
    }

    /**
     * Write armor to file, overwriting existing file if nessecary.
     *
     * @param item The new armor to save
     */
    public void save(V4Item item)
    {
        // If this is an entierly new weapon, add it to names list
        if (!itemNames.contains(item.getName()))
        {
            itemList.getV4Item().add(item);
            itemNames.add(item.getName());
        } else
        {
            // Overwrite existing item
            V4Item oldItem = getItem(item.getName());
            itemList.getV4Item().remove(oldItem);
            itemList.getV4Item().add(item);
        }

        File saveFile = fileKit.createFile("misc/" + FILE_NAME + ".xml");
        saveFile(saveFile, itemList);
    }

    public ObservableArrayList<String> getAvailableItems()
    {
        return itemNames;
    }

    public V4ItemCapsule getItemCapsule(String name)
    {
        V4Item v4Item = getItem(name);
        return XMLFactory.createItemCapsule(v4Item);
    }
}