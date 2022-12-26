package day1216;

/*		내가 찾은 반례
6 6 16
0 0 0 0 1 1
0 0 0 0 0 2
1 1 1 0 1 0
0 0 0 0 0 0
0 1 1 1 1 1
0 0 0 0 0 0

3 3 5
0 2 1
1 1 1
1 1 0

6 6 100
0 1 1 1 1 2
0 0 0 0 0 0
1 1 1 1 1 0
0 0 0 0 0 0
0 1 1 1 1 1
0 0 0 0 0 0

6 6 100
0 1 1 1 1 0
0 0 0 0 0 0
1 1 1 1 1 0
0 0 1 0 2 0
0 1 1 1 1 0
0 0 0 0 0 0
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_17836 {
	static int N, M, T;
	static int[][] maps;
	static int[][] deltas = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
	static dirXY sword;
	static int result = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		T = Integer.parseInt(tokens.nextToken());
		maps = new int[N][M];
		for (int i = 0; i < N; i++) {
			tokens = new StringTokenizer(read.readLine());
			for (int j = 0; j < M; j++) {
				maps[i][j] = Integer.parseInt(tokens.nextToken());
			}
		} // input
		BFS();
		if (result == Integer.MAX_VALUE)
			System.out.println("Fail");
		else
			System.out.println(result);
	}

	private static void BFS() {
		PriorityQueue<dirXY> BFSQ = new PriorityQueue<>();
		BFSQ.add(new dirXY(0, 0, 0));
		maps[0][0] = 3;
		while (!BFSQ.isEmpty()) {
			dirXY temp = BFSQ.poll();
			if (temp.time >= T || temp.time > result)
				return;
			for (int dir = 0; dir < 4; dir++) {
				int nextx = temp.x + deltas[dir][0];
				int nexty = temp.y + deltas[dir][1];
				if (!isIn(nextx, nexty))
					continue;
				if (nextx == N - 1 && nexty == M - 1) {
					result = Math.min(result, temp.time + 1);
					return;
				}
				int nextmap = maps[nextx][nexty];
				if (nextmap == 3 || nextmap == 1) {
					continue;
				}
				if (maps[nextx][nexty] == 2) {
					int withswrd = temp.time + 1 + (N - 1) - nextx + (M - 1) - nexty;
					if (withswrd <= T) {
						result = Math.min(result, withswrd);
					}
					continue;
				}
				BFSQ.add(new dirXY(nextx, nexty, temp.time + 1));
				maps[nextx][nexty] = 3;
			}
		}
	}

	private static boolean isIn(int x, int y) {
		return x >= 0 && y >= 0 && x < N && y < M;
	}

	private static class dirXY implements Comparable<dirXY> {
		int x;
		int y;
		int time;

		public dirXY(int x, int y, int time) {
			this.x = x;
			this.y = y;
			this.time = time;
		}

		@Override
		public int compareTo(dirXY o) {
			return Integer.compare(this.time, o.time);
		}

	}

}
