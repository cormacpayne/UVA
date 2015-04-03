import java.util.*;

class UVA357 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		StringBuilder string = new StringBuilder();

		long[] dp = new long[30100];
		int[] val = {1, 5, 10, 25, 50};

		dp[0] = 1;

		for (int i = 0; i < 5; i++) {
			for (int j = val[i]; j <= 30000; j++) {
				dp[j] += dp[j - val[i]];
			}
		}


		while (in.hasNextInt()) {
			int n = in.nextInt();
			long res = dp[n];
			if (res == 1) string.append("There is only 1 way to produce " + n + " cents change.\n");
			else string.append("There are " + res + " ways to produce " + n + " cents change.\n");
		}
		System.out.print(string);
	}
}
