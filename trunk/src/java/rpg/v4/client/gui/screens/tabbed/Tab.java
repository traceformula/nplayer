package rpg.v4.client.gui.screens.tabbed;

import static rpg.swingx.ColorConstants.*;
import rpg.swingx.JBorderLayoutPanel;
import rpg.v4.client.gui.control.navigation.NavigationEnum;
import rpg.v4.client.gui.screens.ContentOverview;
import rpg.v4.client.gui.util.factories.LabelFactory;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * A single tab with changing bottom border on active.
 */
public class Tab extends JBorderLayoutPanel implements MouseListener
{
    private Color activeBackground = SIDEBAR_DARK_LINE;
    private Color inactiveBackground = STANDARD_BACKGROUND;
    private Border inactiveBorder;
    private Border activeBorder;
    private NavigationEnum link;
    private ContentOverview contentOverview;
    private JLabel clickableLabel;

    public Tab(NavigationEnum link, ContentOverview contentOverview)
    {
        this.link = link;
        this.contentOverview = contentOverview;
        clickableLabel = LabelFactory.createNavTabLabel(link);
        this.add(clickableLabel, BorderLayout.CENTER);

        Border inactiveBottom = BorderFactory.createMatteBorder(0, 0, 1, 0, INFOBAR_DIVIDER_DARK_LINE);
        Border activeBottom = BorderFactory.createEmptyBorder(0, 0, 1, 0);
        Border divider = BorderFactory.createMatteBorder(0, 0, 0, 1, INFOBAR_DIVIDER_DARK_LINE);

        activeBorder = BorderFactory.createCompoundBorder(activeBottom, divider);
        inactiveBorder = BorderFactory.createCompoundBorder(inactiveBottom, divider);

        setBackground(inactiveBackground);
        setBorder(inactiveBorder);

        clickableLabel.addMouseListener(this);
    }

    public JLabel getClickableLabel()
    {
        return clickableLabel;
    }

    public void setActive(boolean isActive)
    {
        if (isActive)
        {
            setBackground(activeBackground);
            setBorder(activeBorder);
        } else
        {
            setBackground(inactiveBackground);
            setBorder(inactiveBorder);
        }
    }

    public void mouseClicked(MouseEvent e)
    {
        contentOverview.setContentView(link);
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
}
