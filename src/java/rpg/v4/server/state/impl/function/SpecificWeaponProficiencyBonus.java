package rpg.v4.server.state.impl.function;

import org.apache.log4j.Logger;
import rpg.v4.middleware.jaxb.V4ItemCapsule;
import rpg.v4.middleware.jaxb.V4Weapon;
import rpg.v4.server.state.Function;
import rpg.v4.server.state.State;

import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;

/**
 * Propagates the proficiency bonus of the weapon if the character is proficient in it.
 */
public class SpecificWeaponProficiencyBonus extends Observable implements Function, Observer, Serializable
{
    private static final Logger logger = Logger.getLogger(SpecificWeaponProficiencyBonus.class);

    private Integer propagatedValue = 0;
    private State addedWeaponProficiencies = null;
    private State owner;
    private V4ItemCapsule itemCapsule;

    public SpecificWeaponProficiencyBonus(V4ItemCapsule itemCapsule)
    {
        this.itemCapsule = itemCapsule;
    }

    public void addStateToObserve(State stateToObserve)
    {
        if (addedWeaponProficiencies == null)
        {
            addedWeaponProficiencies = stateToObserve;
            update(null, null);
        } else
        {
            throw new UnsupportedOperationException("Weapon Proficiency Bonus function can only observe two states");
        }

        stateToObserve.addObserver(this);
    }

    public void setOwner(State owner)
    {
        this.owner = owner;
    }

    /**
     * Either a weapon can be selected/deselected/changed, or the proficiency state can be changed.
     *
     * @param o
     * @param arg
     */
    public void update(Observable o, Object arg)
    {
        String proficiencies = (String) addedWeaponProficiencies.getTotal();
        String weaponID = itemCapsule.getName();
        V4Weapon weapon = itemCapsule.getV4Weapon();

        // Check weapon group and weapon ID with proficiencies
        String weaponGroup = weapon.getGroup();
        String weaponTypCategory = weapon.getCategory() + " " + weapon.getType();
        boolean allowBonus = proficiencies.contains(weaponTypCategory) || proficiencies.contains(weaponGroup) || proficiencies.contains(weaponID);

        Integer bonus = allowBonus ? weapon.getProficiencyBonus() : 0;
        propagate(bonus);
    }

    private void propagate(Integer bonus)
    {
        // If the bonus has changed, send it out, otherwise ignore.
        if (bonus != propagatedValue)
        {
            propagatedValue = bonus;
            owner.add("Proficiency", this, propagatedValue);
        }
    }

    @Override
    public String toString()
    {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(propagatedValue).append(" Weapon proficiency bonus ");
        return stringBuffer.toString();
    }
}
