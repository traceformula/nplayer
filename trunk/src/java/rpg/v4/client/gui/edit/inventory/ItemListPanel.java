package rpg.v4.client.gui.edit.inventory;

import rpg.swingx.JTransparentPanel;
import rpg.swingx.JTransparentScrollPane;
import rpg.v4.client.gui.edit.inventory.item.ItemContextMenuListener;
import rpg.v4.client.gui.edit.inventory.item.ItemRow;
import rpg.v4.client.gui.util.factories.LabelFactory;
import rpg.v4.client.provider.impl.CharacterProvider;
import rpg.v4.middleware.jaxb.V4ItemCapsule;
import rpg.v4.middleware.util.collection.ObservableArrayList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.util.*;

/**
 * Lists all the items in the active entity's inventory.
 */
public class ItemListPanel extends JTransparentPanel implements Observer
{
    private ObservableArrayList<V4ItemCapsule> inventoryList;
    private Box vBox;
    private Map<V4ItemCapsule, ItemRow> rowMap;
    private double weight = 0.0;
    private String weightString = "Weight: ", lbsString = " lbs";
    private JLabel weightLabel;
    private Deque<ItemRow> itemDeque;

    public ItemListPanel()
    {
        JLabel label = LabelFactory.createHeaderLargeLabel("Items");
        weightLabel = LabelFactory.createHeaderLabel("");

        JTransparentPanel headerHorizontal = new JTransparentPanel();
        headerHorizontal.add(label, BorderLayout.WEST);
        headerHorizontal.add(weightLabel, BorderLayout.EAST);

        JTransparentPanel headerNorth = new JTransparentPanel();
        headerNorth.add(headerHorizontal, BorderLayout.NORTH);

        vBox = Box.createVerticalBox();
        vBox.add(headerNorth);

        JTransparentPanel panel = new JTransparentPanel();
        panel.add(vBox, BorderLayout.NORTH);

        JTransparentScrollPane scroller = new JTransparentScrollPane(panel);
        this.add(scroller, BorderLayout.CENTER);

        rowMap = new HashMap<V4ItemCapsule, ItemRow>();
        itemDeque = new ArrayDeque<ItemRow>();
    }

    public void setInventoryList(ObservableArrayList<V4ItemCapsule> inventoryList)
    {
        if (null != this.inventoryList)
        {
            this.inventoryList.deleteObserver(this);
        }

        for (ItemRow row : rowMap.values())
        {
            vBox.remove(row);
        }
        rowMap.clear();
        itemDeque.clear();
        weight = 0.0;


        this.inventoryList = inventoryList;
        for (V4ItemCapsule item : inventoryList)
        {
            ItemRow row = new ItemRow(item);
            MouseListener listener = new ItemContextMenuListener(
                    CharacterProvider.provider.getActive(), item);
            row.addMouseListener(listener);
            vBox.add(row);
            rowMap.put(item, row);
            itemDeque.add(row);
            weight += item.getWeight();
        }
        this.inventoryList.addObserver(this);
        weightLabel.setText(weightString + weight + lbsString);

        shadeItemRows();

        vBox.revalidate();
        vBox.repaint();
    }

    public void update(Observable o, Object arg)
    {
        ObservableArrayList<V4ItemCapsule> itemList = (ObservableArrayList<V4ItemCapsule>) arg;
        for (V4ItemCapsule item : itemList)
        {
            if (!rowMap.keySet().contains(item))
            {
                ItemRow row = new ItemRow(item);
                MouseListener listener = new ItemContextMenuListener(
                        CharacterProvider.provider.getActive(), item);
                row.addMouseListener(listener);

                vBox.add(row);
                rowMap.put(item, row);
                itemDeque.add(row);
                weight += item.getWeight();
            }
        }

        Map<V4ItemCapsule, ItemRow> toRemove = new HashMap<V4ItemCapsule, ItemRow>();
        for (V4ItemCapsule item : rowMap.keySet())
        {
            if (!itemList.contains(item))
            {
                ItemRow row = rowMap.get(item);
                toRemove.put(item, row);
                weight -= item.getWeight();
            }
        }

        for (V4ItemCapsule row : toRemove.keySet())
        {
            rowMap.remove(row);
            vBox.remove(toRemove.get(row));
            itemDeque.remove(toRemove.get(row));
        }

        weightLabel.setText(weightString + weight + lbsString);
        shadeItemRows();
        vBox.revalidate();
        this.revalidate();
        this.repaint();
    }

    private void shadeItemRows()
    {
        int i = 1;
        for (ItemRow row : itemDeque)
        {
            row.setOpaque(0 < i%2);
            i++;
        }
    }
}
