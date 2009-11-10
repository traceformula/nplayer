package rpg.util.state.ability;

import rpg.v4.server.state.State;
import rpg.v4.middleware.util.xml.XMLModifierTypeKit;

import java.util.Map;

import junit.framework.TestCase;

/**
 * To change this template use File | Settings | File Templates.
 */
public class AbilityTestKit extends TestCase
{
    void stateTest(String stateID, String linkedSaveStateID, String saveStateID, String skillStateID, Map<String, State> stateMap)
    {
        State state = stateMap.get(stateID);

        assertTrue(0 == (Integer) state.getTotal());

        // Remove non-exisstant state
        try
        {
            state.remove("Feat", "Non existant");
        } catch (Exception e)
        {
            // Do nothing
        }

        assertTrue(0 == (Integer) state.getTotal());

        // Remove non-exisstant modifier type
        try
        {
            state.remove("Non existant", "Non existant");
        } catch (Exception e)
        {
            // Do nothing
        }

        assertTrue(0 == (Integer) state.getTotal());

        State save = stateMap.get(saveStateID);
        State skill = stateMap.get(skillStateID);

        Integer bonus = 1;
        Integer cummulativeBonus = 0;
        Integer expectedModifier = 0;
        for (String modifierType : XMLModifierTypeKit.instance().getTypes())
        {
            if (!"Ability,Initial,Untyped".contains(modifierType))
            {
                cummulativeBonus += bonus;
                expectedModifier = (int) Math.floor(cummulativeBonus/2);
                state.add(modifierType, "Test", bonus);

                // Check the state
                assertTrue("Ability modifier was different. " + state.getStateID() + modifierType + ": expected: " + (expectedModifier) + ", was "
                        + state.getTotal() + ". " + state.toString(),
                        expectedModifier.equals(state.getTotal()));

                // Check the save
                assertTrue("Save total was different. " + save.getStateID() + ": expected: " + (10 + expectedModifier) +
                        " was " + save.getTotal() + ". " + save.toString(),
                        (10 + expectedModifier) == (Integer) save.getTotal());

                // Check the skill
                assertTrue("Skill total was different. " + skill.getStateID() + ": expected: " + (10 + expectedModifier) +
                        " was " + skill.getTotal() + ". " + skill.toString(),
                        expectedModifier.equals(skill.getTotal()));
            }
        }

        // Remove exissting modifier
        int oldAbilityTotal = (Integer) state.getTotal();
        state.remove("Feat", "Test");
        state.remove("Armor", "Test");
        assertTrue(state.getStateID() + " Removed total check", (oldAbilityTotal - 1) == (Integer) state.getTotal());

        // Overwrite existing modifier
        oldAbilityTotal = (Integer) state.getTotal();
        state.add("Racial", "Test", 2);
        state.add("Shield", "Test", 2);
        assertTrue(state.getStateID() + " Overwritten total check", (oldAbilityTotal + 1) == (Integer) state.getTotal());


        // Checks that max of STR/CON modifier works for saving throw. Save should now use the other ability modifier.
        State linkedSaveAbility = stateMap.get(linkedSaveStateID);
        int impossiblyHigh = 100;
        linkedSaveAbility.add("Racial", "Test", impossiblyHigh);
        expectedModifier = 10 + impossiblyHigh;
        Integer expected = (int) Math.floor(expectedModifier / 2) - 5;
        assertTrue(save.getStateID() + ": " + (10 + expected) + " == " + save.getTotal() + ", " + save.toString(),
                (10 + expected) == (Integer) save.getTotal());
    }
}
