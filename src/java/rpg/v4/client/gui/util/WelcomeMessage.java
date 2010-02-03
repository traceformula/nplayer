package rpg.v4.client.gui.util;

import com.explodingpixels.macwidgets.HudWindow;
import rpg.swingx.ColorConstants;
import rpg.swingx.JTransparentScrollPane;
import rpg.v4.middleware.util.FileKit;
import rpg.v4.middleware.util.SimpleMail;
import rpg.v4.client.gui.control.contentview.ContentManager;
import rpg.v4.client.gui.control.navigation.NavigationEnum;

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
                "  - Actions: Basic actions, such as attacks, can now be created and used in battle.\n\n" +
                "  - Feats: Whilst the benefits are NOT yet being applied, you can create and add feats to you character. The benefits will be added.\n\n" +
                "  - Save files: new save file directory 4.1.3 is created. You can copy your characters etc. from the old directory (probably 4.1.2) in there but avoid the Race file as some new states have been added.\n\n" +
                "I would also be very grateful if you spread the word about nPlayer - the more the merrier!!\n\n" +
                "Also, for the future: the next release will fix a couple of minor bugs.\n\n" +
                "Thanks\n\n" +
                "Sven\n" +
                "http://www.d20characters.com";
        textArea.setText(welcomeText);

        JScrollPane scrollPane = new JTransparentScrollPane(textArea);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        getJDialog().setSize(470, 415);
        getJDialog().setLocationRelativeTo(frame);
        getJDialog().setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getJDialog().setVisible(true);

        Thread thread = new Thread() {

            public void run()
            {
                String message = "New download!";

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
                }
            }
        };

        thread.start();
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
