package rpg.v4.middleware.util.xml;

import rpg.v4.middleware.jaxb.V4ItemCapsule;
import rpg.v4.middleware.jaxb.V4Weapon;
import rpg.v4.middleware.jaxb.V4WeaponList;
import rpg.v4.middleware.util.FileKit;
import rpg.v4.middleware.util.collection.ObservableArrayList;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.util.Collections;

/**
 * Loads and saves the weapons in the weapon XML file.
 */
public class XMLWeaponKit extends XMLKit
{
    private static final String FILE_NAME = "Weapons";
    private static XMLWeaponKit instance = new XMLWeaponKit();
    private static ObservableArrayList<String> weaponNames;
    private static FileKit fileKit = FileKit.getInstance();
    private V4WeaponList weaponList;

    public static XMLWeaponKit instance()
    {
        return instance;
    }

    private XMLWeaponKit()
    {
        try
        {
            weaponList = (V4WeaponList) um.unmarshal(getFile(FILE_NAME));
        } catch (JAXBException e)
        {
            e.printStackTrace();
        }

        weaponNames = new ObservableArrayList<String>(weaponList.getV4Weapon().size());
        for (V4Weapon weapon : weaponList.getV4Weapon())
        {
            weaponNames.add(weapon.getName());
        }

        Collections.sort(weaponNames);
    }

    public V4Weapon getWeapon(String weaponID)
    {
        for (V4Weapon weapon : weaponList.getV4Weapon())
        {
            if (weapon.getName().equals(weaponID))
            {
                return weapon;
            }
        }

        throw new IllegalStateException("Weapon '" + weaponID + "' not found.");
    }

    public boolean exists(String weaponName)
    {
        return weaponNames.contains(weaponName);
    }

    /**
     * Write weapon to file, overwriting existing file if nessecary.
     *
     * @param weapon The new weapon to save
     */
    public void save(V4Weapon weapon)
    {
        // If this is an entierly new weapon, add it to names list
        if (!weaponNames.contains(weapon.getName()))
        {
            weaponList.getV4Weapon().add(weapon);
            weaponNames.add(weapon.getName());
        } else
        {
            // Overwrite existing item
            V4Weapon oldItem = getWeapon(weapon.getName());
            weaponList.getV4Weapon().remove(oldItem);
            weaponList.getV4Weapon().add(weapon);
        }

        File saveFile = fileKit.createFile("misc/" + FILE_NAME + ".xml");
        saveFile(saveFile, weaponList);
    }

    public ObservableArrayList<String> getAvailableWeapons()
    {
        return weaponNames;
    }

    public V4ItemCapsule getWeaponItemCapsule(String name)
    {
        V4Weapon weapon = getWeapon(name);
        return XMLFactory.createItemCapsule(weapon);
    }
}
