/*******************************************************************************
 * Mergesort.java
 * This program demonstrates how mergesort works and prints out information for
 * timing analysis.  It also shows a comparison of how mergesort performs with 
 * different improvements.  The improvements are described in each block of code
 * with the exception of the default Java Arrays sort.  This sort is included
 * for the sake of comparing mergesort with a fully optimized sort.  Students
 * should study the results of the timing analysis and try to determine the 
 * runtime of the algorithm given each improvement.
 ******************************************************************************/

// Adapted from Princeton University (Robert Sedgewick and Kevin Wayne)
// by Jacob Zimmer for IUEA students in the Enthusiastic Programmers' Club

import java.util.Random;
import java.text.NumberFormat;
import java.util.Arrays;

public class Mergesort {

    private static final int CUTOFF = 7;  // cutoff to insertion sort

    /***************************************************************************
     * Basic Mergesort that returns a new array with the sorted contents of the
     * original input.   No optimizations have been made to this sort.
     **************************************************************************/

    // This method is for merging two arrays into a single array in
    // a stable, sorted order.  This is the primary function of mergesort.
    private static double[] merge(double[] a, double[] b) {

        // New array to hold the merged arrays
        double[] c = new double[a.length + b.length];

        // i and j are used as indices into arrays a and b
        int i = 0, j = 0;

        // now we go through and fill array c
        for (int k = 0; k < c.length; k++) {

            // If one array is exhausted, use the other
            if      (i >= a.length)  c[k] = b[j++];
            else if (j >= b.length)  c[k] = a[i++];

            // If an element from a is smaller, add that first
            else if (a[i] <= b[j])   c[k] = a[i++];
            else                     c[k] = b[j++];
        }

        return c;
    }

    // This is the public method that is called to initiate a merge sort,
    // and is the part that handles the recursion.  Its primary function is
    // to break the given arrays in half.
    public static double[] mergesort(double[] input) {

        // Need the size of the input to break it down
        int N = input.length;

        // Base case
        if (N <= 1) return input;

        // Arrays for the two halves of the array.
        double[] a = new double[N/2];
        double[] b = new double[N - N/2];

        // Copy the input array into the halves
        for (int i = 0; i < a.length; i++) {
            a[i] = input[i];
        }
        for (int i = 0; i < b.length; i++) {
            b[i] = input[i + N/2];
        }

        // The recursive call sorts both subarrays before merging.
        return merge(mergesort(a), mergesort(b));
    }

    /***************************************************************************
     * A second basic version of mergesort that is structured in a slightly 
     * different manner than the version above.  This way of writing the code
     * is slightly more optimized, and students should try to reason about why.
     **************************************************************************/

    private static void pmerge(double[] a, double[] aux, int lo, int mid, int hi) {
        // precondition: a[lo .. mid] and a[mid+1 .. hi] are sorted subarrays

        // copy to aux[]
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k]; 
        }

        // merge back to a[]
        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++) {
            if      (i > mid)              a[k] = aux[j++];
            else if (j > hi)               a[k] = aux[i++];
            else if (aux[j] < aux[i])      a[k] = aux[j++];
            else                           a[k] = aux[i++];
        }
    }

    // mergesort a[lo..hi] using auxiliary array aux[lo..hi]
    private static void sort(double[] a, double[] aux, int lo, int hi) {
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        sort(a, aux, lo, mid);
        sort(a, aux, mid + 1, hi);
        pmerge(a, aux, lo, mid, hi);
    }

    public static void sort(double[] a) {
        double[] aux = new double[a.length];
        sort(a, aux, 0, a.length-1);
    }

    /***************************************************************************
     * MergeX has serveral optimizations.  Namely, it skips a copying step in
     * the main merge method, uses a more efficient array copying method, and
     * cuts to insertion sort for arrays smaller than the size of the cutoff
     * value.  Students should change the cutoff value to see how it affects
     * sort performance.
     **************************************************************************/

    private static void mergeX(double[] src, double[] dst, int lo, int mid, int hi) {

        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++) {
            if      (i > mid)              dst[k] = src[j++];
            else if (j > hi)               dst[k] = src[i++];
            else if (src[j] < src[i])      dst[k] = src[j++]; // for stability
            else                           dst[k] = src[i++];
        }
    }

    private static void sortX(double[] src, double[] dst, int lo, int hi) {
        // if (hi <= lo) return;
        if (hi <= lo + CUTOFF) { 
            insertionSort(dst, lo, hi);
            return;
        }
        int mid = lo + (hi - lo) / 2;
        sortX(dst, src, lo, mid);
        sortX(dst, src, mid+1, hi);

        // if (!less(src[mid+1], src[mid])) {
        //    for (int i = lo; i <= hi; i++) dst[i] = src[i];
        //    return;
        // }

        // using System.arraycopy() is a bit faster than the above loop
        if (src[mid+1] >= src[mid]) {
            System.arraycopy(src, lo, dst, lo, hi - lo + 1);
            return;
        }

        mergeX(src, dst, lo, mid, hi);
    }

    public static void sortX(double[] a) {
        double[] aux = a.clone();
        sortX(aux, a, 0, a.length-1);  
    }

    private static void insertionSort(double[] a, int lo, int hi) {
        for (int i = lo; i <= hi; i++)
            for (int j = i; j > lo && (a[j] < a[j-1]); j--)
                exch(a, j, j-1);
    }

    /***************************************************************************
     * Utility Methods.
     **************************************************************************/

    // A method for debugging purposes, checks if the array is sorted
    public static boolean isSorted(double[] a) {
        for (int i = 1; i < a.length; i++) {
            if (a[i] < a[i-1]) {
                return false;
            }
        }
        return true;
    }

    // exchange a[i] and a[j] for the insertion sort
    private static void exch(double[] a, int i, int j) {
        double swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    // Used to create a time series of sorts
    public static double timeSort(int N) {
        Random random = new Random();
        double[] a = new double[N];

        for (int i = 0; i < N; i++) {
            a[i] = random.nextDouble();
        }

        long start = System.currentTimeMillis();
        a = mergesort(a);
        long end = System.currentTimeMillis();

        return (end-start)/1000.0;
    }

    /***************************************************************************
     * Main Method:  Runs the code under various circumstances so students can
     *               analyze factors that effect the performance of mergesort.
     **************************************************************************/

    public static void main(String[] args) {

        // Get the size of array to sort
        int N = Integer.parseInt(args[0]);

        Random random = new Random();
        double[] a = new double[N];

        // Fill the array randomly
        System.out.println("\nFilling the array, please wait...");
        for (int i = 0; i < N; i++) {
            a[i] = random.nextDouble();
        }
        System.out.println("Finished.  Beginning sort...");

        // Sort and timing
        long start = System.currentTimeMillis();
        a = mergesort(a);
        long end = System.currentTimeMillis();

        // Output results
        if (isSorted(a)) {
            System.out.println("The array is now sorted.");
            System.out.printf("Time to sort: %.3f (s)%n", (end-start)/1000.0);
        } else {
            System.out.println("An error has occurred.");
            System.exit(0);
        }


        // // Delay to allow user to read output
        // try {
        //     Thread.sleep(1500);
        // } catch (InterruptedException e) {
        //     Thread.currentThread().interrupt();
        // }
        

        // Time series analysis
        String n = NumberFormat.getIntegerInstance().format(N);
        System.out.println("\nBeginning a time series analysis on N = " + n);
        System.out.println("-------------------------------------------------");
        for (int i = 1; i <= 16; i *= 2) {
            String size = NumberFormat.getIntegerInstance().format(N*i);
            System.out.printf("%-20s \t%.3f%n", size, timeSort(N*i));
        }
        System.out.println("-------------------------------------------------");


        // Analyzing effects of input
        System.out.println("\nBeginning analysis of input composition...");
        System.out.println("-------------------------------------------------");

        a = new double[N];
        for (int i = 0; i < N; i++) {
            a[i] = i;
        }
        start = System.currentTimeMillis();
        a = mergesort(a);
        end = System.currentTimeMillis();
        System.out.printf("Ordered:\t\t%.3f%n", (end-start)/1000.0);

        a = new double[N];
        for (int i = 0; i < N; i++) {
            a[i] = N-i;
        }
        start = System.currentTimeMillis();
        a = mergesort(a);
        end = System.currentTimeMillis();
        System.out.printf("Reversed:\t\t%.3f%n", (end-start)/1000.0);

        a = new double[N];
        for (int i = 0; i < N; i++) {
            a[i] = random.nextDouble();
        }
        start = System.currentTimeMillis();
        a = mergesort(a);
        end = System.currentTimeMillis();
        System.out.printf("Random:\t\t\t%.3f%n", (end-start)/1000.0);

        a = new double[N];
        for (int i = 0; i < N; i++) {
            if (i%2 == 0) {
                a[i] = i + 2;
            } else {
                a[i] = i;
            }
        }
        start = System.currentTimeMillis();
        a = mergesort(a);
        end = System.currentTimeMillis();
        System.out.printf("Mixed:\t\t\t%.3f%n", (end-start)/1000.0);
        System.out.println("-------------------------------------------------");


        // Testing versions of Mergesort
        System.out.println("\nBeginning algorithm tests...");
        System.out.println("-------------------------------------------------");

        a = new double[N];
        for (int i = 0; i < N; i++) {
            a[i] = random.nextDouble();
        }

        double[] b = new double[N];
        double[] c = new double[N];
        double[] d = new double[N];

        System.arraycopy(a, 0, b, 0, N);
        System.arraycopy(a, 0, c, 0, N);
        System.arraycopy(a, 0, d, 0, N);

        start = System.currentTimeMillis();
        sort(a);
        end = System.currentTimeMillis();
        System.out.printf("Princeton Merge:\t%.3f%n", (end-start)/1000.0);

        start = System.currentTimeMillis();
        b = mergesort(b);
        end = System.currentTimeMillis();
        System.out.printf("Mergesort:\t\t%.3f%n", (end-start)/1000.0);

        start = System.currentTimeMillis();
        sortX(c);
        end = System.currentTimeMillis();
        System.out.printf("Princeton MergeX:\t%.3f%n", (end-start)/1000.0);

        start = System.currentTimeMillis();
        Arrays.sort(d);
        end = System.currentTimeMillis();
        System.out.printf("Java Arrays:\t\t%.3f%n", (end-start)/1000.0);
        System.out.println("-------------------------------------------------");
        System.out.println("");
    }
}





