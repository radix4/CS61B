package Recursion;

public class Recursion {
    public static void main(String[] args) {
        //System.out.println(factorial(3));
        System.out.println(fib(1));
        System.out.println(fib2(1,0,0,1));
    }

    public static int factorial(int n){
        if (n <= 1) {
            return n;
        } else {
            return n * factorial(n-1);
        }
    }

    public static int fib(int n){
        if (n <= 1) {
            return n;
        } else {
            return fib(n-1) + fib(n -2);
        }
    }

    public static int fib2(int n, int k, int f0, int f1){ // k = 0, f0 = 0, f1 = 1;
        if(n == k){
            return f0;
        } else {
            return fib2(n, k + 1, f1, f0 + f1);
        }
    }
}
