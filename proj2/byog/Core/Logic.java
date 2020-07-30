package byog.Core;

import java.util.Random;

/** This class contains logic and calculations. */
public class Logic {

    /** A piece is a section of the GUI window. */
    public static int numberOfPieces(int windowWidth, int windowHeight, int pieceWidth, int pieceHeight) {
        return (windowWidth * windowHeight) / (pieceWidth * pieceHeight);
    }

    /** Room is inside the piece if outlines are less than or equals to.
     * Room is in side if top is less than or equal*/
    public static boolean isRoomInsideThePiece(Position room, Position piece){
        int i = room.compareTo(piece);

        return i < 0;

    }

    /** Returns an array of size 2 containing random x and y coordinates. */
    public static Position randomCoordinate() {
        int[] coordinates = randomXYGenerator();
        return new Position(coordinates[0],coordinates[1]);
    }

    /** Return an array of size two containing random width and height of the room. */
    public static int[] randomHallwayLengthGenerator(){
        int lowerBound = 3;
        int upperBound = 10;

        Random random = new Random();

        int width = 3;
        int length = RandomUtils.uniform(random, lowerBound,upperBound);

        int chance = RandomUtils.uniform(random, 0,9);

        if (chance < 5) {
            return new int[]{width, length};
        } else {
            return new int[]{length, width};
        }
    }

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
