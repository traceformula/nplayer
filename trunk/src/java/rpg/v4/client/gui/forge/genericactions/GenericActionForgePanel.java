package rpg.v4.client.gui.forge.genericactions;

import rpg.swingx.JTransparentPanel;
import rpg.v4.client.gui.forge.AbstractForgePanel;
import rpg.v4.client.gui.forge.genericactions.impl.prerequisite.PrerequisiteForge;
import rpg.v4.client.gui.forge.genericactions.impl.prerequisite.PrerequisiteForgePanel;
import rpg.v4.client.gui.forge.genericactions.impl.prerequisite.PrerequisitePanel;
import rpg.v4.client.gui.forge.modifier.ModifierForge;
import rpg.v4.client.gui.forge.modifier.ModifierForgePanel;
import rpg.v4.client.gui.util.factories.LabelFactory;
import rpg.v4.client.gui.util.image.ImageKit;
import rpg.v4.client.gui.util.picker.impl.StringPicker;
import rpg.v4.client.proxy.ClientProxyKit;
import rpg.v4.middleware.jaxb.V4GenericAction;
import rpg.v4.middleware.util.collection.ObservableArrayList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Allows specification of generic powers. More concrete classes can use this, such as a
 * feat forge, actions forge or powers forge.
 */
public abstract class GenericActionForgePanel extends AbstractForgePanel
{
    protected V4GenericAction v4GenericAction;
    protected StringPicker typePicker, subTypePicker, sourcePicker, damageTypePicker, effectTypePicker,
            actionTypePicker, attackTypePicker, targetPicker, attackingStatePicker, defendingStatePicker,
            secondaryAttackTypePicker;
    protected JCheckBox disallowDiceRollsBox;
    protected ModifierForge hitModifiersForge, missModifiersForge;
    protected PrerequisiteForge prerequisiteForge;


    public GenericActionForgePanel(ObservableArrayList<String> itemList, String defaultModifierType, String headerName)
    {
        super(itemList, defaultModifierType, headerName, false);
        v4GenericAction = ClientProxyKit.CLIENT_PROXY.createGenericAction();

        hitModifiersForge = new ModifierForge();
        ModifierForgePanel hitModiferPanel = new ModifierForgePanel(hitModifiersForge, defaultModifierType, "On success:");
        hitModifiersForge.setModifierList(v4GenericAction.getHitModifiers().getV4Modifier());

        missModifiersForge = new ModifierForge();
        ModifierForgePanel missModiferPanel = new ModifierForgePanel(missModifiersForge, defaultModifierType, "On failure:");
        missModifiersForge.setModifierList(v4GenericAction.getMissModifiers().getV4Modifier());

        prerequisiteForge = new PrerequisiteForge();
        PrerequisiteForgePanel prerequisiteForgePanel = new PrerequisiteForgePanel(prerequisiteForge);

        JLabel label = LabelFactory.createInfoLabel("Conditional modifiers to be added.");
        JPanel p = new JTransparentPanel();
        p.add(label, BorderLayout.WEST);
        p.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));

        modifierForgesListBox.add(p);
        modifierForgesListBox.add(prerequisiteForgePanel);
        modifierForgesListBox.add(hitModiferPanel);
        modifierForgesListBox.add(missModiferPanel);

        initialize();
    }

    protected abstract void initialize();

    public void actionPerformed(ActionEvent e)
    {
        String messages = "";
        setNewName(nameTextField.getText());
        String name = v4GenericAction.getName();

        if (null == name || name.length() == 0)
        {
            messages += "\n - Enter a name.";
        }

        if (messages.length() != 0)
        {
            JOptionPane.showMessageDialog(this, "Could not create the generic action:" + messages);
            return;
        }

        boolean exists = checkIfExists();
        if (exists)
        {
            Icon icon = ImageKit.loadImageIcon("QuestionSVG");
            int choice = JOptionPane.showConfirmDialog(
                    this,
                    "An action with the name \"" + name + "\" already exists. \nDo you want to overwrite it?",
                    "",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    icon);
            if (choice != JOptionPane.YES_OPTION)
            {
                return;
            }
        }

        marshal();

        ClientProxyKit.CLIENT_PROXY.saveGenericAction(v4GenericAction);
        v4GenericAction = ClientProxyKit.CLIENT_PROXY.createGenericAction();
        v4GenericAction.setName("");
        nameTextField.setText("");
        hitModifiersForge.setModifierList(v4GenericAction.getHitModifiers().getV4Modifier());
        missModifiersForge.setModifierList(v4GenericAction.getMissModifiers().getV4Modifier());
        super.actionPerformed(null);
    }

    protected abstract boolean checkIfExists();

    protected void marshal()
    {
        v4GenericAction.setName(nameTextField.getText());
        v4GenericAction.setType(typePicker.getText());
        v4GenericAction.setSubType(subTypePicker.getText());
        v4GenericAction.setSource(sourcePicker.getText());
        v4GenericAction.setDamageType(damageTypePicker.getText());
        v4GenericAction.setEffectType(effectTypePicker.getText());
        v4GenericAction.setActionType(actionTypePicker.getText());
        v4GenericAction.setAttackType(attackTypePicker.getText());
        v4GenericAction.setSecondaryAttackType(secondaryAttackTypePicker.getText());
        v4GenericAction.setTarget(targetPicker.getText());
        v4GenericAction.getAttack().setAttackerStateID(attackingStatePicker.getText());
        v4GenericAction.getAttack().setDefenderStateID(defendingStatePicker.getText());
        v4GenericAction.getAttack().setDisalowDiceRow(disallowDiceRollsBox.isSelected());
    }

    @Override
    public void setObject(Object obj)
    {
        v4GenericAction = ClientProxyKit.CLIENT_PROXY.getGenericAction((String) obj);
        nameTextField.setText(v4GenericAction.getName());

        typePicker.setText(v4GenericAction.getType());
        subTypePicker.setText(v4GenericAction.getSubType());
        sourcePicker.setText(v4GenericAction.getSource());
        damageTypePicker.setText(v4GenericAction.getDamageType());
        effectTypePicker.setText(v4GenericAction.getEffectType());
        actionTypePicker.setText(v4GenericAction.getActionType());
        attackTypePicker.setText(v4GenericAction.getAttackType());
        secondaryAttackTypePicker.setText(v4GenericAction.getSecondaryAttackType());
        targetPicker.setText(v4GenericAction.getTarget());

        hitModifiersForge.setModifierList(v4GenericAction.getHitModifiers().getV4Modifier());
        missModifiersForge.setModifierList(v4GenericAction.getMissModifiers().getV4Modifier());
        disallowDiceRollsBox.setSelected(v4GenericAction.getAttack().isDisalowDiceRow());
        attackingStatePicker.setText(v4GenericAction.getAttack().getAttackerStateID());
        defendingStatePicker.setText(v4GenericAction.getAttack().getDefenderStateID());
    }

    @Override
    protected void setNewName(String s)
    {
        v4GenericAction.setName(s);
    }
}