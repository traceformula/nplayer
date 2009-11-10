package rpg.v4.client.gui.control.window;

import rpg.swingx.JTransparentPanel;
import rpg.v4.client.gui.util.image.ImageKit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Displays the three dispose buttons and as private classes contains the listeners and handlers.
 */
public class WindowDisposePanel extends JTransparentPanel
{
    private class WindowControlButton extends JLabel implements MouseListener
    {
        private Icon normalHover;
        private Icon normal;

        public WindowControlButton(ControlType type, MouseListener actionListener)
        {
            setToolTipText(type.getName());
            setOpaque(false);
            setBorder(BorderFactory.createEmptyBorder(3,3,3,3));
            addMouseListener(actionListener);
            addMouseListener(this);

            normal = ImageKit.loadImageIcon(type.getName());
            normalHover = ImageKit.loadImageIcon(type.getName() + "_hover");

            setIcon(normal);

            setFocusable(false);
            putClientProperty("JButton.buttonType", "textured");
        }

        public void mouseClicked(MouseEvent e)
        {

        }

        public void mousePressed(MouseEvent e)
        {
            setIcon(normalHover);
        }

        public void mouseReleased(MouseEvent e)
        {
            setIcon(normal);
        }

        public void mouseEntered(MouseEvent e)
        {
            setIcon(normalHover);
        }

        public void mouseExited(MouseEvent e)
        {
            setIcon(normal);
        }
    }

    public WindowDisposePanel(JFrame mainFrame)
    {
        Box box = Box.createHorizontalBox();
        WindowControlButton minButton = new WindowControlButton(ControlType.MINIMIZE, new MinimizeListener(mainFrame));
        WindowControlButton maxButton = new WindowControlButton(ControlType.MAXIMIZE, new MaximizeListener(mainFrame));
        WindowControlButton closeButton = new WindowControlButton(ControlType.CLOSE, new CloseApplicationListener(mainFrame));

        box.add(minButton);
        box.add(maxButton);
        box.add(closeButton);

        add(box, BorderLayout.CENTER);
    }
}
