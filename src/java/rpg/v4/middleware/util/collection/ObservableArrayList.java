package rpg.v4.middleware.util.collection;


import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: Sven-Ivar Fjeld
 * Date: 18.mar.2005
 * Time: 17:01:50
 * <p/>
 * A list that is observable, sending out notifications when nesseccary. Upon a notification, the encapsulated
 * list will be sent out.
 */
public class ObservableArrayList<E> extends Observable implements List<E>
{

    private ArrayList<E> list;

    public ObservableArrayList(ArrayList<E> list)
    {
        this.list = list;
    }

    public ObservableArrayList()
    {
        this.list = new ArrayList<E>();
    }

    public ObservableArrayList(int initialCapacity)
    {
        this.list = new ArrayList<E>(initialCapacity);
    }

    public boolean add(E e)
    {
        boolean b = list.add(e);
        this.setChanged();
        this.notifyObservers(this);
        return b;
    }

    public boolean remove(Object o)
    {
        boolean b = list.remove(o);
        this.setChanged();
        this.notifyObservers(this);
        return b;
    }

    public boolean containsAll(Collection<?> c)
    {
        return list.containsAll(c);
    }

    public boolean addAll(Collection<? extends E> c)
    {
        boolean changed = list.addAll(c);

        if (changed)
        {
            setChanged();
            notifyObservers();
        }

        return changed;
    }

    public boolean addAll(int index, Collection<? extends E> c)
    {
        boolean changed = list.addAll(index, c);

        if (changed)
        {
            setChanged();
            notifyObservers();
        }

        return changed;
    }

    public boolean removeAll(Collection<?> c)
    {
        boolean changed = list.removeAll(c);

        if (changed)
        {
            setChanged();
            notifyObservers();
        }

        return changed;
    }

    public boolean retainAll(Collection<?> c)
    {
        boolean changed = list.retainAll(c);

        if (changed)
        {
            setChanged();
            notifyObservers();
        }

        return changed;
    }

    public E set(int index, E e)
    {
        E prevObj = list.set(index, e);
        this.setChanged();
        this.notifyObservers(list);
        return prevObj;
    }

    public int size()
    {
        return list.size();
    }

    public boolean isEmpty()
    {
        return list.isEmpty();
    }

    public boolean contains(Object o)
    {
        return list.contains(o);
    }

    public Iterator<E> iterator()
    {
        return list.iterator();
    }

    public Object[] toArray()
    {
        return list.toArray();
    }

    public <T> T[] toArray(T[] a)
    {
        return list.toArray(a);
    }

    public void clear()
    {
        list.clear();
        this.setChanged();
        this.notifyObservers(list);
    }

    public E get(int index)
    {
        return list.get(index);
    }

    public void add(int index, E element)
    {
        list.add(index, element);

        setChanged();
        notifyObservers();
    }

    public E remove(int index)
    {
        E removedObject = list.remove(index);

        if (null != removedObject)
        {
            setChanged();
            notifyObservers();
        }

        return removedObject;
    }

    public int indexOf(Object o)
    {
        return list.indexOf(o);
    }

    public int lastIndexOf(Object o)
    {
        return list.lastIndexOf(o);
    }

    public ListIterator<E> listIterator()
    {
        return list.listIterator();
    }

    public ListIterator<E> listIterator(int index)
    {
        return list.listIterator(index);
    }

    public List<E> subList(int fromIndex, int toIndex)
    {
        return list.subList(fromIndex, toIndex);
    }
}
