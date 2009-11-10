package rpg.v4.client.gui.util;

import com.explodingpixels.macwidgets.HudWindow;
import rpg.swingx.ColorConstants;
import rpg.swingx.JTransparentScrollPane;
import rpg.v4.middleware.util.FileKit;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: fjelds
 * Date: Sep 5, 2009
 * Time: 10:42:25 AM
 * <p/>
 * Displays a simple welcome message
 */
public class WelcomeMessage extends HudWindow
{
    private static String FILE_NAME = "misc/welcomeMessage.xml";

    public WelcomeMessage(JFrame frame)
    {
        super("Welcome Wanderer!");
        File shownIndicator = FileKit.getInstance().createFile(FILE_NAME);
        try
        {
            shownIndicator.createNewFile();
        } catch (IOException e)
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }


        JTextArea textArea = createTextArea();
        String welcomeText = "Welcome to another release of nPlayer!\n\n" +
                "This release includes more functionality around battles, creating actions, feats and powers, and general updates to the charater sheet. Be beware, this release is NOT COMPATIBLE with the previous releases so do NOT reuse your save files.\n\n" +
                "I hope you will enjoy it Ð please send feedback if there are anything you would like to see add, changed. etc. using the bug report tool bottom right.\n\n" +
                "You will notice that certain things are missing, such as proper damage set up in action and control of it.\n\n" +
                "Thanks\n\n" +
                "Sven\n" +
                "http://www.d20characters.com  - not updated yet but it will.";
        textArea.setText(welcomeText);

        JScrollPane scrollPane = new JTransparentScrollPane(textArea);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        getJDialog().setSize(450, 400);
        getJDialog().setLocationRelativeTo(frame);
        getJDialog().setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getJDialog().setVisible(true);
    }


    public static JTextArea createTextArea()
    {
        JTextArea editorPane = new JTextArea();
        editorPane.setOpaque(false);
        editorPane.setForeground(ColorConstants.TEXT_NORMAL);
        editorPane.setCaretColor(ColorConstants.TEXT_NORMAL);
        editorPane.setOpaque(false);
        editorPane.setEditable(false);
        editorPane.setWrapStyleWord(true);
        editorPane.setLineWrap(true);
        editorPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        return editorPane;
    }

    public static JTextArea createTextArea(String text)
    {
        JTextArea textArea = createTextArea();
        textArea.setText(text);
        textArea.setBorder(null);
        return textArea;
    }

    public static boolean isOld()
    {
        return FileKit.getInstance().isSaveFileAvailable("misc/welcomeMessage.xml");
    }

}
