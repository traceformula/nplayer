package rpg.v4.client.gui.vaults;

import rpg.swingx.JContentRenderingPanel;
import rpg.swingx.JRoundedButton;
import rpg.swingx.JTransparentPanel;
import rpg.v4.client.gui.control.combat.JGridManager;
import rpg.v4.client.gui.control.contentview.ContentManager;
import rpg.v4.client.gui.control.navigation.NavigationEnum;
import rpg.v4.client.gui.screens.GroupOverview;
import rpg.v4.client.gui.util.factories.LabelFactory;
import rpg.v4.client.gui.vaults.battle.ActionManagerPanel;
import rpg.v4.server.battle.BattleStateEnum;
import rpg.v4.server.battle.BattleStateManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

/**
 * Displays all characters available.
 */
public class BattleVault extends JContentRenderingPanel implements Observer
{
    private JRoundedButton endBattleButton;
    private JRoundedButton nextButton;

    private class EndBattleActionListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            BattleStateManager.instance.endGame();
            //ContentManager.display(NavigationEnum.BATTLE_VAULT);
        }
    }

    public BattleVault()
    {
        JButton selectGroupsButton = new JRoundedButton("Select groups",
                new ActionListener()
                {

                    public void actionPerformed(ActionEvent actionEvent)
                    {
                        ((GroupOverview) NavigationEnum.GROUP_OVERVIEW.getContentPanel()).setContentView(NavigationEnum.GROUP_VAULT);
                        ContentManager.display(NavigationEnum.GROUP_OVERVIEW);
                    }
                });

        nextButton = new JRoundedButton("Next",
                new ActionListener()
                {
                    public void actionPerformed(ActionEvent e)
                    {
                        BattleStateManager.instance.commitActions();
                        BattleStateManager.instance.moveForward();
                    }
                });
        nextButton.setEnabled(false);

        endBattleButton = new JRoundedButton("End battle", new EndBattleActionListener());
        endBattleButton.setEnabled(false);

        Box hBox = Box.createHorizontalBox();
        hBox.add(selectGroupsButton);
        hBox.add(Box.createHorizontalStrut(15));
        hBox.add(nextButton);
        hBox.add(Box.createHorizontalStrut(15));
        hBox.add(endBattleButton);

        JTransparentPanel headerPanel = new JTransparentPanel();
        JTransparentPanel battleButtonPanel = new JTransparentPanel();
        battleButtonPanel.add(hBox, BorderLayout.SOUTH);
        battleButtonPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));

        headerPanel.add(LabelFactory.createHeaderLargeLabel("Battle screen"), BorderLayout.WEST);
        headerPanel.add(battleButtonPanel, BorderLayout.EAST);
        this.add(headerPanel, BorderLayout.NORTH);

        ActionManagerPanel amp = new ActionManagerPanel();
        JGridManager jgridmap = new JGridManager();

        JSplitPane jSplitPane =
               new JSplitPane(JSplitPane.VERTICAL_SPLIT, amp, jgridmap);
        jSplitPane.setBorder(null);
        jSplitPane.setOpaque(true);
        jSplitPane.setContinuousLayout(true);

        add(jSplitPane, BorderLayout.CENTER);

        BattleStateManager.instance.addObserver(this);
    }

    public void update(Observable o, Object arg)
    {
        // As long as we're not in the pre battle mode we can end the battle.
        endBattleButton.setEnabled(!BattleStateEnum.PRE_BATTLE.equals(arg));
        nextButton.setEnabled(!BattleStateEnum.PRE_BATTLE.equals(arg));
    }
}