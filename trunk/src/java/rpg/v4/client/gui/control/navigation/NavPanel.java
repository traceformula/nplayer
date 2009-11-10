package rpg.v4.client.gui.control.navigation;

import static rpg.swingx.ColorConstants.SIDEBAR_DARK_LINE;
import rpg.swingx.JTransparentPanel;
import rpg.v4.client.gui.util.factories.LabelFactory;

import javax.swing.*;
import java.awt.*;

/**
 * Lists top level navigation links, such as Home, Characters, Groups, etc...
 */
public class NavPanel extends JTransparentPanel
{
    public NavPanel()
    {
        Box verticalBox = Box.createVerticalBox();
        verticalBox = createNavLink(verticalBox);
        add(verticalBox, BorderLayout.NORTH);
        setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, SIDEBAR_DARK_LINE));
    }

    private Box createNavLink(Box box)
    {
        for (NavigationEnum link : NavigationEnum.NAVIGATION_SIDEBAR_LINKS)
        {
            JTransparentPanel linkPanel = new JTransparentPanel();
            JLabel label = LabelFactory.createNavMajorLabel(link);
            linkPanel.add(label, BorderLayout.CENTER);
            box.add(linkPanel);
        }

        box.add(Box.createVerticalStrut(5));
        box.add(Box.createHorizontalStrut(150));
        return box;
    }
}
