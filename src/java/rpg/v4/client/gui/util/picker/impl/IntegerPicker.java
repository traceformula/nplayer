package rpg.v4.client.gui.util.picker.impl;

import org.apache.log4j.Logger;
import rpg.swingx.ColorConstants;
import rpg.v4.client.gui.util.picker.Picker;
import rpg.v4.client.gui.util.picker.listener.PickerListener;
import rpg.v4.middleware.constants.FontConstants;
import rpg.v4.server.state.State;
import rpg.v4.server.state.impl.IntegerState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Sven-Ivar Fjeld
 * Date: 18.sep.2005
 * Time: 14:21:14
 *
 * Allows picking an integer and sets it in the state.
 */
public class IntegerPicker extends JLabel implements Picker, MouseListener
{

    public static final Logger logger = Logger.getLogger(IntegerPicker.class);

    private IntegerState state;
    private Integer lowerBound, upperBound;
    private JPopupMenu popUpMenu;
    private List<Integer> data;
    private PickerListener pickerListener;
    private boolean editable;

    public IntegerPicker()
    {
        lowerBound = 1;
        upperBound = 20;
        data = createDataSet(lowerBound, upperBound);
        pickerListener = new PickerListener(this);
        state = null;

        setText(data.get(0).toString());
        setPreferredSize(new Dimension(20, 20));
        setHorizontalTextPosition(SwingConstants.CENTER);
        setHorizontalAlignment(SwingConstants.CENTER);
        setFont(FontConstants.HEADER_FONT_BOLD);
        setForeground(ColorConstants.TEXT_PICKER);
        addMouseListener(this);
        createPopUpMenu();
    }
    
    public IntegerPicker(State state)
    {
        this();
        setState(state);
    }
    
    public IntegerPicker(int lowerBound, int upperBound)
    {
        this();
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        data = createDataSet(lowerBound, upperBound);
        createPopUpMenu();
    }

    public IntegerPicker(List dataSet)
    {
        this();
        setDataRange(dataSet);
    }

    protected static ArrayList<Integer> createDataSet(int lowerBound, int upperBound)
    {
        ArrayList<Integer> list = new ArrayList<Integer>();

        for (int i = lowerBound; i <= upperBound; i++)
        {
            list.add(i);
        }

        return list;
    }

    public void setUpperBound(Object obj)
    {

        if (obj instanceof Integer)
        {
            upperBound = (Integer) obj;

            if (upperBound >= lowerBound)
            {
                data = createDataSet(lowerBound, upperBound);
                createPopUpMenu();
            }
        } else
        {
            logger.error("Unsupported object for picker: " + obj.getClass().getCanonicalName());
        }
    }

    public void setLowerBound(Object obj)
    {

        if (obj instanceof Integer)
        {
            lowerBound = (Integer) obj;
            if (upperBound >= lowerBound)
            {
                data = createDataSet(lowerBound, upperBound);
                createPopUpMenu();
            }
        } else
        {
            logger.error("Unsupported object for picker: " + obj.getClass().getCanonicalName());
        }
    }

    public void setDataRange(List dataRange)
    {
        if (dataRange == null || dataRange.size() == 0)
        {
            logger.error("New data range contained no information so it was not set.");
            return;
        }

        data.clear();
        data.addAll(dataRange);
        createPopUpMenu();
    }

    public void setState(State state)
    {
        this.state = (IntegerState) state;
        setText(state.getSubTotal("Initial").toString());
    }

    public boolean isDataRangeOfValidObjects(List dataRange)
    {

        for (Object object : dataRange)
        {
            if (!(object instanceof Integer))
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
                state.add("Initial", "Initial value", new Integer((String) obj));
            }
        }
    }

    public void setEditable(boolean editable)
    {
        this.setEnabled(editable);
        this.editable = editable;

        if (editable)
        {
            this.addMouseListener(this);
        } else
        {
            this.removeMouseListener(this);
        }
        adjustPickerState();
    }

    public boolean isEditable()
    {
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
        //System.out.println("isPopUpTrigger:"+e.isPopupTrigger());
        if (isPopupTrigger)
        {
            popUpMenu.show(e.getComponent(),
                    e.getX(), e.getY());
        }
    }

    private void createPopUpMenu()
    {

        initiatePopUpMenu();

        for (Integer integer : data)
        {

            JMenuItem menuItem = new JMenuItem(integer.toString());

            if (data.size() > 0)
            {
                menuItem.setBackground(ColorConstants.BLUE_HIGHLIGHT);
                menuItem.setFont(FontConstants.BOLD_FONT);
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
        int cols = data.size() > 20 ? 6 : 4;
        if (data.size() < cols)
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
}
