package rpg.v4.middleware.util.deepcopy;

/**
 * Allows creation of deep copies of a JAXB item and wrappers
 */
public class JAXBDeepCopy {

    /*
    public static InventoryItem copyItem(InventoryItem item)
    {
        Object newJAXBItem = FastDeepCopy.deepCopy(item.getJAXBItem());

        if (newJAXBItem instanceof Weapon)
        {
            return new WeaponItem((Weapon) newJAXBItem);
        }
        else if (newJAXBItem instanceof ProtectiveItem.ArmorShield)
        {
            return new ArmorShieldItem((ProtectiveItem.ArmorShield) newJAXBItem);
        }

        throw new UnsupportedOperationException("Couldn't copy item " + item.getName() + " [" + newJAXBItem.getClass() + "]");
    }
    */
}
