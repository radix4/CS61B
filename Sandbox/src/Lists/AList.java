package Lists;

/** Invariants:
 * addLast: The next item we want to add, will go into position size.
 * getLast: The item we want to return is in position size -1
 * size: the number of items in the list should be size
 * */

public class AList {

    private int size;
    private int[] array;


    /** Creates an empty list. */
    public AList() {
        array = new int[100];
        size = 0;
    }

    /** Resize the underlying to target capacity */
    private void resize(int capacity){
        int[] a = new int[capacity];
        System.arraycopy(array,0,a,0,size);
        array = a;
    }

    /** Inserts X into the back of the list. */
    public void addLast(int x) {
        if (size == array.length){
            resize(size+1);
        }

        array[size] = x;
        size++;
    }

    /** Returns the item from the back of the list. */
    public int getLast() {
        return array[size - 1];
    }
    /** Gets the ith item in the list (0 is the front). */
    public int get(int i) {
        return array[i];
    }

    /** Returns the number of items in the list. */
    public int size() {
        return size;
    }

    /** Deletes item from back of the list and
     * returns deleted item. */
    public int removeLast() {
        int deletedItem = getLast();

        // array[size - 1] = 0, won't hurt, but not necessary
        size--;

        return deletedItem;
    }
}