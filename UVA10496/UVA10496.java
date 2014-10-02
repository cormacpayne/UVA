import java.util.*;
import java.io.*;

/*
 * UVa 10496 - Collecting Beepers
 * Algorithm - Traveling Salesman Problem (TSP)
 */

class UVA10496 {

	public static int startX, startY, n, rows, cols;
	public static int[] x, y;
	public static int[][] dist, memo;

	public static int solve(int index, int bitmask) {
		if (bitmask == (1 << (n + 1)) - 1) return dist[index][0]; // if bitmask = 1...1, then we've visited everything
		if (memo[index][bitmask] != -1) return memo[index][bitmask];

		int min = 2000000000;

		for (int i = 0; i <= n; i++) {
         // if we haven't already visited i, visit it and add the distance from index to i
			if (i != index && (bitmask & (1 << i)) == 0) min = Math.min(min, dist[index][i] + solve(i, bitmask | (1 << i)));
		}

		return memo[index][bitmask] = min;
	}

	public static void main(String[] args) throws Exception {
		Parser in = new Parser(System.in);
		StringBuilder string = new StringBuilder();

		int t = in.nextInt();

		while (t-- > 0) {

			rows = in.nextInt(); cols = in.nextInt();
			startX = in.nextInt(); startY = in.nextInt();
			n = in.nextInt();

			x = new int[n + 1];	y = new int[n + 1];

			x[0] = startX; y[0] = startY;

			dist = new int[n + 1][n + 1];

			for (int i = 1; i <= n; i++) {
				x[i] = in.nextInt(); y[i] = in.nextInt();
			}

			for (int j = 0; j <= n; j++) {
				for (int k = 0; k <= n; k++) {
					dist[j][k] = Math.abs(x[j] - x[k]) + Math.abs(y[j] - y[k]);
				}
			}

			memo = new int[n + 1][1 << (n + 1)];

			for (int[] rows : memo) Arrays.fill(rows, -1);

			string.append("The shortest path has length " + solve(0, 1) + "\n");
		}
		System.out.print(string);
	}
}

class Parser
{
   final private int BUFFER_SIZE = 1 << 16;

   private DataInputStream din;
   private byte[] buffer;
   private int bufferPointer, bytesRead;

   public Parser(InputStream in)
   {
      din = new DataInputStream(in);
      buffer = new byte[BUFFER_SIZE];
      bufferPointer = bytesRead = 0;
   }

   public long nextLong() throws Exception
   {
      long ret = 0;
      byte c = read();
      while (c <= ' ') c = read();
      boolean neg = c == '-';
      if (neg) c = read();
      do
      {
         ret = ret * 10 + c - '0';
         c = read();
      } while (c > ' ');
      if (neg) return -ret;
      return ret;
   }
   
   //reads in the next string
   public String next() throws Exception
   {
      StringBuilder ret =  new StringBuilder();
      byte c = read();
      while (c <= ' ') c = read();
      do
      {
         ret = ret.append((char)c);
         c = read();
      } while (c > ' ');
      return ret.toString();
   }

   public int nextInt() throws Exception
   {
      int ret = 0;
      byte c = read();
      while (c <= ' ') c = read();
      boolean neg = c == '-';
      if (neg) c = read();
      do
      {
         ret = ret * 10 + c - '0';
         c = read();
      } while (c > ' ');
      if (neg) return -ret;
      return ret;
   }
   
   private void fillBuffer() throws Exception
   {
      bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
      if (bytesRead == -1) buffer[0] = -1;
   }

   private byte read() throws Exception
   {
      if (bufferPointer == bytesRead) fillBuffer();
      return buffer[bufferPointer++];
   }
}
