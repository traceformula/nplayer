package rpg.v4.client.gui.vaults.entries;

import rpg.swingx.JGradientPanel;
import rpg.v4.client.gui.util.factories.LabelFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Displays header information in a fixed size tag
 */
public abstract class VaultTag extends JGradientPanel implements MouseListener
{
    private static final Dimension fixedSize = new Dimension(120, 25);

    protected JLabel headerLabel;

    public VaultTag(String header, Color roundedLineBorder, Color topLine, Color bottomGradient, Color topGradient)
    {
        super(roundedLineBorder, topLine, topGradient, bottomGradient);

        setPreferredSize(fixedSize);
        setMinimumSize(fixedSize);
        setMaximumSize(fixedSize);

        headerLabel = LabelFactory.createDarkHeaderLabel(header);
        add(headerLabel, BorderLayout.CENTER);
        addMouseListener(this);
    }

    public void mouseEntered(MouseEvent e)
    {
    }

    public void mouseExited(MouseEvent e)
    {
    }

    public void mousePressed(MouseEvent e)
    {
    }

    public void mouseReleased(MouseEvent e)
    {
    }
}
