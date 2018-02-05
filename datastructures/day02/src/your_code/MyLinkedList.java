package your_code;

public class MyLinkedList {

    private Node head;
    private Node tail;
    private int size;

    private class Node {
        Chicken val;
        Node prev;
        Node next;

        private Node(Chicken d, Node prev, Node next) {
            this.val = d;
            this.prev = prev;
            this.next = next;
        }

        private Node(Chicken d) {
            this.val = d;
            prev = null;
            next = null;
        }
    }

    public MyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(Chicken c) {
        addLast(c);
    }

    public Chicken pop() {
        return removeLast();
    }

    // iterates to index and returns node
    // does not perform error checking
    private Node iterateToIndex(int index) {
        Node current = head;
        for (int i=0; i<index; i++) {
            current = current.next;
        }
        return current;
    }

    private void addEmpty(Chicken c) {
        head = new Node(c, null, null);
        tail = head;
    }

    public void addLast(Chicken c) {
        if (size() == 0) {
            addEmpty(c);
        } else {
            Node last = new Node(c, tail, null);
            tail.next = last;
            tail = last;
        }
        size++;
    }

    public void addFirst(Chicken c) {
        if (size() == 0) {
            addEmpty(c);
        } else {
            Node first = new Node(c, null, head);
            head.prev = first;
            head = first;
        }
        size++;
    }

    public Chicken get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return iterateToIndex(index).val;
    }

    public Chicken remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        // first check if we are removing head or tail
        if (index == 0) { // head
            return removeFirst();
        } else if (index + 1 == size) { // tail
            return removeLast();
        }


        Node current = iterateToIndex(index);
        // because we have already checked that index is not
        // at the head or tail of the linkedList
        // there must be a prev and next node
        current.prev.next = current.next;
        current.next.prev = current.prev;
        size--;
        return current.val;
    }

    public Chicken removeFirst() {
        if (size == 0) {
            return null;
        }
        Node first = head;
        head = head.next;
        size--;
        return first.val;
    }

    public Chicken removeLast() {
        if (size == 0) {
            return null;
        }
        Node last = tail;
        tail = tail.prev;
        size--;
        return last.val;
    }
}