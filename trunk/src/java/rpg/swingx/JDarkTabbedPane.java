package rpg.swingx;

import rpg.swingx.ui.DarkTabbedPaneUI;

import javax.swing.*;
import java.awt.*;

/**
 *
 */
public class JDarkTabbedPane extends JTabbedPane
{
    public JDarkTabbedPane()
    {
        installUI();
    }

    public JDarkTabbedPane(int tabPlacement)
    {
        super(tabPlacement);
        installUI();
    }

    public JDarkTabbedPane(int tabPlacement, int tabLayoutPolicy)
    {
        super(tabPlacement, tabLayoutPolicy);
        installUI();
    }

    private void installUI()
    {
        setUI(new DarkTabbedPaneUI());
        setForeground(Color.WHITE);
    }
}
