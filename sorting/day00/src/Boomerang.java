import java.util.HashMap;
import java.util.Map;

public class Boomerang {

    private static double eucDist(int[] a, int[] b) {
        return Math.sqrt(Math.pow(a[0] - b[0], 2) + Math.pow(a[1] - b[1], 2));
    }

    public static int numberOfBoomerangs(int[][] points) {
        Map<Double, Integer> distances = new HashMap<Double, Integer>();
        int boomerangs = 0;

        for (int i = 0; i < points.length; i++) {
            // clear distances hashmap
            distances.clear();

            // compute the distances using i as the middle point
            for (int j = 0; j < points.length; j++) {
                double dist = eucDist(points[i], points[j]);
                Integer count = distances.get(dist);

                if (count == null) {
                    distances.put(dist, 1);
                } else {
                    distances.put(dist, count + 1);
                }
            }

            // for all distances with 2 or more - add to boomerangs
            for (Double dist: distances.keySet()) {
                int count = distances.get(dist);

                // avoid duplicate boomerangs,
                // boomerang only when count >= 2
                if (dist > (double) 0 && count >= 2) {
                    boomerangs += count * (count - 1);
                }
            }

        }
        
        return boomerangs;
    }
}

