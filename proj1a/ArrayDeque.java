/** This program demonstrates the implementation of an array-based List
 * @author  Thang Cao
 * @since   06/09/2020
 * */

public class ArrayDeque<T> implements DequeAPI<T>{
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;
    private static final int RFACTOR = 2;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = items.length - 1;
        nextLast = 0;
    }


    public void resize(){
        T[] a = (T[]) new Object[items.length * RFACTOR];

        System.arraycopy(items, nextFirst,a,0,items.length- nextFirst);
        System.arraycopy(items,0,a, nextLast - 1,items.length - nextLast + 1);


        nextFirst = a.length;
        nextLast = items.length - 1;

        items = a;
    }

    public boolean isArrayTooLarge(){
        return ((float) size / items.length < 0.25 && items.length > 16);
    }

    public void shrinkSize(){
        T[] a = (T[]) new Object[items.length/ RFACTOR];
        System.arraycopy(items,0,a,0,items.length/2);

        nextFirst = a.length/2;
        nextLast = items.length/2 - 1;

        items = a;
    }


    /** Decrement the given index by one. */
    public int minusOne(int index){
        index--;

        return index;
    }

    /** Increment the given index by one. */
    public int plusOne(int index){
        index++;

        return index;
    }


    /** Add item to front of the list. */
    @Override
    public void addFirst(T item) {
        if (isEmpty()){
            nextFirst = items.length - 1;
        }

        if (size == items.length){
            resize();
        }

        if (isArrayTooLarge()){
            shrinkSize();
        }


        items[nextFirst] = item;
        nextFirst = minusOne(nextFirst);
        size++;
    }

    /** Add item to back of the list. */
    @Override
    public void addLast(T item) {
        if (isEmpty()){
            nextLast = 0;
        }

        if (size == items.length){
            resize();
        }


        items[nextLast] = item;
        nextLast = plusOne(nextFirst);
        size++;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        int first = nextFirst;
        int last = nextLast;

        while (first != 1){
            if (first == items.length){
                first = 0;
            }

            System.out.println(items[first]);
            first++;
        }

        while (first <= last){
            System.out.println(items[first]);
            first++;
        }



    }

    @Override
    public T removeFirst() {
        T toBeRemoved = items[nextFirst];
        items[nextFirst] = null;
        size--;
        nextFirst++;

        return toBeRemoved;
    }

    @Override
    public T removeLast() {
        T tobeRemoved = items[nextLast];
        items[nextLast] = null;
        nextLast--;
        size--;


        return tobeRemoved;
    }

    @Override
    public T get(int index) {
        if (items[index] == null){
            return null;
        }

        return items[index];
    }

    public static void main(String[] args) {
        ArrayDeque<Integer> items = new ArrayDeque<>();
        items.addFirst(4);
        items.addFirst(3);
        items.addFirst(2);
        items.addFirst(1);
        items.addLast(5);
        items.addLast(6);
        items.addLast(7);
        items.addLast(8);

        System.out.println(items.get(0));

        items.printDeque();

    }
}