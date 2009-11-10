package rpg.v4.client.gui.edit.renderer.impl;

import rpg.swingx.JGradientPanel;
import rpg.swingx.JTransparentPanel;
import rpg.v4.client.gui.util.factories.LabelFactory;

import javax.swing.*;
import java.awt.*;
import java.util.Set;

/**
 * Shows the header and the various columns displayed.
 */
public class StateGroupHeader extends JGradientPanel
{

    public StateGroupHeader(String name, Set<String> modifierTypes)
    {
        super(3, 3, 0, 3);
        JLabel header = LabelFactory.createDarkBoldHeaderLabel(name);
        add(header, BorderLayout.NORTH);

        JTransparentPanel panel = new JTransparentPanel();

        Box box = Box.createHorizontalBox();
        JLabel total = LabelFactory.createDarkNumericalModifierLabel("Total");
        box.add(total);
        for (String mod : modifierTypes)
        {
            JLabel label = LabelFactory.createDarkNumericalModifierLabel(mod);            
            box.add(label);
        }
        panel.add(box, BorderLayout.EAST);

        add(panel, BorderLayout.SOUTH);
    }
}
