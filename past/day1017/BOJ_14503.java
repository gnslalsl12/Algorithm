package day1017;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14503 {
	static int N, M;
	static int [][] maps;
	static int[][] deltas = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	static robot rbt;
	static int result;

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		maps = new int [N][M];
		tokens = new StringTokenizer(read.readLine());
		int r = Integer.parseInt(tokens.nextToken());
		int c = Integer.parseInt(tokens.nextToken());
		int dir = Integer.parseInt(tokens.nextToken());
		rbt = new robot(r, c, dir);
		for (int i = 0; i < N; i++) {
			tokens = new StringTokenizer(read.readLine());
			for (int j = 0; j < M; j++) {
				maps[i][j] = Integer.parseInt(tokens.nextToken());
			}
		}
		result = 0;
		clean();
		breakall: while (true) {
//			print();
			int rt = 0;
			boolean noleft = false;
			while (true) { // true : 왼쪽에 벽/청소
				if (!searchLeft())
					break;
				rbt.dir = rotate();
				rt++;
				if (rt == 4) {
					noleft = true;
					break;
				}
			}
			// 청소할 곳 발견
			if (!noleft) {
				rbt.dir = rotate();
				forward();
				clean();
				continue;
			} else { // 네방향 모두 청소할 곳 없음
				boolean avail = back();
				if (!avail) {
					break breakall;
				}
			}
		}
		System.out.println(result);
	}

	private static void print() {
		System.out.println("//////////////////");
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (i == rbt.x && j == rbt.y)
					if(rbt.dir == 0) System.out.printf("▲");
					else if(rbt.dir == 1) System.out.printf("▶");
					else if(rbt.dir == 2) System.out.printf("▼");
					else System.out.printf("◀ ");
				else if(maps[i][j] == 1)
					System.out.printf("▒ ");

				else if (maps[i][j] == 2)
					System.out.printf("■ ");
				else if(maps[i][j] == 0)
					System.out.printf("□ ");

			}
			System.out.println();
		}
	}

	private static boolean back() {
		rbt.x -= deltas[rbt.dir][0];
		rbt.y -= deltas[rbt.dir][1];
		if(maps[rbt.x][rbt.y] == 1)return false; 
		return true;
	}

	private static void clean() {
		result++;
		maps[rbt.x][rbt.y] = 2;
	}

	private static boolean searchLeft() {
		int x = deltas[rotate()][0];
		int y = deltas[rotate()][1];
		return maps[rbt.x + x][rbt.y + y] != 0;
	}

	private static boolean forward() {
		rbt.x += deltas[rbt.dir][0];
		rbt.y += deltas[rbt.dir][1];
		return true;
	}

	private static int rotate() {
		if (rbt.dir == 0)
			return 3;
		else
			return rbt.dir - 1;
	}


	private static class robot {
		int x;
		int y;
		int dir;

		public robot(int x, int y, int dir) {
			super();
			this.x = x;
			this.y = y;
			this.dir = dir;
		}

	}

}
