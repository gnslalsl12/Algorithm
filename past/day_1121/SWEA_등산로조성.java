package day_1121;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

class SWEA_등산로조성 {
	static int N, K, maps[][];;
	static Queue<dirXY> HighestXY;
	static int[][] deltas = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	static int result;

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(read.readLine());
		for (int test = 1; test <= T; test++) {
			StringTokenizer tokens = new StringTokenizer(read.readLine());
			N = Integer.parseInt(tokens.nextToken());
			K = Integer.parseInt(tokens.nextToken());
			maps = new int[N][N];
			HighestXY = new LinkedList<>();
			int highone = -1;
			result = 0;
			for (int i = 0; i < N; i++) {
				tokens = new StringTokenizer(read.readLine());
				for (int j = 0; j < N; j++) {
					maps[i][j] = Integer.parseInt(tokens.nextToken());
					if (highone < maps[i][j]) {
						highone = maps[i][j];
						HighestXY = new LinkedList<>();
						HighestXY.add(new dirXY(i, j, highone));
					} else if (highone == maps[i][j]) {
						HighestXY.add(new dirXY(i, j, highone));
					}
				}
			}
			while (!HighestXY.isEmpty()) {
				getRoad(HighestXY.poll());
			}
			write.write("#" + test + " " + result + "\n");
		}
		write.close();
	}

	private static void getRoad(dirXY temphigh) {
		Stack<TempRoad> TRstack = new Stack<>();
		TRstack.add(new TempRoad(temphigh.x, temphigh.y, temphigh.h, 1, true, new int[N]));
		int longest = -1;
		while (!TRstack.isEmpty()) {
			TempRoad temp = TRstack.pop();
			longest = Math.max(temp.count, longest);
			for (int dir = 0; dir < 4; dir++) {
				int nextx = temp.x + deltas[dir][0];
				int nexty = temp.y + deltas[dir][1];
				if (!isIn(nextx, nexty) || ((temp.visits[nextx] & (1 << nexty)) != 0))
					continue;
				if (maps[nextx][nexty] >= temp.height) { // 나보다 크거나 같음
					if (!temp.availDig)
						continue;
					if (temp.height <= maps[nextx][nexty] - K)
						continue;
					// 팔 수 있음
					TRstack.add(new TempRoad(nextx, nexty, temp.height - 1, temp.count + 1, false, temp.visits));
				} else {
					TRstack.add(
							new TempRoad(nextx, nexty, maps[nextx][nexty], temp.count + 1, temp.availDig, temp.visits));
				}
			}
		}
		result = Math.max(result, longest);
	}

	private static boolean isIn(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}

	private static class TempRoad {
		int x;
		int y;
		int height;
		int count;
		boolean availDig;
		int[] visits;

		public TempRoad(int x, int y, int height, int count, boolean availDig, int[] visits) {
			this.x = x;
			this.y = y;
			this.height = height;
			this.count = count;
			this.availDig = availDig;
			this.visits = visits.clone();
			this.visits[this.x] |= 1 << this.y;
		}

	}

	private static class dirXY {
		int x;
		int y;
		int h;

		public dirXY(int x, int y, int h) {
			this.x = x;
			this.y = y;
			this.h = h;
		}

	}

}
