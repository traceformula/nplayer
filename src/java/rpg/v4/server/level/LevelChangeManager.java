package rpg.v4.server.level;

import rpg.v4.server.entity.Entity;

/**
 * Applies and removes various Gains from an entity.
 */
public class LevelChangeManager
{
    public static void manageLevelChange(int currentLevel, Entity entity)
    {
        for (int i = 1; i <= 30; i++)
        {
            boolean isAdding = currentLevel >= i;

            for (Gain gain : Gain.getGainsForLevel(i))
            {
                if (isAdding)
                {
                    gain.addTo(entity, i);
                } else
                {
                    gain.removeFrom(entity, i);
                }
            }
        }
    }
}
