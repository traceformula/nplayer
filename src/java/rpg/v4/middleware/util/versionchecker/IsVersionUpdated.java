package rpg.v4.middleware.util.versionchecker;

/**
 * Created by IntelliJ IDEA.
 * User: fjelds
 * Date: Nov 10, 2009
 * Time: 11:33:14 AM
 *
 * Checks with google code if version is updated
 */

import rpg.v4.client.gui.GameMasterFrame;

import java.net.MalformedURLException;
import java.net.URL;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class IsVersionUpdated
{
    private static final Logger logger = Logger.getLogger(IsVersionUpdated.class);
    private static final String urlString = "http://code.google.com/p/nplayer/downloads/list";
    private static final Pattern pattern = Pattern.compile("nPlayer v4\\.[0-9]+\\.[0-9]+ ");

    public static void main(String[] args)
    {
        ClassLoader loader = GameMasterFrame.class.getClassLoader();

        String log4jPropertiesFilename = "/rpg/log4j.properties";

        if (loader != null)
        {
            URL propertiesFile = IsVersionUpdated.class.getResource(log4jPropertiesFilename);
            PropertyConfigurator.configure(propertiesFile);
        }
        
        checkVersion();
    }

    public static String checkVersion()
    {
        URL u;
        String thisLine = null;

        //Open the URL for reading
        try
        {
            u = new URL(urlString);
            try
            {
                InputStream theHTML = u.openStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(theHTML));
                while ((thisLine = reader.readLine()) != null)
                {
                    Matcher matcher = pattern.matcher(thisLine);
                    if (matcher.find())
                    {
                        logger.info("Found: " + matcher.group());
                        return isVersionDifferent(matcher.group());
                    }
                }
            } // end try
            catch (Exception e)
            {
                System.err.println(e);
            }
        } // end try
        catch (MalformedURLException e)
        {
            System.err.println(urlString + " is not a parseable URL");
            System.err.println(e);
        }

        return null;
    }

    private static String isVersionDifferent(String nplayerText)
    {
        String webVersionString = nplayerText.replaceAll("nPlayer v", "").replaceAll(" ", "");
        String versionString = webVersionString.replaceAll("\\.", "");
        String currentVersionString = GameMasterFrame.VERSION.replaceAll("\\.", "");

        Integer webVersion = Integer.parseInt(versionString);
        Integer currentVersion = Integer.parseInt(currentVersionString);
        boolean webVersionIsNewer = webVersion > currentVersion;

        logger.info("Is currentVersion (" + currentVersion + ") different than web version ("
                + webVersion + "): " + webVersionIsNewer);

        return webVersionIsNewer ? webVersionString : null;
    }

}
