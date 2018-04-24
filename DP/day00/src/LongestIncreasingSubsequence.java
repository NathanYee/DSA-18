import java.util.Arrays;
import java.util.Collections;

public class LongestIncreasingSubsequence {

    // Runtime: O(N^2)
    // Space: O(N)
    public static int LIS(int[] A) {
        if (A.length == 0) return 0;

        int[] memo = new int[A.length];
        return LISRec(A, memo, 0);
    }

    private static int LISRec(int[] A, int[] memo, int i) {
        if (i == memo.length - 1) return 1;
        if (memo[i] != 0) return memo[i];

        // iterate through current i and remaining j::end
        int max = 1;
        for (int j = i + 1; j < memo.length; j++) {
            // determine the LIS assuming inclusion of A[j]
            int lis = LISRec(A, memo, j);
            memo[j] = lis;

            // if A[i] can be included in LIS make it the new max
            // otherwise, this j's LIS is not a valid LIS. This
            // also makes sure that a higher A[j] followed by smaller
            // numbers will always have a max of 1
            if (A[i] < A[j]) {
                max = Math.max(max, lis + 1);
            }

            // if we are at the beginning, we should always
            // consider the highest lis
            if (i == 0) {
                max = Math.max(max, lis);
            }
        }

        return max;
    }
}