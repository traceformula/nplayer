package rpg.v4.client.gui.edit.feats;

import rpg.swingx.JTransparentPanel;
import rpg.v4.client.gui.edit.CharacterFeatEdit;
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

    public static JCheckBox createCheckBox(String featName, CharacterFeatEdit characterFeatEdit)
    {
        JCheckBox checkBox = HudWidgetFactory.createHudCheckBox(featName);
        checkBox.setPreferredSize(size);
        checkBox.setMinimumSize(size);
        checkBox.setMaximumSize(size);
        checkBox.addActionListener(characterFeatEdit);
        return checkBox;
    }
}
