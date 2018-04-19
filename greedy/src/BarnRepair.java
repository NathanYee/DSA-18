import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class BarnRepair {
    public static int solve(int M, int[] occupied) {
        // assume we can't block any gaps
        Arrays.sort(occupied);
        int blocked = occupied[occupied.length - 1] - occupied[0] + 1;

        // find the gaps and store them sequentially
        PriorityQueue<Integer> gaps = new PriorityQueue<>((o1, o2) -> o2 - o1);
        for (int i = 0; i < occupied.length-1; i++) {
            int gap = occupied[i + 1] - occupied[i] - 1;
            if (gap > 0) {
                gaps.offer(gap);
            }
        }

        // remove a board and a gap each iteration
        // if you have a gap and two or more boards, patch the gap
        while (M >= 2 && gaps.peek() != null) {
            blocked -= gaps.poll();
            M--;
        }

        // return remaining number of blocked stalls
        return blocked;
    }
}