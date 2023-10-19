import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static int M;
	static int[] Robot;
	static long[] MapWall;
	static long[] MapDirt;
	static int[][] Deltas;
	static int CleanedCount;

	public static void main(String args[]) throws IOException {
		init();
		solv();
	}

	private static void init() throws IOException {
		N = readInt();
		M = readInt();
		Robot = new int[3];
		MapWall = new long[N];
		MapDirt = new long[N];
		for (int i = 0; i < 3; i++) {
			Robot[i] = readInt();
		}
		for (int n = 0; n < N; n++) {
			for (int m = 0; m < M; m++) {
				if (readInt() == 1)
					MapWall[n] |= 1L << m;
			}
		}
		Deltas = new int[][] { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
		CleanedCount = 0;
	}

	private static int readInt() throws IOException {
		int n, c;
		boolean neg = false;
		do {
			n = System.in.read();
			neg = n == 45;
		} while (n <= 45);
		n &= 15;
		while ((c = System.in.read()) > 45) {
			n = (n << 3) + (n << 1) + (c & 15);
		}
		return neg ? -n : n;
	}

	private static void solv() throws IOException {
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		setRobotMove();
		write.write(CleanedCount + "\n");
		write.close();
	}

	private static void setRobotMove() {
		while (true) {
			doCleanCurrent(); // 현재 칸이 아직 청소되지 않은 경우, 현재 칸을 청소한다.
			if (isAllClear()) { // 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 없는 경우
				if (isAbleGoBack()) { // 바라보는 방향을 유지한 채로 한 칸 후진할 수 있다면 한 칸 후진하고 1번으로 돌아간다.
					continue;
				} else { // 바라보는 방향의 뒤쪽 칸이 벽이라 후진할 수 없다면 작동을 멈춘다.
					return;
				}
			} else { // 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 있는 경우
				Robot[2] = (Robot[2] + 3) % 4; // 반시계 방향으로 90도 회전한다.
				doProceed(); // 바라보는 방향을 기준으로 앞쪽 칸이 청소되지 않은 빈 칸인 경우 한 칸 전진한다.
			} // 1번으로 돌아간다다
		}
	}

	private static void doCleanCurrent() {
		if ((MapDirt[Robot[0]] & (1L << Robot[1])) == 0) { // 현재 위치가 아직 청소되지 않음
			CleanedCount++;
			MapDirt[Robot[0]] |= 1L << Robot[1];
		}
	}

	private static boolean isAllClear() {
		for (int[] dir : Deltas) { // 4방향 탐색
			int nextN = Robot[0] + dir[0];
			int nextM = Robot[1] + dir[1];
			if ((MapWall[nextN] & (1L << nextM)) != 0) // 벽이라면
				continue;
			if ((MapDirt[nextN] & (1L << nextM)) == 0) // 청소되지 않음
				return false;
		}
		return true;
	}

	private static boolean isAbleGoBack() {
		int backN = Robot[0] + Deltas[(Robot[2] + 2) % 4][0];
		int backM = Robot[1] + Deltas[(Robot[2] + 2) % 4][1];
		if ((MapWall[backN] & (1L << backM)) == 0) { // 후진 위치가 벽이 아니라면
			Robot[0] = backN; // 한 칸 후진
			Robot[1] = backM;
			return true;
		} else { // 벽이라면
			return false;
		}
	}

	private static void doProceed() {
		int nextN = Robot[0] + Deltas[Robot[2]][0];
		int nextM = Robot[1] + Deltas[Robot[2]][1];
		if ((MapDirt[nextN] & (1L << nextM)) == 0 && (MapWall[nextN] & (1L << nextM)) == 0) { // 앞쪽 칸이 청소되지 않은 빈 칸인 경우
			Robot[0] = nextN; // 한 칸 전진한다
			Robot[1] = nextM;
		}
	}

}