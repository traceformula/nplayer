package rpg.v4.client.gui.screens;

import static rpg.swingx.ColorConstants.SIDEBAR_DARK_LINE;
import rpg.swingx.JBorderLayoutPanel;
import rpg.swingx.JTransparentPanel;
import rpg.swingx.JTransparentScrollPane;
import rpg.v4.client.gui.control.navigation.NavigationEnum;
import rpg.v4.client.gui.screens.tabbed.TabbedBar;
import rpg.v4.client.gui.util.factories.LabelFactory;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractTabbedOverview  extends JTransparentPanel implements ContentOverview
{
    protected java.util.List<NavigationEnum> tabs;
    protected JBorderLayoutPanel mainView;
    protected JComponent activeComponent;
    private TabbedBar tabbedBar;

    protected AbstractTabbedOverview(List<NavigationEnum> tabs)
    {
        this.tabs = tabs;
        tabbedBar = new TabbedBar(tabs, this);
        mainView = new JBorderLayoutPanel();
        mainView.setBackground(SIDEBAR_DARK_LINE);
        activeComponent = LabelFactory.createHeaderLargeLabel("Please select from the vault");

        mainView.add(activeComponent, BorderLayout.CENTER);

        JTransparentScrollPane scroller = new JTransparentScrollPane(mainView);
        add(tabbedBar, BorderLayout.NORTH);
        add(scroller, BorderLayout.CENTER);
        tabbedBar.initDefaultTab();
    }

    public void setContentView(NavigationEnum tabLink)
    {
        tabbedBar.setActive(tabLink);
        JComponent component = tabLink.getContentPanel();
        mainView.remove(activeComponent);
        activeComponent = component;
        mainView.add(activeComponent, BorderLayout.CENTER);
        mainView.revalidate();
        mainView.repaint();
    }
}
