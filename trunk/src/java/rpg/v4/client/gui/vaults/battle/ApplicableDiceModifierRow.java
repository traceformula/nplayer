package rpg.v4.client.gui.vaults.battle;

import org.apache.log4j.Logger;
import rpg.swingx.JTransparentPanel;
import rpg.v4.client.gui.util.factories.LabelFactory;
import rpg.v4.client.gui.util.picker.impl.StringPicker;
import rpg.v4.client.gui.util.picker.impl.IntegerPicker;
import rpg.v4.middleware.jaxb.V4EntityNameKeyPair;
import rpg.v4.middleware.jaxb.V4Modifier;
import rpg.v4.middleware.jaxb.V4ItemCapsule;
import rpg.v4.middleware.jaxb.V4Weapon;
import rpg.v4.middleware.util.NumberGenerator;
import rpg.v4.server.battle.InitiativeOrderManager;
import rpg.v4.server.battle.AppliedModifiersManager;
import rpg.v4.server.entity.Entity;
import rpg.v4.server.state.State;
import rpg.v4.server.vaults.EntityCollectionManager;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.UUID;
import java.util.ArrayList;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

/**
 * Created by IntelliJ IDEA.
 * User: fjelds
 * Date: Nov 5, 2009
 * Time: 8:45:19 PM
 * <p/>
 * Displays one of the modifiers
 */
public class ApplicableDiceModifierRow extends ModifierRow implements PropertyChangeListener
{
    private static final Logger logger = Logger.getLogger(ApplicableDiceModifierRow.class);

    private V4Modifier v4Modifier;
    private StringPicker targetPicker;
    private StringPicker weaponPicker;
    private Entity entity;
    private JLabel diceLabel;
    private boolean useWeapon;
    private IntegerPicker diceRoll;

    public ApplicableDiceModifierRow(V4Modifier v4Modifier, StringPicker targetPicker,
                                     StringPicker weaponPicker, Entity entity)
    {
        this.v4Modifier = v4Modifier;
        this.targetPicker = targetPicker;
        this.weaponPicker = weaponPicker;
        this.entity = entity;
        weaponPicker.addPropertyChangeListener("text", this);
        diceRoll = new IntegerPicker();

        String id = "Heat of the battle - " + UUID.randomUUID().toString();
        v4Modifier.setSourceID(id);

        V4Modifier.DiceModifier diceModifier = v4Modifier.getDiceModifier();
        String overallMod = diceModifier.getOverallMultiplier() == 0.5 ? "Half of" : "All of";

        useWeapon = v4Modifier.getDiceModifier().getDiceType() == 0;
        diceLabel = LabelFactory.createHeaderLabel("");
        propertyChange(null);

        String target = v4Modifier.getTargetID();

        Box hBox = Box.createHorizontalBox();
        hBox.add(LabelFactory.createHeaderLabelNonPadded(overallMod));
        hBox.add(diceLabel);
        hBox.add(LabelFactory.createHeaderLabelNonPadded("="));
        hBox.add(diceRoll);
        hBox.add(LabelFactory.createHeaderLabelNonPadded(" to target's "));
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

                float overallModifer = v4Modifier.getDiceModifier().getOverallMultiplier();
                int value = (int) (Integer.parseInt(diceRoll.getText()) * overallModifer);
                AppliedModifiersManager.getInstance().applyModifier(
                        currentTarget,
                        v4Modifier.getTargetID(),
                        v4Modifier.getV4ModifierType(),
                        v4Modifier.getSourceID(),
                        value);

                Integer isTotal = (Integer) state.getTotal();
                logger.info("Total was/is: " + wasTotal + "/" + isTotal);

                diceRoll.removePropertyChangeListener(this);
                diceRoll.setEditable(false);
                diceRoll.repaint();

                return;
            }
        }
    }

    public void propertyChange(PropertyChangeEvent propertyChangeEvent)
    {
        int numDice = 0, diceType = 0;
        if (useWeapon)
        {
            String weaponName = weaponPicker.getText();
            for (V4ItemCapsule itemCapsule : entity.getWeaponsFromInventoryList())
            {
                if (itemCapsule.getName().equals(weaponName))
                {
                    V4Weapon.Damage damage = itemCapsule.getV4Weapon().getDamage();
                    numDice = damage.getNumDice();
                    diceType = damage.getDiceType();
                }
            }
        } else
        {
            numDice = v4Modifier.getDiceModifier().getNumberOfDice();
            diceType = v4Modifier.getDiceModifier().getDiceType();
        }

        String diceString = numDice + "d" + diceType;
        diceLabel.setText(diceString);
        diceLabel.repaint();

        List<Integer> diceOutcomes = new ArrayList<Integer>(numDice * diceType);
        for (int i=numDice; i <= 50; i++)
        {
            diceOutcomes.add(i);
        }

        diceRoll.setDataRange(diceOutcomes);
        diceRoll.setText(NumberGenerator.pickNumber(numDice, numDice * diceType) + "");
        diceRoll.repaint();

    }
}
