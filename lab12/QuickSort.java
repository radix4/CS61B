import edu.princeton.cs.algs4.Queue;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *  A QuickSort algorithm implementation.
 *
 *  @author Thang Cao
 */

public class QuickSort {
    /**
     * Returns a new queue that contains the given queues catenated together.
     *
     * The items in q2 will be catenated after all of the items in q1.
     */
    private static <Item extends Comparable> Queue<Item> catenate(Queue<Item> q1, Queue<Item> q2) {
        Queue<Item> catenated = new Queue<Item>();
        for (Item item : q1) {
            catenated.enqueue(item);
        }
        for (Item item: q2) {
            catenated.enqueue(item);
        }
        return catenated;
    }

    /** Returns a random item from the given queue. */
    private static <Item extends Comparable> Item getRandomItem(Queue<Item> items) {
        int pivotIndex = (int) (Math.random() * items.size());
        Item pivot = null;
        // Walk through the queue to find the item at the given index.
        for (Item item : items) {
            if (pivotIndex == 0) {
                pivot = item;
                break;
            }
            pivotIndex--;
        }
        return pivot;
    }

    /**
     * Partitions the given unsorted queue by pivoting on the given item.
     *
     * @param unsorted  A Queue of unsorted items
     * @param pivot     The item to pivot on
     * @param less      An empty Queue. When the function completes, this queue will contain
     *                  all of the items in unsorted that are less than the given pivot.
     * @param equal     An empty Queue. When the function completes, this queue will contain
     *                  all of the items in unsorted that are equal to the given pivot.
     * @param greater   An empty Queue. When the function completes, this queue will contain
     *                  all of the items in unsorted that are greater than the given pivot.
     */
    private static <Item extends Comparable> void partition(
            Queue<Item> unsorted, Item pivot,
            Queue<Item> less, Queue<Item> equal, Queue<Item> greater) {
        // Your code here!

        while (!unsorted.isEmpty()) {
            Item item = unsorted.dequeue();
            int cmp = item.compareTo(pivot);

            if (cmp < 0) less.enqueue(item);
            else if (cmp > 0) greater.enqueue(item);
            else equal.enqueue(item);
        }
    }

    @Test
    public void testPartition(){
        Queue<Integer> unsorted = new Queue<>();
        unsorted.enqueue(5);
        unsorted.enqueue(4);
        unsorted.enqueue(2);
        unsorted.enqueue(1);
        unsorted.enqueue(3);

        int pivot = 3;

        Queue<Integer> less = new Queue<>();
        Queue<Integer> equal = new Queue<>();
        Queue<Integer> greater = new Queue<>();

        partition(unsorted, pivot, less, equal, greater);


        while (!less.isEmpty()) {
            int item = less.dequeue();
            assertTrue(item < pivot);
        }

        while (!equal.isEmpty()) {
            int item = equal.dequeue();
            assertEquals(item, pivot);
        }

        while (!greater.isEmpty()) {
            int item = greater.dequeue();
            assertTrue(item > pivot);
        }


    }

    /** Returns a Queue that contains the given items sorted from least to greatest. */
    public static <Item extends Comparable> Queue<Item> quickSort(
            Queue<Item> items) {
        // Your code here!
        return items;
    }

    public static void main(String[] args) {
        Queue<Integer> numbers = new Queue<>();
        numbers.enqueue(5);
        numbers.enqueue(4);
        numbers.enqueue(2);
        numbers.enqueue(1);
        numbers.enqueue(3);

        System.out.println(QuickSort.quickSort(numbers));
    }
}
