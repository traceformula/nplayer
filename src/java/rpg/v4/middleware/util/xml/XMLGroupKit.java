package rpg.v4.middleware.util.xml;

import rpg.v4.middleware.jaxb.V4Group;
import rpg.v4.middleware.jaxb.V4GroupList;
import rpg.v4.middleware.util.FileKit;
import rpg.v4.middleware.util.collection.ObservableArrayList;
import rpg.v4.server.battle.InitiativeOrderManager;
import rpg.v4.server.group.Group;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Manages groups as XML.
 */
public class XMLGroupKit extends XMLKit
{
    private static final String FILE_NAME = "Groups";
    private static FileKit fileKit = FileKit.getInstance();
    private static XMLGroupKit instance = new XMLGroupKit();

    static {
        InitiativeOrderManager.getInstance(); // kicks off the membership subscription.
    }

    private ObservableArrayList<String> groupNames;
    private V4GroupList groupList;
    private Map<String, Group> groupMap = new HashMap<String, Group>();

    public static XMLGroupKit instance()
    {
        return instance;
    }

    private XMLGroupKit()
    {
        try
        {
            Object object = um.unmarshal(getFile(FILE_NAME));
            groupList = (V4GroupList) object;
        } catch (JAXBException e)
        {
            e.printStackTrace();
        }

        groupNames = new ObservableArrayList<String>(groupList.getV4Group().size());
        for (V4Group v4Group : groupList.getV4Group())
        {
            groupNames.add(v4Group.getName());
            groupMap.put(v4Group.getName(), new Group(v4Group));
        }

        Collections.sort(groupNames);
    }

    public ObservableArrayList<String> getGroupNames()
    {
        return groupNames;
    }

    public void save(V4Group v4Group)
    {
        // If this is an entierly new group, add it to names list
        if (!groupNames.contains(v4Group.getName()))
        {
            groupList.getV4Group().add(v4Group);
            groupMap.put(v4Group.getName(), new Group(v4Group));
            groupNames.add(v4Group.getName());
        }

        File saveFile = fileKit.createFile("misc/"+FILE_NAME+".xml");
        saveFile(saveFile, groupList);
    }

    public Group getGroup(String groupName)
    {
        return groupMap.get(groupName);
    }
}
