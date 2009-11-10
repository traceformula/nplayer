package rpg.util.state;

import junit.framework.TestCase;
import org.junit.Test;
import rpg.v4.server.entity.Entity;

/**
 * Test that ID numbers aren't the same
 */
public class EntityUID extends TestCase
{
    @Test
    public void test_EntityID()
    {
        Entity entity1 = new Entity(), entity2 = new Entity();
        assertTrue("Entity IDs are the same: " + entity1.getID() + " equals " + entity2.getID(),
                !entity1.getID().equals(entity2.getID()));        
    }
}
