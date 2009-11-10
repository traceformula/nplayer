package rpg.v4.client.gui.vaults.battle.entryobjects;

import rpg.v4.client.gui.util.factories.LabelFactory;
import rpg.v4.client.gui.util.picker.impl.IntegerPicker;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Created by IntelliJ IDEA.
 * User: fjelds
 * Date: Nov 5, 2009
 * Time: 12:04:51 PM
 * Shows the total
 */
public class TotalEntry implements PropertyChangeListener, GenericEntry
{
    private JLabel babStateLabel = null, abilityLabel = null, diceRollLabel = null;
    private JLabel totalLabel = LabelFactory.createHeaderLabelNonPadded("");
    private int bab = 0, ability = 0, diceRoll = 0;

    public TotalEntry(GenericEntry babStateEntry, GenericEntry abilityEntry, IntegerPicker dicePicker)
    {
        abilityLabel = (JLabel) abilityEntry.getJComponent();
        abilityLabel.addPropertyChangeListener("text", this);
        ability = Integer.parseInt(abilityLabel.getText());

        if (babStateEntry != null)
        {
            babStateLabel = (JLabel) babStateEntry.getJComponent();
            babStateLabel.addPropertyChangeListener("text", this);
            bab = Integer.parseInt(babStateLabel.getText());
        }

        if (dicePicker != null)
        {
            diceRollLabel = dicePicker;
            diceRollLabel.addPropertyChangeListener("text", this);
            diceRoll = Integer.parseInt(diceRollLabel.getText());
        }

        updateTotal();
    }

    public void propertyChange(PropertyChangeEvent propertyChangeEvent)
    {
        if (propertyChangeEvent.getSource() == abilityLabel)
        {
            ability = Integer.parseInt(abilityLabel.getText());
        } else if (propertyChangeEvent.getSource() == babStateLabel)
        {
            bab = Integer.parseInt(babStateLabel.getText());
        } else
        {
            diceRoll = Integer.parseInt(diceRollLabel.getText());
        }

        updateTotal();
    }

    private void updateTotal()
    {
        int total = bab + ability + diceRoll;
        totalLabel.setText(total + "");
        totalLabel.repaint();
    }

    public JComponent getJComponent()
    {
        return totalLabel;
    }

    public void disable()
    {
        abilityLabel.addPropertyChangeListener("text", this);

        if (babStateLabel != null)
        {
            babStateLabel.addPropertyChangeListener("text", this);
        }

        if (diceRollLabel != null)
        {
            diceRollLabel.addPropertyChangeListener("text", this);
        }
    }
}
