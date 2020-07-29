package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

public class Game {
    TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    public static final int WIDTH = 80;
    public static final int HEIGHT = 30;

    /** Creates the dimension of the gui window. */
    public void initializeFrame(){
        ter.initialize(WIDTH, HEIGHT);
    }

    /** Renders the window with black tiles. */
    public void initializeTiles(TETile[][] world){
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                world[x][y] = Tileset.NOTHING;
            }
        }
    }

    /** Renders many rooms across the GUI window.
     * Always starts from the bottom left corner of the GUI window. */
    public void aBunchOfRooms(TETile[][] world){
        int xCoordinate = 0;
        int yCoordinate = 0;

        for (int y = yCoordinate; y < HEIGHT; y += 10) {
            for (int x = xCoordinate; x < WIDTH; x += 10) {
                int[] randomWidthHeight = randomWidthAndHeightGenerator();
                addRectangularRoom(x,y,randomWidthHeight[0],randomWidthHeight[1],world);
            }
        }
    }

    /** Return an array of size two containing random width and height of the room. */
    public int[] randomWidthAndHeightGenerator(){
        int lowerBound = 4;
        int upperBound = 10;

        Random random = new Random();

        int width = RandomUtils.uniform(random, lowerBound,upperBound);
        int height = RandomUtils.uniform(random, lowerBound,upperBound);

        return new int[]{width,height};
    }

    /** Return an array of size two containing random x and y coordinates
     * between 0 and 9. */
    public int[] randomXYGenerator(){
        int lowerBound = 0;
        int upperBound = 9;

        Random random = new Random();

        int x = RandomUtils.uniform(random, lowerBound,upperBound);
        int y = RandomUtils.uniform(random, lowerBound,upperBound);

        return new int[]{x,y};
    }

    /** Renders a square into the world demo. */
    public void addSquaredRoom(int xCoordinate, int yCoordinate, int size, TETile[][] world) {
        if (yCoordinate + size >= HEIGHT || xCoordinate + size >= WIDTH){
            return;
        }

        for (int x = xCoordinate; x < xCoordinate + size; x++) {
            for (int y = yCoordinate; y < yCoordinate + size; y++) {
                world[x][y] = Tileset.WALL;
            }
        }

        for (int x = xCoordinate + 1; x < xCoordinate + size - 1; x++) {
            for (int y = yCoordinate + 1; y < yCoordinate + size - 1; y++) {
                world[x][y] = Tileset.FLOOR;
            }
        }
    }

    /** Renders a square into the world demo. */
    public void addRectangularRoom(int xCoordinate, int yCoordinate, int width, int height, TETile[][] world) {
        if (yCoordinate + height >= HEIGHT || xCoordinate + width >= WIDTH){
            return;
        }

        for (int x = xCoordinate; x < xCoordinate + width; x++) {
            for (int y = yCoordinate; y < yCoordinate + height; y++) {
                world[x][y] = Tileset.WALL;
            }
        }

        for (int x = xCoordinate + 1; x < xCoordinate + width - 1; x++) {
            for (int y = yCoordinate + 1; y < yCoordinate + height - 1; y++) {
                world[x][y] = Tileset.FLOOR;
            }
        }
    }

    /** Renders a square into the world demo.
     * Size of each piece is 10x10.
     * Ex: (0, 9) */
    public void splitScreen(TETile[][] world) {
        world[0][0] = Tileset.TREE;
        world[0][10] = Tileset.TREE;
        world[0][20] = Tileset.TREE;
    }

    /**
     * Method used for playing a fresh game. The game should start from the main menu.
     */
    public void playWithKeyboard() {
    }

    /**
     * Method used for autograding and testing the game code. The input string will be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The game should
     * behave exactly as if the user typed these characters into the game after playing
     * playWithKeyboard. If the string ends in ":q", the same world should be returned as if the
     * string did not end with q. For example "n123sss" and "n123sss:q" should return the same
     * world. However, the behavior is slightly different. After playing with "n123sss:q", the game
     * should save, and thus if we then called playWithInputString with the string "l", we'd expect
     * to get the exact same world back again, since this corresponds to loading the saved game.
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */
    public TETile[][] playWithInputString(String input) {
        // TODO: Fill out this method to run the game using the input passed in,
        // and return a 2D tile representation of the world that would have been
        // drawn if the same inputs had been given to playWithKeyboard().


        TETile[][] finalWorldFrame = new TETile[WIDTH][HEIGHT];
        Game game = new Game();

        game.initializeFrame(); // creates a gui window with given Width and Height.
        game.initializeTiles(finalWorldFrame);  // renders the gui window with black tiles.
        game.aBunchOfRooms(finalWorldFrame);

        /* Draws the 2D array world to the screen */
        ter.renderFrame(finalWorldFrame);

        return finalWorldFrame;
    }

    public static void main(String[] args) {
        Game game = new Game();
    }


}
