package rpg.v4.client.gui;

import org.apache.log4j.PropertyConfigurator;
import static rpg.swingx.ColorConstants.STANDARD_BACKGROUND;
import rpg.swingx.JBorderLayoutPanel;
import rpg.swingx.JGradientPanel;
import rpg.swingx.JTransparentPanel;
import rpg.v4.client.gui.control.contentview.ContentManager;
import rpg.v4.client.gui.control.information.BottomBar;
import rpg.v4.client.gui.control.navigation.NavigationEnum;
import rpg.v4.client.gui.control.navigation.SideBar;
import rpg.v4.client.gui.control.window.WindowDisposePanel;
import rpg.v4.client.gui.util.WelcomeMessage;
import rpg.v4.client.gui.util.image.ImageKit;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.net.URL;

/**
 * The basic frame of the GameMaster client application.
 */
public class GameMasterFrame extends JFrame
{
    public static JFrame frame;
    private static final int WIDTH = 940, HEIGHT = 700;
    private static final String APPLICATION_TITLE = "nPlayer   4";
    public static final String VERSION = "4.1.2"; 

    public GameMasterFrame() throws HeadlessException
    {
        setBasicGUIProperties();
        setupTopbar();
        setupBottomBar();
        setupMainContentPanel();
        centerThisFrame();

        ContentManager.display(NavigationEnum.HOME);

        setVisible(true);

        if (!WelcomeMessage.isOld())
        {
            new WelcomeMessage(this);
        }
    }

    private void setupBottomBar()
    {
        BottomBar bar = new BottomBar(this);
        this.add(bar, BorderLayout.SOUTH);
    }

    private void setupMainContentPanel()
    {
        JBorderLayoutPanel main = new JBorderLayoutPanel();
        main.setBackground(STANDARD_BACKGROUND);
        //main.setBorder(BorderFactory.createMatteBorder(0,0,1,0, ColorConstants.CONTENT_DARK_LINE));

        main.add(SideBar.getINSTANCE(), BorderLayout.WEST);
        main.add(ContentManager.instance().getMainPanel(), BorderLayout.CENTER);

        this.add(main, BorderLayout.CENTER);
    }

    private void setupTopbar()
    {
        ImageIcon betaImage = ImageKit.loadImageIcon("beta-corner");
        JLabel betaLabel = new JLabel(betaImage);
        JTransparentPanel betaPanel = new JTransparentPanel();
        betaPanel.add(betaLabel, BorderLayout.NORTH);
        //betaPanel.setBorder(BorderFactory.createLineBorder(Color.black));

        JLabel label = new JLabel(APPLICATION_TITLE, JLabel.CENTER);
        Border labelSpacing = BorderFactory.createEmptyBorder(10,0,0,10);
        label.setBorder(labelSpacing);

        JPanel windowsDisposePanel = new WindowDisposePanel(this);
        Border spacing = BorderFactory.createEmptyBorder(10,0,0,10);
        windowsDisposePanel.setBorder(spacing);

        JTransparentPanel topPanel = new JTransparentPanel();
        topPanel.add(windowsDisposePanel, BorderLayout.EAST);
        topPanel.add(betaPanel, BorderLayout.WEST);
        topPanel.add(label, BorderLayout.CENTER);

        JGradientPanel topBar = new JGradientPanel(0,0,0,0);
        topBar.add(topPanel, BorderLayout.NORTH);
        topBar.add(new JLabel(" "), BorderLayout.SOUTH);

        topBar.addMouseMotionListener(new MouseMotionAdapter()
        {
            private Point initialClick = null;
            private Long timeStamp = System.currentTimeMillis();

            public void mouseDragged(MouseEvent e)
            {
                Long now = System.currentTimeMillis();
                // Avoid silly mouse pointer reposition upon drag after first click.
                if ((now - timeStamp) > 100)
                {
                    initialClick = null;
                }
                timeStamp = now;

                // Procedd with the drag motion.
                if (initialClick == null)
                {
                    initialClick = e.getPoint();
                }


                // get location of Window
                int thisX = getLocation().x;
                int thisY = getLocation().y;

                // Determine how much the mouse moved since the initial click
                int xMoved = (thisX + e.getX()) - (thisX + initialClick.x);
                int yMoved = (thisY + e.getY()) - (thisY + initialClick.y);

                // Move window to this position
                int X = thisX + xMoved;
                int Y = thisY + yMoved;
                frame.setLocation(X, Y);
            }
        });

        this.add(topBar, BorderLayout.NORTH);
    }

    private void setBasicGUIProperties()
    {
        // Set tooltip display time to 30 seconds. There is only one tooltipmanager
        // for every apaplication => shared instance for all containers
        ToolTipManager manager = ToolTipManager.sharedInstance();
        manager.setDismissDelay(30000);
        this.setSize(WIDTH, HEIGHT);
        this.setMinimumSize(new Dimension(300, 300));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.getContentPane().setLayout(new BorderLayout());
        this.setTitle(APPLICATION_TITLE);
        this.setUndecorated(true);
    }

    private void centerThisFrame()
    {
        //Get the screen size
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();

        //Calculate the frame location
        int x = (screenSize.width - this.getWidth()) / 2;
        int y = (screenSize.height - this.getHeight()) / 2;

        //Set the new frame location
        this.setLocation(x, y);
    }

    public static void main(String[] args)
    {
        configureLogger();
        frame = new GameMasterFrame();
    }


    private static void configureLogger()
    {
        ClassLoader loader = GameMasterFrame.class.getClassLoader();

        String log4jPropertiesFilename = "/rpg/log4j.properties";

        if (loader != null)
        {
            URL propertiesFile = GameMasterFrame.class.getResource(log4jPropertiesFilename);
            PropertyConfigurator.configure(propertiesFile);
        }
    }

}
