package rpg.v4.client.gui.edit.renderer.impl;

import rpg.swingx.JTransparentPanel;
import rpg.v4.client.gui.edit.renderer.ContentBlock;
import rpg.v4.client.gui.util.factories.LabelFactory;
import rpg.v4.client.gui.util.picker.impl.StringPicker;
import rpg.v4.server.state.State;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Shows a string picker as the active user interface component.
 */
public class StringPickerRow extends ContentBlock
{
    private StringPicker picker;

    public StringPickerRow(State state, List<String> dataRange)
    {
        super(state);

        JLabel label = LabelFactory.createHeaderLabel(state.getStateID());
        label.setBorder(BorderFactory.createEmptyBorder(0, 3, 0, 3));

        picker = new StringPicker();
        picker.setDataRange(dataRange);
        picker.setMinimumSize(new Dimension(200, 20));
        picker.setPreferredSize(new Dimension(200, 20));

        JTransparentPanel textFieldPanel = new JTransparentPanel();
        textFieldPanel.setBorder(BorderFactory.createEmptyBorder(2, 1, 2, 2));
        textFieldPanel.add(picker, BorderLayout.NORTH);

        JTransparentPanel panel = new JTransparentPanel();

        panel.add(label, BorderLayout.WEST);
        panel.add(textFieldPanel, BorderLayout.EAST);
        add(panel, BorderLayout.SOUTH);
        updateState(state);
    }

    public void updateState(State state)
    {
        super.updateState(state);
        picker.setState(activeState);
    }
}
