package rpg.v4.server.entity.slot.impl;

import rpg.v4.middleware.jaxb.V4ItemCapsule;
import rpg.v4.server.entity.Entity;
import rpg.v4.server.entity.slot.SlotCriteria;

/**
 * Created by IntelliJ IDEA.
 * User: Sven-Ivar Fjeld
 * Date: 08-Apr-2007
 * Time: 12:41:41
 *
 * The object being passed in must be a weapon and the entity must have enough
 * free hands to equip it.
 * 
 */
public class WeaponCriteria implements SlotCriteria
{
    private Entity entity;

    public WeaponCriteria(Entity entity)
    {
        this.entity = entity;
    }

    public boolean match(V4ItemCapsule itemCapsule)
    {
        return null != itemCapsule.getV4Weapon();
    }
}
