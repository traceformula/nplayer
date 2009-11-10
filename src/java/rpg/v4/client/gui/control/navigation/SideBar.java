package rpg.v4.client.gui.control.navigation;

import static rpg.swingx.ColorConstants.BLACK;
import static rpg.swingx.ColorConstants.SIDEBAR_BG;
import rpg.swingx.JBorderLayoutPanel;
import rpg.swingx.JTransparentPanel;
import rpg.swingx.JTransparentScrollPane;

import javax.swing.*;
import java.awt.*;

/**
 * Shows a side bar used for navigating in the application.
 */
public class SideBar extends JBorderLayoutPanel
{
    private static final SideBar INSTANCE = new SideBar();

    private JTransparentPanel listingPanel = new JTransparentPanel();
    private JComponent activeSideComponent;

    private SideBar()
    {
        setBackground(SIDEBAR_BG);
        add(new NavPanel(), BorderLayout.NORTH);

        JTransparentScrollPane scroller = new JTransparentScrollPane(listingPanel);
        
        add(scroller, BorderLayout.CENTER);
        setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, BLACK));
        activeSideComponent = new JTransparentPanel();
    }

    public static SideBar getINSTANCE()
    {
        return INSTANCE;
    }

    public void removeComponent(JComponent component)
    {
        if (activeSideComponent == component)
        {
            listingPanel.remove(activeSideComponent);
            activeSideComponent = new JTransparentPanel();
            listingPanel.revalidate();
            listingPanel.repaint();
        }
    }

    public void addSideComponent(JComponent component)
    {
        if (activeSideComponent != component)
        {
            listingPanel.remove(activeSideComponent);
            activeSideComponent = component;
            listingPanel.add(activeSideComponent, BorderLayout.NORTH);
            listingPanel.revalidate();
            listingPanel.repaint();
        }
    }
}
