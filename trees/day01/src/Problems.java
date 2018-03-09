import java.util.*;

public class Problems {

    public static int leastSum(int[] A) {
        HashMap<Integer, Integer> map = new HashMap<>();

        // counter the number each digit appears in array
        for (int a: A) {
            int count = map.containsKey(a) ? map.get(a) : 0;
            map.put(a, count + 1);
        }

        int a = 0;
        int b = 0;
        int k = 0;
        int mult = 1;

        // iterate through all digits in reverse order
        for (int i = 9; i >= 0; i--) {
            int count = map.containsKey(i) ? map.get(i) : 0;
            for (int j = 0; j < count; j++) {
                // alternate adding to each number
                // every other time, mult *= 10
                if (k % 2 == 0) {
                    a += i * mult;
                } else {
                    b += i * mult;
                    mult *= 10;
                }
                k++;
            }
        }

        return a + b;
    }
}
