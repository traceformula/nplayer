package rpg.v4.client.gui.forge.modifier;

import rpg.swingx.JRoundedButton;
import rpg.swingx.JTransparentPanel;
import rpg.v4.client.gui.util.factories.LabelFactory;
import rpg.v4.client.gui.util.picker.impl.IntegerPicker;
import rpg.v4.client.gui.util.picker.impl.StringPicker;
import rpg.v4.client.proxy.ClientProxyKit;
import rpg.v4.middleware.jaxb.V4Modifier;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

/**
 * Changes a native {@link V4Modifier}
 */
public class ModifierPanel extends AbstractModifierPanel
{
    private IntegerPicker modifierPicker;
    private StringPicker modifierStringPicker;
    private StringPicker modTypePicker;
    private StringPicker stateTargetPicker;

    public ModifierPanel(V4Modifier modifier, ModifierForgePanel modifierForgePanel)
    {
        super(modifier, modifierForgePanel);
        boolean isStringModifier = "String".equals(modifier.getModifierClass());

        modifierPicker = new IntegerPicker();
        modifierPicker.setLowerBound(-20);
        modifierPicker.setUpperBound(20);

        modifierStringPicker = new StringPicker();

        modifierPicker.setVisible(!isStringModifier);
        modifierStringPicker.setVisible(isStringModifier);

        List<String> availableModifierTypes = ClientProxyKit.CLIENT_PROXY.getModifierTypes();
        modTypePicker = new StringPicker(availableModifierTypes);
        modTypePicker.setText(modifier.getV4ModifierType());


        List<String> availableStates = getTargetableStates();
        stateTargetPicker = new StringPicker(availableStates);
        stateTargetPicker.setText(modifier.getTargetID());

        JButton removeLabel = new JRoundedButton("-");
        removeLabel.setToolTipText("Click to remove");
        removeLabel.addMouseListener(this);

        Box box = Box.createHorizontalBox();
        box.add(removeLabel);
        box.add(Box.createRigidArea(new Dimension(10, 1)));
        box.add(modifierPicker);
        box.add(modifierStringPicker);
        box.add(LabelFactory.createHeaderLabel(" "));
        box.add(modTypePicker);
        box.add(LabelFactory.createHeaderLabel("modifier to"));
        box.add(stateTargetPicker);

        add(box, BorderLayout.NORTH);

        if (isStringModifier)
        {
            String stateTarget = stateTargetPicker.getText();
            modifierStringPicker.setDataRange(ModifierListKit.getList(stateTarget));
            modifierStringPicker.setText((String) modifier.getModifier());
        }
        else
        {
            modifierPicker.setText(String.valueOf(modifier.getModifier()));
        }
        
        modifierPicker.addPropertyChangeListener("text", this);
        modifierStringPicker.addPropertyChangeListener("text", this);
        modTypePicker.addPropertyChangeListener("text", this);
        stateTargetPicker.addPropertyChangeListener("text", this);
        setBorder(BorderFactory.createEmptyBorder(6,0,0,0));
    }

    public void propertyChange(PropertyChangeEvent evt)
    {
        Object source = evt.getSource();
        JLabel label = (JLabel) evt.getSource();
        String text = label.getText();

        if (source == modifierPicker)
        {
            modifier.setModifier(Integer.valueOf(text));
        } else if (source == modifierStringPicker)
        {
            modifier.setModifier(text);
        } else if (source == stateTargetPicker)
        {
            modifier.setTargetID(text);
            if (ModifierListKit.isStringListRequired(text))
            {
                modifierStringPicker.setDataRange(ModifierListKit.getList(text));
                modifierStringPicker.setVisible(true);
                modifierPicker.setVisible(false);
                modifier.setModifierClass("String");
            } else {
                modifierStringPicker.setVisible(false);
                modifierPicker.setVisible(true);
                modifier.setModifierClass("Integer");
            }
        } else if (source == modTypePicker)
        {
            modifier.setV4ModifierType(text);
        }
    }

}
