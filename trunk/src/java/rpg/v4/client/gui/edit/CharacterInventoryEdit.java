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
 * Displays inventory of the active haracter available.
 */
public class CharacterInventoryEdit extends JContentRenderingPanel implements Observer
{
    private JPanel characterInventoryPanel;
    private Entity activeEntity;
    private SlotsViewPanel slotsViewPanel;
    private ItemListPanel itemListPanel;

    public CharacterInventoryEdit()
    {
        slotsViewPanel = new SlotsViewPanel();
        itemListPanel = new ItemListPanel();
        MerchantPanel merchantPanel = new MerchantPanel();
        
        characterInventoryPanel = new JTransparentPanel();
        characterInventoryPanel.add(slotsViewPanel, BorderLayout.NORTH);
        characterInventoryPanel.add(itemListPanel, BorderLayout.CENTER);

        JSplitPane splitPane = new JTransparentSplitPane(JSplitPane.HORIZONTAL_SPLIT, characterInventoryPanel, merchantPanel);
        splitPane.setDividerLocation(570);

        add(splitPane, BorderLayout.CENTER);
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
            // Race state has been updated. Update the equipment slots.
            slotsViewPanel.setSlots(activeEntity.getEquipmentSlotManager().getSlotList());
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

        slotsViewPanel.setSlots(activeEntity.getEquipmentSlotManager().getSlotList());
        itemListPanel.setInventoryList(activeEntity.getInventoryList());
    }

}