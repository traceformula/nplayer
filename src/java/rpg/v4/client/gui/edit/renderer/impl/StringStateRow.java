package rpg.v4.client.gui.edit.renderer.impl;

import static rpg.swingx.ColorConstants.TEXT_HIGHLIGHT_WHITE;
import rpg.swingx.JTransparentPanel;
import rpg.v4.client.gui.edit.renderer.ContentBlock;
import rpg.v4.client.gui.util.factories.LabelFactory;
import rpg.v4.client.gui.util.listener.FocusLostTextUpdateListener;
import rpg.v4.server.state.State;

import javax.swing.*;
import java.awt.*;

/**
 * Displays a state in a general purpose way - in a row.
 */
public class StringStateRow extends ContentBlock
{
    private JTextField textField;
    private FocusLostTextUpdateListener focusLostTextUpdateListener;

    public StringStateRow(State state)
    {
        super(state);
        focusLostTextUpdateListener = new FocusLostTextUpdateListener();

        JLabel label = LabelFactory.createHeaderLabel(state.getStateID());
        label.setBorder(BorderFactory.createEmptyBorder(0, 3, 0, 3));

        initTextField();
        JTransparentPanel textFieldPanel = new JTransparentPanel();
        textFieldPanel.setBorder(BorderFactory.createEmptyBorder(2, 1, 2, 2));
        textFieldPanel.add(textField, BorderLayout.CENTER);

        JTransparentPanel panel = new JTransparentPanel();

        panel.add(label, BorderLayout.WEST);
        panel.add(textFieldPanel, BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);
        updateState(state);
    }

    private void initTextField()
    {
        textField = LabelFactory.createTextFieldWide("", activeState.getStateID().equals("Experience points"));

        if (activeState.isUserEntry())
        {
            textField.addFocusListener(focusLostTextUpdateListener);
        } else
        {
            textField.setEditable(false);
            textField.setOpaque(false);
            textField.setBorder(null);
            textField.setForeground(TEXT_HIGHLIGHT_WHITE);
        }
    }

    public void updateState(State state)
    {
        super.updateState(state);
        focusLostTextUpdateListener.setState(activeState);

        String value = state.getTotal().toString();
        textField.setText(value);
        textField.setToolTipText(state.toString());
        textField.repaint();
    }
    
}
