package rpg.v4.server.entity.slot;

import rpg.v4.middleware.jaxb.V4ItemCapsule;

/**
 * Created by IntelliJ IDEA.
 * User: Sven-Ivar Fjeld
 * Date: 07-Apr-2007
 * Time: 13:09:05
 * <p/>
 * Allows checking whether or not the equipment that is to be equipped
 * can actually be equipped by this entity. This takes into account
 * such things as number of hands free vs. num of hands required to hold
 * or weild the equipment, size etc.
 */
public interface SlotCriteria {

    /**
     * Does this object match the criteria?
     *
     * @param itemCapsule Equipment to be tested if it can be equipped or not
     * @return true if equipment passes this criteria, false otherwise (i.e. equipment cant be equipped in the current state
     */
    public boolean match(V4ItemCapsule itemCapsule);

}
