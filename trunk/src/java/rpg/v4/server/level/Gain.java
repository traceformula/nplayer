package rpg.v4.server.level;

import rpg.v4.server.entity.Entity;
import rpg.v4.server.state.State;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents small isolated changes to states.
 */
public enum Gain
{
    POWER_AT_WILL_ATTACK_1("# of Powers known - At-will"),
    POWER_AT_WILL_ATTACK_2("# of Powers known - At-will", "Level-up_"),
    POWER_ENCOUNTER("# of Powers known - Encounter"),
    POWER_DAILY("# of Powers known - Daily"),
    POWER_UTILITY("# of Powers known - Utility"),
    FEAT("# of Feats known"),
    ABILITY_PLUST_1_TO_TWO(false),
    ABILITY_ALL(true);

    private String stateID;
    private String sourceID = "Level-up ";
    private Integer bonus = 1;
    private boolean toAllAbilities;

    private Gain(String stateID)
    {
        this.stateID = stateID;
    }

    Gain(String stateID, String sourceID)
    {
        this(stateID);
        this.sourceID = sourceID;
    }

    private Gain(boolean toAllAbilities)
    {
        stateID = null;
        this.toAllAbilities = toAllAbilities;
    }

    public void addTo(Entity entity, int levelID)
    {
        if (null != stateID)
        {
            State state = entity.getState(stateID);
            state.add("Untyped", sourceID + levelID, bonus);
        }
    }

    public void removeFrom(Entity entity, int levelID)
    {
        if (null != stateID)
        {
            entity.getState(stateID).remove("Untyped", sourceID + levelID);
        }
    }

    private static final List<Gain> LEVEL_1 = new ArrayList<Gain>(),
            LEVEL_2 = new ArrayList<Gain>(),
            LEVEL_3 = new ArrayList<Gain>(),
            LEVEL_4 = new ArrayList<Gain>(),
            LEVEL_5 = new ArrayList<Gain>(),
            LEVEL_6 = new ArrayList<Gain>(),
            LEVEL_7 = new ArrayList<Gain>(),
            LEVEL_8 = new ArrayList<Gain>(),
            LEVEL_9 = new ArrayList<Gain>(),
            LEVEL_10 = new ArrayList<Gain>(),
            LEVEL_11 = new ArrayList<Gain>(),
            LEVEL_12 = new ArrayList<Gain>(),
            LEVEL_13 = new ArrayList<Gain>(),
            LEVEL_14 = new ArrayList<Gain>(),
            LEVEL_15 = new ArrayList<Gain>(),
            LEVEL_16 = new ArrayList<Gain>(),
            LEVEL_17 = new ArrayList<Gain>(),
            LEVEL_18 = new ArrayList<Gain>(),
            LEVEL_19 = new ArrayList<Gain>(),
            LEVEL_20 = new ArrayList<Gain>(),
            LEVEL_21 = new ArrayList<Gain>(),
            LEVEL_22 = new ArrayList<Gain>(),
            LEVEL_23 = new ArrayList<Gain>(),
            LEVEL_24 = new ArrayList<Gain>(),
            LEVEL_25 = new ArrayList<Gain>(),
            LEVEL_26 = new ArrayList<Gain>(),
            LEVEL_27 = new ArrayList<Gain>(),
            LEVEL_28 = new ArrayList<Gain>(),
            LEVEL_29 = new ArrayList<Gain>(),
            LEVEL_30 = new ArrayList<Gain>();

    public static final Map<Integer, List<Gain>> LEVEL_GAINS_MAP = new HashMap<Integer, List<Gain>>(30);

    static {
        LEVEL_1.add(FEAT);
        LEVEL_1.add(POWER_AT_WILL_ATTACK_1);
        LEVEL_1.add(POWER_AT_WILL_ATTACK_2);
        LEVEL_1.add(POWER_ENCOUNTER);
        LEVEL_1.add(POWER_DAILY);

        LEVEL_2.add(FEAT);
        LEVEL_2.add(POWER_UTILITY);

        LEVEL_3.add(POWER_ENCOUNTER);

        LEVEL_4.add(FEAT);
        LEVEL_4.add(ABILITY_PLUST_1_TO_TWO);

        LEVEL_5.add(POWER_DAILY);

        LEVEL_6.add(POWER_UTILITY);
        LEVEL_6.add(FEAT);

        LEVEL_7.add(POWER_ENCOUNTER);

        LEVEL_8.add(FEAT);
        LEVEL_8.add(ABILITY_PLUST_1_TO_TWO);        

        LEVEL_GAINS_MAP.put(1, LEVEL_1);
        LEVEL_GAINS_MAP.put(2, LEVEL_2);
        LEVEL_GAINS_MAP.put(3, LEVEL_3);
        LEVEL_GAINS_MAP.put(4, LEVEL_4);
        LEVEL_GAINS_MAP.put(5, LEVEL_5);
        LEVEL_GAINS_MAP.put(6, LEVEL_6);
        LEVEL_GAINS_MAP.put(7, LEVEL_7);
        LEVEL_GAINS_MAP.put(8, LEVEL_8);
        LEVEL_GAINS_MAP.put(9, LEVEL_9);
        LEVEL_GAINS_MAP.put(10, LEVEL_10);
        LEVEL_GAINS_MAP.put(11, LEVEL_11);
        LEVEL_GAINS_MAP.put(12, LEVEL_12);
        LEVEL_GAINS_MAP.put(13, LEVEL_13);
        LEVEL_GAINS_MAP.put(14, LEVEL_14);
        LEVEL_GAINS_MAP.put(15, LEVEL_15);
        LEVEL_GAINS_MAP.put(16, LEVEL_16);
        LEVEL_GAINS_MAP.put(17, LEVEL_17);
        LEVEL_GAINS_MAP.put(18, LEVEL_18);
        LEVEL_GAINS_MAP.put(19, LEVEL_19);
        LEVEL_GAINS_MAP.put(20, LEVEL_20);
        LEVEL_GAINS_MAP.put(21, LEVEL_21);
        LEVEL_GAINS_MAP.put(22, LEVEL_22);
        LEVEL_GAINS_MAP.put(23, LEVEL_23);
        LEVEL_GAINS_MAP.put(24, LEVEL_24);
        LEVEL_GAINS_MAP.put(25, LEVEL_25);
        LEVEL_GAINS_MAP.put(26, LEVEL_26);
        LEVEL_GAINS_MAP.put(27, LEVEL_27);
        LEVEL_GAINS_MAP.put(28, LEVEL_28);
        LEVEL_GAINS_MAP.put(29, LEVEL_29);
        LEVEL_GAINS_MAP.put(30, LEVEL_30);
    }

    public static List<Gain> getGainsForLevel(int level)
    {
        return LEVEL_GAINS_MAP.get(level);
    }
}
