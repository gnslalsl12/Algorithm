package day1217;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_16946 {	//빈공간에 인접한 빈공간 연결된 갯수 쓰고 그거 가져가면 되겠네
	static int N, M, maps[][];
	static int[][] deltas = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		maps = new int[N][M];
		for (int i = 0; i < N; i++) {
			String temp = read.readLine();
			for (int j = 0; j < M; j++) {
				if (temp.charAt(j) == '1')
					maps[i][j] = 1;
				else
					maps[i][j] = 0;
			}
		} // input
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (maps[i][j] != 0)
					BFS(i, j);
				write.write(maps[i][j] + "");
			}
			write.write("\n");
		}
		write.close();
	}

	private static void BFS(int x, int y) {
		Queue<dirXY> BFSQ = new LinkedList<>();
		BFSQ.add(new dirXY(x, y));
		int count = 0;
		boolean[][] visits = new boolean[N][M];
		while (!BFSQ.isEmpty()) {
			dirXY temp = BFSQ.poll();
			for (int dir = 0; dir < 4; dir++) {
				int nextx = temp.x + deltas[dir][0];
				int nexty = temp.y + deltas[dir][1];
				if (!isIn(nextx, nexty) || visits[nextx][nexty] || maps[nextx][nexty] != 0)
					continue;
				visits[nextx][nexty] = true;
				count++;
				BFSQ.add(new dirXY(nextx, nexty));
			}
		}
		maps[x][y] += count;
	}

	private static boolean isIn(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < M;
	}

	private static class dirXY {
		int x, y;

		public dirXY(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

}
