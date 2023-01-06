package day0930;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 벽을 부수기 전까지 BFS 한 번
 * 벽을 넘어서 BFS 한 번
 */
public class BOJ_2206 {
	static int[][] deltas = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	static int N, M;
	static int[][] maps;
	static Queue<dirXY> ReadyToBreak = new LinkedList<>();
	static int result;

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());

		maps = new int[N + 1][M + 1];
		for (int i = 1; i <= N; i++) {
			String templine = read.readLine();
			for (int j = 1; j <= M; j++) {
				if (templine.charAt(j - 1) - '0' == 1)
					maps[i][j] = -1;
			}
		} // 매핑
		if (N == 1 && M == 1) {
			System.out.println(1);
			return;
		}
		result = Integer.MAX_VALUE;
		BFSbefore(new dirXY(1, 1, 1)); // 벽 깨기 직전까지 출발

		// 현재 벽을 깨기 직전까지의 상황이 readytobreak에 저장됨
		while (!ReadyToBreak.isEmpty()) {
			BFSafter(ReadyToBreak.poll());
		}
		if (result == Integer.MAX_VALUE)
			result = -1;

		System.out.println(result);
	}

	static void BFSafter(dirXY startpoint) {
		Queue<dirXY> BFSQ = new LinkedList<>();
		BFSQ.add(startpoint);

		while (!BFSQ.isEmpty()) {
			dirXY temp = BFSQ.poll();
			int nowx = temp.x;
			int nowy = temp.y;

			for (int dir = 0; dir < 4; dir++) {
				int nextx = nowx + deltas[dir][0];
				int nexty = nowy + deltas[dir][1];
				if (!isIn(nextx, nexty))
					continue;
				if (maps[nextx][nexty] < 0 && -1 * maps[nextx][nexty] <= (temp.count + 1))
					continue; // 나보다 빠르게 온 곳일 때
				maps[nextx][nexty] = -1 * (temp.count + 1);
				if (nextx == N && nexty == M) {
					result = Math.min(result, temp.count + 1);
					return;
				}
				BFSQ.add(new dirXY(nextx, nexty, temp.count + 1));
			}
		}
	}

	static void BFSbefore(dirXY startpoint) {
		boolean[][] visitedwall = new boolean[N + 1][M + 1];
		Queue<dirXY> BFSQ = new LinkedList<>();
		BFSQ.add(startpoint);
		maps[startpoint.x][startpoint.y] = -1 * startpoint.count;
		while (!BFSQ.isEmpty()) {
			dirXY temp = BFSQ.poll();
			int nowx = temp.x;
			int nowy = temp.y;
			for (int dir = 0; dir < 4; dir++) {
				int nextx = nowx + deltas[dir][0];
				int nexty = nowy + deltas[dir][1];

				if (nextx == N && nexty == M) {
					result = Math.min(result, temp.count + 1);
					return;
				}
				if (!isIn(nextx, nexty))
					continue;
				if (maps[nextx][nexty] == -1) { // 가려는 곳이 벽일 때 :
					if (visitedwall[nextx][nexty])
						continue;
					visitedwall[nextx][nexty] = true;

					for (int dirR = 0; dirR < 4; dirR++) {
						int nextxR = nextx + deltas[dirR][0];
						int nextyR = nexty + deltas[dirR][1];
						if (!isIn(nextxR, nextyR))
							continue;
						if (maps[nextxR][nextyR] != 0)
							continue; // 빈공간이 아니고 벽이거나 지나오 ㄴ길이 때
						ReadyToBreak.add(new dirXY(nextx, nexty, temp.count + 1));
						break;
					}
					continue;
				}
				if (maps[nextx][nexty] < 0 && -1 * maps[nextx][nexty] <= (temp.count + 1)) // 방문한 곳 && 나보다 빠름
					continue;
				maps[nextx][nexty] = -1 * (temp.count + 1);
				// 범위 안이고 벽이 아닐 때
				BFSQ.add(new dirXY(nextx, nexty, temp.count + 1));
			}
		}

	}

	static boolean isIn(int x, int y) {
		return x >= 1 && x <= N && y >= 1 && y <= M;
	}

	static class dirXY {
		int x;
		int y;
		int count;

		public dirXY(int x, int y, int count) {
			super();
			this.x = x;
			this.y = y;
			this.count = count;
		}
	}

}
