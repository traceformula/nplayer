package rpg.v4.client.gui.control.window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

public class MaximizeListener extends AbstractListener
{
    public MaximizeListener(JFrame mainFrame)
    {
        super(mainFrame);
    }

    public void actionPerformed(ActionEvent e)
    {
        if (mainFrame.getExtendedState() == JFrame.MAXIMIZED_BOTH)
        {
            mainFrame.setExtendedState(Frame.NORMAL);
        } else
        {
            mainFrame.setExtendedState(mainFrame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
        if (mainFrame.getExtendedState() == JFrame.MAXIMIZED_BOTH)
        {
            mainFrame.setExtendedState(Frame.NORMAL);
        } else
        {
            mainFrame.setExtendedState(mainFrame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        }
    }

}
