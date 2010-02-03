package rpg.v4.client.gui.edit;

import rpg.swingx.JContentRenderingPanel;
import rpg.swingx.JTransparentPanel;
import rpg.v4.client.provider.impl.CharacterProvider;
import rpg.v4.client.gui.util.factories.LabelFactory;
import rpg.v4.client.gui.edit.feats.FeatPanel;
import rpg.v4.client.proxy.ClientProxyKit;
import rpg.v4.server.entity.Entity;
import rpg.v4.server.state.State;
import rpg.v4.middleware.util.collection.ObservableArrayList;

import javax.swing.*;
import java.util.Observable;
import java.util.Observer;
import java.util.Map;
import java.awt.*;

/**
 * Displays feats of the active character available.
 */
public class CharacterFeatEdit extends JContentRenderingPanel implements Observer
{
    private Entity activeEntity;
    private JPanel featListings;


    public CharacterFeatEdit()
    {
        JLabel label = LabelFactory.createHeaderLargeLabel("Feats");
        add(label, BorderLayout.NORTH);

        featListings = new JTransparentPanel();
        featListings.setLayout(new FlowLayout(FlowLayout.LEFT));
        featListings.setPreferredSize(new Dimension(300,300));
        add(featListings, BorderLayout.CENTER);

        ObservableArrayList<String> featList = ClientProxyKit.CLIENT_PROXY.getAvailableFeats();
        featList.addObserver(this);

        CharacterProvider.provider.addObserver(this);
        CharacterSidePanel.addPanelAsInvoker(this);

        updateFeats();
    }

    private void updateFeats()
    {
        featListings.removeAll();
        featListings.setLayout(new FlowLayout(FlowLayout.LEFT));
        featListings.setPreferredSize(new Dimension(300,300));
        ObservableArrayList<String> featList = ClientProxyKit.CLIENT_PROXY.getAvailableFeats();
        for (String featName : featList)
        {
            FeatPanel featPanel = new FeatPanel(featName);
            featListings.add(featPanel);
        }

        this.revalidate();
        this.repaint();
    }

    private void updateFeatSelection()
    {
        if (null == activeEntity)
        {
            return;
        }

        
    }

    public void update(Observable o, Object arg)
    {
        if (arg instanceof Entity)
        {
            setNewEntity(arg);
        } else if (o instanceof State)
        {
            // Update which feats are available
        } else if (o.equals(ClientProxyKit.CLIENT_PROXY.getAvailableFeats()))
        {
            // Feat list has been updated.
            updateFeats();
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