import java.util.*;
import java.io.*;

public class Main {
	static int N, K, R;
	static int[][] CrossMap;
	static int[][] Deltas;
	static boolean[] Locs;
	static boolean[][] Vis;
	static int Result;

	public static void main(String[] args) throws IOException {
		init();
		solv();
	}

	private static void init() throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		K = Integer.parseInt(tokens.nextToken());
		R = Integer.parseInt(tokens.nextToken());
		CrossMap = new int[N][N];
		for (int r = 0; r < R; r++) {
			tokens = new StringTokenizer(read.readLine());
			int i = Integer.parseInt(tokens.nextToken()) - 1;
			int j = Integer.parseInt(tokens.nextToken()) - 1;
			int nextI = Integer.parseInt(tokens.nextToken()) - 1;
			int nextJ = Integer.parseInt(tokens.nextToken()) - 1;
			if (i == nextI) {
				CrossMap[j < nextJ ? i : nextI][j < nextJ ? j : nextJ] |= 1 << 1;
				CrossMap[j < nextJ ? nextI : i][j < nextJ ? nextJ : j] |= 1 << 3;
			} else {
				CrossMap[i < nextI ? i : nextI][i < nextI ? j : nextJ] |= 1 << 2;
				CrossMap[i < nextI ? nextI : i][i < nextI ? nextJ : j] |= 1 << 0;
			}
		}
		Locs = new boolean[N * N + 1];
		for (int k = 0; k < K; k++) {
			tokens = new StringTokenizer(read.readLine());
			int i = Integer.parseInt(tokens.nextToken()) - 1;
			int j = Integer.parseInt(tokens.nextToken()) - 1;
			Locs[i * N + j] = true;
		}
		Vis = new boolean[N][N];
		Deltas = new int[][] { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
		Result = 0;
		read.close();
	}

	private static void solv() throws IOException {
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		getMetCows();
		write.write(Result + "\n");
		write.close();
	}

	private static void getMetCows() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!Vis[i][j]) {
					setNewMet(i, j);
				}
			}
		}
		Result /= 2;
	}

	private static void setNewMet(int i, int j) {
		int metCount = 0;
		Queue<Integer> BFSQ = new LinkedList<>();
		BFSQ.add(i * N + j);
		Vis[i][j] = true;
		while (!BFSQ.isEmpty()) {
			int next = BFSQ.poll();
			int beforeI = next / N;
			int beforeJ = next % N;
			if (Locs[next])
				metCount++;
			for (int dir = 0; dir < 4; dir++) {
				int nextI = beforeI + Deltas[dir][0];
				int nextJ = beforeJ % N + Deltas[dir][1];
				if (!isIn(nextI, nextJ) || Vis[nextI][nextJ])
					continue;
				if ((CrossMap[beforeI][beforeJ] & (1 << dir)) != 0) // 길을 건너야함
					continue;
				int nextLoc = nextI * N + nextJ;
				Vis[nextI][nextJ] = true;
				BFSQ.add(nextLoc);
			}
		}
		Result += (K - metCount) * metCount; // 여기서 만난 무리와 나머지 무리는 만나지 못함
	}

	private static boolean isIn(int i, int j) {
		return i >= 0 && j >= 0 && i < N && j < N;
	}

}