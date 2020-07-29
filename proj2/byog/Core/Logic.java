package byog.Core;

import java.util.Random;

public class Logic {
    /** Return an array of size two containing random width and height of the room. */
    public static int[] randomWidthAndHeightGenerator(){
        int lowerBound = 4;
        int upperBound = 10;

        Random random = new Random();

        int width = RandomUtils.uniform(random, lowerBound,upperBound);
        int height = RandomUtils.uniform(random, lowerBound,upperBound);

        return new int[]{width,height};
    }

    /** Return an array of size two containing random x and y coordinates
     * between 0 and 9. */
    public static int[] randomXYGenerator(){
        int lowerBound = 0;
        int upperBound = 9;

        Random random = new Random();

        int x = RandomUtils.uniform(random, lowerBound,upperBound);
        int y = RandomUtils.uniform(random, lowerBound,upperBound);

        return new int[]{x,y};
    }
}
