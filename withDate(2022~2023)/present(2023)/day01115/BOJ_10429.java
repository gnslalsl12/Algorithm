package day01115;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ_10429 {
	static int N, M, maps[][], sum;
	static boolean found, visits[][];
	static int[][] deltas = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	static Deque<int[]> DQ = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		maps = new int[3][3];
		found = false;
		visits = new boolean[3][3];
		for (int i = 0; i < 3; i++) {
			String templine = read.readLine();
			for (int j = 0; j < 3; j++) {
				char temp = templine.charAt(j);
				if (temp == '+') {
					maps[i][j] = 11;
				} else if (temp == '-') {
					maps[i][j] = 22;
				} else
					maps[i][j] = temp - '0';
			}
		}
		solv();
		if (!DQ.isEmpty()) {
			write.write("1\n");
			while (!DQ.isEmpty()) {
				int[] out = DQ.pollFirst();
				write.write(out[0] + " " + out[1] + "\n");
			}
		} else {
			write.write("0\n");
		}

		write.close();
		read.close();
	}

	private static void solv() {
		int[][] points = { { 0, 0 }, { 0, 2 }, { 2, 0 }, { 2, 2 }, { 1, 1 } };
		for (int[] pop : points) {
			DQ.add(pop);
			int x = pop[0];
			int y = pop[1];
			sum = maps[x][y];
			visits[x][y] = true;
			if (getSearch(x, y, 1, true))
				break;
			visits[x][y] = false;
			DQ.pop();
		}
	}

	private static boolean getSearch(int x, int y, int count, boolean plus) {
		for (int dir = 0; dir < 4; dir++) {
			int nextx = x + deltas[dir][0];
			int nexty = y + deltas[dir][1];
			if (!isIn(nextx, nexty) || visits[nextx][nexty])
				continue;
			visits[nextx][nexty] = true;
			DQ.addLast(new int[] { nextx, nexty });
			int spot = maps[nextx][nexty];
			if (spot < 10) {
				metNum(spot, plus);
				count++;
				if (count == M && sum == N) {
					return true;
				}
				if (count < M) {
					if (getSearch(nextx, nexty, count, plus))
						return true;
				}
				count--;
				metNum(spot, !plus);
			} else {
				if (spot == 11)
					plus = true;
				else
					plus = false;
				if (getSearch(nextx, nexty, count, plus))
					return true;
			}
			DQ.pollLast();
			visits[nextx][nexty] = false;
		}
		return false;
	}

	private static void metNum(int obj, boolean plus) {
		if (plus)
			sum += obj;
		else
			sum -= obj;
	}

	private static boolean isIn(int x, int y) {
		return x >= 0 && y >= 0 && x < 3 && y < 3;
	}

}
