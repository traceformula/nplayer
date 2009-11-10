package rpg.v4.client.gui.screens;

import rpg.swingx.ColorConstants;
import rpg.swingx.JRoundedButton;
import rpg.swingx.JTransparentPanel;
import rpg.v4.client.gui.control.contentview.ContentManager;
import rpg.v4.client.gui.control.navigation.NavigationEnum;
import rpg.v4.client.gui.util.factories.LabelFactory;
import rpg.v4.middleware.constants.FontConstants;
import rpg.v4.middleware.util.SimpleMail;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Displays forms for a bug entry and a send button so it can be sent to myself using third party email library.
 */
public class BugReportView extends JTransparentPanel implements ActionListener
{
    private JTextArea textArea;
    private JTextArea reproduceTextArea;
    private JTextField emailField;

    public BugReportView(String header)
    {
        this.add(LabelFactory.createHeaderLargeLabel(header), BorderLayout.NORTH);

        String textAreaHeader = "Your message:";
        String reproduceTextAreaHeader = "How to reproduce the bug:";
        textArea = createTextArea();
        reproduceTextArea = createTextArea();

        emailField = new JTextField();
        emailField = createFormEntry(emailField, " Your email address, if you would like a reply:");

        Box panel = Box.createVerticalBox();
        panel.add(createFormEntry(textArea, textAreaHeader));
        panel.add(Box.createVerticalStrut(10));
        panel.add(createFormEntry(reproduceTextArea, reproduceTextAreaHeader));
        panel.add(Box.createVerticalStrut(10));
        panel.add(emailField);
        panel.add(Box.createVerticalStrut(10));

        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton button = new JRoundedButton("Send");
        button.addActionListener(this);
        panel.add(button);

        this.add(panel, BorderLayout.CENTER);

    }

    private JTextArea createTextArea()
    {
        JTextArea textArea = new JTextArea(10, 30);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        return textArea;
    }

    private JScrollPane createFormEntry(JTextArea textArea, String textAreaHeader)
    {
        Border titledBorder = new TitledBorder(null, textAreaHeader, TitledBorder.LEFT,
                TitledBorder.ABOVE_TOP, FontConstants.HEADER_FONT_NORMAL, ColorConstants.TEXT_NORMAL);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBorder(titledBorder);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        textArea.setOpaque(false);
        textArea.setForeground(ColorConstants.TEXT_NORMAL);
        textArea.setCaretColor(ColorConstants.TEXT_NORMAL);
        return scrollPane;
    }

    private JTextField createFormEntry(JTextField emailField, String textAreaHeader)
    {
        Border titledBorder = new TitledBorder(null, textAreaHeader, TitledBorder.LEFT,
                TitledBorder.ABOVE_TOP, FontConstants.HEADER_FONT_NORMAL, ColorConstants.TEXT_NORMAL);
        emailField.setBorder(titledBorder);
        emailField.setOpaque(false);
        emailField.setForeground(ColorConstants.TEXT_NORMAL);
        emailField.setCaretColor(ColorConstants.TEXT_NORMAL);
        return emailField;
    }

    public void actionPerformed(ActionEvent e)
    {
        String sender = emailField.getText();
        String message = textArea.getText();
        String reproduceMessage = reproduceTextArea.getText();
        message = "From: '" + sender + "'\n\nMessage:\n" + message + "\n\nHow to reproduce:\n"+reproduceMessage;
        if ("".equals(message))
        {
            message += "\nNo feedback entered";
        }

        try
        {
            SimpleMail.sendResults(message);
        } catch (Exception ex)
        {
            ex.printStackTrace();
        } finally {
            ContentManager.display(NavigationEnum.DONATE);
        }
    }
}
