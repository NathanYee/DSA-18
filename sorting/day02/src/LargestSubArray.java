import java.util.HashMap;

public class LargestSubArray {

    static int arrayLen(int[] arr) {
        return arr[1] - arr[0];
    }

    static int[] largestSubarray(int[] nums) {
        int count = 0;
        int[] subArr = new int[]{0, 0};
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

        // If at any point count = 0, there have been an equal number of 1's and 0's
        // The starting index of that sub array must start at 0.
        // So, add initial value of count=0,i=0 to the hashmap
        map.put(0, 0);

        // now iterate through the array one time
        for (int i = 0; i < nums.length; i++) {
            // add or subtract to running count
            count += nums[i] == 1 ? 1 : -1;

            if (map.containsKey(count)) { // check if already seen count
                int[] tmpArr = new int[]{map.get(count), i};
                // check if range is largest seen
                if (arrayLen(tmpArr) > arrayLen(subArr)) {
                    subArr = tmpArr;
                }
            } else { // add count to the map
                // because a 0,0 was inserted into count and we are using a running count,
                // we need act as if the array has been shifted by 1. So use i + 1 as the index
                map.put(count, i + 1);
            }
        }

        return subArr;
    }
}
