package rpg.v4.client.gui.edit.feats;

import rpg.swingx.JTransparentPanel;
import com.explodingpixels.macwidgets.HudWidgetFactory;

import javax.swing.*;
import java.awt.*;

/**
 * Created by IntelliJ IDEA.
 * User: fjelds
 * Date: Feb 3, 2010
 * Time: 8:31:21 PM
 *
 * Represents one feat
 */
public class FeatPanel extends JTransparentPanel
{
    private static final Dimension size = new Dimension(160, 20);
    public FeatPanel(String featName)
    {
        JCheckBox checkBox = HudWidgetFactory.createHudCheckBox(featName);
        add(checkBox, BorderLayout.WEST);
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);

    }
}
