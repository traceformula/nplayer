package rpg.v4.middleware.constants;

/**
 * Enumeration for modifier types.
 */
public enum CategoryEnum
{
    ABILITY("Ability scores"),
    DEFENSE("Defenses"),
    SKILL("Skills"),
    SENSES("Senses"),
    CHARACTER_INFORMATION("Character information"),
    OTHER("Other"),
    DYNAMIC_BABS("Weapon BABs (equip the weapon to enable magic modifiers)"),
    PROFICIENCIES("Proficiencies"),
    SAVING_THROWS("Saving throws")
    ;


    private String type;

    CategoryEnum(String type)
    {
        this.type = type;
    }

    @Override
    public String toString()
    {
        return type;
    }
}
