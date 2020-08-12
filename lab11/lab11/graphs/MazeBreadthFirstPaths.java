package lab11.graphs;

import java.util.Queue;
import java.util.PriorityQueue;


/**
 * This program implements the BFS algos for finding the target vertex
 * from the source vertex in a maze.
 *
 *  @author Thang Cao
 */
public class MazeBreadthFirstPaths extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;    // distTo[v] = number of edges shortest s-v path
    public int[] edgeTo;    // edgeTo[v] = previous edge on shortest s-v path
    public boolean[] marked;    // marked[v] = is there an s-v path
    */

    private int s;
    private int t;
    private boolean targetFound = false;
    private Maze maze;
    private static final int INFINITY = Integer.MAX_VALUE;

    /** Upon calling this constructor. */
    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        // Add more variables here!
        this.maze = m;
        this.s = m.xyTo1D(sourceX,sourceY);
        this.t = m.xyTo1D(targetX, targetY);
        this.distTo[s] = 0;
        this.edgeTo[s] = s;
    }

    /** Conducts a breadth first search of the maze starting at the source. */
    private void bfs() {
        // TODO: Your code here. Don't forget to update distTo, edgeTo, and marked, as well as call announce()
        Queue<Integer> queue = new PriorityQueue<>();

        marked[s] = true;   // initial mark
        queue.offer(s);

        while (!queue.isEmpty()) {  // while priority queue is not empty
            int v = queue.poll();   // remove and return a vertex from the queue

            for (int w : maze.adj(v)) {     // iterates through all the neighbor vertices
                if (!marked[w]) {   // if a neighbor vertex is not marked
                    edgeTo[w] = v;  //
                    distTo[w] = distTo[v] + 1;
                    marked[w] = true;
                    queue.offer(w);
                    announce();
                }
            }
        }

    }


    @Override
    public void solve() {
         bfs();
    }
}

