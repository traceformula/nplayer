package rpg.v4.client.gui.edit.feats;

import rpg.swingx.JTransparentPanel;
import rpg.v4.client.gui.edit.CharacterFeatEdit;
import com.explodingpixels.macwidgets.HudWidgetFactory;
import rpg.v4.client.provider.impl.CharacterProvider;
import rpg.v4.client.proxy.ClientProxyKit;
import rpg.v4.middleware.jaxb.V4GenericAction;
import rpg.v4.server.entity.Entity;
import rpg.v4.server.state.State;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

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
    private static final Dimension size = new Dimension(160, 20);

    private Entity activeEntity;
    private JCheckBox checkBox;
    private String featName;
    private V4GenericAction feat;

    public FeatPanel(String featName, CharacterFeatEdit characterFeatEdit)
    {
        this.featName = featName;
        this.feat = ClientProxyKit.CLIENT_PROXY.getGenericAction(featName);

        checkBox = HudWidgetFactory.createHudCheckBox(featName);
        checkBox.setPreferredSize(size);
        checkBox.setMinimumSize(size);
        checkBox.setMaximumSize(size);
        checkBox.addActionListener(characterFeatEdit);
        checkIfPrerequisitesPass();
        this.add(checkBox, BorderLayout.NORTH);
        this.setBorder(BorderFactory.createLineBorder(Color.RED));

        CharacterProvider.provider.addObserver(this);
    }

    private void checkIfPrerequisitesPass()
    {
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
        }

        Entity entity = (Entity) arg;
        activeEntity = entity;
        // For each prerequisite, start observing the state.
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
