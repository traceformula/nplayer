package rpg.v4.client.gui.screens.tabbed;

import static rpg.swingx.ColorConstants.INFOBAR_DIVIDER_DARK_LINE;
import rpg.swingx.JTransparentPanel;
import rpg.v4.client.gui.control.navigation.NavigationEnum;
import rpg.v4.client.gui.screens.ContentOverview;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Maintains a set of tabs.
 */
public class TabbedBar extends JTransparentPanel
{
    private Map<NavigationEnum, Tab> tabMap;
    private NavigationEnum firstTab = null;
    private ContentOverview contentOverview;

    public TabbedBar(List<NavigationEnum> tabs, ContentOverview contentOverview)
    {
        this.contentOverview = contentOverview;
        Box tabBox = Box.createHorizontalBox();

        tabMap = new HashMap<NavigationEnum, Tab>();

        for (NavigationEnum link : tabs)
        {
            Tab tab = new Tab(link, contentOverview);
            tabBox.add(tab);
            tabMap.put(link, tab);

            if (firstTab == null)
            {
                firstTab = link;
            }
        }

        add(tabBox, BorderLayout.WEST);

        JPanel p = new JTransparentPanel();
        p.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, INFOBAR_DIVIDER_DARK_LINE));
        add(p, BorderLayout.CENTER);
    }

    public void initDefaultTab()
    {
        contentOverview.setContentView(firstTab);
    }

    public void setActive(NavigationEnum tabLink)
    {
        Tab activeTab = tabMap.get(tabLink);

        for (Tab tab : tabMap.values())
        {
            tab.setActive(tab.equals(activeTab));
        }
    }
}
