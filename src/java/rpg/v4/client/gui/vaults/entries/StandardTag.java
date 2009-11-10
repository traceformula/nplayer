package rpg.v4.client.gui.vaults.entries;

import static rpg.swingx.ColorConstants.*;
import rpg.v4.client.provider.DataModelProvider;
import rpg.v4.server.group.Group;
import rpg.v4.server.state.State;

import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Observer;

/**
 * The vault tag used for everything except the "new" action tag.
 */
public class StandardTag<T> extends VaultTag implements Observer
{
    protected T tagObject;
    private DataModelProvider<T> provider;

    public StandardTag(String header, DataModelProvider<T> provider)
    {
        super(header, BLACK, VAULT_TAG_NORMAL_TOP_LIGHT_LINE, VAULT_TAG_NORMAL_BOTTOM_GRADIENT, VAULT_TAG_NORMAL_TOP_GRADIENT);
        this.provider = provider;
    }

    public StandardTag(T tagObject, String name, DataModelProvider<T> provider)
    {
        this(name, provider);
        this.tagObject = tagObject;

        if (tagObject instanceof Observable)
        {
            ((Observable) tagObject).addObserver(this);
        }
    }

    public StandardTag(T tagObject, String name, DataModelProvider<T> provider, State state)
    {
        this(name, provider);
        this.tagObject = tagObject;
        state.addObserver(this);
    }

    public void mouseClicked(MouseEvent e)
    {
        if (null != tagObject)
            provider.loadInstance(tagObject);
    }

    public void update(Observable o, Object arg)
    {
        if (o instanceof State)
        {
            String name = (String) ((State) o).getTotal();
            headerLabel.setText(name);
            headerLabel.repaint();
        } else if (o instanceof Group){
            Group group = (Group) o;
            headerLabel.setText(group.getV4Group().getName());
            headerLabel.repaint();
        }
    }

}
