package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WorldGenerator {
    TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    public static final int WIDTH = 70;
    public static final int HEIGHT = 50;

    private List<Rectangular> allPieces = new ArrayList<>();    // keeps track of all the borders
    private List<Rectangular> allRooms = new ArrayList<>();      // keeps track of all the rooms
    private List<Rectangular> allHallways = new ArrayList<>();      // keeps track of all the hallways
    private Map<Rectangular, Rectangular> roomsAndHallways = new HashMap<>();

    /** Creates the dimension of the gui window. */
    public void initializeFrame(){
        ter.initialize(WorldGenerator.WIDTH, WorldGenerator.HEIGHT);
    }
    /** Renders the window with black tiles. */
    public void initializeTiles(TETile[][] world){
        for (int x = 0; x < WorldGenerator.WIDTH; x++) {
            for (int y = 0; y < WorldGenerator.HEIGHT; y++) {
                world[x][y] = Tileset.NOTHING;
            }
        }
    }

    public boolean isInitialHallwaysAppropriate(Rectangular hallway, Direction direction){
        for (Rectangular room : allRooms) {

            // Hallways
            int xBottom = hallway.getBottomLeft().getX();
            int yBottom = hallway.getBottomLeft().getY();
            int xTop = hallway.getTopRight().getX();
            int yTop = hallway.getTopRight().getY();

            // Rooms
            int xRoomBottom = room.getBottomLeft().getX();
            int yRoomBottom = room.getBottomLeft().getY();
            int xRoomTop = room.getTopRight().getX();
            int yRoomTop = room.getTopRight().getY();


            int width = hallway.getWidth();
            int height = hallway.getHeight();

            if (xBottom + width > WIDTH || yBottom + height > HEIGHT ||
                    xBottom - width < 0 || yBottom - height < 0) {
                return false;
            }

        }


        return true;
    }

    /** Renders a rectangular room into the world demo. */
    public void drawHallWay(Rectangular hallway, Direction direction, TETile[][] world) {
        int xBottom;
        int yBottom;

        int width = hallway.getWidth();
        int height = hallway.getHeight();

        if (direction == Direction.NORTH){
            xBottom = hallway.getBottomLeft().getX();
            yBottom = hallway.getBottomLeft().getY();

            for (int x = xBottom; x < xBottom + width; x++) {
                for (int y = yBottom; y < yBottom + height; y++) {
                    world[x][y] = Tileset.WALL;
                }
            }

            for (int x = xBottom + 1; x < xBottom + width - 1; x++) {
                for (int y = yBottom; y < yBottom + height - 1; y++) {
                    world[x][y] = Tileset.FLOOR;
                }
            }
        }
        else if (direction == Direction.EAST) {
            xBottom = hallway.getBottomLeft().getX();
            yBottom = hallway.getBottomLeft().getY();


            for (int x = xBottom; x < xBottom + width; x++) {
                for (int y = yBottom; y < yBottom + height; y++) {
                    world[x][y] = Tileset.WALL;
                }
            }

            for (int x = xBottom; x < xBottom + width - 1; x++) {
                for (int y = yBottom + 1; y < yBottom + height - 1; y++) {
                    world[x][y] = Tileset.FLOOR;
                }
            }
        }else if (direction == Direction.SOUTH) {
            xBottom = hallway.getBottomLeft().getX();
            yBottom = hallway.getBottomLeft().getY();

            for (int x = xBottom; x < xBottom + width; x++) {
                for (int y = yBottom; y < yBottom + height; y++) {
                    world[x][y] = Tileset.WALL;
                }
            }

            for (int x = xBottom + 1; x < xBottom + width - 1; x++) {
                for (int y = yBottom + 1; y < yBottom + height + 1; y++) {
                    world[x][y] = Tileset.FLOOR;
                }
            }
        } else if (direction == Direction.WEST) {
            xBottom = hallway.getBottomLeft().getX();
            yBottom = hallway.getBottomLeft().getY();


            for (int x = xBottom; x < xBottom + width; x++) {
                for (int y = yBottom; y < yBottom + height; y++) {
                    world[x][y] = Tileset.WALL;
                }
            }

            for (int x = xBottom + 1; x < xBottom + width; x++) {
                for (int y = yBottom + 1; y < yBottom + height - 1; y++) {
                    world[x][y] = Tileset.FLOOR;
                }
            }
        }


        // renders a rectangle with walls
        allHallways.add(hallway);
    }

    /** Renders a rectangular room into the world demo. */
    public void addRoom(Rectangular room, TETile[][] world) {
        int xCoordinate = room.getBottomLeft().getX();
        int yCoordinate = room.getBottomLeft().getY();
        int width = room.getWidth();
        int height = room.getHeight();


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
                Rectangular room = Logic.randomSizedRoom();

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

                Rectangular piece = new Rectangular(10,10,pieceBottomLeft,pieceTopRight);
                allPieces.add(piece);

                room.setBottomLeft(roomBottomLeft);
                room.setTopRight(roomTopRight);

                if (Logic.isRoomInsideThePiece(room, piece)) {
                    allRooms.add(room);

                    addRoom(room, world);
                }
            }
        }
    }

    /** Randomly renders a bunch of hallways across the GUI window.
     * Always starts from the bottom left corner (0,0) of the GUI window. */
    public void aBunchOfHallways(TETile[][] world) {
        for (Rectangular room : allRooms) {
            Rectangular hallway = new Rectangular();
            int xBorder;
            int yBorder;
            Direction direction = null;

            while (true) {
                int[] position = Logic.randomBorderCoordinates(room);
                xBorder = position[0];
                yBorder = position[1];

                int width;
                int height;

                if (xBorder == room.getTopRight().getX()) {      // x = top right, ---> East
                    hallway = Logic.randomEastOrWestHallway();
                    direction = Direction.EAST;
                } else if (yBorder == room.getTopRight().getY()) {   // y = top right ----> North
                    hallway = Logic.randomNorthOrSouthHallway();
                    direction = Direction.NORTH;
                } else if (xBorder == room.getBottomLeft().getX()) {     // x = bottom left --> West
                    hallway = Logic.randomEastOrWestHallway();
                    direction = Direction.WEST;
                } else if (yBorder == room.getBottomLeft().getY()) {     // y = bottom left  ---> South
                    hallway = Logic.randomNorthOrSouthHallway();
                    direction = Direction.SOUTH;
                }

                // North, East
                if (xBorder == room.getTopRight().getX() || yBorder == room.getTopRight().getY()) {
                    width = hallway.getWidth();
                    height = hallway.getHeight();
                    hallway.setBottomLeft(new Position(xBorder, yBorder));
                    hallway.setTopRight(new Position(xBorder + width - 1, yBorder + height - 1));
                } else if (xBorder == room.getBottomLeft().getX()) {     // West
                    width = hallway.getWidth();
                    height = hallway.getHeight();
                    hallway.setBottomLeft(new Position(xBorder - width + 1, yBorder));
                    hallway.setTopRight(new Position(xBorder, yBorder + height - 1));
                } else if (yBorder == room.getBottomLeft().getY()) {    // South
                    width = hallway.getWidth();
                    height = hallway.getHeight();
                    hallway.setBottomLeft(new Position(xBorder, yBorder - height + 1));
                    hallway.setTopRight(new Position(hallway.getBottomLeft().getX() + width - 1,
                            hallway.getBottomLeft().getY() + height - 1));
                }

                if (isInitialHallwaysAppropriate(hallway, direction)) {
                    break;
                }

            }

            if (xBorder == room.getTopRight().getX()) {      // North
                drawHallWay(hallway, Direction.EAST, world);
            } else if (yBorder == room.getTopRight().getY()) {   // North
                drawHallWay(hallway, Direction.NORTH, world);
            } else if (xBorder == room.getBottomLeft().getX()) {     // West
                drawHallWay(hallway, Direction.WEST, world);
            } else if (yBorder == room.getBottomLeft().getY()) {     // South
                drawHallWay(hallway, Direction.SOUTH, world);
            }

        }

    }

    public void hallwaysStickingOutOfOtherHallways(TETile[][] world){
        for (Rectangular hall : allHallways) {
            System.out.println("yee yee");
        }
    }

    /** Generate a random world. */
    public void generateWorld(TETile[][] world){
        initializeFrame();
        initializeTiles(world);
        aBunchOfRooms(world);
        aBunchOfHallways(world);
        hallwaysStickingOutOfOtherHallways(world);

        /* Draws the 2D array world to the screen */
        ter.renderFrame(world);
    }

}
