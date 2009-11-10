package rpg.util.state;

import junit.framework.TestCase;
import org.junit.Test;
import rpg.v4.server.state.State;
import rpg.v4.middleware.util.xml.XMLStateSkeletonKit;

import java.util.Map;

/**
 * Tests the half function for levels.
 */
public class HalfFunction extends TestCase
{
    @Test
    public void test_HalfFunction()
    {
        Map<String, State> stateMap = XMLStateSkeletonKit.instance().getStateSkeletonClone();
        State level = stateMap.get("Level");
        State baseDefence = stateMap.get("Base defense");
        State fortitude = stateMap.get("Fortitude");
        State reflex = stateMap.get("Reflex");
        State will = stateMap.get("Will");

        int expected = 9;
        level.add("Racial", "Racial", 1);
        checkStates(level, baseDefence, fortitude, reflex, will, ++expected);

        level.add("Untyped", "Test", 1);
        checkStates(level, baseDefence, fortitude, reflex, will, ++expected);

        level.add("Untyped", "Test2", 2);
        checkStates(level, baseDefence, fortitude, reflex, will, ++expected);
    }

    private void checkStates(State level, State baseDefence, State fortitude, State reflex, State will, int expected)
    {
        int t = (Integer) baseDefence.getTotal();

        assertTrue(baseDefence.getStateID() + ", expected: " + expected + ", " + baseDefence.toString() + ", " + level.toString(), 
                baseDefence.getTotal().equals(expected));
        assertTrue(fortitude.getStateID() + ", expected: " + expected + ", " + fortitude.toString() + ", " + level.toString(), fortitude.getTotal().equals(expected));
        assertTrue(reflex.getStateID() + ", expected: " + expected + ", " + reflex.toString() + ", " + level.toString(), reflex.getTotal().equals(expected));
        assertTrue(will.getStateID() + ", expected: " + expected + ", " + will.toString() + ", " + level.toString(), will.getTotal().equals(expected));
    }
}
