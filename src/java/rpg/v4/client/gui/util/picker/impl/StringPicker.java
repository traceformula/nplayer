package rpg.v4.client.gui.util.picker.impl;


import rpg.swingx.ColorConstants;
import rpg.v4.client.gui.util.picker.Picker;
import rpg.v4.client.gui.util.picker.listener.PickerListener;
import rpg.v4.middleware.constants.FontConstants;
import rpg.v4.middleware.util.collection.ObservableArrayList;
import rpg.v4.server.state.State;
import rpg.v4.server.state.impl.StringState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Sven-Ivar Fjeld
 * Date: 17.06.2008
 * Time: 16:28:01
 */
public class StringPicker extends JLabel implements Picker, MouseListener, Observer
{
    private StringState state;
    private JPopupMenu popUpMenu;
    private List<String> data;
    private PickerListener pickerListener;
    private boolean editable;

    public StringPicker()
    {
        data = new ArrayList<String>();
        pickerListener = new PickerListener(this);

        setOpaque(false);
        //setPreferredSize(new Dimension(20, 20));
        setHorizontalTextPosition(SwingConstants.CENTER);
        setHorizontalAlignment(SwingConstants.CENTER);
        setFont(FontConstants.HEADER_FONT_BOLD);
        setForeground(ColorConstants.TEXT_PICKER);
        addMouseListener(this);
    }

    public StringPicker(String... dataSet)
    {
        this(Arrays.asList(dataSet));
    }

    public StringPicker(List<String> dataSet)
    {
        this();
        setDataRange(dataSet);
    }

    public void setUpperBound(Object obj)
    {
        throw new UnsupportedOperationException("Unsupported method for picker: " + obj.getClass().getCanonicalName());
    }

    public void setLowerBound(Object obj)
    {
        throw new UnsupportedOperationException("Unsupported method for picker: " + obj.getClass().getCanonicalName());
    }

    public void setDataRange(List dataRange)
    {
        if (dataRange == null)
        {
            throw new IllegalArgumentException("New data range contained no information so it was not set.");
        }

        manageObservering(dataRange);

        if (clearDataSet(dataRange)) return;

        addNewDataSet(dataRange);

        createPopUpMenu();
    }

    private void manageObservering(List dataRange)
    {
        if (data instanceof ObservableArrayList)
        {
            ((ObservableArrayList) data).deleteObserver(this);
        }

        if (dataRange instanceof ObservableArrayList)
        {
            ((ObservableArrayList) dataRange).addObserver(this);
        }
    }

    private void addNewDataSet(List dataRange)
    {
        data.addAll(dataRange);

        if (data.size() > 0)
        {
            setText(data.get(0));
        }
    }

    private boolean clearDataSet(List dataRange)
    {
        if (dataRange.size() == 0)
        {
            setEnabled(false);
            setText("(no data)");
            return true;
        }

        data.clear();
        return false;
    }

    public void setState(State state)
    {
        this.state = (StringState) state;

        String stateValue = state.getTotal().toString();
        if (null != stateValue && !"".equals(stateValue))
        {
            setText(stateValue);
        } else
        {
            setPickedValue(getText());
        }
    }

    public boolean isDataRangeOfValidObjects(List dataRange)
    {

        for (Object object : dataRange)
        {
            if (!(object instanceof String))
            {
                return false;
            }
        }

        return true;
    }

    public void setPickedValueQuietly(Object obj)
    {
        if (obj != null)
        {
            String value = (String) obj;
            this.setText(value);
        }
    }

    public void setPickedValue(Object obj)
    {
        if (obj != null)
        {
            setPickedValueQuietly(obj);
            if (null != state)
            {
                // StringStates doesn't care about anything but the value.
                state.add(null, null, obj);
            }
        }
    }

    public void setEditable(boolean editable)
    {
        this.editable = editable;
        adjustPickerState();
    }

    public boolean isEditable()
    {
        this.setEnabled(editable);
        if (editable)
        {
            this.addMouseListener(this);
        } else
        {
            this.removeMouseListener(this);
        }
        return editable;
    }

    private void adjustPickerState()
    {

        popUpMenu.setEnabled(editable);

        popUpMenu.revalidate();

        popUpMenu.repaint();

    }

    private void maybeShowPopup(MouseEvent e, boolean isPopupTrigger)
    {
        if (isPopupTrigger)
        {
            popUpMenu.show(e.getComponent(),
                    e.getX(), e.getY());
        }
    }

    private void createPopUpMenu()
    {
        initiatePopUpMenu();
        // If there are more than one weapon name then create popupmenu and enable itself.
        // Else disable itself.
        if (data.size() > 1)
        {
            this.setEnabled(true);
            for (String weaponName : data)
            {
                JMenuItem menuItem = new JMenuItem(weaponName);

                if (data.size() > 0)
                {
                menuItem.setBackground(ColorConstants.BLUE_HIGHLIGHT);
                menuItem.setFont(FontConstants.HEADER_FONT_SMALL);
                menuItem.addActionListener(pickerListener);
                } else
                {
                menuItem.setBackground(ColorConstants.BLUE_HIGHLIGHT);
                    for (int j = 0; j < menuItem.getMouseListeners().length;)
                    {
                        menuItem.removeMouseListener(menuItem.getMouseListeners()[j]);
                    }
                }

                popUpMenu.add(menuItem);

            }

            adjustPickerState();
        } else
        {
            this.setEnabled(false);
        }
    }

    private void initiatePopUpMenu()
    {

        if (popUpMenu != null)
        {
            Component[] component = popUpMenu.getComponents();
            for (Component aComponent : component)
            {
                if (aComponent instanceof JMenuItem)
                {
                    ((JMenuItem) aComponent).removeActionListener(pickerListener);
                }
            }
            popUpMenu.removeAll();

            this.remove(popUpMenu);
        }

        popUpMenu = new JPopupMenu();
        GridLayout grid = new GridLayout(0, chooseBestColumnSize(), 1, 1);
        popUpMenu.setLayout(grid);
        popUpMenu.setBackground(ColorConstants.BLUE_HIGHLIGHT);
    }

    private int chooseBestColumnSize()
    {
        int cols = 4; //data.size() > 20 ? 6 : 4;
        if (data.size() < cols && data.size() != 0)
        {
            cols = data.size();
        }
        return cols;
    }

    public void mouseClicked(MouseEvent e)
    {
        // Show popup menu. don't use e.ispopuptrigger in mouseReleased because
        // the menu should be shown when either button is pressed.
        maybeShowPopup(e, true);
    }

    public void mousePressed(MouseEvent e)
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void mouseReleased(MouseEvent e)
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void mouseEntered(MouseEvent e)
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void mouseExited(MouseEvent e)
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void setEnabled(boolean enabled)
    {
        super.setEnabled(enabled);
        if (enabled)
        {
            Boolean isThisListening = false;
            MouseListener[] listeners = this.getMouseListeners();
            for (MouseListener listener : listeners)
            {
                isThisListening = isThisListening || listener == this;
            }

            if (!isThisListening)
            {
                addMouseListener(this);
            }
        } else
        {
            this.removeMouseListener(this);
        }
    }

    public void update(Observable o, Object arg)
    {
        // Means the data set has changed.
        setDataRange((List) o);
    }
}
