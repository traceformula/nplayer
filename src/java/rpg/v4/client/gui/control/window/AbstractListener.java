package rpg.v4.client.gui.control.window;

import javax.swing.*;
import java.awt.event.MouseAdapter;

/**
 * Contains the frame to work on.
 */
public abstract class AbstractListener extends MouseAdapter
{
    protected JFrame mainFrame;

    public AbstractListener(JFrame mainFrame)
    {
        this.mainFrame = mainFrame;
    }
}
