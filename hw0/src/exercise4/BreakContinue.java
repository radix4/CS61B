package exercise4;

public class BreakContinue {
    /** your code here */
    public static void windowPosSum(int[] a, int n) {
        for (int i = 0; i < a.length; i++){ // iterate thru the list
            if(a[i] >= 0){ // check if value is positive
                for (int j = 1; j <= n; j++){ // if positive im going to add the next 3 in list
                   if(i + j >= a.length){
                       break;
                   }
                    a[i] += a[j+i];
                }
            }
        }

    }

    public static void main(String[] args) {
        int[] a = {1, 2, -3, 4, 5, 4};
        int n = 3;
        windowPosSum(a, n);

        // Should print 4, 8, -3, 13, 9, 4
        System.out.println(java.util.Arrays.toString(a));
    }
}
