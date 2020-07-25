package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    private static final int WIDTH = 30;
    private static final int HEIGHT = 30;
    private static final Random RANDOM = new Random();

    /** initialize the tile rendering engine with a window of size WIDTH x HEIGHT
     * 1) xOffset or yOffset is essentially moving the */
    public void initialFrame(TERenderer ter){
        //ter.initialize(WIDTH, HEIGHT);
        ter.initialize(WIDTH, HEIGHT,1,0);
    }

    /** Initialize tiles
     * empty space = black tiles */
    public void initializeTiles(TETile[][] world){
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                world[x][y] = Tileset.NOTHING;
            }
        }
    }

    /**
     * ____________________
     *      **
     * --> ****     (x,y) coordinate is here
     *     ****
     *      **
     * _____________________
     *  */
    public void addHexagon(int xCoordinate, int yCoordinate,TETile[][] world, int size, TETile T){
        printTopHalf(xCoordinate,yCoordinate,world,size, T);
        printTwoMiddleLines(xCoordinate, yCoordinate,world,size, T);
        printBottomHalf(xCoordinate,yCoordinate,world,size, T);
    }

    /** Return the number of prints for the two middle
     * lines of the hexagon */
    public int numberOfPrint(int size, int number) {

        if (size == 2) {
            return number + 4;
        } else {
            return numberOfPrint(size - 1, number + 3);
        }
    }

    /** Iterate at x = 1 + xCoordinate, y = size + yCoordinate + 1 */
    public void printTopHalf(int xCoordinate, int yCoordinate,TETile[][] world, int size, TETile T) {
        int numberOfPrint = numberOfPrint(size,0) + xCoordinate;
        int rows = size - 1;
        int x = 1 + xCoordinate;
        int newY = size + yCoordinate + 1;

        for (int y = 0; y < rows; y++) {
            for (int newX = x; newX <= numberOfPrint - 2; newX++) {
                world[newX][newY] = T;
            }

            numberOfPrint--;
            x++;
            newY++;
        }
    }

    /** Prints the two middle lines of the hexagon */
    public void printTwoMiddleLines(int xCoordinate, int yCoordinate,TETile[][] world, int size, TETile T){
        int numberOfPrint = numberOfPrint(size,0) + xCoordinate;
        yCoordinate += size;
        for (int x = xCoordinate; x < numberOfPrint; x++) { // iterate through x
            world[x][yCoordinate] = T;
            world[x][yCoordinate - 1] = T;
        }
    }

    /** Iterate at x = 1 + xCoordinate, y = size + yCoordinate - 2*/
    public void printBottomHalf(int xCoordinate, int yCoordinate, TETile[][] world, int size, TETile T){
        int numberOfPrint = numberOfPrint(size,0) + xCoordinate;
        int rows = size - 1;
        int x = 1 + xCoordinate;
        int newY = size + yCoordinate - 2;

        for (int y = 0; y < rows; y++) {
            for (int newX = x; newX <= numberOfPrint - 2; newX++) {
                world[newX][newY] = T;
            }

            numberOfPrint--;
            x++;
            newY--;
        }
    }

    public void firstAndLastColumnOfHex(TETile[][] world, int size){
        addHexagon(0,6,world,size, randomTile());
        addHexagon(0,12,world,size, randomTile());
        addHexagon(0,18,world,size, randomTile());

        //Last Column
        addHexagon(20,6,world,size, randomTile());
        addHexagon(20,12,world,size, randomTile());
        addHexagon(20,18,world,size, randomTile());
    }

    public void secondAndFourthColumnOfHex(TETile[][] world, int size){
        addHexagon(5,3,world,size, randomTile());
        addHexagon(5,9,world,size, randomTile());
        addHexagon(5,15,world,size, randomTile());
        addHexagon(5,21,world,size, randomTile());

        //Fourth Column
        addHexagon(15,3,world,size, randomTile());
        addHexagon(15,9,world,size, randomTile());
        addHexagon(15,15,world,size, randomTile());
        addHexagon(15,21,world,size, randomTile());
    }

    public void thirdColumnOfHex(TETile[][] world, int size){
        addHexagon(10,0,world,size, randomTile());
        addHexagon(10,6,world,size, randomTile());
        addHexagon(10,12,world,size, randomTile());
        addHexagon(10,18,world,size, randomTile());
        addHexagon(10,24,world,size, randomTile());
    }

    public void generateMap(TETile[][] world, int size){
        firstAndLastColumnOfHex(world,size);
        secondAndFourthColumnOfHex(world,size);
        thirdColumnOfHex(world,size);
    }


    private static TETile randomTile() {
        int tileNum = RANDOM.nextInt(7); // 0 - 6
        switch (tileNum) {
            case 0: return Tileset.WALL;
            case 1: return Tileset.TREE;
            case 2: return Tileset.GRASS;
            case 3: return Tileset.WATER;
            case 4: return Tileset.FLOWER;
            case 5: return Tileset.SAND;
            case 6: return Tileset.MOUNTAIN;

            default: return Tileset.TREE;
        }
    }

    public static void main(String[] args) {
        HexWorld hexWorld = new HexWorld();
        TERenderer ter = new TERenderer();
        TETile[][] world = new TETile[WIDTH][HEIGHT];

        hexWorld.initialFrame(ter);
        hexWorld.initializeTiles(world);



        hexWorld.generateMap(world,3);


        // draws the world to the screen
        ter.renderFrame(world);
    }
}
