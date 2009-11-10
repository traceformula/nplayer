package rpg.v4.middleware.util.xml;

import rpg.v4.middleware.jaxb.V4BodyPart;
import rpg.v4.middleware.jaxb.V4Race;
import rpg.v4.middleware.jaxb.V4RaceList;
import rpg.v4.middleware.util.FileKit;
import rpg.v4.middleware.util.collection.ObservableArrayList;
import rpg.v4.middleware.util.deepcopy.FastDeepCopy;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.util.Collections;
import java.util.List;

/**
 * Manages XML races, loading and saving of  them, as well as providing useful information.
 */
public class XMLRaceKit extends XMLKit
{
    private static final String FILE_NAME = "Races";
    private static XMLRaceKit instance = new XMLRaceKit();
    private static ObservableArrayList<String> raceNames;
    private static FileKit fileKit = FileKit.getInstance();
    private V4RaceList raceList;
    private List<V4BodyPart> standardBodyPartList;

    public static XMLRaceKit instance()
    {
        return instance;
    }

    private XMLRaceKit()
    {
        try
        {
            raceList = (V4RaceList) um.unmarshal(getFile(FILE_NAME));
        } catch (JAXBException e)
        {
            e.printStackTrace();
        }

        raceNames = new ObservableArrayList<String>(raceList.getV4Race().size());
        for (V4Race race : raceList.getV4Race())
        {
            raceNames.add(race.getName());
        }

        standardBodyPartList = raceList.getV4Race().get(0).getV4BodyPart();
        standardBodyPartList = (List<V4BodyPart>) FastDeepCopy.deepCopy(standardBodyPartList);
        Collections.sort(raceNames);
    }

    public V4Race getRace(String raceID)
    {
        for (V4Race race : raceList.getV4Race())
        {
            if (race.getName().equals(raceID))
            {
                return race;
            }
        }

        return null;
    }

    public boolean exists(String raceName)
    {
        return raceNames.contains(raceName);
    }

    /**
     * Write race to file, overwriting existing file if nessecary.
     *
     * @param race The new race to save
     */
    public void save(V4Race race)
    {
        // If this is an entierly new race, add it to names list
        if (!raceNames.contains(race.getName()))
        {
            raceList.getV4Race().add(race);
            raceNames.add(race.getName());
        } else
        {
            // Overwrite existing item
            V4Race oldItem = getRace(race.getName());
            raceList.getV4Race().remove(oldItem);
            raceList.getV4Race().add(race);
        }

        File saveFile = fileKit.createFile("misc/"+FILE_NAME+".xml");
        saveFile(saveFile, raceList);
    }

    public ObservableArrayList<String> getAvailableRaces()
    {
        return raceNames;
    }

    public List<V4BodyPart> getStandardBodyPartList()
    {
        return (List<V4BodyPart>) FastDeepCopy.deepCopy(standardBodyPartList);
    }
}
