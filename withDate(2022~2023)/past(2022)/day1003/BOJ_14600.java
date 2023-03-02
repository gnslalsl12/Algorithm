package day1003;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14600 {
	static int KL;
	static int[][] maps;
	static int count;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		int K = Integer.parseInt(read.readLine());
		KL = (int) Math.pow(2, K);
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		int Hxx = Integer.parseInt(tokens.nextToken());
		int Hyy = Integer.parseInt(tokens.nextToken());
		maps = new int[KL][KL];
		int Hx = KL - Hyy;
		int Hy = Hxx - 1;
		maps[Hx][Hy] = -1;
		count = 1;
		int startx = Hx - Hx % 4;
		int starty = Hy - Hy % 4;

		setCorner(Hx, Hy);
		if (K == 1) {
			printMaps();
			return;
		}

		Fill4X4(startx, starty, getDir(Hx, Hy));
		if (K == 2) {
			printMaps();
			return;
		}

	}


	static int getDir(int Hx, int Hy) { // 0 : 오른쪽 위 / 1 : 오른쪽 아래 / 2 : 왼쪽 아래 / 3 : 왼쪽 위
		if (Hx % 4 == 0 || Hx % 4 == 1) {
			if (Hy % 4 == 0 || Hy % 4 == 1) {
				return 3;
			} else {
				return 0;
			}
		} else {
			if (Hy % 4 == 0 || Hy % 4 == 1) {
				return 2;
			} else {
				return 1;
			}
		}
	}

	static void Fill4X4(int x, int y, int dir) {
		if (dir == 0) {
			maps[x][y] = maps[x][y + 1] = maps[x + 1][y] = count++;
			maps[x + 3][y] = maps[x + 2][y] = maps[x + 3][y + 1] = count++;
			maps[x + 3][y + 3] = maps[x + 3][y + 2] = maps[x + 2][y + 3] = count++;
			maps[x + 2][y + 1] = maps[x + 2][y + 2] = maps[x + 1][y + 1] = count++;
		} else if (dir == 1) {
			maps[x][y] = maps[x][y + 1] = maps[x + 1][y] = count++;
			maps[x][y + 3] = maps[x][y + 2] = maps[x + 1][y + 3] = count++;
			maps[x + 3][y] = maps[x + 2][y] = maps[x + 3][y + 1] = count++;
			maps[x + 1][y + 1] = maps[x + 1][y + 2] = maps[x + 2][y + 1] = count++;
		} else if (dir == 2) {
			maps[x][y + 3] = maps[x][y + 2] = maps[x + 1][y + 3] = count++;
			maps[x][y] = maps[x + 1][y] = maps[x][y + 1] = count++;
			maps[x + 3][y + 3] = maps[x + 2][y + 3] = maps[x + 3][y + 2] = count++;
			maps[x + 1][y + 2] = maps[x + 1][y + 1] = maps[x + 2][y + 2] = count++;
		} else if (dir == 3) {
			maps[x][y + 3] = maps[x][y + 2] = maps[x + 1][y + 3] = count++;
			maps[x + 3][y + 3] = maps[x + 3][y + 2] = maps[x + 2][y + 3] = count++;
			maps[x + 3][y] = maps[x + 2][y] = maps[x + 3][y + 1] = count++;
			maps[x + 2][y + 2] = maps[x + 1][y + 2] = maps[x + 2][y + 1] = count++;
		}
	}

	static void setCorner(int x, int y) {
		int dir = 0;
		if (x % 2 == 0) {
			if (y % 2 == 0) {
				dir = 1;
			} else {
				dir = 2;
			}
		} else {
			if (y % 2 == 0) {
				dir = 0;
			} else {
				dir = 3;
			}
		}
		if (dir == 0) { // 오른쪽 위
			maps[x - 1][y] = maps[x - 1][y + 1] = maps[x][y + 1] = count++;
		} else if (dir == 1) { // 오른쪽 아래
			maps[x][y + 1] = maps[x + 1][y + 1] = maps[x + 1][y] = count++;
		} else if (dir == 2) { // 왼쪽 아래
			maps[x + 1][y] = maps[x + 1][y - 1] = maps[x][y - 1] = count++;
		} else { // 왼쪽 위
			maps[x][y - 1] = maps[x - 1][y - 1] = maps[x - 1][y] = count++;
		}
	}

	static boolean isIn(int x, int y) {
		return x >= 0 && x < KL && y >= 0 && y < KL;
	}

	static void printMaps() {
		for (int[] p : maps) {
			for (int k : p) {
				System.out.printf("%d ", k);
			}
			System.out.println();
		}
	}
}
