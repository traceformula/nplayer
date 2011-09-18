package rpg.v4.client.gui.forge.genericactions.impl.prerequisite;

import rpg.swingx.JRoundedButton;
import rpg.swingx.JTransparentPanel;
import rpg.v4.client.gui.edit.renderer.impl.StringStateGroup;
import rpg.v4.client.gui.util.factories.LabelFactory;
import rpg.v4.client.gui.util.picker.impl.StringPicker;
import rpg.v4.client.proxy.ClientProxyKit;
import rpg.v4.middleware.jaxb.V4Prerequisite;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: fjelds
 * Date: 18/09/2011
 * Time: 10:40
 * Shows a single prerequisite, allowing the user to change and modify it.
 */
public class PrerequisitePanel extends JTransparentPanel implements MouseListener, PropertyChangeListener
{
    private V4Prerequisite v4Prerequisite;
    private PrerequisiteForgePanel prerequisiteForgePanel;
    private StringPicker statePicker, comparisonTypePicker, valuePicker;

    public PrerequisitePanel(V4Prerequisite v4Prerequisite, PrerequisiteForgePanel prerequisiteForgePanel)
    {
        this.v4Prerequisite = v4Prerequisite;
        this.prerequisiteForgePanel = prerequisiteForgePanel;

        List<String> availableStates = ClientProxyKit.CLIENT_PROXY.getAvailableStates();
        statePicker = new StringPicker(availableStates);
        statePicker.setText(v4Prerequisite.getV4StateID());

        comparisonTypePicker = new StringPicker("is", "is at least or greater", "contains");
        comparisonTypePicker.setText(v4Prerequisite.getComparisonType());

        valuePicker = new StringPicker();
        updateValues();
        valuePicker.setText(v4Prerequisite.getValue());

        JButton removeLabel = new JRoundedButton("-");
        removeLabel.setToolTipText("Click to remove");
        removeLabel.addMouseListener(this);

        Box box = Box.createHorizontalBox();
        box.add(removeLabel);
        box.add(Box.createRigidArea(new Dimension(10, 1)));
        box.add(statePicker);
        box.add(LabelFactory.createHeaderLabel(" "));
        box.add(comparisonTypePicker);
        box.add(LabelFactory.createHeaderLabel(" "));
        box.add(valuePicker);

        add(box, BorderLayout.NORTH);


        valuePicker.addPropertyChangeListener("text", this);
        comparisonTypePicker.addPropertyChangeListener("text", this);
        statePicker.addPropertyChangeListener("text", this);
        setBorder(BorderFactory.createEmptyBorder(6,0,0,0));
    }

    private void updateValues()
    {
        valuePicker.setDataRange(StringStateGroup.getList(v4Prerequisite.getV4StateID()));
    }

    public void propertyChange(PropertyChangeEvent evt)
    {
        Object source = evt.getSource();
        JLabel label = (JLabel) evt.getSource();
        String text = label.getText();

        if (source == valuePicker)
        {
            v4Prerequisite.setValue(text);
        } else if (source == comparisonTypePicker)
        {
            v4Prerequisite.setComparisonType(text);
        } else if (source == statePicker)
        {
            v4Prerequisite.setV4StateID(text);
            updateValues();
        }
    }
        

    public V4Prerequisite getV4Prerequisite()
    {
        return v4Prerequisite;
    }

    public void mouseClicked(MouseEvent mouseEvent)
    {
        prerequisiteForgePanel.remove(this);
    }

    public void mousePressed(MouseEvent mouseEvent)
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void mouseReleased(MouseEvent mouseEvent)
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void mouseEntered(MouseEvent mouseEvent)
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void mouseExited(MouseEvent mouseEvent)
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
