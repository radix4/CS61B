package hw4.puzzle;


import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;

/**
 *  An implementation of A* algorithm that solves a word puzzle using Graph data structure.
 *
 *  @author Thang Cao
 */

public class Solver {

    /**
     * 1) SearchNode class is a WorldState.
     * 2) The number of moves made to reach this world state from the initial state.
     * 3) A reference to the previous search node.
     * */
    private static class SearchNode implements Comparable<SearchNode>{
        private WorldState currentState;
        private SearchNode previous;
        private Integer priority;
        private int movesToGetHere;

        public SearchNode(WorldState currentState, SearchNode prev) {
            this.currentState = currentState;
            this.previous = prev;

            if (prev == null) this.movesToGetHere = 0;
            else this.movesToGetHere = previous.movesToGetHere + 1;

            /*this.movesToGetHere = prev == null ? 0 : prev.movesToGetHere + 1; (this also work)*/

            this.priority = currentState.estimatedDistanceToGoal();
        }

        @Override
        public int compareTo(SearchNode o) {
            return this.priority - o.priority;
        }
    }

    private MinPQ<SearchNode> worldStatesPQ = new MinPQ<>();
    private Stack<WorldState> sequenceS = new Stack<>();


    /**
     * Constructor which solves the puzzle, computing
     everything necessary for moves() and solution() to
     not have to solve the problem again. Solves the
     puzzle using the A* algorithm. Assumes a solution exists.
     */
    public Solver(WorldState initial){
        SearchNode current = new SearchNode(initial, null);
        worldStatesPQ.insert(current);
        sequenceS.push(current.currentState);

        while (!current.currentState.isGoal()) {
            for (WorldState state : current.currentState.neighbors()) {
                if (current.previous == null || state.equals(current.previous.currentState)){
                    SearchNode nextNode = new SearchNode(state, current);
                    worldStatesPQ.insert(nextNode);
                }
            }

            current = worldStatesPQ.delMin();
        }

        for (SearchNode node = current; node != null; node = node.previous) {
            sequenceS.push(node.currentState);
        }
    }

    /**
     * Returns the minimum number of moves to solve the puzzle starting
     at the initial WorldState.

     1) (- 1) because do not take into account the initial state move.
     */
    public int moves(){
        return sequenceS.size() - 1;
    }

    /**
     * Returns a sequence of WorldStates from the initial WorldState
     to the solution.
     */
    public Iterable<WorldState> solution(){
        return sequenceS;
    }
}
