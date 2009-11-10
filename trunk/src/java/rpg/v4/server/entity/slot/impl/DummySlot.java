package rpg.v4.server.entity.slot.impl;

import org.apache.log4j.Logger;
import rpg.v4.middleware.jaxb.V4ItemCapsule;
import rpg.v4.server.entity.Entity;
import rpg.v4.server.entity.slot.AbstractSlot;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Sven-Ivar Fjeld
 * Date: 07-Apr-2007
 * Time: 13:24:32
 */
public class DummySlot extends AbstractSlot
{

    public static final Logger logger = Logger.getLogger(DummySlot.class);

    public DummySlot(Entity entity, String name)
    {
        super(entity, name);
        criteria = new DummyCriteria();
    }

    public void equip(V4ItemCapsule itemCapsule)
    {
        logger.info("DummySlot only dummy equipping " + entity.getName() + "'s " + getName() + " with " + itemCapsule.toString());
    }

    public List unequip()
    {
        logger.info("DummySlot only dummy unequipping " + entity.getName() +"'s " + getName());
        return new ArrayList();
    }

    public String toString()
    {
        return "";
    }
}
