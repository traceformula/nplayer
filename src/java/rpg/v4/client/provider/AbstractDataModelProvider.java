package rpg.v4.client.provider;

import java.util.Observable;

/**
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractDataModelProvider<E> extends Observable
        implements DataModelProvider<E>
{
    protected E activeObject;

    public E getActive()
    {
        return activeObject;
    }

}
