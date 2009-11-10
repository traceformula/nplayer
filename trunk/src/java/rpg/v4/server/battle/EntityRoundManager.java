package rpg.v4.server.battle;

import org.apache.log4j.Logger;
import rpg.v4.middleware.jaxb.V4EntityNameKeyPair;
import rpg.v4.middleware.jaxb.V4GenericAction;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by IntelliJ IDEA.
 * User: fjelds
 * Date: Nov 1, 2009
 * Time: 3:08:32 PM
 *
 * Allows an entity to do all the things it needs to do in pre-round, round and post-round,
 * such as ending actions and saving throws.
 */
public class EntityRoundManager extends Observable implements Observer
{
    private static final Logger logger = Logger.getLogger(EntityRoundManager.class);
    private static final EntityRoundManager instance = new EntityRoundManager();

    private EntityRoundManager()
    {
    }

    private void notifyWithActions(List l)
    {
        if (l != null && l.size() > 0)
        {
            setChanged();
            notifyObservers(l);
        }

        BattleStateManager.instance.moveForward();
    }

    private void roundStart()
    {
        logger.info("Round start");
        V4EntityNameKeyPair nkp = InitiativeOrderManager.getInstance().getActiveEntity();

        List<String> actionList = new ArrayList<String>();
        actionList.add(nkp.getName() + "'s round");
        notifyWithActions(actionList);
    }

    private void roundStartOngoingDamage()
    {
        String s = "Round start - ongoing damage";
        notifyWithActions(null);
    }

    private void roundStartEndEffects()
    {
        String s = "Round start - end effects";
        notifyWithActions(null);
    }

    private void roundStartOtherEffects()
    {
        String s = "Round start - other effects";
        notifyWithActions(null);
    }

    private void roundStartRegeneration()
    {
        String s = "Round start - regeneration";
        notifyWithActions(null);
    }

    public static EntityRoundManager getInstance()
    {
        return instance;
    }

    private void roundMiddle()
    {
        logger.info("Round middle");
    }

    private void roundEnd()
    {
        logger.info("Round end");
        BattleStateManager.instance.moveForward();
    }

    public void update(Observable observable, Object o)
    {
        BattleStateEnum battleState = (BattleStateEnum) o;
        switch(battleState)
        {
            case ROUND_START: roundStart(); break;
            case ROUND_START_ONGOING_DAMAGE: roundStartOngoingDamage(); break;
            case ROUND_START_REGENERATION: roundStartRegeneration(); break;
            case ROUND_START_OTHER_EFFECTS: roundStartOtherEffects(); break;
            case ROUND_START_END_EFFECTS: roundStartEndEffects(); break;
            case ROUND_MIDDLE: roundMiddle(); break;
            case ROUND_END: roundEnd(); break;
            case ROUND_END_SAVING_THROWS: roundEndSavingThrows(); break;
            case ROUND_END_CHECK_ACTIONS_SPENT: roundEndActionsSpent(); break;
            case ROUND_END_END_EFFECTS: roundEndEndEffects(); break;
        }
    }

    private void roundEndEndEffects()
    {
        String s = "Round end - end effects";
        notifyWithActions(null);
    }

    private void roundEndActionsSpent()
    {
        String s = "Round end - check actions spent";
        notifyWithActions(null);
    }

    private void roundEndSavingThrows()
    {
        String s = "Round end - saving throws";
        notifyWithActions(null);
    }

    public void addAction(V4GenericAction v4GenericAction)
    {
        List<V4GenericAction> actions = new ArrayList<V4GenericAction>();
        actions.add(v4GenericAction);
        setChanged();
        notifyObservers(actions);
    }


}
