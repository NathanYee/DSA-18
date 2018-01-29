public class MyArrayList {
    private Cow[] elems;
    private int size;

    private boolean indexOutOfBounds(int index){
        return (index < 0 || index > size);
    }

    // Runtime: O(1)
    public MyArrayList() {
        elems = new Cow[10];
        size = 0;
    }

    // Runtime: O(n) - creation of array of length n is O(n)
    public MyArrayList(int capacity) {
        elems = new Cow[capacity];
        size = 0;
    }

    // Runtime: O(1)* - amoritization should bring it from O(n) to O(1)
    public void add(Cow c) {
        // check if array needs to be expanded, if so double size and copy
        if (size == elems.length) {
            Cow[] tempArray = new Cow[elems.length * 2];
            System.arraycopy(elems, 0, tempArray, 0, elems.length);
            elems = tempArray;
        }

        elems[size] = c;
        size++;
    }

    // Runtime: O(1)
    public int size() {
        return size;
    }

    // Runtime: O(1) - indexing into array is constant time
    public Cow get(int index) {
        if (indexOutOfBounds(index)){
            throw new IndexOutOfBoundsException();
        }
        return elems[index];
    }

    // Runtime: O(n)
    // TODO: shrink the size of the array to save memory if size = elems.length / 4
    public Cow remove(int index) {
        if (indexOutOfBounds(index)){
            throw new IndexOutOfBoundsException();
        }
        Cow cagedCow = elems[index];

        System.arraycopy(elems, index+1, elems, index, elems.length - index - 1);

        size --;
        return cagedCow;
    }

    // Runtime: O(n) - dependent on the length of the array twice
    public void add(int index, Cow c) {
        // check if array needs to be expanded, if so double size and copy
        // TODO: insert cow at index and array copy around it for faster runtime
        if (size == elems.length) {
            Cow[] tempArray = new Cow[elems.length * 2];
            System.arraycopy(elems, 0, tempArray, 0, elems.length);
            elems = tempArray;
        }

        System.arraycopy(elems, index, elems, index+1, size-index);
        elems[index] = c;
        size++;
    }
}
