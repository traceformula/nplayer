package rpg.v4.middleware.constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: fjelds
 * Date: 17/09/2011
 * Time: 11:14
 * <p/>
 * Various constants used by pickers
 */
public class PickerConstants
{
    public static final List<String> TARGET_OPTIONS = Arrays.asList("You", "You or one ally", "One creature",
            "One enemy", "One object", "Multiple enemies"),
            SECONDARY_ATTACK_TYPES = Arrays.asList("Weapon", "Touch", "Sight", "Burst", "Blast", "Wall", "1", "5", "10", "15", "20", "25", "30", "n/a"),
            ATTACK_TYPES = Arrays.asList("Melee", "Ranged", "Melee or Ranged", "Close", "Area", "Personal", "n/a"),
            ACTION_TYPES = Arrays.asList("Standard", "Move", "Immediate interrupt",
            "Immediate interrupt", "Immediate reaction", "Minor", "Free", "No action"),
            EFFECT_TYPES = Arrays.asList("Charm", "Conjuration", "Fear", "Healing", "Illusion",
                "Poison", "Polymorph", "Reliable", "Sleep", "Stance", "Teleportation", "Zone", "n/a"),
            DAMAGE_TYPES = Arrays.asList("Acid", "Cold", "Fire", "Force", "Lightning",
                "Necrotic", "Psion", "Psychic", "Radiant", "Thunder", "n/a"),
            SOURCES = Arrays.asList("Arcane", "Divine", "Martial", "Nature", "Ki", "Psionic",
                "Elemental", "Primal", "Shadow", "n/a"),
            SUB_TYPES = Arrays.asList("At will", "Encounter", "Daily", "Utility", "n/a");
}
