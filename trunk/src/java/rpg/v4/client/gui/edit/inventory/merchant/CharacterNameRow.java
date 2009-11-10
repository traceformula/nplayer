package rpg.v4.client.gui.edit.inventory.merchant;

import rpg.v4.client.provider.impl.GroupProvider;
import rpg.v4.middleware.jaxb.V4EntityNameKeyPair;
import rpg.v4.server.group.Group;

/**
 * Displays the name of an item {@link rpg.v4.middleware.jaxb.V4ItemCapsule} and allows it to be added to an {@link rpg.v4.server.entity.Entity} inventory.
 * The item is deep copied into a clone before added.
 */
public class CharacterNameRow extends AbstractMerchantRow
{
    private V4EntityNameKeyPair entityID;

    public CharacterNameRow(V4EntityNameKeyPair entityID)
    {
        super(entityID.getName(), "Click to add");
        this.entityID = entityID;
    }

    protected void doAction()
    {
        Group group = GroupProvider.provider.getActive();

        if (null != group)
        {
            group.addMember(entityID);
        }
    }

}