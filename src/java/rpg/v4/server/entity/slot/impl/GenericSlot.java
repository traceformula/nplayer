package rpg.v4.server.entity.slot.impl;

import org.apache.log4j.Logger;
import rpg.v4.middleware.jaxb.V4Item;
import rpg.v4.middleware.jaxb.V4ItemCapsule;
import rpg.v4.server.entity.Entity;
import rpg.v4.server.entity.slot.AbstractSlot;
import rpg.v4.server.entity.slot.SlotCriteria;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Sven-Ivar Fjeld
 * Date: 07-Apr-2007
 * Time: 18:08:59
 * <p/>
 * Equip a generic item, such as a ring, headband, cape, etc. Defined by the type.
 */
public class GenericSlot extends AbstractSlot
{

    public static final Logger logger = Logger.getLogger(GenericSlot.class);

    public GenericSlot(Entity entity, String name, String type)
    {
        super(entity, name);
        criteria = new GenericCriteria(type);
    }

    public GenericSlot(Entity entity, String name, String type, SlotCriteria criteria)
    {
        super(entity, name);
        this.criteria = criteria;
    }

    public void equip(V4ItemCapsule itemCapsule)
    {
        if (isAvailable(itemCapsule))
        {
            // If a different item is passed in the equip it. Otherwise it meas unequipe it.
            if (itemCapsule != equippedObject)
            {
                equippedObject = itemCapsule;
                applySpecialProperties(true);
            } else
            {
                // Means an unequip takes place, therefore nothing is equipped anymore.
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
        V4Item v4Item = equippedObject.getV4Item();
        super.applySpecialProperties(v4Item.getV4ModifierList().getV4Modifier(), isAdding);
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