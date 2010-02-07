package rpg.swingx.ui;

import rpg.swingx.ColorConstants;

import javax.swing.*;
import javax.swing.plaf.basic.BasicTabbedPaneUI;
import java.awt.*;

/**
 *
 */
public class DarkTabbedPaneUI extends BasicTabbedPaneUI
{
    static
    {
        UIManager.put("TabbedPane.contentAreaColor", ColorConstants.TRANSPARENT);
    }

    protected void paintTabBackground(Graphics g, int tabPlacement,
                                      int tabIndex,
                                      int x, int y, int w, int h,
                                      boolean isSelected)
    {
        g.setColor(isSelected ? ColorConstants.SIDEBAR_BG : ColorConstants.INFOBAR_DIVIDER_DARK_LINE);

        switch (tabPlacement)
        {
            case LEFT:
                g.fillRect(x + 1, y + 1, w - 1, h - 3);
                break;
            case RIGHT:
                g.fillRect(x, y + 1, w - 2, h - 3);
                break;
            case BOTTOM:
                g.fillRect(x + 1, y, w - 3, h - 1);
                break;
            case TOP:
            default:
                g.fillRect(x + 1, y + 1, w - 3, h);
        }
    }

    @Override
    protected void paintTabBorder(Graphics g, int tabPlacement, int tabIndex, int x, int y, int w, int h, boolean isSelected)
    {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.BLACK);

        switch (tabPlacement) {
          case LEFT:
              g2.drawLine(x+1, y+h-2, x+1, y+h-2); // bottom-left highlight
              g2.drawLine(x, y+2, x, y+h-3); // left highlight
              g2.drawLine(x+1, y+1, x+1, y+1); // top-left highlight
              g2.drawLine(x+2, y, x+w-1, y); // top highlight

              g2.setColor(shadow);
              g2.drawLine(x+2, y+h-2, x+w-1, y+h-2); // bottom shadow

              g2.setColor(darkShadow);
              g2.drawLine(x+2, y+h-1, x+w-1, y+h-1); // bottom dark shadow
              break;
          case RIGHT:
              g2.drawLine(x, y, x+w-3, y); // top highlight

              g2.setColor(shadow);
              g2.drawLine(x, y+h-2, x+w-3, y+h-2); // bottom shadow
              g2.drawLine(x+w-2, y+2, x+w-2, y+h-3); // right shadow

              g2.setColor(darkShadow);
              g2.drawLine(x+w-2, y+1, x+w-2, y+1); // top-right dark shadow
              g2.drawLine(x+w-2, y+h-2, x+w-2, y+h-2); // bottom-right dark shadow
              g2.drawLine(x+w-1, y+2, x+w-1, y+h-3); // right dark shadow
              g2.drawLine(x, y+h-1, x+w-3, y+h-1); // bottom dark shadow
              break;
          case BOTTOM:
              g2.drawLine(x, y, x, y+h-3); // left highlight
              g2.drawLine(x+1, y+h-2, x+1, y+h-2); // bottom-left highlight

              g2.setColor(shadow);
              g2.drawLine(x+2, y+h-2, x+w-3, y+h-2); // bottom shadow
              g2.drawLine(x+w-2, y, x+w-2, y+h-3); // right shadow

              g2.setColor(darkShadow);
              g2.drawLine(x+2, y+h-1, x+w-3, y+h-1); // bottom dark shadow
              g2.drawLine(x+w-2, y+h-2, x+w-2, y+h-2); // bottom-right dark shadow
              g2.drawLine(x+w-1, y, x+w-1, y+h-3); // right dark shadow
              break;
          case TOP:
          default:
              g2.drawLine(x, y+2, x, y+h); // left
              g2.drawLine(x, y+2, x+2, y); // left-to-top
              g2.drawLine(x+2, y, x+w-3, y); // top
              int right = isSelected ? x+w-1 : x+w-2;
              g2.drawLine(x+w-3, y, right, y+2); // top-to-left
              g2.drawLine(right, y+2, right, y+h); // right
        }
    }

    @Override
    protected void paintContentBorderBottomEdge(Graphics g, int tabPlacement, int selectedIndex, int x, int y, int w, int h)
    {

    }

    @Override
    protected void paintContentBorderLeftEdge(Graphics g, int tabPlacement, int selectedIndex, int x, int y, int w, int h)
    {

    }

    @Override
    protected void paintContentBorderRightEdge(Graphics g, int tabPlacement, int selectedIndex, int x, int y, int w, int h)
    {

    }

    @Override
    protected void paintContentBorderTopEdge(Graphics g, int tabPlacement, int selectedIndex, int x, int yNormal, int w, int h)
    {
        Rectangle selRect = selectedIndex < 0? null :
                               getTabBounds(selectedIndex, calcRect);

        g.setColor(Color.BLACK);
        int y = yNormal + 1;
        // Draw unbroken line if tabs are not on TOP, OR
        // selected tab is not in run adjacent to content, OR
        // selected tab is not visible (SCROLL_TAB_LAYOUT)
        //
        if (tabPlacement != TOP || selectedIndex < 0 ||
            (selRect.y + selRect.height + 1 < y) ||
            (selRect.x < x || selRect.x > x + w)) {
            g.drawLine(x, y, x+w-2, y);
        } else {
            // Break line to show visual connection to selected tab
            g.drawLine(x, y, selRect.x - 1, y);
            if (selRect.x + selRect.width < x + w - 2) {
                g.drawLine(selRect.x + selRect.width, y,
                           x+w-2, y);
            } else {
                g.setColor(shadow);
                g.drawLine(x+w-2, y, x+w-2, y);
            }
        }
    }
}
