/*
Bubble sort
*/

public class BubbleSort {

    public static void main(String a[]){
        int[] arr1 = {10,34,2,56,7,67,88,42};
        int[] arr2 = doBubbleSort(arr1);
        for(int i =0; i < arr2.length; i++){
            System.out.print(arr2[i]);
            System.out.print(", ");

        }
        System.out.println("");
    }

    public static int[] doBubbleSort(int[] numArray) {

    int n = numArray.length;
    int temp = 0;

    for (int i = 0; i < n; i++) {
        for (int j = 1; j < (n - i); j++) {

            if (numArray[j - 1] > numArray[j]) {
                temp = numArray[j - 1];
                numArray[j - 1] = numArray[j];
                numArray[j] = temp;
            }
        }
    }
    return numArray;
    }
}