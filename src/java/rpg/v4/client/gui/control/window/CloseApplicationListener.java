package rpg.v4.client.gui.control.window;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

public class CloseApplicationListener extends AbstractListener
{
    public CloseApplicationListener(JFrame mainFrame)
    {
        super(mainFrame);
    }

    public void actionPerformed(ActionEvent e)
    {
        System.exit(0);
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
        System.exit(0);
    }
}
