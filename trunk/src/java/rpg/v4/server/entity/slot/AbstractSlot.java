package rpg.v4.server.entity.slot;

import org.apache.log4j.Logger;
import rpg.v4.middleware.jaxb.V4ItemCapsule;
import rpg.v4.middleware.jaxb.V4Modifier;
import rpg.v4.server.entity.Entity;

import java.util.List;
import java.util.Observable;

/**
 * Created by IntelliJ IDEA.
 * User: Sven-Ivar Fjeld
 * Date: 07-Apr-2007
 * Time: 18:09:37
 *
 * Base class of all slots. A slot is one location on a characters body, such as
 * a right hand, head, finger etc., that can be equipped with some sort of item.
 * Items can only be equipped if it meets a slot's SlotCriteria.
 *
 * Slots are automatically created for entities using their equippable location
 * XML defenitions in the Race file.
 */
public abstract class AbstractSlot extends Observable implements Slot
{

    public static final Logger logger = Logger.getLogger(AbstractSlot.class);

    protected Entity entity;
    protected String name;
    protected SlotCriteria criteria;
    protected V4ItemCapsule equippedObject;
    protected int handNumberIndicator;

    /**
     * Creates a new slot
     * @param entity Entity that this slot belongs to
     * @param name The name of this slot, usually taken from the name in the XML defention of a character.
     */
    public AbstractSlot(Entity entity, String name)
    {
        this.entity = entity;
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public abstract void equip(V4ItemCapsule item);

    public abstract List<V4ItemCapsule> unequip();

    public boolean isAvailable(V4ItemCapsule item)
    {
        return criteria.match(item);
    }

    public abstract String toString();

    public V4ItemCapsule getEquippedObject()
    {
        return equippedObject;
    }

    protected void applySpecialProperties(List<V4Modifier> modifiers, boolean isAdding)
    {
        if (isAdding)
            entity.applyModifiers(modifiers);
        else
            entity.removeModifiers(modifiers);
    }
}
