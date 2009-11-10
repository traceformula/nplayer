package rpg.v4.client.gui.control.combat.shape;

import org.apache.log4j.Logger;
import rpg.v4.client.gui.GameMasterFrame;
import rpg.v4.server.entity.Entity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

/**
 * Created by IntelliJ IDEA.
 * User: Sven-Ivar Fjeld
 * Date: 08-Mar-2005
 * Time: 22:27:59
 *
 * Moves a shape about
 */
public class ShapeListener implements MouseListener, MouseMotionListener
{

    static Logger logger = Logger.getLogger(ShapeListener.class);

    private Entity entity;
    private EntityGridShape entityGridShape;
    private JPopupMenu activePopUpMenu;
    private boolean isDragable;


    public ShapeListener(Entity entity, EntityGridShape entityGridShape)
    {
        this.entity = entity;
        this.entityGridShape = entityGridShape;
        isDragable = false;
    }


    public void mouseClicked(MouseEvent e)
    {

    }

    public void mousePressed(MouseEvent e)
    {
        if (e.getButton() == MouseEvent.BUTTON1)
        {
            Component origin = GameMasterFrame.frame;
            origin.setCursor(origin.getToolkit().createCustomCursor(new BufferedImage(3, 3, BufferedImage.TYPE_INT_ARGB),
                    new Point(0, 0), "null"));
            isDragable = true;
        }
    }

    public void mouseReleased(MouseEvent e)
    {
        //System.out.println("is popup "+e.isPopupTrigger() + "\nactive  =  "+activePopUpMenu+"\ntarget  =  "+targetPopUpMenu);
        if (e.isPopupTrigger())
        {
            activePopUpMenu.pack();
            activePopUpMenu.revalidate();
            activePopUpMenu.show(e.getComponent(), e.getX(), e.getY());
        } else if (e.getButton() == MouseEvent.BUTTON1)
        {
            Component origin = GameMasterFrame.frame; // The JFrame decides the cursor type.
            origin.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            isDragable = false;
        }
    }

    public void mouseEntered(MouseEvent e)
    {

    }

    public void mouseExited(MouseEvent e)
    {

    }

    public void mouseDragged(MouseEvent e)
    {
        if (isDragable)
        {
            final Component origin = e.getComponent();
            e.setSource(origin.getParent());
            e.translatePoint(origin.getX(), origin.getY());

            // Update coordinates and position then repaint.
            // If the point is outside the boundaries, then keep it inside the boundaries.
            Point p = (Point) e.getPoint().clone();
            if (p.getX() < 0 && p.getY() < 0)
            {
                p.setLocation(0, 0);
            } else if (p.getX() < 0 && p.getY() >= 0)
            {
                p.setLocation(0, p.getY());
            } else if (p.getX() >= 0 && p.getY() < 0)
            {
                p.setLocation(p.getX(), 0);
            }

            int x = (int) p.getX();
            int y = (int) p.getY();
            entityGridShape.setZoomedBounds(x, y);
            origin.getParent().repaint();
        }
    }

    public void mouseMoved(MouseEvent e)
    {

    }

    public void setActivePopUpMenu(JPopupMenu activePopUpMenu)
    {
        this.activePopUpMenu = activePopUpMenu;
    }
}
