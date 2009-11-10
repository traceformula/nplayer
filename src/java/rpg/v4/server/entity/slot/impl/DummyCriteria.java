package rpg.v4.server.entity.slot.impl;

import rpg.v4.middleware.jaxb.V4ItemCapsule;
import rpg.v4.server.entity.slot.SlotCriteria;

/**
 * Created by IntelliJ IDEA.
 * User: Sven-Ivar Fjeld
 * Date: 07-Apr-2007
 * Time: 13:37:38
 */
public class DummyCriteria implements SlotCriteria
{

    public boolean match(V4ItemCapsule itemCapsule)
    {
        return false;
    }
}
