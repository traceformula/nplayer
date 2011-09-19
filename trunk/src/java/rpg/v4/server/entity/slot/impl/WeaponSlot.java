package rpg.v4.server.entity.slot.impl;

import org.apache.log4j.Logger;
import rpg.v4.middleware.jaxb.V4ItemCapsule;
import rpg.v4.middleware.jaxb.V4Weapon;
import rpg.v4.server.entity.Entity;
import rpg.v4.server.entity.slot.AbstractSlot;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Sven-Ivar Fjeld
 * Date: 08-Apr-2007
 * Time: 12:09:16
 * <p/>
 * Allows a slot to be occupied with a weapon.
 */
public class WeaponSlot extends AbstractSlot
{

    public static final Logger logger = Logger.getLogger(WeaponSlot.class);

    public WeaponSlot(Entity entity, String name)
    {
        super(entity, name);
        criteria = new WeaponCriteria(entity);
    }

    public void equip(V4ItemCapsule itemCapsule)
    {
        if (isAvailable(itemCapsule))
        {
            // Means an equip took place
            if (itemCapsule != equippedObject)
            {
                equippedObject = itemCapsule;
                applySpecialProperties(true);
            } else
            {
                // Means an unequip took place, therefore nothigns equipped anymore.
                applySpecialProperties(false);
                equippedObject = null;
            }
        }
    }

    public List<V4ItemCapsule> unequip()
    {
        List<V4ItemCapsule> unequipped = new ArrayList<V4ItemCapsule>();
        if (equippedObject != null)
        {
            applySpecialProperties(false);
            unequipped.add(equippedObject);
            equippedObject = null;
        }

        return unequipped;
    }

    private void applySpecialProperties(boolean isAdding)
    {
        V4Weapon weapon = equippedObject.getV4Weapon();
        logger.info("Weapon: " + weapon.getName());
        super.applySpecialProperties(weapon.getV4ModifierList().getV4Modifier(), isAdding);
    }

    public String toString()
    {
        if (equippedObject != null)
        {
            return equippedObject.getName();
        }

        return "";
    }
}
