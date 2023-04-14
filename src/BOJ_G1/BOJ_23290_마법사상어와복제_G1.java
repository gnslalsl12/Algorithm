package BOJ_G1;

import java.util.*;
import java.io.*;

public class BOJ_23290_마법사상어와복제_G1 {
	static int M;
	static int S;
	static int[] Shark;
	static int[][] Fdir = { { 0, -1 }, { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 } };
	static ArrayList<int[][]> Sdir = new ArrayList<>();
	static ArrayList<int[]> Fishes = new ArrayList<>();
	static Queue<int[]> DuplicatedFishes = new LinkedList<>();
	static int[][] FishMap = new int[5][5]; // -2, -1 : 남아있는 냄새 / 1이상 : 물고기 수
	static int[][] ScentMap = new int[5][5];
	static boolean[][] SharkVisited = new boolean[5][5];

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		M = Integer.parseInt(tokens.nextToken());
		S = Integer.parseInt(tokens.nextToken());
		for (int i = 0; i < M; i++) {
			tokens = new StringTokenizer(read.readLine());
			int r = Integer.parseInt(tokens.nextToken());
			int c = Integer.parseInt(tokens.nextToken());
			int d = Integer.parseInt(tokens.nextToken()) - 1;
			FishMap[r][c]++;
			Fishes.add(new int[] { r, c, d });
		}
		tokens = new StringTokenizer(read.readLine());
		Shark = new int[] { Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken()) };
		write.write(solv() + "");
		write.write("\n");
		write.close();
		read.close();
	}

	private static int solv() {
		getSdir();
		for (int time = 0; time < S; time++) {
			System.out.println("-------------------------------------");
			printFishMap();
			System.out.println("////////////////////////////물고기 복제");
			getCopy();
			System.out.println("////////////////////////////물고기이동");
			doMoveFish();
			System.out.println("////////////////////////////상어 이동");
			doMoveShark();
			System.out.println("///////////////////////////냄새 삭제");
			doRemoveScent();
			System.out.println("////////////////////////////물고기 붙여넣기");
			doPasteFishes();
		}
		return getFishCount();
	}

	private static void getSdir() { // 상어의 이동 가능한 방향 찾아서 저장 (사전순 작은 순부터 저장)
		int[][] tempdir = { { -1, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 } };
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				for (int k = 0; k < 4; k++) {
					Sdir.add(new int[][] { tempdir[i], tempdir[j], tempdir[k] });
				}
			}
		}
	}

	private static void printFishMap() {
		System.out.println("===========FishMap");
		for (int i = 1; i <= 4; i++) {
			for (int j = 1; j <= 4; j++) {
				System.out.print(FishMap[i][j] + " ");
			}
			System.out.println();
		}
	}

	private static void printScentMap() {
		System.out.println("============ScentMap");
		for (int i = 1; i <= 4; i++) {
			for (int j = 1; j <= 4; j++) {
				System.out.print(ScentMap[i][j] + " ");
			}
			System.out.println();
		}
	}

	private static void getCopy() { // 물고기 리스트 = 상어한테 먹힌 물고기도 포함
		int size = Fishes.size();
		System.out.println(size);
		for (int i = size - 1; i >= 0; i--) {
			if (SharkVisited[Fishes.get(i)[0]][Fishes.get(i)[1]]) { // 직전 상어한테 잡아먹힌 애라면
				System.out.println(Arrays.toString(Fishes.remove(i)) + "는 제거");
				continue;
			}
			System.out.println(Arrays.toString(Fishes.get(i)));
			DuplicatedFishes.add(Fishes.get(i).clone());
		}
		SharkVisited = new boolean[5][5];
	}

	private static void doMoveFish() {
		int size = Fishes.size();
		for (int i = size - 1; i >= 0; i--) {
			int[] fish = Fishes.get(i);
			System.out.println("물고기 : " + Arrays.toString(fish));
			int r = fish[0];
			int c = fish[1];
			int d = fish[2];
			if (FishMap[r][c] == 0) { // 방금 상어가 지나가서 아무도 없으면
				Fishes.remove(i);
				continue;
			}
			FishMap[r][c]--; // 현재 위치에서 벗어나기
			int tempD = d;
			int nextR = r;
			int nextC = c;
			boolean found = false;
			for (int addDir = 0; addDir <= 7; addDir++) {
				tempD = (d + 8 - addDir) % 8;
				nextR = r + Fdir[tempD][0];
				nextC = c + Fdir[tempD][1];
				if (!isIn(nextR, nextC) || ScentMap[nextR][nextC] < 0 || (nextR == Shark[0] && nextC == Shark[1])) {
					// 범위 밖이거나 / 냄새가 있거나 / 상어가 있는 곳이라 이동 불가능
					System.out.println(tempD + "방향 이동 불가");
					continue;
				}
				// 이동 가능한 방향을 찾음
				found = true;
				break;
			}
			if (found) {
				fish[0] = nextR;
				fish[1] = nextC;
				fish[2] = tempD;
			}
			FishMap[fish[0]][fish[1]]++;
			// 이동 가능항 방향이 없으면 그대로 있기
		}
		System.out.println("이동이 끝난 물고기 리스트");
		for (int[] fish : Fishes) {
			System.out.println(Arrays.toString(fish));
		}
		printFishMap();
		printScentMap();
	}

	private static void doMoveShark() {
		System.out.println("상어 위치 : " + Arrays.toString(Shark));
		int maxFishes = -1;
		int[][] dirStack = new int[3][2];
		for (int i = Sdir.size() - 1; i >= 0; i--) { // 사전순 큰 것부터 탐색해서 비교 저장해나가기
			boolean[][] visited = new boolean[5][5]; // 잡아먹ㅇ느 물고기 임시 저장
			int tempFishes = 0;
			int Sr = Shark[0];
			int Sc = Shark[1];
			int[] dir1 = Sdir.get(i)[0];
			int[] dir2 = Sdir.get(i)[1];
			int[] dir3 = Sdir.get(i)[2];
			Sr += dir1[0];
			Sc += dir1[1];
			if (!isIn(Sr, Sc)) {
				continue;
			}
			if (FishMap[Sr][Sc] > 0) { // 물고기가 있을 때만
				tempFishes += FishMap[Sr][Sc];
				visited[Sr][Sc] = true;
			}
			Sr += dir2[0];
			Sc += dir2[1];
			if (!isIn(Sr, Sc)) { // 잡아먹은 물고기 되돌리기
				continue;
			}
			if (FishMap[Sr][Sc] > 0 && !visited[Sr][Sc]) { // 물고기가 있을 때만
				tempFishes += FishMap[Sr][Sc];
				visited[Sr][Sc] = true;
			}
			Sr += dir3[0];
			Sc += dir3[1];
			if (!isIn(Sr, Sc)) {
				continue;
			}
			if (FishMap[Sr][Sc] > 0 && !visited[Sr][Sc]) { // 물고기가 있을 때만
				tempFishes += FishMap[Sr][Sc];
			}
			// 다 먹음
			if (maxFishes <= tempFishes) { // 가장 많이 먹고 사전순 높은 이동 방향을 저장 && 상어 위치 기억
				dirStack = Sdir.get(i);
				maxFishes = tempFishes;
			}
		}
		System.out.println("이동 스택 : ");
		for (int[] dir : dirStack) { // 상어 이동시키면서 냄새로 변환하기
			System.out.println(Arrays.toString(dir));
			Shark[0] += dir[0];
			Shark[1] += dir[1];
			if (FishMap[Shark[0]][Shark[1]] > 0) { // 물고기가 있는 경로면
				SharkVisited[Shark[0]][Shark[1]] = true;
				FishMap[Shark[0]][Shark[1]] = 0; // 다 없애기
				ScentMap[Shark[0]][Shark[1]] = -3; // 냄새 남기기
			}
		}
		System.out.println("상어 이동");
		System.out.println("상어 위치 : " + Arrays.toString(Shark));
		printFishMap();
		printScentMap();
	}

	private static void doRemoveScent() {
		for (int r = 1; r <= 4; r++) {
			for (int c = 1; c <= 4; c++) {
				if (ScentMap[r][c] < 0)
					ScentMap[r][c]++;
			}
		}

		printScentMap();
	}

	private static void doPasteFishes() {
		while (!DuplicatedFishes.isEmpty()) {
			int[] tempFish = DuplicatedFishes.poll();
			FishMap[tempFish[0]][tempFish[1]]++;
			Fishes.add(tempFish);
		}
		printFishMap();
	}

	// 움직ㅇ니 후 상어가 잡아먹은 물고기가 물고기 리스트에 남아있음
	private static int getFishCount() {
		int count = 0;
		for (int i = 1; i <= 4; i++) {
			for (int j = 1; j <= 4; j++) {
				count += FishMap[i][j];
			}
		}
		return count;
	}

	private static boolean isIn(int r, int c) {
		return r <= 4 && r >= 1 && c <= 4 && c >= 1;
	}

}
