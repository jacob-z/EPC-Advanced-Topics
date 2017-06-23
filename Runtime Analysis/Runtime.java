/*******************************************************************************
 * This program is designed to show runtimes and how they can be calculated
 * by looking at code.  The examples are progressively more difficult.
 ******************************************************************************/

public class Runtime {
	public static void main(String[] args) {
		// Check for correct number of arguments
		if (args.length != 1) {
			System.out.println("Usage: java Runtime N" + "\n" +
							   "N is the number of inputs.");
			System.exit(0);
		}

		// n is the number of inputs to run the program with.
		int n = Integer.parseInt(args[0]);
		if (n <= 0) {
			System.out.println("N must be a positive integer > 0.");
			System.exit(0);
		}

		// We will generate an array to be used in the following examples.
		int[] a = new int[n];



		System.out.println("\nBeginning Runtime Analysis:");
		System.out.println("-------------------------------------------------");


		// The first runtime we will test is an array access.
		long start = System.currentTimeMillis();
		a[0] = 5;
		long end = System.currentTimeMillis();
		System.out.print("        Array assignment time:\t" + (end-start));
		System.out.println("\t(ms)");


		// Time to run through 50,000,000 array elements
		int[] b = new int[50000000];
		start = System.currentTimeMillis();
		for (int i = 0; i < 50000000; i++) {
			b[i] = i;
		}
		end = System.currentTimeMillis();
		System.out.print(" 50,000,000 array assignments:\t" + (end-start));
		System.out.println("\t(ms)");

		// Time to run through 100,000,000 array elements
		b = new int[100000000];
		start = System.currentTimeMillis();
		for (int i = 0; i < 100000000; i++) {
			b[i] = i;
		}
		end = System.currentTimeMillis();
		System.out.print("100,000,000 array assignments:\t" + (end-start));
		System.out.println("\t(ms)");

		// Time to run through 200,000,000 array elements
		b = new int[200000000];
		start = System.currentTimeMillis();
		for (int i = 0; i < 200000000; i++) {
			b[i] = i;
		}
		end = System.currentTimeMillis();
		System.out.print("200,000,000 array assignments:\t" + (end-start));
		System.out.println("\t(ms)");

		// Time to run through 400,000,000 array elements
		b = new int[400000000];
		start = System.currentTimeMillis();
		for (int i = 0; i < 400000000; i++) {
			b[i] = i;
		}
		end = System.currentTimeMillis();
		System.out.print("400,000,000 array assignments:\t" + (end-start));
		System.out.println("\t(ms)\n");



		System.out.println("\nBeginning Control Structure Analysis:");
		System.out.println("-------------------------------------------------");

		// This section introduces the concept of runtimes based on structures
		// This is the runtime of a single loop.
		start = System.currentTimeMillis();
		for (int i = 0; i < n; i++) {
			a[i] = i % n/5;
		}
		end = System.currentTimeMillis();
		System.out.print("Single loop:\t" + (end-start));
		System.out.println("\t(ms)");

		// This is the runtime of a double loop.
		start = System.currentTimeMillis();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				a[i] = j % n/5;
			}
		}
		end = System.currentTimeMillis();
		System.out.print("Double loop:\t" + (end-start));
		System.out.println("\t(ms)");

		// This is the runtime of a triple loop.
		start = System.currentTimeMillis();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				for (int k = 0; k < n; k++) {
					a[i] = j % n/5;
				}
			}
		}
		end = System.currentTimeMillis();
		System.out.print("Triple loop:\t" + (end-start));
		System.out.println("\t(ms)");

	}
}
