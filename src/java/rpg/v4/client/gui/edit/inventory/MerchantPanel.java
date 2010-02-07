package rpg.v4.client.gui.edit.inventory;

import rpg.swingx.*;
import rpg.v4.client.gui.edit.inventory.merchant.MerchantItemRow;
import rpg.v4.client.gui.util.factories.LabelFactory;
import rpg.v4.client.provider.impl.CharacterProvider;
import rpg.v4.client.proxy.ClientProxyKit;
import rpg.v4.middleware.jaxb.V4ItemCapsule;
import rpg.v4.middleware.util.collection.ObservableArrayList;
import rpg.v4.server.entity.Entity;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Lists all items available in the application and allows a user to add it to the active
 * entity's inventory. If any new items become available through the forges then these items
 * are automatically made available.
 */
public class MerchantPanel extends JBorderLayoutPanel implements Observer
{
    private Map<String, Object> weaponRowMap;
    private Map<String, Object> armorRowMap;
    private Box weaponBox, armorBox;
    private ObservableArrayList<String> weaponList;
    private ObservableArrayList<String> armorList;

    public MerchantPanel()
    {
        CharacterProvider.provider.addObserver(this);

        weaponList = ClientProxyKit.CLIENT_PROXY.getAvailableWeapons();
        weaponList.addObserver(this);

        armorList = ClientProxyKit.CLIENT_PROXY.getAvailableItems();
        armorList.addObserver(this);

        JLabel label = LabelFactory.createHeaderLargeLabel("Merchant");
        add(label, BorderLayout.NORTH);

        JDarkTabbedPane tabbedPane = new JDarkTabbedPane();
        weaponBox = Box.createVerticalBox();
        armorBox = Box.createVerticalBox();

        addTab("Weapons", weaponBox, tabbedPane);
        addTab("Items", armorBox, tabbedPane);
        this.add(tabbedPane, BorderLayout.CENTER);

        weaponRowMap = new HashMap<String, Object>();
        manageChange(weaponList, weaponRowMap, weaponBox);

        armorRowMap = new HashMap<String, Object>();
        manageChange(armorList, armorRowMap, armorBox);

        setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 5));
        setPreferredSize(new Dimension(50,50));
        setBackground(ColorConstants.SIDEBAR_BG);
        setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, Color.BLACK));
    }

    private void addTab(String title, Box box, JTabbedPane tabbedPane)
    {
        JTransparentPanel panel = new JTransparentPanel();
        panel.add(box, BorderLayout.NORTH);

        JTransparentScrollPane scroller = new JTransparentScrollPane(panel);

        tabbedPane.addTab(title, scroller);
    }

    public void update(Observable o, Object arg)
    {
        if (arg instanceof Entity)
        {
            // No need to do anything yet until multiple clients are using the
            // same server.
        } else
        {
            if (o == weaponList)
                manageChange(arg, weaponRowMap, weaponBox);
            else if (o == armorList)
                manageChange(arg, armorRowMap, armorBox);
        }
    }

    private void manageChange(Object arg, Map map, Box box)
    {
        for (String name : (List<String>) arg)
        {
            // Found new item that has been added so add a merchant row.
            if (!map.containsKey(name))
            {
                V4ItemCapsule itemCapsule = null;
                if (map == weaponRowMap)
                {
                    itemCapsule = ClientProxyKit.CLIENT_PROXY.getWeaponItemCapsule(name);
                }
                else if (map == armorRowMap)
                {
                    itemCapsule = ClientProxyKit.CLIENT_PROXY.getArmorItemCapsule(name);
                }
                MerchantItemRow row = new MerchantItemRow(itemCapsule);
                box.add(row);
                map.put(name, row);
            }

            box.revalidate();
            box.repaint();
        }
    }
}
