// This is to show differences in array indexing options

public class Indexing {



	public static void main(String[] args) {

		int rows = 4, cols = 3;

		int[] one = new int[rows*cols];
		int[][] two = new int[rows][cols];


		// fill both arrays with a single for-loop.
		for (int i = 0; i < rows*cols; i++) {

			// Trivial in the case of a one-dimensional array
			one[i] = i+1;

			// More complex for a two-dimensional array
			two[i/cols][i%cols] = i+1;
		}


		// Now print both using two loops
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {

				// Trivial with a two-dimensional array
				System.out.print(two[i][j] + " ");

				// More complex for a one-dimensional array
				System.out.println(one[i*cols + j]);
			}
		}
	}
}