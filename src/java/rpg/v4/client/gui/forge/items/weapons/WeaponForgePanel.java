package rpg.v4.client.gui.forge.items.weapons;

import rpg.v4.client.gui.forge.AbstractForgePanel;
import rpg.v4.client.gui.util.factories.LabelFactory;
import rpg.v4.client.gui.util.picker.impl.IntegerPicker;
import rpg.v4.client.gui.util.picker.impl.StringPicker;
import rpg.v4.client.proxy.ClientProxyKit;
import rpg.v4.middleware.constants.StateIDs;
import rpg.v4.middleware.jaxb.V4Modifier;
import rpg.v4.middleware.jaxb.V4Weapon;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Allows specification of race name and modifiers.
 */
public class WeaponForgePanel extends AbstractForgePanel
{
    private V4Weapon weapon;
    private StringPicker baseWeaponPicker;
    private StringPicker typePicker;
    private StringPicker categoryPicker;
    private StringPicker groupPicker;
    private IntegerPicker damageNumDicePicker;
    private IntegerPicker damageDiePicker;
    private IntegerPicker proficiencyBonusPicker;
    private IntegerPicker numOfHandsPicker;
    private JTextField priceTextField;
    private JTextField weightTextField;

    public WeaponForgePanel()
    {
        super(ClientProxyKit.CLIENT_PROXY.getAvailableWeapons(), "Item", "Weapon");
        weapon = ClientProxyKit.CLIENT_PROXY.createWeapon();
        modifierForge.setModifierList(weapon.getV4ModifierList().getV4Modifier());
        initialize();
    }

    public void actionPerformed(ActionEvent e)
    {
        String messages = "";
        setNewName(nameTextField.getText());

        String name = weapon.getName();

        if (null == name || name.length() == 0)
        {
            messages += "\n - Enter a name.";
        }

        if (messages.length() != 0)
        {
            JOptionPane.showMessageDialog(this, "Could not create the weapon:" + messages);
            return;
        }

        marshal();

        ClientProxyKit.CLIENT_PROXY.saveWeapon(weapon);
        weapon = ClientProxyKit.CLIENT_PROXY.createWeapon();
        weapon.setName("");
        nameTextField.setText("");
        weightTextField.setText("");
        priceTextField.setText("");
        modifierForge.setModifierList(weapon.getV4ModifierList().getV4Modifier());
        super.actionPerformed(null);
    }

    private void marshal()
    {
        weapon.setName(nameTextField.getText());

        if (!"None".equals(baseWeaponPicker.getText()))
        {
            weapon.setBaseWeapon(baseWeaponPicker.getText());
        } else
        {
            weapon.setBaseWeapon("");
        }

        weapon.setProficiencyBonus(Integer.valueOf(proficiencyBonusPicker.getText()));
        weapon.getDamage().setNumDice(Integer.valueOf(damageNumDicePicker.getText()));
        weapon.getDamage().setDiceType(Integer.valueOf(damageDiePicker.getText()));
        weapon.setPrice(Integer.valueOf(priceTextField.getText()));
        weapon.setWeight(Double.valueOf(weightTextField.getText()));
        weapon.setGroup(groupPicker.getText());
        weapon.setType(typePicker.getText());
        weapon.setCategory(categoryPicker.getText());
        weapon.setHandRequirement(Integer.valueOf(numOfHandsPicker.getText()));

        // If adding modifier to this weapons BAB, update the BAB to this weapons name
        for (V4Modifier modifier : modifierForge.getModifierList())
        {
            if (modifier.getTargetID().equals(StateIDs.THIS_WEAPON_BAB.toString()))
            {
                modifier.setTargetID("BAB " + weapon.getName());
            }
        }
    }

    @Override
    public void setObject(Object name)
    {
        weapon = ClientProxyKit.CLIENT_PROXY.getWeapon((String) name);
        nameTextField.setText(weapon.getName());
        if ("".equals(weapon.getBaseWeapon()))
        {
            baseWeaponPicker.setText("None");
        } else
        {
            baseWeaponPicker.setText(weapon.getBaseWeapon());
        }

        proficiencyBonusPicker.setText(String.valueOf(weapon.getProficiencyBonus()));
        damageNumDicePicker.setText(String.valueOf(weapon.getDamage().getNumDice()));
        damageDiePicker.setText(String.valueOf(weapon.getDamage().getDiceType()));
        priceTextField.setText(String.valueOf(weapon.getPrice()));
        weightTextField.setText(String.valueOf(weapon.getWeight()));
        groupPicker.setText(weapon.getGroup());
        typePicker.setText(weapon.getType());
        categoryPicker.setText(weapon.getCategory());
        numOfHandsPicker.setText(String.valueOf(weapon.getHandRequirement()));

        // Have to check if there's any modifiers to this weapons BAB
        // If adding modifier to this weapons BAB, update the BAB to this weapons name
        for (V4Modifier modifier : weapon.getV4ModifierList().getV4Modifier())
        {
            if (modifier.getTargetID().equals("BAB " + weapon.getName()))
            {
                modifier.setTargetID(StateIDs.THIS_WEAPON_BAB.toString());
            }
        }

        modifierForge.setModifierList(weapon.getV4ModifierList().getV4Modifier());
    }

    @Override
    protected void setNewName(String s)
    {
        weapon.setName(s);
    }

    private void initialize()
    {
        List<String> choosableBases = new ArrayList<String>();
        choosableBases.add("None");
        for (String s : ClientProxyKit.CLIENT_PROXY.getAvailableWeapons())
        {
            choosableBases.add(s);
        }

        baseWeaponPicker = new StringPicker(choosableBases);
        addEntryPair("Base weapon", baseWeaponPicker);

        damageNumDicePicker = new IntegerPicker(1, 5);
        addEntryPair("Damage dice (number of)", damageNumDicePicker);

        Integer[] dieList = new Integer[]{4, 6, 8, 10, 12, 20};
        damageDiePicker = new IntegerPicker(Arrays.asList(dieList));
        addEntryPair("Damage die (d4, d6, etc)", damageDiePicker);

        proficiencyBonusPicker = new IntegerPicker(0, 5);
        addEntryPair("Proficiency bonus", proficiencyBonusPicker);

        priceTextField = LabelFactory.createTextFieldWide("", true);
        priceTextField.setColumns(6);
        addEntryPair("Price in gp", priceTextField);

        weightTextField = LabelFactory.createTextFieldWide("");
        weightTextField.setColumns(10);
        addEntryPair("Weight", weightTextField);

        groupPicker = new StringPicker("Axe",
                "Bow",
                "Crossbow",
                "Flail",
                "Hammer",
                "Heavy Blade",
                "Light Blade",
                "Mace",
                "Pick",
                "Polearm",
                "Sling",
                "Spear",
                "Staff",
                "Unarmed"
        );
        addEntryPair("Group", groupPicker);

        typePicker = new StringPicker("Melee", "Ranged");
        addEntryPair("Type", typePicker);

        categoryPicker = new StringPicker("Simple", "Military", "Superior");
        addEntryPair("Category", categoryPicker);

        numOfHandsPicker = new IntegerPicker(1, 2);
        addEntryPair("Number of hands required", numOfHandsPicker);
    }
}