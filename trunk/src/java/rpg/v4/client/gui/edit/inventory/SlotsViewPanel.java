package rpg.v4.client.gui.edit.inventory;

import rpg.swingx.ColorConstants;
import rpg.swingx.JTransparentPanel;
import rpg.v4.client.gui.util.factories.LabelFactory;
import rpg.v4.server.entity.slot.Slot;
import rpg.v4.server.entity.slot.impl.MultiSlot;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Visualises the active entity's body slots and what's equipped in them. Clicking on the equipped
 * slots should unequip the item.
 */
public class SlotsViewPanel extends JTransparentPanel
{
    private Box verticalBox;
    private Map<String, SlotRow> slotMap;

    public SlotsViewPanel()
    {
        slotMap = new HashMap<String, SlotRow>();
        JLabel label = LabelFactory.createHeaderLargeLabel("Slots");
        add(label, BorderLayout.NORTH);

        verticalBox = Box.createVerticalBox();
        add(verticalBox, BorderLayout.CENTER);

        Border border = BorderFactory.createMatteBorder(0, 0, 1, 0, ColorConstants.CONTENT_DARK_LINE);
        setBorder(border);
    }

    private SlotRow getSlotRow(String slotName)
    {
        if (!slotMap.containsKey(slotName))
        {
            slotMap.put(slotName, new SlotRow(slotName));
            verticalBox.add(slotMap.get(slotName));
        }

        return slotMap.get(slotName);
    }

    public void setSlots(List<MultiSlot> slots)
    {
        for (Slot slot : slots)
        {
            SlotRow slotRow = getSlotRow(slot.getName());
            slotRow.setSlot(slot);
        }

        // Set non-applicable slots to hidden and others to visible
        for (String slotName : slotMap.keySet())
        {
            boolean matchingSlot = false;
            for (Slot slot : slots)
            {
                matchingSlot = matchingSlot || slot.getName().equals(slotName);
            }

            slotMap.get(slotName).setVisible(matchingSlot);
        }
    }
}
