package rpg.v4.client.gui.edit;

import rpg.swingx.JContentRenderingPanel;
import rpg.swingx.JTransparentPanel;
import rpg.swingx.JTransparentSplitPane;
import rpg.v4.client.gui.edit.inventory.ItemListPanel;
import rpg.v4.client.gui.edit.inventory.MerchantPanel;
import rpg.v4.client.gui.edit.inventory.SlotsViewPanel;
import rpg.v4.client.provider.impl.CharacterProvider;
import rpg.v4.server.entity.Entity;
import rpg.v4.server.state.State;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Displays feats of the active character available.
 */
public class CharacterFeatEdit extends JContentRenderingPanel implements Observer
{
    private JPanel characterInventoryPanel;
    private Entity activeEntity;

    public CharacterFeatEdit()
    {
        characterInventoryPanel = new JTransparentPanel();

        CharacterProvider.provider.addObserver(this);
        CharacterSidePanel.addPanelAsInvoker(this);
    }

    public void update(Observable o, Object arg)
    {
        if (arg instanceof Entity)
        {
            setNewEntity(arg);
        } else if (o instanceof State)
        {
            // Update which feats are available
        }
    }

    private void setNewEntity(Object arg)
    {
        Entity entity = (Entity) arg;
        if (activeEntity != null)
        {
            activeEntity.getStateMap().get("Race Updated").deleteObserver(this);
        }

        activeEntity = entity;
        activeEntity.getStateMap().get("Race Updated").addObserver(this);
    }

}