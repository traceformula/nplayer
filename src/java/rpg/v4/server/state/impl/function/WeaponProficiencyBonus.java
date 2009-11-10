package rpg.v4.server.state.impl.function;

import rpg.v4.middleware.jaxb.V4Weapon;
import rpg.v4.middleware.util.xml.XMLWeaponKit;
import rpg.v4.server.state.Function;
import rpg.v4.server.state.State;

import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;

/**
 * Propagates the proficiency bonus of the weapon if the character is proficient in it.
 */
public class WeaponProficiencyBonus extends Observable implements Function, Observer, Serializable
{
    private Integer propagatedValue = 0;
    private State selectedWeaponState = null, addedWeaponProficiencies = null;
    private State owner;

    public void addStateToObserve(State stateToObserve)
    {
        if (selectedWeaponState == null)
        {
            selectedWeaponState = stateToObserve;
        } else if (addedWeaponProficiencies == null)
        {
            addedWeaponProficiencies = stateToObserve;
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
     * @param o
     * @param arg
     */
    public void update(Observable o, Object arg)
    {
        State state = (State) o;
        String weaponID = (String) selectedWeaponState.getTotal();

        // If a weapon has been selected, and suddenly the proficiency changes, check
        // if the bonus is applicable and pass out the proficiency if changed.
        if (state == addedWeaponProficiencies || (null != weaponID && (!"".equals(weaponID))))
        {

            String proficiencies = (String) selectedWeaponState.getTotal();
            V4Weapon weapon = XMLWeaponKit.instance().getWeapon(weaponID);

            // Check weapon group and weapon ID with proficiencies
            String weaponGroup = weapon.getGroup();
            boolean allowBonus = proficiencies.contains(weaponGroup) || proficiencies.contains(weaponID);
            Integer bonus = allowBonus ? weapon.getProficiencyBonus() : 0;
            propagate(bonus);
        } else
        {
            // Weapon has been unselected. Send out nothing in bonus.
            propagate(0);
        }
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
