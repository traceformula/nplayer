package rpg.v4.client.gui.vaults.battle.entryobjects;

import rpg.v4.client.gui.util.picker.impl.StringPicker;
import rpg.v4.middleware.jaxb.V4ItemCapsule;
import rpg.v4.server.entity.Entity;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by IntelliJ IDEA.
 * User: fjelds
 * Date: Nov 5, 2009
 * Time: 10:22:06 AM
 *
 * Manages the associated inventory list
 */
public class WeaponEntry implements Observer, GenericEntry
{
    private StringPicker weaponPicker = new StringPicker();
    private Entity owner;

    public WeaponEntry(Entity owner)
    {
        this.owner = owner;

        // Observe the inventory list. If it changes, update the available items.
        owner.getInventoryList().addObserver(this);
        update(null, null);
    }

    public JComponent getJComponent()
    {
        return weaponPicker;
    }

    public void disable()
    {
        owner.getInventoryList().deleteObserver(this);
    }

    public void update(Observable observable, Object o)
    {
        List<String> weapons = new ArrayList<String>();
        for (V4ItemCapsule itemCapsule : owner.getWeaponsFromInventoryList())
        {
            weapons.add(itemCapsule.getName());
        }

        weaponPicker.setDataRange(weapons);
    }
}
