import java.io.*;
import java.util.*;

/*
 * 섬 번호 기록하고
 * 각 섬들이 원형으로 둘러싸는지
 * 이후에 맵 전체 돌면서
 * 가로로 쭉 그어서 섬 색칠하기 (다른 맵 만들어서 거기다가 +1씩 해주기)
*/

public class Main {

	static int N, M;
	static int[][] Map;
	static int[][] Deltas;
	static int IslandCount;
	static ArrayList<Integer> IslandStates;
	static int[] Result;
	static boolean[][] Vis;

	public static void main(String[] args) throws IOException {
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		init();
		solv();
		for (int i = 0; i < Result.length; i++) {
			if (i == Result.length - 1)
				write.write(Result[i] + "\n");
			else
				write.write(Result[i] + " ");
		}
		write.close();
	}

	private static void init() throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		Map = new int[N][M];
		for (int n = 0; n < N; n++) {
			String inputLine = read.readLine();
			for (int m = 0; m < M; m++) {
				Map[n][m] = inputLine.charAt(m) == 'x' ? -1 : 0;
			}
		}
		Deltas = new int[][] { { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 }, { 0, -1 }, { -1, -1 } };
		IslandCount = 0;
		IslandStates = new ArrayList<>(); // 처음엔 섬의 첫 위치값, 그 다음엔
		Vis = new boolean[N][M];
		read.close();
	}

	private static void solv() {
		getOuterIslands();
		if (IslandCount == 0) {
			Result = new int[] { -1 };
			return;
		}
		setEachOuterIsland();
		setIslandsHeight();
	}

	private static void getOuterIslands() {
		for (int n = 0; n < N; n++) {
			for (int m = 0; m < M; m++) {
				if (Map[n][m] == -1) { // 섬을 만남 => 섬 번호 기입
					isIsland(n, m);
				} else if (Map[n][m] == 0 && !Vis[n][m]) { // 방문 안 한 바다를 만남 => 해당 바다가 섬으로 둘러싸여있는가
					isSurroundedOcean(n, m);
				}
			}
		}
	}

	private static void isIsland(int n, int m) { // 섬마다 번호 기입
		Queue<Integer> BFSQ = new LinkedList<>();
		IslandCount++;
		Map[n][m] = IslandCount;
		BFSQ.add(n * M + m);
		while (!BFSQ.isEmpty()) {
			int loc = BFSQ.poll();
			for (int[] dir : Deltas) {
				int nextI = loc / M + dir[0];
				int nextJ = loc % M + dir[1];
				if (!isIn(nextI, nextJ) || Map[nextI][nextJ] == 0) // 범위 밖이거나 바다는 X
					continue;
				else if (Map[nextI][nextJ] == -1) {
					Map[nextI][nextJ] = IslandCount;
					BFSQ.add(nextI * M + nextJ);
				}
			}
		}
		IslandStates.add(n * M + m); // 첫 위치의 8방 확인 시 바다를 만나거나 범위 밖이면 외곽 섬,(둘러싸인 섬이 없음) / 다른 섬 번호면 해당 섬으로 둘러싸임
	}

	private static void isSurroundedOcean(int n, int m) {
		Queue<Integer> BFSQ = new LinkedList<>();
		Queue<Integer> OceanQ = new LinkedList<>(); // 모든 바다 위치 저장
		Vis[n][m] = true;
		BFSQ.add(n * M + m);
		OceanQ.add(n * M + m);
		int firstMetIsland = -1;
		while (!BFSQ.isEmpty()) {
			int loc = BFSQ.poll();
			for (int indexD = 0; indexD < 8; indexD += 2) {
				int nextI = loc / M + Deltas[indexD][0];
				int nextJ = loc % M + Deltas[indexD][1];
				if (!isIn(nextI, nextJ)) {
					firstMetIsland = -2; // 범위 밖이라면 맵을 벗어나는 바다 (섬으로 둘러싸이지 않음)
					continue;
				} else if (Map[nextI][nextJ] > 0 || Map[nextI][nextJ] == -1) { // 섬을 만남
					if (firstMetIsland == -1) // 처음 만난 섬 (둘러쌀 수도 있는 섬)
						firstMetIsland = Map[nextI][nextJ]; // 번호가 정해지지 않은 섬을 만나더라도 해당 섬으로 둘러싸일 일은 없음
					continue;
				} else if (Vis[nextI][nextJ])
					continue;
				Vis[nextI][nextJ] = true;
				BFSQ.add(nextI * M + nextJ);
				if (firstMetIsland != -2)
					OceanQ.add(nextI * M + nextJ);
			}
		}
		if (firstMetIsland != -2) {
			while (!OceanQ.isEmpty()) {
				int loc = OceanQ.poll();
				Map[loc / M][loc % M] = firstMetIsland;
			}
		}
	}

	private static void setEachOuterIsland() { // 섬별로 둘러싼 섬 확인하기
		for (int i = 1; i <= IslandCount; i++) {
			int firstLoc = IslandStates.get(i - 1); // i섬의 첫번쨰 위치
			for (int[] dir : Deltas) {
				int nextI = firstLoc / M + dir[0];
				int nextJ = firstLoc % M + dir[1];
				if (!isIn(nextI, nextJ) || Map[nextI][nextJ] == 0) { // 범위 밖이거나 바다가 있음
					IslandStates.set(i - 1, i); // i섬을 둘러싼 건 i섬임 (최외곽섬)
					break;
				} else if (Map[nextI][nextJ] != i) { // 범위 안이면서 바다가 아니고, 다른 섬을 발견함 => 해당 섬으로 둘러싸인 것
					IslandStates.set(i - 1, Map[nextI][nextJ]);
					break;
				}
			}
		}
	}

	private static void setIslandsHeight() { // 섬별로 둘러싼 섬에게 높이 +1 해주기
		int[] eachHeights = new int[IslandCount + 1];
		int maxHeight = 0;
		for (int i = 1; i <= IslandCount; i++) {
			int outerOfI = IslandStates.get(i - 1); // i섬을 둘러싼 섬
			if (outerOfI == i)
				continue;
			else {
				int inner = i;
				while (inner != outerOfI) {
					// i를 둘러싼 섬의 높이 =max(해당 섬의 높이,현재섬의 높이 +1)
					eachHeights[outerOfI] = Math.max(eachHeights[outerOfI], eachHeights[inner] + 1);
					maxHeight = Math.max(maxHeight, eachHeights[outerOfI]);
					inner = outerOfI;
					outerOfI = IslandStates.get(outerOfI - 1);
				}
			}
		}
		setResult(eachHeights, maxHeight);
	}

	private static void setResult(int[] eachHeights, int maxHeight) { // 각 높이별 개수 세기
		Result = new int[maxHeight + 1];
		for (int isl = 1; isl <= IslandCount; isl++) {
			Result[eachHeights[isl]]++;
		}
	}

	private static boolean isIn(int i, int j) {
		return i >= 0 && j >= 0 && i < N && j < M;
	}

}