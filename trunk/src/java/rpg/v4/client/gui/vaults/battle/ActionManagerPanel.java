package rpg.v4.client.gui.vaults.battle;

import rpg.swingx.ColorConstants;
import rpg.swingx.JBorderLayoutPanel;
import rpg.swingx.JTransparentPanel;
import rpg.swingx.JTransparentScrollPane;
import rpg.v4.server.battle.BattleStateEnum;
import rpg.v4.server.battle.BattleStateManager;
import rpg.v4.server.battle.EntityRoundManager;
import rpg.v4.server.battle.InitiativeOrderManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by IntelliJ IDEA.
 * User: fjelds
 * Date: Oct 29, 2009
 * Time: 10:47:25 PM
 * <p/>
 * Allows user to select, manage and commit the actions he/she takes.
 */
public class ActionManagerPanel extends JBorderLayoutPanel implements Observer
{
    private Box entryBox;
    private JTransparentScrollPane scroller;
    private List<ActionEntryPanel> actionPanelsList = new ArrayList<ActionEntryPanel>();

    public ActionManagerPanel()
    {
        this.setBackground(ColorConstants.SIDEBAR_DARK_LINE);

        add(Box.createVerticalStrut(200), BorderLayout.WEST);
        InitiativeOrderManager.getInstance().addObserver(this);

        entryBox = Box.createVerticalBox();
        ActionSelectorPanel actionSelector = new ActionSelectorPanel();

        JTransparentPanel top = new JTransparentPanel();
        top.add(entryBox, BorderLayout.NORTH);
        top.add(actionSelector, BorderLayout.SOUTH);

        JTransparentPanel p = new JTransparentPanel();
        p.add(top, BorderLayout.NORTH);

        scroller = new JTransparentScrollPane(p);
        add(scroller, BorderLayout.CENTER);

        EntityRoundManager.getInstance().addObserver(this);
        BattleStateManager.instance.addObserver(this);
    }

    public void update(Observable observable, Object o)
    {
        if (BattleStateEnum.RESET.equals(o))
        {
            entryBox.removeAll();
        } else if (BattleStateEnum.COMMIT.equals(o))
        {
            for (ActionEntryPanel aep : actionPanelsList)
            {
                aep.commit();
            }

            actionPanelsList.clear();

        } else if (! (o instanceof BattleStateEnum))
        {
            List actions = (List) o;
            for (Object obj : actions)
            {
                ActionEntryPanel aep = new ActionEntryPanel(obj);
                actionPanelsList.add(aep);
                entryBox.add(aep);
            }
        }

        entryBox.revalidate();
        entryBox.repaint();

        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                scroller.getVerticalScrollBar().setValue(
                        scroller.getVerticalScrollBar().getMaximum());
            }
        });
    }
}
