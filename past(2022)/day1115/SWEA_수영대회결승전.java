package day1115;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class SWEA_수영대회결승전 {
	static int N;
	static int[][] maps;
	static int[][] deltas = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	static int[] Torns;

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(read.readLine());
		StringTokenizer tokens;
		StringBuilder sb = new StringBuilder();
		for (int test = 1; test <= T; test++) {
			N = Integer.parseInt(read.readLine());
			maps = new int[N][N];
			Torns = new int[N];
			for (int i = 0; i < N; i++) {
				tokens = new StringTokenizer(read.readLine());
				for (int j = 0; j < N; j++) {
					int temp = Integer.parseInt(tokens.nextToken());
					if (temp == 1) { // 섬만 아니면 일단 true
						maps[i][j] = -1;
					} else if (temp == 2) {
						Torns[i] |= 1 << j;
						maps[i][j] = Integer.MAX_VALUE;
					} else {
						maps[i][j] = Integer.MAX_VALUE;
					}

				}
			}
			tokens = new StringTokenizer(read.readLine());
			dirXY start = new dirXY(Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken()), 0);
			tokens = new StringTokenizer(read.readLine());
			dirXY dest = new dirXY(Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken()), 0);
			int tempresult = bfs(start, dest);
			sb.append("#" + test + " " + tempresult + "\n");
		}
		System.out.print(sb);
	}

	private static int bfs(dirXY start, dirXY dest) {
		PriorityQueue<dirXY> PQ = new PriorityQueue<>();
		if (maps[start.x][start.y] == -2) {
			start.time = 2;
		}
		maps[start.x][start.y] = start.time;
		PQ.add(start);

		int result = Integer.MAX_VALUE;
		while (!PQ.isEmpty()) {
			dirXY temp = PQ.poll();
			if (temp.x == dest.x && temp.y == dest.y) {
				result =temp.time;
				break;
			}
			if (maps[temp.x][temp.y] != -2 && maps[temp.x][temp.y] < temp.time)
				continue;
			for (int dir = 0; dir < 4; dir++) {
				int nextx = temp.x + deltas[dir][0];
				int nexty = temp.y + deltas[dir][1];
				if (!isIn(nextx, nexty) || maps[nextx][nexty] == -1)
					continue;
				int temptime = temp.time;
				if ((Torns[nextx] & (1 << nexty)) != 0) { // 다음에 가려는 위치가 소용돌잉 일 때는 일단 무조건 추가
					temptime += 2 - (temptime) % 3;
				} // 없어질 때까지 기다리기
				temptime += 1; // 이동하는데 걸리는 시간
				if (maps[nextx][nexty] > temptime) {
					PQ.offer(new dirXY(nextx, nexty, temptime));
					maps[nextx][nexty] = temptime;
				}

			}
		}
		if(result == Integer.MAX_VALUE) result = -1;
		return result;
	}

	private static boolean isIn(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}

	private static class dirXY implements Comparable<dirXY> {
		int x;
		int y;
		int time;

		public dirXY(int x, int y, int time) {
			super();
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
