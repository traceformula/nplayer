package rpg.swingx.ui;

import com.explodingpixels.painter.ImagePainter;
import com.explodingpixels.painter.Painter;
import com.explodingpixels.widgets.ImageBasedJComponent;
import com.explodingpixels.widgets.plaf.*;
import rpg.swingx.ui.scrollbar.DarkScrollBarArtworkUtils;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import java.awt.*;

/**
 * Creates an dark style scroll bar, either horizontal or vertical based on
 * {@link javax.swing.JScrollBar#getOrientation()}.
 * <br>
 * <img src="../../../../../graphics/iAppHorizontalScrollBar.png">
 * <img src="../../../../../graphics/iAppVerticalScrollBar.png">
 */
public class DarkScrollBarUI extends SkinnableScrollBarUI {

    public DarkScrollBarUI() {
        super(createScrollBarSkinProvider());
    }

    public static ComponentUI createUI(JComponent c)    {
        return new DarkScrollBarUI();
    }

    private static ScrollBarSkinProvider createScrollBarSkinProvider() {
        return new ScrollBarSkinProvider() {
            public ScrollBarSkin provideSkin(ScrollBarOrientation orientation) {
                return orientation == ScrollBarOrientation.HORIZONTAL
                        ? createHorizontalSkin() : createVerticalSkin();
            }
        };
    }

    private static ScrollBarSkin createHorizontalSkin() {
        JComponent topCap = new ImageBasedJComponent(DarkScrollBarArtworkUtils.getScrollBarLeftCap().getImage());

        Dimension minimumThumbSize = DarkScrollBarArtworkUtils.getHorizontalScrollBarMinimumSize();
        AbstractButton decrementButton = DarkScrollBarArtworkUtils.createHorizontalTogetherDecrementButton();
        AbstractButton incrementButton = DarkScrollBarArtworkUtils.createHorizontalTogetherIncrementButton();
        Painter<Component> trackPainter = new ImagePainter(DarkScrollBarArtworkUtils.getHorizontalTrack().getImage());
        ScrollThumbImagePainter scrollerThumb = DarkScrollBarArtworkUtils.createHorizontalScrollerThumb();
        int topCapRecess = DarkScrollBarArtworkUtils.getScrollBarTopCapRecess();
        int decrementButtonRecess = DarkScrollBarArtworkUtils.getDecrementButtonRecess();
        Dimension preferredSize = new Dimension(100, decrementButton.getPreferredSize().height);

        return new ButtonsTogetherScrollBarSkin(
                topCap, decrementButton, incrementButton, trackPainter, scrollerThumb,
                topCapRecess, decrementButtonRecess, minimumThumbSize, preferredSize);
    }

    private static ScrollBarSkin createVerticalSkin() {
        Image topCapImage = DarkScrollBarArtworkUtils.getScrollBarTopCap().getImage();
        JComponent topCap = new ImageBasedJComponent(topCapImage);

        Dimension minimumThumbSize = DarkScrollBarArtworkUtils.getVerticalScrollBarMinimumSize();
        AbstractButton decrementButton = DarkScrollBarArtworkUtils.createVerticalTogetherDecrementButton();
        AbstractButton incrementButton = DarkScrollBarArtworkUtils.createVerticalTogetherIncrementButton();
        Painter<Component> trackPainter = new ImagePainter(DarkScrollBarArtworkUtils.getVerticalTrack().getImage());
        ScrollThumbImagePainter scrollerThumb = DarkScrollBarArtworkUtils.createVerticalScrollerThumb();
        int topCapRecess = DarkScrollBarArtworkUtils.getScrollBarTopCapRecess();
        int decrementButtonRecess = DarkScrollBarArtworkUtils.getDecrementButtonRecess();
        Dimension preferredSize = new Dimension(decrementButton.getPreferredSize().width, 100);

        return new ButtonsTogetherScrollBarSkin(
                topCap, decrementButton, incrementButton, trackPainter, scrollerThumb,
                topCapRecess, decrementButtonRecess, minimumThumbSize, preferredSize);
    }
}
