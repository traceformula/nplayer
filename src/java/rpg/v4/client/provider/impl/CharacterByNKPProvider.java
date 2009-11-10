package rpg.v4.client.provider.impl;

import rpg.v4.client.provider.AbstractDataModelProvider;
import rpg.v4.client.proxy.ClientProxyKit;
import rpg.v4.middleware.jaxb.V4EntityNameKeyPair;
import rpg.v4.server.entity.Entity;

/**
 * Load an entity based on the name-key-pair object.
 */
public class CharacterByNKPProvider<E> extends AbstractDataModelProvider<E>
{
    public static final CharacterByNKPProvider<V4EntityNameKeyPair> provider = new CharacterByNKPProvider<V4EntityNameKeyPair>();
    public void loadNewInstance()
    {
        CharacterProvider.provider.loadNewInstance();
    }

    public void loadCollectionSummary()
    {
        CharacterProvider.provider.loadCollectionSummary();
    }

    public void loadInstance(E nameKeyPair)
    {
        Entity entityToLoad = ClientProxyKit.CLIENT_PROXY.loadEntity((V4EntityNameKeyPair) nameKeyPair);
        CharacterProvider.provider.loadInstance(entityToLoad);
    }
}
