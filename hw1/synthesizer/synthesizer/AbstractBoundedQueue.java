package synthesizer;

public abstract class AbstractBoundedQueue<T> implements BoundedQueue<T> {

    /** protected keyword means only subclasses of this class
     * and classes in the same package can access to this variable.*/
    protected int fillCount;
    protected int capacity;


    @Override
    public int capacity() {
        return capacity;
    }

    @Override
    public int fillCount() {
        return fillCount;
    }

    @Override
    public abstract void enqueue(T x);

    @Override
    public abstract T dequeue();

    @Override
    public abstract T peek();

    @Override
    public boolean isEmpty() {
        return fillCount == 0;
    }

    @Override
    public boolean isFull() {
        return fillCount == capacity;
    }
}
