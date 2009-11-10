package rpg.util.state;

import junit.framework.TestCase;
import org.junit.Test;
import rpg.v4.server.entity.Entity;
import rpg.v4.server.state.State;
import rpg.v4.server.state.impl.StringListState;
import rpg.v4.middleware.util.xml.XMLStateSkeletonKit;
import com.sun.org.apache.xerces.internal.xs.StringList;

import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: fjelds
 * Date: Sep 13, 2009
 * Time: 12:02:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class StringListTest extends TestCase
{
    @Test
    public void test_StringList()
    {
        Map<String, State> stateMap = XMLStateSkeletonKit.instance().getStateSkeletonClone();
        State state = stateMap.get("Added Armor prof.");
        assertNotNull(state);

        Class stateClass = state.getClass();
        Class slClass = StringListState.class;
        assertTrue(stateClass.equals(slClass));
        assertTrue(state.getTotal().equals(""));

        String modifierType = "test";
        String string = "Chain mail", string2 = "Leather", multiTotal = string2 + ", " + string;
        String source = "Sven-Ivar", source2 = "Fjeld";

        state.add(modifierType, source, string);
        assertTrue(state.getTotal().equals(string));

        state.add(modifierType, source2, string);
        assertTrue(state.getTotal().equals(string));

        state.add(modifierType, source2, string2);
        String total = (String) state.getTotal();
        assertTrue(total.equals(multiTotal));

        state.remove(modifierType, source2);
        assertTrue(state.getTotal().equals(string));

        state.remove(modifierType, source);
        assertTrue(state.getTotal().equals(""));
    }
}
