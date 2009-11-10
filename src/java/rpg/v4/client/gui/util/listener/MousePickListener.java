package rpg.v4.client.gui.util.listener;

import rpg.swingx.ColorConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

/**
 * Created by IntelliJ IDEA.
 * User: fjelds
 * Date: Aug 27, 2009
 * Time: 7:55:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class MousePickListener extends MouseAdapter
{
    private static Cursor normalCursor = new Cursor(Cursor.DEFAULT_CURSOR);
    private static Cursor handCursor = new Cursor(Cursor.HAND_CURSOR);
    private Color oldColor;

    private JComponent component;

    public MousePickListener(JComponent component)
    {
        this.component = component;
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent)
    {
        super.mouseEntered(mouseEvent);
        oldColor = component.getForeground();
        component.setCursor(handCursor);
        component.setForeground(ColorConstants.TEXT_PICKER);
        component.revalidate();
        component.repaint();
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent)
    {
        super.mouseExited(mouseEvent);
        component.setCursor(normalCursor);
        component.setForeground(oldColor);
        component.repaint();
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent)
    {
        super.mouseClicked(mouseEvent);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent)
    {
        super.mousePressed(mouseEvent);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent)
    {
        super.mouseReleased(mouseEvent);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent mouseWheelEvent)
    {
        super.mouseWheelMoved(mouseWheelEvent);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent)
    {
        super.mouseDragged(mouseEvent);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent)
    {
        super.mouseMoved(mouseEvent);    //To change body of overridden methods use File | Settings | File Templates.
    }
}
