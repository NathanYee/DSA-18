import java.util.HashMap;
import java.util.Map;

public class SplitCoins {

    public static int arraySum(int[] coins) {
        int sum = 0;
        for (int c : coins) {
            sum += c;
        }
        return sum;
    }

    public static int splitCoins(int[] coins) a{
        Map<Key, Integer> DP = new HashMap<>();
        int sum = arraySum(coins);
        int optimalSum = sum / 2;
        int optimalCoins = splitCoinsRec(coins, 0, 0, DP, optimalSum);

        // difference between piles is right pile minus left pile
        return Math.abs((sum - optimalCoins) - optimalCoins);
    }

    private static int splitCoinsRec(int[] coins, int i, int j, Map<Key, Integer> DP, int opt) {
        Key k = new Key(i, j);

        if (DP.containsKey(k)){
            return DP.get(k);
        }

        if (i == coins.length) return j;

        // guess left and right pile
        int l = splitCoinsRec(coins, i + 1, coins[i] + j, DP, opt);
        int r = splitCoinsRec(coins, i + 1, j, DP, opt);

        // determine if left or right pile is better
        if (Math.abs(l - opt) < Math.abs(r - opt)) {
            DP.put(k, l);
        } else {
            DP.put(k, r);
        }

        return DP.get(k);


    }
}
