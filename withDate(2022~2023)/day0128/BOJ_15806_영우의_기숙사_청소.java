package day0128;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_15806_영우의_기숙사_청소 {
	static int N, M, K, t;
	static Queue<dirXY> Molds = new LinkedList<>();
	static boolean[][] CheckMap;
	static ArrayList<dirXY> SpreadDeltas = new ArrayList<>();
	static int[][] deltas = { { -2, 1 }, { -1, 2 }, { 1, 2 }, { 2, 1 }, { 2, -1 }, { 1, -2 }, { -1, -2 }, { -2, -1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken()); // 방 크기 N*N
		M = Integer.parseInt(tokens.nextToken()); // 곰팡이 개수 M
		K = Integer.parseInt(tokens.nextToken()); // 검사 위치 개수 K
		t = Integer.parseInt(tokens.nextToken()); // 검사 일자
		CheckMap = new boolean[N][N];
		for (int i = 0; i < M; i++) {
			tokens = new StringTokenizer(read.readLine());
			int x = Integer.parseInt(tokens.nextToken()) - 1;
			int y = Integer.parseInt(tokens.nextToken()) - 1;
			Molds.add(new dirXY(x, y));
		}
		for (int i = 0; i < K; i++) {
			tokens = new StringTokenizer(read.readLine());
			int x = Integer.parseInt(tokens.nextToken()) - 1;
			int y = Integer.parseInt(tokens.nextToken()) - 1;
			CheckMap[x][y] = true;
		}
		String result = solv() ? "YES\n" : "NO\n";
		write.write(result);
		write.close();
		read.close();
	}

	private static boolean solv() {
		getDoubleSpreadDeltas();
		while (!Molds.isEmpty()) {
			dirXY M = Molds.poll();
			if (getOddorEvenSpread(M.x, M.y))
				return true;
		}
		return false;
	}

	private static void getDoubleSpreadDeltas() {
		boolean[][] StartSpread = new boolean[20][20];
		for (int dir = 0; dir < 8; dir++) {
			for (int[] d : deltas) {
				int x = d[0] + deltas[dir][0];
				int y = d[1] + deltas[dir][1];
				if (StartSpread[x + 10][y + 10])
					continue;
				StartSpread[x + 10][y + 10] = true;
				SpreadDeltas.add(new dirXY(x, y));
			}
		}
	}

	private static boolean getOddorEvenSpread(int x, int y) {
		Queue<dirXY> BFSQ = new LinkedList<>();
		if (t == 1) {
			for (int dir = 0; dir < 8; dir++) {
				if (CheckMap[x + deltas[dir][0]][y + deltas[dir][1]])
					return true;
			}
			return false;
		}
		if (t % 2 == 0) {
			BFSQ.add(new dirXY(x, y));
		} else {
			for (int dir = 0; dir < 8; dir++) {
				int nextx = x + deltas[dir][0];
				int nexty = y + deltas[dir][1];
				if (isIn(nextx, nexty))
					BFSQ.add(new dirXY(nextx, nexty));
			}
		}
		boolean[][][] TempSpreadMaps = new boolean[2][N][N];
		for (int day = t % 2 == 1 ? 1 : 2; day <= t; day += 2) {
			int size = BFSQ.size();
			while (size-- > 0) {
				dirXY temp = BFSQ.poll();
				TempSpreadMaps[0][temp.x][temp.y] = true;
				for (dirXY Sdelta : SpreadDeltas) {
					int nextx = temp.x + Sdelta.x;
					int nexty = temp.y + Sdelta.y;
					if (!isIn(nextx, nexty) || TempSpreadMaps[1][nextx][nexty])
						continue;
					if (CheckMap[nextx][nexty])
						return true;
					TempSpreadMaps[1][nextx][nexty] = true;
					if (!TempSpreadMaps[0][nextx][nexty])
						BFSQ.add(new dirXY(nextx, nexty));
				}
			}
		}
		return false;
	}

	private static boolean isIn(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}

	private static class dirXY {
		int x;
		int y;

		public dirXY(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}

}