package day1216;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1245 {
	static int N, M;
	static int[][] maps;
	static int[][] deltas = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 }, { 1, 1 }, { 1, -1 }, { -1, 1 }, { -1, -1 } };
	static int result = 0;
	static PriorityQueue<dirXY> fromH = new PriorityQueue<>();

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		maps = new int[N][M];
		for (int i = 0; i < N; i++) {
			tokens = new StringTokenizer(read.readLine());
			for (int j = 0; j < M; j++) {
				int temph = Integer.parseInt(tokens.nextToken());
				maps[i][j] = temph;
				if (temph == 0)
					continue;
				fromH.add(new dirXY(i, j, temph));
			}
		}
		// (제일 큰 놈 -1)로 다 칠하고 다시 뽑았을 때 자기보다 같거나 작으면 봉우리 카운트 X
		// 만약 내가 더 크면 주변 탐색해서 제일 큰 놈 또 있는지 확인
		Queue<dirXY> last = new LinkedList<>();
		int result = 0;
		while (!fromH.isEmpty()) {
			dirXY pop = fromH.poll();
			if (maps[pop.x][pop.y] > pop.h) { // 나보다 높은 애로 칠해짐
				continue;
			}
			BFS(pop);
			last.add(pop);
		} // 맵을 정리(제일 큰놈 외에는 하나 작은 놈으로
		while (!last.isEmpty()) {
			dirXY temp = last.poll();
			if (maps[temp.x][temp.y] == temp.h) { // 아직 사람 세우지 않음
				System.out.println(temp);
				result++;
			}
			maps[temp.x][temp.y] = temp.h + 1; // 사람 세움
			for (int dir = 0; dir < 8; dir++) {
				int nextx = temp.x + deltas[dir][0];
				int nexty = temp.y + deltas[dir][1];
				if (!isIn(nextx, nexty) || maps[nextx][nexty] == 0 || maps[nextx][nexty] == temp.h + 1)
					continue;
				maps[nextx][nexty] = temp.h + 1;
			}
		}
		System.out.println(result);

	}

	private static void BFS(dirXY pop) {
		Queue<dirXY> BFSQ = new LinkedList<>();
		BFSQ.add(pop);
		int height = pop.h;
		while (BFSQ.isEmpty()) {
			dirXY temp = BFSQ.poll();
			for (int dir = 0; dir < 4; dir++) {
				int nextx = temp.x + deltas[dir][0];
				int nexty = temp.y + deltas[dir][1];
				if (!isIn(nextx, nexty) || maps[nextx][nexty] == height || maps[nextx][nexty] == 0)
					continue;
				maps[nextx][nexty] = height;
				BFSQ.add(new dirXY(nextx, nexty));
			}
		}
	}

	private static boolean isIn(int x, int y) {
		return x >= 0 && y >= 0 && x < N && y < M;
	}

	private static class dirXY implements Comparable<dirXY> {
		int x;
		int y;
		int h;

		@Override
		public String toString() {
			return "[x=" + x + ", y=" + y + ", h=" + h + "]";
		}

		public dirXY(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

		public dirXY(int x, int y, int h) {
			this.x = x;
			this.y = y;
			this.h = h;
		}

		@Override
		public int compareTo(dirXY o) {
			return Integer.compare(o.h, this.h);
		}

	}

}
