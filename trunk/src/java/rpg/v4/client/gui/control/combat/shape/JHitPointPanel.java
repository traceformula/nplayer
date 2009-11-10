package rpg.v4.client.gui.control.combat.shape;

import rpg.swingx.JTransparentPanel;
import rpg.v4.server.entity.Entity;
import rpg.v4.server.state.State;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by IntelliJ IDEA.
 * User: Sven-Ivar Fjeld
 * Date: 12-Feb-2005
 * Time: 13:05:39
 * To change this template use File | Settings | File Templates.
 */

class JHitPointPanel extends JTransparentPanel implements Observer
{
    private static int BAR_WIDTH = 6, PANEL_WIDTH = 6, BAR_HEIGHT = 20;
    private State hitPoints, wounds;
    private JPanel remainingHPBar, woundsHPBar;

    public JHitPointPanel(Entity entity)
    {
        super();

        hitPoints = entity.getState("Hit points");
        hitPoints.addObserver(this);

        wounds = entity.getState("Wounds");
        wounds.addObserver(this);

        add(Box.createHorizontalStrut(PANEL_WIDTH), BorderLayout.NORTH);
        remainingHPBar = new JPanel();
        remainingHPBar.setBackground(Color.GREEN);

        woundsHPBar = new JPanel();
        woundsHPBar.setBackground(Color.RED);

        Box box = Box.createVerticalBox();
        box.add(woundsHPBar);
        box.add(remainingHPBar);
        update(null, null);

        add(box, BorderLayout.CENTER);

        setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    public void update(Observable o, Object arg)
    {
        int totalHP = (Integer) hitPoints.getTotal();
        int woundsTaken = (Integer) wounds.getTotal();
        System.out.println("Updating hit points panel. WT: " + woundsTaken);
        int remaining = totalHP - woundsTaken;
        double remainingPercentage = ((double) remaining) / ((double)totalHP);
        double woundsPercentage = ((double) woundsTaken) / ((double) totalHP);
        int rHeight = (int) Math.ceil((((double) BAR_HEIGHT) * remainingPercentage));
        int wHeight = (int) Math.ceil((((double) BAR_HEIGHT) * woundsPercentage));

        Dimension rD = new Dimension(BAR_WIDTH, rHeight);
        remainingHPBar.setPreferredSize(rD);
        remainingHPBar.setMaximumSize(rD);
        remainingHPBar.setMinimumSize(rD);
        remainingHPBar.revalidate();
        remainingHPBar.repaint();

        Dimension wD = new Dimension(BAR_WIDTH, wHeight);
        woundsHPBar.setPreferredSize(wD);
        woundsHPBar.setMaximumSize(wD);
        woundsHPBar.setMinimumSize(wD);
        woundsHPBar.revalidate();
        woundsHPBar.repaint();
        setToolTipText("Wounds: " + wounds.getTotal() + ", Total HP: " + hitPoints.getTotal());
    }
}