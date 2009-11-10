package rpg.v4.client.gui.util.picker.listener;

import rpg.v4.client.gui.util.picker.Picker;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by IntelliJ IDEA.
 * User: Sven-Ivar Fjeld
 * Date: 18.sep.2005
 * Time: 14:43:49
 */
public class PickerListener implements ActionListener
{

    private Picker picker;

    public PickerListener(Picker picker)
    {
        this.picker = picker;
    }

    public void actionPerformed(ActionEvent e)
    {

        if (e.getSource() instanceof JMenuItem)
        {

            JMenuItem menuItem = (JMenuItem) e.getSource();

            /*
            * Does not set a value directly in an action as a picker can be a picker for different things.
            * This decouples the action and the picker allowing only one pickerlistener class for all
            * pickers. This makes sense as there must be a specific picker class for every picker type.
            */
            picker.setPickedValue(menuItem.getText());

        }
    }

}
