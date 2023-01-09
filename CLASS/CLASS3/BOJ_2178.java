package SolvedAc.CLASS3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2178 {
	static boolean[][] maps;
	static int[][] deltas = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	static int N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		maps = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			String temp = read.readLine();
			for (int j = 0; j < M; j++) {
				if (temp.charAt(j) == '1') {
					maps[i][j] = true;
				}
			}
		}
		System.out.println(BFS());
	}

	private static int BFS() {
		Queue<dirXY> bfsq = new LinkedList<>();
		bfsq.add(new dirXY(0, 0, 1));
		int result = 0;
		breakall: while (!bfsq.isEmpty()) {
			dirXY temp = bfsq.poll();
			if (!maps[temp.x][temp.y])
				continue;
			maps[temp.x][temp.y] = false;
			for (int dir = 0; dir < 4; dir++) {
				int x = temp.x + deltas[dir][0];
				int y = temp.y + deltas[dir][1];
				if (!isIn(x, y))
					continue;
				if (x == N - 1 && y == M - 1) {
					result = temp.count + 1;
					break breakall;
				}
				if (!maps[x][y])
					continue;
				bfsq.add(new dirXY(x, y, temp.count + 1));

			}
		}
		return result;
	}

	private static boolean isIn(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < M;
	}

	private static class dirXY {
		int x, y, count;

		public dirXY(int x, int y, int count) {
			super();
			this.x = x;
			this.y = y;
			this.count = count;
		}

	}
}
