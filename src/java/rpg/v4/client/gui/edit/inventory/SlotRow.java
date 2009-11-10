package rpg.v4.client.gui.edit.inventory;

import rpg.swingx.JTransparentPanel;
import rpg.v4.client.gui.util.factories.LabelFactory;
import rpg.v4.server.entity.slot.Slot;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Displays the status of a slot, i.e. which equipment, if any, is equipped.
 */
public class SlotRow extends JTransparentPanel implements Observer
{
    private Slot slot;
    private Box horizontalBox;
    private String slotName;
    private JLabel slotContent;

    public SlotRow(String slotName)
    {
        this.slotName = slotName;
        horizontalBox = Box.createHorizontalBox();
        add(horizontalBox, BorderLayout.NORTH);

        JLabel slotHeader = LabelFactory.createHeaderLabel(this.slotName + ":");
        horizontalBox.add(slotHeader);
        horizontalBox.add(Box.createRigidArea(new Dimension(10,10)));

        slotContent = LabelFactory.createHeaderLabel("_");
        horizontalBox.add(slotContent);
    }

    public void setSlot(Slot slot)
    {
        if (!slotName.equals(slot.getName()))
        {
            throw new IllegalArgumentException("Slot of type '" + slotName + "' can only accept slots of the same type. Wrong slot type: " + slot.getName());
        }

        if (null != this.slot)
        {
            this.slot.deleteObserver(this);
        }

        this.slot = slot;
        this.slot.addObserver(this);
        slotContent.setText(slot.toString());
    }

    public void update(Observable o, Object arg)
    {
        slotContent.setText(slot.toString());
        
    }
}
