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
    public static boolean isRoomInsideThePiece(Rectangular room, Rectangular piece){
        Position roomBottomLeft = room.getBottomLeft();
        Position roomTopRight = room.getTopRight();
        Position pieceBottomLeft = piece.getBottomLeft();
        Position pieceTopRight = piece.getTopRight();

        int roomBottomLeftX = roomBottomLeft.getX();
        int roomBottomLeftY = roomBottomLeft.getY();
        int roomTopRightX = roomTopRight.getX();
        int roomTopRightY = roomTopRight.getY();


        int pieceBottomLeftX = pieceBottomLeft.getX();
        int pieceBottomLeftY = pieceBottomLeft.getY();
        int pieceTopRightX = pieceTopRight.getX();
        int pieceTopRightY = pieceTopRight.getY();



        return (pieceTopRightX - roomTopRightX >= 0) && (pieceTopRightY - roomTopRightY >= 0)
                && (roomBottomLeftX - pieceBottomLeftX >= 0) && (roomBottomLeftY - pieceBottomLeftY >= 0);

    }

    /** Returns an array of size 2 containing random x and y coordinates. */
    public static Position randomCoordinate() {
        int[] coordinates = randomXYGenerator(0, 9);
        return new Position(coordinates[0],coordinates[1]);
    }

    /** Returns an array of size 2 containing random x,y border coordinates. */
    public static int[] randomBorderCoordinates(Rectangular room){
        Position bottomLeft = room.getBottomLeft();
        Position topRight = room.getTopRight();

        int bottomLeftX = bottomLeft.getX();
        int bottomLeftY = bottomLeft.getY();
        int topRightX = topRight.getX();
        int topRightY = topRight.getY();

        Random random = new Random();

        int chance = RandomUtils.uniform(random, 0,99);

        // if chance < 25, x stays, y changes
        if (chance < 25) {
            int newY = RandomUtils.uniform(random, bottomLeftY,topRightY - 2);
            return new int[] {topRightX, newY};
        } else if (chance < 50){    // x changes, y stays
            int newX = RandomUtils.uniform(random, bottomLeftX,topRightX - 2);
            return new int[]{newX, topRightY};
        } else if (chance < 75){    // WEST
            int newY = RandomUtils.uniform(random, bottomLeftY,topRightY - 2);
            return new int[] {bottomLeftX, newY};
        } else {        // x changes, y stays
            int newX = RandomUtils.uniform(random, bottomLeftX,topRightX - 2);
            return new int[] {newX, bottomLeftY};
        }
    }

    /** Returns an array of size 2 containing random x,y border coordinates. */
    public static int[] randomNorthHallwayBorderCoordinates(Rectangular hallway){
        Position bottomLeft = hallway.getBottomLeft();
        Position topRight = hallway.getTopRight();

        int bottomLeftX = bottomLeft.getX();
        int bottomLeftY = bottomLeft.getY();
        int topRightX = topRight.getX();
        int topRightY = topRight.getY();

        Random random = new Random();

        int chance = RandomUtils.uniform(random, 0,99);

        // if chance < 25, x stays, y changes
        if (chance < 25) {
            int newY = RandomUtils.uniform(random, bottomLeftY,topRightY - 2);
            return new int[] {topRightX, newY};
        } else if (chance < 50){    // x changes, y stays
            int newX = RandomUtils.uniform(random, bottomLeftX,topRightX - 2);
            return new int[]{newX, topRightY};
        } else if (chance < 75){    // WEST
            int newY = RandomUtils.uniform(random, bottomLeftY,topRightY - 2);
            return new int[] {bottomLeftX, newY};
        } else {        // x changes, y stays
            int newX = RandomUtils.uniform(random, bottomLeftX,topRightX - 2);
            return new int[] {newX, bottomLeftY};
        }
    }

    /** Return a random sized room without coordinates. */
    public static Rectangular randomSizedRoom() {
        int[] randomWidthHeight = Logic.randomWidthAndHeightGenerator();
        int width = randomWidthHeight[0];
        int height = randomWidthHeight[1];
        return new Rectangular(width,height);
    }


    /** Circumference of the shapes. */
    public static Position[] circumference(Position bottomLeft, Position topRight) {
        return new Position[] {bottomLeft, topRight};
    }

    /** Return a random sized hallway without coordinates. */
    public static Rectangular randomNorthOrSouthHallway() {
        int width = 3;
        int upperBound = 10;

        Random random = new Random();

        int length = RandomUtils.uniform(random, 3,upperBound);

        return new Rectangular(3,length);
    }

    /** Return a random sized hallway without coordinates. */
    public static Rectangular randomEastOrWestHallway() {
        int width = 3;
        int upperBound = 10;

        Random random = new Random();

        int length = RandomUtils.uniform(random, 3,upperBound);

        return new Rectangular(length,3);
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
    public static int[] randomXYGenerator(int lowerBound, int upperBound){
        Random random = new Random();

        int x = RandomUtils.uniform(random, lowerBound,upperBound);
        int y = RandomUtils.uniform(random, lowerBound,upperBound);

        return new int[]{x,y};
    }
}
