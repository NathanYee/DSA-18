import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Collections;
import java.util.Comparator;


public class FrequencyPrint {

    static String frequencyPrint(String s) {
        String[] split = s.split("\\s+");
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        ArrayList<Entry<String, Integer>> tuples = new ArrayList<>();
        Comparator<Entry<String, Integer>> com = Entry.comparingByValue();

        // create map that counts occurrences of strings
        for (String str: split) {
            int count = map.containsKey(str) ? map.get(str) : 0;
            map.put(str, count + 1);
        }

        // create list of tuples
        for (Entry<String, Integer> entry : map.entrySet()) {
            tuples.add(entry);
        }

        // sort tuples by smallest int
        Collections.sort(tuples, com);

        // create return string
        String freqStr = new String("");
        for (Entry<String, Integer> entry : tuples) {
            for (int i = 0; i < entry.getValue(); i++) {
                freqStr = freqStr.concat(entry.getKey());
                freqStr = freqStr.concat(" ");
            }
        }
        freqStr = freqStr.substring(0, s.length());

        return freqStr;
    }

}
