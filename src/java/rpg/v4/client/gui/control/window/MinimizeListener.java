package rpg.v4.client.gui.control.window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

public class MinimizeListener extends AbstractListener
{
    public MinimizeListener(JFrame mainFrame)
    {
        super(mainFrame);
    }

    public void actionPerformed(ActionEvent e)
    {
        mainFrame.setState(Frame.ICONIFIED);
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
        mainFrame.setState(Frame.ICONIFIED);
    }

}
