import java.util.*;
import java.io.*;

public class Main {
	static int[] Map; // 지도 상 물고기의 번호*10 + 방향
	static int[] FishLocs; // 물고기 번호순 위치
	static int[][] Deltas;
	static int Result;

	public static void main(String[] args) throws IOException {
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		init();
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				int a = readInt();
				int b = readInt() - 1;
				Map[i * 4 + j] = a * 10 + b; // 번호*10 + 방향
				FishLocs[a] = i * 4 + j;
			}
		}
		solv();
		write.write(Result + "\n");
		write.close();
	}

	private static int readInt() throws IOException {
		int n, c;
		boolean neg = false;
		do {
			n = System.in.read();
			if (n == 45)
				neg = true;
		} while (n <= 45);
		n &= 15;
		while ((c = System.in.read()) >= 45) {
			n = (n << 3) + (n << 1) + (c & 15);
		}
		return neg ? -n : n;
	}

	private static void init() {
		Map = new int[16];
		FishLocs = new int[17];
		Deltas = new int[][] { { -1, 0 }, { -1, -1 }, { 0, -1 }, { 1, -1 }, { 1, 0 }, { 1, 1 }, { 0, 1 }, { -1, 1 } };
		Result = 0;
	}

	private static void solv() {
		int firstFish = Map[0];
		int initCount = firstFish / 10;
		FishLocs[firstFish / 10] = -1;
		Map[0] = (Map[0] % 10) * -1 - 100; // 초기 상어 위치 세팅
		DFS(new int[] { 0, initCount }, Map, FishLocs); // sharkInfo : {위치, 합산}
	}

	private static void DFS(int[] sharkInfo, int[] inputM, int[] inputFL) {
		Result = Math.max(sharkInfo[1], Result);
		moveFishes(inputM, inputFL);
		moveShark(sharkInfo, inputM, inputFL);
	}

	private static void moveFishes(int[] inputM, int[] inputFL) {
		for (int f = 1; f <= 16; f++) {
			if (inputFL[f] >= 0) { // 아직 살아있는 물고기라면
				int i = inputFL[f] / 4;
				int j = inputFL[f] % 4;
				int dir = inputM[inputFL[f]] % 10;
				for (int add = 0; add <= 7; add++) {
					int nextDir = (dir + add) % 8; // 방향 돌리기
					int nextI = i + Deltas[nextDir][0];
					int nextJ = j + Deltas[nextDir][1];
					if (isIn(nextI, nextJ) && inputM[nextI * 4 + nextJ] >= 0) { // 이동할 수 있음 (상어는 음수)
						int tempFish = inputM[nextI * 4 + nextJ]; // 이동할 곳의 물고기 정보
						inputM[nextI * 4 + nextJ] = f * 10 + nextDir; // 이동한 위치에 현재 물고기 세팅(새로운 방향)
						inputM[inputFL[f]] = tempFish; // 원래 위치에 맞바꿀 물고기 세팅(번호*10 + 방향)
						inputFL[f] = nextI * 4 + nextJ; // 현재 물고기 정보에 위치 업데이트
						inputFL[tempFish / 10] = i * 4 + j; // 바뀐 물고기 정보에 위치 업데이트
						break;
					}
				}
			}
		}
	}

	private static void moveShark(int[] sharkInfo, int[] inputM, int[] inputFL) {
		int sI = sharkInfo[0] / 4;
		int sJ = sharkInfo[0] % 4;
		int sDir = (inputM[sharkInfo[0]] + 100) * -1;
		for (int dist = 1; dist <= 3; dist++) {
			int nextSI = sI + Deltas[sDir][0] * dist;
			int nextSJ = sJ + Deltas[sDir][1] * dist;
			if (isIn(nextSI, nextSJ) && inputM[nextSI * 4 + nextSJ] > 0) { // 이동 가능한 방향일 때
				int[] newFL = inputFL.clone();
				int[] newM = inputM.clone();
				int[] newSharkInfo = sharkInfo.clone();
				newM[newSharkInfo[0]] = 0; // 원래 상어 위치 0처리(빈칸)
				int targetInfo = newM[nextSI * 4 + nextSJ]; // 잡아먹힐 물고기 번호*10 + 방향
				int targetFishNum = targetInfo / 10;
				int targetFishDir = targetInfo % 10;
				newM[nextSI * 4 + nextSJ] = targetFishDir * -1 - 100;
				newFL[targetFishNum] = -1; // 물고기 죽음 처리
				newSharkInfo = new int[] { nextSI * 4 + nextSJ, sharkInfo[1] + targetFishNum };
				DFS(newSharkInfo, newM, newFL);
			}
		}
	}

	private static boolean isIn(int i, int j) {
		return i >= 0 && j >= 0 && i < 4 && j < 4;
	}

}