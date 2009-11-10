package rpg.v4.client.gui.vaults.battle;

import rpg.swingx.JTransparentPanel;
import rpg.v4.client.gui.util.factories.LabelFactory;
import rpg.v4.client.gui.util.picker.impl.IntegerPicker;
import rpg.v4.client.gui.util.picker.impl.StringPicker;
import rpg.v4.client.gui.vaults.battle.entryobjects.*;
import rpg.v4.middleware.jaxb.V4EntityNameKeyPair;
import rpg.v4.middleware.jaxb.V4GenericAction;
import rpg.v4.middleware.util.NumberGenerator;
import rpg.v4.server.battle.InitiativeOrderManager;
import rpg.v4.server.entity.Entity;
import rpg.v4.server.vaults.EntityCollectionManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: fjelds
 * Date: Nov 1, 2009
 * Time: 4:18:32 PM
 */
public class ActionEntryPanel extends JTransparentPanel
{
    private V4EntityNameKeyPair owner;
    private V4GenericAction action;
    private Entity entity;
    private GenericEntry dsf;
    private GenericEntry weaponEntry;
    private GenericEntry babStateEntry;
    private GenericEntry attackStateEntry;
    private GenericEntry totalEntry;
    private GenericEntry ao;
    private IntegerPicker d20Picker;
    private StringPicker targetPicker;
    private boolean stringBased = false;
    private ResultsPanel resultsPanel;

    public ActionEntryPanel(Object entryData)
    {
        if (entryData instanceof String)
        {
            addContent((String) entryData);
        } else if (entryData instanceof ArrayList)
        {
            List l = (List) entryData;
            addContent((String) l.get(0));
        } else if (entryData instanceof V4GenericAction)
        {
            addContent((V4GenericAction) entryData);
            setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        } else
        {

            throw new IllegalArgumentException("unsupported data entry class: " + entryData);
        }
    }

    private void addContent(String dataString)
    {
        JLabel label = LabelFactory.createHeaderLabel(dataString);
        JTransparentPanel p = new JTransparentPanel();
        p.add(label, BorderLayout.NORTH);
        add(p, BorderLayout.WEST);
        stringBased = true;
    }

    private void addContent(V4GenericAction v4GenericAction)
    {
        action = v4GenericAction;
        owner = v4GenericAction.getV4EntityNameKeyPair();
        entity = EntityCollectionManager.get(owner);

        Box hBox = Box.createHorizontalBox();

        JLabel nameLabel = LabelFactory.createHeaderLabelNonPadded(v4GenericAction.getName() + ":");
        hBox.add(nameLabel);

        babStateEntry = null;
        if (v4GenericAction.getSecondaryAttackType().equals("Weapon"))
        {
            weaponEntry = new WeaponEntry(entity);
            hBox.add(weaponEntry.getJComponent());

            // Need the usual state and any weapon BAB state
            babStateEntry = new BabStateEntry((StringPicker) weaponEntry.getJComponent(), entity);
            hBox.add(LabelFactory.createHeaderLabelNonPadded(" "));
            hBox.add(babStateEntry.getJComponent());
            hBox.add(LabelFactory.createHeaderLabelNonPadded("+"));
        }

        // Need the usual state and any weapon BAB state
        String attackStateID = v4GenericAction.getAttack().getAttackerStateID();
        attackStateEntry = new StateEntry(entity.getState(attackStateID));
        hBox.add(attackStateEntry.getJComponent());


        d20Picker = null;
        if (! action.getAttack().isDisalowDiceRow())
        {
            d20Picker = new IntegerPicker(1, 20);
            d20Picker.setToolTipText("Your d20 roll");
            Integer diceRoll = NumberGenerator.pickNumber(20);
            d20Picker.setText(diceRoll.toString());

            hBox.add(LabelFactory.createHeaderLabelNonPadded("+"));
            hBox.add(d20Picker);
        }

        hBox.add(LabelFactory.createHeaderLabelNonPadded("="));

        totalEntry = new TotalEntry(babStateEntry, attackStateEntry, d20Picker);
        hBox.add(totalEntry.getJComponent());

        JLabel vsLabel = LabelFactory.createHeaderLabel(" vs. ");
        hBox.add(vsLabel);

        
        targetPicker = new StringPicker(InitiativeOrderManager.getInstance().getAlphabeticalParticipantList());
        hBox.add(targetPicker);

        JLabel defenderStateIDLabel = LabelFactory.createHeaderLabelNonPadded(v4GenericAction.getAttack().getDefenderStateID());
        hBox.add(defenderStateIDLabel);

        dsf = new DefenderStateEntry(targetPicker, v4GenericAction.getAttack().getDefenderStateID());
        hBox.add(dsf.getJComponent());

        JLabel resultLabel = LabelFactory.createHeaderLabel("=");
        hBox.add(resultLabel);

        ao = new ActionOutcome(totalEntry, dsf);
        hBox.add(ao.getJComponent());

        resultsPanel = new ResultsPanel(action, ao, targetPicker, (StringPicker) weaponEntry.getJComponent(), entity);

        JTransparentPanel p = new JTransparentPanel();
        p.add(hBox, BorderLayout.NORTH);
        p.add(resultsPanel, BorderLayout.SOUTH);
        add(p, BorderLayout.WEST);
    }

    public void commit()
    {
        if (stringBased)
        {
            return;
        }
        
        if (d20Picker != null)
        {
            d20Picker.setEditable(false);
        }

        if (babStateEntry != null)
        {
            babStateEntry.disable();
        }

        if (weaponEntry != null)
        {
            weaponEntry.disable();
        }

        dsf.disable();
        attackStateEntry.disable();
        totalEntry.disable();
        ao.disable();
        targetPicker.setEnabled(false);

        resultsPanel.disableAndCommit();
    }
}
