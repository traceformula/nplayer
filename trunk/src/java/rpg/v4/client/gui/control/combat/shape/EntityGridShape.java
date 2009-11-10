package rpg.v4.client.gui.control.combat.shape;

import org.apache.log4j.Logger;
import rpg.swingx.ColorConstants;
import static rpg.swingx.ColorConstants.REACH_COLOR;
import rpg.v4.middleware.constants.FontConstants;
import rpg.v4.middleware.jaxb.V4EntityNameKeyPair;
import rpg.v4.middleware.util.NumberGenerator;
import rpg.v4.server.entity.Entity;
import rpg.v4.server.vaults.EntityCollectionManager;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by IntelliJ IDEA.
 * User: Sven-Ivar Fjeld
 * Date: 08-Mar-2005
 * Time: 19:48:28
 * <p/>
 * Manages an entity's shape in the grid, including the reach panel
 * <p/>
 */
public class EntityGridShape implements Observer
{
    static Logger logger = Logger.getLogger(EntityGridShape.class);
    private static final int REACH_IN_FEET = 5;
    private static final double FIVE_FEET = 5;

    private Point3D pointBasic,     // The internal representation, 1,2,3,4, etc. Should be used in mathematical calculations
            pointZoomed;    // The point representing the zoomed location in a JPanel. Should be used for drawing
    private double pixelsPrFoot;
    private int reachWidth, reachLeftAdjustment;
    private JPanel shape2D = new JPanel();
    private JPanel reachPanel2D = new JPanel();
    private JLabel prefix;
    private Entity entity;

    public EntityGridShape(V4EntityNameKeyPair nkp)
    {
        entity = EntityCollectionManager.get(nkp);
        entity.addObserver(this);
        createPrefix(entity);

        reachPanel2D.setBackground(REACH_COLOR);

        pixelsPrFoot = 4;

        int x = NumberGenerator.pickNumber(36);
        int y = NumberGenerator.pickNumber(15);
        int z = 0;
        entity.getState("X-Location").add("Initial", "Grid", x);
        entity.getState("Y-Location").add("Initial", "Grid", y);
        entity.getState("Z-Location").add("Initial", "Grid", z);
        pointBasic = new Point3D(x, y, z);
        pointZoomed = new Point3D(0, 0, 0);
        shape2D.setBounds((int) pointBasic.x, (int) pointBasic.y, (int) FIVE_FEET, (int) FIVE_FEET);

        reachWidth = (int) ((REACH_IN_FEET * 2 + FIVE_FEET) * pixelsPrFoot);
        reachLeftAdjustment = (int) (REACH_IN_FEET * pixelsPrFoot);
        reachPanel2D.setBounds((int) pointBasic.x - reachLeftAdjustment,
                (int) pointBasic.y - reachLeftAdjustment,
                reachWidth, reachWidth);

        ShapeListener shapeListener = new ShapeListener(entity, this);

        //popUpMenuActive = this.createPopUpMenu2D(popUpMenuActive);
        //shapeListener.setActivePopUpMenu(popUpMenuActive);

        shape2D.addMouseListener(shapeListener);
        shape2D.addMouseMotionListener(shapeListener);
        shape2D.setToolTipText(entity.getName());

        // Change when the name of the character changes.
        //entity.getGm().getObservableEntityList().addObserver(this);
        //entity.getGm().getRoundManager().addObserver(this);
        setPixelsPrFoot(4);
    }

    private void createPrefix(Entity entity)
    {
        String name = entity.getName();
        if (entity.getName().length() > 1)
        {
            name = entity.getName().substring(0, 2);
        }

        prefix = new JLabel(name);
        prefix.setFont(FontConstants.HEADER_FONT_SMALL);
        prefix.setForeground(ColorConstants.TEXT_NORMAL);

        shape2D.add(prefix);
        shape2D.setBackground(entity.getColor());
        shape2D.setToolTipText(entity.getName());
    }

    private void updateSubShapes()
    {
        entity.getState("X-Location").add("Initial", "Grid", (int) pointBasic.getX());
        entity.getState("Y-Location").add("Initial", "Grid", (int) pointBasic.getY());
        entity.getState("Z-Location").add("Initial", "Grid", 0);

        shape2D.setBounds((int) pointZoomed.x, (int) pointZoomed.y, (int) (FIVE_FEET * pixelsPrFoot), (int) (FIVE_FEET * pixelsPrFoot));
        reachPanel2D.setBounds((int) pointZoomed.x - reachLeftAdjustment,
                (int) (pointZoomed.y - reachLeftAdjustment), reachWidth, reachWidth);
    }

    public void update(Observable o, Object arg)
    {
        logger.info("Updating EntityGridShape. o is "+ o.getClass().getCanonicalName() +", arg is entity? " + (arg instanceof Entity));
        shape2D.setBackground(entity.getColor());
        shape2D.repaint();
    }

    public double getWidth()
    {
        return FIVE_FEET * pixelsPrFoot;
    }

    public void setPixelsPrFoot(int pixelsPrFoot)
    {
        this.pixelsPrFoot = pixelsPrFoot;
        reachWidth = (int) ((REACH_IN_FEET * 2 + FIVE_FEET) * pixelsPrFoot);
        reachLeftAdjustment = REACH_IN_FEET * pixelsPrFoot;
        pointZoomed = new Point3D(pointBasic.x * FIVE_FEET * pixelsPrFoot, pointBasic.y * FIVE_FEET * pixelsPrFoot, pointBasic.z * FIVE_FEET * pixelsPrFoot);
        updateSubShapes();
    }

    public void setZoomedBounds(int unprocessedX, int unprocessedY)
    {
        int w = (int) getWidth();
        int x = (int) ((Math.floor(unprocessedX / w)) * w), y = (int) ((Math.floor(unprocessedY / w)) * w);

        pointBasic.x = Math.floor(unprocessedX / w);
        pointBasic.y = Math.floor(unprocessedY / w);
        pointZoomed.x = x;
        pointZoomed.y = y;
        updateSubShapes();
    }

    public void setBasicBounds(int basicX, int basicY)
    {
        pointBasic.x = basicX;
        pointBasic.y = basicY;

        pointZoomed.x = basicX * getWidth();
        pointZoomed.y = basicY * getWidth();
        updateSubShapes();
    }

    public Component getShape2D()
    {
        return shape2D;
    }

    public Component getReachPanel2D()
    {
        return reachPanel2D;
    }
}
