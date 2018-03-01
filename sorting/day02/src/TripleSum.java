import java.util.HashMap;

public class TripleSum {

    static int tripleSum(int arr[], int sum) {
        int count = 0;
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>(); // values, index

        // create a hashmap of array values (keys) to indices (values)
        for (int i = 0; i < arr.length; i++) {
            map.put(arr[i], i);
        }

        // loop through all possible pairs, with repeats
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {

                // check if the map contains the needed int to complete the triple
                // and that it is not a repeat idx
                int needed = sum - arr[i] - arr[j]; // the needed int
                if (map.containsKey(needed)) {
                    int idx = map.get(needed);
                    if (idx != i && idx != j) { // repeat idx check
                        count += 1;
                    }
                }
            }
        }

        // divide the count by 3 to remove all duplicate triples
        return count / 3;
    }
}
