package rpg.v4.client.gui.forge.modifier;

import rpg.swingx.JRoundedButton;
import rpg.swingx.JTransparentPanel;
import rpg.v4.client.gui.util.factories.LabelFactory;
import rpg.v4.middleware.jaxb.V4Modifier;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Allows new modifiers to be created.
 */
public class ModifierForgePanel extends JTransparentPanel implements MouseListener, Observer
{
    private ModifierForge modifierForge;
    private String defaultModifier;
    private Box box;
    private List<AbstractModifierPanel> rowList;
    private String canonicalName = "rpg.v4.client.gui.forge.modifier.ModifierPanel";
    private String diceCanonicalName = "rpg.v4.client.gui.forge.modifier.DiceModifierPanel";
    private JRoundedButton addDiceModifierButton;
    private JLabel headerLabel;

    public ModifierForgePanel(ModifierForge modifierForge, String defaultModifier, String headerText)
    {
        this.modifierForge = modifierForge;
        this.defaultModifier = defaultModifier;
        rowList = new ArrayList<AbstractModifierPanel>();
        box = Box.createVerticalBox();
        box.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 5));

        if (modifierForge.isCaterForWeapons())
        {
            System.out.println("Cater for weapons: " + modifierForge.isCaterForWeapons());
            canonicalName = "rpg.v4.client.gui.forge.modifier.WeaponModifierPanel";
        }

        JTransparentPanel verticalBoxPanel = new JTransparentPanel();
        verticalBoxPanel.add(box, BorderLayout.NORTH);

        JButton addModifierButton = new JRoundedButton("+ Modifier");
        addModifierButton.addMouseListener(this);

        addDiceModifierButton = new JRoundedButton("+ Dice Modifier");
        addDiceModifierButton.addMouseListener(this);

        Box hBox = Box.createHorizontalBox();
        hBox.add(addModifierButton);
        hBox.add(Box.createHorizontalStrut(10));
        hBox.add(addDiceModifierButton);

        JTransparentPanel buttonPanel = new JTransparentPanel();
        buttonPanel.add(hBox, BorderLayout.WEST);

        JTransparentPanel modifierPanel = new JTransparentPanel();
        modifierPanel.add(buttonPanel, BorderLayout.NORTH);
        modifierPanel.add(verticalBoxPanel, BorderLayout.SOUTH);
        modifierPanel.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0));

        createModifierRow(false);

        headerLabel = LabelFactory.createHeaderLabelNoSidePadding(headerText);
        this.add(headerLabel, BorderLayout.NORTH);
        this.add(modifierPanel, BorderLayout.CENTER);
        this.setBorder(BorderFactory.createEmptyBorder(10, 15, 0, 5));
        modifierForge.addObserver(this);
    }

    public void remove(AbstractModifierPanel p)
    {
        modifierForge.remove(p.getModifier());
        box.remove(p);
        revalidate();
        repaint();
    }

    public void mouseClicked(MouseEvent e)
    {
        boolean includeDice = e.getSource() == addDiceModifierButton;
        createModifierRow(includeDice);
    }

    private void createModifierRow(boolean includeDice)
    {
        V4Modifier modifier = modifierForge.createModifier(includeDice);
        modifier.setV4ModifierType(defaultModifier);
        modifier.setSourceID(defaultModifier);

        // If dice are included, it's most likely going to be a wounds type of modifier
        if (includeDice)
        {
            modifier.setTargetID("Wounds");
        } else
        {
            modifier.setTargetID("Strength");
        }

        modifier.setModifier("2");

        AbstractModifierPanel p = createModifierPanel(modifier);
        rowList.add(p);
        box.add(p);
        revalidate();
        repaint();
    }

    public void update(Observable o, Object arg)
    {
        if (arg instanceof List)
        {
            for (AbstractModifierPanel p : rowList)
            {
                box.remove(p);
            }

            List<V4Modifier> modifierList = (List<V4Modifier>) arg;
            for (V4Modifier modifier : modifierList)
            {
                AbstractModifierPanel p = createModifierPanel(modifier);
                rowList.add(p);
                box.add(p);
            }
            revalidate();
            repaint();
        } else
        {
            System.out.println("Modifer forge - strange place to be in if/else");
        }
    }

    private AbstractModifierPanel createModifierPanel(V4Modifier modifier)
    {
        boolean isDiceModifier = null != modifier.getDiceModifier();
        String classPanelName = isDiceModifier ? diceCanonicalName : canonicalName;
        try
        {
            Class modifierPanelClass = ClassLoader.getSystemClassLoader().loadClass(classPanelName);
            Constructor stateConstructor = modifierPanelClass.getConstructor(
                    modifier.getClass(), ModifierForgePanel.class);
            Object[] args = new Object[]{modifier, this};
            AbstractModifierPanel modifierRowPanel = (AbstractModifierPanel) stateConstructor.newInstance(args);
            return modifierRowPanel;
        } catch (InstantiationException e)
        {
            e.printStackTrace();
        } catch (IllegalAccessException e)
        {
            e.printStackTrace();
        } catch (ClassNotFoundException e)
        {
            throw new IllegalArgumentException(e);
        } catch (NoSuchMethodException e)
        {
            e.printStackTrace();
        } catch (InvocationTargetException e)
        {
            e.printStackTrace();
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }

    public void mousePressed(MouseEvent e)
    {
    }

    public void mouseReleased(MouseEvent e)
    {
    }

    public void mouseEntered(MouseEvent e)
    {
    }

    public void mouseExited(MouseEvent e)
    {
    }

    public void setHeader(String headerText)
    {
        headerLabel.setText(headerText);
    }
}
