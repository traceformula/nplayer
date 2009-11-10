package rpg.swingx;

import rpg.swingx.ui.GlossyRoundedButtonUI;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Creates a glossy rounded button using the excellent HudButtonUI from
 * http://explodingpixels.wordpress.com/2009/01/03/creating-a-hud-style-button/
 */
public class JGlossyButton extends JButton implements MouseListener
{
    public JGlossyButton()
    {
        initButtonModel();
    }

    public JGlossyButton(ActionListener... listeners)
    {
        this(null, listeners);
    }

    public JGlossyButton(String text)
    {
        super(text);
        initButtonModel();
    }

    public JGlossyButton(String text, ActionListener... listeners)
    {
        super(text);

        for (ActionListener listener : listeners)
        {
            addActionListener(listener);
        }

        initButtonModel();
    }

    public JGlossyButton(Action a)
    {
        super(a);
        initButtonModel();
    }

    public JGlossyButton(Icon icon)
    {
        super(icon);
        initButtonModel();
    }

    public JGlossyButton(String text, Icon icon)
    {
        super(text, icon);
        initButtonModel();
    }

    public JGlossyButton(String s, ImageIcon imageIcon, ActionListener... listeners)
    {
        super(s, imageIcon);

        for (ActionListener listener : listeners)
        {
            addActionListener(listener);
        }

        initButtonModel();
    }

    private void initButtonModel()
    {
        setUI(new GlossyRoundedButtonUI());
        CompoundBorder border = (CompoundBorder) getBorder();
        setBorder(border.getInsideBorder());
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
        ((GlossyRoundedButtonUI) getUI()).setHovering(true);
        repaint();
    }

    public void mouseExited(MouseEvent e)
    {
        ((GlossyRoundedButtonUI) getUI()).setHovering(false);
        repaint();
    }
}