package rpg.v4.client.gui.edit;

import rpg.v4.client.gui.sidepanel.SidePanel;
import rpg.v4.client.gui.sidepanel.SidePanelAssociate;
import rpg.v4.client.gui.util.listener.AncestorChangeListener;
import rpg.v4.client.provider.impl.CharacterByNKPProvider;
import rpg.v4.client.provider.impl.CharacterProvider;
import rpg.v4.client.proxy.ClientProxyKit;
import rpg.v4.middleware.jaxb.V4EntityNameKeyPair;

import javax.swing.*;

/**
 * Created by IntelliJ IDEA.
 * User: fjelds
 * Date: Sep 28, 2009
 * Time: 8:29:42 PM
 *
 *
 */
public class CharacterSidePanel implements SidePanelAssociate
{
    private static final CharacterSidePanel SIDE_PANEL = new CharacterSidePanel();
    private SidePanel<V4EntityNameKeyPair> sidePanel;

    public static void addPanelAsInvoker(JPanel panel)
    {
        SIDE_PANEL.setPanel(panel);
    }

    private CharacterSidePanel()
    {
        sidePanel = new SidePanel<V4EntityNameKeyPair>(this, ClientProxyKit.CLIENT_PROXY.getAvailableEntities(), true);
    }

    private void setPanel(JPanel panel)
    {
        panel.addPropertyChangeListener(new AncestorChangeListener(sidePanel));
    }

    public void setObject(Object objectToLoad)
    {
        V4EntityNameKeyPair itemToLoad = (V4EntityNameKeyPair) objectToLoad;
        CharacterByNKPProvider.provider.loadInstance(itemToLoad);
    }

    public void addNew()
    {
        CharacterProvider.provider.loadNewInstance();
    }
}
