/*
Symmetric.java

This class shows an example of symmetric encryption using an LFSR.
*/

import java.util.Arrays;
import java.util.Scanner;

public class Symmetric {

    // XOR a randomly generated bit string from key with input
    public static int[] encode(int[] input, int[] key) {
        int[] steppedKey = generate(key, input.length);
        int[] output = new int[input.length];

        for (int i = 0; i < input.length; i++) {
            if (input[i] != steppedKey[i]) {
                output[i] = 1;
            }
            else {
                output[i] = 0;
            }
        }

        return output;
    }

    // XOR a randomly generated bit string from key with input
    public static int[] decode(int[] input, int[] key) {
        int[] steppedKey = generate(key, input.length);
        int[] output = new int[input.length];

        for (int i = 0; i < input.length; i++) {
            if (input[i] != steppedKey[i]) {
                output[i] = 1;
            }
            else {
                output[i] = 0;
            }
        }

        return output;
    }

    // generate a random bit string of length steps from key
    public static int[] generate(int[] key, int steps) {
        int[] newsteps = new int[key.length + steps];

        for (int i = 0; i < key.length; i++) {
            newsteps[i] = key[i];
        }

        for (int i = 0; i < steps; i++) {
            if (newsteps[i] != newsteps[i + 2]) {
                newsteps[i + key.length] = 1;
            }
            else {
                newsteps[i + key.length] = 0;
            }
        }

        return Arrays.copyOfRange(newsteps, key.length, newsteps.length);
    }

    // easy format array printing
    public static String printArray(int[] array) {
        String output = "";
        for (int i : array) {
            output += Integer.toString(i);
        }

        return output;
    }

    // converts a binary string to an int array of 1s and 0s
    public static int[] strToArr(String str) {
        int[] output = new int[str.length()];
        for (int i = 0; i < str.length(); i++) {
            output[i] = Integer.parseInt(String.valueOf(str.charAt(i)));
        }
        return output;
    }


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("-------------------------------------------------------------");
        System.out.println("Please give an encoding key (a string of 1s and 0s)");
        int[] key = strToArr(in.nextLine());
        int keylength = key.length;
        System.out.println("-------------------------------------------------------------");
        System.out.println("Verifying generate() function on given key...");
        System.out.printf("Given key: %s\n", printArray(key));
        int[] newsteps = generate(key, keylength);
        System.out.printf("%d steps:  %s\n", keylength, printArray(newsteps));

        while (true) {
            System.out.println("-------------------------------------------------------------");
            System.out.println("Please enter an input as a string of 1s and 0s (quit to exit)");
            String inputStr = in.nextLine();
            System.out.println("-------------------------------------------------------------");
            if (inputStr.equals("quit"))
                break;
            int[] input = strToArr(inputStr);
            int[] encoded = encode(input, key);
            System.out.printf("Encoding (with key %s)...\n", printArray(key));
            System.out.printf("%s (encoded)\n\n", printArray(encoded));
            System.out.println("To decode, enter encoding key:");
            int[] newkey = strToArr(in.nextLine());
            int[] decoded = decode(encoded, newkey);
            System.out.printf("\n");
            System.out.println("Input:   " + printArray(input));
            System.out.println("Decoded: " + printArray(decoded));
            if (Arrays.equals(decoded, input))
                System.out.println("Success! Decoded equals input!");
            else
                System.out.println("Wrong key! Access denied.");
        }
    }
}