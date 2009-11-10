package rpg.v4.client.gui.edit.inventory.item;

import rpg.v4.middleware.jaxb.V4ItemCapsule;
import rpg.v4.server.entity.Entity;
import rpg.v4.server.entity.slot.Slot;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

/**
 * To change this template use File | Settings | File Templates.
 */
public class ItemContextMenuListener implements MouseListener, ActionListener
{
    private Entity entity;
    private JPopupMenu popupMenu;
    private JMenuItem removeItem, unequipItem;
    private V4ItemCapsule item;

    public ItemContextMenuListener(Entity entity, V4ItemCapsule item)
    {
        this.entity = entity;
        this.item = item;
        popupMenu = new JPopupMenu();

        removeItem = new JMenuItem("Remove");
        removeItem.setActionCommand("Remove");
        removeItem.addActionListener(this);
        popupMenu.add(removeItem);

        unequipItem = new JMenuItem("Unequip");
        unequipItem.setActionCommand("Unequip");
        unequipItem.addActionListener(this);
        unequipItem.setEnabled(false);
        popupMenu.add(unequipItem);
    }

    public void mouseClicked(MouseEvent e)
    {

    }

    public void mousePressed(MouseEvent e)
    {

    }

    public void mouseReleased(MouseEvent e)
    {
        maybeShowPopup(e);
    }

    public void mouseEntered(MouseEvent e)
    {

    }

    public void mouseExited(MouseEvent e)
    {

    }

    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == removeItem)
        {
            entity.remove(item);
        } else if (e.getSource() == unequipItem)
        {
            entity.unequip(item);
        } else if (e.getSource() instanceof JSlotMenuItem)
        {
            JSlotMenuItem jmi = (JSlotMenuItem) e.getSource();
            jmi.getSlot().equip(item);
        } else
        {
            throw new UnsupportedOperationException("Clicked a menu item that is not supported.");
        }
    }

    private void maybeShowPopup(MouseEvent e)
    {
        if (e.isPopupTrigger() || e.getButton() == MouseEvent.BUTTON1)
        {

            popupMenu = new JPopupMenu();
            popupMenu.add(removeItem);

            List<Slot> slotList = entity.getEquipmentSlotManager().getEquippableSlots(item);

            popupMenu.addSeparator();
            if (slotList.size() > 0)
            {

                for (Slot slot : slotList)
                {

                    JMenuItem menuItem = new JSlotMenuItem(slot);
                    menuItem.addActionListener(this);
                    popupMenu.add(menuItem);

                }
            }

            boolean isEquipped = ! entity.getEquipmentSlotManager().isEquipped(item).isEmpty();
            unequipItem.setEnabled(isEquipped);
            popupMenu.add(unequipItem);

            popupMenu.show(e.getComponent(), e.getX(), e.getY());
        }
    }


    private class JSlotMenuItem extends JMenuItem
    {

        private Slot slot;

        public JSlotMenuItem(Slot slot)
        {
            super(slot.getName());
            this.slot = slot;
        }


        public Slot getSlot()
        {
            return slot;
        }
    }
}
