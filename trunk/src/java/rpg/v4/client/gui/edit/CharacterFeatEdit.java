package rpg.v4.client.gui.edit;

import rpg.swingx.JContentRenderingPanel;
import rpg.swingx.JTransparentPanel;
import rpg.v4.client.provider.impl.CharacterProvider;
import rpg.v4.client.gui.util.factories.LabelFactory;
import rpg.v4.client.gui.edit.feats.FeatPanel;
import rpg.v4.client.proxy.ClientProxyKit;
import rpg.v4.server.entity.Entity;
import rpg.v4.server.state.State;
import rpg.v4.server.state.impl.StringState;
import rpg.v4.server.state.impl.StringListState;
import rpg.v4.middleware.util.collection.ObservableArrayList;

import javax.swing.*;
import java.util.Observable;
import java.util.Observer;
import java.util.Map;
import java.util.HashMap;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import org.apache.log4j.Logger;

/**
 * Displays feats of the active character available.
 */
public class CharacterFeatEdit extends JContentRenderingPanel implements Observer, ActionListener
{
    private static final Logger logger = Logger.getLogger(CharacterFeatEdit.class);

    private Entity activeEntity;
    private JPanel featListings;
    private Map<String, FeatPanel> featMap = new HashMap<String, FeatPanel>(100);
    private boolean isUpdating = false;


    public CharacterFeatEdit()
    {
        JLabel label = LabelFactory.createHeaderLargeLabel("Feats");
        JLabel infoLabel = LabelFactory.createInfoLabel("The actual benefits of feats are currently NOT being applied but will be soon.");
        infoLabel.setBorder(BorderFactory.createEmptyBorder(5,10,5,5));
        Box box = Box.createVerticalBox();
        box.add(label);
        box.add(infoLabel);
        add(box, BorderLayout.NORTH);

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
        featMap.clear();
        featListings.setLayout(new FlowLayout(FlowLayout.LEFT));
        featListings.setPreferredSize(new Dimension(300,300));
        ObservableArrayList<String> featList = ClientProxyKit.CLIENT_PROXY.getAvailableFeats();
        for (String featName : featList)
        {
            FeatPanel featPanel = new FeatPanel(featName, this);
            featListings.add(featPanel);
            featMap.put(featName, featPanel);
        }

        this.revalidate();
        this.repaint();
    }

    private void updateFeatSelection()
    {
        isUpdating = true;
        if (null == activeEntity)
        {
            return;
        }

        StringListState selectedFeatsState = (StringListState) activeEntity.getStateMap().get("Selected Feats");
        for (FeatPanel featPanel : featMap.values())
        {
            featPanel.setSelected(selectedFeatsState.contains(featPanel.getFeatName()));
        }

        isUpdating = false;
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
        updateFeatSelection();
    }

    public void actionPerformed(ActionEvent actionEvent)
    {
        if (isUpdating || activeEntity == null)
            return;

        JCheckBox checkBox = (JCheckBox) actionEvent.getSource();
        String featName = checkBox.getText();
        Boolean isSelected = checkBox.isSelected();

        StringListState selectedFeatsState = (StringListState) activeEntity.getStateMap().get("Selected Feats");

        if (selectedFeatsState.contains(featName) && !isSelected)
        {
            // Feat has been deselected.
            selectedFeatsState.remove("Untyped", featName);
        } else if (isSelected && !selectedFeatsState.contains(featName))
        {
            selectedFeatsState.add("Untyped", featName, featName);
        }
    }
}