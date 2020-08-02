package AdHocTesting;


/** Tests for the Sort class. */
public class TestSort {

    /** Test the Sort.sort method. */
    public static void testSort() {
        String[] input = {"i", "have", "an", "egg"};
        String[] expected = {"an", "egg", "have", "i"};

        Sort.sort(input);

        org.junit.Assert.assertArrayEquals(expected,input);

        /** Alternate way to test (but very ugly, take advantage of JUnit .
        for (int i = 0; i < input.length; i++) {
            if (!input[i].equals(expected[i])){
                System.out.println("Mismatch in position " + i + ", expected: " + expected[i] + ", but got: " + input[i]);
                return;
            }
        }

        if (java.util.Arrays.equals(input,expected)) {
            System.out.println("Error! There seem to have a problem with sort.sort.");
        }*/
    }

    public static void testFindSmallest() {
        String[] input = {"i", "have", "an", "egg"};
        int expected = 2;

        int actual = Sort.findSmallest(input);
        org.junit.Assert.assertEquals(expected,actual);

        String[] input2 = {"there", "are", "many", "pigs"};
        int expected2 = 1;

        int actual2 = Sort.findSmallest(input2);
        org.junit.Assert.assertEquals(expected2,actual2);


    }

    /** Test the Sort.swap method. */
    public static void testSwap() {
        String[] input = {"i", "have", "an", "egg"};
        int a = 0;
        int b = 2;

        String[] expected = {"an", "have", "i", "egg"};

        Sort.swap(input, a, b);

        org.junit.Assert.assertArrayEquals(expected,input);

    }

    public static void main(String[] args) {
        testSwap();
        testFindSmallest();
        testSort();
    }
}
