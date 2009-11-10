package rpg.v4.server.entity.slot;

import rpg.v4.middleware.jaxb.V4ItemCapsule;

import java.util.List;
import java.util.Observer;

/**
 * Created by IntelliJ IDEA.
 * User: Sven-Ivar Fjeld
 * Date: 07-Apr-2007
 * Time: 13:07:47
 *
 * A slot that can be used to equip items in
 */
public interface Slot
{
    /**
     * Returns the name of this slot, such as "Right hand"
     * @return Name of the slot
     */
    public String getName();

    /**
     * Attempts to equip the item. If the item meets this slot's criteria
     * then it is equipped, otherwise it isn't.
     * @param itemCapsule Item to be equipped.
     */
    public void equip(V4ItemCapsule itemCapsule);

    /**
     * Free up the slot by unequipping it.
     * @return
     */
    public List<V4ItemCapsule> unequip();

    /**
     * Checks if the slot is free for this item by checking it's criteria.
     * @param itemCapsule Item for which to check if this slot is available
     * @return True if item can be equipped, false if not.
     */
    public boolean isAvailable(V4ItemCapsule itemCapsule);

    public String toString();

    /**
     * Add an observer to this slot
     * @param o Observer to add
     */
    public void addObserver(Observer o);

    /**
     * Remove an observer from this slot.
     * @param o Observer to remove
     */
    public void deleteObserver(Observer o);

    /**
     * Get the equipped object of this slot.
     * @return The item being equipped in this slot.
     */
    public V4ItemCapsule getEquippedObject();
}
