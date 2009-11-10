package rpg.v4.client.gui.vaults.battle;

import rpg.swingx.JTransparentPanel;
import rpg.v4.client.gui.util.picker.impl.StringPicker;
import rpg.v4.client.gui.vaults.battle.entryobjects.ActionOutcome;
import rpg.v4.client.gui.vaults.battle.entryobjects.GenericEntry;
import rpg.v4.middleware.jaxb.V4GenericAction;
import rpg.v4.server.entity.Entity;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Created by IntelliJ IDEA.
 * User: fjelds
 * Date: Nov 5, 2009
 * Time: 8:04:38 PM
 *
 * Displays the various results
 */
public class ResultsPanel extends JTransparentPanel implements PropertyChangeListener
{
    private V4GenericAction action;
    private StringPicker targetPicker;
    private ApplicableModifiersPanel hitPanel;
    private ApplicableModifiersPanel missPanel;
    private JLabel outcomeLabel;


    public ResultsPanel(V4GenericAction action, GenericEntry ao, StringPicker targetPicker, StringPicker weaponPicker, Entity entity)
    {
        this.action = action;
        this.targetPicker = targetPicker;

        V4GenericAction.HitModifiers hitModifiers = action.getHitModifiers();
        hitPanel = new ApplicableModifiersPanel(hitModifiers.getV4Modifier(), targetPicker, weaponPicker, entity);
        add(hitPanel, BorderLayout.NORTH);

        V4GenericAction.MissModifiers missModifiers = action.getMissModifiers();
        missPanel = new ApplicableModifiersPanel(missModifiers.getV4Modifier(), targetPicker, weaponPicker, entity);
        add(missPanel, BorderLayout.SOUTH);

        outcomeLabel = (JLabel) ao.getJComponent();
        outcomeLabel.addPropertyChangeListener("text", this);
        propertyChange(null);

        setBorder(BorderFactory.createEmptyBorder(0,10,0,0));
    }

    public void propertyChange(PropertyChangeEvent propertyChangeEvent)
    {
        boolean isSuccess = outcomeLabel.getText().equals(ActionOutcome.SUCCESS);
        hitPanel.setVisible(isSuccess);
        missPanel.setVisible(!isSuccess);
        repaint();
    }

    public void disableAndCommit()
    {
        outcomeLabel.removePropertyChangeListener(this);
        hitPanel.commitIfApplicable();
        missPanel.commitIfApplicable();

    }
}
