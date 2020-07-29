package byog.Core;

import java.util.Random;

/** This class contains logic and calculations. */
public class Logic {

    /** A piece is a section of the GUI window. */
    public static int numberOfPieces(int windowWidth, int windowHeight, int pieceWidth, int pieceHeight) {
        return (windowWidth * windowHeight) / (pieceWidth * pieceHeight);
    }

    /** room is inside the piece if outlines are less than or equals to. */
    public static boolean isRoomInsideThePiece(Position room, Position piece){
        int roomX = room.getX();
        int roomY = room.getY();
        int pieceX = piece.getX();
        int pieceY = piece.getY();

        return (pieceX >= roomX) && (pieceY >= roomY);

    }

    /** Returns an array of size 2 containing random x and y coordinates. */
    public static Position randomCoordinate() {
        int[] coordinates = randomXYGenerator();
        return new Position(coordinates[0],coordinates[1]);
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
