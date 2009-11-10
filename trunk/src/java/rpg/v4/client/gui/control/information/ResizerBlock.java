package rpg.v4.client.gui.control.information;

import static rpg.swingx.ColorConstants.*;
import rpg.v4.client.gui.GameMasterFrame;
import rpg.v4.client.gui.util.image.ImageKit;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * Allows a user to resize the application.
 */
public class ResizerBlock extends BottomBlock implements MouseListener, MouseMotionListener
{
    private Cursor normalCursor = new Cursor(Cursor.DEFAULT_CURSOR);
    private Cursor resiserCursor = new Cursor(Cursor.NW_RESIZE_CURSOR);
    private Point initialPoint;
    private GameMasterFrame gameMasterFrame;

    public ResizerBlock(GameMasterFrame gameMasterFrame)
    {
        super(0, INFORBAR_BOTTOM_LINE, INFOBAR_BOTTOM_GRADIENT, INFORBAR_BOTTOM_LINE, INFOBAR_TOP_GRADIENT, INFOBAR_LIGHT_LINE);
        this.gameMasterFrame = gameMasterFrame;
        Box box = Box.createHorizontalBox();
        ImageIcon icon = ImageKit.loadImageIcon("resizer");
        JLabel resizerLabel = new JLabel(icon);
        resizerLabel.addMouseListener(this);
        resizerLabel.addMouseMotionListener(this);
        box.add(resizerLabel);
        add(box, BorderLayout.SOUTH);

        Border air = BorderFactory.createEmptyBorder(2,2,2,2);
        Border darkLine = BorderFactory.createMatteBorder(0, 0, 1, 0, INFORBAR_BOTTOM_LINE);
        Border inner2 = BorderFactory.createCompoundBorder(darkLine, air);

        Border topDarkLine = BorderFactory.createMatteBorder(1, 0, 0, 0, INFOBAR_DIVIDER_DARK_LINE);
        Border topLightLine = BorderFactory.createMatteBorder(1, 0, 0, 0, INFOBAR_LIGHT_LINE);
        Border topDividerLine = BorderFactory.createCompoundBorder(topDarkLine, topLightLine);

        setBorder(BorderFactory.createCompoundBorder(topDividerLine, inner2));
    }

    public void mouseDragged(MouseEvent e)
    {
        Point mousePoint = e.getPoint();
        int dX = mousePoint.x - initialPoint.x;
        int dY = mousePoint.y - initialPoint.y;

        Dimension frameDimension = gameMasterFrame.getSize();
        int newX = (int) (frameDimension.getWidth() + dX);
        int newY = (int) (frameDimension.getHeight() + dY);

        gameMasterFrame.setSize(new Dimension(newX, newY));
        gameMasterFrame.repaint();
    }

    public void mouseEntered(MouseEvent e)
    {
        setCursor(resiserCursor);
    }

    public void mouseExited(MouseEvent e)
    {
        setCursor(normalCursor);
    }

    public void mouseClicked(MouseEvent e)
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void mousePressed(MouseEvent e)
    {
        initialPoint = e.getPoint();
    }

    public void mouseReleased(MouseEvent e)
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void mouseMoved(MouseEvent e)
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
