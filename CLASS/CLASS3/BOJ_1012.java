package SolvedAc.CLASS3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1012 {
	static boolean[][] maps;
	static Queue<dirXY> orders;
	static int[][] deltas = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	static int result;
	static int N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(tokens.nextToken());
		for (int test = 1; test <= T; test++) {
			tokens = new StringTokenizer(read.readLine());
			M = Integer.parseInt(tokens.nextToken());
			N = Integer.parseInt(tokens.nextToken());
			int K = Integer.parseInt(tokens.nextToken());
			maps = new boolean[N][M];
			orders = new LinkedList<>();
			result = 0;
			for (int i = 0; i < K; i++) {
				tokens = new StringTokenizer(read.readLine());
				int y = Integer.parseInt(tokens.nextToken());
				int x = Integer.parseInt(tokens.nextToken());
				orders.add(new dirXY(x, y));
				maps[x][y] = true;
			}
			// 입력
			while (!orders.isEmpty()) {
				dirXY pop = orders.poll();
				if (!maps[pop.x][pop.y])
					continue;
				BFS(pop);
			}
			sb.append(result + "\n");
		}
		System.out.print(sb);
	}

	private static void BFS(dirXY input) {
		Queue<dirXY> BFSQ = new LinkedList<>();
		BFSQ.add(input);
		maps[input.x][input.y] = false;
		result++;
		while (!BFSQ.isEmpty()) {
			dirXY temp = BFSQ.poll();
			for (int dir = 0; dir < 4; dir++) {
				int nextx = temp.x + deltas[dir][0];
				int nexty = temp.y + deltas[dir][1];
				if (!isIn(nextx, nexty))
					continue;
				if (maps[nextx][nexty]) {
					maps[nextx][nexty] = false;
					BFSQ.add(new dirXY(nextx, nexty));
				}
			}
		}
	}

	private static boolean isIn(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < M;
	}

	private static class dirXY {
		int x;
		int y;

		public dirXY(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

	}
}
