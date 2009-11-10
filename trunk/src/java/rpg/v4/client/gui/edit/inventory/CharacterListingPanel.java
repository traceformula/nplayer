package rpg.v4.client.gui.edit.inventory;

import rpg.swingx.JTransparentPanel;
import rpg.swingx.JTransparentScrollPane;
import rpg.v4.client.gui.edit.inventory.merchant.CharacterNameRow;
import rpg.v4.client.gui.util.factories.LabelFactory;
import rpg.v4.client.provider.impl.CharacterProvider;
import rpg.v4.client.proxy.ClientProxyKit;
import rpg.v4.middleware.jaxb.V4EntityNameKeyPair;
import rpg.v4.middleware.util.collection.ObservableArrayList;
import rpg.v4.server.entity.Entity;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * Lists all characters names of available {@link rpg.v4.server.entity.Entity}s in the application and allows a
 * user to add these to the active {@link rpg.v4.middleware.jaxb.V4Group}s member list. If any new characters becomes
 * available then these are automatically made available. To identify which {@link rpg.v4.server.entity.Entity} to add this
 * uses {@link rpg.v4.middleware.jaxb.V4EntityNameKeyPair}. 
 */
public class CharacterListingPanel extends JTransparentPanel implements Observer
{
    private static final Logger logger = Logger.getLogger(CharacterListingPanel.class);
    private Map<V4EntityNameKeyPair, CharacterNameRow> characterNameRows;
    private Box vBox;

    public CharacterListingPanel()
    {
        JLabel label = LabelFactory.createHeaderLargeLabel("Add Characters");
        add(label, BorderLayout.NORTH);

        vBox = Box.createVerticalBox();

        JTransparentPanel panel = new JTransparentPanel();
        panel.add(vBox, BorderLayout.NORTH);

        JTransparentScrollPane scroller = new JTransparentScrollPane(panel);
        this.add(scroller, BorderLayout.CENTER);

        characterNameRows = new HashMap<V4EntityNameKeyPair, CharacterNameRow>();

        ObservableArrayList<V4EntityNameKeyPair> entityNameKeyPairs = ClientProxyKit.CLIENT_PROXY.getAvailableEntities();
        entityNameKeyPairs.addObserver(this);
        manageChange(entityNameKeyPairs);

        setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 5));
    }

    public void update(Observable o, Object arg)
    {
        logger.info("Updating listing panel");
        manageChange(arg);
    }

    private void manageChange(Object arg)
    {
        for (V4EntityNameKeyPair entityID: (List<V4EntityNameKeyPair>) arg)
        {
            // Found new entity that has been added so add an entity row.
            if (!characterNameRows.containsKey(entityID))
            {
                CharacterNameRow row = new CharacterNameRow(entityID);
                vBox.add(row);
                characterNameRows.put(entityID, row);
            }

            vBox.revalidate();
            vBox.repaint();
        }
    }
}