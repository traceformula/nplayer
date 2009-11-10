package rpg.v4.middleware.constants;

/**
 * Created by IntelliJ IDEA.
 * User: fjelds
 * Date: Oct 25, 2009
 * Time: 8:19:07 AM
 *
 * A list of general state ids
 */
public enum StateIDs
{
    THIS_WEAPON_BAB("This Weapon BAB")
    ;


    private String type;

    StateIDs(String type)
    {
        this.type = type;
    }

    @Override
    public String toString()
    {
        return type;
    }
}
