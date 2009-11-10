package rpg.v4.middleware.util.xml;

import rpg.v4.middleware.jaxb.V4ModifierTypeList;

import javax.xml.bind.JAXBException;
import java.util.List;

/**
 * Loads the available legal modifiers (e.g. "Armor bonus", "Feat bonus", "Penalty", etc.).
 */
public class XMLModifierTypeKit extends XMLKit
{
    private static final String FILE_NAME = "ModifierTypes";
    private static XMLModifierTypeKit instance = new XMLModifierTypeKit();

    private V4ModifierTypeList modifierTypeList;

    private XMLModifierTypeKit()
    {
        try
        {
            modifierTypeList = (V4ModifierTypeList) um.unmarshal(getFile(FILE_NAME));
        } catch (JAXBException e)
        {
            e.printStackTrace();
        }

    }

    public static XMLModifierTypeKit instance()
    {
        return instance;
    }

    /**
     * Returns a modifiable list of strings, where each string represents a modifier type.
     * @return List of modifier types
     */
    public List<String> getTypes()
    {
        return modifierTypeList.getV4ModifierType();
    }
}
