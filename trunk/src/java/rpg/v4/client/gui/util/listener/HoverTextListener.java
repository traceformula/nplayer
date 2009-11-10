package rpg.v4.client.gui.util.listener;

import static rpg.swingx.ColorConstants.TEXT_HIGHLIGHT;
import static rpg.swingx.ColorConstants.TEXT_NORMAL;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Underlines text
 */
public class HoverTextListener extends MouseAdapter
{
    private Color originalColor = TEXT_NORMAL;
    private Color highlightColor = TEXT_HIGHLIGHT;
    private Icon onHover, normal;

    public HoverTextListener(Color highlight)
    {
        this(null, null);
        this.highlightColor = highlight;
    }

    public HoverTextListener(Icon normal, Icon onHover)
    {
        this.normal = normal;
        this.onHover = onHover;
    }

    public void mouseEntered(MouseEvent event)
    {
        JComponent component = (JComponent) event.getSource();
        originalColor = component.getForeground();
        component.setForeground(highlightColor);

        if (null != onHover && component instanceof JLabel)
        {
            ((JLabel) component).setIcon(onHover);
        }

        component.repaint();
    }

    public void mouseExited(MouseEvent event)
    {
        JComponent component = (JComponent) event.getSource();
        component.setForeground(originalColor);

        if (null != normal && component instanceof JLabel)
        {
            ((JLabel) component).setIcon(normal);
        }
        
        component.repaint();
    }
}
