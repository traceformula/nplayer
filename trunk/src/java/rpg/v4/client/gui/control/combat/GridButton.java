package rpg.v4.client.gui.control.combat;

import rpg.swingx.ColorConstants;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by IntelliJ IDEA.
 * User: Sven-Ivar Fjeld
 * Date: 02-Jan-2005
 * Time: 13:19:09
 * To change this template use File | Settings | File Templates.
 */
public class GridButton extends JPanel implements MouseListener
{

    public static int width = 14, height = 14;
    public static Dimension gridDimension = new Dimension(width, height);

    private JGrid jgrid;
    private Color activeColor = ColorConstants.BLUE_HIGHLIGHT;

    public GridButton(JGrid jgrid)
    {
        super();
        this.jgrid = jgrid;
        this.setSize(gridDimension);
        this.setMinimumSize(gridDimension);
        this.setMaximumSize(gridDimension);
        this.setBackground(ColorConstants.STANDARD_BACKGROUND);
        this.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        this.addMouseListener(this);
    }


    public void paint(Graphics g)
    {
        super.paint(g);
        g.setColor(activeColor);
        g.drawLine(4, 2, 4, 11);
        g.drawLine(9, 2, 9, 11);
        g.drawLine(2, 4, 11, 4);
        g.drawLine(2, 9, 11, 9);
    }

    public void mouseClicked(MouseEvent e)
    {
        //To change body of implemented methods use File | Settings | File Templates.
        jgrid.setGridWanted(!jgrid.isGridWanted());
        if (jgrid.isGridWanted())
        {
            activeColor = ColorConstants.BLUE_HIGHLIGHT;
        } else
        {
            activeColor = Color.lightGray;
        }
        this.repaint();
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
