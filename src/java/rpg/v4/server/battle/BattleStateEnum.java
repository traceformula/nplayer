package rpg.v4.server.battle;

/**
 * Contains the various state a battle can be in:
 * - Pre-battle
 * - Initiative order creation
 * - Round-Pre
 * - Round
 * - Round-Post
 * - Post-Battle
 * <p/>
 * Used by BattleStateManager
 */
public enum BattleStateEnum
{
    PRE_BATTLE,
    INITIATIVE_ORDER_CREATION,
    ROUND_START,
    ROUND_START_ONGOING_DAMAGE,
    ROUND_START_REGENERATION,
    ROUND_START_OTHER_EFFECTS,
    ROUND_START_END_EFFECTS,
    ROUND_MIDDLE,
    ROUND_END,
    ROUND_END_SAVING_THROWS,
    ROUND_END_CHECK_ACTIONS_SPENT,
    ROUND_END_END_EFFECTS,
    END_BATTLE,
    UNWIND,
    RESET,
    SWITCH_ACTIVE_ENTITY,
    COMMIT
}
