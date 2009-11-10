package rpg.v4.client.gui.edit;

import org.apache.log4j.Logger;
import rpg.swingx.JContentRenderingPanel;
import rpg.swingx.JRoundedButton;
import rpg.swingx.JTransparentPanel;
import rpg.swingx.JTransparentScrollPane;
import rpg.v4.client.gui.control.navigation.NavigationEnum;
import rpg.v4.client.gui.edit.renderer.StateGroup;
import rpg.v4.client.gui.edit.renderer.StateGroupFactory;
import rpg.v4.client.gui.screens.CharacterOverview;
import rpg.v4.client.gui.util.factories.LabelFactory;
import rpg.v4.client.provider.impl.CharacterProvider;
import rpg.v4.client.proxy.ClientProxyKit;
import static rpg.v4.middleware.constants.CategoryEnum.*;
import rpg.v4.server.entity.Entity;
import rpg.v4.server.state.State;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

/**
 * Displays all characters available.
 */
public class CharacterEdit extends JContentRenderingPanel implements Observer, ActionListener
{
    private static final Logger logger = Logger.getLogger(CharacterEdit.class);
    private JLabel headerLabel;
    private Entity activeEntity;
    private Map<String, StateGroup> stateGroup;
    private Box stateGroupBox;

    public CharacterEdit()
    {
        stateGroup = new HashMap<String, StateGroup>();

        headerLabel = LabelFactory.createHeaderLargeLabel("Please select a character from the side list");
        JButton button = new JRoundedButton("Save", this);

        JTransparentPanel savePanel = new JTransparentPanel();
        savePanel.add(button, BorderLayout.SOUTH);
        savePanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));

        JTransparentPanel headerPanel = new JTransparentPanel();
        headerPanel.add(headerLabel, BorderLayout.WEST);
        headerPanel.add(savePanel, BorderLayout.EAST);
        headerPanel.add(new JLabel(" "), BorderLayout.SOUTH);

        stateGroupBox = Box.createVerticalBox();
        JPanel characterSheetPanel = new JTransparentPanel();
        characterSheetPanel.add(headerPanel, BorderLayout.NORTH);
        characterSheetPanel.add(stateGroupBox, BorderLayout.CENTER);
        //characterSheetPanel.setVisible(false);

        JTransparentScrollPane scroller = new JTransparentScrollPane(characterSheetPanel);
        add(scroller, BorderLayout.CENTER);

        CharacterProvider.provider.addObserver(this);
        CharacterSidePanel.addPanelAsInvoker(this);
    }

    public void update(Observable o, Object arg)
    {
        if (arg instanceof Entity)
        {
            setNewEntity(arg);
            setupGroupPanels();
        } else if (o == activeEntity.getStateMap().get("Name"))
        {
            State nameState = (State) o;
            headerLabel.setText((String) nameState.getTotal());
            headerLabel.repaint();
        } else if (o == activeEntity.getDynamicStateIDs())
        {
            logger.info("Dynamic states update");
            renderStates(activeEntity.getDynamicStateMap());
        }
    }

    private void setNewEntity(Object arg)
    {
        Entity entity = (Entity) arg;
        if (activeEntity != entity)
        {
            if (activeEntity != null)
            {
                activeEntity.getStateMap().get("Name").deleteObserver(this);
                activeEntity.getDynamicStateIDs().deleteObserver(this);

                if (stateGroup.containsKey(DYNAMIC_BABS.toString()))
                {
                    StateGroup group = stateGroup.get(DYNAMIC_BABS.toString());
                    group.clearGroup();
                }
            }

            activeEntity = entity;
            headerLabel.setText(entity.getName());

            renderStates(activeEntity.getStateMap());
            renderStates(activeEntity.getDynamicStateMap());
            activeEntity.getStateMap().get("Name").addObserver(this);
            activeEntity.getDynamicStateIDs().addObserver(this);

            CharacterOverview overview = (CharacterOverview) NavigationEnum.CHARACTER_OVERVIEW.getContentPanel();
            overview.setContentView(NavigationEnum.CHARACTER_EDIT);
        }
    }

    private void setupGroupPanels()
    {
        stateGroupBox.add(stateGroup.get(CHARACTER_INFORMATION.toString()));
        stateGroupBox.add(stateGroup.get(PROFICIENCIES.toString()));
        stateGroupBox.add(stateGroup.get(ABILITY.toString()));
        stateGroupBox.add(stateGroup.get(DEFENSE.toString()));
        stateGroupBox.add(stateGroup.get(SKILL.toString()));
        stateGroupBox.add(stateGroup.get(SENSES.toString()));
        stateGroupBox.add(stateGroup.get(SAVING_THROWS.toString()));
        stateGroupBox.add(stateGroup.get(DYNAMIC_BABS.toString()));
        stateGroupBox.add(stateGroup.get(OTHER.toString()));
    }

    private void renderStates(Map<String, State> stateMap)
    {
        for (State state : stateMap.values())
        {
            if (!state.isHidden())
            {
                StateGroup group = getStateGroup(state);
                group.updateState(state);
            }
        }
    }

    private StateGroup getStateGroup(State state)
    {
        // Try to get the group if it already has been added
        StateGroup group = stateGroup.get(state.getCategory());

        // Otherwise, create it
        if (null == group)
        {
            group = StateGroupFactory.createGroup(state);
            stateGroup.put(state.getCategory(), group);
        }

        return group;
    }

    public void actionPerformed(ActionEvent e)
    {
        String messages = "";

        String name = (String) activeEntity.getStateMap().get("Name").getTotal();

        if (null == name || name.length() == 0)
        {
            messages += "\n - Enter a name.";
        }


        if (messages.length() != 0)
        {
            JOptionPane.showMessageDialog(this, "Could not create the entity:" + messages);
            return;
        }

        ClientProxyKit.CLIENT_PROXY.saveEntity(activeEntity);
    }
}
