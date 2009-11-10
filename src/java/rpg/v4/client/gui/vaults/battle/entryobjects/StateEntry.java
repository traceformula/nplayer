package rpg.v4.client.gui.vaults.battle.entryobjects;

import rpg.v4.client.gui.util.factories.LabelFactory;
import rpg.v4.server.state.State;

import javax.swing.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by IntelliJ IDEA.
 * User: fjelds
 * Date: Nov 5, 2009
 * Time: 10:42:01 AM
 *
 * Displays the number in a state.
 */
public class StateEntry implements Observer, GenericEntry
{
    private State state;
    private JLabel stateLabel = LabelFactory.createHeaderLabelNonPadded("");

    public StateEntry(State state)
    {
        this.state = state;
        state.addObserver(this);

        stateLabel.setToolTipText(state.getStateID());
        update(null, null);
    }

    public JComponent getJComponent()
    {
        return stateLabel;
    }

    public void disable()
    {
        state.deleteObserver(this);
    }

    public void update(Observable observable, Object o)
    {

        stateLabel.setText(state.getTotal().toString());
        stateLabel.repaint();
    }
}
