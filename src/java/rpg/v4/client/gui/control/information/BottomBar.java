package rpg.v4.client.gui.control.information;

import rpg.swingx.JTransparentPanel;
import rpg.v4.client.gui.GameMasterFrame;

import javax.swing.*;
import java.awt.*;

/**
 * Displays various bottom bar widgets.
 */
public class BottomBar extends JTransparentPanel
{
    public BottomBar(GameMasterFrame gameMasterFrame)
    {
        add(new InformationBlock(), BorderLayout.CENTER);

        Box hBox = Box.createHorizontalBox();
        hBox.add(new ContactBlock());
        hBox.add(new ResizerBlock(gameMasterFrame));

        add(hBox, BorderLayout.EAST);
    }
}
