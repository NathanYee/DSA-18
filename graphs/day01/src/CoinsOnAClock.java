import java.util.*;
import java.util.stream.IntStream;

public class CoinsOnAClock {

    public enum Coin{
        PENNY (1,'p'),
        NICKEL (5, 'n'),
        DIME (10, 'd');

        private final int value;
        private final char name;

        Coin(int value, char name) {
            this.value = value;
            this.name = name;
        }

        public int getValue() {
            return value;
        }

        public char getName() {
            return name;
        }


    }

    private static boolean placable(char[] clock, int time, int hours) {
        int idx = (time + hours) % clock.length;
        return (clock[idx] == 0);
    }

    // check if one spot is remaining on clock
    private static boolean finalCoin(char[] clock) {
        int count = clock.length;
        for (char c: clock) {
            count -= c == 0 ? 0 : 1;
        }
        return count == 1;
    }

    private static void coinsBacktrack(int[] coins, char[] clock, int time, List<char[]> l) {
        if (IntStream.of(coins).sum() == 0) {
            l.add(Arrays.copyOf(clock, clock.length));
        } else {
            for (int i = 0; i < coins.length; i++) {
                if (coins[i] > 0) {
                    int hours = Coin.values()[i].getValue();
                    if (placable(clock, time, hours) || finalCoin(clock)) {
                        clock[time] = Coin.values()[i].getName();
                        coins[i]--;
                        int oldTime = time;
                        time = (time + hours) % clock.length;
                        coinsBacktrack(coins, clock, time, l);
                        time = oldTime;
                        coins[i]++;
                        clock[time] = 0;
                    }
                }
            }
        }

    }

    public static List<char[]> coinsOnAClock(int pennies, int nickels, int dimes, int hoursInDay) {
        List<char[]> result = new ArrayList<>();
        int[] coins = {pennies, nickels, dimes};
        char[] clock = new char[hoursInDay];
        coinsBacktrack(coins, clock, 0, result);
        return result;
    }
}
