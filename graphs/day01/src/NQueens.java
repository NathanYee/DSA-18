import java.util.ArrayList;
import java.util.List;

public class NQueens {


    /**
     * Checks the 45° and 135° diagonals for an existing queen. For example, if the board is a 5x5
     * and you call checkDiagonal(board, 3, 1), The positions checked for an existing queen are
     * marked below with an `x`. The location (3, 1) is marked with an `o`.
     *
     * ....x
     * ...x.
     * x.x..
     * .o...
     * .....
     *
     * Returns true if a Queen is found.
     *
     * Do not modify this function (the tests use it)
     */
    public static boolean checkDiagonal(char[][] board, int r, int c) {
        int y = r - 1;
        int x = c - 1;
        while (y >= 0 && x >= 0) {
            if (board[y][x] == 'Q') return true;
            x--;
            y--;
        }
        y = r - 1;
        x = c + 1;
        while (y >= 0 && x < board[0].length) {
            if (board[y][x] == 'Q') return true;
            x++;
            y--;
        }
        return false;
    }

    public static boolean checkRow(char[][] board, int r, int c) {
        // check rows above of current row
        for (int i = 0; i < r; i++) {
            if (board[i][c] == 'Q') return true;
        }
        return false;
    }

    public static boolean checkSpace(char[][] board, int r, int c) {
        return (checkDiagonal(board, r, c) || checkRow(board, r, c));
    }


    /**
     * Creates a deep copy of the input array and returns it
     */
    private static char[][] copyOf(char[][] A) {
        char[][] B = new char[A.length][A[0].length];
        for (int i = 0; i < A.length; i++)
            System.arraycopy(A[i], 0, B[i], 0, A[0].length);
        return B;
    }

    private static void nQueensBacktrack(char[][] board, int n, int row, List<char[][]> l) {
        /* base case - all rows have been filled */
        if (row >= n) {
            l.add(copyOf(board));
            return;
        }

        for (int i = 0; i < n; i++) {
            if (! checkSpace(board, row, i)) {
                board[row][i] = 'Q';
                nQueensBacktrack(board, n, row + 1, l);
                board[row][i] = '.';
            }
        }
        
    }


    public static List<char[][]> nQueensSolutions(int n) {
        // TODO
        List<char[][]> answers = new ArrayList<>();
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }
        nQueensBacktrack(board, n, 0, answers);
        return answers;
    }

}
