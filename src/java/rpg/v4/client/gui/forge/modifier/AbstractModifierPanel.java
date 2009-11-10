package rpg.v4.client.gui.forge.modifier;

import rpg.swingx.JTransparentPanel;
import rpg.v4.middleware.jaxb.V4Modifier;
import rpg.v4.client.proxy.ClientProxyKit;

import java.beans.PropertyChangeListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: fjelds
 * Date: Nov 6, 2009
 * Time: 11:30:07 AM
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractModifierPanel extends JTransparentPanel implements PropertyChangeListener, MouseListener
{
    protected V4Modifier modifier;
    protected ModifierForgePanel modifierForgePanel;

    public AbstractModifierPanel(V4Modifier modifier, ModifierForgePanel modifierForgePanel)
    {
        this.modifier = modifier;
        this.modifierForgePanel = modifierForgePanel;
    }

    public V4Modifier getModifier()
    {
        return modifier;
    }

    protected List<String> getTargetableStates()
    {
        return ClientProxyKit.CLIENT_PROXY.getAvailableTargetableStates();
    }


    public void mouseClicked(MouseEvent e)
    {
        modifierForgePanel.remove(this);
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
