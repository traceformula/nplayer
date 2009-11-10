package rpg;

import junit.framework.Test;
import junit.framework.TestSuite;
import rpg.util.state.ability.BasicAbilityStateTest;
import rpg.util.state.BaseAttackBonusTest;
import rpg.util.state.EntityUID;
import rpg.util.state.StringListTest;
import rpg.util.vaults.EntityVaultTest;


/**
 *
 */
public class StateTestSuite
{
    public static Test suite() {

        TestSuite suite = new TestSuite();
        suite.addTestSuite(BaseAttackBonusTest.class);
        suite.addTestSuite(BasicAbilityStateTest.class);
        suite.addTestSuite(BaseAttackBonusTest.class);
        suite.addTestSuite(EntityUID.class);
        suite.addTestSuite(EntityVaultTest.class);
        suite.addTestSuite(StringListTest.class);
        return suite;
    }

    /**
     * Runs the test suite using the textual runner.
     */
    public static void main(String[] args) {
        junit.textui.TestRunner.run(suite());
    }
}
