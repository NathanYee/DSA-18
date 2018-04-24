import java.util.HashMap;

public class DiceRollSum {

    // Runtime: O(N)
    // Space: O(N)
    public static int diceRollSum(int N) {

        HashMap<Integer, Integer> memo = new HashMap<>();

        return diceRollRec(N, memo);
    }

    private static int diceRollRec(int N, HashMap<Integer, Integer> memo) {
        if (memo.containsKey(N)) return memo.get(N);
        if (N < 0) return 0;
        if (N == 0) return 1;

        int accumulator = 0;
        for (int i = 1; i <= 6; i++) {
            int subroll = diceRollRec(N - i, memo);
            memo.put(N - i, subroll);
            accumulator += subroll;
        }
        return accumulator;
    }

}
