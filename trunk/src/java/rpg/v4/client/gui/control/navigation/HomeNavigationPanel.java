package rpg.v4.client.gui.control.navigation;

import static rpg.swingx.ColorConstants.*;
import rpg.swingx.JReflectionImage;
import rpg.swingx.JTransparentPanel;
import rpg.v4.client.gui.control.contentview.ContentManager;
import rpg.v4.client.gui.util.factories.LabelFactory;
import rpg.v4.client.gui.util.image.ImageKit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

/**
 *
 */
public class HomeNavigationPanel extends JTransparentPanel implements MouseListener
{
    private NavigationEnum navEnum;
    private boolean isOpaque;
    private Color initialBGColor = SIDEBAR_DARK_LINE;
    private Color highlight = HIGHLIGHT;
    private JTransparentPanel refPanel;
    private JReflectionImage homeReflectionImage;

    public HomeNavigationPanel(String header, String text, String imageName, NavigationEnum navEnum)
    {
        this.navEnum = navEnum;

        BufferedImage homeBIMG = ImageKit.loadBufferedImage(imageName);
        BufferedImage image_hover = ImageKit.loadBufferedImage(imageName + "_hover");
        homeReflectionImage = new JReflectionImage(homeBIMG, image_hover);
        refPanel = new JTransparentPanel();
        refPanel.add(homeReflectionImage, BorderLayout.CENTER);
        refPanel.setBorder(BorderFactory.createMatteBorder(0,10,0,10, SIDEBAR_DARK_LINE));

        JLabel headerLabel = LabelFactory.createHeaderLargeLabel(header);

        JTextArea textArea = new JTextArea(text, 5, 15);
        textArea.setEditable(false);
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        textArea.setOpaque(false);
        textArea.setBorder(null);
        textArea.setForeground(TEXT_NORMAL);
        textArea.addMouseListener(this);

        JTransparentPanel textPanel = new JTransparentPanel();
        textPanel.add(headerLabel, BorderLayout.NORTH);
        textPanel.add(textArea, BorderLayout.WEST);

        Box hBox = Box.createHorizontalBox();
        hBox.add(refPanel);
        hBox.add(textPanel);

        add(hBox, BorderLayout.NORTH);
        addMouseListener(this);
    }

    public void mouseClicked(MouseEvent e)
    {
        setNoHighlight();
        ContentManager.display(navEnum);
    }

    public void mousePressed(MouseEvent e)
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void mouseReleased(MouseEvent e)
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void mouseEntered(MouseEvent event)
    {
        isOpaque = refPanel.isOpaque();

        homeReflectionImage.setHovering(true);
        refPanel.setOpaque(true);
        refPanel.setBackground(highlight);
        refPanel.repaint();
    }

    public void mouseExited(MouseEvent event)
    {
        setNoHighlight();
    }

    private void setNoHighlight()
    {
        refPanel.setOpaque(isOpaque);

        if (isOpaque)
        {
            refPanel.setBackground(initialBGColor);
        }

        homeReflectionImage.setHovering(false);
        refPanel.repaint();
    }
}
