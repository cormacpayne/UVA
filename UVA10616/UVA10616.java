import java.util.*;

/*
 * UVa 10616 - Divisible Group Sums
 * Algorithm - 0-1 Knapsack (Subset Sum)
 */

class UVA10616 {

	public static int n, q, d, m;
	public static int[] a;
	public static long[][][] memo;

	public static long solve(int sum, int num, int index) {
		if (num == m) return (sum == 0) ? 1 : 0;
		if (index == n) return 0;
		if (memo[sum][num][index] != -1) return memo[sum][num][index];
		int temp = (sum + a[index]) % d; // if a[index] is negative and brings sum below 0
		if (temp < 0) temp += d;         // increase by d to make it bigger than 0
		return memo[sum][num][index] = solve(temp, num + 1, index + 1) + solve(sum, num, index + 1);
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		q = in.nextInt();
		int t = 1;
		while (n != 0 && q != 0) {
			a = new int[n];
			System.out.printf("SET %d:\n", t++);
			for (int i = 0; i < n; i++) a[i] = in.nextInt();
			for (int j = 1; j <= q; j++) {
				d = in.nextInt();
				m = in.nextInt();
				memo = new long[d + 1][m + 1][n + 1];
				for (long[][] array : memo) {
					for (long[] row : array) {
						Arrays.fill(row, -1);
					}
				} 
				System.out.printf("QUERY %d: %d\n", j, solve(0, 0, 0));
			}
			n = in.nextInt();
			q = in.nextInt();
		}
	}
}
