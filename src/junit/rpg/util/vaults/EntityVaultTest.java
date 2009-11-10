package rpg.util.vaults;

import junit.framework.TestCase;
import org.junit.Test;
import rpg.v4.server.entity.Entity;
import rpg.v4.server.vaults.EntityCollectionManager;

/**
 * Tests basic entity vault functionality
 */
public class EntityVaultTest extends TestCase
{
    @Test
    public void test_EntityID()
    {
        Entity entity1 = new Entity(), entity2 = new Entity(), entity3 = EntityCollectionManager.newInstance();

        assertTrue("Entity IDs are the same: " + entity1.getID() + " equals " + entity2.getID(), !entity1.getID().equals(entity2.getID()));
        assertTrue("Entity IDs are the same: " + entity1.getID() + " equals " + entity3.getID(), !entity1.getID().equals(entity3.getID()));
        assertTrue("Entity IDs are the same: " + entity2.getID() + " equals " + entity3.getID(), !entity2.getID().equals(entity3.getID()));

        assertFalse("Vault erronously added entity!", EntityCollectionManager.add(entity3));
        int oldSize = EntityCollectionManager.getSize();
        EntityCollectionManager.add(entity1);
        EntityCollectionManager.add(entity2);
        int newSize = EntityCollectionManager.getSize();

        assertTrue("Entities weren't added!", oldSize != newSize);
        assertTrue("Entities weren't added properly!", (oldSize + 2) == newSize);
    }
}