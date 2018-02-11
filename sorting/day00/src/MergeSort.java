import java.util.Arrays;

public class MergeSort extends SortAlgorithm {

    private static final int INSERTION_THRESHOLD = 10;
    private static final InsertionSort insertionSort = new InsertionSort();

    /**
     * This is the recursive step in which you split the array up into
     * a left and a right portion, sort them, and then merge them together.
     * Use Insertion Sort if the length of the array is <= INSERTION_THRESHOLD
     *
     * TODO
     * Best-case runtime:
     * Worst-case runtime:
     * Average-case runtime:
     *
     * Space-complexity:
     */

    @Override
    public int[] sort(int[] array) {
        if (array.length < INSERTION_THRESHOLD) {
            return insertionSort.sort(array);
        }

        int[] left = Arrays.copyOfRange(array, 0, (int) (array.length / 2));
        int[] right = Arrays.copyOfRange(array, (int) (array.length / 2), array.length);

        return merge(sort(left), sort(right));
    }

    /**
     * Given two sorted arrays a and b, return a new sorted array containing
     * all elements in a and b. A test for this method is provided in `SortTest.java`
     */
    public int[] merge(int[] a, int[] b) {
        int i = 0, j = 0, k = 0;
        int[] merged = new int[a.length + b.length];

        // merge elements until reach the end of one array
        while (i < a.length && j < b.length) {
            if (a[i] <= b[j]) {
                merged[k++] = a[i++];
            } else {
                merged[k++] = b[j++];
            }
        }

        // fill in the rest from the non-empty array
        System.arraycopy(a, i, merged, k, a.length-i);
        System.arraycopy(b, j, merged, k, b.length-j);

        return merged;
    }

}
