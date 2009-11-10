package rpg.v4.middleware.util.xml;

import rpg.v4.middleware.jaxb.V4Class;
import rpg.v4.middleware.jaxb.V4ClassList;
import rpg.v4.middleware.util.FileKit;
import rpg.v4.middleware.util.collection.ObservableArrayList;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.util.Collections;

/**
 * To change this template use File | Settings | File Templates.
 */
public class XMLClassKit extends XMLKit
{
    private static final String FILE_NAME = "CharacterClasses";
    private static XMLClassKit instance = new XMLClassKit();
    private static FileKit fileKit = FileKit.getInstance();

    private V4ClassList classesList;
    private ObservableArrayList<String> classNames;
    private ObservableArrayList<String> paragonPathNames;
    private ObservableArrayList<String> allNames;

    private XMLClassKit()
    {
        try
        {
            classesList = (V4ClassList) um.unmarshal(getFile(FILE_NAME));
        } catch (JAXBException e)
        {
            e.printStackTrace();
        }

        allNames = new ObservableArrayList<String>(classesList.getV4Class().size());
        classNames = new ObservableArrayList<String>(classesList.getV4Class().size());
        paragonPathNames = new ObservableArrayList<String>(classesList.getV4Class().size());
        for (V4Class characterClass : classesList.getV4Class())
        {
            addClassToNamesLists(characterClass);
        }

        Collections.sort(classNames);
    }

    private void addClassToNamesLists(V4Class characterClass)
    {
        if (characterClass.isParagonPath())
        {
            paragonPathNames.add(characterClass.getName());
        } else
        {
            classNames.add(characterClass.getName());
        }

        allNames.add(characterClass.getName());
    }

    public static XMLClassKit instance()
    {
        return instance;
    }

    public ObservableArrayList<String> getAllAvailableClasses()
    {
        return allNames;
    }

    public ObservableArrayList<String> getAvailableClasses()
    {
        return classNames;
    }

    public ObservableArrayList<String> getAvailableParagonPaths()
    {
        return paragonPathNames;
    }

    public void save(V4Class characterClass)
    {
        // If this is an entierly new class, add it to names list
        if (!classNames.contains(characterClass.getName()))
        {
            classesList.getV4Class().add(characterClass);
            addClassToNamesLists(characterClass);
        } else
        {
            // Overwrite existing item
            V4Class oldItem = getCharacterClass(characterClass.getName());
            classesList.getV4Class().remove(oldItem);
            classesList.getV4Class().add(characterClass);
        }
        
        File saveFile = fileKit.createFile("misc/" + FILE_NAME + ".xml");
        saveFile(saveFile, classesList);
    }

    public V4Class getCharacterClass(String className)
    {
        for (V4Class characterClass : classesList.getV4Class())
        {
            if (characterClass.getName().equals(className))
            {
                return characterClass;
            }
        }

        throw new IllegalStateException("Character class '" + className + "' not found.");
    }
}
