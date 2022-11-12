package day1112;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_3019 {
	static int C, P;
	static int[] maps;

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		C = Integer.parseInt(tokens.nextToken());
		P = Integer.parseInt(tokens.nextToken());
		tokens = new StringTokenizer(read.readLine());
		maps = new int[C];
		for (int i = 0; i < C; i++) {
			maps[i] = Integer.parseInt(tokens.nextToken());
		}
		int result = setBlock(P);
		System.out.println(result);
	}

	private static int block7(int x, int y) {
		int tempcount = 0;
		if (isIn(x, y + 2) && maps[y + 1] == x && maps[y + 2] == x)
			tempcount++;
		if (isIn(x, y + 1) && maps[y + 1] == x + 2)
			tempcount++;
		if (isIn(x - 1, y + 2) && maps[y + 1] == x && maps[y + 2] == x - 1)
			tempcount++;
		if (isIn(x, y + 1) && maps[y + 1] == x)
			tempcount++;
		return tempcount;
	}

	private static int block6(int x, int y) {
		int tempcount = 0;
		if (isIn(x, y + 2) && maps[y + 1] == x && maps[y + 2] == x)
			tempcount++;
		if (isIn(x, y + 1) && maps[y + 1] == x)
			tempcount++;
		if (isIn(x, y + 2) && maps[y + 1] == x + 1 && maps[y + 2] == x + 1)
			tempcount++;
		if (isIn(x - 2, y + 1) && maps[y + 1] == x - 2)
			tempcount++;
		return tempcount;
	}

	private static int block5(int x, int y) {
		int tempcount = 0;
		if (isIn(x, y + 2) && maps[y + 1] == x && maps[y + 2] == x)
			tempcount++;
		if (isIn(x, y + 1) && maps[y + 1] == x + 1)
			tempcount++;
		if (isIn(x - 1, y + 2) && maps[y + 1] == x - 1 && maps[y + 2] == x)
			tempcount++;
		if (isIn(x - 1, y + 1) && maps[y + 1] == x - 1)
			tempcount++;
		return tempcount;
	}

	private static int block4(int x, int y) {
		int tempcount = 0;
		if (isIn(x - 1, y + 2) && maps[y + 1] == x - 1 && maps[y + 2] == x - 1)
			tempcount++;
		if (isIn(x, y + 1) && maps[y + 1] == x + 1)
			tempcount++;
		return tempcount;
	}

	private static int block3(int x, int y) {
		int tempcount = 0;
		if (isIn(x, y + 2) && maps[y + 1] == x && maps[y + 2] == x + 1)
			tempcount++;
		if (isIn(x - 1, y + 1) && maps[y + 1] == x - 1)
			tempcount++;
		return tempcount;
	}

	private static int block2(int x, int y) {
		if (isIn(x, y + 1) && maps[y + 1] == x) {
			return 1;
		}
		return 0;
	}

	private static int block1(int x, int y) {
		if (isIn(x, y + 3) && maps[y + 1] == x && maps[y + 2] == x && maps[y + 3] == x)
			return 2;
		return 1;
	}

	private static int setBlock(int type) {
		int resultcount = 0;
		switch (type) {
		case (1): {
			for (int line = 0; line < C; line++) {
				resultcount += block1(maps[line], line);
			}
			break;
		}
		case (2): {
			for (int line = 0; line < C - 1; line++) {
				resultcount += block2(maps[line], line);
			}
			break;
		}
		case (3): {
			for (int line = 0; line < C - 1; line++) {
				resultcount += block3(maps[line], line);
			}
			break;
		}
		case (4): {
			for (int line = 0; line < C - 1; line++) {
				resultcount += block4(maps[line], line);
			}
			break;
		}
		case (5): {
			for (int line = 0; line < C - 1; line++) {
				resultcount += block5(maps[line], line);
			}
			break;
		}
		case (6): {
			for (int line = 0; line < C - 1; line++) {
				resultcount += block6(maps[line], line);
			}
			break;
		}
		case (7): {
			for (int line = 0; line < C - 1; line++) {
				resultcount += block7(maps[line], line);
			}
			break;
		}
		}
		return resultcount;
	}

	private static boolean isIn(int x, int y) {
		return x >= 0 && y >= 0 && y < C;
	}

}
