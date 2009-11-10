package rpg.v4.client.gui.control.combat.shape;

import org.apache.log4j.Logger;
import static rpg.swingx.ColorConstants.*;
import rpg.swingx.JTransparentPanel;
import rpg.v4.client.gui.util.factories.LabelFactory;
import rpg.v4.client.proxy.ClientProxyKit;
import rpg.v4.middleware.jaxb.V4EntityNameKeyPair;
import rpg.v4.server.entity.Entity;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by IntelliJ IDEA.
 * User: fjelds
 * Date: Sep 19, 2009
 * Time: 12:11:53 PM
 *
 *
 */
public class InitativeLabel extends JTransparentPanel implements Observer
{
    private static final Logger logger = Logger.getLogger(InitativeLabel.class);
    private Entity entity;
    private JLabel label;

    private class HitPointLine extends JTransparentPanel
    {

        public HitPointLine(Entity entity)
        {
            JHitPointPanel label = new JHitPointPanel(entity);
            add(label, BorderLayout.CENTER);
        }
    }

    public InitativeLabel (V4EntityNameKeyPair nkp)
    {
        this.entity = ClientProxyKit.CLIENT_PROXY.loadEntity(nkp);
        JTransparentPanel namePanel = new JTransparentPanel();
        label = LabelFactory.createHeaderLabel(nkp.getName());
        namePanel.add(label, BorderLayout.NORTH);
        add(namePanel, BorderLayout.CENTER);

        HitPointLine hpLine = new HitPointLine(entity);
        add(hpLine, BorderLayout.WEST);

        setBorder(BorderFactory.createEmptyBorder(2,5,2,5));
        setBackground(HIGHLIGHT);

        nkp.addObserver(this);
    }

    public void update(Observable observable, Object o)
    {
        Boolean isActive = (Boolean) o;
        logger.info(entity.getNameKeyPair() + " is active: " + isActive + ". IsVisible: " + isVisible());

        setOpaque(isActive);
        label.setForeground(isActive?TEXT_HIGHLIGHT:TEXT_NORMAL);

        repaint();
    }

}
