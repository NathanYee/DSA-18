package your_code;
import ADTs.StackADT;

import java.util.LinkedList;

/**
 * An implementation of the Stack interface.
 */
public class MyStack implements StackADT<Integer> {

    private LinkedList<Integer> mS;
    private LinkedList<Integer> ll;

    public MyStack() {
        mS = new LinkedList<>();
        ll = new LinkedList<>();
    }

    @Override
    public void push(Integer e) {
        // check if stack is empty
        if (ll.size() == 0) {
            mS.addFirst(e);
        } else {
            // check if e is smaller than current max
            if (mS.getFirst() > e) {
                mS.addFirst(mS.getFirst());
            } else { // if greater, e becomes new max on stack
                mS.addFirst(e);
            }
        }
        ll.addFirst(e);
    }

    @Override
    public Integer pop() {
        mS.removeFirst();
        Integer pop = ll.removeFirst();
        return pop;
    }

    @Override
    public boolean isEmpty() {
        return ll.isEmpty();
    }

    @Override
    public Integer peek() {
        return ll.getFirst();
    }

    public Integer maxElement() {
        return mS.getFirst();
    }
}
