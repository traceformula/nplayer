package rpg.v4.client.gui.util.listener;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * When a key is typed this updates the encapsulated JLabels with the text from source component
 * in which the key was typed. The source component must be a JTextField.
 */
public class KeyTypedListener implements KeyListener
{
    private JLabel[] labels;

    public KeyTypedListener(JLabel... labels)
    {
        this.labels = labels;
    }

    public void keyTyped(KeyEvent e)
    {
        JTextField component = (JTextField) e.getSource();
        String text = component.getText();

        char c = e.getKeyChar();
        // Control characters are applied first, then any listeners take turns, and then
        // the characters get added to tect components. So, have to add manually.
        if (! Character.isISOControl(c)) {
            text += c;
        }

        // Update the lables.
        for (JLabel label : labels)
        {
            label.setText(text);
        }
    }

    public void keyPressed(KeyEvent e)
    {
        // Do nothing
    }

    public void keyReleased(KeyEvent e)
    {
        // Do nothing
    }
}
