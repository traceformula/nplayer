package rpg.util.state;

import junit.framework.TestCase;
import org.junit.Test;
import rpg.v4.server.state.State;
import rpg.v4.middleware.util.xml.XMLStateSkeletonKit;

import java.util.Map;

/**
 * Tests that base attack bonus is updated correctly
 */
public class BaseAttackBonusTest extends TestCase
{
    @Test
    public void test_BABFunction()
    {
        Map<String, State> stateMap = XMLStateSkeletonKit.instance().getStateSkeletonClone();
        State experiencePoints = stateMap.get("Experience points");
        State baseAttackBonus = stateMap.get("Base Attack Bonus");
        State strength = stateMap.get("Strength");
        State selectedAction = stateMap.get("Selected Action");

        int expected = 0;
        assertTrue(baseAttackBonus.getStateID() + ", expected: " + expected + ", bab actual total: " + baseAttackBonus.getTotal(),
                baseAttackBonus.getTotal().equals(expected));

        experiencePoints.add("Untyped", "LevelTest", "5000"); // Level 4, half = 2
        expected = 2;
        assertTrue(baseAttackBonus.getStateID() + ", expected: " + expected + ", bab actual total: " + baseAttackBonus.getTotal(),
                baseAttackBonus.getTotal().equals(expected));

        strength.add("Untyped", "StrTest", 4); // Str 14, bonus = 2, no ability should be propagated through
        assertTrue(baseAttackBonus.getStateID() + ", expected: " + expected + ", bab actual total: " + baseAttackBonus.getTotal(),
                baseAttackBonus.getTotal().equals(expected));

        strength.remove("Untyped", "StrTest"); // Str 10, bonus = 0, no ability should be propagated through
        assertTrue(baseAttackBonus.getStateID() + ", expected: " + expected + ", bab actual total: " + baseAttackBonus.getTotal(),
                baseAttackBonus.getTotal().equals(expected));

        // Setting an attack. Strength should flow through, atm. 0;
        selectedAction.add("Untyped", "ActionTest", "Melee Basic Attack");
        assertTrue(baseAttackBonus.getStateID() + ", expected: " + expected + ", bab actual total: " + baseAttackBonus.getTotal(),
                baseAttackBonus.getTotal().equals(expected));

        strength.add("Untyped", "StrTest", 4); // Str 14, bonus = 2
        expected += 2;
        assertTrue(baseAttackBonus.getStateID() + ", expected: " + expected + ", bab actual total: " + baseAttackBonus.getTotal(),
                baseAttackBonus.getTotal().equals(expected));
    }
}
