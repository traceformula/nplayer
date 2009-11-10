package rpg.v4.client.gui.vaults.entries;

import static rpg.swingx.ColorConstants.*;
import rpg.v4.client.provider.DataModelProvider;

import java.awt.event.MouseEvent;

/**
 * To change this template use File | Settings | File Templates.
 */
public class NewTag extends VaultTag
{
    private DataModelProvider provider;

    public NewTag(String header, DataModelProvider provider)
    {
        super(header, BLACK, VAULT_TAG_NEW_TOP_LIGHT_LINE, VAULT_TAG_NEW_BOTTOM_GRADIENT, VAULT_TAG_NEW_TOP_GRADIENT);
        this.provider = provider;
    }

    public void mouseClicked(MouseEvent e)
    {
        provider.loadNewInstance();
    }
}
