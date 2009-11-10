package rpg.v4.client.gui.control.information;

import static rpg.swingx.ColorConstants.*;
import rpg.swingx.JGradientPanel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * Basic bottom block.
 */
public class BottomBlock extends JGradientPanel
{
    public BottomBlock(int leftBorder, Color bottomDarkLine, Color bottomGradientColor,
                       Color bottomLightLine, Color topGradientColor, Color topLighLine)
    {
        super(bottomDarkLine, bottomGradientColor, bottomLightLine, topGradientColor, topLighLine);

        Border air = BorderFactory.createEmptyBorder(10,10,10,10);
        Border darkLine = BorderFactory.createMatteBorder(0, 0, 1, 0, INFORBAR_BOTTOM_LINE);
        Border inner2 = BorderFactory.createCompoundBorder(darkLine, air);

        Border topDarkLine = BorderFactory.createMatteBorder(1, leftBorder, 0, 0, INFOBAR_DIVIDER_DARK_LINE);
        Border topLightLine = BorderFactory.createMatteBorder(1, leftBorder, 0, 0, INFOBAR_LIGHT_LINE);
        Border topDividerLine = BorderFactory.createCompoundBorder(topDarkLine, topLightLine);

        setBorder(BorderFactory.createCompoundBorder(topDividerLine, inner2));
    }
}
