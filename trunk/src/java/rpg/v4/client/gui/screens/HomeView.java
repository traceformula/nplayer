package rpg.v4.client.gui.screens;

import static rpg.swingx.ColorConstants.SIDEBAR_DARK_LINE;
import rpg.swingx.JBorderLayoutPanel;
import rpg.swingx.JTransparentPanel;
import rpg.v4.client.gui.control.navigation.HomeNavigationPanel;
import rpg.v4.client.gui.control.navigation.NavigationEnum;

import javax.swing.*;
import java.awt.*;

/**
 * Displays the Home screen.
 */
public class HomeView extends JBorderLayoutPanel
{

    public HomeView(String header)
    {
        JTransparentPanel panel = new JTransparentPanel();
        panel.setLayout(new GridLayout(0,2));

        JPanel characterPanel = new HomeNavigationPanel("Characters",
                "Create new characters, load perviously made characters and edit these.",
                "Character_large",
                NavigationEnum.CHARACTER_OVERVIEW);
        JPanel groupsPanel = new HomeNavigationPanel("Groups",
                "Create new groups and assign or remove members. Start a battle between selected groups.",
                "Groups_large",
                NavigationEnum.GROUP_OVERVIEW);
        JPanel forgePanel = new HomeNavigationPanel("Forges",
                "Create new weapons, armor and items for your game, both normal and magical. These can be bought and equipped by your characters.",
                "Forge_large",
                NavigationEnum.FORGE_VAULT);
        JPanel battlePanel = new HomeNavigationPanel("Battles",
                "Manage battles between groups. Quite a bit of work remains in this section.",
                "Battle_large",
                NavigationEnum.BATTLE_VAULT);

        panel.add(characterPanel);
        panel.add(groupsPanel);
        panel.add(forgePanel);
        panel.add(battlePanel);

        this.add(panel, BorderLayout.CENTER);
        this.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        setBackground(SIDEBAR_DARK_LINE);
    }
}
