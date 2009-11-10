package rpg.swingx.ui.scrollbar;

import com.explodingpixels.widgets.ImageBasedJComponent;
import rpg.swingx.ui.DarkScrollBarUI;

import javax.swing.*;
import java.awt.*;

/**
 * A factory for iApp style widgets.
 */
public class DarkScrollBarWidgetUI
{

    private static JComponent SCROLL_PANE_CORNER =
            new ImageBasedJComponent(new ImageIcon(DarkScrollBarWidgetUI.class.getResource(
                    "/com/explodingpixels/macwidgets/images/iapp_scrollpane_corner.png")).getImage());

    private DarkScrollBarWidgetUI() {
        // utility class - no constructor needed.
    }

    /**
     * Creates an iApp style {@link JScrollPane}, with vertical and horizontal scrollbars shown as
     * needed.
     *
     * @param view the view to wrap inside the {@code JScrollPane}.
     * @return an iApp style {@code JScrollPane}.
     * @see #makeIAppScrollPane
     */
    public static JScrollPane createScrollPaneWithButtonsTogether(Component view) {
        return createScrollPaneWithButtonsTogether(view, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    }

    /**
     * Creates an iApp style {@link JScrollPane} using the given scroll bar policies.
     *
     * @param view                      the view to wrap inside the {@code JScrollPane}.
     * @param verticalScrollBarPolicy   the vertical scroll bar policy.
     * @param horizontalScrollBarPolicy the horizontal scroll bar policy.
     * @return an iApp style {@code JScrollPane} using the given scroll bar policies.
     * @see #makeIAppScrollPane
     */
    public static JScrollPane createScrollPaneWithButtonsTogether(
            Component view, int verticalScrollBarPolicy, int horizontalScrollBarPolicy) {
        JScrollPane retVal = new JScrollPane(view, verticalScrollBarPolicy, horizontalScrollBarPolicy);
        makeIAppScrollPane(retVal);
        return retVal;
    }

    /**
     * Makes the given {@link JScrollPane} an iApp style scroll pane that looks like this:
     * <br>
     * <img src="../../../../graphics/iAppScrollbars.png">
     *
     * @param scrollPane the {@code JScrollPane} to make an iApp style scroll pane.
     * @return an iApp style scroll pane.
     */
    public static JScrollPane makeIAppScrollPane(JScrollPane scrollPane) {
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        installUIDelegates(scrollPane);
        scrollPane.setCorner(JScrollPane.LOWER_RIGHT_CORNER, SCROLL_PANE_CORNER);
        // TODO listen for scrollBar.setUI calls in order to reinstall UI delegates.
        return scrollPane;
    }

    // ScrollBarUI creation methods ///////////////////////////////////////////////////////////////

    private static void installUIDelegates(JScrollPane scrollPane) {
        scrollPane.getVerticalScrollBar().setUI(new DarkScrollBarUI());
        scrollPane.getHorizontalScrollBar().setUI(new DarkScrollBarUI());
    }

}
