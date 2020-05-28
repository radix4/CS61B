package exercise2;

public class ReturnMaxValue {

    public static void main(String[] args) {
        /** Returns the maximum value from m. */
        int[] numbers = new int[]{9, 2, 15, 2, 22, 10, 6};
        System.out.println("Max value: " + max(numbers));
    }

    public static int max(int[] m) {
        int max = m[0];

        for(int i = 1; i < m.length; i++){
            if (max < m[i]){
                max = m[i];
            }
        }

        return max;
    }

}
