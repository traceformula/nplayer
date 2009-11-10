package rpg.v4.client.gui.forge.genericactions.impl;

import com.explodingpixels.macwidgets.HudWidgetFactory;
import rpg.swingx.JTransparentPanel;
import rpg.v4.client.gui.forge.genericactions.GenericActionForgePanel;
import rpg.v4.client.gui.util.factories.LabelFactory;
import rpg.v4.client.gui.util.picker.impl.StringPicker;
import rpg.v4.client.proxy.ClientProxyKit;
import rpg.v4.middleware.util.collection.ObservableArrayList;

import javax.swing.*;
import java.awt.*;

/**
 * To change this template use File | Settings | File Templates.
 */
public class ActionForgePanel extends GenericActionForgePanel
{
    public ActionForgePanel()
    {
        super(ClientProxyKit.CLIENT_PROXY.getAvailableActions(), "Action", "Action");
    }

    protected void initialize()
    {

        String[] type = {"Action"};
        typePicker = new StringPicker(type);
        addEntryPair("Type", typePicker);

        subTypePicker = new StringPicker("At will", "Encounter", "Daily", "Utility", "n/a");
        addEntryPair("Sub type", subTypePicker);

        sourcePicker = new StringPicker("Arcane", "Divine", "Martial", "Nature", "Ki", "Psionic",
                "Elemental", "Primal", "Shadow", "n/a");
        addEntryPair("Source", sourcePicker);

        damageTypePicker = new StringPicker("Acid", "Cold", "Fire", "Force", "Lightning",
                "Necrotic", "Psion", "Psychic", "Radiant", "Thunder", "n/a");
        addEntryPair("Damage type", damageTypePicker);

        effectTypePicker = new StringPicker("Charm", "Conjuration", "Fear", "Healing", "Illusion",
                "Poison", "Polymorph", "Reliable", "Sleep", "Stance", "Teleportation", "Zone", "n/a");
        addEntryPair("Effect type", effectTypePicker);

        actionTypePicker = new StringPicker("Standard", "Move", "Immediate interrupt",
                "Immediate interrupt", "Immediate reaction", "Minor", "Free", "No action");
        addEntryPair("Action type", actionTypePicker);

        attackTypePicker = new StringPicker("Melee", "Ranged", "Melee or Ranged", "Close", "Area", "Personal", "n/a");
        addEntryPair("Attack type", attackTypePicker);

        secondaryAttackTypePicker = new StringPicker("Weapon", "Touch", "Sight", "Burst", "Blast", "Wall", "1", "5", "10", "15", "20", "25", "30", "n/a");
        addEntryPair("Secondary attack type", secondaryAttackTypePicker);

        targetPicker = new StringPicker("One creature", "You", "You or one ally", "One creature",
                "One enemy", "One object", "Multiple enemies");
        addEntryPair("Target", targetPicker);

        attackingStatePicker = new StringPicker(ClientProxyKit.CLIENT_PROXY.getAvailableTargetableStates());
        defendingStatePicker = new StringPicker(ClientProxyKit.CLIENT_PROXY.getAvailableTargetableStates());
        JLabel vsLabel = LabelFactory.createHeaderLabel("   vs.   ");
        Box hBox = Box.createHorizontalBox();
        hBox.add(attackingStatePicker);
        hBox.add(vsLabel);
        hBox.add(defendingStatePicker);
        JTransparentPanel p = new JTransparentPanel();
        p.add(hBox, BorderLayout.WEST);
        addEntryPair("Check", p);

        disallowDiceRollsBox = HudWidgetFactory.createHudCheckBox("");
        addEntryPair("Disallow dice roll", disallowDiceRollsBox);
    }

    @Override
    protected boolean checkIfExists()
    {
        ObservableArrayList<String> existingNames = ClientProxyKit.CLIENT_PROXY.getAvailableActions();
        return existingNames.contains(v4GenericAction.getName());
    }

}
