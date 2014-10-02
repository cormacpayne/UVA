import java.util.*;

/*
 * UVa 10911 - Forming Quiz Teams
 * Algorithm - DP with bitmask
 */

class UVA10911 {

	public static int n, end;
	public static int[] x, y;
	public static double[] memo;

	public static double solve(int bitmask) {
		if (bitmask == end) return 0;
		if (memo[bitmask] > -1.0) return memo[bitmask];
		int start = -1;
		for (int i = 0; i < 2*n; i++) {
			if ((bitmask & (1 << i)) == 0) {
				start = i;
				break;
			}
		}
		double min = 2000000000.0;
		for (int j = start + 1; j < 2*n; j++) {
			if ((bitmask & (1 << j)) == 0) {
				min = Math.min(min, calc(start, j) + solve(bitmask | (1 << start) | (1 << j)));
			}
		}

		return memo[bitmask] = min;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		n = in.nextInt();
		int t = 1;
		while (n != 0) {
			x = new int[2*n];
			y = new int[2*n];
			memo = new double[(1 << (2*n))];

			Arrays.fill(memo, -1.0);

			end = (1 << (2*n)) - 1;

			for (int i = 0; i < 2*n; i++) {
				String blank = in.next();
				x[i] = in.nextInt();
				y[i] = in.nextInt();
			}
			System.out.printf("Case %d: %.2f\n", t++, solve(0));
			n = in.nextInt();
		}
	}

	public static double calc(int i, int j) {
		int dx = x[i] - x[j];
		int dy = y[i] - y[j];
		return Math.sqrt(dx*dx + dy*dy);
	}
}
