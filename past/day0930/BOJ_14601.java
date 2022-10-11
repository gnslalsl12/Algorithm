package day0930;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14601 {
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

		Fill8X8(Hx, Hy);
		if (K == 3) {
			printMaps();
			return;
		}

		int startx16 = Hx - Hx % 16;
		int starty16 = Hy - Hy % 16;
		Fill16X16(startx16 + 7, starty16 + 7);
		if (K == 4) {
			printMaps();
			return;
		}

		int startx32 = Hx - Hx % 32;
		int starty32 = Hy - Hy % 32;
		Fill32X32(startx32 + 15, starty32 + 15);
		if (K == 5) {
			printMaps();
			return;
		}

		int startx64 = Hx - Hx % 64;
		int starty64 = Hy - Hy % 64;
		Fill64X64(startx64 + 31, starty64 + 31);
		if (K == 6) {
			printMaps();
			return;
		}
		
		int startx128 = Hx - Hx % 128;
		int starty128 = Hy - Hy % 128;
		Fill128X128(startx128 + 63, starty128 + 63);
		printMaps();
	}

	static void Fill128X128(int x, int y) {
		if (maps[x][y] == 0) {
			setCorner(x, y);
			Fill4X4(x - x % 4, y - y % 4, getDir(x, y));
			Fill8X8(x, y);
			Fill16X16(x - x % 16 + 7, y - y % 16 + 7);
			Fill32X32(x - x % 32 + 15, y - y % 32 + 15);
			Fill64X64(x - x % 64 + 31, y - y % 64 + 31);
		}
		x++;
		if (maps[x][y] == 0) {
			setCorner(x, y);
			Fill4X4(x - x % 4, y - y % 4, getDir(x, y));
			Fill8X8(x, y);
			Fill16X16(x - x % 16 + 7, y - y % 16 + 7);
			Fill32X32(x - x % 32 + 15, y - y % 32 + 15);
			Fill64X64(x - x % 64 + 31, y - y % 64 + 31);
		}
		y++;
		x--;
		if (maps[x][y] == 0) {
			setCorner(x, y);
			Fill4X4(x - x % 4, y - y % 4, getDir(x, y));
			Fill8X8(x, y);
			Fill16X16(x - x % 16 + 7, y - y % 16 + 7);
			Fill32X32(x - x % 32 + 15, y - y % 32 + 15);
			Fill64X64(x - x % 64 + 31, y - y % 64 + 31);
		}
		x++;
		if (maps[x][y] == 0) {
			setCorner(x, y);
			Fill4X4(x - x % 4, y - y % 4, getDir(x, y));
			Fill8X8(x, y);
			Fill16X16(x - x % 16 + 7, y - y % 16 + 7);
			Fill32X32(x - x % 32 + 15, y - y % 32 + 15);
			Fill64X64(x - x % 64 + 31, y - y % 64 + 31);
		}
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				if (maps[x - i][y - j] == 0) {
					maps[x - i][y - j] = count;
				}
			}
		}
		count++;
	}

	static void Fill64X64(int x, int y) {
		if (maps[x][y] == 0) {
			setCorner(x, y);
			Fill4X4(x - x % 4, y - y % 4, getDir(x, y));
			Fill8X8(x, y);
			Fill16X16(x - x % 16 + 7, y - y % 16 + 7);
			Fill32X32(x - x % 32 + 15, y - y % 32 + 15);
		}
		x++;
		if (maps[x][y] == 0) {
			setCorner(x, y);
			Fill4X4(x - x % 4, y - y % 4, getDir(x, y));
			Fill8X8(x, y);
			Fill16X16(x - x % 16 + 7, y - y % 16 + 7);
			Fill32X32(x - x % 32 + 15, y - y % 32 + 15);
		}
		y++;
		x--;
		if (maps[x][y] == 0) {
			setCorner(x, y);
			Fill4X4(x - x % 4, y - y % 4, getDir(x, y));
			Fill8X8(x, y);
			Fill16X16(x - x % 16 + 7, y - y % 16 + 7);
			Fill32X32(x - x % 32 + 15, y - y % 32 + 15);
		}
		x++;
		if (maps[x][y] == 0) {
			setCorner(x, y);
			Fill4X4(x - x % 4, y - y % 4, getDir(x, y));
			Fill8X8(x, y);
			Fill16X16(x - x % 16 + 7, y - y % 16 + 7);
			Fill32X32(x - x % 32 + 15, y - y % 32 + 15);
		}
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				if (maps[x - i][y - j] == 0) {
					maps[x - i][y - j] = count;
				}
			}
		}
		count++;
	}

	static void Fill32X32(int x, int y) {
		if (maps[x][y] == 0) {
			setCorner(x, y);
			Fill4X4(x - x % 4, y - y % 4, getDir(x, y));
			Fill8X8(x, y);
			Fill16X16(x - x % 16 + 7, y - y % 16 + 7);
		}
		x++;
		if (maps[x][y] == 0) {
			setCorner(x, y);
			Fill4X4(x - x % 4, y - y % 4, getDir(x, y));
			Fill8X8(x, y);
			Fill16X16(x - x % 16 + 7, y - y % 16 + 7);
		}
		y++;
		x--;
		if (maps[x][y] == 0) {
			setCorner(x, y);
			Fill4X4(x - x % 4, y - y % 4, getDir(x, y));
			Fill8X8(x, y);
			Fill16X16(x - x % 16 + 7, y - y % 16 + 7);
		}
		x++;
		if (maps[x][y] == 0) {
			setCorner(x, y);
			Fill4X4(x - x % 4, y - y % 4, getDir(x, y));
			Fill8X8(x, y);
			Fill16X16(x - x % 16 + 7, y - y % 16 + 7);
		}
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				if (maps[x - i][y - j] == 0) {
					maps[x - i][y - j] = count;
				}
			}
		}
		count++;
	}

	static void Fill16X16(int x, int y) {
		if (maps[x][y] == 0) {
			setCorner(x, y);
			Fill4X4(x - x % 4, y - y % 4, getDir(x, y));
			Fill8X8(x, y);

		}
		y++;
		if (maps[x][y] == 0) {
			setCorner(x, y);
			Fill4X4(x - x % 4, y - y % 4, getDir(x, y)); // 여기 고쳐야함 (startx, statrty로
			Fill8X8(x, y);
		}
		y--;
		x++;
		if (maps[x][y] == 0) {
			setCorner(x, y);
			Fill4X4(x - x % 4, y - y % 4, getDir(x, y)); // 여기 고쳐야함 (startx, statrty로
			Fill8X8(x, y);
		}
		y++;
		if (maps[x][y] == 0) {
			setCorner(x, y);
			Fill4X4(x - x % 4, y - y % 4, getDir(x, y)); // 여기 고쳐야함 (startx, statrty로
			Fill8X8(x, y);
		}
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				if (maps[x - i][y - j] == 0) {
					maps[x - i][y - j] = count;
				}
			}
		}
		count++;
	}

	static void Fill8X8(int Hx, int Hy) {
		int startx = Hx - Hx % 4;
		int starty = Hy - Hy % 4;
		int x = startx - startx % 8;
		int y = starty - starty % 8;

		if (maps[x + 3][y + 3] == 0) {
			setCorner(x + 3, y + 3);
			Fill4X4(x, y, getDir(x + 3, y + 3));
		}
		if (maps[x + 3][y + 4] == 0) {
			setCorner(x + 3, y + 4);
			Fill4X4(x, y + 4, getDir(x + 3, y + 4));
		}
		if (maps[x + 4][y + 3] == 0) {
			setCorner(x + 4, y + 3);
			Fill4X4(x + 4, y, getDir(x + 4, y + 3));
		}
		if (maps[x + 4][y + 4] == 0) {
			setCorner(x + 4, y + 4);
			Fill4X4(x + 4, y + 4, getDir(x + 4, y + 4));
		}
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				if (maps[x + 3 + i][y + 3 + j] == 0) {
					maps[x + 3 + i][y + 3 + j] = count;
				}
			}
		}
		count++;
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