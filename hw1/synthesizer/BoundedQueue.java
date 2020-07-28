import java.util.Iterator;

/** An interface cannot implements another interface
 * Can only extends */
public interface BoundedQueue<T> extends Iterable<T>{
    int capacity();     // return size of the buffer
    int fillCount();     // return the number of items currently in the buffer
    void enqueue( T x);     //  add item x to the end
    T dequeue();    // delete and return item from the front
    T peek();       // return (but do not delete) item from the front

    default boolean isEmpty(){
        return fillCount() == 0;
    }

    default boolean isFull(){
        return capacity() == fillCount();
    }

    // this is an abstract method
    // abstract classes that implement this interface class
    // do not need to override
    Iterator<T> iterator();
}
