package rpg.v4.middleware.util.xml;

import org.apache.log4j.Logger;
import rpg.v4.middleware.util.FileKit;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Returns an unmarshaller for the specific package.
 */
public class XMLKit
{
    public static final Logger logger = Logger.getLogger(XMLKit.class);

    /**
     * Create a JAXB unmarschaller for the provided packagename.
     *
     * @param pckg - Package to be unmarshalled.
     * @return an unmarshaller
     */
    public synchronized static Unmarshaller getUnmarshaller(String pckg)
    {
        Unmarshaller um = null;
        try
        {
            JAXBContext jc = JAXBContext.newInstance(pckg);
            um = jc.createUnmarshaller();
        } catch (JAXBException e)
        {
            logger.error("Could not create unmarshaller for package [" + pckg + "]:", e);
        }
        return um;
    }

    public static File getFile(String listName)
    {
        File file = new File(FileKit.getInstance().getBaseString() + "/"+FileKit.SAVE+"/misc/"+listName+".xml");
        if (!file.exists())
        {
            logger.info("Could not find custom list " + listName + ", using default one. ");
            file = new File(FileKit.getInstance().getXMLString() + "/data/"+listName+".xml");
        }

        return file;
    }

    public static void saveFile(File saveFile, Object list)
    {
        try
        {
            JAXBContext jaxbContext = JAXBContext.newInstance("rpg.v4.middleware.jaxb");
            Marshaller entityMarshaller = jaxbContext.createMarshaller();
            entityMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            FileOutputStream stream = new FileOutputStream(saveFile);
            entityMarshaller.marshal(list, stream);
            stream.close();
        } catch (JAXBException e)
        {
            logger.error("Couldn't manage to save the file, something was wrong with the specification. Please email me the error to fjelds @ gmail . com.", e);
        } catch (FileNotFoundException e)
        {
            logger.error("Couldn't find the file that was tried to be used for saving. Make sure '" + saveFile.getAbsolutePath() + "' can be accessed.", e);
        } catch (IOException e)
        {
            logger.error("Couldn't close the stream, file might not have been saved.", e);
        }
    }

    public static void xmlToString(Object xmlObject)
    {
        JAXBContext jaxbContext = null;
        try
        {
            jaxbContext = JAXBContext.newInstance("rpg.v4.middleware.jaxb");
            Marshaller entityMarshaller = jaxbContext.createMarshaller();
            entityMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        } catch (JAXBException e)
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    protected Unmarshaller um;
    
    public XMLKit()
    {
        um = XMLKit.getUnmarshaller("rpg.v4.middleware.jaxb");
    }
}
