package rpg.swingx;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;

/**
 * A transparent split pane, intially with a very thin divider.
 */
public class JTransparentSplitPane extends JSplitPane
{
    static
    {
        UIDefaults uidefs = UIManager.getLookAndFeelDefaults();

		uidefs.put("SplitPane.darkShadow", new ColorUIResource(ColorConstants.INFORBAR_BOTTOM_LINE));
		uidefs.put("SplitPane.highlight", new ColorUIResource(ColorConstants.CONTENT_DARK_LINE));
    }

    public JTransparentSplitPane()
    {
        super();
        makeTransparent();
    }

    public JTransparentSplitPane(int newOrientation)
    {
        super(newOrientation);
        makeTransparent();
    }

    public JTransparentSplitPane(int newOrientation, boolean newContinuousLayout)
    {
        super(newOrientation, newContinuousLayout);
        makeTransparent();
    }

    public JTransparentSplitPane(int newOrientation, boolean newContinuousLayout, Component newLeftComponent, Component newRightComponent)
    {
        super(newOrientation, newContinuousLayout, newLeftComponent, newRightComponent);
        makeTransparent();
    }

    public JTransparentSplitPane(int newOrientation, Component newLeftComponent, Component newRightComponent)
    {
        super(newOrientation, newLeftComponent, newRightComponent);
        makeTransparent();
    }

    private void makeTransparent()
    {
        setBorder(null);
        setOpaque(false);
        setDividerSize(2);
    }
}
