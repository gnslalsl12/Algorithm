package day1114;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_6087 {
	static int W, H;
	static int[][] maps;
	static dirXY startC, destC;
	static int[][] deltas = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		W = Integer.parseInt(tokens.nextToken());
		H = Integer.parseInt(tokens.nextToken());
		maps = new int[H][W];
		startC = new dirXY(0, 0, -2, -1);
		for (int i = 0; i < H; i++) {
			String templine = read.readLine();
			for (int j = 0; j < W; j++) {
				if (templine.charAt(j) == '*') {
					maps[i][j] = -1;
				} else {
					maps[i][j] = Integer.MAX_VALUE;
				}
				if (templine.charAt(j) == 'C') {
					if (startC.turncount == -2) {
						startC = new dirXY(i, j, -1, -1);
						maps[i][j] = 0;
					} else {
						destC = new dirXY(i, j, -1, -1);
						maps[i][j] = Integer.MAX_VALUE;
					}
				}
			}
		}
		// input
		System.out.println(bfs());
	}

	private static int bfs() {
		PriorityQueue<dirXY> BFSQ = new PriorityQueue<>();
		BFSQ.add(startC);
		int result = Integer.MAX_VALUE;
		while (!BFSQ.isEmpty()) {
			dirXY temp = BFSQ.poll();
			if (temp.x == destC.x && temp.y == destC.y) {
				result = Math.min(result, temp.turncount);
				break;
			}
			if (maps[temp.x][temp.y] < temp.turncount)
				continue;
			for (int dir = 0; dir < 4; dir++) {
				int nextx = temp.x + deltas[dir][0];
				int nexty = temp.y + deltas[dir][1];
				if (!isIn(nextx, nexty))
					continue;
				int tempcount = temp.turncount;
				if (dir != temp.dir)
					tempcount++;
				if (maps[nextx][nexty] >= tempcount) {
					maps[nextx][nexty] = tempcount;
					BFSQ.add(new dirXY(nextx, nexty, tempcount, dir));
				}
			}
		}
		return result;
	}

	private static boolean isIn(int x, int y) {
		return x >= 0 && x < H && y >= 0 && y < W;
	}

	private static class dirXY implements Comparable<dirXY> {
		int x;
		int y;
		int turncount;
		int dir;

		public dirXY(int x, int y, int turncount, int dir) {
			super();
			this.x = x;
			this.y = y;
			this.turncount = turncount;
			this.dir = dir;
		}

		@Override
		public int compareTo(dirXY o) {
			if (this.turncount == o.turncount)
				return Integer.compare((Math.abs(this.x - destC.x) + Math.abs(this.y - destC.y)),
						Math.abs(o.x - destC.x) + Math.abs(o.y - destC.y));
			return Integer.compare(this.turncount, o.turncount);
		}

	}

}
