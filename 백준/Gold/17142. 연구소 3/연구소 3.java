import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static int M;
	static int EmptyCount;
	static char[][] Map;
	static ArrayList<Integer> VirusLocs;
	static int[][] Deltas;
	static int Result;

	public static void main(String[] args) throws IOException {
		init();
		solv();
	}

	private static void init() throws IOException {
		N = readInt();
		M = readInt();
		EmptyCount = 0;
		Map = new char[N][N];
		VirusLocs = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int input = readInt();
				if (input == 1) {
					Map[i][j] = 'X';
				} else if (input == 0) {
					Map[i][j] = 'O';
					EmptyCount++;
				} else if (input == 2) {
					Map[i][j] = 'V';
					VirusLocs.add(i * N + j);
				}
			}
		}
		Deltas = new int[][] { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
		Result = Integer.MAX_VALUE;
	}

	private static int readInt() throws IOException {
		int n, c;
		boolean neg = false;
		do {
			n = System.in.read();
			neg = n == 45;
		} while (n <= 45);
		n &= 15;
		while ((c = System.in.read()) >= 45)
			n = (n << 3) + (n << 1) + (c & 15);
		return neg ? -n : n;
	}

	private static void solv() throws IOException {
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		getComb(0, 0, 0);
		write.write((Result == Integer.MAX_VALUE) ? "-1\n" : Result + "\n");
		write.close();
	}

	private static void getComb(int count, int start, int sel) { // 조합으로 케이스 분화
		if (count == M) {
			setCase(sel);
			return;
		}

		for (int i = start; i < VirusLocs.size(); i++) {
			sel |= 1 << i;
			getComb(count + 1, i + 1, sel);
			sel &= ~(1 << i);
		}
	}

	private static void setCase(int sel) {
		Queue<int[]> bfsQ = new LinkedList<>();
		int tempEmptyCount = EmptyCount; // 빈공간 개수
		long[] visits = new long[N];
		int minTime = 0; // 해당 케이스의 전체 선점 시간
		for (int i = 0; i < VirusLocs.size(); i++) { // 조합으로 선택한 바이러스들의 위치 정보
			if ((sel & (1 << i)) != 0) {
				int loc = VirusLocs.get(i);
				bfsQ.add(new int[] { loc, 0 }); // 위치, 시간
				visits[loc / N] |= 1L << (loc % N); // 시작위치
			}
		}

		while (!bfsQ.isEmpty()) {
			int[] info = bfsQ.poll();
			int x = info[0] / N;
			int y = info[0] % N;
			int t = info[1];
			for (int[] dir : Deltas) {
				int nextX = x + dir[0];
				int nextY = y + dir[1];
				if (!isIn(nextX, nextY) || Map[nextX][nextY] == 'X')
					continue; // 갈 수 없는 곳
				if ((visits[nextX] & (1L << nextY)) != 0L)
					continue; // 다른 바이러스가 이미 선점한 곳
				visits[nextX] |= 1L << nextY; // 도착
				if (Map[nextX][nextY] == 'O') { // 길일 때만
					tempEmptyCount--; // 빈공간 하나 삭제
					minTime = Math.max(minTime, t + 1); // 최소 시간 기록
				}
				bfsQ.add(new int[] { nextX * N + nextY, t + 1 });
			}
		}

		if (tempEmptyCount == 0) {
			Result = Math.min(Result, minTime);
		}
	}

	private static boolean isIn(int i, int j) {
		return i >= 0 && i < N && j >= 0 && j < N;
	}

}