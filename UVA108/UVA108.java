import java.util.*;

/*
 * UVa 108 - Maximum Sum
 * Algorithm - Max 2D Range Sum
 */

class UVA108 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int[][] grid = new int[n][n];

		for (int y = 0; y < n; y++) for (int z = 0; z < n; z++) {
			grid[y][z] = in.nextInt();
			if (z > 0) grid[y][z] += grid[y][z - 1]; // pre-processing
		}

		int maxSubRect = -127 * 100 * 100; // lowest possible value for this problem
		int subRect = 0;

		for (int l = 0; l < n; l++) for (int r = l; r < n; r++) {
			subRect = 0;
			for (int row = 0; row < n; row++) {
				// Max 1D Range Sum on columns of this row i
				if (l > 0) subRect += grid[row][r] - grid[row][l - 1];
				else subRect += grid[row][r];

				// Kadane's algorithm on rows
				if (subRect < 0) subRect = 0;
				maxSubRect = Math.max(maxSubRect, subRect);
			}
		}

		System.out.println(maxSubRect);
	}	
}
