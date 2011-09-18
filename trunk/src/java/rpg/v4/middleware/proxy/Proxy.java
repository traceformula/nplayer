package rpg.v4.middleware.proxy;

import rpg.v4.middleware.jaxb.*;
import rpg.v4.middleware.util.collection.ObservableArrayList;
import rpg.v4.server.entity.Entity;

import java.util.List;

/**
 * A simple proxy interface
 */
public interface Proxy
{
    public List<V4EntityNameKeyPair> getEntityNameKeyPairs();

    public Entity newEntity();

    public V4Group newV4Group();

    public Entity loadEntity(V4EntityNameKeyPair nkp);

    public ObservableArrayList<String> getGroupNames();

    public void addToGroup(String entityID, String groupID);

    public void deleteFromGroup(String entityID, String groupID);

    public V4Modifier createModifier(boolean includeDice);

    public V4Race createRace();

    public V4Weapon createWeapon();

    public V4Item createItem();

    public V4Class createClass();

    public V4Entity createEntity();

    public V4GenericAction createGenericAction();

    public void saveRace(V4Race race);

    public void saveClass(V4Class characterClass);

    public void saveWeapon(V4Weapon weapon);

    public void saveItem(V4Item v4Item);

    public void saveEntity(Entity entity);

    public void saveGroup(V4Group v4Group);

    public void saveGenericAction(V4GenericAction v4GenericAction);

    public List<String> getAvailableStates();

    public List<String> getAvailableTargetableStates();

    public List<String> getModifierTypes();

    public ObservableArrayList<String> getAvailableRaces();

    public ObservableArrayList<String> getAllAvailableClasses();

    public ObservableArrayList<String> getAvailableClasses();

    public ObservableArrayList<String> getAvailableParagonPaths();

    public ObservableArrayList<String> getAvailableWeapons();

    public ObservableArrayList<String> getAvailableItems();

    public ObservableArrayList<V4EntityNameKeyPair> getAvailableEntities();

    public ObservableArrayList<String> getAvailableFeats();

    public ObservableArrayList<String> getAvailableActions();

    public ObservableArrayList<String> getAvailablePowers();

    public ObservableArrayList<V4EntityNameKeyPair> getEntityInitiativeOrderList();

    public V4Race getRace(String name);

    public V4Class getCharacterClass(String name);

    public V4Weapon getWeapon(String name);

    public V4Item getArmor(String name);

    public V4ItemCapsule getWeaponItemCapsule(String name);

    public V4ItemCapsule getArmorItemCapsule(String name);

    public V4GenericAction getGenericAction(String name);

    public V4Prerequisite createPrerequisite();
}
