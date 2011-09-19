package rpg.v4.client.gui.edit.feats;

import org.apache.log4j.Logger;
import rpg.swingx.ColorConstants;
import rpg.swingx.JTransparentPanel;
import rpg.v4.client.gui.edit.CharacterFeatEdit;
import com.explodingpixels.macwidgets.HudWidgetFactory;
import rpg.v4.client.gui.util.factories.LabelFactory;
import rpg.v4.client.provider.impl.CharacterProvider;
import rpg.v4.client.proxy.ClientProxyKit;
import rpg.v4.middleware.jaxb.V4GenericAction;
import rpg.v4.middleware.jaxb.V4Prerequisite;
import rpg.v4.server.entity.Entity;
import rpg.v4.server.state.State;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import static java.lang.Math.floor;

/**
 * Created by IntelliJ IDEA.
 * User: fjelds
 * Date: Feb 3, 2010
 * Time: 8:31:21 PM
 *
 * Represents one feat
 */
public class FeatPanel extends JTransparentPanel implements Observer
{
    private static final Logger logger = Logger.getLogger(FeatPanel.class);
    private static final Dimension size = new Dimension(160, 20);

    private Entity activeEntity;
    private JCheckBox checkBox;
    private String featName;
    private V4GenericAction feat;
    private Map<V4Prerequisite, JLabel> prerqLabelMap;

    public FeatPanel(String featName, CharacterFeatEdit characterFeatEdit)
    {
        this.featName = featName;
        this.feat = ClientProxyKit.CLIENT_PROXY.getGenericAction(featName);

        prerqLabelMap = new HashMap<V4Prerequisite, JLabel>(feat.getV4Prerequisite().size());
        Box vBox = Box.createVerticalBox();
        for (V4Prerequisite v4Prerequisite : feat.getV4Prerequisite())
        {
            JLabel l = LabelFactory.createSmallLightHeaderLabel(
                    v4Prerequisite.getV4StateID() + "  " +
                            v4Prerequisite.getComparisonType() + "  " +
                            v4Prerequisite.getValue());
            vBox.add(l);
            prerqLabelMap.put(v4Prerequisite, l);
        }

        Box hBox = Box.createHorizontalBox();
        hBox.add(Box.createHorizontalStrut(23));
        hBox.add(vBox);

        checkBox = HudWidgetFactory.createHudCheckBox(featName);
        checkBox.setPreferredSize(size);
        checkBox.setMinimumSize(size);
        checkBox.setMaximumSize(size);
        checkBox.addActionListener(characterFeatEdit);
        this.add(checkBox, BorderLayout.NORTH);
        this.add(hBox, BorderLayout.SOUTH);
        //this.setBorder(BorderFactory.createLineBorder(Color.RED));

        CharacterProvider.provider.addObserver(this);
    }

    private void checkIfPrerequisitesPass()
    {
        boolean isPass = true;
        for (V4Prerequisite v4Prerequisite : feat.getV4Prerequisite())
        {
            State state = activeEntity.getState(v4Prerequisite.getV4StateID());
            String type = state.getType();
            boolean individualPass = true;
            if ("String".equals(type))
            {
                String total = (String) state.getTotal();
                String comparison = v4Prerequisite.getComparisonType();
                String value = v4Prerequisite.getValue();

                if ("is".equals(comparison))
                {
                    individualPass = total.equals(value);
                } else if ("is at least or greater".equals(comparison))
                {
                    throw new UnsupportedOperationException("This 'is at least or greater' comparison is not supported for string states.");
                } else if ("contains".equals(comparison))
                {
                    individualPass = total.contains(value);
                } else
                {
                    logger.warn("Reached ELSE statement in STRING comparison. Strange.");
                }
            } else if ("Integer".equals(type) || "IntegerAbility".equals(type))
            {
                Integer stateTotal = (Integer) state.getTotal();
                String comparison = v4Prerequisite.getComparisonType();
                Integer value = Integer.valueOf(v4Prerequisite.getValue());
                if ("IntegerAbility".equals(type))
                {
                    value = (int) floor((Integer) value / 2) - 5;
                }

                if ("is".equals(comparison))
                {
                    individualPass = stateTotal.equals(value);
                } else if ("is at least or greater".equals(comparison))
                {
                    individualPass = stateTotal >= value;
                } else if ("contains".equals(comparison))
                {
                    throw new UnsupportedOperationException("This 'contains' comparison is not supported for integer states.");
                } else
                {
                    logger.warn("Reached ELSE statement in INTEGER comparison. Strange.");
                }

            } else
            {
                logger.warn("Reached ELSE statement in type flow control. Strange. Type: [" + type + "], stated id: [" + state.getStateID() + "]");
            }

            isPass = isPass && individualPass;
            Color textColor = individualPass ? ColorConstants.TEXT_NORMAL : Color.RED;
            prerqLabelMap.get(v4Prerequisite).setForeground(textColor);
        }

        // Now act on the boolean deduced
        checkBox.setEnabled(isPass);
        Color bgColor = isPass ? Color.GREEN : Color.RED;
    }

    public void update(Observable o, Object arg)
    {
        if (arg instanceof Entity)
        {
            // The active entity has changed, so update the feat to observe correct
            // states of the new entity.
            setNewEntity(arg);
        } else if (o instanceof State)
        {
            // A pre req state has changed, check if the feat is available or unavailable.
            checkIfPrerequisitesPass();
        }
    }

    private void setNewEntity(Object arg)
    {
        if (activeEntity != null)
        {
            // For each pre requisite, stop observing the state.
            for (V4Prerequisite v4Prerequisite : feat.getV4Prerequisite())
            {
                activeEntity.getState(v4Prerequisite.getV4StateID()).deleteObserver(this);
            }
        }

        Entity entity = (Entity) arg;
        activeEntity = entity;
        // For each prerequisite, start observing the state.
        for (V4Prerequisite v4Prerequisite : feat.getV4Prerequisite())
        {
            activeEntity.getState(v4Prerequisite.getV4StateID()).addObserver(this);
        }
        checkIfPrerequisitesPass();
    }

    public String getFeatName()
    {
        return featName;
    }

    public void setSelected(boolean isSelected)
    {
        checkBox.setSelected(isSelected);
    }
}
