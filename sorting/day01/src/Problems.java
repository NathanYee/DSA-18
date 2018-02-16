import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class Problems {

    private static PriorityQueue<Integer> minPQ() {
        return new PriorityQueue<>(11);
    }

    private static PriorityQueue<Integer> maxPQ() {
        return new PriorityQueue<>(11, Collections.reverseOrder());
    }

    private static double getMedian(List<Integer> A) {
        double median = (double) A.get(A.size() / 2);
        if (A.size() % 2 == 0)
            median = (median + A.get(A.size() / 2 - 1)) / 2.0;
        return median;
    }

    // Runtime of this algorithm is O(N^2). Sad! We provide it here for testing purposes
    public static double[] runningMedianReallySlow(int[] A) {
        double[] out = new double[A.length];
        List<Integer> seen = new ArrayList<>();
        for (int i = 0; i < A.length; i++) {
            int j = 0;
            while (j < seen.size() && seen.get(j) < A[i])
                j++;
            seen.add(j, A[i]);
            out[i] = getMedian(seen);
        }
        return out;
    }


    /**
     *
     * @param inputStream an input stream of integers
     * @return the median of the stream, after each element has been added
     */
    public static double[] runningMedian(int[] inputStream) {
        double[] runningMedian = new double[inputStream.length];

        PriorityQueue<Double> maxPQ = new PriorityQueue<Double>(Collections.reverseOrder());
        PriorityQueue<Double> minPQ = new PriorityQueue<Double>();

        for (int i = 0; i < inputStream.length; i++) {
            if (maxPQ.size() == 0) {
                maxPQ.offer((double) inputStream[i]);
                runningMedian[i] = (double) maxPQ.peek();
                continue;
            }
            if (minPQ.size() == 0) {
                if (maxPQ.peek() >= inputStream[i]) {
                    minPQ.offer(maxPQ.poll());
                    maxPQ.offer((double) inputStream[i]);
                } else {
                    minPQ.offer((double) inputStream[i]);
                }
                runningMedian[i] = (double) (maxPQ.peek() + minPQ.peek()) / 2;
                continue;
            }

            // if equal sized PQs
            if (maxPQ.size() == minPQ.size()) {
                // add to queue
                if (maxPQ.peek() >= inputStream[i]) {
                    maxPQ.offer((double) inputStream[i]);
                } else {
                    minPQ.offer((double) inputStream[i]);
                }

                // add to runningMedian
                if (maxPQ.size() > minPQ.size()) {
                    runningMedian[i] = (double) maxPQ.peek();
                } else {
                    runningMedian[i] = (double) minPQ.peek();
                }
            } else if (maxPQ.size() > minPQ.size()) { // if maxPQ is larger
                // if it should go in maxPQ
                if (maxPQ.peek() >= inputStream[i]) {
                    maxPQ.offer((double) inputStream[i]);
                    minPQ.offer(maxPQ.poll());
                } else {
                    minPQ.offer((double) inputStream[i]);
                }

                // add to runningMedian
                runningMedian[i] = (double) (maxPQ.peek() + minPQ.peek()) / 2;

            } else { // minPQ is larger
                // if it should go in minPQ
                if (minPQ.peek() < inputStream[i]) {
                    maxPQ.offer(minPQ.poll());
                    minPQ.offer((double) inputStream[i]);
                } else {
                    maxPQ.offer((double) inputStream[i]);
                }

                // add to runningMedian
                runningMedian[i] = (double) (maxPQ.peek() + minPQ.peek()) / 2;
            }
        }

        return runningMedian;
    }

}
