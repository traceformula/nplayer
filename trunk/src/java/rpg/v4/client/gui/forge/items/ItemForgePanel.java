package rpg.v4.client.gui.forge.items;

import rpg.v4.client.gui.forge.AbstractForgePanel;
import rpg.v4.client.gui.util.factories.LabelFactory;
import rpg.v4.client.gui.util.picker.impl.StringPicker;
import rpg.v4.client.proxy.ClientProxyKit;
import rpg.v4.middleware.jaxb.V4Item;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Allows specification of items, magical and non-magical.
 */
public class ItemForgePanel extends AbstractForgePanel implements PropertyChangeListener
{
    private V4Item v4Item;
    private StringPicker baseItemPicker;
    private JTextField priceTextField;
    private JTextField weightTextField;
    private StringPicker typePicker, subTypePicker;
    private StringPicker categoryPicker;
    private String oldSubTypeValue = "", oldCategoryValue = "";

    public ItemForgePanel()
    {
        super(ClientProxyKit.CLIENT_PROXY.getAvailableItems(), "Item", "Item");
        v4Item = ClientProxyKit.CLIENT_PROXY.createItem();
        modifierForge.setModifierList(v4Item.getV4ModifierList().getV4Modifier());
        initialize();
    }

    private void initialize()
    {

        String[] itemTypes = {"Armor", "Amulet", "Belt", "Boots", "Bracelets", "Bracers", "Brooch", "Cape", "Cloak",
                "Gauntlets", "Glasses", "Gloves", "Hat", "Headband", "Helmet", "Lens", "Mantle",
                "Medallion", "Necklace", "Orb", "OtherNonSpecific", "Periapt", "Phylactery", "Ring",
                "Sandals", "Scarab", "Shirt", "Shoes", "Vest", "Vestment"};
        typePicker = new StringPicker(itemTypes);
        typePicker.addPropertyChangeListener("text", this);
        addEntryPair("Type", typePicker);

        subTypePicker = new StringPicker("Light", "Heavy");
        addEntryPair("Sub type", subTypePicker);

        categoryPicker = new StringPicker("Cloth Armor", "Leather Armor", "Hide Armor",
                "Chainmail", "Scale Armor", "Plate Armor");
        addEntryPair("Category", categoryPicker);

        List<String> choosableBases = new ArrayList<String>();
        choosableBases.add("None");
        for (String s : ClientProxyKit.CLIENT_PROXY.getAvailableItems())
        {
            choosableBases.add(s);
        }
        baseItemPicker = new StringPicker(choosableBases);
        addEntryPair("Base item", baseItemPicker);

        priceTextField = LabelFactory.createTextFieldWide("", true);
        priceTextField.setColumns(6);
        addEntryPair("Price in gp", priceTextField);

        weightTextField = LabelFactory.createTextFieldWide("");
        weightTextField.setColumns(10);
        addEntryPair("Weight", weightTextField);

    }

    public void actionPerformed(ActionEvent e)
    {
        String messages = "";
        setNewName(nameTextField.getText());
        String name = v4Item.getName();

        if (null == name || name.length() == 0)
        {
            messages += "\n - Enter a name.";
        }

        if (messages.length() != 0)
        {
            JOptionPane.showMessageDialog(this, "Could not create the armor:" + messages);
            return;
        }

        marshal();

        ClientProxyKit.CLIENT_PROXY.saveItem(v4Item);
        v4Item = ClientProxyKit.CLIENT_PROXY.createItem();
        v4Item.setName("");
        nameTextField.setText("");
        weightTextField.setText("");
        priceTextField.setText("");
        modifierForge.setModifierList(v4Item.getV4ModifierList().getV4Modifier());
    }

    private void marshal()
    {
        v4Item.setName(nameTextField.getText());

        if (!"None".equals(baseItemPicker.getText()))
        {
            v4Item.setBaseItem(baseItemPicker.getText());
        } else
        {
            v4Item.setBaseItem("");
        }

        v4Item.setPrice(Integer.valueOf(priceTextField.getText()));
        v4Item.setWeight(Double.valueOf(weightTextField.getText()));
        v4Item.setType(typePicker.getText());
        boolean isArmorType = "Armor".equals(typePicker.getText());
        if (isArmorType)
        {
            v4Item.setSubType(subTypePicker.getText());
            v4Item.setCategory(categoryPicker.getText());
        } else
        {
            v4Item.setSubType("");
            v4Item.setCategory("");
        }
    }

    @Override
    public void setObject(Object name)
    {
        System.out.println("Setting new object!!");
        v4Item = ClientProxyKit.CLIENT_PROXY.getArmor( (String) name);
        nameTextField.setText(v4Item.getName());

        boolean isArmorType = "Armor".equals(typePicker.getText());
        subTypePicker.setEnabled(isArmorType);
        categoryPicker.setEnabled(isArmorType);
        baseItemPicker.setEnabled(isArmorType);

        if ("".equals(v4Item.getBaseItem()))
        {
            baseItemPicker.setText("None");
        } else
        {
            baseItemPicker.setText(v4Item.getBaseItem());
        }

        priceTextField.setText(String.valueOf(v4Item.getPrice()));
        weightTextField.setText(String.valueOf(v4Item.getWeight()));
        typePicker.setText(v4Item.getType());
        subTypePicker.setText(v4Item.getSubType());
        categoryPicker.setText(v4Item.getCategory());

        modifierForge.setModifierList(v4Item.getV4ModifierList().getV4Modifier());
    }

    @Override
    protected void setNewName(String s)
    {
        v4Item.setName(s);
    }

    public void propertyChange(PropertyChangeEvent evt)
    {
        boolean isArmorType = "Armor".equals(typePicker.getText());
        subTypePicker.setEnabled(isArmorType);
        categoryPicker.setEnabled(isArmorType);
        baseItemPicker.setEnabled(isArmorType);

        if (!isArmorType)
        {
            if (oldSubTypeValue.equals("") && oldCategoryValue.equals(""))
            {
                oldSubTypeValue = subTypePicker.getText();
                oldCategoryValue = categoryPicker.getText();
            }

            subTypePicker.setText("");
            categoryPicker.setText("");
            baseItemPicker.setText("None");
        } else
        {
            subTypePicker.setText(oldSubTypeValue);
            categoryPicker.setText(oldCategoryValue);
            v4Item.setBaseItem(baseItemPicker.getText());

            oldSubTypeValue = "";
            oldCategoryValue = "";
        }
    }
}