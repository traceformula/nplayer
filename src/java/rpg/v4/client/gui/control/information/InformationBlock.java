package rpg.v4.client.gui.control.information;

import static rpg.swingx.ColorConstants.*;
import rpg.swingx.ColorConstants;
import rpg.v4.middleware.util.versionchecker.IsVersionUpdated;
import rpg.v4.client.gui.util.listener.MousePickListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import com.centerkey.utils.BareBonesBrowserLaunch;

/**
 * Bottom information bar with email icons, resize etc. etc.
 */
public class InformationBlock extends BottomBlock implements MouseListener
{
    public static InformationBlock instance = new InformationBlock();
    private JLabel infoLabel;

    private InformationBlock()
    {
        super(0, INFORBAR_BOTTOM_LINE, INFOBAR_BOTTOM_GRADIENT, INFORBAR_BOTTOM_LINE, INFOBAR_TOP_GRADIENT, INFOBAR_LIGHT_LINE);

        infoLabel = new JLabel("");
        add(infoLabel, BorderLayout.CENTER);

        Thread updateAComponent = new Thread()
        {
            public void run()
            {
                while (!isDisplayable())
                {
                    try
                    {
                        Thread.sleep(1000);
                    } catch (InterruptedException e)
                    {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }
                }

                checkVersion();
            }
        };
        updateAComponent.start();
    }

    public void setInformationString(String text)
    {
        infoLabel.setText(text);
        infoLabel.repaint();

        Color newBottomColor = ColorConstants.INFO_BAR_HIGHLIGHT_BOTTOM;
        Color newTopColor = ColorConstants.INFO_BAR_HIGHLIGHT_TOP;
        fadeInto(newBottomColor, newTopColor);
    }

    private void checkVersion()
    {
        String isNewerVersionAvailable = IsVersionUpdated.checkVersion();

        if (isNewerVersionAvailable != null)
        {
            infoLabel.setToolTipText("http://code.google.com/p/nplayer");
            infoLabel.addMouseListener(this);
            infoLabel.addMouseListener(new MousePickListener(infoLabel));
            this.addMouseListener(new MousePickListener(this));
            this.addMouseListener(this);

            setInformationString("A newer version of nPlayer, version " + isNewerVersionAvailable + ", is availabe. Click here and download it.");
        }
    }

    public void mouseClicked(MouseEvent e)
    {
        BareBonesBrowserLaunch.openURL("http://code.google.com/p/nplayer");
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
