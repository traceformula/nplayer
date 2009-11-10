package rpg.v4.client.gui.vaults;

import rpg.swingx.JContentRenderingPanel;
import rpg.swingx.JRoundedButton;
import rpg.swingx.JTransparentPanel;
import rpg.v4.client.gui.control.contentview.ContentManager;
import rpg.v4.client.gui.control.navigation.NavigationEnum;
import rpg.v4.client.gui.edit.GroupSidePanel;
import rpg.v4.client.gui.util.factories.LabelFactory;
import rpg.v4.client.gui.vaults.entries.GroupTag;
import rpg.v4.client.gui.vaults.entries.NewTag;
import rpg.v4.client.gui.vaults.entries.VaultTag;
import rpg.v4.client.provider.impl.GroupProvider;
import rpg.v4.client.proxy.ClientProxyKit;
import rpg.v4.middleware.util.xml.XMLGroupKit;
import rpg.v4.server.battle.BattleStateManager;
import rpg.v4.server.group.Group;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;


/**
 * Displays all groups available.
 */
public class GroupVault extends JContentRenderingPanel implements Observer
{
    private JTransparentPanel tagContainer;
    private List<VaultTag> tagList;
    private int myHeight = 0;

    private class StartBattleActionListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            BattleStateManager.instance.startGame();
            ContentManager.display(NavigationEnum.BATTLE_VAULT);
        }
    }

    public GroupVault()
    {
        JTransparentPanel headerPanel = new JTransparentPanel();
        JButton button = new JRoundedButton("Start battle", new StartBattleActionListener());
        JTransparentPanel battleButtonPanel = new JTransparentPanel();
        battleButtonPanel.add(button, BorderLayout.SOUTH);
        battleButtonPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));

        headerPanel.add(LabelFactory.createHeaderLargeLabel("Click on a group to edit or select groups for combat"), BorderLayout.WEST);
        headerPanel.add(battleButtonPanel, BorderLayout.EAST);
        this.add(headerPanel, BorderLayout.NORTH);

        tagContainer = new JTransparentPanel();
        tagContainer.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        tagContainer.setPreferredSize(new Dimension(300, 200));
        tagContainer.add(new NewTag("New group", GroupProvider.provider));

        this.add(tagContainer, BorderLayout.CENTER);
        tagList = new ArrayList<VaultTag>();
        GroupProvider.provider.loadCollectionSummary();
        ClientProxyKit.CLIENT_PROXY.getGroupNames().addObserver(this);
        update(null, ClientProxyKit.CLIENT_PROXY.getGroupNames());

        GroupSidePanel.addPanelAsInvoker(this);
    }

/*
    @Override
    public void repaint()
    {
        if (tagContainer != null && tagContainer.getHeight() != this.getHeight())
        {
            myHeight = this.getHeight();
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    tagContainer.setPreferredSize(new Dimension(300, myHeight));
                }
            });
        }

    }
*/

    public void update(Observable o, Object arg)
    {
        for (VaultTag tag : tagList)
        {
            tagContainer.remove(tag);
        }
        tagList.clear();

        List<String> groupNamesList = (List<String>) arg;
        for (String groupName : groupNamesList)
        {
            Group group = XMLGroupKit.instance().getGroup(groupName);
            VaultTag tag = new GroupTag<Group>(group, groupName, GroupProvider.provider);
            tagContainer.add(tag);
            tagList.add(tag);
        }

        tagContainer.revalidate();
        tagContainer.repaint();
    }
}
