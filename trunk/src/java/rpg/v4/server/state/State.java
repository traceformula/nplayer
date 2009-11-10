package rpg.v4.server.state;

import rpg.v4.middleware.jaxb.V4State;

import java.util.Observer;
import java.util.Set;

/**
 * Maintains a set of modifiers or string based entity states from a variety of sources.
 * <p/>
 * A state should be Observable so that they can be added later. When a change is comitted in
 * a staterecord, it recalculates and notifies the observers.
 */
public interface State
{
    public String getStateID();

    public String getType();

    public String getSubType();

    public String getCategory();

    public Object getTotal();

    public boolean isUserEntry();

    public boolean isHidden();
    
    public Object getSubTotal(String modifierType);

    public Set<String> getSupportedModifierTypes();

    public Object remove(String modifierType, Object source);

    public void add(String modifierType, Object source, Object value);

    public void addObserver(Observer observer);

    public void deleteObserver(Observer o);

    public String toString();

    public V4State toV4State();
}
