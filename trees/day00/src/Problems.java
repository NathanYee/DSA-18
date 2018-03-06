import java.util.*;

public class Problems {

    public static BinarySearchTree<Integer> minimalHeight(List<Integer> values) {
        // TODO
        return new BinarySearchTree<>();
    }

    public static boolean isIsomorphic(TreeNode n1, TreeNode n2) {
        if (n1 == null && n2 == null) {
            return true;
        }
        if (n1 == null || n2 == null) {
            return false;
        }
        if (n1.key == n2.key) {
            return isIsomorphic(n1.leftChild, n2.leftChild) && isIsomorphic(n1.rightChild, n2.rightChild) ||
                    isIsomorphic(n1.leftChild, n2.rightChild) && isIsomorphic(n1.rightChild, n2.leftChild);
        }
        return false;
    }
}