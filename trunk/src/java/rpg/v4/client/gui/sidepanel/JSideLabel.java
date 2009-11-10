package rpg.v4.client.gui.sidepanel;

import static rpg.swingx.ColorConstants.TEXT_NORMAL;
import rpg.v4.client.gui.util.listener.HoverTextListener;
import rpg.v4.client.gui.util.listener.SelectedBGChangerListener;
import static rpg.v4.middleware.constants.FontConstants.PLAIN_FONT;

import javax.swing.*;

/**
 * Created by IntelliJ IDEA.
 * User: fjelds
 * Date: Sep 27, 2009
 * Time: 6:39:04 PM
 *
 * Displays as a regular JLabel but also encapsulates the object that this lable
 * is associated with. This object can the later easily be retrieved.
 */
public class JSideLabel<T> extends JLabel
{
    private T associatedObject;

    public JSideLabel(T associatedObject)
    {
        super(associatedObject.toString());
        this.associatedObject = associatedObject;
        setForeground(TEXT_NORMAL);
        setFont(PLAIN_FONT);
        addMouseListener(new HoverTextListener(null, null));
        addMouseListener(new SelectedBGChangerListener());
    }

    public T getAssociatedObject()
    {
        return associatedObject;
    }
}
