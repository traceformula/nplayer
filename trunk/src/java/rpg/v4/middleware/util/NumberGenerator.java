package rpg.v4.middleware.util;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by IntelliJ IDEA.
 * User: Sven-Ivar Fjeld
 * Date: 31-Aug-2004
 * Time: 19:12:30
 *
 * convenience methods for random number generation (e.g. a d20 roll) and querying (such as what
 * is the ability modifier of, say, 18).
 */
public class NumberGenerator
{

    public static Random generator = new Random();

    /**
     * Get a single random integer in the range 0 (inclusive) to N (inclusive).
     * <p/>
     * Important: if this method is called multiple times in rapid
     * succession, it will return the SAME result.
     *
     * @param inclusiveLowerLimit
     * @param inclusiveUpperLimit must be greater than 0.
     * @throws IllegalArgumentException if param does not comply.
     * @return
     */
    public static Integer pickNumber(int inclusiveLowerLimit, int inclusiveUpperLimit)
    {
        if (inclusiveUpperLimit < inclusiveLowerLimit)
        {
            throw new IllegalArgumentException("UpperLimit must be bigger than lowerLimit (" + inclusiveUpperLimit + ">" + inclusiveLowerLimit + ")");
        }
        //if the no-argument constructor is used, the seed is taken as
        //the current time; thus, Random objects created in rapid succession
        //in this way will have the same seed, and will generate the same sequence
        //of random numbers. Use pickNumbers instead.
        int rnum = 0;
        while (rnum < inclusiveLowerLimit)
        {
            rnum = NumberGenerator.generator.nextInt(inclusiveUpperLimit + 1);
        }
        return rnum;
    }

    public static Integer pickNumber(int inclusiveUpperLimit)
    {
        return pickNumber(1, inclusiveUpperLimit);
    }


    /**
     * Get a List of random Integers in the range 0 (inclusive) to N (exclusive).
     *
     * @param aUpperLimit must be greater than 0.
     * @param aListSize   must be greater than 0.
     * @throws IllegalArgumentException if param does not comply.
     * @return
     */
    public static ArrayList<Integer> pickNumbers(int aUpperLimit, int aListSize)
    {
        if (aUpperLimit <= 0)
        {
            throw new IllegalArgumentException("UpperLimit must be positive: " + aUpperLimit);
        }
        if (aListSize <= 0)
        {
            throw new IllegalArgumentException("Size of returned List must be greater than 0.");
        }
        //reuse one and the same Random object to generate a series
        //of random Integers
        Random generator = new Random();
        ArrayList<Integer> result = new ArrayList<Integer>();
        for (int idx = 0; idx < aListSize; ++idx)
        {
            result.add(generator.nextInt(aUpperLimit));
        }
        return result;
    }


}
