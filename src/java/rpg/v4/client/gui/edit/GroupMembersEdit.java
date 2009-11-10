package rpg.v4.client.gui.edit;

import rpg.swingx.JRoundedButton;
import rpg.swingx.JTransparentPanel;
import rpg.v4.client.gui.forge.modifier.AbstractModifierPanel;
import rpg.v4.client.gui.util.factories.LabelFactory;
import rpg.v4.client.provider.impl.GroupProvider;
import rpg.v4.middleware.jaxb.V4EntityNameKeyPair;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Allows new modifiers to be created.
 */
public class GroupMembersEdit extends JTransparentPanel
{
    private Box box;
    private List<GroupMemberPanel> rowList;

    public GroupMembersEdit()
    {
        rowList = new ArrayList<GroupMemberPanel>();
        box = Box.createVerticalBox();
        box.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 5));

        JTransparentPanel verticalBoxPanel = new JTransparentPanel();
        verticalBoxPanel.add(box, BorderLayout.NORTH);

        JLabel header = LabelFactory.createHeaderLabel("Members:");
        header.setBorder(BorderFactory.createEmptyBorder(0, 11, 10, 10));
        JTransparentPanel headerPanel = new JTransparentPanel();
        headerPanel.add(header, BorderLayout.NORTH);

        this.add(headerPanel, BorderLayout.WEST);
        this.add(verticalBoxPanel, BorderLayout.CENTER);
        this.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        GroupSidePanel.addPanelAsInvoker(this);
    }

    public void remove(AbstractModifierPanel p)
    {
        box.remove(p);
        revalidate();
        repaint();
    }

    public void update(List<V4EntityNameKeyPair> nkpList)
    {
        for (GroupMemberPanel p : rowList)
        {
            box.remove(p);
        }

        for (V4EntityNameKeyPair memberID : nkpList)
        {
            GroupMemberPanel p = new GroupMemberPanel(memberID);
            rowList.add(p);
            box.add(p);
        }
        revalidate();
        repaint();
    }

    public void update(Observable observable, Object o)
    {
        throw new UnsupportedOperationException("Group members edit should not be Observing anything.");
    }

    private class GroupMemberPanel extends JTransparentPanel implements MouseListener
    {
        private V4EntityNameKeyPair memberID;

        public GroupMemberPanel(V4EntityNameKeyPair memberID)
        {
            this.memberID = memberID;
            Box memberBox = Box.createHorizontalBox();

            JButton removeLabel = new JRoundedButton("-");
            removeLabel.setToolTipText("Click to remove");
            removeLabel.addMouseListener(this);

            memberBox.add(removeLabel);
            memberBox.add(LabelFactory.createHeaderLabel(memberID.getName()));

            add(memberBox, BorderLayout.NORTH);
        }

        public void mouseClicked(MouseEvent e)
        {
            GroupProvider.provider.getActive().remove(memberID);
        }

        public void mousePressed(MouseEvent e)
        {
            //To change body of implemented methods use File | Settings | File Templates.
        }

        public void mouseReleased(MouseEvent e)
        {
            //To change body of implemented methods use File | Settings | File Templates.
        }

        public void mouseEntered(MouseEvent e)
        {
            //To change body of implemented methods use File | Settings | File Templates.
        }

        public void mouseExited(MouseEvent e)
        {
            //To change body of implemented methods use File | Settings | File Templates.
        }
    }
}