package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

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

    /** Return a random sized room without coordinates. */
    private RectangularRoom randomSizedRoom() {
        int[] randomWidthHeight = Logic.randomWidthAndHeightGenerator();
        int width = randomWidthHeight[0];
        int height = randomWidthHeight[1];
        return new RectangularRoom(width,height);
    }


    /** Renders a rectangular room into the world demo. */
    public void addRectangularRoom(RectangularRoom room, TETile[][] world) {
        int xCoordinate = room.getBottomLeft().getX();
        int yCoordinate = room.getBottomLeft().getY();
        int width = room.getWidth();
        int height = room.getHeight();


        if (xCoordinate + width >= WIDTH || yCoordinate + height >= HEIGHT){
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

    /** Renders many rooms across the GUI window.
     * Always starts from the bottom left corner of the GUI window. */
    public void aBunchOfRooms(TETile[][] world){
        int xCoordinate = 0;
        int yCoordinate = 0;

        for (int y = yCoordinate; y < HEIGHT; y += 10) {
            for (int x = xCoordinate; x < WIDTH; x += 10) {
                RectangularRoom room = randomSizedRoom();
                Position bottomLeft = new Position(x,y);
                room.setBottomLeft(bottomLeft);
                addRectangularRoom(room, world);
            }
        }
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
