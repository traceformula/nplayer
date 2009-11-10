package rpg.v4.client.gui.vaults.battle.entryobjects;

import rpg.v4.client.gui.util.factories.LabelFactory;
import rpg.v4.client.gui.util.picker.impl.StringPicker;
import rpg.v4.middleware.jaxb.V4EntityNameKeyPair;
import rpg.v4.server.battle.InitiativeOrderManager;
import rpg.v4.server.entity.Entity;
import rpg.v4.server.state.State;
import rpg.v4.server.vaults.EntityCollectionManager;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by IntelliJ IDEA.
 * User: fjelds
 * Date: Nov 5, 2009
 * Time: 3:34:15 PM
 *
 * Displays the total of the state of the defender, like the total of the Armor Class if that's what's specified.
 */
public class DefenderStateEntry implements Observer, PropertyChangeListener, GenericEntry
{
    private StringPicker targetPicker;
    private Entity currentTarget;
    private String defenderStateID;
    private State currentState;
    private JLabel label = LabelFactory.createHeaderLabelNonPadded("");

    public DefenderStateEntry(StringPicker targetPicker, String defenderStateID)
    {

        this.targetPicker = targetPicker;
        targetPicker.addPropertyChangeListener("text", this);
        this.defenderStateID = defenderStateID;
        propertyChange(null);
    }

    public void update(Observable observable, Object o)
    {
        int total = (Integer) currentState.getTotal();
        label.setText(total + "");
        label.setToolTipText(currentTarget.getName() + " " + defenderStateID);
    }

    public void propertyChange(PropertyChangeEvent propertyChangeEvent)
    {
        List<V4EntityNameKeyPair> battleParticipants = InitiativeOrderManager.getInstance().getEntityInitiativeOrderList();
        String targetName = targetPicker.getText();
        for (V4EntityNameKeyPair participant : battleParticipants)
        {
            if (targetName.equals(participant.getName()))
            {
                if (currentState != null)
                {
                    currentState.deleteObserver(this);
                }

                currentTarget = EntityCollectionManager.get(participant);
                currentState = currentTarget.getState(defenderStateID);
                currentState.addObserver(this);
                update(null, null);
                return;
            }
        }
    }

    public JComponent getJComponent()
    {
        return label;
    }

    public void disable()
    {
        if (currentState != null)
        {
            currentState.deleteObserver(this);
        }

        targetPicker.removePropertyChangeListener(this);
    }
}
