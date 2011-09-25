package rpg.v4.client.gui.forge.modifier;

import rpg.v4.client.proxy.ClientProxyKit;
import rpg.v4.middleware.jaxb.V4Modifier;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Maintains the current modifers in the forge in the modifier XML data model.
 */
public class ModifierForge extends Observable
{
    private List<V4Modifier> modifierList;
    private String dynamicStatePicker;
    private boolean caterForWeapons = false;

    public ModifierForge()
    {
        modifierList = new ArrayList<V4Modifier>();
    }

    public ModifierForge(List<V4Modifier> modifierList)
    {
        this.modifierList = modifierList;
    }

    public List<V4Modifier> getModifierList()
    {
        return modifierList;
    }

    public void remove(V4Modifier mod)
    {
        if (modifierList.contains(mod))
        {
            modifierList.remove(mod);
        }
    }

    public V4Modifier createModifier(boolean includeDice)
    {
        V4Modifier mod = ClientProxyKit.CLIENT_PROXY.createModifier(includeDice);
        modifierList.add(mod);
        return mod;
    }

    public void setModifierList(List<V4Modifier> modifierList)
    {
        this.modifierList = modifierList;
        setChanged();
        notifyObservers(modifierList);
    }

    public boolean isCaterForWeapons()
    {
        return caterForWeapons;
    }

    public void setCaterForWeapons(boolean caterForWeapons)
    {
        this.caterForWeapons = caterForWeapons;
    }
}
