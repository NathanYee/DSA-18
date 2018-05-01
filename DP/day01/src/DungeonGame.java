public class DungeonGame {

    private static void printHealth(int[][] health) {
        for (int i = 0; i < health.length; i++) {
            for (int j = 0; j < health[0].length; j++) {
                System.out.print(health[i][j]);
                System.out.print(", ");
            }
            System.out.println();
        }
        System.out.println();
    }


    // to step on square health[i][[j], you must have had ___ health
    // to get to the lower value next square
    // however, the final value must be greater or equal to 1
    private static int getHealth(int[][] map, int[][] health, int i, int j) {
        int right = health[i][j + 1] - map[i][j];
        int down = health[i + 1][j] - map[i][j];
        return Math.max(Math.min(right, down), 1);
    }

    public static int minInitialHealth(int[][] map) {

        int width = map[0].length;
        int height = map.length;
        int[][] health = new int[height + 1][width + 1];

        // set right column to infinity
        for (int i = 0; i < height + 1; i++) {
            health[i][width] = Integer.MAX_VALUE / 2;
        }

        // set bottom row to infinity
        for (int i = 0; i < width + 1; i++) {
            health[height][i] = Integer.MAX_VALUE / 2;
        }

        // set the destination to:
        // 1 - map[dest] if map is a negative number
        // minimum 1 if map[dest] happens to be positive
        health[height - 1][width - 1] = Math.max(1 - map[height - 1][width - 1], 1);

        for (int i = height - 1; i >= 0; i--) {
            for (int j = width - 1; j >= 0; j--) {

                // skip the destination
                if (i == height - 1 && j == width - 1) {
                    continue;
                }

                // get the health for at the current square
                health[i][j] = getHealth(map, health, i, j);
            }
        }

        return health[0][0];
    }
}
