package rpg.v4.server.entity.slot.impl;

import org.apache.log4j.Logger;
import rpg.v4.middleware.jaxb.V4ItemCapsule;
import rpg.v4.server.entity.Entity;
import rpg.v4.server.entity.slot.AbstractSlot;
import rpg.v4.server.entity.slot.Slot;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Sven-Ivar Fjeld
 * Date: 08-Apr-2007
 * Time: 11:32:36
 * <p/>
 * Contains many slots that can be used for the same equipment slot,
 * such as hand which can use shields, weapons or weapons+bucklers.
 */
public class MultiSlot extends AbstractSlot implements Slot
{

    public static final Logger logger = Logger.getLogger(MultiSlot.class);
    private List<Slot> slotList;

    public MultiSlot(Entity entity, String name)
    {
        super(entity, name);
        this.slotList = new ArrayList<Slot>();
    }

    @Override
    /**
     * Returns true if there is a slot available for this equipment.
     */
    public boolean isAvailable(V4ItemCapsule itemCapsule)
    {
        for (Slot slot : slotList)
        {
            if (slot.isAvailable(itemCapsule))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Equip all slots which can equip this equipment with it.
     * TODO: Possible bug if an item can be equipped by multiple slots?
     * @param itemCapsule
     */
    public void equip(V4ItemCapsule itemCapsule)
    {
        for (Slot slot : slotList)
        {
            slot.unequip();
        }
        setChanged();
        notifyObservers();
        
        for (Slot slot : slotList)
        {
            if (slot.isAvailable(itemCapsule))
            {
                slot.equip(itemCapsule);
            }
        }
        setChanged();
        notifyObservers();
    }

    /**
     * Unequip all slots.
     */
    public List<V4ItemCapsule> unequip()
    {
        List<V4ItemCapsule> unequipped = new ArrayList<V4ItemCapsule>();
        for (Slot slot : slotList)
        {
            unequipped.addAll(slot.unequip());
        }

        setChanged();
        notifyObservers();
        return unequipped;
    }

    /**
     * Add the slot to this slot holder unless already present.
     * @param slot to be added
     */
    public void add(Slot slot)
    {
        if (slotList.contains(slot))
        {
            throw new UnsupportedOperationException("Can't add slot - already in this multislot list.");
        }

        slotList.add(slot);
    }

    /**
     * Attempt to remove the slot from this slot holder.
     * @param slot to remove
     * @return false if unsuccesful (i.e. it didn't exist).
     */
    public boolean remove(Slot slot)
    {
        return slotList.remove(slot);
    }

    public String toString()
    {
        String slotString = "";
        for (Slot slot : slotList)
        {
            if (slotString.length() > 0)
            {
                slotString += "; ";
            }
            slotString += slot.toString();
        }
        return slotString;
    }

    @Override
    public V4ItemCapsule getEquippedObject()
    {
        for (Slot slot : slotList)
        {
            if (null != slot.getEquippedObject())
            {
                return slot.getEquippedObject();
            }
        }

        return null;
    }
}
