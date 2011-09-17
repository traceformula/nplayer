package rpg.v4.client.gui.forge.genericactions.impl;

import com.explodingpixels.macwidgets.HudWidgetFactory;
import rpg.swingx.JTransparentPanel;
import rpg.v4.client.gui.forge.genericactions.GenericActionForgePanel;
import rpg.v4.client.gui.util.factories.LabelFactory;
import rpg.v4.client.gui.util.picker.impl.IntegerPicker;
import rpg.v4.client.gui.util.picker.impl.StringPicker;
import rpg.v4.client.proxy.ClientProxyKit;
import rpg.v4.middleware.constants.PickerConstants;
import rpg.v4.middleware.util.collection.ObservableArrayList;

import javax.swing.*;
import java.awt.*;

/**
 * Power forge.
 */
public final class PowerForgePanel extends GenericActionForgePanel
{
    private StringPicker classPicker;
    private IntegerPicker levelPicker;

    public PowerForgePanel()
    {
        super(ClientProxyKit.CLIENT_PROXY.getAvailablePowers(), "Power", "Power");
    }

    protected void initialize()
    {
        String[] type = {"Power"};
        typePicker = new StringPicker(type);
        classPicker = new StringPicker(ClientProxyKit.CLIENT_PROXY.getAllAvailableClasses());
        levelPicker = new IntegerPicker(1, 30);
        subTypePicker = new StringPicker(PickerConstants.SUB_TYPES);
        sourcePicker = new StringPicker(PickerConstants.SOURCES);
        damageTypePicker = new StringPicker(PickerConstants.DAMAGE_TYPES);
        effectTypePicker = new StringPicker(PickerConstants.EFFECT_TYPES);
        actionTypePicker = new StringPicker(PickerConstants.ACTION_TYPES);
        attackTypePicker = new StringPicker(PickerConstants.ATTACK_TYPES);
        secondaryAttackTypePicker = new StringPicker(PickerConstants.SECONDARY_ATTACK_TYPES);
        targetPicker = new StringPicker(PickerConstants.TARGET_OPTIONS);

        addEntryPair("Type", typePicker);
        addEntryPair("Class requirement", classPicker);
        addEntryPair("Level", levelPicker);
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
    protected void marshal()
    {
        v4GenericAction.getRequirements().getCharacterClass().add(classPicker.getText());
        v4GenericAction.getRequirements().setLevel(Integer.valueOf(levelPicker.getText()));
        super.marshal();
    }

    @Override
    public void setObject(Object name)
    {
        super.setObject(name);
        classPicker.setText(v4GenericAction.getRequirements().getCharacterClass().get(0));
        levelPicker.setText(v4GenericAction.getRequirements().getLevel().toString());
    }

    @Override
    protected boolean checkIfExists()
    {
        ObservableArrayList<String> existingNames = ClientProxyKit.CLIENT_PROXY.getAvailablePowers();
        return existingNames.contains(v4GenericAction.getName());
    }

}
