package rpg.v4.client.gui.forge.genericactions.impl;

import com.explodingpixels.macwidgets.HudWidgetFactory;
import rpg.swingx.JTransparentPanel;
import rpg.v4.client.gui.forge.genericactions.GenericActionForgePanel;
import rpg.v4.client.gui.util.factories.LabelFactory;
import rpg.v4.client.gui.util.picker.impl.StringPicker;
import rpg.v4.client.proxy.ClientProxyKit;
import rpg.v4.middleware.constants.PickerConstants;
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
        missModifierPanel.setVisible(false);
        hitModifierPanel.setHeader("Modifiers to apply when applicable, either when selected or when condition is met:");

        typePicker = new StringPicker(type);
        subTypePicker = new StringPicker("n/a");
        sourcePicker = new StringPicker("n/a");
        damageTypePicker = new StringPicker("n/a");
        effectTypePicker = new StringPicker("n/a");
        actionTypePicker = new StringPicker("No action");
        attackTypePicker = new StringPicker("n/a");
        secondaryAttackTypePicker = new StringPicker("n/a");
        targetPicker = new StringPicker(PickerConstants.TARGET_OPTIONS);

        addEntryPair("Type", typePicker);

        attackingStatePicker = new StringPicker(ClientProxyKit.CLIENT_PROXY.getAvailableTargetableStates());
        defendingStatePicker = new StringPicker(ClientProxyKit.CLIENT_PROXY.getAvailableTargetableStates());
        disallowDiceRollsBox = HudWidgetFactory.createHudCheckBox("");
    }

    @Override
    protected boolean checkIfExists()
    {
        ObservableArrayList<String> existingNames = ClientProxyKit.CLIENT_PROXY.getAvailableFeats();
        return existingNames.contains(v4GenericAction.getName());
    }

}
