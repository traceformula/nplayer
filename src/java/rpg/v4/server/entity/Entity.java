package rpg.v4.server.entity;

import org.apache.log4j.Logger;
import rpg.v4.middleware.jaxb.*;
import rpg.v4.middleware.util.collection.ObservableArrayList;
import rpg.v4.middleware.util.xml.XMLClassKit;
import rpg.v4.middleware.util.xml.XMLFactory;
import rpg.v4.middleware.util.xml.XMLRaceKit;
import rpg.v4.middleware.util.xml.XMLStateSkeletonKit;
import rpg.v4.middleware.util.StringConstants;
import rpg.v4.server.entity.slot.Slot;
import rpg.v4.server.level.LevelChangeManager;
import rpg.v4.server.state.State;
import rpg.v4.server.state.impl.IntegerState;
import rpg.v4.server.state.impl.StringListState;
import rpg.v4.server.state.impl.StringState;
import rpg.v4.server.state.impl.function.SpecificWeaponProficiencyBonus;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Holds all the entity state and manages complicated state changes.
 * <p/>
 * It's observable just for the Color which can change.
 */
public class Entity extends Observable implements Observer
{
    private static Logger logger = Logger.getLogger(Entity.class);
    private static String ID = "Unique ID";
    private Map<String, State> stateMap, dynamicStateMap;
    private V4Race race;
    private State uniqueIDState, raceState, characterClassState, paragonPathState, nameState, levelState;
    private V4Class characterClass, paragonPath;
    private V4EntityNameKeyPair nkp;
    private ObservableArrayList<V4ItemCapsule> inventoryList;
    private EquipmentSlotManager equipmentSlotManager;
    private Color color = Color.WHITE;
    private ObservableArrayList<String> dynamicStateIDs;

    public Entity()
    {
        this(XMLStateSkeletonKit.instance().getStateSkeletonClone());
    }

    public Entity(Map<String, State> stateMap)
    {
        equipmentSlotManager = new EquipmentSlotManager(this);
        this.stateMap = stateMap;

        uniqueIDState = stateMap.get(ID);
        if ("".equals(uniqueIDState.getTotal()))
        {
            UUID uuid = UUID.randomUUID();
            uniqueIDState.add("Initial", this, uuid.toString());
        }

        raceState = stateMap.get("Race");
        raceState.addObserver(this);
        if (!raceState.getTotal().equals(""))
            update((Observable) raceState, null);

        characterClassState = stateMap.get("Character class");
        characterClassState.addObserver(this);
        if (!characterClassState.getTotal().equals(""))
            update((Observable) characterClassState, null);

        paragonPathState = stateMap.get("Paragon path");
        paragonPathState.addObserver(this);
        if (!paragonPathState.getTotal().equals(""))
            update((Observable) paragonPathState, null);

        nameState = stateMap.get("Name");
        nameState.addObserver(this);
        if (nameState.getTotal().equals(""))
            nameState.add("New character", "", "");

        levelState = stateMap.get("Level");
        levelState.addObserver(this);
        LevelChangeManager.manageLevelChange((Integer) levelState.getTotal(), this);

        inventoryList = new ObservableArrayList<V4ItemCapsule>(50);
        inventoryList.addObserver(this);

        nkp = XMLFactory.createV4EntityNameKeyPair((String) nameState.getTotal(), (String) uniqueIDState.getTotal());

        dynamicStateIDs = new ObservableArrayList<String>(50);
        dynamicStateMap = new HashMap<String, State>(50);
    }

    public Entity(V4Entity v4Entity, V4EntityNameKeyPair nkp)
    {
        this();
        this.nkp = nkp;
        uniqueIDState.add("Initial", this, nkp.getKey());

        for (V4State v4State : v4Entity.getV4State())
        {
            //logger.info("StateID: " + v4State.getStateID());
            State state = getState(v4State.getStateID());
            Object initialValue = v4State.getInitialValue();
            // If it's not a string state it must be an integer state
            if (!(state instanceof StringState) && !(state instanceof StringListState))
            {
                initialValue = Integer.valueOf((String) initialValue);
            }

            // String lists don't care about inital values
            if (!(state instanceof StringListState))
            {
                state.add("Initial", "Initial value", initialValue);
            }
        }

        if (null != v4Entity.getV4Inventory())
        {
            for (V4ItemCapsule itemCapsule : v4Entity.getV4Inventory().getV4ItemCapsule())
            {
                inventoryList.add(itemCapsule);
            }
        }

        // Load equipped objects
        for (V4BodyPart bodyPart : v4Entity.getV4EquippedObjects().getV4BodyPart())
        {
            V4ItemCapsule itemCapsule = bodyPart.getV4ItemCapsule();
            Slot slot = this.getEquipmentSlotManager().getMultiSlot(bodyPart.getName());
            slot.equip(itemCapsule);
        }
    }

    public Map<String, State> getStateMap()
    {
        return stateMap;
    }

    public State getState(String stateID)
    {
        return stateMap.get(stateID);
    }

    public State getDynamicState(String dynamicStateID)
    {
        return dynamicStateMap.get(dynamicStateID);
    }

    public String getID()
    {
        return (String) uniqueIDState.getTotal();
    }

    public String getName()
    {
        State state = stateMap.get("Name");
        return (String) state.getTotal();
    }

    public void setRace(String raceID)
    {
        if (null != race)
        {
            removeModifiers(race.getV4ModifierList().getV4Modifier());
        }

        race = XMLRaceKit.instance().getRace(raceID);
        // Set the slots before the update to the state.
        equipmentSlotManager.setRaceSlots(race);
        applyModifiers(race.getV4ModifierList().getV4Modifier());
    }

    public void applyModifiers(List<V4Modifier> modifiers)
    {
        if (null == modifiers)
        {
            return;
        }

        for (V4Modifier modifier : modifiers)
        {
            applyModifier(modifier);
        }
    }

    public void applyModifier(V4Modifier modifier)
    {
        String targetState = modifier.getTargetID();

        // Ignore special DYNAMIC_BAB state
        if (!targetState.startsWith(StringConstants.DYNAMIC_BAB))
        {
            String modifierType = modifier.getV4ModifierType();
            String sourceID = modifier.getSourceID();
            Object modifierValue = modifier.getModifier();
            applyModifier(targetState, modifierType, sourceID, modifierValue);
        }
    }

    public void applyModifier(String targetState, String modifierType, String sourceID, Object modifierValue)
    {
        stateMap.get(targetState).add(modifierType, sourceID, modifierValue);
    }

    public void removeModifiers(List<V4Modifier> modifiers)
    {
        if (null == modifiers)
        {
            return;
        }

        for (V4Modifier modifier : modifiers)
        {
            removeModifier(modifier);
        }
    }

    public void removeModifier(V4Modifier modifier)
    {
        String targetState = modifier.getTargetID();

        // Ignore special DYNAMIC_BAB state
        if (!targetState.startsWith(StringConstants.DYNAMIC_BAB))
        {
            String modifierType = modifier.getV4ModifierType();
            String sourceID = modifier.getSourceID();
            removeModifier(targetState, modifierType, sourceID);
        }
    }

    public void removeModifier(String targetState, String modifierType, String sourceID)
    {
        stateMap.get(targetState).remove(modifierType, sourceID);
    }

    public void update(Observable o, Object arg)
    {
        if (o == raceState)
        {
            setRace((String) raceState.getTotal());
        } else if (o == characterClassState)
        {
            setCharacterClass((String) characterClassState.getTotal());
        } else if (o == paragonPathState)
        {
            setParagonPath((String) paragonPathState.getTotal());
        } else if (o == nameState)
        {
            nkp.setName((String) nameState.getTotal());
        } else if (o == levelState)
        {
            LevelChangeManager.manageLevelChange((Integer) levelState.getTotal(), this);
        } else if (o == inventoryList)
        {
            updateDynamicWeaponStates();
        }
    }

    /**
     * An item has been either added or removed from the inventory.
     * If it is a weapon then that was added/removed, adjust the dynamic states
     * accordingly.
     * <p/>
     * TODO: Optimise
     */
    private void updateDynamicWeaponStates()
    {
        updateDynamicAddedWeapons();
        updateDynamicRemovedWeapons();
    }

    private void updateDynamicRemovedWeapons()
    {
        List<V4ItemCapsule> weapons = getWeaponsFromInventoryList();

        // Compare the weapons UUID with the UUIDs in the dynamic state id list. The UUIDS
        // present in the dynamic state list but not in the inventory needs to be removed.
        List<String> toBeRemoved = new ArrayList<String>(weapons.size());
        for (String stateID : dynamicStateIDs)
        {
            boolean isPresentInInventory = false;
            for (V4ItemCapsule itemCapsule : weapons)
            {
                isPresentInInventory = isPresentInInventory || itemCapsule.getUUID().equals(stateID);
            }

            // If existing DynamicStateID is not present in inventory, i.e. the item has been
            // removed, then mark the state for removal too.
            if (!isPresentInInventory)
            {
                toBeRemoved.add(stateID);
            }
        }

        // Remove any obsolete states.
        for (String stateID : toBeRemoved)
        {
            //logger.info("Removing dynamic state: " + stateID);
            dynamicStateMap.remove(stateID);
            //stateMap.remove(stateID);
            dynamicStateIDs.remove(stateID);
        }
    }

    private void updateDynamicAddedWeapons()
    {
        List<V4ItemCapsule> weapons = getWeaponsFromInventoryList();

        // Compare the weapons UUID with the UUIDs in the dynamic state id list. The ones missing
        // needs to be added.
        List<V4ItemCapsule> toBeAdded = new ArrayList<V4ItemCapsule>(weapons.size());
        for (V4ItemCapsule weapon : weapons)
        {
            if (!dynamicStateIDs.contains(weapon.getUUID()))
            {
                toBeAdded.add(weapon);
            }
        }

        // Create a dynamic state for the given weapon.
        // Create V4State for the weapon. Create the integer state for it. Adde to state map.
        for (V4ItemCapsule weapon : toBeAdded)
        {
            V4State v4State = XMLFactory.createV4State(weapon);
            State dynamicState = new IntegerState(v4State, stateMap, true);
            //logger.info("Adding dynamic state id: " + dynamicState.getStateID());

            // Allow this weapon to get proficiency bonuses if applicable
            SpecificWeaponProficiencyBonus function = new SpecificWeaponProficiencyBonus(weapon);
            function.setOwner(dynamicState);
            String weaponProficiencies = "Consolidated Weapon prof.";
            function.addStateToObserve(stateMap.get(weaponProficiencies));

            dynamicStateMap.put(dynamicState.getStateID(), dynamicState);
            //stateMap.put(dynamicState.getStateID(), dynamicState);
            dynamicStateIDs.add(dynamicState.getStateID());

            // Add the special weapon DYNAMIC_BAB if any
            for (V4Modifier modifier : weapon.getV4Weapon().getV4ModifierList().getV4Modifier())
            {
                if (modifier.getTargetID().equals(StringConstants.DYNAMIC_BAB + weapon.getName()))
                {
                    String targetState = modifier.getTargetID();
                    String modifierType = modifier.getV4ModifierType();
                    String sourceID = modifier.getSourceID();
                    Object modifierValue = modifier.getModifier();
                    dynamicStateMap.get(targetState).add(modifierType, sourceID, modifierValue);
                }
            }
        }
    }

    public List<V4ItemCapsule> getWeaponsFromInventoryList()
    {
        List<V4ItemCapsule> weapons = new ArrayList<V4ItemCapsule>(20);
        for (V4ItemCapsule itemCapusle : inventoryList)
        {
            if (itemCapusle.getV4Weapon() != null)
            {
                weapons.add(itemCapusle);
            }
        }
        return weapons;
    }

    private void setCharacterClass(String characterClassID)
    {
        if (characterClass != null)
        {
            removeModifiers(characterClass.getV4ModifierList().getV4Modifier());
        }

        characterClass = XMLClassKit.instance().getCharacterClass(characterClassID);
        applyModifiers(characterClass.getV4ModifierList().getV4Modifier());
    }

    private void setParagonPath(String paragonPathID)
    {
        if (paragonPath != null)
        {
            removeModifiers(paragonPath.getV4ModifierList().getV4Modifier());
        }

        paragonPath = XMLClassKit.instance().getCharacterClass(paragonPathID);
        applyModifiers(paragonPath.getV4ModifierList().getV4Modifier());
    }

    public EquipmentSlotManager getEquipmentSlotManager()
    {
        return equipmentSlotManager;
    }

    public ObservableArrayList<V4ItemCapsule> getInventoryList()
    {
        return inventoryList;
    }

    public void remove(V4ItemCapsule item)
    {
        getInventoryList().remove(item);
        unequip(item);
    }

    public void unequip(V4ItemCapsule item)
    {
        List<Slot> slots = getEquipmentSlotManager().isEquipped(item);
        for (Slot slot : slots)
        {
            slot.unequip();
        }
    }

    public V4EntityNameKeyPair getNameKeyPair()
    {
        return nkp;
    }

    public Color getColor()
    {
        return color;
    }

    public void setColor(Color color)
    {
        this.color = color;
        setChanged();
        notifyObservers(color);
    }

    public V4Race getRace()
    {
        return race;
    }

    public Map<String, State> getDynamicStateMap()
    {
        return dynamicStateMap;
    }

    public ObservableArrayList<String> getDynamicStateIDs()
    {
        return dynamicStateIDs;
    }
}
