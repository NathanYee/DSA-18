public class DungeonGame {

    public static int minInitialHealth(int[][] map) {

        int[][] currHealth = new int[map.length][map[0].length];
        int[][] minHealth = new int[map.length][map[0].length];

        // upper left corner
        minHealth[0][0] = Math.max(1 - map[0][0], 1);
        currHealth[0][0] = minHealth[0][0] + map[0][0];

        // top row
        for (int i = 1; i < map[0].length; i++) {
            int lastMinHealth = minHealth[0][i - 1];
            int lastCurrHealth = currHealth[0][i - 1];

            // check to see if we currently have any health that we can use
            if (lastCurrHealth + map[0][i] > 0) {
                minHealth[0][i] = lastMinHealth;
            } else {
                minHealth[0][i] = lastMinHealth + 1 - lastCurrHealth - map[0][i];
            }

            // update current health
            currHealth[0][i] = Math.max(lastCurrHealth + map[0][i], 1);
        }

        // left column
        for (int i = 1; i < map.length; i++) {
            int lastMinHealth = minHealth[i - 1][0];
            int lastCurrHealth = currHealth[i - 1][0];

            // check to see if we currently have any health that we can use
            if (lastCurrHealth + map[i][0] > 0) {
                minHealth[i][0] = lastMinHealth;
            } else {
                minHealth[i][0] = lastMinHealth + 1 - lastCurrHealth - map[i][0];
            }

            // update current health
            currHealth[i][0] = Math.max(lastCurrHealth + map[i][0], 1);
        }

        // rest
        for (int i = 1; i < map.length; i++) {
            for (int j = 1; j < map[0].length; j++) {

                // determine what was the last min health and last current health
                int lastMinHealth;
                int lastCurrHealth;

                // when on the last one - if map[i][j] is positive, we should always pick minHealth
                // otherwise we might want the one that has overall more health
                if (minHealth[i - 1][j] > minHealth[i][j - 1]) {
                    lastMinHealth = minHealth[i][j - 1];
                    lastCurrHealth = currHealth[i][j - 1];
                } else if (minHealth[i - 1][j] < minHealth[i][j - 1]) {
                    lastMinHealth = minHealth[i - 1][j];
                    lastCurrHealth = currHealth[i - 1][j];
                } else {
                    lastMinHealth = minHealth[i][j - 1];
                    lastCurrHealth = Math.max(currHealth[i][j - 1], currHealth[i - 1][j]);
                }

                // check to see if we currently have any health that we can use
                if (lastCurrHealth + map[i][j] > 0) {
                    minHealth[i][j] = lastMinHealth;
                } else {
                    minHealth[i][j] = lastMinHealth + 1 - lastCurrHealth - map[i][j];
                }

                currHealth[i][j] = Math.max(lastCurrHealth + map[i][j], 1);
            }
        }

        return minHealth[map.length - 1][map[0].length - 1];
    }
}
