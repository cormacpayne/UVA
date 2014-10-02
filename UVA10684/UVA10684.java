import java.util.*;
/*
 *  UVa 10684 - The jackpot
 *  Algorithm - Max 1D Range Sum
 */

class UVA10684 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();

		while (n != 0) {
			int sum = 0;
			int ans = 0;`

			for (int i = 0; i < n; i++) {
				int num = in.nextInt();
				sum += num;
				ans = Math.max(ans, sum);
				if (sum < 0) sum = 0;
			}

			if (ans > 0) {
				System.out.println("The maximum winning streak is " + ans + ".");
			} else {
				System.out.println("Losing streak.");
			}

			n = in.nextInt();
		}
	}
}
