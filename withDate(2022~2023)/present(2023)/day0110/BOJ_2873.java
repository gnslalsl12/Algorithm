package day0110;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2873 {
	static int R, C;
	static int[][] Maps;
	static Stacked[][] StackMaps;
	static int[][] deltas = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	static char[] DirArray = { 'U', 'R', 'D', 'L' };
	static String result;

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		R = Integer.parseInt(tokens.nextToken());
		C = Integer.parseInt(tokens.nextToken());
		Maps = new int[R][C];
		StackMaps = new Stacked[R][C];
		result = "";
		for (int i = 0; i < R; i++) {
			tokens = new StringTokenizer(read.readLine());
			for (int j = 0; j < C; j++) {
				Maps[i][j] = Integer.parseInt(tokens.nextToken());
				StackMaps[i][j] = new Stacked("", Maps[i][j]);
			}
		}
		getBFS();
		write.write(result + "\n");
		write.close();
		read.close();
	}

	private static void getBFS() {
		Queue<dirXY> Q = new LinkedList<>();
		Q.add(new dirXY(0, 0));
		while (!Q.isEmpty()) {
			dirXY temp = Q.poll();
			for (int dir = 0; dir < 4; dir++) {
				int nextx = temp.x + deltas[dir][0];
				int nexty = temp.y + deltas[dir][1];
				if (!isIn(nextx, nexty) || temp.vis[nextx][nexty])
					continue;
				long tempsum = StackMaps[temp.x][temp.y].Hstack + Maps[nextx][nexty];
				String tohere = StackMaps[temp.x][temp.y].Dstack + DirArray[dir];
				if (nextx == R - 1 && nexty == C - 1 && StackMaps[nextx][nexty].Hstack < tempsum) {
					result = tohere;
					StackMaps[nextx][nexty].Hstack = tempsum;
					continue;
				}
				if (StackMaps[nextx][nexty].Hstack < tempsum) {
					StackMaps[nextx][nexty] = new Stacked(tohere, tempsum);
					Q.add(new dirXY(nextx, nexty, temp.vis));
				}

			}
		}

	}

	private static boolean isIn(int x, int y) {
		return x >= 0 && x < R && y >= 0 && y < C;
	}

	private static class dirXY {
		int x;
		int y;
		boolean[][] vis = new boolean[R][C];

		public dirXY(int x, int y) {
			this.x = x;
			this.y = y;
			this.vis = new boolean[R][C];
			this.vis[x][y] = true;
		}

		public dirXY(int x, int y, boolean[][] vis) {
			this.x = x;
			this.y = y;
			for (int i = 0; i < R; i++) {
				this.vis[i] = vis[i].clone();
			}
			this.vis[x][y] = true;
		}

	}

	private static class Stacked {
		String Dstack;
		long Hstack;

		public Stacked(String dstack, long hstack) {
			this.Dstack = dstack;
			this.Hstack = hstack;
		}

	}

}
