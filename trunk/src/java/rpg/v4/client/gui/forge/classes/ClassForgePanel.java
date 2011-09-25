package rpg.v4.client.gui.forge.classes;

import rpg.swingx.JDarkCheckBox;
import rpg.v4.client.gui.forge.AbstractForgePanel;
import rpg.v4.client.proxy.ClientProxyKit;
import rpg.v4.middleware.jaxb.V4Class;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * A class forge
 */
public class ClassForgePanel extends AbstractForgePanel
{
    private V4Class characterClass;

    private JDarkCheckBox paragonPath;

    public ClassForgePanel()
    {
        super(ClientProxyKit.CLIENT_PROXY.getAllAvailableClasses(), "Untyped", "Class", true, false);
        characterClass = ClientProxyKit.CLIENT_PROXY.createClass();
        ownerModifierForge.setModifierList(characterClass.getV4ModifierList().getV4Modifier());
        initialize();
    }

    private void initialize()
    {
        paragonPath = new JDarkCheckBox("Paragon path");
        generalEntryListBox.add(paragonPath);
    }

    public void setObject(Object obj)
    {
        String name = (String) obj;
        characterClass = ClientProxyKit.CLIENT_PROXY.getCharacterClass(name);
        nameTextField.setText(characterClass.getName());
        paragonPath.setSelected(characterClass.isParagonPath());
        ownerModifierForge.setModifierList(characterClass.getV4ModifierList().getV4Modifier());
    }

    protected void setNewName(String s)
    {
        characterClass.setName(s);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        String messages = "";

        String name = characterClass.getName();

        if (null == name || name.length() == 0)
        {
            messages += "\n - Enter a name.";
        }

        if (ClientProxyKit.CLIENT_PROXY.getAllAvailableClasses().contains(name))
        {
            messages += "\n - A class with this name already exists. Please change the name.";
        }

        if (messages.length() != 0)
        {
            JOptionPane.showMessageDialog(this, "Could not create the race:" + messages);
            return;
        }

        characterClass.setParagonPath(paragonPath.isSelected());
        
        ClientProxyKit.CLIENT_PROXY.saveClass(characterClass);
        characterClass = ClientProxyKit.CLIENT_PROXY.createClass();
        characterClass.setName("");
        nameTextField.setText("");
        ownerModifierForge.setModifierList(characterClass.getV4ModifierList().getV4Modifier());
        super.actionPerformed(null);
    }
}
