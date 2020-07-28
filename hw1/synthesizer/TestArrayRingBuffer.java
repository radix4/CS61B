import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer arb = new ArrayRingBuffer(10);
    }

    @Test
    public void testEnqueue() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<>(10);

        for (int i = 0; i < 7; i++) {
            arb.enqueue(i);
        }

        int actual = arb.peek();

        assertEquals(0, actual);
    }

    @Test
    public void testDequeue() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<>(10);

        arb.enqueue(5);
        arb.enqueue(10);
        arb.enqueue(15);

        int actual = arb.dequeue();
        int actual2 = arb.dequeue();

        assertEquals(5, actual);
        assertEquals(10, actual2);

        int size = arb.fillCount();
        assertEquals(1,size);

        ArrayRingBuffer<Integer> arb2 = new ArrayRingBuffer<>(10);
        for (int i = 0; i < arb2.capacity(); i++) {
            arb2.enqueue(i);
        }

        arb2.dequeue();
        arb2.enqueue(5);


    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
}
