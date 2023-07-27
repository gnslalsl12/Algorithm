import java.util.*;
import java.io.*;

public class Main {

	static int[][] WholeDir = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 }, { -2, 1 }, { -1, 2 }, { 1, 2 }, { 2, 1 },
			{ 2, -1 }, { 1, -2 }, { -1, -2 }, { -2, -1 } };
	static int K, W, H;
	static boolean[][] Map;
	static int[][][] CountMap;
	static long Result;

	public static void main(String[] args) throws IOException {
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		init();
		solv();
		write.write(Result + "\n");
		write.close();
	}

	private static void init() throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(read.readLine());
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		W = Integer.parseInt(tokens.nextToken());
		H = Integer.parseInt(tokens.nextToken());
		Map = new boolean[H][W];
		CountMap = new int[H][W][K + 1];
		Result = Long.MAX_VALUE;
		for (int h = 0; h < H; h++) {
			tokens = new StringTokenizer(read.readLine());
			for (int w = 0; w < W; w++) {
				if (tokens.nextToken().equals("1"))
					Map[h][w] = true;
				Arrays.fill(CountMap[h][w], Integer.MAX_VALUE);
			}
		}
		read.close();
	}

	private static void solv() {
		getSearchBFS();
	}

	private static void getSearchBFS() {
		int[] start = new int[] { 0, 0, 0, 0 }; // i,j,tC,kC
		Queue<int[]> BFSQ = new LinkedList<>();
		BFSQ.add(start);
		CountMap[0][0][0] = 0;
		while (!BFSQ.isEmpty()) {
			int[] current = BFSQ.poll();
			int i = current[0];
			int j = current[1];
			int tC = current[2];
			int kC = current[3];
			if (CountMap[i][j][kC] != tC)
				continue;
			tC++;
			for (int dir = 0; dir < 12; dir++) {
				int nextI = i + WholeDir[dir][0];
				int nextJ = j + WholeDir[dir][1];
				if (dir == 4) {
					if (kC == K)
						break;
					kC++;
				}
				if (!isIn(nextI, nextJ) || Map[nextI][nextJ])
					continue; // 범위 밖 / 장애물
				else if (CountMap[nextI][nextJ][kC] <= tC)
					continue; // tC를 더 많이 들여서 옴
				CountMap[nextI][nextJ][kC] = tC;
				BFSQ.add(new int[] { nextI, nextJ, tC, kC });
			}
		}
		for (int k = 0; k <= K; k++) {
			Result = Math.min(Result, CountMap[H - 1][W - 1][k]);
		}
		if (Result == Integer.MAX_VALUE)
			Result = -1;
	}

	private static boolean isIn(int i, int j) {
		return i >= 0 && j >= 0 && i < H && j < W;
	}

}