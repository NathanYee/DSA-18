public class EditDistance {

    private static int minThree(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }

    private static void printGrid(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                System.out.print(grid[i][j]);
                System.out.print(", ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static int minEditDist(String a, String b) {
        int[][] grid = new int[a.length() + 1][b.length() + 1];

        for (int i = 0; i < grid.length; i++) {
            grid[i][0] = i;
        }

        for (int i = 0; i < grid[0].length; i++) {
            grid[0][i] = i;
        }

        for (int i = 1; i < grid.length; i++) {
            for (int j = 1; j < grid[0].length; j++) {
                // same - keep character
                if (a.charAt(i - 1) == b.charAt(j - 1)) {
                    grid[i][j] = grid[i - 1][j - 1];
                } else { // different - either insert, delete, or replace character
                    int insert = grid[i][j - 1];
                    int delete = grid[i - 1][j];
                    int replace = grid[i - 1][j - 1];

                    // add one to the minimum for the additional move across
                    // the smallest edit distance known
                    grid[i][j] = minThree(insert, replace, delete) + 1;
                }
                printGrid(grid);
            }
        }

        return grid[a.length()][b.length()];
    }
}
