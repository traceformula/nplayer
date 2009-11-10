package rpg.v4.client.gui.screens;

import static rpg.v4.client.gui.control.navigation.NavigationEnum.FORGE_TABS;

/**
 * Allows drilling into a specific forge in order to create content.
 */
public class ForgeView extends AbstractTabbedOverview
{

    public ForgeView()
    {
        super(FORGE_TABS);
    }
}