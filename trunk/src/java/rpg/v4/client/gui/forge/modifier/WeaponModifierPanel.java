package rpg.v4.client.gui.forge.modifier;

import rpg.v4.client.proxy.ClientProxyKit;
import rpg.v4.middleware.constants.StateIDs;
import rpg.v4.middleware.jaxb.V4Modifier;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: fjelds
 * Date: Oct 25, 2009
 * Time: 8:15:14 AM
 *
 * Makes the special "This Weapon's BAB" state available as a targetable state.
 *
 **/
public class WeaponModifierPanel extends ModifierPanel
{
    public WeaponModifierPanel(V4Modifier modifier, ModifierForgePanel modifierForgePanel)
    {
        super(modifier, modifierForgePanel);
    }

    @Override
    protected List<String> getTargetableStates()
    {
        List<String> availableStates = ClientProxyKit.CLIENT_PROXY.getAvailableTargetableStates();
        List<String> availableWeaponStates = new ArrayList<String>(availableStates.size() + 1);
        availableWeaponStates.add(StateIDs.THIS_WEAPON_BAB.toString());
        
        for (String targetableState : availableStates)
        {
            availableWeaponStates.add(targetableState);
        }

        return availableWeaponStates;
    }
}
