package rpg.v4.client.gui.forge.genericactions.impl.prerequisite;

import rpg.v4.client.proxy.ClientProxyKit;
import rpg.v4.middleware.jaxb.V4Prerequisite;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Created by IntelliJ IDEA.
 * User: fjelds
 * Date: 18/09/2011
 * Time: 10:27
 * Holds the list of prerequisites which has been added
 */
public class PrerequisiteForge extends Observable
{
    private List<V4Prerequisite> prerequisiteList;
    private boolean caterForWeapons = false;

    public PrerequisiteForge()
    {
        prerequisiteList = new ArrayList<V4Prerequisite>();
    }

    public PrerequisiteForge(List<V4Prerequisite> prerequisiteList)
    {
        this.prerequisiteList = prerequisiteList;
    }

    public List<V4Prerequisite> getPrerequisiteList()
    {
        return prerequisiteList;
    }

    public void remove(V4Prerequisite prerequisite)
    {
        if (prerequisiteList.contains(prerequisite))
        {
            prerequisiteList.remove(prerequisite);
        }
    }

    public V4Prerequisite createPrerequisite()
    {
        V4Prerequisite prerequisite = ClientProxyKit.CLIENT_PROXY.createPrerequisite();
        prerequisiteList.add(prerequisite);
        return prerequisite;
    }

    public void setPrerequisiteList(List<V4Prerequisite> prerequisiteList)
    {
        this.prerequisiteList = prerequisiteList;
        setChanged();
        notifyObservers(prerequisiteList);
    }
}
