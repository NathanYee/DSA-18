package your_code;

import ADTs.QueueADT;
import ADTs.StackADT;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

/**
 * An implementation of a priority Queue
 */
public class MyPriorityQueue {

    private ArrayList <Integer> al = new ArrayList();

    public void enqueue(int item) {
        al.add(item);
    }

    public int dequeue() {
        return al.remove(0);
    }

    public boolean isEmpty() {
        return al.isEmpty();
    }

    public int front() { return al.get(0); }

    /**
     * Return and remove the largest item on the queue.
     */
    public int dequeueMax() {
        int maxIndex = al.indexOf(Collections.max(al));
        return al.remove(maxIndex);
    }

}

