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

    /** @return      a queue of queues that each contain one item. */
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
        if ((q1 == null || q1.isEmpty()) && (q2 == null || q2.isEmpty()))
            throw new IllegalArgumentException();
        if (q1 == null || q1.isEmpty()) return q2;
        if (q2 == null || q2.isEmpty()) return q1;

        Queue<Item> result = new Queue<>();

        while (!(q1.isEmpty() && q2.isEmpty())) {
            result.enqueue(MergeSort.getMin(q1,q2));
        }

        return result;
    }

    @Test
    public void testMergeSortedQueues(){
        Queue<Integer> numbers1 = new Queue<>();
        Queue<Integer> numbers2 = new Queue<>();

        numbers1.enqueue(1);
        numbers1.enqueue(2);
        numbers2.enqueue(3);
        numbers2.enqueue(4);

        Queue<Integer> numbers = MergeSort.mergeSortedQueues(numbers1, numbers2);

        assertEquals(4, numbers.size());

        int num1 = numbers.dequeue();
        assertEquals(1, num1);

        int num2 = numbers.dequeue();
        assertEquals(2, num2);

        int num3 = numbers.dequeue();
        assertEquals(3, num3);

        int num4 = numbers.dequeue();
        assertEquals(4, num4);


    }

    /** Returns a Queue that contains the given items sorted from least to greatest. */
    public static <Item extends Comparable> Queue<Item> mergeSort(
            Queue<Item> items) {

        Queue<Queue<Item>> bunchOfSingleItemQueues = MergeSort.makeSingleItemQueues(items);
        Queue<Item> firstSortedHalf = new Queue<>();
        Queue<Item> secondSortedHalf = new Queue<>();

        int leftSize = bunchOfSingleItemQueues.size() / 2;
        for (int i = 0; i < leftSize; i++) {
            firstSortedHalf = MergeSort.mergeSortedQueues(firstSortedHalf,bunchOfSingleItemQueues.dequeue());
        }

        int rightSize = bunchOfSingleItemQueues.size();
        for (int i = 0; i < rightSize; i++) {
            secondSortedHalf = MergeSort.mergeSortedQueues(secondSortedHalf, bunchOfSingleItemQueues.dequeue());
        }


        return mergeSortedQueues(firstSortedHalf, secondSortedHalf);
    }

    @Test
    public void testMergeSort(){
        Queue<Integer> numbers = new Queue<>();
        numbers.enqueue(5);
        numbers.enqueue(4);
        numbers.enqueue(9);
        numbers.enqueue(6);
        numbers.enqueue(1);

        Queue<Integer> sorted = MergeSort.mergeSort(numbers);

        int num1 = sorted.dequeue();
        int num2 = sorted.dequeue();
        int num3 = sorted.dequeue();
        int num4 = sorted.dequeue();
        int num5 = sorted.dequeue();

        assertEquals(1, num1);
        assertEquals(4, num2);
        assertEquals(5, num3);
        assertEquals(6, num4);
        assertEquals(9, num5);
    }


    public static void main(String[] args) {
        Queue<Integer> numbers = new Queue<>();
        numbers.enqueue(5);
        numbers.enqueue(4);
        numbers.enqueue(9);
        numbers.enqueue(6);
        numbers.enqueue(1);

        System.out.println(MergeSort.mergeSort(numbers));

    }
}
