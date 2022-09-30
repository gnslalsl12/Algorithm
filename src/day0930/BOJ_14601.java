package day0930;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_14601 {
	static int KL;
	static int[][] maps;
	static int count;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		int K = Integer.parseInt(read.readLine());
		KL = (int) Math.pow(2, K);
		System.out.print("Hxx, Hyy : ");
		String[] temp = read.readLine().split(" ");
		int Hxx = Integer.parseInt(temp[0]);
		int Hyy = Integer.parseInt(temp[1]);

		maps = new int[KL][KL];
		int Hx = KL - Hyy;
		int Hy = Hxx - 1;
		maps[Hx][Hy] = -1;

		boolean found = false;
		// 오른쪽 위 채우기부터
		count = 1;
		int dir = 0;
		if (Hx % 2 == 0) {
			if (Hy % 2 == 0) {
				setCorner(Hx, Hy, 1);
			} else {
				setCorner(Hx, Hy, 2);
			}
		} else {
			if (Hy % 2 == 0) {
				setCorner(Hx, Hy, 0);
			} else {
				setCorner(Hx, Hy, 3);
			}
		} // 초기 타일 세팅
		int startx = Hx - Hx % 4;
		int starty = Hy - Hy % 4;
		if(Hx%4 == 0 || Hx%4 == 1) {
			if(Hy%4 == 0 || Hy%4 == 1) {
				dir = 3;
			}else {
				dir = 0;
			}
		}else {
			if(Hy%4 == 0 || Hy%4 == 1) {
				dir = 2;
			}else {
				dir = 1;
			}
		}
		System.out.println("Hx, Hy : " + Hx + ", " + Hy);
		System.out.println("x, y  : " + startx + ", " + starty);
		System.out.println("dir : " + dir);
		Fill4X4(startx, starty, dir);
		for (int[] t : maps) {
			System.out.println(Arrays.toString(t));
		}
	}

	/*
	 * ㄱ자로 만드는 구조를 크게 만들면 큰 ㄱ자를 만듦으로써 모든 타일을 꽉 채울 수 있음
	 */

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

	static void setCorner(int x, int y, int dir) {
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

	static void toStrick(int Hx, int Hy, int dir) {
		while (true) {
			if (Hx % 2 == 0 && Hy % 2 == 0)
				break;
			switch (dir) {
			case (0): {
				if (!isIn(Hx + 1, Hy - 1))
					return;
				setCorner(Hx, Hy, 2);
				break;
			}
			case (1): {
				if (!isIn(Hx - 1, Hy - 1))
					return;
				setCorner(Hx, Hy, 3);
				break;
			}
			case (2): {
				if (!isIn(Hx - 1, Hy + 1))
					return;
				setCorner(Hx, Hy, 0);
				break;
			}
			case (3): {
				if (!isIn(Hx + 1, Hy + 1))
					return;
				setCorner(Hx, Hy, 1);
				break;
			}
			}
		}
	}

}
