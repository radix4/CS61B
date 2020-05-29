package exercise3;

public class MaxValueAgain {

    public static void main(String[] args) {
        int[] numbers = new int[]{9, 2, 15, 2, 22, 10, 6};
        System.out.println("Max value: " + max(numbers));
    }

    /** Returns the maximum value from m integer array. */
    public static int max(int[] m) {
        int max = m[0];
        int i = 1;

        while(i < m.length){
            if (max < m[i]){
                max = m[i];
            }
            i++;
        }

        return max;
    }

}