package rpg.v4.server.battle;

import org.apache.log4j.Logger;
import static rpg.v4.server.battle.BattleStateEnum.*;

import java.util.Observable;

/**
 * A battle state manager - effectively a statemaching describing and publishing what states a battler
 * can be in. Will loop indefinetly until endGame is called.
 */
public class BattleStateManager extends Observable
{
    private static final Logger logger = Logger.getLogger(BattleStateManager.class);
    public static final BattleStateManager instance = new BattleStateManager();

    private BattleStateEnum battleState = PRE_BATTLE;

    private BattleStateManager()
    {
        InitiativeOrderManager.getInstance();
        addObserver(EntityRoundManager.getInstance());
    }

    public BattleStateEnum moveForward()
    {
        switch(battleState)
        {
            case PRE_BATTLE: battleState = INITIATIVE_ORDER_CREATION; break;
            case INITIATIVE_ORDER_CREATION: battleState = SWITCH_ACTIVE_ENTITY; break;
            case SWITCH_ACTIVE_ENTITY: battleState = ROUND_START; break;
            case ROUND_START: battleState = ROUND_START_ONGOING_DAMAGE; break;
            case ROUND_START_ONGOING_DAMAGE: battleState = ROUND_START_REGENERATION; break;
            case ROUND_START_REGENERATION: battleState = ROUND_START_OTHER_EFFECTS; break;
            case ROUND_START_OTHER_EFFECTS: battleState = ROUND_START_END_EFFECTS; break;
            case ROUND_START_END_EFFECTS: battleState = ROUND_MIDDLE; break;
            case ROUND_MIDDLE: battleState = ROUND_END; break;
            case ROUND_END: battleState = ROUND_END_SAVING_THROWS; break;
            case ROUND_END_SAVING_THROWS: battleState = ROUND_END_CHECK_ACTIONS_SPENT; break;
            case ROUND_END_CHECK_ACTIONS_SPENT: battleState = ROUND_END_END_EFFECTS; break;
            case ROUND_END_END_EFFECTS: battleState = SWITCH_ACTIVE_ENTITY; break;
            case END_BATTLE: battleState = UNWIND; break;
            case UNWIND: battleState = RESET; break;
            case RESET: battleState = PRE_BATTLE; break;
        }

        setChanged();
        notifyObservers(battleState);
        return battleState;
    }

    public BattleStateEnum endGame()
    {
        battleState = END_BATTLE;
        setChanged();
        notifyObservers(battleState);

        // Unwind
        moveForward();

        // Reset
        moveForward();

        // Pre battle
        moveForward();

        return battleState;
    }

    public void startGame()
    {
        moveForward();
    }

    public void commitActions()
    {
        setChanged();
        notifyObservers(COMMIT);
    }
}
