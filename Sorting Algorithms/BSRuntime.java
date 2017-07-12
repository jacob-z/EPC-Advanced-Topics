/*
BSRuntime
*/

import java.util.Random;

public  class BSRuntime {
    public static void main(String a[]){
        System.out.println("-----------------------------------------------------------------");
        System.out.println("Running 5 tests of BubbleSort.");
        System.out.println("Each test displays the average elapsed time of 3 sorts of size N.");
        System.out.println("-----------------------------------------------------------------");

        Random ran = new Random();
        BubbleSort bubble = new BubbleSort();
        int[] sizearr = {4000, 8000, 16000, 32000, 64000};


        for (int i = 0; i < 5; i++) {
            int size = sizearr[i];
            int[] arr = new int[size];
            for (int j = 0; j < size; j++) {
                arr[j] = ran.nextInt(100);
            }
            long timetotal = 0;
            for (int j = 0; j < 3; j++) {
                long start = System.currentTimeMillis();
                bubble.doBubbleSort(arr);
                long end = System.currentTimeMillis();
                long time = end - start;
                timetotal += time;
            }
            System.out.println("N = " + size + " -- Elapsed time: " + (timetotal/3) + " milliseconds");
        }
        System.out.println("-----------------------------------------------------------------");   
    }
}