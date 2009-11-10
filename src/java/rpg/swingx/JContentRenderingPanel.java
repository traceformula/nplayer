package rpg.swingx;

import static rpg.swingx.ColorConstants.SIDEBAR_DARK_LINE;

import java.awt.*;

/**
 * Renders a dark background panel
 */
public class JContentRenderingPanel extends JBorderLayoutPanel
{
    public JContentRenderingPanel()
    {
        setDefaultBackground();
    }

    public JContentRenderingPanel(boolean isDoubleBuffered)
    {
        super(isDoubleBuffered);
        setDefaultBackground();
    }

    public JContentRenderingPanel(LayoutManager layout)
    {
        super(layout);
        setDefaultBackground();
    }

    public JContentRenderingPanel(LayoutManager layout, boolean isDoubleBuffered)
    {
        super(layout, isDoubleBuffered);
        setDefaultBackground();
    }

    private void setDefaultBackground()
    {
        setBackground(SIDEBAR_DARK_LINE);
    }
}
