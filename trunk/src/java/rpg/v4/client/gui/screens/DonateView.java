package rpg.v4.client.gui.screens;

import com.centerkey.utils.BareBonesBrowserLaunch;
import rpg.swingx.JTransparentPanel;
import rpg.v4.client.gui.util.WelcomeMessage;
import rpg.v4.client.gui.util.factories.LabelFactory;
import rpg.v4.client.gui.util.image.ImageKit;
import rpg.v4.client.gui.util.listener.MousePickListener;
import rpg.v4.client.gui.GameMasterFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Donation page
 */
public class DonateView extends JTransparentPanel implements MouseListener
{
    private static final String DONATION_URL = "https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=2132399";
    private JLabel donateLabel, webSiteLabel;

    public DonateView()
    {
        String header = "Thank you for using nPlayer version "+ GameMasterFrame.VERSION+". Please donate towards the software. Thank you!";
        add(LabelFactory.createHeaderLargeLabel(header), BorderLayout.NORTH);

        ImageIcon icon = ImageKit.loadImageIcon("PayPal");

        donateLabel = new JLabel(icon);
        donateLabel.setToolTipText(DONATION_URL);
        donateLabel.addMouseListener(this);
        donateLabel.addMouseListener(new MousePickListener(donateLabel));
        add(donateLabel, BorderLayout.CENTER);

        String helperText = "Please donate towards the software by clicking the donate button above :-) I much appreciate it! Thanks. \n\n" +
                "Credits:\n";
        
        JTextArea area = WelcomeMessage.createTextArea();
        area.setText(helperText);

        Box vBox = Box.createVerticalBox();
        vBox.add(createLinks("Please also visit the web site: http://www.d20characters.com", "http://www.d20characters.com"));
        vBox.add(area);
        vBox.add(createLinks("  - Wizards of the Coast, for their great products.", "http://www.wizards.com/DnD"));
        vBox.add(createLinks("  - Sun, for Java.", "http://java.sun.com"));
        vBox.add(createLinks("  - The Gang of Four, for Design Patterns.", "http://en.wikipedia.org/wiki/Design_pattern"));
        vBox.add(createLinks("  - Ken, for his blog and MacWidgets", "http://explodingpixels.wordpress.com"));
        vBox.add(Box.createVerticalStrut(10));
        add(vBox, BorderLayout.SOUTH);
    }

    private JTextArea createLinks(String text, String url)
    {
        JTextArea textArea = WelcomeMessage.createTextArea(text);
        textArea.setToolTipText(url);
        textArea.addMouseListener(this);
        textArea.addMouseListener(new MousePickListener(textArea));
        return textArea;
    }

    public void mouseClicked(MouseEvent e)
    {
        JComponent source = (JComponent) e.getSource();
        String url = source.getToolTipText();
        BareBonesBrowserLaunch.openURL(url);
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
