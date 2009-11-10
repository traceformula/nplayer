package rpg.v4.middleware.util;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;

import rpg.v4.client.gui.GameMasterFrame;

/**
 * SINGELTON
 * <p/>
 * The FileKit takes care of the file integrity that this program is generating.
 * <p/>
 * Class makrd sure that the proper directories always exist (e.g. when a program starts the first
 * time around it creates the desired directory structure for the saved data), that the files
 * asked for are loaded properly and accompanying error handling, etc.
 * <p/>
 * This kit loads and saves the specified Group or Entity in the filepath specified relative
 * to the base directory controlled by the program itself.
 */
public class FileKit
{

    static Logger logger = Logger.getLogger(FileKit.class);
    private static FileKit fileKit = null;

    private static File baseDirectory, saveDir;
    private static String baseString, xmlString;
    public static final String SAVE = "save_v" + GameMasterFrame.VERSION;

    private FileKit()
    {
        baseDirectory = new File(".");
        try
        {
            baseString = baseDirectory.getCanonicalPath();
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        this.checkAndReccursivlyAmendSaveDirectory();

        xmlString = baseString + (isDirAvailable(baseDirectory, "src") ? "/src/xml" : "/xml");
    }

    /**
     * Checks if the save-directory is present. As this is the directory where all
     * saved files (groups, racedescriptions, characters), is saved this is a very
     * crucial directory. If it is not present, then create the directory and
     * it's subdirectories.
     */
    private void checkAndReccursivlyAmendSaveDirectory()
    {
        if (!isDirAvailable(baseDirectory, SAVE))
        {
            createDir(baseDirectory, SAVE);
        }
        saveDir = new File(baseString + "/" + SAVE);

        if (!isDirAvailable(saveDir, "groups"))
        {
            createDir(saveDir, "groups");
        }

        if (!isDirAvailable(saveDir, "entities"))
        {
            createDir(saveDir, "entities");
        }

        if (!isDirAvailable(saveDir, "items"))
        {
            createDir(saveDir, "items");
        }

        if (!isDirAvailable(saveDir, "misc"))
        {
            createDir(saveDir, "misc");
        }
    }

    /**
     * Loads all groups and entities into memory.
     */
    public void loadAll()
    {
        // If save dir exists, get the groupList
        if (isDirAvailable(baseDirectory, SAVE))
        {
            saveDir = new File(baseString + "/" + SAVE);
            // If there are groups available, load them.
            if (isDirAvailable(saveDir, "groups"))
            {
                File groupsDir = createFile(saveDir, "groups");
                String[] groupNames = groupsDir.list();
                String string = "- Groups: ";
                for (int i = 0; i < groupNames.length; i++)
                {
                    String name = groupNames[i];
                    string += name + ((i < groupNames.length - 1) ? ", " : "");
                }
                logger.info(string);

            } else
            {
                logger.info("Created groups dir:" + createDir(saveDir, "groups"));
            }

            // If there are entities available, load them provided they belong to a grouop.
            if (isDirAvailable(saveDir, "entities"))
            {
                File entitiesDir = createFile(saveDir, "entities");
                String[] entitiesNames = entitiesDir.list();
                //logger.info("Number of entities available: " + entitiesNames.length);
                String string = "- Enteties: ";
                for (int i = 0; i < entitiesNames.length; i++)
                {
                    String name = entitiesNames[i];
                    string += name + ((i < entitiesNames.length - 1) ? ", " : "");
                }
                logger.info(string);
            } else
            {
                logger.info("Created entities dir:" + createDir(saveDir, "entities"));
            }


        } else
        {
            logger.warn("Save dir did not exist. Creating it along with the subdirectories Groups and Entities.");
            saveDir = new File(baseString + "/" + SAVE);
            boolean createdGroupsDir, createdEntitiesDir;
            if (!isDirAvailable(saveDir, "groups"))
            {
                createdGroupsDir = createDir(saveDir, "groups");
                logger.info(". . . Created groups dir...[" + ((createdGroupsDir) ? "OK" : "FAILED") + "]");
            }

            if (!isDirAvailable(saveDir, "entities"))
            {
                createdEntitiesDir = createDir(saveDir, "entities");
                logger.info(". . . Created entity dir...[" + ((createdEntitiesDir) ? "OK" : "FAILED") + "]");
            }

            logger.info("Save dir amended...[OK]");
        }
    }

    public boolean createDir(File dir, String directory)
    {
        try
        {
            File dirToCreate = new File(dir.getCanonicalPath() + "/" + directory);
            return dirToCreate.mkdir();
        } catch (IOException e)
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return false;
    }

    /**
     * Creates a file in the relative path, relative to the internally set save directory. So, this returns
     * a file in the save directory specified by the relativePath argument.
     *
     * @param relativePath - Relative to the internally set save directory.
     * @return File
     */
    public File createFile(String relativePath)
    {
        return createFile(getSaveDir(), relativePath);
    }

    private File createFile(File directory, String path)
    {

        try
        {
            return new File(directory.getCanonicalPath() + "/" + path);
        } catch (IOException e)
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return null;
    }

    public boolean isSaveFileAvailable(String relativePath)
    {
        File saveDir = getSaveDir();
        try
        {
            File file = new File(saveDir.getCanonicalPath() + "/" + relativePath);
            return file.exists();
        } catch (IOException e)
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        return false;
    }

    public boolean isDirAvailable(File dir, String directory)
    {
        String[] s = dir.list();
        for (String value : s)
        {
            if (value.equals(directory))
            {
                return true;
            }

        }
        return false;
    }


    /**
     * *****************************************************************************************************
     * GETTERS AND SETTERS
     * ******************************************************************************************************
     */

    /**
     * Get the base directory of the application.
     * @return Base directory of application, usually ".".
     */
    public String getBaseString()
    {
        return baseString;
    }

    /**
     * Get the directory where the XML files are stored. Usually "./xml" or "./src/xml".
     * @returnXML directory
     */
    public String getXMLString()
    {
        return xmlString;
    }

    private static File getSaveDir()
    {
        return saveDir;
    }

    /**
     * Loading the specified file, typically of format <subSaveDir>/<fileName>.xml such as
     * entities/Grimgar__4324324234234.xml
     * @param saveFile
     * @return
     */
    public File getFile(String saveFile)
    {
        return new File(baseString + "/"+SAVE+"/" + saveFile);
    }

    /**
     * *****************************************************************************************************
     * SINGELTON ACCESS METHOD
     * ******************************************************************************************************
     */
    public static FileKit getInstance()
    {

        if (fileKit == null)
        {
            synchronized (FileKit.class)
            {
                if (fileKit == null)
                {
                    fileKit = new FileKit();
                }
            }
        }

        return fileKit;
    }
}
