package day1013;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1917 {
	static int[][] maps = new int[6][6];
	static int[][] deltas = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	static Queue<dirXY> Planes = new LinkedList<>();
//	static int found = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens;
		StringBuilder sb = new StringBuilder();
		continueall: for (int tc = 0; tc < 3; tc++) {
			maps = new int[6][6];
			Planes = new LinkedList<>();
//			int count = 0;
			for (int i = 0; i < 6; i++) {
				tokens = new StringTokenizer(read.readLine());
				for (int j = 0; j < 6; j++) {
					maps[i][j] = Integer.parseInt(tokens.nextToken());
					if (maps[i][j] != 0) {
						Planes.offer(new dirXY(i, j));
//						count++;
					}
				}
			}
			for (int i = 0; i < 6; i++) {
				int hcount = 0;
				int vcount = 0;
				for (int j = 0; j < 6; j++) {
					if (maps[i][j] != 0) {
						hcount++;
					}
					if (maps[j][i] != 0) {
						vcount++;
					}
				}
				if (hcount > 4 || vcount > 4) {
//					System.out.println("no");
					if (tc == 2) {
						sb.append("no");
					} else {
						sb.append("no\n");
					}
					continue continueall;
				}
			}
//			if (count != 6) {
////				System.out.println("no");
//				sb.append("no\n");
//				continue continueall;
//			}
			for (dirXY pop : Planes) {
				if (!BFS(pop)) {
//					System.out.println("no");
					if (tc == 2) {
						sb.append("no");
					} else {
						sb.append("no\n");
					}
					continue continueall;
				}
			}
			if (tc == 2) {
				sb.append("yes");
			} else {
				sb.append("yes\n");
			}
		}
		System.out.print(sb);

	}

	private static boolean BFS(dirXY start) {
		Queue<dirXY> BFSQ = new LinkedList<>();
		BFSQ.offer(start);
		boolean[] dirs = new boolean[4];
		boolean[][] visited = new boolean[6][6];
		visited[start.x][start.y] = true;
		while (!BFSQ.isEmpty()) {
			dirXY temp = BFSQ.poll();
			for (int dir = 0; dir < 4; dir++) {
				int nextx = temp.x + deltas[dir][0];
				int nexty = temp.y + deltas[dir][1];
				if (!isIn(nextx, nexty) || maps[nextx][nexty] == 0 || visited[nextx][nexty])
					continue;
				visited[nextx][nexty] = true;
				if (start.x == temp.x && start.y == temp.y) {
					dirs[dir] = true;
				} else if (dirs[dir]) {
//					if (maps[start.x][start.y] == 1) {
//						found = maps[nextx][nexty];
//					}
					return true;
				}
				BFSQ.add(new dirXY(nextx, nexty));
			}
		}
		return false;
	}

	private static boolean isIn(int x, int y) {
		return x >= 0 && y >= 0 && x < 6 && y < 6;
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
