package rpg.v4.client.gui.vaults.battle.entryobjects;

import rpg.v4.client.gui.util.factories.LabelFactory;
import rpg.v4.client.gui.util.picker.impl.StringPicker;
import rpg.v4.middleware.jaxb.V4ItemCapsule;
import rpg.v4.middleware.util.StringConstants;
import rpg.v4.server.entity.Entity;
import rpg.v4.server.state.State;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by IntelliJ IDEA.
 * User: fjelds
 * Date: Nov 5, 2009
 * Time: 10:42:01 AM
 *
 * Displays the BAB number in a state.
 */
public class BabStateEntry implements Observer, PropertyChangeListener, GenericEntry
{
    private State currentBABState;
    private V4ItemCapsule currentWeapon;
    private StringPicker weaponPicker;
    private Entity entity;
    private JLabel stateLabel = LabelFactory.createHeaderLabelNonPadded("");

    public BabStateEntry(StringPicker weaponPicker, Entity entity)
    {
        this.weaponPicker = weaponPicker;
        this.entity = entity;

        weaponPicker.addPropertyChangeListener("text", this);
        propertyChange(null);
    }

    public JComponent getJComponent()
    {
        return stateLabel;
    }

    public void disable()
    {
        if (currentBABState != null)
        {
            currentBABState.deleteObserver(this);
        }

        weaponPicker.setEnabled(false);
        weaponPicker.removePropertyChangeListener(this);
    }

    public void update(Observable observable, Object o)
    {
        stateLabel.setText(currentBABState.getTotal().toString());
        stateLabel.setToolTipText("Weapon BAB: " + currentBABState.toString());

        stateLabel.repaint();
    }

    public void propertyChange(PropertyChangeEvent propertyChangeEvent)
    {
        String weaponName = weaponPicker.getText();
        for (V4ItemCapsule itemCapsule : entity.getWeaponsFromInventoryList())
        {
            if (itemCapsule.getName().equals(weaponName))
            {
                // If it is different, remove me as an observer from the previous BAB state,
                // and add me to the new state.
                if (currentBABState != null)
                {
                    currentBABState.deleteObserver(this);
                }
                
                currentWeapon = itemCapsule;
                String babStateID = StringConstants.DYNAMIC_BAB + currentWeapon.getName();
                currentBABState = entity.getDynamicState(babStateID);
                currentBABState.addObserver(this);
                update(null, null);
                return;
            }
        }
    }
}
