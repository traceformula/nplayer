package rpg.v4.client.gui.forge.genericactions.impl.prerequisite;

import rpg.swingx.JRoundedButton;
import rpg.swingx.JTransparentPanel;
import rpg.v4.client.gui.util.factories.LabelFactory;
import rpg.v4.client.proxy.ClientProxyKit;
import rpg.v4.middleware.jaxb.V4Prerequisite;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by IntelliJ IDEA.
 * User: fjelds
 * Date: 18/09/2011
 * Time: 10:13
 * Panel allowing user to change (add/remove/modify) prerequisites.
 */
public class PrerequisiteForgePanel extends JTransparentPanel implements MouseListener, Observer
{
    private PrerequisiteForge prerequisiteForge;
    private Box box;
    private List<PrerequisitePanel> rowList;

    public PrerequisiteForgePanel(PrerequisiteForge prerequisiteForge)
    {
        this.prerequisiteForge = prerequisiteForge;
        rowList = new ArrayList<PrerequisitePanel>();
        box = Box.createVerticalBox();
        box.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 5));

        JTransparentPanel verticalBoxPanel = new JTransparentPanel();
        verticalBoxPanel.add(box, BorderLayout.NORTH);

        JButton addModifierButton = new JRoundedButton("+ Prerequisite");
        addModifierButton.addMouseListener(this);

        JTransparentPanel buttonPanel = new JTransparentPanel();
        buttonPanel.add(addModifierButton, BorderLayout.WEST);

        JTransparentPanel modifierPanel = new JTransparentPanel();
        modifierPanel.add(buttonPanel, BorderLayout.NORTH);
        modifierPanel.add(verticalBoxPanel, BorderLayout.SOUTH);
        modifierPanel.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0));

        this.add(LabelFactory.createHeaderLabelNoSidePadding("Prerequisites to add or use this:"),
                BorderLayout.NORTH);
        this.add(modifierPanel, BorderLayout.CENTER);
        this.setBorder(BorderFactory.createEmptyBorder(10, 15, 0, 5));
        prerequisiteForge.addObserver(this);
    }

    public void remove(PrerequisitePanel p)
    {
        prerequisiteForge.remove(p.getV4Prerequisite());
        box.remove(p);
        revalidate();
        repaint();
    }

    public void mouseClicked(MouseEvent e)
    {
        createModifierRow();
    }

    private void createModifierRow()
    {
        V4Prerequisite v4Prerequisite = prerequisiteForge.createPrerequisite();
        v4Prerequisite.setV4StateID("Race");
        v4Prerequisite.setComparisonType("is");
        List<String> raceList = ClientProxyKit.CLIENT_PROXY.getAvailableRaces();
        if (null != raceList && raceList.size() > 0)
        {
            v4Prerequisite.setValue(ClientProxyKit.CLIENT_PROXY.getAvailableRaces().get(0));
        } else
        {
            v4Prerequisite.setValue("Human");
        }

        PrerequisitePanel p = new PrerequisitePanel(v4Prerequisite, this);
        rowList.add(p);
        box.add(p);
        revalidate();
        repaint();
    }

    public void update(Observable o, Object arg)
    {
        if (arg instanceof List)
        {
            for (PrerequisitePanel p : rowList)
            {
                box.remove(p);
            }

            List<V4Prerequisite> v4PrerequisiteList = (List<V4Prerequisite>) arg;
            for (V4Prerequisite v4Prerequisite : v4PrerequisiteList)
            {
                PrerequisitePanel p = new PrerequisitePanel(v4Prerequisite, this);
                rowList.add(p);
                box.add(p);
            }
            revalidate();
            repaint();
        } else
        {
            System.out.println("Prerequisite forge - strange place to be in if/else");
        }
    }

    public void mousePressed(MouseEvent e)
    {
    }

    public void mouseReleased(MouseEvent e)
    {
    }

    public void mouseEntered(MouseEvent e)
    {
    }

    public void mouseExited(MouseEvent e)
    {
    }

}