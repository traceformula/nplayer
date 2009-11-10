package rpg.v4.server.state.impl;

import rpg.v4.server.state.SubState;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * A sub state which sums all the modifiers added to it together to the total it represents.
 */
public class IntegerStackableSubState implements SubState, Serializable
{
    private IntegerState owner;
    private String modifierType;
    private Map<Object, Integer> availableModifiers;
    private int activeModifier;

    public IntegerStackableSubState(IntegerState owner, String modifierType)
    {
        this.owner = owner;
        this.modifierType = modifierType;
        availableModifiers = new HashMap<Object, Integer>();
        activeModifier = 0;
    }

    public String getModifierType()
    {
        return modifierType;
    }

    public Object getActiveModifier()
    {
        return activeModifier;
    }

    /**
     * Add a modifier to this substate from give source. If the source already exists then the old
     * modifier is replaced.
     *
     * @param source The applicator
     * @param value  The modifier and must be an Integer
     * @return true if it was added.
     */
    public void addModifier(Object source, Object value)
    {
        int oldValue = activeModifier;
        Integer newValue = (Integer) value;

        if (availableModifiers.containsKey(source))
        {
            activeModifier -= availableModifiers.remove(source);
        }

        availableModifiers.put(source, newValue);
        activeModifier += newValue;

        owner.changeModifierSilently(oldValue, activeModifier);
    }

    /**
     * Removes the modifer for this source.
     *
     * @param source The applicator
     * @return The modifier value
     */
    public Object removeModifier(Object source)
    {
        if (!availableModifiers.containsKey(source))
        {
            return null;
        }

        int oldValue = activeModifier;
        Integer modifier = availableModifiers.remove(source);
        activeModifier -= modifier;
        owner.changeModifierSilently(oldValue, activeModifier);

        return modifier;
    }

    @Override
    public String toString()
    {
        String returnValue = activeModifier + " ( ";

        for (Object key : availableModifiers.keySet())
        {
            returnValue += availableModifiers.get(key) + " - " + key + ", ";
        }

        returnValue += ") ";

        return returnValue;
    }
}
