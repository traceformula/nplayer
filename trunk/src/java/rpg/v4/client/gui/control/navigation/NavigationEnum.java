package rpg.v4.client.gui.control.navigation;

import rpg.swingx.JTransparentPanel;
import rpg.v4.client.gui.edit.CharacterEdit;
import rpg.v4.client.gui.edit.CharacterInventoryEdit;
import rpg.v4.client.gui.edit.GroupEdit;
import rpg.v4.client.gui.edit.CharacterFeatEdit;
import rpg.v4.client.gui.edit.CharacterStateDisplayer;
import rpg.v4.client.gui.forge.classes.ClassForgePanel;
import rpg.v4.client.gui.forge.genericactions.impl.ActionForgePanel;
import rpg.v4.client.gui.forge.genericactions.impl.ConditionForgePanel;
import rpg.v4.client.gui.forge.genericactions.impl.FeatForgePanel;
import rpg.v4.client.gui.forge.genericactions.impl.PowerForgePanel;
import rpg.v4.client.gui.forge.items.ItemForgePanel;
import rpg.v4.client.gui.forge.items.weapons.WeaponForgePanel;
import rpg.v4.client.gui.forge.race.RaceForgePanel;
import rpg.v4.client.gui.screens.*;
import rpg.v4.client.gui.vaults.BattleVault;
import rpg.v4.client.gui.vaults.GroupVault;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * A class representing the various states the program can be in.
 */
public enum NavigationEnum
{
    HOME("Home", "Home", "Home"),
    CHARACTER_OVERVIEW("CharacterOverview", "Characters", "Character"),
    CHARACTER_EDIT("EditCharacter", "Character", "Character"),
    CHARACTER_INVENTORY_EDIT("EditInventory", "Inventory", "Inventory"),
    CHARACTER_FEAT_EDIT("EditFeats", "Feats", "Feats"),
    CHARACTER_STATE_LIST("ViewStates", "States", "States"),
    CHARACTER_POWER_EDIT("EditPowers", "Powers", "Powers"),
    CHARACTER_ANALYTICS("AnalyseCharacter", "Analytics", "Analytics"),
    GROUP_OVERVIEW("GroupOverview", "Groups", "Groups"),
    GROUP_VAULT("GroupVault", "Groups", "Groups"),
    GROUP_EDIT("EditGroup", "Group", "Edit"),
    FORGE_VAULT("ForgeVault", "Forges", "Forge"),
    FORGE_RACES("RaceForge", "Race", "Race"),
    FORGE_CLASSES("ClassForge", "Class", "Class"),
    FORGE_WEAPONS("WeaponForge", "Weapon", "Weapon"),
    FORGE_ITEM("ItemForge", "Item", "Item"),
    FORGE_GENPOW_ACTIONS("ActionForge", "Action", "Action"),
    FORGE_GENPOW_POWERS("PowerForge", "Power", "Power"),
    FORGE_GENPOW_FEATS("FeatForge", "Feat", "Feat"),
    FORGE_GENPOW_CONDITIONS("ConditionForge", "Condition", "Condition"),
    BATTLE_VAULT("BattleVault", "Battles", "Battle"),
    EMAIL_ME("Email", "Report errors or faults", "Email"),
    DONATE("Donate", "Donations", "Donate"),
    ADD("Add", "Add", "Add"),
    REMOVE("Remove", "Remove", "Remove"),
    REMOVE_IMAGE("RemoveImage", "", "RemoveImage"),
    REMOVE_MINI("RemoveMini", "", "Remove");

    private String enumID;
    private String displayName;
    private String iconName;
    private JPanel contentPanel = new JTransparentPanel();

    NavigationEnum(String enumID, String displayName, String iconName)
    {
        this.enumID = enumID;
        this.displayName = displayName;
        this.iconName = iconName;
    }

    public String getEnumID()
    {
        return enumID;
    }

    public String getDisplayName()
    {
        return displayName;
    }

    public String getIconName()
    {
        return iconName;
    }

    public JPanel getContentPanel()
    {
        return contentPanel;
    }

    public void setContentPanel(JPanel contentPanel)
    {
        this.contentPanel = contentPanel;
    }

    public String toString()
    {
        return displayName;
    }

    // Lists
    public static final List<NavigationEnum> NAVIGATION_SIDEBAR_LINKS = new ArrayList<NavigationEnum>(),
        CHARACTER_TABS = new ArrayList<NavigationEnum>(),
        FORGE_TABS = new ArrayList<NavigationEnum>(),
        GROUP_TABS = new ArrayList<NavigationEnum>();

    // Initialize lists
    static
    {
        NAVIGATION_SIDEBAR_LINKS.add(HOME);
        NAVIGATION_SIDEBAR_LINKS.add(CHARACTER_OVERVIEW);
        NAVIGATION_SIDEBAR_LINKS.add(GROUP_OVERVIEW);
        NAVIGATION_SIDEBAR_LINKS.add(BATTLE_VAULT);
        NAVIGATION_SIDEBAR_LINKS.add(FORGE_VAULT);
        NAVIGATION_SIDEBAR_LINKS.add(DONATE);

        CHARACTER_TABS.add(CHARACTER_EDIT);
        CHARACTER_TABS.add(CHARACTER_INVENTORY_EDIT);
        CHARACTER_TABS.add(CHARACTER_FEAT_EDIT);
        CHARACTER_TABS.add(CHARACTER_POWER_EDIT);
        CHARACTER_TABS.add(CHARACTER_STATE_LIST);
        //CHARACTER_TABS.add(CHARACTER_ANALYTICS);

        //FORGE_TABS.add(FORGE_VAULT);
        FORGE_TABS.add(FORGE_RACES);
        FORGE_TABS.add(FORGE_CLASSES);
        FORGE_TABS.add(FORGE_WEAPONS);
        FORGE_TABS.add(FORGE_ITEM);
        FORGE_TABS.add(FORGE_GENPOW_POWERS);
        FORGE_TABS.add(FORGE_GENPOW_FEATS);
        FORGE_TABS.add(FORGE_GENPOW_ACTIONS);
        FORGE_TABS.add(FORGE_GENPOW_CONDITIONS);

        GROUP_TABS.add(GROUP_VAULT);
        GROUP_TABS.add(GROUP_EDIT);

        // Set up content panels
        HOME.setContentPanel(new HomeView(HOME.getDisplayName()));
        CHARACTER_EDIT.setContentPanel(new CharacterEdit());
        CHARACTER_INVENTORY_EDIT.setContentPanel(new CharacterInventoryEdit());
        CHARACTER_FEAT_EDIT.setContentPanel(new CharacterFeatEdit());
        CHARACTER_STATE_LIST.setContentPanel(new CharacterStateDisplayer());
        CHARACTER_OVERVIEW.setContentPanel(new CharacterOverview());
        GROUP_VAULT.setContentPanel(new GroupVault());
        GROUP_EDIT.setContentPanel(new GroupEdit());
        GROUP_OVERVIEW.setContentPanel(new GroupOverview());
        FORGE_RACES.setContentPanel(new RaceForgePanel());
        FORGE_CLASSES.setContentPanel(new ClassForgePanel());
        FORGE_WEAPONS.setContentPanel(new WeaponForgePanel());
        FORGE_ITEM.setContentPanel(new ItemForgePanel());
        FORGE_GENPOW_POWERS.setContentPanel(new PowerForgePanel());
        FORGE_GENPOW_ACTIONS.setContentPanel(new ActionForgePanel());
        FORGE_GENPOW_FEATS.setContentPanel(new FeatForgePanel());
        FORGE_GENPOW_CONDITIONS.setContentPanel(new ConditionForgePanel());
        FORGE_VAULT.setContentPanel(new ForgeView());
        BATTLE_VAULT.setContentPanel(new BattleVault());
        EMAIL_ME.setContentPanel(new BugReportView(EMAIL_ME.getDisplayName()));
        DONATE.setContentPanel(new DonateView());
    }


}
