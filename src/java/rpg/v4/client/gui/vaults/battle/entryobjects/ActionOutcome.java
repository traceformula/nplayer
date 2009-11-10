package rpg.v4.client.gui.vaults.battle.entryobjects;

import rpg.v4.client.gui.util.factories.LabelFactory;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Created by IntelliJ IDEA.
 * User: fjelds
 * Date: Nov 5, 2009
 * Time: 3:44:42 PM
 *
 * Decides wether or not something is a success or not, depending on two number
 */
public class ActionOutcome implements PropertyChangeListener, GenericEntry
{
    public static final String SUCCESS = "Success!", FAILURE = "Failure";
    
    private JLabel outcome = LabelFactory.createHeaderLabelNonPadded("");
    private JLabel attackerLabelTotal, targetLabelTotal;

    public ActionOutcome(GenericEntry totalEntry, GenericEntry dsf)
    {
        attackerLabelTotal = (JLabel) totalEntry.getJComponent();
        targetLabelTotal = (JLabel) dsf.getJComponent();

        attackerLabelTotal.addPropertyChangeListener("text", this);
        targetLabelTotal.addPropertyChangeListener("text", this);
        propertyChange(null);
    }

    public JComponent getJComponent()
    {
        return outcome;
    }

    public void propertyChange(PropertyChangeEvent propertyChangeEvent)
    {
        Integer attackTotal = Integer.parseInt(attackerLabelTotal.getText());
        Integer defensiveTotal = Integer.parseInt(targetLabelTotal.getText());

        boolean isSuccess = attackTotal >= defensiveTotal;
        outcome.setText(isSuccess ? SUCCESS : FAILURE);
    }

    public void disable()
    {
        attackerLabelTotal.removePropertyChangeListener(this);
        targetLabelTotal.removePropertyChangeListener(this);
    }
}
