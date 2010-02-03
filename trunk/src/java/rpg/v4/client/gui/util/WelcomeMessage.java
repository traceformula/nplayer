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
                "This release sees some updates around actions - some attack logic and some feat releated:\n\n" +
                "  - Actions: Basic attack actions can now be created and used in battle.\n\n" +
                "  - Feats: Whilst the benefits are NOT yet being applied, you can create and add feats to you character. The benefits will be added in the next relese.\n\n" +
                "I would also be very grateful if you spread the word about nPlayer - the more the merrier!!\n\n" +
                "Also, for the future: the next release will fix a couple of minor bugs.\n\n" +
                "Thanks\n\n" +
                "Sven\n" +
                "http://www.d20characters.com";
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
