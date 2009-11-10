package rpg.swingx;

import java.awt.*;

/**
 * A transparent JPanel
 */
public class JTransparentPanel extends JBorderLayoutPanel
{
    public JTransparentPanel(LayoutManager layout, boolean isDoubleBuffered)
    {
        super(layout, isDoubleBuffered);
        setTransparancy();
    }

    public JTransparentPanel(LayoutManager layout)
    {
        super(layout);
        setTransparancy();
    }

    public JTransparentPanel(boolean isDoubleBuffered)
    {
        super(isDoubleBuffered);
        setTransparancy();
    }

    public JTransparentPanel()
    {
        setTransparancy();
    }

    private void setTransparancy()
    {
        setOpaque(false);
    }
}
