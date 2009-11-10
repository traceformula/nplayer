package rpg.v4.client.gui.control.combat;

import static rpg.swingx.ColorConstants.*;
import rpg.swingx.JGradientPanel;
import rpg.swingx.JTransparentPanel;
import rpg.v4.client.gui.control.combat.shape.InitativeLabel;
import rpg.v4.client.gui.util.factories.LabelFactory;
import rpg.v4.client.proxy.ClientProxyKit;
import rpg.v4.middleware.jaxb.V4EntityNameKeyPair;
import rpg.v4.middleware.util.collection.ObservableArrayList;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 *
 */
public class InitiativeOrderPanel extends JTransparentPanel implements Observer
{
    private Box listingsBox;

    public InitiativeOrderPanel()
    {
        listingsBox = Box.createVerticalBox();
        
        JTransparentPanel p = new JTransparentPanel();
        JLabel header = LabelFactory.createDarkHeaderLabel("Initiative order:");
        JPanel gradientHeader = new JGradientPanel(GENERAL_DARK_LINE, BOTTOM_GRADIENT_COLOR, BOTTOM_LIGHT_LINE, TOP_GRADIENT_COLOR, TOP_LIGHT_LINE, 0, 2, 1, 2);
        gradientHeader.add(header, BorderLayout.NORTH);
        p.add(gradientHeader, BorderLayout.NORTH);
        p.add(listingsBox, BorderLayout.SOUTH);
        add(p, BorderLayout.NORTH);

        ClientProxyKit.CLIENT_PROXY.getEntityInitiativeOrderList().addObserver(this);
    }

    public void update(Observable o, Object arg)
    {
        ObservableArrayList<V4EntityNameKeyPair> initiativeOrderList = (ObservableArrayList<V4EntityNameKeyPair>) o;
        updateEntities(initiativeOrderList);
    }

    private void updateEntities(ObservableArrayList<V4EntityNameKeyPair> entityMembershipList)
    {
        listingsBox.removeAll();
        // Add new entities
        for (V4EntityNameKeyPair nkp : entityMembershipList)
        {
            InitativeLabel label = new InitativeLabel(nkp);
            listingsBox.add(label);
        }

        listingsBox.revalidate();
        listingsBox.repaint();
    }
}
