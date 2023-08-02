import java.util.*;
import java.io.*;

public class Main {

	static int R, C, T;
	static int[][] Map;
	static Queue<int[]> DustList;
	static int[] MachineRow;
	static int[][] Deltas;
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
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		R = Integer.parseInt(tokens.nextToken());
		C = Integer.parseInt(tokens.nextToken());
		T = Integer.parseInt(tokens.nextToken());
		DustList = new LinkedList<>();
		Map = new int[R][C];
		MachineRow = new int[2];
		Deltas = new int[][] { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
		Result = 0;
		for (int r = 0; r < R; r++) {
			tokens = new StringTokenizer(read.readLine());
			for (int c = 0; c < C; c++) {
				int dust = Integer.parseInt(tokens.nextToken());
				if (dust > 0) {
					Result += dust;
					DustList.add(new int[] { r * C + c, dust });
				} else if (dust == -1) {
					Map[r][c] = -1;
					MachineRow[MachineRow[0] == 0 ? 0 : 1] = r;
				}
			}
		}
		read.close();
	}

	private static void solv() {
		while (T-- > 0) {
			setSpreadDust(); // 먼지 확산
			setBlowWind(0); // 윗바람
			setBlowWind(1); // 아랫바람
			getDustList(); // 먼지 리스트
		}
	}

	private static void setSpreadDust() {
		while (!DustList.isEmpty()) {
			int[] dustInfo = DustList.poll();
			int loc = dustInfo[0];
			int originAmount = dustInfo[1]; // 원래 먼지 양
			int removedAmount = 0; // 흩뿌려진 먼지 양
			for (int[] delta : Deltas) {
				int nextX = loc / C + delta[0];
				int nextY = loc % C + delta[1];
				if (!isIn(nextX, nextY) || Map[nextX][nextY] == -1)
					continue;
				removedAmount += originAmount / 5;
				Map[nextX][nextY] += originAmount / 5; // 뿌려진 위치
			}
			Map[loc / C][loc % C] += originAmount - removedAmount; // 원래 위치
		}
	}

	private static void setBlowWind(int side) { // 0 : 윗바람 / 1 : 아랫바람
		int currentR = MachineRow[side];
		int currentC = 0;
		int dir = side == 0 ? 0 : 2;
		while (true) {
			int nextR = currentR + Deltas[dir][0];
			int nextC = currentC + Deltas[dir][1];
			if (!isIn(nextR, nextC) || (nextR == MachineRow[(side + 1) % 2] && nextC == C - 1)) { // 방향 전환부분
				dir = side == 0 ? (dir + 1) % 4 : (dir + 3) % 4;
				continue;
			}
			if (currentR == MachineRow[side] && currentC == 0) { // 첫 시작부분
				Result -= Map[nextR][nextC]; // 정화된 먼지
			} else {
				if (nextR == MachineRow[side] && nextC == 0) { // 마지막부분
					Map[currentR][currentC] = 0; // 깨끗한 공기
					return;
				}
				Map[currentR][currentC] = Map[nextR][nextC];
			}
			currentR = nextR;
			currentC = nextC;
		}
	}

	private static void getDustList() { // 먼지 리스트 구하기
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (Map[r][c] > 0) {
					DustList.add(new int[] { r * C + c, Map[r][c] });
					Map[r][c] = 0;
				}
			}
		}
	}

	private static boolean isIn(int i, int j) {
		return i >= 0 && i < R && j >= 0 && j < C;
	}

}