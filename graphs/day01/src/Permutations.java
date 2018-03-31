import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Permutations {

    private static void backtrack(LinkedList<Integer> cur, Set<Integer> unused, List<List<Integer>> perms) {
        if (unused.isEmpty()) {
            perms.add(new LinkedList<>(cur));
        }

        for (int u : new LinkedList<>(unused)) {
            cur.addLast(u);
            unused.remove(u);
            backtrack(cur, unused, perms);
            unused.add(u);
            cur.removeLast();
        }
    }

    public static List<List<Integer>> permutations(List<Integer> A) {
        LinkedList<Integer> curr = new LinkedList<>();
        Set<Integer> unused = new HashSet<>(A);
        List<List<Integer>> permutations = new LinkedList<>();
        backtrack(curr, unused, permutations);
        return permutations;
    }

}
