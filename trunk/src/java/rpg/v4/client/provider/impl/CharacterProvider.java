package rpg.v4.client.provider.impl;

import rpg.v4.client.provider.AbstractDataModelProvider;
import rpg.v4.client.proxy.ClientProxyKit;
import rpg.v4.middleware.jaxb.V4EntityNameKeyPair;
import rpg.v4.server.entity.Entity;

import java.util.List;

/**
 * Provides a client access point to server side {@link rpg.v4.middleware.jaxb.V4Entity} and {@link Entity} collections,
 * changing the active entity when needed as well as firing off events when the active entity
 * changes so it's observers change change accordingly.
 */
public class CharacterProvider<E> extends AbstractDataModelProvider<E>
{
    public static final CharacterProvider<Entity> provider = new CharacterProvider<Entity>();

    public void loadNewInstance()
    {
        loadInstance((E) ClientProxyKit.CLIENT_PROXY.newEntity());
    }

    public void loadInstance(E entityToLoad)
    {
        activeObject = entityToLoad;
        setChanged();
        notifyObservers(activeObject);
    }

    public void loadCollectionSummary()
    {
        List<V4EntityNameKeyPair> collectionSummary = ClientProxyKit.CLIENT_PROXY.getEntityNameKeyPairs();
        setChanged();
        notifyObservers(collectionSummary);
    }
}
