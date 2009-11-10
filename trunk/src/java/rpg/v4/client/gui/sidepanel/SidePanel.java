package rpg.v4.client.gui.sidepanel;

import static rpg.swingx.ColorConstants.*;
import rpg.swingx.JGradientPanel;
import rpg.swingx.JTransparentPanel;
import rpg.v4.client.gui.util.factories.LabelFactory;
import rpg.v4.middleware.util.collection.ObservableArrayList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Lists the available races which are clickable to obtain more information. To be used in the side bar.
 */
public class SidePanel<T> extends JTransparentPanel implements MouseListener, Observer
{
    private Box box;
    private SidePanelAssociate sidePanelAssociate;
    private ObservableArrayList<T> itemList;
    private List<T> addedItemNames;
    private JGradientPanel addGradientHeader;

    public SidePanel(SidePanelAssociate sidePanelAssociate, ObservableArrayList<T> itemList, boolean canAdd)
    {
        this(itemList);
        this.sidePanelAssociate = sidePanelAssociate;
        addGradientHeader.setVisible(canAdd);
    }

    private SidePanel(ObservableArrayList<T> itemList)
    {
        this.itemList = itemList;
        box = Box.createVerticalBox();

        JTransparentPanel p = new JTransparentPanel();
        JLabel header = LabelFactory.createDarkHeaderLabel("Creations:");
        JPanel gradientHeader = new JGradientPanel(GENERAL_DARK_LINE, BOTTOM_GRADIENT_COLOR, BOTTOM_LIGHT_LINE, TOP_GRADIENT_COLOR, TOP_LIGHT_LINE, 0, 2, 1, 2);
        gradientHeader.add(header, BorderLayout.NORTH);

        MouseAdapter addNew = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent)
            {
                sidePanelAssociate.addNew();
            }
        };

        JLabel addHeader = LabelFactory.createDarkHeaderLabel("+");
        addHeader.setToolTipText("Click to create a new one");
        addHeader.addMouseListener(addNew);
        addGradientHeader = new JGradientPanel(GENERAL_DARK_LINE, BOTTOM_GRADIENT_COLOR, BOTTOM_LIGHT_LINE, TOP_GRADIENT_COLOR, TOP_LIGHT_LINE, new Color(0,0,0,20), 0, 2, 1, 2);
        addGradientHeader.add(addHeader, BorderLayout.CENTER);

        JTransparentPanel headerBox = new JTransparentPanel();
        headerBox.add(gradientHeader, BorderLayout.CENTER);
        headerBox.add(addGradientHeader, BorderLayout.EAST);

        p.add(headerBox, BorderLayout.NORTH);
        p.add(box, BorderLayout.SOUTH);
        add(p, BorderLayout.NORTH);


        addedItemNames = new ArrayList<T>();

        for (T item : itemList)
        {
            addItemToList(item);
        }

        add(p, BorderLayout.NORTH);

        itemList.addObserver(this);
    }

    private void addItemToList(T item)
    {
        addedItemNames.add(item);
        JTransparentPanel linkPanel = new JTransparentPanel();
        JSideLabel label = new JSideLabel<T>(item);
        label.setBorder(BorderFactory.createEmptyBorder(3, 10, 3, 10));
        label.addMouseListener(this);
        linkPanel.add(label, BorderLayout.CENTER);
        box.add(linkPanel);
    }

    public void mouseClicked(MouseEvent e)
    {
        JSideLabel<T> label = (JSideLabel) e.getSource();
        sidePanelAssociate.setObject(label.getAssociatedObject());
    }

    public void mousePressed(MouseEvent e)
    {
    }

    public void mouseReleased(MouseEvent e)
    {
    }

    public void mouseEntered(MouseEvent e)
    {
    }

    public void mouseExited(MouseEvent e)
    {
    }

    public void update(Observable o, Object arg)
    {
        for (T item : itemList)
        {
            if (!addedItemNames.contains( item))
            {
                addItemToList(item);
                revalidate();
                repaint();
                return;
            }
        }
    }
}
