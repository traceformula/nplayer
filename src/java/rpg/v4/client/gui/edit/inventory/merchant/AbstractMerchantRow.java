package rpg.v4.client.gui.edit.inventory.merchant;

import rpg.swingx.JTransparentPanel;
import rpg.v4.client.gui.util.factories.LabelFactory;
import rpg.v4.client.gui.util.listener.MousePickListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by IntelliJ IDEA.
 * User: fjelds
 * Date: Aug 29, 2009
 * Time: 11:15:23 PM
 *
 */
public abstract class AbstractMerchantRow extends JTransparentPanel implements MouseListener
{
    protected AbstractMerchantRow(String labelText, String tooltip)
    {
        this(labelText, tooltip, 5, 5);
    }

    protected AbstractMerchantRow(String labelText, String tooltip, int topPadding, int bottomPadding)
    {
        JLabel itemLabel = LabelFactory.createHeaderLabel(labelText, topPadding, bottomPadding);
        itemLabel.setToolTipText(tooltip);
        itemLabel.addMouseListener(this);
        itemLabel.addMouseListener(new MousePickListener(itemLabel));

        Box horizontalBox = Box.createHorizontalBox();
        horizontalBox.add(itemLabel);
        add(horizontalBox, BorderLayout.NORTH);
        setBorder(BorderFactory.createEmptyBorder(5,0,5,0));
    }


    protected abstract void doAction();

    public final void mouseClicked(MouseEvent mouseEvent)
    {
        doAction();
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
