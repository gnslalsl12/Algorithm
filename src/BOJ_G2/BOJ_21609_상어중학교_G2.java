package BOJ_G2;

import java.util.*;
import java.io.*;

public class BOJ_21609_상어중학교_G2 {
	static int N, M;
	static int[][] Map;
	static int[][] deltas;
	static int[] Visited;
	static PriorityQueue<Group> AllGroups;
	static long Result;

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
		solv();
		write.write(Result + "\n");
		write.close();
		read.close();
	}

	// 초기화
	private static void init() {
		Map = new int[N][N];
		deltas = new int[][] { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
		Visited = new int[N];
		AllGroups = new PriorityQueue<>();
		Result = 0;
	}

	// 풀이
	private static void solv() {
		while (true) {
			doSearchBiggestGroup(); // 그룹화 및 조건에 맞는 그룹 찾기
			if (AllGroups.isEmpty()) // 더이상 그룹이 없을 때 종료
				return;
			doRemoveGroup();
			addGravity();
			doRotateSquare();
			addGravity();
		}
	}

	// 그룹화 & 조건에 맞게 그룹 정렬
	private static void doSearchBiggestGroup() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (Map[i][j] > 0 && (Visited[i] & (1 << j)) == 0) { // 일반 블록 하나 발견
					doGatherGroup(i, j);
				}
			}
		}
	}

	// 일반 블록 하나를 중심으로 BFS 탐색
	private static void doGatherGroup(int i, int j) {
		Queue<int[]> BFSQ = new LinkedList<>();
		BFSQ.add(new int[] { i, j });
		int color = Map[i][j];
		Group G = new Group(i, j);

		// 일반 블록은 globally 중복 불가, 무지개 블록은 globally 중복 가능 & locally 중복 불가
		Visited[i] |= 1 << j; // 일반 블록 방문(global)
		int[] visitedRainbow = new int[N]; // 무지개 블록 방문(local)

		while (!BFSQ.isEmpty()) {
			int[] loc = BFSQ.poll();
			for (int dir = 0; dir < 4; dir++) {
				int nextI = loc[0] + deltas[dir][0];
				int nextJ = loc[1] + deltas[dir][1];

				// 범위 밖이거나 / 블랙 블록이거나 / 현재 그룹에서 이미 방문한 무지개 블록이거나 / 이미 방문한 일반 블록이라면
				if (!isIn(nextI, nextJ) || Map[nextI][nextJ] <= -1 || (visitedRainbow[nextI] & (1 << nextJ)) != 0
						|| (Visited[nextI] & (1 << nextJ)) != 0)
					continue;

				// 중심 블록과 다른 색 블록이라면
				if (Map[nextI][nextJ] > 0 && Map[nextI][nextJ] != color)
					continue;

				// 방문처리 구분
				if (Map[nextI][nextJ] == 0)
					visitedRainbow[nextI] |= 1 << nextJ;
				else
					Visited[nextI] |= 1 << nextJ;

				// BFS 추가 및 그룹화
				int[] nextLoc = new int[] { nextI, nextJ };
				BFSQ.add(nextLoc);
				G.blocks.add(nextLoc);

				// 그룹의 무지개 블록 수 세기
				if (Map[nextI][nextJ] == 0)
					G.rainbowCount++;

				// 그룹 총 블록 수
				G.size++;
			}
		}

		// 블록 수가 2개 이상일 때만 그룹화
		if (G.size >= 2) {
			AllGroups.add(G);
		}
	}

	// 조건에 맞는 그룹 하나 삭제
	private static void doRemoveGroup() {
		Group A = AllGroups.poll();

		// 점수 획득
		Result += Math.pow(A.size, 2);

		while (!A.blocks.isEmpty()) {
			int[] loc = A.blocks.poll();
			Map[loc[0]][loc[1]] = -2;
		}
		AllGroups.clear();

		// 방문 초기화
		Visited = new int[N];
	}

	// 중력 추가
	private static void addGravity() {
		for (int j = 0; j < N; j++) {
			for (int i = N - 1; i >= 0; i--) {

				// 빈공간(-2)발견시
				if (Map[i][j] == -2) {

					// 해당 위치에서부터 위로 탐색
					for (int nextI = i - 1; nextI >= 0; nextI--) {

						// 블랙블록이면 생략
						if (Map[nextI][j] == -1) {
							i = nextI;
							break;
						}

						// 일반 / 무지개 블록 발견시 내리기
						else if (Map[nextI][j] >= 0) {
							Map[i][j] = Map[nextI][j];
							Map[nextI][j] = -2;
							break;
						}
					}
				}
			}
		}
	}

	// 회전시키기
	private static void doRotateSquare() {
		int[][] tempMap = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				tempMap[N - 1 - j][i] = Map[i][j];
			}
		}
		for (int i = 0; i < N; i++) {
			Map[i] = tempMap[i];
		}
	}

	private static class Group implements Comparable<Group> {
		int baseLoc;
		int size;
		int rainbowCount;
		Queue<int[]> blocks;

		public Group(int baseI, int baseJ) {
			this.baseLoc = baseI * 100 + baseJ;
			size = 1;
			rainbowCount = 0;
			this.blocks = new LinkedList<>();
			this.blocks.add(new int[] { baseI, baseJ });
		}

		// 조건에 맞게 정렬
		@Override
		public int compareTo(Group arg0) {
			if (this.size == arg0.size)
				if (this.rainbowCount == arg0.rainbowCount)
					return Integer.compare(arg0.baseLoc, this.baseLoc);
				else
					return Integer.compare(arg0.rainbowCount, this.rainbowCount);
			return Integer.compare(arg0.size, this.size);
		}

	}

	private static boolean isIn(int i, int j) {
		return i >= 0 && j >= 0 && i < N && j < N;
	}

}
