package rpg.v4.client.gui.forge.modifier;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: fjelds
 * Date: Sep 13, 2009
 * Time: 11:21:15 PM
 *
 * Provides useful lists for the modifiers
 */
public class ModifierListKit
{
    private static final List<Integer> integerList = new ArrayList(20);

    private static final List<String> armorTypes = new ArrayList(6),
                                      weaponTypes = new ArrayList(8);

    static {
        for (int i=-5; i < 20; i++)
        {
            integerList.add(i);
        }

        armorTypes.add("Cloth Armor");
        armorTypes.add("Leather Armor");
        armorTypes.add("Hide Armor");
        armorTypes.add("Chainmail");
        armorTypes.add("Scale Armor");
        armorTypes.add("Plate Armor");

        weaponTypes.add("Simple Melee");
        weaponTypes.add("Simple Ranged");
        weaponTypes.add("Military Melee");
        weaponTypes.add("Military Ranged");
        weaponTypes.add("Superior Melee");
        weaponTypes.add("Superior Ranged");
        weaponTypes.add("Improvised Melee");
        weaponTypes.add("Improvised Ranged");
    }

    public static List getList(String listID)
    {
        if (listID.equals("Added Armor prof.") || listID.equals("Removed Armor prof."))
            return armorTypes;
        else if (listID.equals("Added Weapon prof.") || listID.equals("Removed Weapon prof."))
            return weaponTypes;
        return integerList;
    }

    public static boolean isStringListRequired(String listID)
    {
        return listID.equals("Added Armor prof.") || listID.equals("Removed Armor prof.") ||
                listID.equals("Added Weapon prof.") || listID.equals("Removed Weapon prof.");
    }
}
