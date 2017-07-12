/*
Insertion sort
*/

public class InsertionSort {
 
    public static void main(String a[]){
        int[] arr1 = {10,34,2,56,7,67,88,42};
        int[] arr2 = doInsertionSort(arr1);
        for(int i =0; i < arr2.length; i++){
            System.out.print(arr2[i]);
            System.out.print(", ");

        }
        System.out.println("");
    }
     
    public static int[] doInsertionSort(int[] input){
         
        int n = input.length;  
        for (int j = 1; j < n; j++) {  
            int key = input[j];  
            int i = j-1;  
            while ( (i > -1) && ( input[i] > key ) ) {  
                input[i+1] = input[i];  
                i--;  
            }  
            input[i+1] = key;  
        }  
        return input;
    }
}