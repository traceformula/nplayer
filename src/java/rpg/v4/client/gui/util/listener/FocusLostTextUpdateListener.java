package rpg.v4.client.gui.util.listener;

import rpg.v4.server.state.State;

import javax.swing.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

/**
 * When focus is lost, update the state with the new text.
 */
public class FocusLostTextUpdateListener implements FocusListener
{
    private State state = null;
    private JLabel label = null;

    public FocusLostTextUpdateListener()
    {
    }

    public FocusLostTextUpdateListener(State state)
    {
        this.state = state;
    }

    public FocusLostTextUpdateListener(JLabel label)
    {
        this.label = label;
    }

    public void setState(State state)
    {
        this.state = state;
    }

    public void setLabel(JLabel label)
    {
        this.label = label;
    }

    public void focusGained(FocusEvent e)
    {
    }

    public void focusLost(FocusEvent e)
    {
        JTextField textField = (JTextField) e.getSource();

        if (null != state)
            state.add(null, null, textField.getText());

        if (null != label)
            label.setText(textField.getText());
    }
}
