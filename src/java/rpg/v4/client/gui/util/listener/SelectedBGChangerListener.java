package rpg.v4.client.gui.util.listener;

import static rpg.swingx.ColorConstants.HIGHLIGHT;
import static rpg.swingx.ColorConstants.SIDEBAR_BG;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Changes the background to what it was.
 */
public class SelectedBGChangerListener extends MouseAdapter
{
    private boolean isOpaque = false;
    private Color initialBGColor = SIDEBAR_BG;
    private Color highlight;

    public SelectedBGChangerListener()
    {
        this(HIGHLIGHT);
    }

    public SelectedBGChangerListener(Color highlight)
    {
        this.highlight = highlight;
    }

    public void mouseEntered(MouseEvent event)
    {
        JComponent component = (JComponent) event.getSource();
        isOpaque = component.isOpaque();

        if (isOpaque)
        {
            initialBGColor = component.getBackground();
        }           

        component.setOpaque(true);
        component.setBackground(highlight);
        component.repaint();
    }

    public void mouseExited(MouseEvent event)
    {
        JComponent component = (JComponent) event.getSource();

        component.setOpaque(isOpaque);

        if (isOpaque)
        {
            component.setBackground(initialBGColor);
        }
        
        component.repaint();
    }
}
