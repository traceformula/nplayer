package rpg.util.state.ability;

import rpg.v4.server.state.State;
import rpg.v4.middleware.util.xml.XMLStateSkeletonKit;

import java.util.Map;

import org.junit.Test;

/**
 * Tests that strength modification trickles through and that the totals update.
 */
public class BasicAbilityStateTest extends AbilityTestKit
{
    @Test
    public void test_BasicStrength()
    {
        String stateID = "Strength";
        String linkedSaveStateID = "Constitution";
        String saveStateID = "Fortitude";
        String skillStateID = "Athletics";

        Map<String, State> stateMap = XMLStateSkeletonKit.instance().getStateSkeletonClone();

        stateTest(stateID, linkedSaveStateID, saveStateID, skillStateID, stateMap);
    }

    @Test
    public void test_BasicConstitution()
    {
        String stateID = "Constitution";
        String linkedSaveStateID = "Strength";
        String saveStateID = "Fortitude";
        String skillStateID = "Endurance";

        Map<String, State> stateMap = XMLStateSkeletonKit.instance().getStateSkeletonClone();

        stateTest(stateID, linkedSaveStateID, saveStateID, skillStateID, stateMap);
    }

    @Test
    public void test_BasicDexterity()
    {
        String stateID = "Dexterity";
        String linkedSaveStateID = "Intelligence";
        String saveStateID = "Reflex";
        String skillStateID = "Acrobatics";

        Map<String, State> stateMap = XMLStateSkeletonKit.instance().getStateSkeletonClone();

        stateTest(stateID, linkedSaveStateID, saveStateID, skillStateID, stateMap);
    }

    @Test
    public void test_BasicIntelligence()
    {
        String stateID = "Intelligence";
        String linkedSaveStateID = "Dexterity";
        String saveStateID = "Reflex";
        String skillStateID = "History";

        Map<String, State> stateMap = XMLStateSkeletonKit.instance().getStateSkeletonClone();

        stateTest(stateID, linkedSaveStateID, saveStateID, skillStateID, stateMap);
    }

    @Test
    public void test_BasicWisdom()
    {
        String stateID = "Wisdom";
        String linkedSaveStateID = "Charisma";
        String saveStateID = "Will";
        String skillStateID = "Heal";

        Map<String, State> stateMap = XMLStateSkeletonKit.instance().getStateSkeletonClone();

        stateTest(stateID, linkedSaveStateID, saveStateID, skillStateID, stateMap);

        // Test passive stuff
        State passiveInsigthState = stateMap.get("Passive insight");
        assertTrue("Passive insight: " + passiveInsigthState.toString(), passiveInsigthState.getTotal().equals(5));

        State passivePerceptionState = stateMap.get("Passive perception");
        assertTrue("Passive perception: " + passivePerceptionState.toString(), passivePerceptionState.getTotal().equals(5));

    }

    @Test
    public void test_BasicCharisma()
    {
        String stateID = "Charisma";
        String linkedSaveStateID = "Wisdom";
        String saveStateID = "Will";
        String skillStateID = "Diplomacy";

        Map<String, State> stateMap = XMLStateSkeletonKit.instance().getStateSkeletonClone();

        stateTest(stateID, linkedSaveStateID, saveStateID, skillStateID, stateMap);
    }


}
