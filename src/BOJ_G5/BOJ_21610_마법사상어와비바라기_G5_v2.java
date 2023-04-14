package BOJ_G5;

import java.util.*;
import java.io.*;

public class BOJ_21610_마법사상어와비바라기_G5_v2 {
	static int N, M;
	static long[][] Map;
	static int[][] deltas;
	static Queue<int[]> MoveCloudsQ;
	static ArrayList<int[]> Clouds;
	static long[] DeletedClouds;

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		init();
		for (int r = 1; r <= N; r++) {
			tokens = new StringTokenizer(read.readLine());
			for (int c = 1; c <= N; c++) {
				Map[r][c] = Integer.parseInt(tokens.nextToken());
			}
		}
		for (int m = 1; m <= M; m++) {
			tokens = new StringTokenizer(read.readLine());
			int d = Integer.parseInt(tokens.nextToken());
			int s = Integer.parseInt(tokens.nextToken());
			MoveCloudsQ.add(new int[] { d, s });
		}
		// 입력
		write.write(solv() + "\n");
		write.close();
		read.close();
	}

	private static void init() {
		Map = new long[N + 1][N + 1];
		deltas = new int[][] { {}, { 0, -1 }, { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 },
				{ 1, -1 } };
		MoveCloudsQ = new LinkedList<>();
		Clouds = new ArrayList<>();
		Clouds.add(new int[] { N, 1 });
		Clouds.add(new int[] { N, 2 });
		Clouds.add(new int[] { N - 1, 1 });
		Clouds.add(new int[] { N - 1, 2 });
		DeletedClouds = new long[N + 1];
	}

	private static long solv() {
		long result = 0;
		while (!MoveCloudsQ.isEmpty()) {
			doMoveClouds(MoveCloudsQ.poll());
			doDuplicateWater();
			result = doMakeClouds();
		}
		return result;
	}

	private static void doMoveClouds(int[] input) {
		int d = input[0];
		int s = input[1];
		int size = Clouds.size();
		for (int i = 0; i < size; i++) {
			int[] cloud = Clouds.get(i);
			int nextR = (cloud[0] - 1) + deltas[d][0] * s;
			int nextC = (cloud[1] - 1) + deltas[d][1] * s;
			nextR %= N;
			if (nextR < 0)
				nextR += N;
			nextC %= N;
			if (nextC < 0)
				nextC += N;
			Map[++nextR][++nextC]++; // 비 내리기
			DeletedClouds[nextR] |= 1 << nextC;
			Clouds.set(i, new int[] { nextR, nextC });
		}
	}

	private static void doDuplicateWater() {
		int size = Clouds.size();
		for (int i = 0; i < size; i++) {
			int[] cloud = Clouds.get(i);
			int r = cloud[0];
			int c = cloud[1];
			for (int dir = 2; dir <= 8; dir += 2) {
				int nextR = r + deltas[dir][0];
				int nextC = c + deltas[dir][1];
				if (isIn(nextR, nextC) && Map[nextR][nextC] != 0)
					Map[r][c]++;
			}
		}
		Clouds.clear();
	}

	private static long doMakeClouds() {
		long result = 0;
		for (int r = 1; r <= N; r++) {
			for (int c = 1; c <= N; c++) {
				if (Map[r][c] >= 2 && (DeletedClouds[r] & (1 << c)) == 0) {
					Map[r][c] -= 2;
					Clouds.add(new int[] { r, c });
				}
				result += Map[r][c];
			}
		}
		DeletedClouds = new long[N + 1];
		return result;
	}

	private static boolean isIn(int r, int c) {
		return r >= 1 && r <= N && c >= 1 && c <= N;
	}
}
