import java.util.*;

/*
 * UVa 674 - Coin Change
 * Algorithm - Coin Change (DP)
 */

class UVA674 {

	public static int[] values = {1, 5, 10, 25, 50};
	public static int[][] memo;

	public static int ways(int type, int value) {
		if (value == 0) return 1;
		if (value < 0 || type == 5) return 0;
		if (memo[type][value] != -1) return memo[type][value];
		return memo[type][value] = ways(type + 1, value) + ways(type, value - values[type]);
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		memo = new int[5][7500];
		for (int[] row : memo) Arrays.fill(row, -1);
		while (in.hasNext()) {
			int n = in.nextInt();			
			System.out.println(ways(0, n));
		}
	}
}
