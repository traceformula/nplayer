package rpg.v4.client.gui.control.information;

import static rpg.swingx.ColorConstants.*;
import rpg.v4.client.gui.control.navigation.NavigationEnum;
import rpg.v4.client.gui.util.factories.LabelFactory;

import javax.swing.*;
import java.awt.*;

/**
 * Displays a resize corner, mailto icon and faq/help icon
 */
public class ContactBlock extends BottomBlock
{
    public ContactBlock()
    {
        super(1, INFORBAR_BOTTOM_LINE, INFOBAR_BOTTOM_GRADIENT, INFORBAR_BOTTOM_LINE, INFOBAR_TOP_GRADIENT, INFOBAR_LIGHT_LINE);
        Box box = Box.createHorizontalBox();
        JLabel emailLabel = LabelFactory.createDarkLabel(NavigationEnum.EMAIL_ME);
        box.add(emailLabel);
        add(box, BorderLayout.CENTER);
    }
}
