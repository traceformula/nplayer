package rpg.v4.client.gui.edit.renderer.impl;

import rpg.swingx.JTransparentPanel;
import rpg.v4.client.gui.edit.renderer.ContentBlock;
import rpg.v4.client.gui.util.factories.LabelFactory;
import rpg.v4.client.gui.util.picker.Picker;
import rpg.v4.client.gui.util.picker.impl.IntegerPicker;
import rpg.v4.server.state.State;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Displays a integer/numerical state in a row.
 */
public class IntegerStateRow extends ContentBlock
{
    private Box box;
    private Map<String, JLabel> labelList;
    private JLabel total;
    protected Picker initialPicker;

    public IntegerStateRow(State state)
    {
        super(state);
        labelList = new HashMap<String, JLabel>();

        JLabel label = LabelFactory.createHeaderLabel(state.getStateID());
        label.setBorder(BorderFactory.createEmptyBorder(0, 3, 0, 3));

        JTransparentPanel panel = new JTransparentPanel();
        box = Box.createHorizontalBox();
        panel.add(box, BorderLayout.EAST);
        panel.add(label, BorderLayout.WEST);
        add(panel, BorderLayout.SOUTH);

        initColumns(state);
        updateState(state);
    }

    private void initColumns(State state)
    {
        total = LabelFactory.createNumericalModifierLabel("");
        box.add(total);

        for (String mod : state.getSupportedModifierTypes())
        {
            JLabel label;
            if (mod.equals("Initial"))
            {
                initialPicker = (IntegerPicker) LabelFactory.createNumericalPickerLabel(state);
                label = (JLabel) initialPicker;
                ((Picker) label).setLowerBound(0);
                ((Picker) label).setUpperBound(35);
            } else
            {
                label = LabelFactory.createNumericalModifierLabel("");
            }
            labelList.put(mod, label);
            box.add(label);
        }
    }

    public void updateState(State state)
    {
        super.updateState(state);
        initialPicker.setState(state);

        total.setText(state.getTotal().toString());
        total.setToolTipText(state.toString());
        total.repaint();

        for (String key : labelList.keySet())
        {
            Object subTotal = state.getSubTotal(key);
            String text = subTotal != null ? subTotal.toString() : "";
            JLabel label = labelList.get(key);
            if (!text.equals(label.getText()))
            {
                label.setText(text);
                label.repaint();
            }
        }
    }
}
