package AdHocTesting;

public class Sort {
    public static void sort(String[] x) {
        /** Find the smallest item.
         * Move it to the front.
         * Selection sort the rest (using recursion)? */

        sort(x,0);
    }

    /** Sorts x starting at position start. */
    private static void sort(String[] x, int start){
        if (start == x.length) {
            return;
        }

        int smallestIndex = findSmallest(x);
        swap(x,start,smallestIndex);
        sort(x, start + 1);

    }

    public static void swap(String[] x, int a, int b) {
        String temp = x[a];
        x[a] = x[b];
        x[b] = temp;
    }

    /** Return the index of the smallest String in x. */
    public static int findSmallest(String[] x){
        int smallestIndex = 0;
        for (int i = 0; i < x.length; i++) {
            int cmp = x[i].compareTo(x[smallestIndex]);
            //from the internet, if x[i] < x[smallestIndex], cmp will be -1

            if (cmp < 0) {
                smallestIndex = i;
            }
        }

        return smallestIndex;
    }
}
