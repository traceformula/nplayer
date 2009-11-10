package rpg.v4.client.gui.vaults.battle;

import org.apache.log4j.Logger;
import rpg.swingx.JTransparentPanel;
import rpg.v4.client.gui.util.factories.LabelFactory;
import rpg.v4.client.gui.util.picker.impl.StringPicker;
import rpg.v4.middleware.jaxb.V4EntityNameKeyPair;
import rpg.v4.middleware.jaxb.V4Modifier;
import rpg.v4.server.battle.InitiativeOrderManager;
import rpg.v4.server.battle.AppliedModifiersManager;
import rpg.v4.server.entity.Entity;
import rpg.v4.server.state.State;
import rpg.v4.server.vaults.EntityCollectionManager;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.UUID;

/**
 * Created by IntelliJ IDEA.
 * User: fjelds
 * Date: Nov 5, 2009
 * Time: 8:45:19 PM
 *
 * Displays one of the modifiers
 */
public class ApplicableModifierRow extends ModifierRow
{
    private static final Logger logger = Logger.getLogger(ApplicableDiceModifierRow.class);

    private V4Modifier v4Modifier;
    private StringPicker targetPicker;

    public ApplicableModifierRow(V4Modifier v4Modifier, StringPicker targetPicker)
    {
        this.v4Modifier = v4Modifier;
        this.targetPicker = targetPicker;
        String id = "Heat of the battle - " + UUID.randomUUID().toString();
        v4Modifier.setSourceID(id);

        String modifier = v4Modifier.getModifier().toString();
        String type = v4Modifier.getV4ModifierType();
        String target = v4Modifier.getTargetID();

        Box hBox = Box.createHorizontalBox();
        hBox.add(LabelFactory.createHeaderLabelNonPadded(modifier));
        hBox.add(LabelFactory.createHeaderLabelNonPadded(type));
        hBox.add(LabelFactory.createHeaderLabelNonPadded(" modifier to target's "));
        hBox.add(LabelFactory.createHeaderLabelNonPadded(target));

        add(hBox, BorderLayout.WEST);        
    }

    public void commit()
    {
        List<V4EntityNameKeyPair> battleParticipants = InitiativeOrderManager.getInstance().getEntityInitiativeOrderList();
        String targetName = targetPicker.getText();
        for (V4EntityNameKeyPair participant : battleParticipants)
        {
            if (targetName.equals(participant.getName()))
            {
                logger.info("Applying modifier to " + targetName + ", " + v4Modifier);
                Entity currentTarget = EntityCollectionManager.get(participant);
                State state = currentTarget.getState(v4Modifier.getTargetID());
                Integer wasTotal = (Integer) state.getTotal();

                AppliedModifiersManager.getInstance().applyModifier(currentTarget, v4Modifier);

                Integer isTotal = (Integer) state.getTotal();
                logger.info("Total was/is: " + wasTotal + "/" + isTotal);
                return;
            }
        }
    }
}
