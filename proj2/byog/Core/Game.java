package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.*;

public class Game {
    TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    public static final int WIDTH = 80;
    public static final int HEIGHT = 30;

    private List<RectangularRoom> allPieces ;    // keeps track of all the borders
    private List<RectangularRoom> allRooms;      // keeps track of all the rooms
    private List<RectangularRoom> allHallways;      // keeps track of all the hallways

    public Game() {
        this.allPieces = new ArrayList<>();
        this.allRooms = new ArrayList<>();
        this.allHallways = new ArrayList<>();

    }


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


    /** Renders a rectangular room into the world demo.
     * #####            ###
     * ....#            #.#
     * #####            #.#
     * */
    public void addHallWay(RectangularRoom hallway, TETile[][] world) {
        int xCoordinate = hallway.getBottomLeft().getX();
        int yCoordinate = hallway.getBottomLeft().getY();
        int width = hallway.getWidth();
        int height = hallway.getHeight();


        if (xCoordinate + width >= WIDTH || yCoordinate + height >= HEIGHT ||
                xCoordinate < 0 || yCoordinate < 0){
            return;
        }

        for (int x = xCoordinate; x < xCoordinate + width; x++) {
            for (int y = yCoordinate; y < yCoordinate + height; y++) {
                world[x][y] = Tileset.WALL;
            }
        }

        if (width == 3) {
            for (int x = xCoordinate + 1; x < xCoordinate + width - 1; x++) {
                for (int y = yCoordinate; y < yCoordinate + height - 1; y++) {
                    world[x][y] = Tileset.FLOOR;
                }
            }
        } else {
            for (int x = xCoordinate; x < xCoordinate + width - 1; x++) {
                for (int y = yCoordinate + 1; y < yCoordinate + height - 1; y++) {
                    world[x][y] = Tileset.FLOOR;
                }
            }
        }
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

    /** Randomly renders a bunch of rooms across the GUI window.
     * Always starts from the bottom left corner (0,0) of the GUI window. */
    public void aBunchOfRooms(TETile[][] world){
        for (int y = 0; y < HEIGHT; y += 10) {
            for (int x = 0; x < WIDTH; x += 10) {
                RectangularRoom room = Logic.randomSizedRoom();

                Position randomCoordinate = Logic.randomCoordinate();
                randomCoordinate.setX(randomCoordinate.getX() + x); // add x or y to get to the current piece
                randomCoordinate.setY(randomCoordinate.getY() + y);

                int randomX = randomCoordinate.getX();
                int randomY = randomCoordinate.getY();
                int width = room.getWidth();
                int height = room.getHeight();

                Position roomBottomLeft = new Position(randomX,randomY);
                Position roomTopRight = new Position(randomX + width - 1, randomY + height - 1);

                Position pieceBottomLeft = new Position(x, y);
                Position pieceTopRight = new Position(x + 9, y + 9);

                RectangularRoom piece = new RectangularRoom(10,10,pieceBottomLeft,pieceTopRight);
                allPieces.add(piece);

                room.setBottomLeft(roomBottomLeft);
                room.setTopRight(roomTopRight);

                if (Logic.isRoomInsideThePiece(room, piece)) {
                    allRooms.add(room);

                    addRectangularRoom(room, world);
                }
            }
        }
    }

    /** Randomly renders a bunch of hallways across the GUI window.
     * Always starts from the bottom left corner (0,0) of the GUI window. */
    public void aBunchOfHallways(TETile[][] world) {
        for (RectangularRoom room : allRooms) {
            int[] position = Logic.randomBorderCoordinates(room);
            int xBorder = position[0];
            int yBorder = position[1];

            RectangularRoom hallway = new RectangularRoom();

            if(xBorder == room.getTopRight().getX()) {      // if x Border
                hallway = Logic.randomNorthHallway();
            } else if (yBorder == room.getTopRight().getY()){
                hallway = Logic.randomNorthHallway();
            }

            int width = hallway.getWidth();
            int height = hallway.getHeight();

            hallway.setBottomLeft(new Position(xBorder, yBorder));
            hallway.setTopRight(new Position(xBorder + width - 1, yBorder + height - 1));

            allHallways.add(hallway);

            addHallWay(hallway, world);

        }

        System.out.println(allRooms.size());
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
        game.aBunchOfHallways(finalWorldFrame);


        /* Draws the 2D array world to the screen */
        ter.renderFrame(finalWorldFrame);


        return finalWorldFrame;
    }

    public static void main(String[] args) {
        Game game = new Game();
    }


}
