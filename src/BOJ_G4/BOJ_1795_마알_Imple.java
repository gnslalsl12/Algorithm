package BOJ_G4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1795_¸¶¾Ë_Imple {
	static int N, M;
	static int[][] deltas = { { -2, 1 }, { -1, 2 }, { 1, 2 }, { 2, 1 }, { 2, -1 }, { 1, -2 }, { -1, -2 }, { -2, -1 } };
	static int[][] Map;
	static Queue<int[]> KnightList;
	static ArrayList<int[]>[] FootPrints;
	static int MaxKnight;
	static int KnightCount;

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		init();
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		for (int i = 0; i < N; i++) {
			String templine = read.readLine();
			for (int j = 0; j < M; j++) {
				if (templine.charAt(j) != '.') {
					int pow = templine.charAt(j) - '0';
					KnightList.add(new int[] { i, j, pow });
					KnightCount++;
					MaxKnight = Math.max(MaxKnight, pow);
				}
			}
		}
	}

	private static void init() {
		Map = new int[10][10];
		KnightList = new LinkedList<>();
		FootPrints = new ArrayList[10];
		for (int i = 1; i <= 9; i++) {
			FootPrints[i] = new ArrayList<>();
		}
		MaxKnight = -1;
		KnightCount = 0;

	}

	private static void solv() {
		getWholeFP();
	}

	private static void getWholeFP() {
		for (int i = 1; i < MaxKnight; i++) {
			getFootPrint(i);
		}
	}

	private static void getFootPrint(int input) {
		boolean[][] FootMap = new boolean[2*N][19];
		FootMap[10][10] = true;
		int[] startpoint = { 10, 10, 0 };
		Queue<int[]> Q = new LinkedList<>();
		Q.add(startpoint);
		while (!Q.isEmpty()) {
			int[] temp = Q.poll();
			int count = temp[2];
			for (int dir = 0; dir < 8; dir++) {
				int nextx = temp[0] + deltas[dir][0];
				int nexty = temp[1] + deltas[dir][1];
				if (!isInFP(nextx, nexty))
					continue;
				if (count % input == 0) { // setting
					if (FootMap[nextx][nexty])
						continue;
					FootMap[nextx][nexty] = true;
					FootPrints[input].add(new int[] { nextx - 10, nexty - 10 });
				}
				Q.add(new int[] { nextx, nexty, count + 1 });
			}
		}

	}

	private static boolean isInFP(int x, int y) {
		return x >= 0 && x < 19 && y >= 0 && y < 19;
	}

	private static boolean isIn(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < M;
	}

}
