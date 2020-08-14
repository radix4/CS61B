import edu.princeton.cs.algs4.Queue;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *  A Merge Sort implementation.
 *
 *  @author Thang Cao
 */

public class MergeSort {
    /**
     * Removes and returns the smallest item that is in q1 or q2.
     *
     * The method assumes that both q1 and q2 are in sorted order, with the smallest item first. At
     * most one of q1 or q2 can be empty (but both cannot be empty).
     *
     * @param   q1  A Queue in sorted order from least to greatest.
     * @param   q2  A Queue in sorted order from least to greatest.
     * @return      The smallest item that is in q1 or q2.
     */
    private static <Item extends Comparable> Item getMin(
            Queue<Item> q1, Queue<Item> q2) {
        if (q1.isEmpty()) {
            return q2.dequeue();
        } else if (q2.isEmpty()) {
            return q1.dequeue();
        } else {
            // Peek at the minimum item in each queue (which will be at the front, since the
            // queues are sorted) to determine which is smaller.
            Comparable q1Min = q1.peek();
            Comparable q2Min = q2.peek();
            if (q1Min.compareTo(q2Min) <= 0) {
                // Make sure to call dequeue, so that the minimum item gets removed.
                return q1.dequeue();
            } else {
                return q2.dequeue();
            }
        }
    }

    /** Returns a queue of queues that each contain one item from items. */
    private static <Item extends Comparable> Queue<Queue<Item>>
            makeSingleItemQueues(Queue<Item> items) {
        if (items == null || items.isEmpty()) throw new IllegalArgumentException();

        Queue<Queue<Item>> q = new Queue<>();
        int size = items.size();

        for (int i = 0; i < size; i++) {
            Queue<Item> singleQueue = new Queue<>();    // instantiate a queue with one item
            singleQueue.enqueue(items.dequeue());   // enqueue the item
            q.enqueue(singleQueue);
        }

        return q;
    }

    /** Returns a queue of size 1 queues, each has 1 item. */
    @Test
    public void testMakeSingleItemQueues(){
        Queue<Integer> numbers = new Queue<>();
        numbers.enqueue(1);
        numbers.enqueue(2);
        numbers.enqueue(3);

        Queue<Queue<Integer>> q = MergeSort.makeSingleItemQueues(numbers);

        assertEquals(3, q.size());

        Queue<Integer> q1 = q.dequeue();
        Queue<Integer> q2 = q.dequeue();
        Queue<Integer> q3 = q.dequeue();

        int num1 = q1.peek();
        int num2 = q2.peek();
        int num3 = q3.peek();


        assertEquals(1, num1);
        assertEquals(2, num2);
        assertEquals(3, num3);

        int size1 = q1.size();
        int size2 = q2.size();
        int size3 = q3.size();

        assertEquals(1, size1);
        assertEquals(1, size2);
        assertEquals(1, size3);
    }

    /**
     * Returns a new queue that contains the items in q1 and q2 in sorted order.
     *
     * This method should take time linear in the total number of items in q1 and q2.  After
     * running this method, q1 and q2 will be empty, and all of their items will be in the
     * returned queue.
     *
     * @param   q1  A Queue in sorted order from least to greatest.
     * @param   q2  A Queue in sorted order from least to greatest.
     * @return      A Queue containing all of the q1 and q2 in sorted order, from least to
     *              greatest.
     *
     */
    private static <Item extends Comparable> Queue<Item> mergeSortedQueues(
            Queue<Item> q1, Queue<Item> q2) {
        // Your code here!
        return null;
    }

    /** Returns a Queue that contains the given items sorted from least to greatest. */
    public static <Item extends Comparable> Queue<Item> mergeSort(
            Queue<Item> items) {
        // Your code here!
        return items;
    }


    public static void main(String[] args) {
        Queue<Integer> numbers = new Queue<>();
        numbers.enqueue(5);
        numbers.enqueue(4);
        numbers.enqueue(9);
        numbers.enqueue(6);
        numbers.enqueue(1);

        MergeSort.mergeSort(numbers);

        System.out.println(numbers);

    }
}
