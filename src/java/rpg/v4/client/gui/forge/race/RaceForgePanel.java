package rpg.v4.client.gui.forge.race;

import rpg.swingx.JTransparentPanel;
import rpg.v4.client.gui.forge.AbstractForgePanel;
import rpg.v4.client.gui.util.factories.LabelFactory;
import rpg.v4.client.proxy.ClientProxyKit;
import rpg.v4.middleware.jaxb.V4BodyPart;
import rpg.v4.middleware.jaxb.V4Race;
import rpg.v4.middleware.util.deepcopy.FastDeepCopy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

/**
 * Allows specification of race name and modifiers.
 */
public class RaceForgePanel extends AbstractForgePanel
{
    private V4Race race;
    private Box bodyPartListingsBox;

    public RaceForgePanel()
    {
        super(ClientProxyKit.CLIENT_PROXY.getAvailableRaces(), "Racial", "Race");
        race = ClientProxyKit.CLIENT_PROXY.createRace();
        modifierForge.setModifierList(race.getV4ModifierList().getV4Modifier());

        bodyPartListingsBox = Box.createVerticalBox();
        JTransparentPanel bodyPanel = new JTransparentPanel();
        bodyPanel.add(bodyPartListingsBox, BorderLayout.NORTH);
        addEntryPair("Equipment slots", bodyPanel);
        displayBodyParts(race.getV4BodyPart());
    }

    public void actionPerformed(ActionEvent e)
    {
        String messages = "";
        setNewName(nameTextField.getText());
        String name = race.getName();

        if (null == name || name.length() == 0)
        {
            messages += "\n - Enter a name.";
        }

        if (messages.length() != 0)
        {
            JOptionPane.showMessageDialog(this, "Could not create the race:" + messages);
            return;
        }

        V4Race raceToSave = ClientProxyKit.CLIENT_PROXY.getRace(name);
        if (raceToSave != null)
            raceToSave.setV4ModifierList(race.getV4ModifierList());
        else
            raceToSave = this.race;

        ClientProxyKit.CLIENT_PROXY.saveRace(raceToSave);
        
        race = ClientProxyKit.CLIENT_PROXY.createRace();
        race.setName("");
        nameTextField.setText("");
        modifierForge.setModifierList(race.getV4ModifierList().getV4Modifier());
        super.actionPerformed(null);
    }

    @Override
    public void setObject(Object name)
    {
        V4Race raceToLoad = ClientProxyKit.CLIENT_PROXY.getRace((String) name);
        // Clone it to avoid wrong saving upon name change.
        this.race = (V4Race) FastDeepCopy.deepCopy(raceToLoad);
        nameTextField.setText(race.getName());
        modifierForge.setModifierList(race.getV4ModifierList().getV4Modifier());

        bodyPartListingsBox.removeAll();
        List<V4BodyPart> bodyPartList = raceToLoad.getV4BodyPart();
        displayBodyParts(bodyPartList);
    }

    private void displayBodyParts(List<V4BodyPart> bodyPartList)
    {
        for (V4BodyPart bodyPart : bodyPartList)
        {
            String boydPartString = bodyPart.getName() + ":    ";
            for (V4BodyPart.CompatibleItem compatible : bodyPart.getCompatibleItem())
            {
                boydPartString += compatible.getType() + ", ";
            }
            boydPartString = boydPartString.substring(0, boydPartString.length() - 2);

            JLabel label = LabelFactory.createHeaderLabel(boydPartString);
            label.setToolTipText("Compatible item types for \""+bodyPart.getName()+"\"");
            bodyPartListingsBox.add(label);
        }
    }

    @Override
    protected void setNewName(String s)
    {
        race.setName(s);
    }
}
