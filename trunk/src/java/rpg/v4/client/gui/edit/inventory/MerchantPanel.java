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

import org.apache.log4j.Logger;

/**
 * Lists all items available in the application and allows a user to add it to the active
 * entity's inventory. If any new items become available through the forges then these items
 * are automatically made available.
 */
public class MerchantPanel extends JBorderLayoutPanel implements Observer
{
    private static final Logger logger = Logger.getLogger(MerchantPanel.class);

    private Map<String, Object> weaponRowMap;
    private Map<String, Object> armorRowMap;
    private Map<String, Object> itemsRowMap;
    private Box weaponBox, armorBox, itemsBox;
    private ObservableArrayList<String> weaponList;
    private ObservableArrayList<String> nonWeaponsItemList;

    public MerchantPanel()
    {
        CharacterProvider.provider.addObserver(this);

        weaponList = ClientProxyKit.CLIENT_PROXY.getAvailableWeapons();
        weaponList.addObserver(this);

        nonWeaponsItemList = ClientProxyKit.CLIENT_PROXY.getAvailableItems();
        nonWeaponsItemList.addObserver(this);

        JLabel label = LabelFactory.createHeaderLargeLabel("Merchant");
        add(label, BorderLayout.NORTH);

        JDarkTabbedPane tabbedPane = new JDarkTabbedPane();
        weaponBox = Box.createVerticalBox();
        armorBox = Box.createVerticalBox();
        itemsBox = Box.createVerticalBox();

        addTab("Weapons", weaponBox, tabbedPane);
        addTab("Armor", armorBox, tabbedPane);
        addTab("Items", itemsBox, tabbedPane);
        this.add(tabbedPane, BorderLayout.CENTER);

        weaponRowMap = new HashMap<String, Object>();
        manageChange(weaponList, weaponRowMap, weaponBox);

        armorRowMap = new HashMap<String, Object>();
        manageChange(nonWeaponsItemList, armorRowMap, armorBox);

        itemsRowMap = new HashMap<String, Object>();
        manageChange(nonWeaponsItemList, itemsRowMap, itemsBox);

        setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 5));
        setPreferredSize(new Dimension(50, 50));
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
            else if (o == nonWeaponsItemList)
                manageChange(arg, armorRowMap, armorBox);
        }
    }

    private void manageChange(Object arg, Map map, Box box)
    {
        boolean isWeapon = map == weaponRowMap;
        boolean isArmor = map == armorRowMap;
        boolean isItem = map == itemsRowMap;

        for (String name : (List<String>) arg)
        {
            // Found new item that has been added so add a merchant row.
            if (!map.containsKey(name))
            {
                boolean isArmorType = false;
                V4ItemCapsule itemCapsule = null;
                if (isWeapon)
                {
                    itemCapsule = ClientProxyKit.CLIENT_PROXY.getWeaponItemCapsule(name);
                } else
                {
                    itemCapsule = ClientProxyKit.CLIENT_PROXY.getArmorItemCapsule(name);
                    isArmorType = "Armor".equals(itemCapsule.getV4Item().getType());
                }

                boolean toAdd = isWeapon || (isArmor && isArmorType) || (isItem && !isArmorType);

                if (toAdd)
                {
                    MerchantItemRow row = new MerchantItemRow(itemCapsule);
                    box.add(row);
                    map.put(name, row);
                }
            }

            box.revalidate();
            box.repaint();
        }
    }
}
