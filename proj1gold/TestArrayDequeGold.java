/** This program tests the StudentArrayDeque by comparing it to the ArrayDequeSolution
 * using random method calls.
 * @author  Thang Cao
 * @since   06/12/2020
 * */

import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {

    private void randomAddFirst(Integer randNum,
                           StudentArrayDeque<Integer> bug, ArrayDequeSolution<Integer> solution) {
        bug.addFirst(randNum);
        solution.addFirst(randNum);
    }

    private void randomAddLast(Integer randNum,
                                StudentArrayDeque<Integer> bug, ArrayDequeSolution<Integer> solution) {
        bug.addLast(randNum);
        solution.addLast(randNum);
    }

    private void removeFirst(StudentArrayDeque<Integer> bug, ArrayDequeSolution<Integer> solution) {
        bug.removeFirst();
        solution.removeFirst();
    }

    private void removeLast(StudentArrayDeque<Integer> bug, ArrayDequeSolution<Integer> solution) {
        bug.removeLast();
        solution.removeLast();
    }

    /** Test the StudentArrayDeque class.
     * Randomly calls StudentArrayDeque and ArrayDequeSolution methods
     * until they disagree on an output.
     * ----> Found bugs for removeFirst and removeLast methods. */
    @Test
    public void testStudentArrayDeque() {
        StudentArrayDeque<Integer> bug = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> solution = new ArrayDequeSolution<>();
        int numberOfCalls = 1000;
        int size = 0;
        for (Integer i = 0; i < numberOfCalls; i++) {
            Integer random = StdRandom.uniform(10);

            if (random < 5) {
                randomAddFirst(random, bug, solution);
                size++;
            } else {
                randomAddLast(random, bug, solution);
                size++;
            }

            if (random < 2) {
                removeFirst(bug,solution);
                size--;
            } else if (random == 5){
                removeLast(bug,solution);
                size--;
            }


        }

        for (Integer i = 0; i < size; i++) {
            assertEquals("Bug found at index: " + i,bug.get(i),solution.get(i));
        }
    }
}
