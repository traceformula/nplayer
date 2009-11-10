package rpg.v4.client.gui.edit.renderer.impl;

import rpg.v4.client.gui.edit.renderer.ContentBlock;
import rpg.v4.client.gui.edit.renderer.StateGroup;
import rpg.v4.server.state.State;

/**
 * shows all the numerical modifier headers
 */
public class IntegerStateGroup extends StateGroup
{
    public IntegerStateGroup(State state)
    {
        super(state);
    }

    public ContentBlock createBlock(State state)
    {
        return new IntegerStateRow(state);
    }
}
