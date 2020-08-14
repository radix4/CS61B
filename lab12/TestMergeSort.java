import edu.princeton.cs.algs4.Queue;
import org.junit.Test;
import static org.junit.Assert.*;

/** This class uses JUnit to test the MergeSort Class. */
public class TestMergeSort {

    /** Returns a queue of size 1 queues, each has 1 item. */
    @Test
    public void testSingleItemQueues(){
        Queue<Integer> numbers = new Queue<>();
        numbers.enqueue(1);
        numbers.enqueue(2);
        numbers.enqueue(3);

    }
}
