package rpg.v4.client.gui.edit;

import rpg.swingx.*;
import rpg.v4.client.gui.control.navigation.NavigationEnum;
import rpg.v4.client.gui.edit.inventory.CharacterListingPanel;
import rpg.v4.client.gui.screens.GroupOverview;
import rpg.v4.client.gui.util.factories.LabelFactory;
import rpg.v4.client.gui.util.listener.KeyTypedListener;
import rpg.v4.client.provider.impl.GroupProvider;
import rpg.v4.client.proxy.ClientProxyKit;
import rpg.v4.middleware.jaxb.V4Group;
import rpg.v4.middleware.util.NumberGenerator;
import rpg.v4.server.group.Group;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

/**
 * Displays all groups available. Listens to events from the {@link rpg.v4.client.provider.impl.GroupProvider}
 * in order to update both which groups are available and refreshing data displayed, base on
 * the active group set in the provider.
 */
public class GroupEdit extends JContentRenderingPanel implements Observer, ActionListener
{
    private JLabel headerLabel;
    private JTextField groupNameTextField;
    private Group activeGroup;
    private GroupMembersEdit membersEditPanel;
    private JTransparentScrollPane scroller;
    private JColorChooser tcc;

    public GroupEdit()
    {
        JTransparentPanel contentPanel = new JTransparentPanel();

        // Set up header panel at top
        headerLabel = LabelFactory.createHeaderLargeLabel("Please select a group from the vault");
        JLabel emptyHeaderSizerLabel = LabelFactory.createHeaderLargeLabel(" ");
        JTransparentPanel headerPanel = new JTransparentPanel();
        headerPanel.add(headerLabel, BorderLayout.WEST);
        headerPanel.add(emptyHeaderSizerLabel, BorderLayout.CENTER);

        JButton button = new JRoundedButton("Save", this);
        JTransparentPanel savePanel = new JTransparentPanel();
        savePanel.add(button, BorderLayout.SOUTH);
        savePanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));

        headerPanel.add(savePanel, BorderLayout.EAST);

        contentPanel.add(headerPanel, BorderLayout.NORTH);

        // Set up text field with a key listener so the header label gets updated.
        KeyTypedListener keyTypedListener = new KeyTypedListener(headerLabel);
        groupNameTextField = LabelFactory.createTextFieldWide("");
        groupNameTextField.addKeyListener(keyTypedListener);
        JTransparentPanel textFieldPanel = new JTransparentPanel();
        textFieldPanel.setBorder(BorderFactory.createEmptyBorder(2, 1, 2, 2));
        textFieldPanel.add(groupNameTextField, BorderLayout.WEST);

        JLabel groupNameLabel = LabelFactory.createHeaderLabel("Name");
        JTransparentPanel groupNamePanel = new JTransparentPanel();
        groupNamePanel.add(groupNameLabel, BorderLayout.WEST);
        groupNamePanel.add(textFieldPanel, BorderLayout.CENTER);

        int red = NumberGenerator.pickNumber(1, 255);
        int green = NumberGenerator.pickNumber(1, 255);
        int blue = NumberGenerator.pickNumber(1, 255);
        tcc = new JColorChooser(new Color(red, green, blue));
        ColorBox colorBox = new ColorBox(new Color(red, green, blue));
        tcc.getSelectionModel().addChangeListener(colorBox);
        AbstractColorChooserPanel[] colorChooserList = tcc.getChooserPanels();

        AbstractColorChooserPanel colorChooser = colorChooserList[0];
        colorChooser.setBackground(ColorConstants.TRANSPARENT);
        JTransparentPanel colorHorisontalPanel = new JTransparentPanel();
        JTransparentPanel colorBoxHolder = new JTransparentPanel();
        colorBoxHolder.add(colorBox, BorderLayout.NORTH);
        JTransparentPanel colorBoxHolder2 = new JTransparentPanel();
        Border padding = BorderFactory.createEmptyBorder(10,10,10,10);
        colorBoxHolder2.setBorder(padding);

        colorBoxHolder2.add(colorBoxHolder, BorderLayout.WEST);
        colorHorisontalPanel.add(colorBoxHolder2, BorderLayout.CENTER);
        colorHorisontalPanel.add(colorChooser, BorderLayout.EAST);

        JTransparentPanel colorTopPanel = new JTransparentPanel();
        colorTopPanel.add(colorHorisontalPanel, BorderLayout.NORTH);

        JLabel groupColorLabel = LabelFactory.createHeaderLabel("Color");
        JTransparentPanel labelTopPanel = new JTransparentPanel();
        labelTopPanel.add(groupColorLabel, BorderLayout.NORTH);

        JTransparentPanel groupColorPanel = new JTransparentPanel();
        groupColorPanel.add(labelTopPanel, BorderLayout.WEST);
        groupColorPanel.add(colorTopPanel, BorderLayout.CENTER);

        membersEditPanel = new GroupMembersEdit();

        Box stateGroupBox = Box.createVerticalBox();
        stateGroupBox.add(groupNamePanel);
        stateGroupBox.add(groupColorPanel);
        stateGroupBox.add(membersEditPanel);

        JPanel groupEditPanel = new JTransparentPanel();
        groupEditPanel.add(stateGroupBox, BorderLayout.NORTH);

        scroller = new JTransparentScrollPane(groupEditPanel);
        contentPanel.add(scroller, BorderLayout.CENTER);
        scroller.setVisible(false);

        CharacterListingPanel characterListingPanel = new CharacterListingPanel();
        JSplitPane splitPane = new JTransparentSplitPane(JSplitPane.HORIZONTAL_SPLIT, contentPanel, characterListingPanel);
        splitPane.setDividerLocation(570);

        this.add(splitPane, BorderLayout.CENTER);

        GroupProvider.provider.addObserver(this);
        GroupSidePanel.addPanelAsInvoker(this);
    }

    public void update(Observable o, Object arg)
    {
        if (o instanceof Group)
        {
            Group group = (Group) o;
            membersEditPanel.update(group.getV4Group().getV4EntityNameKeyPair());
        } else
        {
            updateActiveGroup(arg);
        }
    }

    private void updateActiveGroup(Object arg)
    {
        Group group = (Group) arg;
        if (activeGroup != group)
        {
            scroller.setVisible(true);
            if (null != activeGroup)
            {
                activeGroup.deleteObserver(this);
            }

            activeGroup = group;
            headerLabel.setText(group.getV4Group().getName());
            groupNameTextField.setText(group.getV4Group().getName());
            membersEditPanel.update(group.getV4Group().getV4EntityNameKeyPair());
            activeGroup.addObserver(this);
            V4Group.Color xmlColor = activeGroup.getV4Group().getColor();
            Color color = new Color(xmlColor.getRed(), xmlColor.getGreen(), xmlColor.getBlue());
            tcc.setColor(color);

            GroupOverview overview = (GroupOverview) NavigationEnum.GROUP_OVERVIEW.getContentPanel();
            overview.setContentView(NavigationEnum.GROUP_EDIT);
        }
    }

    public void actionPerformed(ActionEvent e)
    {
        if (activeGroup == null)
            return;

        String messages = "";

        String name = headerLabel.getText();

        if (null == name || name.length() == 0)
        {
            messages += "\n - Enter a name.";
        }

        if (messages.length() != 0)
        {
            JOptionPane.showMessageDialog(this, "Could not create the group:" + messages);
            return;
        }

        activeGroup.getV4Group().setName(name);
        Color newColor = tcc.getColor();
        activeGroup.setColor(newColor);
        activeGroup.setInBattle(false);
        ClientProxyKit.CLIENT_PROXY.saveGroup(activeGroup.getV4Group());
    }

    private class ColorBox extends JPanel implements ChangeListener
    {
        private ColorBox(Color color)
        {
            Dimension size = new Dimension(20, 20);
            setPreferredSize(size);
            setMaximumSize(size);
            setMinimumSize(size);
            setSize(size);
            Border gridBorder = BorderFactory.createLineBorder(ColorConstants.GRID_COLOR);
            setBorder(gridBorder);
            setBackground(color);
        }

        public void stateChanged(ChangeEvent e)
        {
            Color newColor = tcc.getColor();
            setBackground(newColor);
            activeGroup.setColor(newColor);
        }
    }
}
