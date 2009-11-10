package rpg.v4.client.gui.control.combat.shape;

/**
 * Created by IntelliJ IDEA.
 * User: Sven-Ivar Fjeld
 * Date: 08-Mar-2005
 * Time: 21:16:43
 * <p/>
 * This was deprecated:    This class was created before the Point3d in Java3d was discovered (notice the difference in
 * the last letter: This class ends with a capital D while the other a normal d).
 */
public class Point3D
{

    public double x, y, z;

    public Point3D(int x, int y, int z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Point3D(double x, double y, double z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Point3D()
    {
        x = 0;
        y = 0;
        z = 0;
    }

    public double getX()
    {
        return x;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public double getY()
    {
        return y;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    public double getZ()
    {
        return z;
    }

    public void setZ(int z)
    {
        this.z = z;
    }
}
