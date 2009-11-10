package rpg.v4.client.gui.control.contentview;

import rpg.swingx.JTransparentPanel;
import rpg.v4.client.gui.control.navigation.NavigationEnum;

import javax.swing.*;
import java.awt.*;

/**
 * Manages which content is actively displayed.
 */
public class ContentManager
{
    private static final ContentManager contentManager = new ContentManager();

    private JTransparentPanel mainPanel;
    private JComponent activeContent;

    public static void display(NavigationEnum link)
    {
        if (null == link.getContentPanel())
        {
            throw new IllegalArgumentException("NavigationEnum with null content panel was passed in, which is illegal.");
        }

        contentManager.setContentDisplay(link.getContentPanel());
    }

    public static ContentManager instance()
    {
        return contentManager;
    }

    private ContentManager()
    {
        mainPanel = new JTransparentPanel();

        // Dummy for initial contect
        activeContent = new JTransparentPanel();
        mainPanel.add(activeContent, BorderLayout.CENTER);
    }

    public JPanel getMainPanel()
    {
        return mainPanel;
    }

    public void setContentDisplay(JPanel conent)
    {
        mainPanel.remove(activeContent);

        activeContent = conent;
        mainPanel.add(activeContent, BorderLayout.CENTER);
        mainPanel.revalidate();
        mainPanel.repaint();
    }
}
