package rpg.v4.server.proxy;

import rpg.v4.middleware.jaxb.*;
import rpg.v4.middleware.proxy.Proxy;
import rpg.v4.middleware.util.collection.ObservableArrayList;
import rpg.v4.middleware.util.deepcopy.FastDeepCopy;
import rpg.v4.middleware.util.xml.*;
import rpg.v4.server.battle.InitiativeOrderManager;
import rpg.v4.server.entity.Entity;
import rpg.v4.server.vaults.EntityCollectionManager;

import java.util.List;

/**
 * Communicates with the client side inProcessProxy directly
 */
public class InProcessProxy implements Proxy
{
    public List<V4EntityNameKeyPair> getEntityNameKeyPairs()
    {
        return EntityCollectionManager.getEntityNameKeyPairs();
    }

    public Entity newEntity()
    {
        return EntityCollectionManager.newInstance();
    }

    public V4Group newV4Group()
    {
        return XMLFactory.createV4Group();
    }

    public Entity loadEntity(V4EntityNameKeyPair nkp)
    {
        return EntityCollectionManager.get(nkp);
    }

    public ObservableArrayList<String> getGroupNames()
    {
        return XMLGroupKit.instance().getGroupNames();
    }

    public void addToGroup(String entityID, String groupID)
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void deleteFromGroup(String entityID, String groupID)
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public V4Modifier createModifier(boolean includeDice)
    {
        return XMLFactory.createModifier(includeDice);
    }

    public V4Race createRace()
    {
        return XMLFactory.createRace();
    }

    public List<String> getAvailableStates()
    {
        return XMLStateSkeletonKit.getAvailableStates();
    }

    public List<String> getAvailableTargetableStates()
    {
        return XMLStateSkeletonKit.getAvailableTargetableStates();
    }

    public List<String> getModifierTypes()
    {
        return XMLModifierTypeKit.instance().getTypes();
    }

    public void saveRace(V4Race race)
    {
        XMLRaceKit.instance().save(race);
    }

    public void saveClass(V4Class characterClass)
    {
        XMLClassKit.instance().save(characterClass);
    }

    public void saveWeapon(V4Weapon weapon)
    {
        XMLWeaponKit.instance().save(weapon);
    }

    public void saveItem(V4Item v4armor)
    {
        XMLItemKit.instance().save(v4armor);
    }

    public void saveEntity(Entity entity)
    {
        XMLEntityKit.instance().save(entity);
    }

    public void saveGroup(V4Group v4Group)
    {
        XMLGroupKit.instance().save(v4Group);
    }

    public void saveGenericAction(V4GenericAction v4GenericAction)
    {
        XMLActionKit.instance().save(v4GenericAction);
    }

    public ObservableArrayList<String> getAvailableRaces()
    {
        return XMLRaceKit.instance().getAvailableRaces();
    }

    public V4Race getRace(String name)
    {
        V4Race race = XMLRaceKit.instance().getRace(name);
        return (V4Race) FastDeepCopy.deepCopy(race);
    }

    public V4Class getCharacterClass(String name)
    {
        V4Class charClass = XMLClassKit.instance().getCharacterClass(name);
        return (V4Class) FastDeepCopy.deepCopy(charClass);
    }

    public V4Weapon getWeapon(String name)
    {
        V4Weapon weapon = XMLWeaponKit.instance().getWeapon(name);
        return (V4Weapon) FastDeepCopy.deepCopy(weapon);
    }

    /**
     * Return a copy of the item. Don't want to return a object as it changes
     * on the server then.
     * @param name
     * @return
     */
    public V4Item getArmor(String name)
    {
        V4Item item = XMLItemKit.instance().getItem(name);
        return (V4Item) FastDeepCopy.deepCopy(item);
    }

    public V4ItemCapsule getWeaponItemCapsule(String name)
    {
        return XMLWeaponKit.instance().getWeaponItemCapsule(name);
    }

    public V4ItemCapsule getArmorItemCapsule(String name)
    {
        return XMLItemKit.instance().getItemCapsule(name);
    }

    public V4GenericAction getGenericAction(String name)
    {
        return XMLActionKit.instance().getGenericAction(name);
    }

    public ObservableArrayList<String> getAvailableParagonPaths()
    {
        return XMLClassKit.instance().getAvailableParagonPaths();
    }

    public ObservableArrayList<String> getAllAvailableClasses()
    {
        return XMLClassKit.instance().getAllAvailableClasses();
    }

    public V4Class createClass()
    {
        return XMLFactory.createClass();
    }

    public V4Entity createEntity()
    {
        return XMLFactory.createEntity();
    }

    public V4GenericAction createGenericAction()
    {
        return XMLFactory.createGenericAction();
    }

    public ObservableArrayList<String> getAvailableClasses()
    {
        return XMLClassKit.instance().getAvailableClasses();
    }

    public ObservableArrayList<String> getAvailableWeapons()
    {
        return XMLWeaponKit.instance().getAvailableWeapons();
    }

    public ObservableArrayList<String> getAvailableItems()
    {
        return XMLItemKit.instance().getAvailableItems();
    }

    public ObservableArrayList<V4EntityNameKeyPair> getAvailableEntities()
    {
        return XMLEntityKit.instance().getAvailableEntities();
    }

    public ObservableArrayList<String> getAvailableFeats()
    {
        return XMLActionKit.instance().getAvailableFeats();
    }

    public ObservableArrayList<String> getAvailableActions()
    {
        return XMLActionKit.instance().getAvailableActions();
    }

    public ObservableArrayList<String> getAvailablePowers()
    {
        return XMLActionKit.instance().getAvailablePowers();
    }

    public ObservableArrayList<V4EntityNameKeyPair> getEntityInitiativeOrderList()
    {
        return InitiativeOrderManager.getInstance().getEntityInitiativeOrderList();
    }

    public V4Weapon createWeapon()
    {
        return XMLFactory.createWeapon();
    }

    public V4Item createItem()
    {
        return XMLFactory.createItem();
    }
}
