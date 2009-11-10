package rpg.v4.client.gui.util.listener;

import rpg.v4.client.gui.control.contentview.ContentManager;
import rpg.v4.client.gui.control.navigation.NavigationEnum;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Tells the ContentManager that the content desired to display has changed.
 */
public class NavigationEnumOnclickListener extends MouseAdapter
{
    private NavigationEnum link;

    public NavigationEnumOnclickListener(NavigationEnum link)
    {
        this.link = link;
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
        ContentManager.display(link);
    }
}
