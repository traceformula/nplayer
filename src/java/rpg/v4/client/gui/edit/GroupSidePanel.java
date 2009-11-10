package rpg.v4.client.gui.edit;

import rpg.v4.client.gui.sidepanel.SidePanel;
import rpg.v4.client.gui.sidepanel.SidePanelAssociate;
import rpg.v4.client.gui.util.listener.AncestorChangeListener;
import rpg.v4.client.provider.impl.GroupProvider;
import rpg.v4.client.proxy.ClientProxyKit;
import rpg.v4.middleware.util.xml.XMLGroupKit;
import rpg.v4.server.group.Group;

import javax.swing.*;

/**
 * Created by IntelliJ IDEA.
 * User: fjelds
 * Date: Sep 28, 2009
 * Time: 8:29:42 PM
 *
 *
 */
public class GroupSidePanel implements SidePanelAssociate
{
    private static final GroupSidePanel SIDE_PANEL = new GroupSidePanel();
    private SidePanel<String> sidePanel;

    public static void addPanelAsInvoker(JPanel panel)
    {
        SIDE_PANEL.setPanel(panel);
    }

    private GroupSidePanel()
    {
        sidePanel = new SidePanel<String>(this, ClientProxyKit.CLIENT_PROXY.getGroupNames(), true);
    }

    private void setPanel(JPanel panel)
    {
        panel.addPropertyChangeListener(new AncestorChangeListener(sidePanel));
    }

    public void setObject(Object objectToLoad)
    {
        String itemToLoad = (String) objectToLoad;
        Group group = XMLGroupKit.instance().getGroup(itemToLoad);
        GroupProvider.provider.loadInstance(group);
    }

    public void addNew()
    {
        GroupProvider.provider.loadNewInstance();
    }
}
