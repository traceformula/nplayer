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

    protected void fadeInto(Color newBottomGradient, Color newTopGradient)
    {
        Thread fader = new Fader(newBottomGradient, newTopGradient);
        fader.start();
    }
    
    private class Fader extends Thread
    {
        private Color newBottomGradient, newTopGradient;

        public Fader(Color newBottomGradient, Color newTopGradient)
        {
            this.newBottomGradient = newBottomGradient;
            this.newTopGradient = newTopGradient;
        }

        public void run()
        {
            double deltaPercentageMovement = 0.025;
            int bottomDRed = (int) ((newBottomGradient.getRed() - bottomGradient.getRed()) * deltaPercentageMovement); 
            int bottomDGreen = (int) ((newBottomGradient.getGreen() - bottomGradient.getGreen()) * deltaPercentageMovement); 
            int bottomDBlue = (int) ((newBottomGradient.getBlue() - bottomGradient.getBlue()) * deltaPercentageMovement);
            boolean isBottomRedDecresing = newBottomGradient.getRed() < bottomGradient.getRed();
            boolean isBottomGreenDecresing = newBottomGradient.getGreen() < bottomGradient.getGreen();
            boolean isBottomBlueDecresing = newBottomGradient.getBlue() < bottomGradient.getBlue();
            
            int topDRed = (int) ((newTopGradient.getRed() - topGradient.getRed()) * deltaPercentageMovement); 
            int topDGreen = (int) ((newTopGradient.getGreen() - topGradient.getGreen()) * deltaPercentageMovement); 
            int topDBlue = (int) ((newTopGradient.getBlue() - topGradient.getBlue()) * deltaPercentageMovement);
            boolean isTopRedDecresing = newTopGradient.getRed() < topGradient.getRed();
            boolean isTopGreenDecresing = newTopGradient.getGreen() < topGradient.getGreen();
            boolean isTopBlueDecresing = newTopGradient.getBlue() < topGradient.getBlue();
            
            System.out.println("Bottom deltas: " + bottomDRed + ", " + bottomDGreen + ", " + bottomDBlue);
            System.out.println("Bottom red current/target/isDecreasing: " + bottomGradient.getRed() + "/" + newBottomGradient.getRed() +"/" + isBottomRedDecresing);
            // It'd be ridiculous if it took 1000 steps to fade.
            int infiniteLoopCounter = 1000;
            int step = 0;
            boolean bottomColorDiffers = true;
            boolean topColorDiffers = true;
            boolean stillFading = (bottomColorDiffers || topColorDiffers) && step < infiniteLoopCounter;
            
            while (stillFading)
            {
                //System.out.println("Step "+step+", red: " + newBottomGradient.getRed()+", "+ (bottomGradient.getRed() + bottomDRed)+", "+ isBottomRedDecresing);
                int red = getTheColor(newBottomGradient.getRed(), bottomGradient.getRed() + bottomDRed, isBottomRedDecresing);
                int blue = getTheColor(newBottomGradient.getBlue(), bottomGradient.getBlue() + bottomDBlue, isBottomBlueDecresing);
                int green = getTheColor(newBottomGradient.getGreen(), bottomGradient.getGreen() + bottomDGreen, isBottomGreenDecresing);
                //System.out.println("Step "+step+", red: " + red);
                Color newBottomColor = new Color(red, green, blue);

                red = getTheColor(newTopGradient.getRed(), topGradient.getRed() + topDRed, isTopRedDecresing);
                blue = getTheColor(newTopGradient.getBlue(), topGradient.getBlue() + topDBlue, isTopBlueDecresing);
                green = getTheColor(newTopGradient.getGreen(), topGradient.getGreen() + topDGreen, isTopGreenDecresing);
                Color newTopColor = new Color(red, green, blue);
                
                bottomColorDiffers =! (bottomGradient.equals(newBottomColor));
                topColorDiffers =! (topGradient.equals(newTopColor));
                step++;
                stillFading = (bottomColorDiffers || topColorDiffers) && step < infiniteLoopCounter;

                // Set the new colors and repaint
                bottomGradient = newBottomColor;
                topGradient = newTopColor;
                revalidate();
                repaint();

                try
                {
                    //System.out.println("*** sleeping *** step="+step);
                    Thread.sleep(20);
                } catch (InterruptedException e)
                {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
            }
        }
        
        private int getTheColor(int targetColor, int calculatedNextColor, boolean isDecreasing)
        {
            //System.out.println("Is beyond target: " + (isDecreasing && calculatedNextColor < targetColor)  + " || " +
            //        (!isDecreasing && calculatedNextColor > targetColor));
            boolean isBeyondTarget = (isDecreasing && calculatedNextColor < targetColor) || 
                    (!isDecreasing && calculatedNextColor > targetColor);
            return isBeyondTarget ? targetColor : calculatedNextColor;
        }
    }
}
