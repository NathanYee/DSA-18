import java.util.LinkedList;
import java.util.List;

/**
 * Board definition for the 8 Puzzle challenge
 */
public class Board {

    private int n;
    public int[][] tiles;

    //TODO
    // Create a 2D array representing the solved board state
    private int[][] goal = {{1,2,3},{4,5,6},{7,8,0}};

    /*
     * Set the global board size and tile state
     */
    public Board(int[][] b) {
        this.tiles = b;
        }

    /*
     * Size of the board 
     (equal to 3 for 8 puzzle, 4 for 15 puzzle, 5 for 24 puzzle, etc)
     */
    private int size() {
        return this.tiles.length;
    }

    /*
     * Returns the manhattan distance for an individual number
     */
    private int manhattanDistance(int i, int j) {
        int num = this.tiles[i][j];
        if (num == 0) {
            return 0;
        }
        int finali = (num - 1) / this.size();
        int finalj = (num - 1) % this.size();
        int result = Math.abs(i - finali) + Math.abs(j - finalj);
        return result;
    }
    /*
     * Sum of the manhattan distances between the tiles and the goal
     */
    public int manhattan() {
        int sum = 0;
        for (int i = 0; i < this.size(); i++) {
            for (int j = 0; j < this.size(); j++) {
//                System.out.print(manhattanDistance(i,j));
                sum += manhattanDistance(i,j);
            }
        }
        return sum;
    }

    /*
     * Compare the current state to the goal state
     */
    public boolean isGoal() {
        for (int i = 0; i < this.size(); i++) {
            for (int j = 0; j < this.size(); j++) {
                if (this.tiles[i][j] != this.goal[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    /*
     * Returns true if the board is solvable
     * Research how to check this without exploring all states
     */
    public boolean solvable() {
        // format board into 1D array
        int flat[] = new int[this.size() * this.size()];
        for (int i = 0; i < this.size(); i++) {
            for (int j = 0; j < this.size(); j++) {
                flat[i*3 + j] = this.tiles[i][j];
            }
        }

        // count number of inversions
        int inversions = 0;
        for (int i = 0; i < flat.length; i++) {
            for (int j = i+1; j < flat.length; j++) {
                if (flat[i] > flat[j] && flat[j] != 0) {
                    inversions++;
                }
            }
        }

        // check if number of inversions is even
        return (inversions % 2 == 0);
    }

    /*
     * checks if a tile location is within the bounds of the board
     */
    private boolean isValidTile(int i, int j) {
        return (i >= 0 && j >= 0 && i < this.size() && j < this.size());
    }

    private Board swapTile(int i0, int j0, int i, int j) {
        // create deep copy of tiles
        int tilesCopy[][] = new int[this.size()][this.size()];
        for (int k = 0; k < this.size(); k++) {
            for (int l = 0; l < this.size(); l++) {
                tilesCopy[k][l] = this.tiles[k][l];
            }
        }

        // create a new board to modify
        Board swapBoard = new Board(tilesCopy);

        // replace 0 with tile
        swapBoard.tiles[i0][j0] = swapBoard.tiles[i][j];

        // replace old tile with 0
        swapBoard.tiles[i][j] = 0;

        return swapBoard;
    }

    /*
     * Return all neighboring boards in the state tree
     */
    public Iterable<Board> neighbors() {
        // locate i and j for blank (zero) tile
        int i0 = 0;
        int j0 = 0;
        rows: for (int i = 0; i < this.size(); i++) {
            cols: for (int j = 0; j < this.size(); j++) {
                if (this.tiles[i][j] == 0) {
                    i0 = i;
                    j0 = j;
                    break rows;
                }
            }
        }

        // iterate through all possible moves for sliding
        List<Board> boards = new LinkedList<>();

        // try to slide tiles horizontally
        for (int i = -1; i < 2; i+=2) { // first i = -1, then i = 1
            if (isValidTile(i0 + i, j0)) {
                boards.add(swapTile(i0, j0, i0 + i, j0));
            }
        }

        // try to slide tiles vertically
        for (int j = -1; j < 2; j+=2) {
            if (isValidTile(i0, j0+j)) {
                boards.add(swapTile(i0, j0, i0, j0 + j));
            }
        }

        return boards;
    }

    /*
     * Check if this board equals a given board state
     */
    @Override
    public boolean equals(Object x) {
        // Check if the board equals an input Board object
        if (x == this) return true;
        if (x == null) return false;
        if (!(x instanceof Board)) return false;
        // Check if the same size
        Board y = (Board) x;
        if (y.tiles.length != n || y.tiles[0].length != n) {
            return false;
        }
        // Check if the same tile configuration
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (this.tiles[i][j] != y.tiles[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        // DEBUG - Your solution can include whatever output you find useful
        int[][] initState = {{1, 2, 3}, {4, 0, 6}, {7, 8, 5}};
        Board board = new Board(initState);

        System.out.println("Size: " + board.size());
        System.out.println("Solvable: " + board.solvable());
        System.out.println("Manhattan: " + board.manhattan());
        System.out.println("Is goal: " + board.isGoal());
        System.out.println("Neighbors:");
        Iterable<Board> it = board.neighbors();
    }
}
