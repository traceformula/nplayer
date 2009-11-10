package rpg.swingx;

import rpg.swingx.ui.RoundedButtonUI;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Creates a rounded button using the excellent HudButtonUI from
 * http://explodingpixels.wordpress.com/2009/01/03/creating-a-hud-style-button/
 */
public class JRoundedButton extends JButton implements MouseListener
{
    public JRoundedButton()
    {
        initUIModel();
    }

    public JRoundedButton(ActionListener... listeners)
    {
        this(null, listeners);
    }

    public JRoundedButton(String text)
    {
        super(text);
        initUIModel();
    }

    public JRoundedButton(String text, ActionListener... listeners)
    {
        super(text);

        for (ActionListener listener : listeners)
        {
            addActionListener(listener);
        }

        initUIModel();
    }

    public JRoundedButton(Action a)
    {
        super(a);
        initUIModel();
    }

    public JRoundedButton(Icon icon)
    {
        super(icon);
        initUIModel();
    }

    public JRoundedButton(String text, Icon icon)
    {
        super(text, icon);
        initUIModel();
    }

    private void initUIModel()
    {
        setUI(new RoundedButtonUI());

        // Special case for tiny remove button
        if ("-".equals(getText()))
        {
            setBorder(BorderFactory.createEmptyBorder(3,7,6,7));
        } else
        {
            setBorder(BorderFactory.createEmptyBorder(6,10,8,10));
        }
        addMouseListener(this);
    }

    public void mouseClicked(MouseEvent e)
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void mousePressed(MouseEvent e)
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void mouseReleased(MouseEvent e)
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void mouseEntered(MouseEvent e)
    {
        ((RoundedButtonUI) getUI()).setHovering(true);
        repaint();
    }

    public void mouseExited(MouseEvent e)
    {
        ((RoundedButtonUI) getUI()).setHovering(false);
        repaint();
    }
}
