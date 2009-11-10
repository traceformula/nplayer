package rpg.v4.server.state;

/**
 * Contains and maintains states for one of the legal modifier types (see XML file).
 * <p/>
 * Because a modifier can be stackable (e.g. penalties) and non-stacable (e.g. armor bonus) the
 * concrete classes need to manage the correct contract for the modifier type.
 * <p/>
 * Substates can also be numerical (e.g. an ability) or string based (e.g. conditions such as
 * blinded and dazzled), the concrete classes will need to manage that too.
 * <p/>
 * Substates also provide a proper tostring method which is intended to be used for pop up
 * helper text.
 */
public interface SubState
{
    public String getModifierType();

    public Object getActiveModifier();

    public void addModifier(Object source, Object value);

    public Object removeModifier(Object source);

    public String toString();
}
