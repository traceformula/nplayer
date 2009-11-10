package rpg.swingx;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class JReflectionImage extends JComponent {

    private BufferedImage image;
    private BufferedImage image_hover;
    private boolean hovering = false;

    public JReflectionImage(BufferedImage image, BufferedImage image_hover)
    {
        this.image = image;
        this.image_hover = image_hover;
        Dimension preferredSize = new Dimension(image.getWidth(), (int) (image.getHeight() * 2.2));
        setPreferredSize(preferredSize);
        setMaximumSize(preferredSize);
        setMinimumSize(preferredSize);
    }

    public void setHovering(boolean hovering)
    {
        this.hovering = hovering;
    }

    public void paintComponent( Graphics g ) {
        Graphics2D g2d = (Graphics2D)g;
        int width = getWidth();
        int height = getHeight();
        
        BufferedImage image = hovering ? image_hover : this.image;

        int imageWidth = image.getWidth();
        int imageHeight = image.getHeight();
        int gap = 1;
        float opacity = 0.5f;
        float fadeHeight = 0.1f;

        g2d.translate( (width-imageWidth)/2, height/2-imageHeight );
        g2d.drawRenderedImage( image, null );
        g2d.translate( 0, 2*imageHeight+gap );
        g2d.scale( 1, -1 );

        BufferedImage reflection = new BufferedImage( imageWidth, imageHeight, BufferedImage.TYPE_INT_ARGB );
		Graphics2D rg = reflection.createGraphics();
        rg.drawRenderedImage( image, null );
		rg.setComposite( AlphaComposite.getInstance( AlphaComposite.DST_IN ) );
        rg.setPaint(
            new GradientPaint(
                0, imageHeight*fadeHeight, new Color( 0.0f, 0.0f, 0.0f, 0.0f ),
                0, imageHeight, new Color( 0.0f, 0.0f, 0.0f, opacity )
            )
        );
        rg.fillRect( 0, 0, imageWidth, imageHeight );
        rg.dispose();
        g2d.drawRenderedImage( reflection, null );
    }
}
