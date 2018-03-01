import java.util.stream.IntStream;

public class CountingSort {

    /**
     * Use counting sort to sort positive integer array A.
     * Runtime: O(n + k)
     * Space: O(k)
     *
     * k: maximum element in array A
     */
    static void countingSort(int[] A) {
        // create array to store counts of each int
        int k = IntStream.of(A).max().getAsInt();
        int[] counts = new int[k + 1];

        // count each int
        for (int i : A) {
            counts[i]++;
        }

        // write ints to original array
        int l = 0; // position in A
        for (int i = 0; i < counts.length; i++) {
            for (int j = 0; j < counts[i]; j++) {
                A[l] = i;
                l++;
            }
        }
    }
}
