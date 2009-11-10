package rpg.v4.client.gui.util.picker;

import rpg.v4.server.state.State;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Sven-Ivar Fjeld
 * Date: 18.sep.2005
 * Time: 14:17:55
 * <p/>
 * A picker is an object that allows a user to pick a value in a set of possible choices. This
 * can be numbers, colors, entities, etc. It then notifies the registered rpg.server.action.Action
 * what happened and with the new value.
 */
public interface Picker
{

    public void setUpperBound(Object obj);

    public void setLowerBound(Object obj);

    public void setDataRange(List choiceRange);

    public void setState(State state);

    public boolean isDataRangeOfValidObjects(List dataRange);

    /**
     * This sets the value of the picker, displayed to the user but does not propagate it to the action
     * <p/>
     * Could have casted a picker to a JLabel but then we could never create a picker that extends a
     * different class.
     *
     * @param obj
     */
    public void setPickedValueQuietly(Object obj);

    /**
     * When this is called then the picker sets its own picked value to something AND notifies its
     * encapsulated action of what has changed. When the action updates it sends an update to its
     * observers, among them an ActionPanel. The ActionPanel with tell the picker to update itself,
     * and need to do this as the picker could potentially be changed by other factors. When this
     * happens, the picker value is set quietly instead.
     *
     * @param obj
     */
    public void setPickedValue(Object obj);

    public void setEditable(boolean editable);

    public boolean isEditable();
}
