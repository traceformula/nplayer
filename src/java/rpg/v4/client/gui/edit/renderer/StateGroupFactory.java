package rpg.v4.client.gui.edit.renderer;

import rpg.v4.client.gui.edit.renderer.impl.IntegerStateGroup;
import rpg.v4.client.gui.edit.renderer.impl.StringStateGroup;
import static rpg.v4.middleware.constants.CategoryEnum.CHARACTER_INFORMATION;
import rpg.v4.server.state.State;


/**
 * Grouping of states.
 */
public class StateGroupFactory
{
    public static StateGroup createGroup(State state)
    {
        if ((state.getType().equals("Integer") || state.getType().equals("IntegerAbility"))
                && !state.getCategory().equals(CHARACTER_INFORMATION.toString()))
        {
            return new IntegerStateGroup(state);
        } else
        {
            return new StringStateGroup(state);
        }
    }
}
