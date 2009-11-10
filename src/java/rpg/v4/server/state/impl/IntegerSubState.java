package rpg.v4.server.state.impl;

import rpg.v4.server.state.State;
import rpg.v4.server.state.SubState;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * A numerical substate which keeps track of all modifiers applicable to this state and which
 * keeps track of the largest value present. This is a non-stackable sub state.
 */
public class IntegerSubState implements SubState, Serializable
{
    private IntegerState owner;
    private String modifierType;
    private Map<Object, Integer> availableModifiers;
    private Object activeSource;
    private Integer activeModifier;

    public IntegerSubState(IntegerState owner, String modifierType)
    {
        this.owner = owner;
        this.modifierType = modifierType;
        availableModifiers = new HashMap<Object, Integer>();
        activeSource = null;
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
     * Add a modifier to this substate from give source. If the new modifier is greater than the currently active
     * one then change to the new one. If the source already exists then the old modifier is replaced. 
     *
     * @param source The applicator
     * @param value  The modifier and must be an Integer
     * @return true if it was added.
     */
    public void addModifier(Object source, Object value)
    {
        boolean isReplacement = availableModifiers.containsKey(source);

        Integer newValue = (Integer) value;
        availableModifiers.put(source, newValue);

        // If new value is more beneficial then change to using the new value, or
        // if the modifier is a replacement
        if (newValue > activeModifier || isReplacement)
        {
            owner.changeModifierSilently(activeModifier, newValue);
            activeModifier = newValue;
            activeSource = source;
        }
    }

    /**
     * Removes the modifer for this source. If this source is the active source then also
     * remove the modifier from the state total and, if applicable, reinstante another
     * available modifier.
     *
     * @param source The applicator
     * @return The modifier value
     */
    public Object removeModifier(Object source)
    {
        if (!availableModifiers.containsKey(source))
        {
            throw new UnsupportedOperationException("Can not remove modifier because the source is not in the list of applicators. Source: " + source);
        }

        Integer modifier = availableModifiers.remove(source);

        // If this is the active modifier, remove it and reinstate another.
        if (source.equals(activeSource))
        {
            activeSource = getLargestSource();

            // If there is no other source, i.e. active source == null, just use 0.
            activeModifier = (activeSource != null) ? activeModifier = availableModifiers.get(activeSource) : 0;
            owner.changeModifierSilently(modifier, activeModifier);
        }

        return modifier;
    }

    private Object getLargestSource()
    {
        Object source = null;
        Integer maxValue = -999;
        for (Object aSource : availableModifiers.keySet())
        {
            Integer value = availableModifiers.get(aSource);
            if (value > maxValue)
            {
                source = aSource;
            }
        }

        return source;
    }

    @Override
    public String toString()
    {
        if (null == activeSource)
        {
            return "";
        }

        String returnValue = activeModifier + " ";

        if (activeSource instanceof State)
        {
            returnValue += ((State) activeSource).getStateID();
        } else {
            returnValue += activeSource; 
        }
        return returnValue;
    }
}
