package rpg.v4.server.battle;

import rpg.v4.server.entity.Entity;
import rpg.v4.middleware.jaxb.V4Modifier;

import java.util.List;
import java.util.ArrayList;
import java.util.Observer;
import java.util.Observable;

import org.apache.log4j.Logger;

/**
 * Created by IntelliJ IDEA.
 * User: fjelds
 * Date: Nov 7, 2009
 * Time: 9:51:31 AM
 *
 * Gateway for modifiers. During battle modifiers are recorded here and used for unwinding.
 */
public class AppliedModifiersManager implements Observer
{
    private static final Logger logger = Logger.getLogger(AppliedModifiersManager.class);
    private static final Logger mdLogger = Logger.getLogger(ModifierDetails.class);
    private static final AppliedModifiersManager instance = new AppliedModifiersManager();

    public static AppliedModifiersManager getInstance()
    {
        return instance;
    }

    private class ModifierDetails
    {
        private Entity entity;
        private String targetState;
        private String v4ModifierType;
        private String sourceID;
        private int value;

        public ModifierDetails(Entity entity, String targetState, String v4ModifierType, String sourceID, int value)
        {

            this.entity = entity;
            this.targetState = targetState;
            this.v4ModifierType = v4ModifierType;
            this.sourceID = sourceID;
            this.value = value;
        }

        public Entity getEntity()
        {
            return entity;
        }

        public String getTargetState()
        {
            return targetState;
        }

        public String getV4ModifierType()
        {
            return v4ModifierType;
        }

        public String getSourceID()
        {
            return sourceID;
        }

        public int getValue()
        {
            return value;
        }

        public void unwind()
        {
            mdLogger.debug("Unwiding " + value + " to " + entity.getName() + "'s " + targetState);
            entity.removeModifier(targetState, v4ModifierType, sourceID);
        }
    }

    private List<ModifierDetails> modifiersAppliedDuringBattle = new ArrayList<ModifierDetails>(100);

    private AppliedModifiersManager()
    {
        logger.info("AppliedModifiersManager starting to listen");
        BattleStateManager.instance.addObserver(this);
    }

    public void applyModifier(Entity entity, V4Modifier v4Modifier)
    {
        Integer value = (Integer) v4Modifier.getModifier();
        this.applyModifier(entity, v4Modifier.getTargetID(), v4Modifier.getV4ModifierType(),
                v4Modifier.getSourceID(), value);
    }

    public void applyModifier(Entity entity, String targetState, String v4ModifierType, String sourceID, int value)
    {
        entity.applyModifier(targetState, v4ModifierType, sourceID, value);
        ModifierDetails modifierDetails = new ModifierDetails(entity, targetState, v4ModifierType, sourceID, value);
        modifiersAppliedDuringBattle.add(modifierDetails);
    }

    public void update(Observable observable, Object o)
    {
        logger.info("Will we unwind? " + BattleStateEnum.UNWIND.equals(o));
        if (BattleStateEnum.UNWIND.equals(o))
        {
            for (ModifierDetails modifierDetails : modifiersAppliedDuringBattle)
            {
                modifierDetails.unwind();
            }
        }
    }
}
