package lab11.graphs;

/**
 *  @author Josh Hug
 */
public class BreadthFirstDemo {
    /* Runs a breadth first search from (1, 1) to (N, N) on the graph in the config file. */
    
    public static void main(String[] args) {
        Maze maze = new Maze("lab11/graphs/maze.txt");

        int startX = 5;
        int startY = 5;
        int targetX = maze.N();
        int targetY = maze.N();

        MazeExplorer mbfp = new MazeBreadthFirstPaths(maze, startX, startY, targetX, targetY);
        mbfp.solve();
    }

}
