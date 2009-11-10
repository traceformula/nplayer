package rpg.v4.client.gui.control.combat;

import rpg.swingx.ColorConstants;
import rpg.v4.middleware.jaxb.V4EntityNameKeyPair;
import rpg.v4.middleware.util.Point3D;
import rpg.v4.server.battle.BattleStateManager;
import rpg.v4.server.battle.InitiativeOrderManager;
import rpg.v4.server.entity.Entity;
import rpg.v4.server.vaults.EntityCollectionManager;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by IntelliJ IDEA.
 * User: Sven-Ivar Fjeld
 * Date: 02-Jan-2005
 * Time: 13:05:43
 */
public class JGrid extends JPanel implements Observer
{
    private static BasicStroke STROKE_1PX = new BasicStroke(1),
            strokeWidePx = new BasicStroke(2);
    public static int width = 901, height = 900, squareSize = 5,
            pixelsPrFoot = 4,
            pixelsPrSquare = pixelsPrFoot * squareSize;
    public static Dimension gridDimension = new Dimension(width, height);
    private boolean isGridWanted = true;


    public JGrid()
    {
        super();
        this.setSize(gridDimension);
        this.setMinimumSize(gridDimension);
        this.setPreferredSize(gridDimension);
        this.setBackground(ColorConstants.SIDEBAR_BG);

        this.setLayout(null);
        BattleStateManager.instance.addObserver(this);
    }

    public void paint(Graphics g)
    {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        // Draw reach rectangles

        // Draw grid
        if (isGridWanted)
        {
            //System.out.println(Color.gray);
            g2.setColor(ColorConstants.GRID_COLOR);
            // Draw vertical bars
            for (int x = 0; x < width; x = x + pixelsPrSquare)
            {
                g2.drawLine(x, 0, x, height);
            }
            // Draw horizontal bars
            for (int y = 0; y < width; y = y + pixelsPrSquare)
            {
                g2.drawLine(0, y, height, y);
            }
        }

        //Draw active rectangle
        V4EntityNameKeyPair nkp = InitiativeOrderManager.getInstance().getActiveEntity();

        if (null != nkp)
        {
            Entity activeEntity = EntityCollectionManager.get(nkp);
            int x = (Integer) activeEntity.getState("X-Location").getTotal();
            int y = (Integer) activeEntity.getState("Y-Location").getTotal();
            int z = (Integer) activeEntity.getState("Z-Location").getTotal();

            Point3D location = new Point3D(x, y, z);
            g2.setStroke(strokeWidePx);
            g2.setColor(ColorConstants.TEXT_PICKER);
            g2.setRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON));
            g2.drawRoundRect((int) location.x * pixelsPrSquare, (int) location.y * pixelsPrSquare,
                    pixelsPrSquare, pixelsPrSquare, 5, 5);

            g2.setStroke(STROKE_1PX);
            g2.setRenderingHints(new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_OFF));
        }
    }

    public boolean isGridWanted()
    {
        return isGridWanted;
    }

    public void setGridWanted(boolean gridWanted)
    {
        isGridWanted = gridWanted;
        this.repaint();
    }

    public void update(Observable observable, Object o)
    {
        repaint();
    }
}
