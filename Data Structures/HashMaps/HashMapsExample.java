import java.util.HashMap;
import java.util.Scanner;

public class HashMapsExample {

	public static void main(String[] args){

		int words = 466547;
		int numbers = 82192;
		
		// Initialize three types of HashMap
		HashMap<Integer, String>                mapJV = new HashMap<>();
		SeparateChainingHashST<Integer, String> mapSC = new SeparateChainingHashST<>();
		LinearProbingHashST<Integer, String>    mapLP = new LinearProbingHashST<>();

		Scanner in = new Scanner(System.in);
		String[] terms = new String[words];

		// Build dictionary lists
		System.out.println("\nBuilding term list...");
		long t0b = System.currentTimeMillis();
		for (int i = 0; in.hasNext(); i++)
			terms[i] = in.nextLine();
		long t1b = System.currentTimeMillis();
		System.out.println("Done in " + (t1b-t0b)/1 + " ms.");

		// Time filling the tables.
		System.out.println("\nTesting " + terms.length + " calls to put:");
		long t0JV = System.currentTimeMillis();
		for (int i = 0; i < terms.length; i++)
			mapJV.put(i, terms[i]);
		long t1JV = System.currentTimeMillis();				
		System.out.println("Java Default:\t\t" + (t1JV - t0JV)/1 + " ms");

		long t0SC = System.currentTimeMillis();
		for (int i = 0; i < terms.length; i++)
			mapSC.put(i, terms[i]);
		long t1SC = System.currentTimeMillis();
		System.out.println("Separate Chaining:\t" + (t1SC - t0SC)/1 + " ms");

		long t0LP = System.currentTimeMillis();
		for (int i = 0; i < terms.length; i++)
			mapLP.put(i, terms[i]);
		long t1LP = System.currentTimeMillis();
		System.out.println("Linear Probing:\t\t" + (t1LP - t0LP)/1 + " ms");


		// Time reading the tables.
		String tmp;
		System.out.println("\nTesting " + terms.length + " calls to get:");
		long t0JVg = System.currentTimeMillis();
		for (int i = 0; i < terms.length; i++)
			tmp = mapJV.get(i);
		long t1JVg = System.currentTimeMillis();				
		System.out.println("Java Default:\t\t" + (t1JVg - t0JVg)/1 + " ms");

		long t0SCg = System.currentTimeMillis();
		for (int i = 0; i < terms.length; i++)
			tmp = mapSC.get(i);
		long t1SCg = System.currentTimeMillis();
		System.out.println("Separate Chaining:\t" + (t1SCg - t0SCg)/1 + " ms");

		long t0LPg = System.currentTimeMillis();
		for (int i = 0; i < terms.length; i++)
			tmp = mapLP.get(i);
		long t1LPg = System.currentTimeMillis();
		System.out.println("Linear Probing:\t\t" + (t1LPg - t0LPg)/1 + " ms");


		System.out.println("Done.");

	}	
}