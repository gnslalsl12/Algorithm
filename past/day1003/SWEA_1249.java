package day1003;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class SWEA_1249 {
	static int[][] deltas = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(read.readLine());
		for (int test = 1; test <= T; test++) {
			N = Integer.parseInt(read.readLine());
			int[][][] maps = new int[N][N][2]; // 0층은 기존 맵 1층은 경로 저장값 맵
			for (int i = 0; i < N; i++) {
				String templine = read.readLine();
				for (int j = 0; j < N; j++) {
					maps[i][j][0] = templine.charAt(j) - '0';
					maps[i][j][1] = 0;
				}
			} // mapping 완료
			Queue<dirXY> BFSQ = new LinkedList<>();
			boolean[][] total = new boolean[N][N];
			boolean[][] start = new boolean[N][N];
			BFSQ.add(new dirXY(0, 0, start, 0));
			while (!BFSQ.isEmpty()) {
				dirXY temp = BFSQ.poll();
				if (total[temp.x][temp.y] && maps[temp.x][temp.y][1] < temp.wayvalue)	//현재 저장 경로값이 내 위치에 갱신된 애보다 크면 continue;
					continue;
				for (int dir = 0; dir < 4; dir++) {
					int nextx = temp.x + deltas[dir][0];
					int nexty = temp.y + deltas[dir][1];
					if (!isIn(nextx, nexty))
						continue;
					if (temp.visits[nextx][nexty])
						continue;
					temp.visits[nextx][nexty] = true;
					int tempvalue = temp.wayvalue + maps[nextx][nexty][0]; // 여기까지 온 경로 값 + 다음으로 갈 맵의 값
					if (!total[nextx][nexty]) { // 내가 아예 처음이네?
						total[nextx][nexty] = true;
					} else {
						if (maps[nextx][nexty][1] <= tempvalue) { // 누가 내 앞에 왔는데 나보다 작거나 같으면 중지
							continue;
						}
					}
					maps[nextx][nexty][1] = tempvalue;	//내가 처음이거나 내가 앞에온 사람보다 빠를 떄
					BFSQ.add(new dirXY(nextx, nexty, temp.visits, tempvalue));
				}
			}
			int result = maps[N - 1][N - 1][1];
			sb.append(String.format("#%d %d\n", test, result));
		}
		System.out.print(sb);
	}

	private static boolean isIn(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}

	private static class dirXY {
		int x;
		int y;
		boolean[][] visits;
		int wayvalue;
		public dirXY(int x, int y, boolean[][] visits, int wayvalue) {
			super();
			this.x = x;
			this.y = y;
			this.visits = new boolean[N][N];
			for (int i = 0; i < visits.length; i++) {
				this.visits[i] = visits[i].clone();
			}
			this.wayvalue = wayvalue;
		}
	}
}