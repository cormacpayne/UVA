import java.util.*;

class UVA10003 {

	/*
	 * UVa 10003 - Cutting Sticks
	 * Algorithm - DP
	 */

	public static int length, n;
	public static int[] a;
	public static int[][] memo;

	public static int solve(int left, int right) {
		if (left + 1 == right) return 0;
		if (memo[left][right] != -1) return memo[left][right];
		int res = Integer.MAX_VALUE;
		for (int i = left + 1; i < right; i++) {
			res = Math.min(res, solve(left, i) + solve(i, right) + a[right] - a[left]);
		}
		return memo[left][right] = res;
	} 

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		StringBuilder string = new StringBuilder();

		while (true) {
			length = in.nextInt();
			if (length == 0) break;

			n = in.nextInt();
			a = new int[n+2];
			a[0] = 0;
			a[n+1] = length;
			for (int i = 1; i <= n; i++) a[i] = in.nextInt();

			memo = new int[n+2][n+2];
			for (int[] row : memo) Arrays.fill(row, -1);

			int result = solve(0, n+1);
			string.append("The minimum cutting is " + result + ".\n");
		}

		System.out.print(string);
	}
}
