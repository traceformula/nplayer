package rpg.swingx;

import static rpg.swingx.ColorConstants.*;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * Paints a gradient background.
 */
public class JGradientPanel extends JBorderLayoutPanel
{
    private Color topGradient;
    private Color bottomGradient;

    public JGradientPanel()
    {
        this(GENERAL_DARK_LINE, BOTTOM_GRADIENT_COLOR, BOTTOM_LIGHT_LINE, TOP_GRADIENT_COLOR, TOP_LIGHT_LINE, 10, 10, 10, 10);
    }

    public JGradientPanel(int top, int left, int bottom, int right)
    {
        this(GENERAL_DARK_LINE, BOTTOM_GRADIENT_COLOR, BOTTOM_LIGHT_LINE, TOP_GRADIENT_COLOR, TOP_LIGHT_LINE, top, left, bottom, right);
    }

    public JGradientPanel(Color bottomDarkLine, Color bottomGradient, Color bottomLightLine,
                          Color topGradient, Color topLighLine)
    {
        this(bottomDarkLine, bottomGradient, bottomLightLine, topGradient, topLighLine, 10, 10, 10, 10);
    }

    public JGradientPanel(Color bottomDarkLine, Color bottomGradient, Color bottomLightLine,
                          Color topGradient, Color topLighLine, int top, int left, int bottom, int right)
    {
        this(bottomDarkLine, bottomGradient, bottomLightLine, topGradient, topLighLine, new Color(0,0,0,0), top, left, bottom, right);
    }

    public JGradientPanel(Color bottomDarkLine, Color bottomGradient, Color bottomLightLine,
                          Color topGradient, Color topLighLine, Color leftDivider, int top, int left, int bottom, int right)
    {
        this.bottomGradient = bottomGradient;
        this.topGradient = topGradient;

        Border air = BorderFactory.createEmptyBorder(top, left, bottom, right);
        Border lightLine = BorderFactory.createMatteBorder(0, 0, 1, 0, bottomLightLine);
        Border darkLine = BorderFactory.createMatteBorder(0, 0, 1, 0, bottomDarkLine);
        Border leftSide = BorderFactory.createMatteBorder(0, 1, 0, 0, leftDivider);

        Border inner = BorderFactory.createCompoundBorder(lightLine, air);
        Border leftWithAir = BorderFactory.createCompoundBorder(leftSide, inner);
        Border inner2 = BorderFactory.createCompoundBorder(darkLine, leftWithAir);

        Border topLine = BorderFactory.createMatteBorder(1, 0, 0, 0, topLighLine);

        setBorder(BorderFactory.createCompoundBorder(topLine, inner2));
    }

    public JGradientPanel(Color lineBorderColor, Color topLightLine, Color topGradient, Color bottomGradient)
    {
        this.bottomGradient = bottomGradient;
        this.topGradient = topGradient;

        Border topLineBorder = BorderFactory.createMatteBorder(1, 0, 1, 0, topLightLine);
        Border lineBorder = BorderFactory.createLineBorder(lineBorderColor);
        Border border = BorderFactory.createCompoundBorder(lineBorder, topLineBorder);
        setBorder(border);
    }

    @Override
    public void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;

        GradientPaint paint = new GradientPaint(
                0, 1, topGradient,
                0, this.getHeight(), bottomGradient);
        // install the paint and fill a rectangle with it.
        g2.setPaint(paint);
        g2.fillRect(0, 0, this.getWidth(), this.getHeight());
    }


}
