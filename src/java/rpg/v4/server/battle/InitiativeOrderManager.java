package rpg.v4.server.battle;

import org.apache.log4j.Logger;
import rpg.v4.middleware.jaxb.V4EntityNameKeyPair;
import rpg.v4.middleware.util.collection.ObservableArrayList;
import rpg.v4.middleware.util.xml.XMLGroupKit;
import static rpg.v4.server.battle.BattleStateEnum.*;
import rpg.v4.server.entity.Entity;
import rpg.v4.server.group.Group;
import rpg.v4.server.state.State;
import rpg.v4.server.vaults.EntityCollectionManager;

import java.util.*;

/**
 * Keeps track of which groups are part of the battle and the initiative order.
 * <p/>
 * Class will observe the group list so that if any group is added then it will
 * start observing the group. Once observing the group, it can deduce if the group
 * is selected to be part of the battle or not, and will add/remove the group
 * members to the initiative list accordingly by using the Initiative state of the
 * entity.
 * <p/>
 * The Initiative state is then observed for Entities part of the battle. When the
 * initiative changes then their order in the list is changed. However, once a battle
 * has commenced, the current initiative order needs to be retained and hence
 * this class stops observing the initiative states.
 */
public class InitiativeOrderManager extends Observable implements Observer
{
    private static final Logger logger = Logger.getLogger(InitiativeOrderManager.class);
    private static final InitiativeOrderManager instance = new InitiativeOrderManager();

    private ObservableArrayList<V4EntityNameKeyPair> entityInitiativeOrderList;
    private List<String> alphabeticalParticipantList;
    private V4EntityNameKeyPair activeEntity = null;
    private int activeIndex = 0;

    private InitiativeOrderManager()
    {
        entityInitiativeOrderList = new ObservableArrayList<V4EntityNameKeyPair>(100);
        BattleStateManager.instance.addObserver(this);
    }

    public V4EntityNameKeyPair getActiveEntity()
    {
        return activeEntity;
    }

    public void next()
    {
        // Set current active entity to non-active
        if (activeEntity != null)
        {
            activeEntity.setIsActive(false);
        }

        if (entityInitiativeOrderList.size() > 0)
        {
            if (activeEntity == null)
            {
                activeIndex = 0;
            } else
            {
                activeIndex++;
                if (activeIndex >= entityInitiativeOrderList.size())
                {
                    activeIndex = 0;
                }
            }

            activeEntity = entityInitiativeOrderList.get(activeIndex);
            activeEntity.setIsActive(true);
            BattleStateManager.instance.moveForward();
        } else if (activeEntity != null)
        {
            logger.error("Active entity is not null AND there's no entities in the initiative list.");
        }
    }

    private void createInitiativeOrder(ObservableArrayList<String> groupNames)
    {
        // Make sure the initiative order is clear
        entityInitiativeOrderList.clear();

        // Move entity identifiers into one list.
        Map<Integer, List<V4EntityNameKeyPair>> initiativeBuckets = bucketGroupMembers(groupNames);
        for (int i = 1000; i >= -100; i--)
        {
            if (initiativeBuckets.containsKey(i))
            {
                List<V4EntityNameKeyPair> nkpList = initiativeBuckets.get(i);

                // TODO: Tie breaks

                for (V4EntityNameKeyPair nkp : nkpList)
                    entityInitiativeOrderList.add(nkp);
            }
        }

        alphabeticalParticipantList = new ArrayList<String>(entityInitiativeOrderList.size());
        for (V4EntityNameKeyPair nkp : entityInitiativeOrderList)
        {
            alphabeticalParticipantList.add(nkp.getName());
        }
        Collections.sort(alphabeticalParticipantList);

        BattleStateManager.instance.moveForward();
    }

    private Map<Integer, List<V4EntityNameKeyPair>> bucketGroupMembers(ObservableArrayList<String> groupNames)
    {
        // Create buckets and assign entity identifiers to their assigned bucket.
        Map<Integer, List<V4EntityNameKeyPair>> initiativeBuckets = new HashMap<Integer, List<V4EntityNameKeyPair>>(100);
        for (String groupName : groupNames)
        {
            Group group = XMLGroupKit.instance().getGroup(groupName);
            if (group.isInBattle())
            {
                for (V4EntityNameKeyPair nkp : group.getV4Group().getV4EntityNameKeyPair())
                {
                    Entity entity = EntityCollectionManager.get(nkp);
                    State initiativeState = entity.getState("Initiative");
                    Integer initiativeTotal = (Integer) initiativeState.getTotal();

                    if (!initiativeBuckets.containsKey(initiativeTotal))
                    {
                        initiativeBuckets.put(initiativeTotal, new ArrayList<V4EntityNameKeyPair>(5));
                    }

                    initiativeBuckets.get(initiativeTotal).add(nkp);
                }
            }
        }

        return initiativeBuckets;
    }

    public static InitiativeOrderManager getInstance()
    {
        return instance;
    }

    public ObservableArrayList<V4EntityNameKeyPair> getEntityInitiativeOrderList()
    {
        return entityInitiativeOrderList;
    }

    public List<String> getAlphabeticalParticipantList()
    {
        return alphabeticalParticipantList;
    }

    public void update(Observable o, Object arg)
    {
        if (RESET.equals(arg))
        {
            entityInitiativeOrderList.clear();
            activeIndex = 0;
            activeEntity = null;
        } else if (INITIATIVE_ORDER_CREATION.equals(arg))
        {
            ObservableArrayList<String> groupNames = XMLGroupKit.instance().getGroupNames();
            createInitiativeOrder(groupNames);
        } else if (SWITCH_ACTIVE_ENTITY.equals(arg))
        {
            next();
        }
    }
}
