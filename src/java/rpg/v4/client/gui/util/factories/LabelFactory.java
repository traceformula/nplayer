package rpg.v4.client.gui.util.factories;

import static rpg.swingx.ColorConstants.*;
import rpg.v4.client.gui.control.navigation.NavigationEnum;
import rpg.v4.client.gui.util.NumericTextField;
import rpg.v4.client.gui.util.image.ImageKit;
import rpg.v4.client.gui.util.listener.HoverTextListener;
import rpg.v4.client.gui.util.listener.NavigationEnumOnclickListener;
import rpg.v4.client.gui.util.listener.SelectedBGChangerListener;
import rpg.v4.client.gui.util.picker.impl.IntegerPicker;
import static rpg.v4.middleware.constants.FontConstants.*;
import rpg.v4.server.state.State;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * Helper class creating various labels.
 */
public class LabelFactory
{
    public static JLabel createNavMajorLabel(NavigationEnum link)
    {
        JLabel label = createNavSimpleLink(link);
        label.setBorder(BorderFactory.createEmptyBorder(3, 10, 3, 10));
        label.addMouseListener(new NavigationEnumOnclickListener(link));
        return label;
    }

    public static JLabel createNavTabLabel(NavigationEnum link)
    {
        JLabel label = createNavSimpleLink(link);
        label.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        return label;
    }

    public static JLabel createNavSimpleLink(NavigationEnum link)
    {
        return createNavSimpleLink(link.getDisplayName(), link.getIconName());
    }

    public static JLabel createNavSimpleLink(String linkName, String iconName)
    {
        ImageIcon icon = ImageKit.loadImageIcon(iconName);
        ImageIcon hoverIcon = ImageKit.loadImageIcon(iconName + "_hover");
        JLabel label = new JLabel(linkName, icon, JLabel.LEFT);
        label.setForeground(TEXT_NORMAL);
        label.setFont(PLAIN_FONT);
        label.addMouseListener(new HoverTextListener(icon, hoverIcon));
        label.addMouseListener(new SelectedBGChangerListener());
        return label;
    }

    public static JLabel createDarkLabel(NavigationEnum link)
    {
        ImageIcon icon = ImageKit.loadImageIcon(link.getIconName());
        ImageIcon hoverIcon = ImageKit.loadImageIcon(link.getIconName() + "_hover");
        JLabel label = new JLabel(link.getDisplayName(), icon, JLabel.LEFT);
        label.setForeground(INFOBAR_DIVIDER_DARK_LINE);
        label.setFont(BOLD_FONT);
        label.setBorder(BorderFactory.createEmptyBorder(3, 10, 3, 10));
        label.addMouseListener(new HoverTextListener(icon, hoverIcon));
        label.addMouseListener(new SelectedBGChangerListener());
        label.addMouseListener(new NavigationEnumOnclickListener(link));

        return label;
    }

    public static JLabel createLabel(NavigationEnum link, Color highLight)
    {
        ImageIcon icon = ImageKit.loadImageIcon(link.getIconName());
        ImageIcon hoverIcon = ImageKit.loadImageIcon(link.getIconName() + "_hover");
        JLabel label = new JLabel(link.getDisplayName(), icon, JLabel.LEFT);
        label.setForeground(TEXT_NORMAL);
        label.setFont(PLAIN_FONT);
        label.setBorder(BorderFactory.createEmptyBorder(3, 10, 3, 10));
        label.addMouseListener(new HoverTextListener(icon, hoverIcon));
        label.addMouseListener(new SelectedBGChangerListener(highLight));
        label.addMouseListener(new NavigationEnumOnclickListener(link));

        return label;
    }

    public static JLabel createHeaderLargeLabel(String header)
    {
        JLabel label = new JLabel(header, JLabel.LEFT);
        label.setForeground(TEXT_NORMAL);
        label.setFont(HEADER_FONT_LARGE);
        label.setBorder(BorderFactory.createEmptyBorder(3, 10, 3, 10));

        return label;
    }

    public static JLabel createHeaderLabelNonPadded(String header)
    {
        JLabel label = createHeaderLabel(header);
        label.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
        return label;
    }

    public static JLabel createHeaderLabel(String header)
    {
        return createHeaderLabel(header, 3, 3);
    }

    public static JLabel createHeaderLabel(String header, int topPadding, int bottomPadding)
    {
        JLabel label = new JLabel(header, JLabel.LEFT);
        label.setForeground(TEXT_NORMAL);
        label.setFont(HEADER_FONT_NORMAL);
        label.setBorder(BorderFactory.createEmptyBorder(topPadding, 10, bottomPadding, 10));

        return label;
    }

    public static JLabel createDarkHeaderLabel(String header)
    {
        JLabel label = new JLabel(header, JLabel.LEFT);
        label.setForeground(BLACK);
        label.setFont(HEADER_FONT_NORMAL);
        label.setBorder(BorderFactory.createEmptyBorder(3, 10, 3, 10));

        return label;
    }

    public static JLabel createDarkBoldHeaderLabel(String header)
    {
        JLabel label = new JLabel(header, JLabel.LEFT);
        label.setForeground(BLACK);
        label.setFont(HEADER_FONT_BOLD);
        label.setBorder(BorderFactory.createEmptyBorder(3, 10, 3, 10));

        return label;
    }

    public static JLabel createSmallLightHeaderLabel(String header)
    {
        JLabel label = createSmallDarkHeaderLabel(header);
        label.setForeground(TEXT_NORMAL);
        return label;
    }

    public static JLabel createSmallDarkHeaderLabel(String header)
    {
        JLabel label = new JLabel(header, JLabel.LEFT);
        label.setForeground(BLACK);
        label.setFont(HEADER_FONT_SMALL);
        return label;
    }


    public static JLabel createNumericalModifierLabel(String header)
    {
        JLabel label = new JLabel(header, JLabel.CENTER)
        {
            @Override
            public void setText(String text)
            {
                super.setText(text);

                if ("0".equals(text))
                {
                    setForeground(TEXT_SHADE);
                } else
                {
                    setForeground(TEXT_HIGHLIGHT_WHITE);
                }
            }
        };
        label.setForeground(TEXT_NORMAL);
        label.setFont(HEADER_FONT_NORMAL);
        label.setBorder(BorderFactory.createEmptyBorder(0, 3, 0, 3));

        Dimension size = new Dimension(40, 20);
        label.setPreferredSize(size);
        label.setMaximumSize(size);
        label.setMinimumSize(size);

        return label;
    }

    public static JLabel createNumericalPickerLabel(State state)
    {
        JLabel label = new IntegerPicker(state);
        //label.setForeground(TEXT_NORMAL);
        //label.setFont(HEADER_FONT_NORMAL);
        //label.setBorder(BorderFactory.createEmptyBorder(0, 3, 0, 3));

        Dimension size = new Dimension(40, 20);
        label.setPreferredSize(size);
        label.setMaximumSize(size);
        label.setMinimumSize(size);

        return label;
    }

    public static JTextField createTextFieldWide(String header)
    {
        return createTextFieldWide(header, false);
    }

    public static JTextField createTextFieldWide(String header, boolean isNumeric)
    {
        JTextField label = isNumeric ? new NumericTextField(header, 20) : new JTextField(header, 20);
        label.setFont(HEADER_FONT_NORMAL);

        Border emptptyBorder = BorderFactory.createEmptyBorder(0, 5, 0, 5);
        Border compound = BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(BLACK), emptptyBorder);
        label.setBorder(compound);
        return label;
    }

    public static JLabel createDarkNumericalModifierLabel(String header)
    {
        JLabel label = createNumericalModifierLabel(header);
        label.setForeground(BLACK);
        label.setFont(HEADER_FONT_SMALL);
        label.setToolTipText(header);
        return label;
    }

    public static JLabel createInfoLabel(String infoText)
    {
        ImageIcon icon = ImageKit.loadImageIcon("info2");
        JLabel label = new JLabel(infoText, icon, JLabel.LEFT);
        label.setForeground(TEXT_NORMAL);
        label.setFont(PLAIN_FONT);
        return label;
    }
}
