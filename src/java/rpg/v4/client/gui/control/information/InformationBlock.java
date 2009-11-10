package rpg.v4.client.gui.control.information;

import static rpg.swingx.ColorConstants.*;

import javax.swing.*;
import java.awt.*;

/**
 * Bottom information bar with email icons, resize etc. etc.
 */
public class InformationBlock extends BottomBlock
{
    public InformationBlock()
    {
        super(0, INFORBAR_BOTTOM_LINE, INFOBAR_BOTTOM_GRADIENT, INFORBAR_BOTTOM_LINE, INFOBAR_TOP_GRADIENT, INFOBAR_LIGHT_LINE);
        add(new JLabel(""), BorderLayout.CENTER);
    }
}
