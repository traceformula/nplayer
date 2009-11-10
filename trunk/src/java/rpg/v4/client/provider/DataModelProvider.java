package rpg.v4.client.provider;

/**
 * To change this template use File | Settings | File Templates.
 */
public interface DataModelProvider<E>
{
    public void loadNewInstance();

    public void loadCollectionSummary();

    public void loadInstance(E toLoad);
}
