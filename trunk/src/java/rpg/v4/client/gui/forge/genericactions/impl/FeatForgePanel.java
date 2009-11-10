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
 * Feat forge.
 */
public class FeatForgePanel extends GenericActionForgePanel
{
    public FeatForgePanel()
    {
        super(ClientProxyKit.CLIENT_PROXY.getAvailableFeats(), "Feat", "Feat");
    }

    protected void initialize()
    {

        String[] type = {"Feat"};
        typePicker = new StringPicker(type);
        addEntryPair("Type", typePicker);

        subTypePicker = new StringPicker("n/a");
        addEntryPair("Sub type", subTypePicker);

        sourcePicker = new StringPicker("n/a");
        addEntryPair("Source", sourcePicker);

        damageTypePicker = new StringPicker("n/a");
        addEntryPair("Damage type", damageTypePicker);

        effectTypePicker = new StringPicker("n/a");
        addEntryPair("Effect type", effectTypePicker);

        actionTypePicker = new StringPicker("No action");
        addEntryPair("Action type", actionTypePicker);

        attackTypePicker = new StringPicker("n/a");
        addEntryPair("Attack type", attackTypePicker);

        secondaryAttackTypePicker = new StringPicker("n/a");
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
        ObservableArrayList<String> existingNames = ClientProxyKit.CLIENT_PROXY.getAvailableFeats();
        return existingNames.contains(v4GenericAction.getName());
    }

}
