package rpg.v4.server.entity.slot.impl;

import rpg.v4.middleware.jaxb.V4Item;
import rpg.v4.middleware.jaxb.V4ItemCapsule;
import rpg.v4.server.entity.slot.SlotCriteria;

/**
 * Created by IntelliJ IDEA.
 * User: Sven-Ivar Fjeld
 * Date: 10-Apr-2007
 * Time: 20:16:01
 *
 * Tells wether it's of this geneeric item's type. What this means is that if this slot is a ring
 * slot then it checks if the item being equipped is a ring. If it is, return true, otherwise false.
 *
 * Likewise this could be used for armors to check if a heavy armor slot is being equipped with say
 * a light armor. ATM. there's a separate armor criteria that does this.
 */
public class GenericCriteria implements SlotCriteria
{
    private String type;

    public GenericCriteria(String type)
    {
        this.type = type;
    }

    public boolean match(V4ItemCapsule itemCapsule)
    {
        V4Item item = itemCapsule.getV4Item();
        if (null != item)
        {
            return type.equals(item.getType());
        }
        return false;
    }
}