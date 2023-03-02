package SWEA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

public class SWEA_1868_파핑파핑지뢰찾기 {
	static int N;
	static int[][] Map;
	static int[][] deltas = { { -1, -1, }, { -1, 1 }, { 1, -1 }, { 1, 1 }, { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 },
			{ 0, 0 } };
	static Queue<int[]> BombList = new LinkedList<>();
	static int ResultCount;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(read.readLine());
		for (int test = 1; test <= T; test++) {
			N = Integer.parseInt(read.readLine());
			Map = new int[N][N];
			ResultCount = 0;
			for (int i = 0; i < N; i++) {
				String temp = read.readLine();
				for (int j = 0; j < N; j++) {
					if (temp.charAt(j) == '*') {
						Map[i][j] = -1;
						BombList.add(new int[] { i, j });
					}
				}
			}
			solv();
			write.write("#" + test + " " + ResultCount + "\n");
		}
		write.close();
		read.close();
	}

	private static void solv() {
		setCount();
		getZeroArea();
		getRemainCount();
	}

	private static void getRemainCount() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (Map[i][j] != -1)
					ResultCount++;
			}
		}
	}

	private static void setZeroAreaGathered(int i, int j) {
		ResultCount++;
		Queue<int[]> XYlist = new LinkedList<>();
		Map[i][j] = -1;
		XYlist.add(new int[] { i, j });
		while (!XYlist.isEmpty()) {
			int[] temp = XYlist.poll();
			int x = temp[0];
			int y = temp[1];
			for (int dir = 0; dir < 8; dir++) {
				int nextx = x + deltas[dir][0];
				int nexty = y + deltas[dir][1];
				if (!isIn(nextx, nexty) || Map[nextx][nexty] == -1)
					continue;
				if (Map[nextx][nexty] == 0) {
					XYlist.add(new int[] { nextx, nexty });
				}
				Map[nextx][nexty] = -1;
			}
		}
	}

	private static void getZeroArea() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (Map[i][j] == 0) {
					setZeroAreaGathered(i, j);
				}
			}
		}
	}

	private static void setCount() {
		while (!BombList.isEmpty()) {
			int[] temp = BombList.poll();
			int x = temp[0];
			int y = temp[1];
			for (int dir = 0; dir < 8; dir++) {
				int nextx = x + deltas[dir][0];
				int nexty = y + deltas[dir][1];
				if (!isIn(nextx, nexty) || Map[nextx][nexty] == -1)
					continue;
				Map[nextx][nexty]++;
			}
		}
	}

	private static boolean isIn(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}

}
