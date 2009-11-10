package rpg.v4.client.gui.edit.inventory.merchant;

import rpg.v4.client.provider.impl.CharacterProvider;
import rpg.v4.middleware.jaxb.V4ItemCapsule;
import rpg.v4.middleware.util.deepcopy.FastDeepCopy;
import rpg.v4.server.entity.Entity;

/**
 * Displays the name of an item {@link rpg.v4.middleware.jaxb.V4ItemCapsule} and allows it to be added to an {@link rpg.v4.server.entity.Entity} inventory.
 * The item is deep copied into a clone before added.
 */
public class MerchantItemRow extends AbstractMerchantRow
{
    private V4ItemCapsule item;

    public MerchantItemRow(V4ItemCapsule item)
    {
        super(item.getName(), "Click to add");
        this.item = item;
    }

    protected void doAction()
    {
        Entity entity = CharacterProvider.provider.getActive();

        if (null != entity)
        {
            V4ItemCapsule newItem = (V4ItemCapsule) FastDeepCopy.deepCopy(item);
            newItem.setUUID("BAB " + newItem.getName());
            entity.getInventoryList().add(newItem);
        }
    }
}
