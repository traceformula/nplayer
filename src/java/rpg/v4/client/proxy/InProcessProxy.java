package rpg.v4.client.proxy;

import rpg.v4.middleware.jaxb.*;
import rpg.v4.middleware.proxy.Proxy;
import rpg.v4.middleware.util.collection.ObservableArrayList;
import rpg.v4.server.entity.Entity;

import java.util.List;

/**
 * Contacts the server side inProcessProxy directly.
 */
public class InProcessProxy implements Proxy
{
    private Proxy serverProxy;

    public InProcessProxy(Proxy serverProxy)
    {
        this.serverProxy = serverProxy;
    }

    public List<V4EntityNameKeyPair> getEntityNameKeyPairs()
    {
        return serverProxy.getEntityNameKeyPairs();
    }

    public Entity newEntity()
    {
        return serverProxy.newEntity();
    }

    public V4Group newV4Group()
    {
        return serverProxy.newV4Group();
    }

    public Entity loadEntity(V4EntityNameKeyPair nkp)
    {
        return serverProxy.loadEntity(nkp);
    }

    public ObservableArrayList<String> getGroupNames()
    {
        return serverProxy.getGroupNames();
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
        return serverProxy.createModifier(includeDice);
    }

    public V4Race createRace()
    {
        return serverProxy.createRace();
    }

    public List<String> getAvailableStates()
    {
        return serverProxy.getAvailableStates();
    }

    public List<String> getAvailableTargetableStates()
    {
        return serverProxy.getAvailableTargetableStates();
    }

    public List<String> getModifierTypes()
    {
        return serverProxy.getModifierTypes();
    }

    public void saveRace(V4Race race)
    {
        serverProxy.saveRace(race);
    }

    public void saveClass(V4Class characterClass)
    {
        serverProxy.saveClass(characterClass);
    }

    public void saveWeapon(V4Weapon weapon)
    {
        serverProxy.saveWeapon(weapon);
    }

    public void saveItem(V4Item v4armor)
    {
        serverProxy.saveItem(v4armor);
    }

    public void saveEntity(Entity entity)
    {
        serverProxy.saveEntity(entity);
    }

    public void saveGroup(V4Group v4Group)
    {
        serverProxy.saveGroup(v4Group);
    }

    public void saveGenericAction(V4GenericAction v4GenericAction)
    {
        serverProxy.saveGenericAction(v4GenericAction);
    }

    public ObservableArrayList<String> getAvailableRaces()
    {
        return serverProxy.getAvailableRaces();
    }

    public V4Race getRace(String name)
    {
        return serverProxy.getRace(name);
    }

    public V4Class getCharacterClass(String name)
    {
        return serverProxy.getCharacterClass(name);
    }

    public V4Weapon getWeapon(String name)
    {
        return serverProxy.getWeapon(name);
    }

    public V4Item getArmor(String name)
    {
        return serverProxy.getArmor(name);
    }

    public V4ItemCapsule getWeaponItemCapsule(String name)
    {
        return serverProxy.getWeaponItemCapsule(name);        
    }

    public V4ItemCapsule getArmorItemCapsule(String name)
    {
        return serverProxy.getArmorItemCapsule(name);        
    }

    public V4GenericAction getGenericAction(String name)
    {
        return serverProxy.getGenericAction(name);
    }

    public ObservableArrayList<String> getAvailableParagonPaths()
    {
        return serverProxy.getAvailableParagonPaths();
    }

    public ObservableArrayList<String> getAllAvailableClasses()
    {
        return serverProxy.getAllAvailableClasses();
    }

    public V4Class createClass()
    {
        return serverProxy.createClass();
    }

    public V4Entity createEntity()
    {
        return serverProxy.createEntity();
    }

    public V4GenericAction createGenericAction()
    {
        return serverProxy.createGenericAction();
    }

    public ObservableArrayList<String> getAvailableClasses()
    {
        return serverProxy.getAvailableClasses();
    }

    public ObservableArrayList<String> getAvailableWeapons()
    {
        return serverProxy.getAvailableWeapons();
    }

    public ObservableArrayList<String> getAvailableItems()
    {
        return serverProxy.getAvailableItems();
    }

    public ObservableArrayList<V4EntityNameKeyPair> getAvailableEntities()
    {
        return serverProxy.getAvailableEntities();
    }

    public ObservableArrayList<String> getAvailableFeats()
    {
        return serverProxy.getAvailableFeats();
    }

    public ObservableArrayList<String> getAvailableActions()
    {
        return serverProxy.getAvailableActions();
    }

    public ObservableArrayList<String> getAvailablePowers()
    {
        return serverProxy.getAvailablePowers();
    }

    public ObservableArrayList<V4EntityNameKeyPair> getEntityInitiativeOrderList()
    {
        return serverProxy.getEntityInitiativeOrderList();
    }

    public V4Weapon createWeapon()
    {
        return serverProxy.createWeapon();
    }

    public V4Item createItem()
    {
        return serverProxy.createItem();
    }
}
