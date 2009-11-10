package rpg.v4.client.gui.edit.inventory.item;

import rpg.swingx.ColorConstants;
import rpg.swingx.JTransparentPanel;
import rpg.v4.client.gui.util.factories.LabelFactory;
import rpg.v4.client.gui.util.listener.MousePickListener;
import rpg.v4.middleware.jaxb.V4ItemCapsule;

import javax.swing.*;
import java.awt.*;

/**
 * Lists the item in an inventory
 */
public class ItemRow extends JTransparentPanel
{

    public ItemRow(V4ItemCapsule item)
    {
        JLabel header = LabelFactory.createHeaderLabel(item.getName());
        JLabel weightLabel = LabelFactory.createHeaderLabel(item.getWeight() + " lbs");

        addMouseListener(new MousePickListener(header));

        JTransparentPanel headerHorizontal = new JTransparentPanel();
        headerHorizontal.add(header, BorderLayout.WEST);
        headerHorizontal.add(weightLabel, BorderLayout.EAST);

        JTransparentPanel headerNorth = new JTransparentPanel();
        headerNorth.add(headerHorizontal, BorderLayout.NORTH);

        //header.addMouseListener(new HoverTextListener(TEXT_PICKER));

        add(headerNorth, BorderLayout.NORTH);
        setBackground(ColorConstants.ROW_TRANSPARENCY);
    }
}
