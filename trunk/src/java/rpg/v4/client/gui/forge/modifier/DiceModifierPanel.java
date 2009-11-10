package rpg.v4.client.gui.forge.modifier;

import rpg.swingx.JTransparentPanel;
import rpg.swingx.JRoundedButton;
import rpg.v4.middleware.jaxb.V4Modifier;
import rpg.v4.client.gui.util.picker.impl.IntegerPicker;
import rpg.v4.client.gui.util.picker.impl.StringPicker;
import rpg.v4.client.gui.util.factories.LabelFactory;
import rpg.v4.client.proxy.ClientProxyKit;

import javax.swing.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.*;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: fjelds
 * Date: Nov 6, 2009
 * Time: 10:51:10 AM
 *
 * Allows a person to select the dices for the modifier
 */
public class DiceModifierPanel extends AbstractModifierPanel
{
    private V4Modifier modifier;
    private ModifierForgePanel modifierForgePanel;
    private IntegerPicker numberOfDicePicker;
    private StringPicker modTypePicker, stateTargetPicker, diceTypePicker, multiplierPicker;

    public DiceModifierPanel(V4Modifier modifier, ModifierForgePanel modifierForgePanel)
    {
        super(modifier, modifierForgePanel);
        this.modifier = modifier;
        this.modifierForgePanel = modifierForgePanel;

        // Half of all of
        multiplierPicker = new StringPicker("All of", "Half of");
        String multiplier = modifier.getDiceModifier().getOverallMultiplier() == 0.5 ? "Half of" : "All of";
        multiplierPicker.setText(multiplier);

        // num dice picker
        numberOfDicePicker = new IntegerPicker(1, 30);
        numberOfDicePicker.setText(modifier.getDiceModifier().getNumberOfDice()+"");

        // dice type picker, including "[Weapon]" translated to 0
        diceTypePicker = new StringPicker("[Weapon]", "d1", "d2", "d4", "d6", "d8",
                "d10", "d12", "d20", "d100");
        int diceType = modifier.getDiceModifier().getDiceType();
        String diceTypeString = diceType == 0 ? "[Weapon]" : "d" + diceType;
        diceTypePicker.setText(diceTypeString);

        // type
        List<String> availableModifierTypes = ClientProxyKit.CLIENT_PROXY.getModifierTypes();
        modTypePicker = new StringPicker(availableModifierTypes);
        modTypePicker.setText(modifier.getV4ModifierType());

        // to which target state
        List<String> availableStates = getTargetableStates();
        stateTargetPicker = new StringPicker(availableStates);
        stateTargetPicker.setText(modifier.getTargetID());

        JButton removeLabel = new JRoundedButton("-");
        removeLabel.setToolTipText("Click to remove");
        removeLabel.addMouseListener(this);


        Box box = Box.createHorizontalBox();
        box.add(removeLabel);
        box.add(Box.createRigidArea(new Dimension(10, 1)));
        box.add(multiplierPicker);
        box.add(LabelFactory.createHeaderLabel(""));
        box.add(numberOfDicePicker);
        box.add(LabelFactory.createHeaderLabelNonPadded(" "));
        box.add(diceTypePicker);
        box.add(LabelFactory.createHeaderLabel(""));
        box.add(modTypePicker);
        box.add(LabelFactory.createHeaderLabel("modifier to"));
        box.add(stateTargetPicker);

        add(box, BorderLayout.NORTH);

        numberOfDicePicker.addPropertyChangeListener("text", this);
        diceTypePicker.addPropertyChangeListener("text", this);
        modTypePicker.addPropertyChangeListener("text", this);
        stateTargetPicker.addPropertyChangeListener("text", this);
        setBorder(BorderFactory.createEmptyBorder(6,0,0,0));

    }

    public void propertyChange(PropertyChangeEvent evt)
    {
        Object source = evt.getSource();
        JLabel label = (JLabel) evt.getSource();
        String text = label.getText();

        if (source == numberOfDicePicker)
        {
            modifier.getDiceModifier().setNumberOfDice(Integer.valueOf(text));
        } else if (source == diceTypePicker)
        {
            int value = ("[Weapon]".equals(text))?0:Integer.valueOf(text.replaceAll("d", ""));
            modifier.getDiceModifier().setDiceType(value);
        } else if (source == multiplierPicker)
        {
            double value = "Half of".equals(text)?0.5:1;
            modifier.getDiceModifier().setOverallMultiplier((float) value);
        } else if (source == stateTargetPicker)
        {
            modifier.setTargetID(text);
        } else if (source == modTypePicker)
        {
            modifier.setV4ModifierType(text);
        }
    }
}
