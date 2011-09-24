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
 * Created by IntelliJ IDEA.
 * User: fjelds
 * Date: 24/09/2011
 * Time: 01:14
 * Allows creation of conditions
 */
public class ConditionForgePanel extends GenericActionForgePanel
{
    public ConditionForgePanel()
    {
        super(ClientProxyKit.CLIENT_PROXY.getAvailableConditions(), "Penalty", "Condition");
    }

    protected void initialize()
    {

        String[] type = {"Condition"};

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
        //addEntryPair("Sub type", subTypePicker);
        //addEntryPair("Source", sourcePicker);
        //addEntryPair("Damage type", damageTypePicker);
        //addEntryPair("Effect type", effectTypePicker);
        //addEntryPair("Action type", actionTypePicker);
        //addEntryPair("Attack type", attackTypePicker);
        //addEntryPair("Secondary attack type", secondaryAttackTypePicker);
        //addEntryPair("Target", targetPicker);

        attackingStatePicker = new StringPicker(ClientProxyKit.CLIENT_PROXY.getAvailableTargetableStates());
        defendingStatePicker = new StringPicker(ClientProxyKit.CLIENT_PROXY.getAvailableTargetableStates());
        JLabel vsLabel = LabelFactory.createHeaderLabel("   vs.   ");
        Box hBox = Box.createHorizontalBox();
        hBox.add(attackingStatePicker);
        hBox.add(vsLabel);
        hBox.add(defendingStatePicker);
        JTransparentPanel p = new JTransparentPanel();
        p.add(hBox, BorderLayout.WEST);
        //addEntryPair("Check", p);

        disallowDiceRollsBox = HudWidgetFactory.createHudCheckBox("");
        //addEntryPair("Disallow dice roll", disallowDiceRollsBox);
    }

    @Override
    protected boolean checkIfExists()
    {
        ObservableArrayList<String> existingNames = ClientProxyKit.CLIENT_PROXY.getAvailableConditions();
        return existingNames.contains(v4GenericAction.getName());
    }
}
