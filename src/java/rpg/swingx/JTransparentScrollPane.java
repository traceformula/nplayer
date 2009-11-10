package rpg.swingx;

import rpg.swingx.ui.scrollbar.DarkScrollBarWidgetUI;

import javax.swing.*;
import java.awt.*;

/**
 * A transparent scroll pane
 */
public class JTransparentScrollPane extends JScrollPane
{
    public JTransparentScrollPane()
    {
        makeTransparent();
    }

    public JTransparentScrollPane(Component view)
    {
        super(view);
        makeTransparent();
    }

    public JTransparentScrollPane(Component view, int vsbPolicy, int hsbPolicy)
    {
        super(view, vsbPolicy, hsbPolicy);
        makeTransparent();
    }

    public JTransparentScrollPane(int vsbPolicy, int hsbPolicy)
    {
        super(vsbPolicy, hsbPolicy);
        makeTransparent();
    }

    private void makeTransparent()
    {
        setBorder(null);
        setOpaque(false);
        getViewport().setOpaque(false);
        getViewport().setBorder(null);
        DarkScrollBarWidgetUI.makeIAppScrollPane(this);
    }
}
