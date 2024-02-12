import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static PriorityQueue<Integer> LocList = new PriorityQueue<>();
	static int[][] KnightMove = { { -2, 1 }, { -1, 2 }, { 1, 2 }, { 2, 1 }, { 2, -1 }, { 1, -2 }, { -1, -2 },
			{ -2, -1 } };
	static int[][] RookMove = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	static int[][] BishopMove = { { -1, 1 }, { 1, 1 }, { 1, -1 }, { -1, -1 } };
	static int Result = 0;
	static int[][][] Moves = { {}, KnightMove, RookMove, BishopMove };

	public static void main(String[] args) throws IOException {
		init();
		solv();
	}

	private static void init() throws IOException {
		N = readInt();
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				LocList.add(readInt() * 1000 + r * N + c);
			}
		}
	}

	private static int readInt() throws IOException {
		int n, c;
		do {
			n = System.in.read();
		} while (n <= 45);
		n &= 15;
		while ((c = System.in.read()) >= 45) {
			n = (n << 3) + (n << 1) + (c & 15);
		}
		return n;
	}

	private static void solv() throws IOException {
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		getResult();
		write.write(Result + "\n");
		write.close();
	}

	private static void getResult() {
		int fromInfo = ((LocList.poll() % 1000) * 100) + 14; // /10 : 위치, %10 : piece 가능 케이스
		while (!LocList.isEmpty()) {
			int toLoc = LocList.poll() % 1000; // 말이 이동해야할 목적지
			fromInfo = setMove(fromInfo, toLoc);
		}
	}

	private static int setMove(int fromInfo, int toLoc) {
		int toLocR = toLoc / N;
		int toLocC = toLoc % N;
		int[][] dijkMap = new int[N][N];
		for (int n = 0; n < N; n++) {
			Arrays.fill(dijkMap[n], 105);
		}
		dijkMap[(fromInfo / 100) / N][(fromInfo / 100) % N] = -1; // 시작지점

		PriorityQueue<Integer> movePQ = new PriorityQueue<>();
		for (int startPiece = 1; startPiece <= 3; startPiece++) {
			if (((fromInfo % 100) & (1 << startPiece)) != 0) {
				movePQ.add((fromInfo / 100) * 10 + startPiece);
			}
		} // 시작 지점에 도착해있는 말 종류마다 케이스 추가
		int minCount = 105;
		int arrivedPiece = 0;

		while (!movePQ.isEmpty()) { // 카운트가 작은 순서대로
			int currentInfo = movePQ.poll();
			int currentCount = currentInfo / 10000; // 이동 카운트
			int currentLoc = (currentInfo % 10000) / 10; // 이동 위치
			int currentPiece = currentInfo % 10; // 이동 말
			if (currentCount >= minCount) { // 최소 카운트보다 같거나 크면 탐색 중지
				break;
			}
			for (int nextPiece = 1; nextPiece <= 3; nextPiece++) { // 말 하나씩
				for (int[] dir : Moves[nextPiece]) { // 해당 말의 이동 방향
					int maxLen = nextPiece == 1 ? 1 : N;// 나이트 제외 방향마다 맵 끝까지 이동 가능
					for (int len = 1; len <= maxLen; len++) {
						int nextR = currentLoc / N + dir[0] * len;
						int nextC = currentLoc % N + dir[1] * len;
						if (!isIn(nextR, nextC)) { // 범위 밖
							break;
						}
						// 다음 위치까지 이동 카운트 = 현재 카운트 + 이동(1) + 말 바꿈(1 : 0);
						int nextCount = currentCount + 1 + (currentPiece != nextPiece ? 1 : 0);

						// 다음 위치 카운트보다 크면 중지
						if (dijkMap[nextR][nextC] < nextCount) {
							continue;
						}

						// 도착
						if (nextR == toLocR && nextC == toLocC) {
							// 도착 카운트가 새로운 최소값
							if (dijkMap[toLocR][toLocC] > nextCount) {
								dijkMap[toLocR][toLocC] = nextCount; // 도착 카운트 갱신
								minCount = nextCount;
								arrivedPiece = 0;
							}
							arrivedPiece |= (1 << nextPiece); // 도착 말 추가
							continue;
						}

						// 도착이 아닌데 최소 카운트보다 같거나 크면 탐색 중지
						if (nextCount >= minCount) {
							continue;
						}
						// 도착 위치가 아닌 다른 위치 이동 최소값 갱신 및 추가
						if (dijkMap[nextR][nextC] >= nextCount) {
							dijkMap[nextR][nextC] = nextCount;
							movePQ.add((nextCount * 10000) + (nextR * N + nextC) * 10 + nextPiece); //
						}
					}
				}
			}
		}
		Result += dijkMap[toLocR][toLocC]; // 도착 카운트 추가
		return toLoc * 100 + arrivedPiece;

	}

	private static boolean isIn(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}

}