import java.util.*;
import java.io.*;

public class Main {
	static int[][][] Plates;
	static int[][] Deltas;
	static int[][] MazeFloors;
	static int Result;

	public static void main(String[] args) throws IOException {
		init();
		solv();
	}

	private static void init() throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		Plates = new int[5][4][5];
		MazeFloors = new int[5][5];
		for (int floor = 0; floor < 5; floor++) {
			for (int i = 0; i < 5; i++) {
				StringTokenizer tokens = new StringTokenizer(read.readLine());
				for (int j = 0; j < 5; j++) {
					Plates[floor][0][i] |= (tokens.nextToken().equals("1") ? 1 : 0) << j;
				}
			}
		}
		Deltas = new int[][] { { -1, 0, 0 }, { 0, 1, 0 }, { 1, 0, 0 }, { 0, -1, 0 }, { 0, 0, 1 }, { 0, 0, -1 } };
		Result = Integer.MAX_VALUE;
		read.close();
	}

	private static void solv() throws IOException {
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		setRotatePlates();
		getStartableCase();
		write.write((Result == Integer.MAX_VALUE ? "-1\n" : Result + "\n"));
		write.close();
	}

	private static void setRotatePlates() { // 각 입력 판마다 회전 케이스 미리 구해두기
		for (int floor = 0; floor < 5; floor++) {
			for (int rotateCount = 1; rotateCount <= 3; rotateCount++) {
				Plates[floor][rotateCount] = getRotatePlate(floor, rotateCount);
			}
		}
	}

	private static int[] getRotatePlate(int floor, int rotateCount) { // floor층의 판을 rotateCount만큼 회전시킨 판 생성
		int[] plate = Plates[floor][0];
		int[] nextPlate = new int[5];
		boolean convertIJ = rotateCount % 2 == 1;
		int startI = rotateCount == 3 ? 0 : 4;
		int addI = startI == 0 ? 1 : -1;
		for (int i = 0; i < 5; i++) {
			int startJ = rotateCount == 1 ? 0 : 4;
			int addJ = startJ == 0 ? 1 : -1;
			for (int j = 0; j < 5; j++) {
				boolean a = (plate[i] & (1 << j)) != 0;
				nextPlate[convertIJ ? startJ : startI] |= (a ? 1 : 0) << (convertIJ ? startI : startJ);
				startJ += addJ;
			}
			startI += addI;
		}
		return nextPlate;
	}

	private static void getStartableCase() { // 층 순서 정하기 (12345 ~ 54321)
		getPerm(0, 0, 0);
	}

	private static void getPerm(int count, int vis, int sel) {
		if (count == 5) {
			isStartable(sel); // 가능한 경우일까?
			if (Result == 12)
				return;
			return;
		}

		for (int i = 1; i <= 5; i++) {
			if ((vis & (1 << i)) != 0)
				continue;
			vis |= 1 << i;
			getPerm(count + 1, vis, sel * 10 + i);
			if (Result == 12)
				return;
			vis &= ~(1 << i);
		}
	}

	private static void isStartable(int sel) { // 1층과 5층만의 회전 케이스를 봤을 때 입구와 출구가 존재하는가 확인
		for (int startR = 0; startR < 4; startR++) {
			for (int endR = 0; endR < 4; endR++) {
				if ((Plates[sel / 10000 - 1][startR][0] & 1) != 0 && (Plates[sel % 10 - 1][endR][4] & (1 << 4)) != 0) {
					setMaze(sel, (startR + 1) * 10000 + endR + 1); // 가능한 케이스라면 중간 층도 회전 시킨 케이스 생성
				}
				if (Result == 12)
					return;
			}
		}
	}

	private static void setMaze(int order, int rotateSandE) {
		for (int secondR = 1; secondR <= 4; secondR++) {
			for (int thirdR = 1; thirdR <= 4; thirdR++) {
				for (int fourthR = 1; fourthR <= 4; fourthR++) {
					getEscapeCount(order, rotateSandE + secondR * 1000 + thirdR * 100 + fourthR * 10); // 미로 완성
					if (Result == 12)
						return;
				}
			}
		}
	}

	private static void getEscapeCount(int inputOrder, int inputRotate) { // inputOrder 순서의 층이 각 inputRoatet만큼 회전 경우
		int order = inputOrder / 10000;
		int rotate = inputRotate / 10000;
		MazeFloors[0] = Plates[order - 1][rotate - 1];

		inputOrder %= 10000;
		inputRotate %= 10000;
		order = inputOrder / 1000;
		rotate = inputRotate / 1000;
		MazeFloors[1] = Plates[order - 1][rotate - 1];

		inputOrder %= 1000;
		inputRotate %= 1000;
		order = inputOrder / 100;
		rotate = inputRotate / 100;
		MazeFloors[2] = Plates[order - 1][rotate - 1];

		inputOrder %= 100;
		inputRotate %= 100;
		order = inputOrder / 10;
		rotate = inputRotate / 10;
		MazeFloors[3] = Plates[order - 1][rotate - 1];

		order = inputOrder % 10;
		rotate = inputRotate % 10;
		MazeFloors[4] = Plates[order - 1][rotate - 1];

		Queue<Integer> BFSQ = new LinkedList<>(); // 3차원 BFS 돌려보기
		int[][] vis = new int[5][5];
		vis[0][0] |= 1 << 0;
		BFSQ.add(0);
		while (!BFSQ.isEmpty()) {
			int info = BFSQ.poll();
			int count = info / 1000;
			info %= 1000;
			int i = (info / 10) / 5;
			int j = (info / 10) % 5;
			int floor = info % 10;
			for (int[] dir : Deltas) {
				int nextI = i + dir[0];
				int nextJ = j + dir[1];
				int nextFloor = floor + dir[2];
				if (!isIn(nextI, nextJ, nextFloor) || (MazeFloors[nextFloor][nextI] & (1 << nextJ)) == 0
						|| (vis[nextFloor][nextI] & (1 << nextJ)) != 0) // 범위 밖이거나 막혀있거나 방문한 곳
					continue;
				if (nextI == 4 && nextJ == 4 && nextFloor == 4) {
					Result = Math.min(Result, count + 1);
					return;
				}
				vis[nextFloor][nextI] |= 1 << nextJ;
				BFSQ.add((count + 1) * 1000 + (nextI * 5 + nextJ) * 10 + nextFloor);
			}
		}

	}

	private static boolean isIn(int i, int j, int floor) {
		return i >= 0 && j >= 0 && floor >= 0 && i < 5 && j < 5 && floor < 5;
	}

}