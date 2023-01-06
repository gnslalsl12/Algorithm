package day1011;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_9207 {
	static int N;
	static boolean[][] maps;
	static int[][] deltas = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	static int resulttime;
	static int resultcount;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(read.readLine());
		StringBuilder sb = new StringBuilder();
		for (int test = 0; test < N; test++) {
			maps = new boolean[9][9];
			resultcount = 0;
			for (int i = 0; i < 5; i++) {
				if (i == 0 || i == 4) {
					maps[i][0] = maps[i][1] = maps[i][2] = maps[i][6] = maps[i][7] = maps[i][8] = true;
				}
				String templine = read.readLine();
				for (int j = 0; j < 9; j++) {
					if (templine.charAt(j) == 'o') {
						maps[i][j] = true;
						resultcount++;
					}
				}
			}
			int tempresult = resultcount;
			if (test < N - 1)
				read.readLine();
			for (int x = 0; x < 5; x++) {
				for (int y = 0; y < 9; y++) {
					if (!isIn(x, y))
						continue;
					if (maps[x][y]) {
						DFS(x, y, tempresult, 0);
					}
				}
			}
			sb.append(resultcount + " " + (tempresult - resultcount) + "\n");
		}
		System.out.print(sb);
	}

	private static void DFS(int x, int y, int solscount, int timecount) {
		if (solscount <= resultcount) {
			resultcount = solscount;
			resulttime = timecount;
		}
		for (int dir = 0; dir < 4; dir++) {
			int nextx = x + deltas[dir][0];
			int nexty = y + deltas[dir][1];
			if (!isIn(nextx, nexty))
				continue;
			if (!maps[nextx][nexty])
				continue;
			int furtherx = nextx + deltas[dir][0];
			int furthery = nexty + deltas[dir][1];
			if (!isIn(furtherx, furthery))
				continue;
			if (maps[furtherx][furthery])
				continue;
			maps[x][y] = maps[nextx][nexty] = false;
			maps[furtherx][furthery] = true;
			for (int tempx = 0; tempx < 5; tempx++) {
				for (int tempy = 0; tempy < 9; tempy++) {
					if (!isIn(tempx, tempy))
						continue;
					if (maps[tempx][tempy]) {
						DFS(tempx, tempy, solscount - 1, timecount + 1);
					}
				}
			}
			maps[x][y] = maps[nextx][nexty] = true;
			maps[furtherx][furthery] = false;
		}
	}

	private static void print() {
		System.out.println("///////////////////////////");
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 9; j++) {
				if (maps[i][j])
					System.out.printf("O ");
				else
					System.out.printf(".... ");
			}
			System.out.println();
		}
	}

	private static boolean isIn(int x, int y) {
		return x >= 0 && y >= 0 && x < 9 && y < 9
				&& !((x == 0 || x == 4) && (y == 0 || y == 1 || y == 2 || y == 6 || y == 7 || y == 8));
	}
}