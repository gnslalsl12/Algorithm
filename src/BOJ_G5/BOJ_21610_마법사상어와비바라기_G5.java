package BOJ_G5;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_21610_마법사상어와비바라기_G5 {
	static int N, M;
	static int[][] Map;
	static int[][] deltas;
	static Queue<int[]> MoveQ;
	static ArrayList<int[]> Clouds;
	static long[] Visited;

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		init();
		for (int i = 0; i < N; i++) {
			tokens = new StringTokenizer(read.readLine());
			for (int j = 0; j < N; j++) {
				Map[i][j] = Integer.parseInt(tokens.nextToken());
			}
		}
		for (int i = 0; i < M; i++) {
			tokens = new StringTokenizer(read.readLine());
			MoveQ.add(new int[] { Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken()) });
		}
		write.write(solv() + "\n");
		write.close();
		read.close();
	}

	private static void init() {
		Map = new int[N][N];
		MoveQ = new LinkedList<>();
		Clouds = new ArrayList<>();
		Visited = new long[N];
		deltas = new int[][] { {}, { 0, -1 }, { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 },
				{ 1, -1 } };
		Clouds.add(new int[] { N - 1, 0 });
		Clouds.add(new int[] { N - 1, 1 });
		Clouds.add(new int[] { N - 2, 0 });
		Clouds.add(new int[] { N - 2, 1 });
	}

	private static int solv() {
		while (!MoveQ.isEmpty()) {
			doMoveClouds(MoveQ.poll());
			doCopyWater();
			doMakeClouds();
		}
		return getTotalWater();
	}

	private static void doMoveClouds(int[] move) { // 구름 이동하기
		int size = Clouds.size();
		for (int i = 0; i < size; i++) {
			int nextR = (N + Clouds.get(i)[0] + (deltas[move[0]][0] * (move[1] % N))) % N; // 이동한 R
			int nextC = (N + Clouds.get(i)[1] + (deltas[move[0]][1] * (move[1] % N))) % N; // 이동한 C
			Map[nextR][nextC]++; // 물 +1
			Visited[nextR] |= 1L << nextC; // 구름 생성지 기록
			Clouds.set(i, new int[] { nextR, nextC }); // 이동된 구름들 저장
		}
	}

	private static void doCopyWater() { // 이동된 구름들 대각선 탐색
		for (int[] cloud : Clouds) {
			for (int index = 2; index <= 8; index += 2) {
				int diagR = cloud[0] + deltas[index][0];
				int diagC = cloud[1] + deltas[index][1];
				if (isIn(diagR, diagC) && Map[diagR][diagC] != 0) {
					Map[cloud[0]][cloud[1]]++;
				}
			}
		}
		Clouds.clear();
	}

	private static void doMakeClouds() { // 2 이상인 지역에서 구름 생성
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (Map[r][c] >= 2 && (Visited[r] & (1L << c)) == 0) { // 이전 구름 생성지 제외
					Map[r][c] -= 2;
					Clouds.add(new int[] { r, c });
				}
			}
		}
		Visited = new long[N];
	}

	private static int getTotalWater() {
		int count = 0;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				count += Map[r][c];
			}
		}
		return count;
	}

	private static boolean isIn(int r, int c) {
		return r >= 0 && c >= 0 && r < N && c < N;
	}

}
