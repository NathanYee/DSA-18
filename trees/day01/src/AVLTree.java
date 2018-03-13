public class AVLTree<T extends Comparable<T>> extends BinarySearchTree<T> {

    /**
     * Delete a key from the tree rooted at the given node.
     */
    @Override
    TreeNode<T> delete(TreeNode<T> n, T key) {
        n = super.delete2(n, key);
        if (n != null) {
            // update the height of the tree using the height of the left and right child
            n.height = calcHeight(n);
            return balance(n);
        }
        return null;
    }

    /**
     * Insert a key into the tree rooted at the given node.
     */
    @Override
    TreeNode<T> insert(TreeNode<T> n, T key) {
        n = super.insert(n, key);
        if (n != null) {
            // update the height of the tree using the height of the left and right child
            n.height = calcHeight(n);
            return balance(n);
        }
        return null;
    }

    /**
     * Delete the minimum descendant of the given node.
     */
    @Override
    TreeNode<T> deleteMin(TreeNode<T> n) {
        n = super.deleteMin(n);
        if (n != null) {
            n.height = calcHeight(n);
            return balance(n);
        }
        return null;
    }

    private int calcHeight(TreeNode<T> n) {
        return 1 + Math.max(height(n.leftChild), height(n.rightChild));
    }

    // Return the height of the given node. Return -1 if null.
    private int height(TreeNode<T> n) {
        if (n == null) return -1;
        return n.height;
    }

    // Restores the AVL tree property of the subtree. Return the head of the new subtree
    TreeNode<T> balance(TreeNode<T> n) {
        if (balanceFactor(n) == 2) {
            if (balanceFactor(n.rightChild) <= -1) {
                n.rightChild = rotateRight(n.rightChild);
            }
            n = rotateLeft(n);
        }
        if (balanceFactor(n) == -2) {
            if (balanceFactor(n.leftChild) >= 1) {
                n.leftChild = rotateLeft(n.leftChild);
            }
            n = rotateRight(n);
        }

        return n;
    }

    /**
     * Returns the balance factor of the subtree. The balance factor is defined
     * as the difference in height of the left subtree and right subtree, in
     * this order. Therefore, a subtree with a balance factor of -1, 0 or 1 has
     * the AVL property since the heights of the two child subtrees differ by at
     * most one.
     */
    private int balanceFactor(TreeNode<T> n) {
        return height(n.rightChild) - height(n.leftChild);
    }

    /**
     * Perform a right rotation on node `n`. Return the head of the rotated tree.
     */
    private TreeNode<T> rotateRight(TreeNode<T> n) {
        TreeNode<T> x = n.leftChild;
        n.leftChild = x.rightChild;
        x.rightChild = n;

        // recalculate heights
        n.height = calcHeight(n);
        x.height = calcHeight(x);

        return x;
    }

    /**
     * Perform a left rotation on node `n`. Return the head of the rotated tree.
     */
    private TreeNode<T> rotateLeft(TreeNode<T> n) {
        TreeNode<T> z = n.rightChild;
        n.rightChild = z.leftChild;
        z.leftChild = n;

        // recalculate heights
        n.height = calcHeight(n);
        z.height = calcHeight(z);

        return z;
    }
}
