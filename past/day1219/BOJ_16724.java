package day1219;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_16724 {
	static int N, M, maps[][];
	static int[][] deltas = { { -1, 0 }, { 0, 1 }, { 0, -1 }, { 1, 0 } };
	static int result = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		maps = new int[N][M];
		for (int i = 0; i < N; i++) {
			String temp = read.readLine();
			for (int j = 0; j < M; j++) {
				char t = temp.charAt(j);
				if (t == 'U') {
					maps[i][j] = 0;
				} else if (t == 'R') {
					maps[i][j] = 1;
				} else if (t == 'L') {
					maps[i][j] = 2;
				} else {
					maps[i][j] = 3;
				}
			}
		}
		int result = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (maps[i][j] != -1) {
					result++;
					move(i, j);
				}
			}
		}
		System.out.println(result);
	}

	private static void move(int i, int j) {
		Stack<dirXY> ST = new Stack<>();
		ST.add(new dirXY(i, j));
		while (!ST.isEmpty()) {
			dirXY temp = ST.pop();
			int tempdir = maps[temp.x][temp.y];
			if (maps[temp.x][temp.y] == -1)
				continue;
			maps[temp.x][temp.y] = -1;
			int flowx = temp.x + deltas[tempdir][0];
			int flowy = temp.y + deltas[tempdir][1];
			if (maps[flowx][flowy] != -1) {
				ST.add(new dirXY(flowx, flowy)); // 흘러가는대로 감
			}
			for (int dir = 0; dir < 4; dir++) {
				if (tempdir == dir)
					continue;
				int nextx = temp.x + deltas[dir][0];
				int nexty = temp.y + deltas[dir][1];
				if (!isIn(nextx, nexty) || maps[nextx][nexty] == -1 || maps[nextx][nexty] + dir != 3)
					continue; // 범위 밖 / 이미 확인한 곳 / 역으로 가는 방향이 아님
				ST.add(new dirXY(nextx, nexty));
			}
		}
	}

	private static boolean isIn(int x, int y) {
		return x >= 0 && y >= 0 && x < N && y < M;
	}

	private static class dirXY {
		int x;
		int y;

		public dirXY(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}

}
