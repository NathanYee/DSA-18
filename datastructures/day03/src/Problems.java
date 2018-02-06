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
        LinkedList<Integer> l = new LinkedList<>();
        int size = A.length - k;

        for (int i=0; i<A.length; i++) {

            while (l.size() > 0 && A[i] < l.getLast() && k > 0) {
                l.removeLast();
                k--;
            }
            if (l.size() < size)
            l.addLast(A[i]);
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
        List<String> l = new ArrayList<>();
        Stack<String> stack = new Stack<>();
        for (int i=0; i<s.length(); i++) {
            char c = s.charAt(i);
            if (c == ' ' || c == '(') {
            } else if (c == '+' || c == '-' || c == '/' || c == '*') {
                stack.push(s.substring(i,i+1));
            } else if (c == ')') { // key insight: whenever we see ')' pop operator off stack
                l.add(stack.pop());
            } else {
                l.add(s.substring(i, i+1));
            }

        }
        return String.join(" ", l);
    }

}
