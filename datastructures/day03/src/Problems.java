import java.util.*;

public class Problems {

    public static class Node {
        int val;
        Node next;

        Node(int d) {
            this.val = d;
            next = null;
        }
    }

    public static List<Integer> removeKDigits(int[] A, int k) {

        int needed = A.length - k;
        int remaining = A.length;

        // For now, return a List that's correct size, but contains only 0s
        List<Integer> l = new LinkedList<>();
        for (int i = 0; i < A.length - k; i++) {
            // if we just have enough integers left, go ahead and fill the array
            // this makes sure we don't return an undersized array
            if (remaining == needed) {
                l.add(A[i]);
            } else if (A[i] > 0) {
                l.add(A[i]);
                needed--;
                remaining--;
            } else {
                remaining--;
            }
        }
        return l;
    }

    public static boolean isPalindrome(Node n) {

        // first find the length of list for later use
        int len = 0;
        Node curr = n;
        while (curr != null) {
            curr = curr.next;
            len++;
        }

        // reverse first half of list
        Node prev = null;
        curr = n;
        Node next;
        for (int i = 0; i < (int) len / 2; i++) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        // then assign new heads
        Node head = prev;
        Node head2;
        if (len % 2 == 0) {
            head2 = curr;
        } else {
            head2 = curr.next;
        }

        // now iterate through both halves of the list
        // to check if palindromic
        while (head != null) {
            if (head.val != head2.val) {
                return false;
            }
            head = head.next;
            head2 = head2.next;
        }
        return true;
    }

    public static String infixToPostfix(String s) {
        // TODO
        return null;
    }

}
