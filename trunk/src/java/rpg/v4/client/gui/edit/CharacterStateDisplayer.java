package rpg.v4.client.gui.edit;

import org.apache.log4j.Logger;
import rpg.swingx.ColorConstants;
import rpg.swingx.JContentRenderingPanel;
import rpg.swingx.JTransparentPanel;
import rpg.v4.client.gui.util.factories.LabelFactory;
import rpg.v4.client.provider.impl.CharacterProvider;
import rpg.v4.client.proxy.ClientProxyKit;
import rpg.v4.middleware.constants.FontConstants;
import rpg.v4.server.entity.Entity;
import rpg.v4.server.state.State;

import javax.swing.*;
import java.awt.*;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: fjelds
 * Date: 20/09/2011
 * Time: 09:46
 * To change this template use File | Settings | File Templates.
 */
public class CharacterStateDisplayer extends JContentRenderingPanel implements Observer
{
    private static final Logger logger = Logger.getLogger(CharacterStateDisplayer.class);

    private Entity activeEntity;
    private JPanel stateListings, dynamicStateListings;


    public CharacterStateDisplayer()
    {
        // Static states listings panels and boxes
        JLabel label = LabelFactory.createHeaderLargeLabel("States");
        add(label, BorderLayout.NORTH);

        stateListings = new JTransparentPanel();
        stateListings.setLayout(new TopToBottomGridLayout(0, 4));
        stateListings.setBorder(BorderFactory.createEmptyBorder(3, 10, 3, 10));
        // Static states
        for (String stateID : ClientProxyKit.CLIENT_PROXY.getAvailableStates())
        {
            stateListings.add(new JStateLabel(stateID));
        }


        JTransparentPanel p = new JTransparentPanel();
        p.add(stateListings, BorderLayout.NORTH);

        JPanel pHead = new JTransparentPanel();
        pHead.add(LabelFactory.createHeaderLabel("Static"), BorderLayout.WEST);

        Box pBox = Box.createVerticalBox();
        pBox.add(pHead);
        pBox.add(p);

        JPanel pHolder = new JTransparentPanel();
        pHolder.add(pBox, BorderLayout.NORTH);

        // Dynamic stats listings panels and boxes
        dynamicStateListings = new JTransparentPanel();

        JTransparentPanel dp = new JTransparentPanel();
        dp.add(dynamicStateListings, BorderLayout.NORTH);

        JPanel dpHead = new JTransparentPanel();
        dpHead.add(LabelFactory.createHeaderLabel("Dynamic"), BorderLayout.WEST);

        Box dpBox = Box.createVerticalBox();
        dpBox.add(dpHead);
        dpBox.add(dp);

        JPanel dpHolder = new JTransparentPanel();
        dpHolder.add(dpBox, BorderLayout.NORTH);

        Box box = Box.createVerticalBox();
        box.add(pHolder);
        box.add(Box.createVerticalStrut(30));
        box.add(dpHolder);

        JTransparentPanel center = new JTransparentPanel();
        center.add(box, BorderLayout.NORTH);
        add(center, BorderLayout.CENTER);

        CharacterProvider.provider.addObserver(this);
        CharacterSidePanel.addPanelAsInvoker(this);

        updateStates();
    }

    private void updateStates()
    {
        //stateListings.removeAll();

        dynamicStateListings.removeAll();
        dynamicStateListings.setLayout(new TopToBottomGridLayout(0, 4));
        dynamicStateListings.setBorder(BorderFactory.createEmptyBorder(3, 10, 3, 10));


        // Dynamic states
        if (activeEntity != null)
        {
            for (String dynamicStateID : activeEntity.getDynamicStateIDs())
            {
                String text = dynamicStateID + " = " + activeEntity.getDynamicState(dynamicStateID).getTotal();
                JLabel label = LabelFactory.createSmallLightHeaderLabel(text);
                dynamicStateListings.add(label);
            }
        }

        this.revalidate();
        this.repaint();
    }

    public void update(Observable o, Object arg)
    {
        if (arg instanceof Entity)
        {
            setNewEntity(arg);
        } else if (o.equals(activeEntity.getDynamicStateIDs()))
        {
            // Feat list has been updated.
            updateStates();
        }
    }

    private void setNewEntity(Object arg)
    {
        Entity entity = (Entity) arg;
        activeEntity = entity;
        activeEntity.getDynamicStateIDs().addObserver(this);
        updateStates();
    }

    private class JStateLabel extends JLabel implements Observer
    {

        private String stateID;
        private State state;

        public JStateLabel(String stateID)
        {
            super("", JLabel.LEFT);
            this.stateID = stateID;
            this.setPreferredSize(new Dimension(50, 10));
            this.setForeground(ColorConstants.TEXT_NORMAL);
            this.setFont(FontConstants.HEADER_FONT_SMALL);

            if (activeEntity != null)
            {
                state = activeEntity.getState(stateID);
                state.addObserver(this);
            }

            CharacterProvider.provider.addObserver(this);

            this.updateText();
        }

        private void updateText()
        {
            String text = stateID + " = " + (state != null ? state.getTotal() : " ");
            setText(text);
            setToolTipText(text);
        }

        public void update(Observable o, Object arg)
        {
            if (arg instanceof Entity)
            {
                if (null != state)
                    state.deleteObserver(this);

                state = activeEntity.getState(stateID);
                state.addObserver(this);

            }

            updateText();
        }
    }
}
