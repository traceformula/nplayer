package rpg.v4.client.gui.forge;

import rpg.swingx.JRoundedButton;
import rpg.swingx.JTransparentPanel;
import rpg.v4.client.gui.forge.modifier.ModifierForge;
import rpg.v4.client.gui.forge.modifier.ModifierForgePanel;
import rpg.v4.client.gui.sidepanel.SidePanel;
import rpg.v4.client.gui.sidepanel.SidePanelAssociate;
import rpg.v4.client.gui.util.factories.LabelFactory;
import rpg.v4.client.gui.util.listener.AncestorChangeListener;
import rpg.v4.middleware.util.collection.ObservableArrayList;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractForgePanel extends JTransparentPanel implements ActionListener, SidePanelAssociate, DocumentListener
{
    protected ModifierForge modifierForge;
    protected JTextField nameTextField;
    protected Box generalEntryListBox, modifierForgesListBox;
    protected Boolean caterForWeapons = false;

    public AbstractForgePanel(ObservableArrayList<String> itemList, String defaultModifierType, String headerName)
    {
        this(itemList, defaultModifierType, headerName, true);
    }

    public AbstractForgePanel(ObservableArrayList<String> itemList, String defaultModifierType, String headerName, boolean useModifierForge)
    {
        nameTextField = LabelFactory.createTextFieldWide("");
        nameTextField.getDocument().addDocumentListener(this);
        nameTextField.setColumns(20);

        generalEntryListBox = Box.createVerticalBox();
        generalEntryListBox.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));

        modifierForgesListBox = Box.createVerticalBox();

        addEntryPair("Name", nameTextField);

        JTransparentPanel generalEntryPanel = new JTransparentPanel();
        generalEntryPanel.add(generalEntryListBox, BorderLayout.WEST);

        JLabel forgeHeader = LabelFactory.createHeaderLargeLabel(headerName + " forge");

        JTransparentPanel contentPanel = new JTransparentPanel();
        contentPanel.add(generalEntryPanel, BorderLayout.NORTH);
        contentPanel.add(modifierForgesListBox, BorderLayout.CENTER);

        caterForWeapons = headerName.equals("Weapon");
        if (useModifierForge)
        {
            modifierForge = new ModifierForge();
            modifierForge.setCaterForWeapons(caterForWeapons);
            ModifierForgePanel modifierForgePanel = new ModifierForgePanel(modifierForge, defaultModifierType);
            modifierForgesListBox.add(modifierForgePanel);
        }

        JButton button = new JRoundedButton("Save", this);

        JTransparentPanel buttonPanel = new JTransparentPanel();
        buttonPanel.add(button, BorderLayout.WEST);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 11, 10, 10));

        JTransparentPanel p = new JTransparentPanel();

        p.add(forgeHeader, BorderLayout.NORTH);
        p.add(contentPanel, BorderLayout.CENTER);
        p.add(buttonPanel, BorderLayout.SOUTH);

        add(p, BorderLayout.NORTH);


        SidePanel<String> sidePanel = new SidePanel<String>(this, itemList, false);
        addPropertyChangeListener(new AncestorChangeListener(sidePanel));
    }

    protected void addEntryPair(String entryText, JComponent entryComponent)
    {
        JLabel headerLabel = LabelFactory.createHeaderLabel(entryText + ":");
        headerLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 8));
        JTransparentPanel headerPanel = new JTransparentPanel();
        headerPanel.add(headerLabel, BorderLayout.NORTH);

        Box b = Box.createHorizontalBox();
        b.add(headerPanel);
        b.add(entryComponent);
        b.setBorder(BorderFactory.createEmptyBorder(3, 0, 3, 0));

        JTransparentPanel p = new JTransparentPanel();
        p.add(b, BorderLayout.WEST);
        generalEntryListBox.add(p);
    }

    public abstract void setObject(Object name);

    public void addNew()
    {
        throw new UnsupportedOperationException("Forged cannot add new instances.");
    }

    public void insertUpdate(DocumentEvent e)
    {
        updateName(e);
    }

    public void removeUpdate(DocumentEvent e)
    {
        updateName(e);
    }

    public void changedUpdate(DocumentEvent e)
    {
        updateName(e);
    }

    private void updateName(DocumentEvent e)
    {
        String s = null;
        try
        {
            s = e.getDocument().getText(0, e.getDocument().getLength());
        } catch (BadLocationException e1)
        {
            e1.printStackTrace();
        }
        if (s != null && !"".equals(s))
        {
            setNewName(s);
        }

    }

    protected abstract void setNewName(String s);
}
