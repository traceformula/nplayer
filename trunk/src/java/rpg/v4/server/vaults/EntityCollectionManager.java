package rpg.v4.server.vaults;

import rpg.v4.middleware.jaxb.V4Entity;
import rpg.v4.middleware.jaxb.V4EntityNameKeyPair;
import rpg.v4.middleware.util.xml.XMLEntityKit;
import rpg.v4.server.entity.Entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Stores all entities.
 */
public class EntityCollectionManager
{
    private static Map<V4EntityNameKeyPair, Entity> entityVault = new HashMap<V4EntityNameKeyPair, Entity>();

    static {
        // Add in available saved entities which can be loaded on demand.
        for(V4EntityNameKeyPair nkp : XMLEntityKit.instance().getAvailableEntities())
        {
            entityVault.put(nkp, null);
        }
    }

    public static boolean add(Entity entity)
    {
        // No need to add/overwrite the entity again.
        if (entityVault.containsKey(entity.getNameKeyPair()))
        {
            return false;
        }

        entityVault.put(entity.getNameKeyPair(), entity);
        return entityVault.containsKey(entity.getNameKeyPair());
    }

    public static Entity newInstance()
    {
        Entity entity = new Entity();
        add(entity);
        return entity;
    }

    public static List<V4EntityNameKeyPair> getEntityNameKeyPairs()
    {
        List<V4EntityNameKeyPair> summary = new ArrayList<V4EntityNameKeyPair>(entityVault.size());

        for (V4EntityNameKeyPair nkp : entityVault.keySet())
        {
            summary.add(nkp);
        }

        return summary;
    }

    public static int getSize()
    {
        return entityVault.size();
    }

    public static void save()
    {

    }

    public static void save(String id)
    {
        throw new UnsupportedOperationException("Not supported - can only save xml");
    }

    public static Entity get(V4EntityNameKeyPair nkp)
    {
        if (entityVault.containsKey(nkp))
        {
            Entity entity = entityVault.get(nkp);

            if (null != entity)
                return entity; 
        }

        // Try by unique id
        // TOOD: Temporary fix to isActive problem for NKP when stored in JSideLabels. Perhaps remove isActive.
        // Need this though as name can change.
        for(V4EntityNameKeyPair stordeNkp : entityVault.keySet())
        {
            if (stordeNkp.getKey().equals(nkp.getKey()))
            {
                Entity entity = entityVault.get(stordeNkp);
                if (null != entity)
                    return entity;
            }
        }

        return loadEntity(nkp);
    }

    public static Entity loadEntity(V4EntityNameKeyPair nkp)
    {
        Entity entity = entityVault.get(nkp);

        // If the entity isn't loaded yet, load it now.
        if (null == entity)
        {
            V4Entity v4Entity = XMLEntityKit.instance().loadEntity(nkp);
            entity = new Entity(v4Entity, nkp);
            entityVault.put(nkp, entity);
        }

        return entity;
    }
}
