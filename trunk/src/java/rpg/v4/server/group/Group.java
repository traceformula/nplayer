package rpg.v4.server.group;

import rpg.v4.middleware.jaxb.V4EntityNameKeyPair;
import rpg.v4.middleware.jaxb.V4Group;
import rpg.v4.server.entity.Entity;
import rpg.v4.server.vaults.EntityCollectionManager;

import java.awt.*;
import java.util.List;
import java.util.Observable;

/**
 * An object represenetation of a group. This is created as it needs to be observable, as
 * opposed to the non-observable XML representation {@link rpg.v4.middleware.jaxb.V4Group}.
*/
public class Group extends Observable
{
    private V4Group v4Group;
    private Color groupColor = Color.WHITE;

    public Group(V4Group v4Group)
    {
        this.v4Group = v4Group;

        V4Group.Color xmlColor = v4Group.getColor();
        groupColor = new Color(xmlColor.getRed(), xmlColor.getGreen(), xmlColor.getBlue());
        updateEntityColors();
    }

    /**
     * Adds a member to this group unless the member already exists. The name and UUID of
     * {@link rpg.v4.middleware.jaxb.V4EntityNameKeyPair} is checked as opposed to the object reference
     * because the nkp can be differents due to loading from disk and new {@link rpg.v4.server.entity.Entity}
     * objects being created in program.
     * @param nkp
     */
    public void addMember(V4EntityNameKeyPair nkp)
    {
        List<V4EntityNameKeyPair> membersList = v4Group.getV4EntityNameKeyPair();

        // Need to do a thurough check because marshalled from disk objects aren't the same.
        boolean exists = false;
        for(V4EntityNameKeyPair existingNKP : membersList)
        {
            if (existingNKP.getName().equals(nkp.getName()) &&
                existingNKP.getKey().equals(nkp.getKey()))
            {
                exists = true;
            }
        }

        if (!exists)
        {
            Entity entity = EntityCollectionManager.get(nkp);
            entity.setColor(groupColor);
            membersList.add(nkp);
            setChanged();
            notifyObservers(v4Group.getV4EntityNameKeyPair());
        }
    }

    /**
     * Returns the XML object representation of this group: {@link V4Group}.
     * @return
     */
    public V4Group getV4Group()
    {
        return v4Group;
    }

    /**
     * Removes a member from this group based on the object reference of the {@link V4EntityNameKeyPair}.
     * @param nkp
     */
    public void remove(V4EntityNameKeyPair nkp)
    {
        List<V4EntityNameKeyPair> membersList = v4Group.getV4EntityNameKeyPair();

        if (membersList.contains(nkp))
        {
            EntityCollectionManager.get(nkp).setColor(Color.WHITE);
            membersList.remove(nkp);
            setChanged();
            notifyObservers(v4Group.getV4EntityNameKeyPair());
        }
    }

    public void setInBattle(boolean inBattle)
    {
        v4Group.setInBattle(inBattle);
        setChanged();
        notifyObservers(inBattle);
    }

    public boolean isInBattle()
    {
        return v4Group.isInBattle();
    }

    public void setColor(Color newColor)
    {
        groupColor = newColor;
        v4Group.getColor().setRed(newColor.getRed());
        v4Group.getColor().setGreen(newColor.getGreen());
        v4Group.getColor().setBlue(newColor.getBlue());

        updateEntityColors();
    }

    private void updateEntityColors()
    {
        List<V4EntityNameKeyPair> membersList = v4Group.getV4EntityNameKeyPair();

        for(V4EntityNameKeyPair nkp : membersList)
        {
            EntityCollectionManager.get(nkp).setColor(groupColor);
        }
    }

    public Color getGroupColor()
    {
        return groupColor;
    }
}
