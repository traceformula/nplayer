package rpg.v4.client.gui.util.listener;

import rpg.v4.client.gui.control.navigation.SideBar;

import javax.swing.*;

/**
 * Sets the given panel in the SideBar listing when the ancestor is changed. The idea is that when
 * a main content component (i.e. the source of the event) changes it's ancestor, it means that the
 * content has changed and that the side bar should be updated respectively. 
 */
public class AncestorChangeListener implements java.beans.PropertyChangeListener, java.io.Serializable
{
    private final JComponent panel;

    public AncestorChangeListener(JComponent panel)
    {
        this.panel = panel;
    }

    public void propertyChange(java.beans.PropertyChangeEvent evt)
    {
        if (evt.getPropertyName().equals("ancestor"))
        {
            JComponent source = (JComponent) evt.getSource();
            if (source.isDisplayable() && source.isVisible())
            {
                SideBar.getINSTANCE().addSideComponent(panel);
            } else
            {
                SideBar.getINSTANCE().removeComponent(panel);
            }
        }
    }
}