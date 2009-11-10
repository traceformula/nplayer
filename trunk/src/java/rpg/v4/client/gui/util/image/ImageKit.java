package rpg.v4.client.gui.util.image;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;

/**
 * Helps loading images.
 */
public class ImageKit
{

    public static ImageIcon loadImageIcon(String name)
    {
        Image img = loadImage(name);

        if (img != null)
            return new ImageIcon(img);

        return null;
    }

    public static BufferedImage loadBufferedImage(String name)
    {
        ImageIcon image = loadImageIcon(name);
        int width = image.getIconWidth();
        int height = image.getIconHeight();

        BufferedImage bufferedImage = new BufferedImage(width, height,BufferedImage.TYPE_INT_ARGB);
        Graphics2D bufImageGraphics = bufferedImage.createGraphics();
        bufImageGraphics.drawImage(image.getImage(), 0, 0, null);

        return bufferedImage;
    }

    public static Image loadImage(String name)
    {
        ClassLoader loader = ImageKit.class.getClassLoader();

        String imageName = "/rpg/v4/client/gui/util/image/images/" + name + ".png";

        if (loader != null)
        {
            URL url = ImageKit.class.getResource(imageName);

            if (url != null)
            {
                Toolkit tk = Toolkit.getDefaultToolkit();
                return tk.getImage(url);
            }
        }

        return null;
    }

}
