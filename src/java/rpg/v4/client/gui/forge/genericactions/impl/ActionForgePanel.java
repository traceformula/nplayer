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
        subTypePicker = new StringPicker(PickerConstants.SUB_TYPES);
        sourcePicker = new StringPicker(PickerConstants.SOURCES);
        damageTypePicker = new StringPicker(PickerConstants.DAMAGE_TYPES);
        effectTypePicker = new StringPicker(PickerConstants.EFFECT_TYPES);
        actionTypePicker = new StringPicker(PickerConstants.ACTION_TYPES);
        attackTypePicker = new StringPicker(PickerConstants.ATTACK_TYPES);
        secondaryAttackTypePicker = new StringPicker(PickerConstants.SECONDARY_ATTACK_TYPES);
        targetPicker = new StringPicker(PickerConstants.TARGET_OPTIONS);

        addEntryPair("Type", typePicker);
        addEntryPair("Sub type", subTypePicker);
        addEntryPair("Source", sourcePicker);
        addEntryPair("Damage type", damageTypePicker);
        addEntryPair("Effect type", effectTypePicker);
        addEntryPair("Action type", actionTypePicker);
        addEntryPair("Attack type", attackTypePicker);
        addEntryPair("Secondary attack type", secondaryAttackTypePicker);
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
