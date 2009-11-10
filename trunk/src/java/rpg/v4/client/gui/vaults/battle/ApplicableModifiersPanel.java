package rpg.v4.client.gui.vaults.battle;

import rpg.swingx.JTransparentPanel;
import rpg.v4.client.gui.util.picker.impl.StringPicker;
import rpg.v4.middleware.jaxb.V4Modifier;
import rpg.v4.server.entity.Entity;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: fjelds
 * Date: Nov 5, 2009
 * Time: 8:40:12 PM
 * <p/>
 * Shows the modifiers to be applied
 */
public class ApplicableModifiersPanel extends JTransparentPanel
{
    private List<ModifierRow> amrList = new ArrayList<ModifierRow>();

    public ApplicableModifiersPanel(List<V4Modifier> v4ModifierList, StringPicker targetPicker, StringPicker weaponPicker,
                                    Entity entity)
    {
        Box vBox = Box.createVerticalBox();

        for (V4Modifier v4Modifier : v4ModifierList)
        {
            boolean isDiceModifier = v4Modifier.getDiceModifier() != null;
            ModifierRow amr;
            if (isDiceModifier)
            {
                amr = new ApplicableDiceModifierRow(v4Modifier, targetPicker, weaponPicker, entity);
            } else
            {
                amr = new ApplicableModifierRow(v4Modifier, targetPicker);
            }

            vBox.add(amr);
            amrList.add(amr);
        }

        add(vBox, BorderLayout.NORTH);
    }

    public void commitIfApplicable()
    {
        if (isVisible())
        {
            for (ModifierRow amr : amrList)
            {
                amr.commit();
            }
        }
    }
}
