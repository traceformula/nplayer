package rpg.v4.client.gui.edit.renderer;

import rpg.swingx.JTransparentPanel;
import rpg.v4.server.state.State;

import java.util.Observable;
import java.util.Observer;

/**
 * A content block representing a state
 */
public abstract class ContentBlock extends JTransparentPanel implements Observer
{
    protected State activeState;

    public ContentBlock(State state)
    {
        activeState = state;
    }

    public void update(Observable o, Object arg)
    {
        updateState((State) o);
    }

    public void updateState(State state)
    {
        activeState.deleteObserver(this);
        activeState = state;
        activeState.addObserver(this);
        //updateStateValue(state);
    }
}
