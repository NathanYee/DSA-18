package divide_and_conquer;

import java.util.Arrays;

public class PeakFinding {

    // Return -1 if left is higher, 1 if right is higher, 0 if peak
    private static int peakOneD(int i, int[] nums) {
        if (i > 0 && nums[i] < nums[i - 1])
            return -1;
        if (i < nums.length - 1 && nums[i] < nums[i + 1])
            return 1;
        return 0;
    }


    public static int findOneDPeak(int[] nums) {
        // base cases
        if (nums.length == 1) {
            return 0;
        } else if (nums.length == 2) {
            if (nums[0] > nums[1]) {
                return 0;
            }
            return 1;
        }

        // divide
        int i = nums.length / 2;
        if (nums[i] < nums[i - 1]) {
            return findOneDPeak(Arrays.copyOfRange(nums, 0, i - 1));
        } else if (nums[i] < nums[i + 1]) {
            return i + 1 + findOneDPeak(Arrays.copyOfRange(nums, i + 1, nums.length));
        }

        // get lucky
        return i;
    }

    // Return -1 if left is higher, 1 if right is higher, 0 if peak
    private static int peakX(int x, int y, int[][] nums) {
        if (x > 0 && nums[y][x] < nums[y][x - 1])
            return -1;
        if (x < nums[0].length - 1 && nums[y][x] < nums[y][x + 1])
            return 1;
        return 0;
    }

    // Return -1 if up is higher, 1 if down is higher, 0 if peak
    private static int peakY(int x, int y, int[][] nums) {
        if (y > 0 && nums[y][x] < nums[y - 1][x])
            return -1;
        if (y < nums.length - 1 && nums[y][x] < nums[y + 1][x])
            return 1;
        return 0;
    }

    // These two functions return the index of the highest value along the X or Y axis, with the given
    // value for the other axis. Searches between hi (exclusive) and lo (inclusive)
    private static int maxXIndex(int y, int lo, int hi, int[][] nums) {
        int maxIndex = -1;
        for (int x = lo; x < hi; x++) {
            if (maxIndex == -1 || nums[y][x] > nums[y][maxIndex])
                maxIndex = x;
        }
        return maxIndex;
    }

    private static int maxYIndex(int x, int lo, int hi, int[][] nums) {
        int maxIndex = -1;
        for (int y = lo; y < hi; y++) {
            if (maxIndex == -1 || nums[y][x] > nums[maxIndex][x])
                maxIndex = y;
        }
        return maxIndex;
    }

    // divide rows
    public static int[] findTwoDPeakY(int[][] nums, int x0, int x1, int y0, int y1) {
        int y = (y1 - y0) / 2 + y0;
        int x = maxXIndex(y, x0, x1, nums);

        if (y == 0) {
            return new int[]{y, x};
        }

        int peak = peakY(x, y, nums);
        switch (peak) {
            case -1:
                return findTwoDPeakX(nums, x0, x1, y0, y);
            case 1:
                return findTwoDPeakX(nums, x0, x1, y + 1, y1);
            case 0:
                return new int[]{y, x};
        }
        return null;
    }

    // divide columns
    public static int[] findTwoDPeakX(int[][] nums, int x0, int x1, int y0, int y1) {
        int x = (x1 - x0) / 2 + x0;
        int y = maxYIndex(x, y0, y1, nums);

        if (x == 0) {
            return new int[]{y, x};
        }

        int peak = peakX(x, y, nums);
        switch (peak) {
            case -1:
                return findTwoDPeakY(nums, x0, x, y0, y1);
            case 1:
                return findTwoDPeakY(nums, x+1, x1, y0, y1);
            case 0:
                return new int[]{y, x};
        }
        return null;
    }

    public static int[] findTwoDPeak(int[][] nums) {
        return findTwoDPeakX(nums, 0, nums[0].length, 0, nums.length);
    }

}
