import jdk.nashorn.api.tree.Tree;

import java.util.LinkedList;
import java.util.List;

public class BinarySearchTree<T extends Comparable<T>> {
    TreeNode<T> root;
    private int size;

    public int size() {
        return size;
    }

    public boolean contains(T key) {
        return find(root, key) != null;
    }

    /**
     * Add a node to the BST. Internally calls insert to recursively find the new node's place
     */
    public boolean add(T key) {
        if (find(root, key) != null) return false;
        root = insert(root, key);
        size++;
        return true;
    }

    public void addAll(T[] keys) {
        for (T k : keys)
            add(k);
    }

    /**
     * traverses the BST in sorted order
     * runtime: O(n) - only visit each node once
     */
    public List<T> inOrderTraversal() {
        return inOrderTraversal(root);
    }

    public List<T> inOrderTraversal(TreeNode<T> n) {
        // base case for leaf children
        if (n.isLeaf()) {
            List<T> l = new LinkedList<>();
            l.add(n.key);
            return l;
        }

        // if not a leaf, create new list
        List<T> l = new LinkedList<>();

        // add smaller children
        if (n.leftChild != null) {
            List<T> L = inOrderTraversal(n.leftChild);
            l.addAll(L);
        }

        // add current node
        l.add(n.key);

        // add larger children
        if (n.rightChild != null) {
            List<T> R = inOrderTraversal(n.rightChild);
            l.addAll(R);
        }

        return l;
    }

    /**
     * Deletes a node from the BST using the following logic:
     * 1. If the node has a left child, replace it with its predecessor
     * 2. Else if it has a right child, replace it with its successor
     * 3. If it has no children, simply its parent's pointer to it
     */
    public boolean delete(T key) {
        TreeNode<T> toDelete = find(root, key);
        if (toDelete == null) {
            System.out.println("Key does not exist");
            return false;
        }
        TreeNode<T> deleted = delete(toDelete);
        if (toDelete == root) {
            root = deleted;
        }
        size--;
        return true;
    }

    private TreeNode<T> delete(TreeNode<T> n) {
        // Recursive base case
        if (n == null) return null;

        TreeNode<T> replacement;

        if (n.isLeaf())
            // Case 1: no children
            replacement = null;
        else if (n.hasRightChild() != n.hasLeftChild())
            // Case 2: one child
            replacement = (n.hasRightChild()) ? n.rightChild : n.leftChild; // replacement is the non-null child
        else {
            // Case 3: two children
            // TODO
            replacement = null;
        }

        // Put the replacement in its correct place, and set the parent.
        n.replaceWith(replacement);
        return replacement;
    }

    public T findPredecessor(T key) {
        TreeNode<T> n = find(root, key);
        if (n != null) {
            TreeNode<T> predecessor = findPredecessor(n);
            if (predecessor != null)
                return predecessor.key;
        }
        return null;
    }

    public TreeNode<T> predLeftSubtree(TreeNode<T> n) {
        if (n.rightChild != null) {
            return predLeftSubtree(n.rightChild);
        }
        return n;
    }

    private TreeNode<T> predParents(TreeNode<T> n) {
        if (n.parent == null) {
            return null;
        }
        if (n == n.parent.rightChild) {
            return n.parent;
        }
        return predParents(n.parent);
    }

    private TreeNode<T> findPredecessor(TreeNode<T> n) {
        // if left subtree exists, find rightmost node in left subtree
        if (n.leftChild != null) {
            return predLeftSubtree(n.leftChild);
        }

        // otherwise check if node has parent
        if (n.parent != null) {
            return predParents(n);
        }

        // node has no predecessor
        return null;
    }

    public T findSuccessor(T key) {
        TreeNode<T> n = find(root, key);
        if (n != null) {
            TreeNode<T> successor = findSuccessor(n);
            if (successor != null)
                return successor.key;
        }
        return null;
    }

    private TreeNode<T> succRightSubtree(TreeNode<T> n) {
        if (n.leftChild != null) {
            return succRightSubtree(n.leftChild);
        }
        return n;
    }

    private TreeNode<T> succParents(TreeNode<T> n) {
        // check parent
        if (n.parent != null) {
            // if node is left child of parent, parent is successor
            if (n.parent.leftChild == n) {
                return n.parent;
            }
            // otherwise continue going up the tree
            return succParents(n.parent);
        }
        // if no parent, n is the last node in tree
        return null;
    }

    private TreeNode<T> findSuccessor(TreeNode<T> n) {
        // search right subtree
        if (n.rightChild != null) {
            return succRightSubtree(n.rightChild);
        }

        // search parents
        return succParents(n);
    }

    /**
     * Returns a node with the given key in the BST, or null if it doesn't exist.
     */
    private TreeNode<T> find(TreeNode<T> currentNode, T key) {
        if (currentNode == null)
            return null;
        int cmp = key.compareTo(currentNode.key);
        if (cmp < 0)
            return find(currentNode.leftChild, key);
        else if (cmp > 0)
            return find(currentNode.rightChild, key);
        return currentNode;
    }

    /**
     * Recursively insert a new node into the BST
     */
    private TreeNode<T> insert(TreeNode<T> node, T key) {
        if (node == null) return new TreeNode<>(key);

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.leftChild = insert(node.leftChild, key);
            node.leftChild.parent = node;
        } else {
            node.rightChild = insert(node.rightChild, key);
            node.rightChild.parent = node;
        }
        return node;
    }
}
