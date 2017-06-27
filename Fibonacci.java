/*
Fibonacci.java

Calculate the nth Fibonacci number using recursion.
*/


public class Fibonacci {
    // return the nth Fibonacci number using iteration
    public static int fibIter(int n) {
        if (n <= 1) {
            return n;
        }
        int fibprev = 1;
        int fibcurr = 1;
        int temp = 0;
        for (int i = 0; i < n - 2; i++) {
            temp = fibcurr;
            fibcurr += fibprev;
            fibprev = temp;
        }
        return fibcurr;
    }

    // return nth Fibonacci number using array
    public static long fibArray(int n) {
        long[] arr = new long[n+1];
        arr[0] = 0;
        arr[1] = 1;
        for (int i = 2; i < n + 1; i++) {
            arr[i] = arr[i-1] + arr[i-2];
        }
        return arr[n];
    }

    // return the nth Fibonacci number using recursion
    public static int fibRecur(int n) {
        if (n <= 1) {
            return n;
        }
        else {
            return fibRecur(n-1) + fibRecur(n-2);
        }
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);

        //long start = System.currentTimeMillis();
        //System.out.println("Iterative: " + fibIter(n));
        //long one = System.currentTimeMillis();
        System.out.println("UsingArray: " + fibArray(n));
        //long two = System.currentTimeMillis();
        //System.out.println("Recursive: " + fibRecur(n));
        //long end = System.currentTimeMillis();
        //System.out.println("Time for iterative: " + (one - start) + " milliseconds.");
        //System.out.println("Time for arraymethod: " + (two - one) + " milliseconds.");
        //System.out.println("Time for recursive: " + (end - two) + " milliseconds.");
    }
}

// 47 causes overflow
