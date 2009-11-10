package rpg.swingx;

import javax.swing.*;
import java.awt.*;

/**
 * A JPanel with BorderLayout as it's default laayout manager.
 */
public class JBorderLayoutPanel extends JPanel
{
    public JBorderLayoutPanel(LayoutManager layout, boolean isDoubleBuffered)
    {
        super(layout, isDoubleBuffered);
        setLayout();
    }

    public JBorderLayoutPanel(LayoutManager layout)
    {
        super(layout);
        setLayout();
    }

    public JBorderLayoutPanel(boolean isDoubleBuffered)
    {
        super(isDoubleBuffered);
        setLayout();
    }

    public JBorderLayoutPanel()
    {
        super();
        setLayout();
    }

    private void setLayout()
    {
        setLayout(new BorderLayout());
    }
}
