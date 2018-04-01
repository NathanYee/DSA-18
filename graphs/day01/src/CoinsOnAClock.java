import java.util.*;
import java.util.stream.IntStream;

public class CoinsOnAClock {

    private static int coinValues(int i) {
        if (i == 0) return 1;
        if (i == 1) return 5;
        if (i == 2) return 10;
        return 0;
    }

    private static char coinNames(int i) {
        if (i == 0) return 'p';
        if (i == 1) return 'n';
        if (i == 2) return 'd';
        return 'x';
    }

    // need to allow final coins
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
            return;
        }
//        for (int i = 0; i < clock.length; i++) {
//            if (clock[i] == 0) {
//                break;
//            }
//            if (i == clock.length - 1) {
//
//            }
//        }
//        for (char c : clock) {
//            if (c == 0) break;
//            l.add(Arrays.copyOf(clock, clock.length));
//            return;
//        }

        for (int i = 0; i < coins.length; i++) {
            if (coins[i] > 0) {
                int hours = coinValues(i);
                if (placable(clock, time, hours) || finalCoin(clock)) {
                    clock[time] = coinNames(i);
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

    public static List<char[]> coinsOnAClock(int pennies, int nickels, int dimes, int hoursInDay) {
        List<char[]> result = new ArrayList<>();
        int[] coins = {pennies, nickels, dimes};
        char[] clock = new char[hoursInDay];
        coinsBacktrack(coins, clock, 0, result);
        return result;
    }
}
