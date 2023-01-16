package day0116;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_2615 {
	static int[] Bmaps = new int[19], Wmaps = new int[19];
	static int[][] deltas = { { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 } };
	static int rx, ry;

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer tokens;
		for (int i = 0; i < 19; i++) {
			tokens = new StringTokenizer(read.readLine());
			for (int j = 0; j < 19; j++) {
				char temp = tokens.nextToken().charAt(0);
				if (temp == '1')
					Bmaps[i] |= 1 << j;
				else if (temp == '2')
					Wmaps[i] |= 1 << j;
			}
		}
		rx = -1;
		ry = -1;
		int result = solv();
		if (result == 0)
			write.write("0\n");
		else
			write.write(result + "\n" + rx + " " + ry + "\n");
		write.close();
		read.close();
	}

	private static int solv() {
		for (int i = 0; i < 19; i++) {
			for (int j = 0; j < 19; j++) {
				if ((Bmaps[i] & 1 << j) != 0) {
					if (getSearch(i, j, Bmaps))
						return 1;
				} else if ((Wmaps[i] & 1 << j) != 0) {
					if (getSearch(i, j, Wmaps))
						return 2;
				}
			}
		}
		return 0;
	}

	private static boolean getSearch(int x, int y, int[] maps) {
		rx = x + 1;
		ry = y + 1;
		boolean done = false;
		for (int dir = 0; dir < 4; dir++) {
			int count = 1;
			int nextx = x;
			int nexty = y;
			while (true) {
				nextx += deltas[dir][0];
				nexty += deltas[dir][1];
				if (!isIn(nextx, nexty) || (maps[nextx] & 1 << nexty) == 0)
					break;
				count++;
				if (count == 6)
					break;
			}
			if (count == 5) {
				done = true;
				break;
			}
		}
		return done;
	}

	private static boolean isIn(int x, int y) {
		return x >= 0 && y >= 0 && x < 19 && y < 19;
	}

}
