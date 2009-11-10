package rpg.v4.client.provider.impl;

import rpg.v4.client.provider.AbstractDataModelProvider;
import rpg.v4.client.proxy.ClientProxyKit;
import rpg.v4.server.group.Group;

import java.util.List;

/**
 * Provides a client access point to server side {@link rpg.v4.middleware.jaxb.V4Group} collections, changing the
 * active group when needed as well as firing off events when the active group
 * changes so it's observers change change accordingly
 */
public class GroupProvider<E> extends AbstractDataModelProvider<E>
{
    public static final GroupProvider<Group> provider = new GroupProvider<Group>();

    private GroupProvider()
    {
    }

    public void loadNewInstance()
    {
        E toLoad = (E) new Group(ClientProxyKit.CLIENT_PROXY.newV4Group());
        loadInstance(toLoad);
    }

    public void loadInstance(E groupToLoad)
    {
        activeObject = groupToLoad;
        setChanged();
        notifyObservers(activeObject);
    }

    public void loadCollectionSummary()
    {
        List<String> collectionSummary = ClientProxyKit.CLIENT_PROXY.getGroupNames();
        setChanged();
        notifyObservers(collectionSummary);
    }
}
