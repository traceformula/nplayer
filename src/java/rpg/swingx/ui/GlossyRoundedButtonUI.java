package rpg.swingx.ui;

import static rpg.swingx.ColorConstants.TEXT_PICKER;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.geom.Arc2D;
import java.awt.geom.Area;
import java.awt.geom.RoundRectangle2D;

/**
 * To change this template use File | Settings | File Templates.
 */
public class GlossyRoundedButtonUI extends BasicButtonUI
{

    /* Font constants. */
    public static final float FONT_SIZE = 11.0f;
    public static final Color FONT_COLOR = Color.WHITE;

    /* Color constants. */
    private static final Color TOP_COLOR = new Color(170, 170, 170, 50);
    private static final Color BOTTOM_COLOR = new Color(17, 17, 17, 50);
    private static final Color TOP_PRESSED_COLOR = new Color(249, 249, 249, 153);
    private static final Color BOTTOM_PRESSED_COLOR = new Color(176, 176, 176, 153);
    private static final Color LIGHT_SHADOW_COLOR = new Color(0, 0, 0, 145);
    private static final Color DARK_SHADOW_COLOR = new Color(0, 0, 0, 50);

    /* Border constants. */
    private static final Color BORDER_COLOR = new Color(0xc5c8cf);
    private static final int BORDER_WIDTH = 1;

    /* Margin constants. */
    private static final int TOP_AND_BOTTOM_MARGIN = 2;
    private static final int LEFT_AND_RIGHT_MARGIN = 16;

    private final Roundedness fRoundedness;

    private boolean hovering = false;

    /**
     * Creates a HUD style {@link javax.swing.plaf.ButtonUI} with full rounded edges.
     */
    public GlossyRoundedButtonUI()
    {
        fRoundedness = Roundedness.ROUNDED_BUTTON;
    }

    @Override
    protected void installDefaults(AbstractButton b)
    {
        super.installDefaults(b);

        // TODO save original values.

        b.setFont(getHudFont());
        b.setForeground(FONT_COLOR);
        b.setOpaque(false);
        b.setHorizontalTextPosition(AbstractButton.CENTER);

        // add space for the drop shadow underneath the button.
        int bottomMargin = TOP_AND_BOTTOM_MARGIN + getHudControlShadowSize();
        b.setMargin(new Insets(TOP_AND_BOTTOM_MARGIN, LEFT_AND_RIGHT_MARGIN,
                bottomMargin, LEFT_AND_RIGHT_MARGIN));
    }

    public void setHovering(boolean hovering)
    {
        this.hovering = hovering;
    }

    @Override
    public void paint(Graphics g, JComponent c)
    {
        // now that the background is painted, call the super.paint.
        super.paint(g, c);
        
        AbstractButton button = (AbstractButton) c;
        Graphics2D graphics = (Graphics2D) g.create();

        // paint the HUD button border and background in the visual space that the
        // button should take up (the area not including the drop shadow). note
        // that the paint method will also paint the shadow "below" button.
        int buttonHeight = button.getHeight() - getHudControlShadowSize();
        paintHudControlBackground(graphics, button, button.getWidth(),
                buttonHeight, fRoundedness);

        graphics.dispose();


    }

    /**
     * Paints a HUD style button background onto the given {@link java.awt.Graphics2D} context.
     * The background will be painted from 0,0 to width/height.
     *
     * @param graphics    the graphics context to paint onto.
     * @param button      the button being painted.
     * @param width       the width of the area to paint.
     * @param height      the height of the area to paint.
     * @param roundedness the roundedness to use when painting the background.
     */
    public void paintHudControlBackground(
            Graphics2D graphics, AbstractButton button, int width, int height,
            Roundedness roundedness)
    {
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // TODO replace with real drop shadow painting. see Romain Guy's article for
        // TODO more info on real drop shadows:
        // TODO   http://www.jroller.com/gfx/entry/fast_or_good_drop_shadows

        // paint a light shadow line further away from the button.
        graphics.setColor(LIGHT_SHADOW_COLOR);
        int arcDiameter = roundedness.getRoundedDiameter(height);
        graphics.drawRoundRect(0, 0, width - 1, height, arcDiameter, arcDiameter);

        // paint a dark shadow line closer to the button.
        graphics.setColor(DARK_SHADOW_COLOR);
        int smallerShadowArcDiameter = height - 1;
        graphics.drawRoundRect(0, 0, width - 1, height + 1, smallerShadowArcDiameter,
                smallerShadowArcDiameter);

        // fill the button with the gradient paint.
        graphics.setPaint(createButtonPaint(button, BORDER_WIDTH));
        graphics.fillRoundRect(0, 1, width, height - 1, arcDiameter, arcDiameter);


        // draw the border around the button.
        graphics.setColor(hovering ? TEXT_PICKER : BORDER_COLOR);
        graphics.drawRoundRect(0, 0, width - 1, height - 1, arcDiameter, arcDiameter);

        // Draw glossy effect
        // draw Arc2D.Double

        Color glossyTransparentColor = new Color(255, 255, 255, 30);

        int halfHeight = height/2;
        double fractionOfHeight = 0.2;
        int arcStartHeight = (int) (halfHeight - halfHeight * fractionOfHeight);
        int arcHeight = (int) (halfHeight * fractionOfHeight);
        RoundRectangle2D wholeRectangle = new RoundRectangle2D.Float(0, 0, width - 1, height - 1, arcDiameter, arcDiameter);

        Arc2D arc = new Arc2D.Double(0, arcStartHeight-1, width - 1, arcHeight+1, 180, 180, Arc2D.PIE);
        Rectangle topRectangle = new Rectangle(0, 0, width, arcStartHeight+4);

        Area wholeRectangleArea = new Area(wholeRectangle);
        Area arcArea = new Area(arc);
        Area topSquareArea = new Area(topRectangle);

        arcArea.intersect(wholeRectangleArea);
        topSquareArea.intersect(wholeRectangleArea);

        topSquareArea.add(arcArea);

        graphics.setColor(glossyTransparentColor);
        graphics.fill(topSquareArea);
    }

    /**
     * Creates a HUD style gradient paint for the given button offset from the top
     * and bottom of the button by the given line border size.
     */
    private static Paint createButtonPaint(AbstractButton button,
                                           int lineBorderWidth)
    {
        boolean isPressed = button.getModel().isPressed();
        Color topColor = isPressed ? TOP_PRESSED_COLOR : TOP_COLOR;
        Color bottomColor = isPressed ? BOTTOM_PRESSED_COLOR : BOTTOM_COLOR;
        int bottomY = button.getHeight() - lineBorderWidth * 2;
        return new GradientPaint(0, lineBorderWidth, topColor, 0, bottomY, bottomColor);
    }

    /**
     * Gets the number of pixels that a HUD style widget's shadow takes up. HUD
     * button's have a shadow directly below them, that is, there is no top, left
     * or right component to the shadow.
     *
     * @return the number of pixels that a HUD style widget's shadow takes up.
     */
    private static int getHudControlShadowSize()
    {
        // this is hardcoded at two pixels for now, but ideally it would be
        // calculated.
        return 2;
    }

    /**
     * Gets the font used by HUD style widgets.
     *
     * @return the font used by HUD style widgets.
     */
    private static Font getHudFont()
    {
        return UIManager.getFont("Button.font").deriveFont(Font.BOLD, FONT_SIZE);
    }

    /**
     * An enumeration representing the roundness styles of HUD buttons. Using this
     * enumeration will make it easier to transition this code to support more
     * HUD controls, like check boxes and combo buttons.
     */
    public enum Roundedness
    {
        /**
         * A roundedness of 95%, equates to almost a half-circle as the button
         * edge shape.
         */
        ROUNDED_BUTTON(.95);

        private final double fRoundedPercentage;

        private Roundedness(double roundedPercentage)
        {
            fRoundedPercentage = roundedPercentage;
        }

        private int getRoundedDiameter(int controlHeight)
        {
            int roundedDiameter = (int) (controlHeight * fRoundedPercentage);
            // force the rounded diameter value to be even - odd values look lumpy.
            int makeItEven = roundedDiameter % 2;
            return roundedDiameter - makeItEven;
        }
    }

}