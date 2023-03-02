package day1216;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1245_re {
	static int N, M;
	static int[][] maps;
	static int[][] deltas = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 }, { 1, 1 }, { 1, -1 }, { -1, 1 }, { -1, -1 } };
	static int result = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		maps = new int[N][M];
		for (int i = 0; i < N; i++) {
			tokens = new StringTokenizer(read.readLine());
			for (int j = 0; j < M; j++) {
				maps[i][j] = Integer.parseInt(tokens.nextToken());
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (maps[i][j] <= 0)
					continue;
				BFS(i, j);
			}
		}
//		print();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (maps[i][j] != 0) {
					HighBFS(i, j);
				}
			}
		}
		System.out.println(result);
	}

	private static void print() {
		System.out.println();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				sb.append(maps[i][j] + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

	private static void HighBFS(int i, int j) {
		result++;
		Queue<Integer> HBFSQ = new LinkedList<>();
		HBFSQ.add(setXY(i, j));
		while (!HBFSQ.isEmpty()) {
			int temp = HBFSQ.poll();
			int tempx = getX(temp);
			int tempy = getY(temp);
			maps[tempx][tempy] = 0;
			for (int dir = 0; dir < 8; dir++) {
				int nextx = tempx + deltas[dir][0];
				int nexty = tempy + deltas[dir][1];
				if (!isIn(nextx, nexty) || maps[nextx][nexty] == 0)
					continue;
				// 범위 안이고 0이 아닌 놈 발견
				HBFSQ.add(setXY(nextx, nexty));
				maps[nextx][nexty] = 0;
			}
		}
	}

	private static void BFS(int i, int j) {
		Queue<Integer> BFSQ = new LinkedList<>();
		BFSQ.add(setXY(i, j));
		int max = maps[i][j];
		Queue<Integer> Highest = new LinkedList<>();
		Highest.add(setXY(i, j));
		while (!BFSQ.isEmpty()) {
			int temp = BFSQ.poll();
			int tempx = getX(temp);
			int tempy = getY(temp);
			maps[tempx][tempy] = 0;
			for (int dir = 0; dir < 4; dir++) {
				int nextx = tempx + deltas[dir][0];
				int nexty = tempy + deltas[dir][1];
				if (!isIn(nextx, nexty) || maps[nextx][nexty] == 0)
					continue;
				BFSQ.add(setXY(nextx, nexty));
				if (maps[nextx][nexty] > max) { // 더 높은 곳 발견
					max = maps[nextx][nexty];
					Highest = new LinkedList<>();
					Highest.add(setXY(nextx, nexty));
				} else if (maps[nextx][nexty] == max) { // 같은 평지
					Highest.add(setXY(nextx, nexty));
				}
			}
		}
		while (!Highest.isEmpty()) {
			int pop = Highest.poll();
			maps[getX(pop)][getY(pop)] = max * -1;
		}

	}

	private static boolean isIn(int x, int y) {
		return x >= 0 && y >= 0 && x < N && y < M;
	}

	private static int setXY(int x, int y) {
		return x * M + y;
	}

	private static int getX(int S) {
		return S / M;
	}

	private static int getY(int S) {
		return S % M;
	}

}
