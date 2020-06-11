
/**
 * This program demonstrates the implementation of an array-based List
 *
 * @author Thang Cao
 * @since 06/09/2020
 */

public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;
    private static final int RFACTOR = 2;

    /**
     * Constructor:
     * 1) Creates a default array of capacity = 8.
     * 2) Default size = 0 (empty deque)
     * 3) Setting nextFirst to point to the end of the deque.
     * 4) Setting nextLast at the very front of the deque.
     */
    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = items.length - 1;
        nextLast = 0;
    }


    /**
     * Expand the deque if run out of space when adding items to deque.
     * 1) Create a bigger array.
     * 2) Splits the previous array into two, in order to re-arrange.
     */
    private void resize(int newCapacity) {
        T[] biggerArray = (T[]) new Object[newCapacity];

        int numOfFirstItems = (items.length - 1) - nextFirst;
        int numOfLastItems = items.length - numOfFirstItems;
        int firstPosition = nextFirst + 1;
        int lastPosition = numOfFirstItems;

        System.arraycopy(items, firstPosition, biggerArray, 0, numOfFirstItems);
        System.arraycopy(items, 0, biggerArray, lastPosition, numOfLastItems);


        nextFirst = biggerArray.length - 1;
        nextLast = items.length;

        items = biggerArray;
    }

    /**
     * Return true if the deque's size is less than 25% of the deque total capacity.
     * Deque's capacity of 16 or less is fine.
     */
    private boolean isArrayTooLarge() {
        return ((float) size / items.length < 0.25 && items.length > 16);
    }


    /**
     * Reduce the size of the deque by a factor of 2.
     * If the total number of items in the list does not exceed
     * 25% of the whole list.
     */
    private void shrinkSize() {
        T[] a = (T[]) new Object[items.length / RFACTOR];
        System.arraycopy(items, 0, a, 0, items.length / 2);

        nextFirst = a.length / 2;
        nextLast = items.length / 2 - 1;

        items = a;
    }


    /**
     * Decrement the given index by one.
     */
    private int minusOne(int index) {
        index--;

        return index;
    }

    /**
     * Increment the given index by one.
     */
    private int plusOne(int index) {
        index++;

        return index;
    }


    /**
     * Add item to front of the list.
     */

    public void addFirst(T item) {
        if (isEmpty()) {
            nextFirst = items.length - 1;
        }

        if (size == items.length) {
            resize(items.length * RFACTOR);
        }

        items[nextFirst] = item;
        nextFirst = minusOne(nextFirst);
        size++;
    }

    /**
     * Add item to back of the list.
     */

    public void addLast(T item) {
        if (isEmpty()) {
            nextLast = 0;
        }

        if (size == items.length) {
            resize(items.length * RFACTOR);
        }


        items[nextLast] = item;
        nextLast = plusOne(nextLast);
        size++;
    }


    /**
     * Return true if list is empty.
     */

    public boolean isEmpty() {
        return (size == 0);
    }

    /**
     * Return the size of the list.
     */

    public int size() {
        return size;
    }

    private T[] tempSortedArray() {
        T[] temp = (T[]) new Object[items.length];

        int numOfFirstItems = (items.length - 1) - nextFirst;
        int numOfLastItems = items.length - numOfFirstItems;
        int firstPosition = nextFirst + 1;
        int lastPosition = numOfFirstItems;

        System.arraycopy(items, firstPosition, temp, 0, numOfFirstItems);
        System.arraycopy(items, 0, temp, lastPosition, numOfLastItems);

        return temp;
    }

    /**
     * Print all items in the deque (first to last)
     */

    public void printDeque() {
        T[] temp = tempSortedArray();

        for (T item : temp) {
            if (item == null) {
                continue;
            }
            System.out.print(item + " ");
        }

    }


    /** Check if first or last pointer is empty. */
    private boolean firstOrLastEmpty(){
        return (nextFirst == items.length - 1 || nextLast == 0);
    }

    /**
     * Remove the first item in the deque.
     */
    public T removeFirst() {
        if (isEmpty()){
            return null;
        }

        T toBeRemoved;

        if (firstOrLastEmpty()){
            nextFirst = 0;
            toBeRemoved = items[nextFirst];
            items[nextFirst] = null;

        } else {
            toBeRemoved = items[nextFirst + 1];
            items[nextFirst + 1] = null;
            nextFirst = plusOne(nextFirst);
        }
        size = minusOne(size);
        nextFirst = plusOne(nextFirst);

        if (isArrayTooLarge()) {
            shrinkSize();
        }

        return toBeRemoved;
    }

    /**
     * Remove the last item in the deque.
     * Check if array is too large (for efficiency).
     */

    public T removeLast() {
        T tobeRemoved = items[nextLast-1];
        items[nextLast-1] = null;
        nextLast = minusOne(nextLast);
        size = minusOne(size);

        if (isArrayTooLarge()) {
            shrinkSize();
        }

        return tobeRemoved;
    }

    /**
     * Return the given index's item.
     */

    public T get(int index) {
        T[] temp = tempSortedArray();

        return temp[index];
    }

    public static void main(String[] args) {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        deque.addLast(0);
        deque.addLast(1);
        deque.addLast(2);
        System.out.println(deque.removeFirst());

    }
}
