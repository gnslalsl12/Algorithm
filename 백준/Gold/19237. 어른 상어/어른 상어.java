import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static int M;
	static int Remains;
	static int K;
	static int[][] Map;
	static int[][] Deltas;
	static int TotalTime;
	static ArrayList<Shark> SharkList;

	public static void main(String[] args) throws IOException {
		init();
		solv();
	}

	private static void init() throws IOException {
		N = readInt();
		M = readInt();
		Remains = M;
		K = readInt();
		Map = new int[N][N];
		SharkList = new ArrayList<>();
		TotalTime = 0;
		for (int m = 0; m <= M; m++) {
			SharkList.add(new Shark());
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int input = readInt();
				Map[i][j] = input * 10000;
				if (input != 0) {
					SharkList.get(input).putXY(input, i, j);
				}
			}
		}
		for (int m = 1; m <= M; m++) {
			SharkList.get(m).dir = readInt() - 1;
		}
		for (int m = 1; m <= M; m++) {
			int[][] dirInfo = new int[4][4];
			for (int infoIndex = 0; infoIndex < 4; infoIndex++) {
				for (int dirIndex = 0; dirIndex < 4; dirIndex++) {
					dirInfo[infoIndex][dirIndex] = readInt() - 1;
				}
			}
			SharkList.get(m).dirInfo = dirInfo;
		}
		Deltas = new int[][] { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

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
		while (++TotalTime <= 1000) {
			setWholeSharkMove();
			if (Remains == 1)
				break;
		}
		write.write((TotalTime == 1001) ? "-1\n" : TotalTime + "\n");
		write.close();
	}

	private static void setWholeSharkMove() {
		for (int m = M; m >= 1; m--) {
			SharkList.get(m).move();
		}
	}

	private static class Shark {
		int num;
		int x;
		int y;
		int dir;
		int[][] dirInfo;
		boolean onMyWay = false;

		public Shark() {
		}

		private void putXY(int num, int x, int y) {
			this.num = num;
			this.x = x;
			this.y = y;
		}

		private void move() {
			if (num == -1)
				return;
			onMyWay = false;
			boolean reVis = false;
			int findX = x;
			int findY = y;
			int findDir = dir;
			for (int nextDir : dirInfo[dir]) {
				int nextX = x + Deltas[nextDir][0];
				int nextY = y + Deltas[nextDir][1];
				if (!isIn(nextX, nextY)) // 범위 밖
					continue;
				int nextMapSmell = Map[nextX][nextY];
				int sharkNum = nextMapSmell / 10000; // 해당 냄새를 남긴 상어 번호
				int remainTime = nextMapSmell % 10000; // 해당 냄새가 기록된 시간
				if (sharkNum != 0 && sharkNum != num) { // 빈공간이 아니고 다른 상어의 냄새
					if ((remainTime == TotalTime && !SharkList.get(sharkNum).onMyWay) || remainTime + K < TotalTime) {
						// 빈 공간에 동시에 도착한 상어(나보다 높은 번호) || 이미 사라진 냄새(빈공간)
						if (remainTime == TotalTime) {
							SharkList.get(sharkNum).num = -1;
							Remains--;
							// 동시에 도착한 상어면 쫓아내기
						}
						findX = nextX;
						findY = nextY;
						findDir = nextDir;
						onMyWay = false;
						break;
					} else { // 자기 자리로 돌아온 다른 상어이거나 아직 남아있는 냄새
						continue;
					}
				} else { // 내 냄새이거나 빈공간
					if (reVis && sharkNum != 0 && remainTime + K >= TotalTime) {// 재방문인데 빈공간이 아니면 패스
						continue;
					}
					// 재방문이 아니거나 빈공간이거나 이미 사라진 내 냄새라면
					reVis = true;
					findX = nextX;
					findY = nextY;
					findDir = nextDir;
					if (sharkNum == 0 || remainTime + K < TotalTime) { // 빈공간이면 끝, 내 냄새라면 계속 탐색(빈공간이 나올 때까지)
						onMyWay = false;
						break;
					} else {
						onMyWay = true;
					}
				}
			}
			x = findX;
			y = findY;
			Map[findX][findY] = TotalTime + num * 10000;
			dir = findDir;
		}

	}

	private static boolean isIn(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}

}