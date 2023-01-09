package SolvedAc.CLASS4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2638 {
	static int N, M;
	static char[][] maps;
	static Queue<dirXY> Q = new LinkedList<>();
	static int[][] deltas = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
	static int result = 0;
	static boolean done = false;

	public static void main(String[] args) throws IOException { // 빈공간에서 탐색을 하고 주위에 하나도 없으면 다음 Q에 안넣음
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		maps = new char[N][M];
		for (int i = 0; i < N; i++) {
			tokens = new StringTokenizer(read.readLine());
			for (int j = 0; j < M; j++) {
				int temp = Integer.parseInt(tokens.nextToken());
				if (temp == 0) {
					maps[i][j] = 'b';
					Q.add(new dirXY(i, j));
				} else
					maps[i][j] = 'c';
			}
		}
		while (!done) {
			melting();
//			print();
		}
		System.out.println(result - 1);
	}

	private static void print() {
		System.out.println();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (maps[i][j] == 'b')
					sb.append("0 ");
				else
					sb.append(maps[i][j] + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

	private static void addBlank(int x, int y) {
		Queue<dirXY> tempQ = new LinkedList<>();
		tempQ.add(new dirXY(x, y));
		Q.add(new dirXY(x, y));
		boolean[][] visit = new boolean[N][M];
		while (!tempQ.isEmpty()) {
			dirXY temp = tempQ.poll();
			
		}
	}

	private static void melting() {
		result++;
		Queue<dirXY> tempMelt = new LinkedList<>();
		int totalcount = 0;
		int size = Q.size();
		while (size-- > 0) {
			dirXY temp = Q.poll();
			int count = 0;
			for (int dir = 0; dir < 4; dir++) {
				int nextx = temp.x + deltas[dir][0];
				int nexty = temp.y + deltas[dir][1];

				if (!isIn(nextx, nexty) || maps[nextx][nexty] == 'b')
					continue;
				count++;
				if (maps[nextx][nexty] == 'c') {
					maps[nextx][nexty] = 'm';
					tempMelt.add(new dirXY(nextx, nexty));
				} else {
					maps[nextx][nexty] = 'b';
					Q.add(new dirXY(nextx, nexty));
				}
			}
			totalcount += count;
			if (count == 0)
				continue;
			Q.add(temp);

		}
		while (!tempMelt.isEmpty()) {
			dirXY temp = tempMelt.poll();
			if (maps[temp.x][temp.y] == 'm')
				maps[temp.x][temp.y] = 'c';
		}
		if (totalcount == 0)
			done = true;
	}

	private static boolean isIn(int x, int y) {
		return x >= 0 && y >= 0 && x < N && y < M;
	}

	private static class dirXY {
		int x, y;

		public dirXY(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}

}
