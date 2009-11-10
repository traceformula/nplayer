package rpg.v4.client.gui.vaults.entries;

import rpg.swingx.ColorConstants;
import static rpg.swingx.ColorConstants.BATTLE_SELECTED;
import static rpg.swingx.ColorConstants.BATTLE_UNSELECTED;
import rpg.swingx.JTransparentPanel;
import rpg.v4.client.provider.DataModelProvider;
import rpg.v4.server.group.Group;
import rpg.v4.server.state.State;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Allows a group to be added to a battle.
 */
public class GroupTag<T> extends StandardTag<T>
{
    public GroupTag(String header, DataModelProvider<T> dataModelProvider)
    {
        super(header, dataModelProvider);
        setup(tagObject);
    }

    public GroupTag(T tagObject, String name, DataModelProvider<T> dataModelProvider)
    {
        super(tagObject, name, dataModelProvider);
        setup(tagObject);
    }

    public GroupTag(T tagObject, String name, DataModelProvider<T> dataModelProvider, State state)
    {
        super(tagObject, name, dataModelProvider, state);
        setup(tagObject);
    }

    private void setup(T tagObject)
    {
        this.add(new ToBattleButton((Group) tagObject), BorderLayout.EAST);
    }

    private class ToBattleButton extends JTransparentPanel implements MouseListener
    {
        private Group group;
        private JCheckBox checkBox;

        private ToBattleButton(Group group)
        {
            this.group = group;
            //checkBox = HudWidgetFactory.createHudCheckBox("");
            checkBox = new JCheckBox();
            checkBox.setBackground(ColorConstants.TRANSPARENT);
            checkBox.addMouseListener(this);

            setBattleContext();

            JTransparentPanel p = new JTransparentPanel();
            p.add(checkBox, BorderLayout.WEST);

            add(p, BorderLayout.NORTH);
        }

        private void setBattleContext()
        {
            if (group.isInBattle())
            {
                checkBox.setForeground(BATTLE_SELECTED);
                checkBox.setToolTipText("Click to remove from battle.");
            } else
            {
                checkBox.setForeground(BATTLE_UNSELECTED);
                checkBox.setToolTipText("Click to add to battle.");
            }
            repaint();
        }

        public void mouseClicked(MouseEvent e)
        {
            group.setInBattle(checkBox.isSelected());
            setBattleContext();
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
