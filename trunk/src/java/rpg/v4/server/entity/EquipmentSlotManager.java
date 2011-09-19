package rpg.v4.server.entity;


import org.apache.log4j.Logger;
import rpg.v4.middleware.jaxb.V4BodyPart;
import rpg.v4.middleware.jaxb.V4ItemCapsule;
import rpg.v4.middleware.jaxb.V4Race;
import rpg.v4.server.entity.slot.Slot;
import rpg.v4.server.entity.slot.impl.GenericSlot;
import rpg.v4.server.entity.slot.impl.MultiSlot;
import rpg.v4.server.entity.slot.impl.WeaponSlot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Sven-Ivar Fjeld
 * Date: 07-Apr-2007
 * Time: 11:00:13
 * <p/>
 * Holds a list of every body part of an entity which can be used to
 * equip some form of item.
 */
public class EquipmentSlotManager
{
    private static Logger logger = Logger.getLogger(EquipmentSlotManager.class);
    private Entity entity;
    private List<MultiSlot> slotList;

    public EquipmentSlotManager(Entity entity)
    {
        this.entity = entity;
        slotList = new ArrayList<MultiSlot>();
    }

    public List<Slot> isEquipped(V4ItemCapsule item)
    {
        List<Slot> slots = new ArrayList<Slot>();

        for (MultiSlot slot : slotList)
        {
            V4ItemCapsule equippedObject = slot.getEquippedObject();

            if (null != equippedObject)
            {
                if (item == equippedObject || item.equals(equippedObject) || item.getName().equals(equippedObject.getName()))
                {
                    slots.add(slot);
                }
            }
        }
        return slots;
    }

    /**
     * Return all slots where this object can be equipped. Can't equip it if it is already
     * equipped.
     *
     * @param item The equipment to evaluate
     * @return a list of slots where this piece of equipment could potentially be equipped
     */
    public List<Slot> getEquippableSlots(V4ItemCapsule item)
    {

        List<Slot> slotList = new ArrayList<Slot>();

        // If the equipment isn't already equipped.
        if (isEquipped(item).isEmpty())
        {
            for (Slot slot : this.slotList)
            {
                if (slot.isAvailable(item))
                {
                    slotList.add(slot);
                }
            }
        }

        return slotList;
    }

    /**
     * Add a slot to a slot holder (A MultiSlot.java). If a slot holder doesn't exist
     * then a new one is created.
     *
     * @param slot to be added.
     */
    public void add(Slot slot)
    {
        MultiSlot multiSlot = getMultiSlot(slot.getName());
        multiSlot.add(slot);
    }

    protected MultiSlot getMultiSlot(String slotName)
    {
        for (MultiSlot slot : slotList)
        {
            if (slotName.equals(slot.getName()))
            {
                return slot;
            }
        }

        // If it doesn't exist then create a new one and add it to this slot list.
        MultiSlot slot = new MultiSlot(entity, slotName);
        slotList.add(slot);
        return slot;
    }

    private boolean containsSlotName(String slotName)
    {
        for (MultiSlot slot : slotList)
        {
            if (slotName.equals(slot.getName()))
            {
                return true;
            }
        }

        return false;
    }

    /**
     * Remove a slot for all the slot holders.
     *
     * @param slot to remove
     * @return true if it was removed once or more, false if it was never removed
     */
    public boolean removeSlot(Slot slot)
    {
        boolean wasRemoved = false;
        for (MultiSlot slotHolder : slotList)
        {
            wasRemoved = wasRemoved || slotHolder.remove(slot);
        }
        return wasRemoved;
    }

    public List<MultiSlot> getSlotList()
    {
        return slotList;
    }

    public List<Slot> getEquippedSlotList()
    {
        List<Slot> slotList = new ArrayList<Slot>();
        for (Slot slot : getSlotList())
        {
            if (null != slot.getEquippedObject())
            {
                slotList.add(slot);
            }
        }

        return slotList;
    }

    public void setRaceSlots(V4Race race)
    {
        Map unequippedObjects = unequipAllObjects();

        // If a race changes then don't update any observers until everything is
        // cleared and parsed. This is to avoid unwanted gui updates which later
        // break updates, and also speeds tings up.
        slotList.clear();
        setEquipmentSlots(entity, race.getV4BodyPart());
        equipObjects(unequippedObjects);
    }

    private void setEquipmentSlots(Entity entity, List<V4BodyPart> bodyPartList)
    {
        for (V4BodyPart bodyPart : bodyPartList)
        {
            for (V4BodyPart.CompatibleItem cat : bodyPart.getCompatibleItem())
            {
                String itemType = cat.getType();

                Slot slot;
                if (itemType.equals("Weapon"))
                {
                    slot = new WeaponSlot(entity, bodyPart.getName());
                } else
                {
                    slot = new GenericSlot(entity, bodyPart.getName(), itemType);
                }

                this.add(slot);
            }
        }
    }


    public Map<String, List<V4ItemCapsule>> unequipAllObjects()
    {
        Map<String, List<V4ItemCapsule>> map = new HashMap<String, List<V4ItemCapsule>>();

        for (MultiSlot slot : slotList)
        {
            logger.info("Slot: " + slot.toString());
            List<V4ItemCapsule> unequippedObjects = slot.unequip();
            map.put(slot.getName(), unequippedObjects);
        }

        return map;
    }

    public void equipObjects(Map<String, List<V4ItemCapsule>> map)
    {
        for (String key : map.keySet())
        {
            if (containsSlotName(key))
            {
                Slot slot = getMultiSlot(key);
                List<V4ItemCapsule> equipment = map.get(key);
                for (V4ItemCapsule itemCapsule : equipment)
                {
                    slot.equip(itemCapsule);
                }
            }
        }
    }
}
