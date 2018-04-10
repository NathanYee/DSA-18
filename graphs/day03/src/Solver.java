/**
 * Solver definition for the 8 Puzzle challenge
 * Construct a tree of board states using A* to find a path to the goal
 */

import java.util.*;

public class Solver {

    public int minMoves = -1;
    private State solutionState;
    private boolean solved = false;

    /**
     * State class to make the cost calculations simple
     * This class holds a board state and all of its attributes
     */
    private class State {
        // Each state needs to keep track of its cost and the previous state
        private Board board;
        private int moves; // equal to g-cost in A*
        public int cost; // equal to f-cost in A*
        private State prev;

        public State(Board board, int moves, State prev) {
            this.board = board;
            this.moves = moves;
            this.prev = prev;
            // TODO
            cost = 0;
        }

        public int totalCost() {
            return this.moves + this.cost;
        }

        @Override
        public boolean equals(Object s) {
            if (s == this) return true;
            if (s == null) return false;
            if (!(s instanceof State)) return false;
            return ((State) s).board.equals(this.board);
        }

        @Override
        public int hashCode() {
            return this.board.tiles.hashCode();
        }
    }

    /*
     * Return the root state of a given state
     */
    private State root(State state) {
        // TODO: Your code here
        return null;
    }

    private Iterable<State> neighbors(State q) {
        Iterable<Board> boards = q.board.neighbors();
        List<State> states = new LinkedList<>();
        for (Board board: boards) {
            State u = new State(board, q.moves + 1, q);
            u.cost = u.board.manhattan();
            states.add(u);
        }
        return states;
    }

    /*
     * A* Solver
     * Find a solution to the initial board using A* to generate the state tree
     * and a identify the shortest path to the the goal state
     */
    public Solver(Board initial) {
        if (!initial.solvable()) {
            return;
        }

        Queue<State> open = new PriorityQueue<>(5, (a,b) -> a.totalCost() - b.totalCost());
        Map<State, Integer> minCostVisited = new HashMap<>();
        open.add(new State(initial, 0, null));

        while (open.peek() != null) {
            State q = open.poll();

            Iterable<State> successors = neighbors(q);

            // look at each successor
            for (State u: successors) {
                if (u.board.isGoal()) {
                    this.minMoves = u.moves;
                    this.solved = true;
                    return;
                }

                // check if we have visited u
                // if visited cost is higher than u
                // re-visit u
                if (minCostVisited.containsKey(u)) {
                    if (minCostVisited.get(u) > u.totalCost()) {
                        open.add(u);
                    }
                } else { // we have never visited u - add to open
                    open.add(u);
                }
            }

            // add q to minCostVisited - as it is a visited node
            minCostVisited.put(q, q.totalCost());
        }
    }

    /*
     * Is the input board a solvable state
     * Research how to check this without exploring all states
     */
    public boolean isSolvable() {
        return this.solved;
    }

    /*
     * Return the sequence of boards in a shortest solution, null if unsolvable
     */
    public Iterable<Board> solution() {
        // TODO: Your code here
        return null;
    }

    public State find(Iterable<State> iter, Board b) {
        for (State s : iter) {
            if (s.board.equals(b)) {
                return s;
            }
        }
        return null;
    }

    /*
     * Debugging space
     */
    public static void main(String[] args) {
        int[][] initState = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
        Board initial = new Board(initState);

        Solver solver = new Solver(initial);
        System.out.print(solver.minMoves);
    }


}
