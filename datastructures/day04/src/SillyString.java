import java.util.HashMap;
import java.util.Map;

public class SillyString {
    private final String innerString;

    public SillyString(String innerString) {
        this.innerString = innerString;
    }

    public String toString() {
        return innerString;
    }

    @Override
    public boolean equals(Object other) {
        return this.toString().equals(other.toString());
    }

    @Override
    public int hashCode() {
        // strings of the same length that contain the same characters hash to the same value
        // O(N) time complexity
        int total = 0;
        for (int i=0; i<innerString.length(); i++) {
            total += innerString.charAt(i);
        }
        return total;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        SillyString first = new SillyString("Hello");
        SillyString second = new SillyString("World");

        System.out.println(first.hashCode());
        System.out.println(second.hashCode());
    }
}
