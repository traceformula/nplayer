package rpg.v4.middleware.util.deepcopy;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Utility for making deep copies (vs. clone()’s shallow copies) of
 * objects.
 * <p/>
 * Objects are first serialized and then deserialized. Error
 * checking is fairly minimal in this implementation. If an object is
 * encountered that cannot be serialized (or that references an object
 * that cannot be serialized) an error is printed to System.err and
 * null is returned. Depending on your specific application, it might
 * make more sense to have deepCopy(…) re-throw the exception.
 * <p/>
 * From: http://javatechniques.com/blog/faster-deep-copies-of-java-objects/
 */
public class FastDeepCopy
{

    /*
     * Returns a deepCopy of the activeObject, or null if the activeObject cannot
     * be serialized.
     */
    public static Object deepCopy(Object orig)
    {
        Object obj = null;
        try
        {
            // Write the activeObject out to a byte array
            FastByteArrayOutputStream fbos =
                    new FastByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(fbos);
            out.writeObject(orig);
            out.flush();
            out.close();

            // Retrieve an input stream from the byte array and read
            // a deepCopy of the activeObject back in.
            ObjectInputStream in =
                    new ObjectInputStream(fbos.getInputStream());
            obj = in.readObject();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (ClassNotFoundException cnfe)
        {
            cnfe.printStackTrace();
        }
        return obj;
    }

}