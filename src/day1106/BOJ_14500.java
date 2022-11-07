package day1106;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14500 {
	static int[][] maps;
	static int result;
	static int N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		result = Integer.MIN_VALUE;
		maps = new int[N][M];
		for (int i = 0; i < N; i++) {
			tokens = new StringTokenizer(read.readLine());
			for (int j = 0; j < M; j++) {
				maps[i][j] = Integer.parseInt(tokens.nextToken());
			}
		} // mapping

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (isIn(i + 3, j))
					getBlueByX(i, j);
				if (isIn(i, j + 3))
					getBlueByY(i, j);

				if (isIn(i + 1, j + 1))
					getYellow(i, j);

				if (isIn(i + 2, j + 1))
					getOrangeDown(i, j);
				if (isIn(i + 1, j + 2))
					getOrangeRight(i, j);

				if (isIn(i + 2, j + 1))
					getGreenDown(i, j);
				if (isIn(i + 1, j + 2))
					getGreenRight(i, j);

				if (isIn(i + 2, j + 1))
					getPurpleDown(i, j);
				if (isIn(i + 1, j + 2))
					getPurpleRight(i, j);
			}
		}
		System.out.println(result);
	}

	private static boolean isIn(int i, int j) {
		return i >= 0 && j >= 0 && i < N && j < M;
	}

	private static void getPurpleRight(int x, int y) {
		result = Math.max(maps[x][y] + maps[x][y + 1] + maps[x][y + 2] + maps[x + 1][y + 1], result);
		result = Math.max(maps[x][y + 1] + maps[x + 1][y] + maps[x + 1][y + 1] + maps[x + 1][y + 2], result);
	}

	private static void getPurpleDown(int x, int y) {
		result = Math.max(maps[x][y] + maps[x + 1][y] + maps[x + 1][y + 1] + maps[x + 2][y], result);
		result = Math.max(maps[x][y + 1] + maps[x + 1][y] + maps[x + 1][y + 1] + maps[x + 2][y + 1], result);
	}

	private static void getGreenDown(int x, int y) {
		result = Math.max(maps[x][y] + maps[x + 1][y] + maps[x + 1][y + 1] + maps[x + 2][y + 1], result); // 그림상
		result = Math.max(maps[x][y + 1] + maps[x + 1][y + 1] + maps[x + 1][y] + maps[x + 2][y], result); // 그림 대칭
	}

	private static void getGreenRight(int x, int y) {
		result = Math.max(maps[x + 1][y] + maps[x + 1][y + 1] + maps[x][y + 1] + maps[x][y + 2], result); // 그림상 회전
		result = Math.max(maps[x][y] + maps[x][y + 1] + maps[x + 1][y + 1] + maps[x + 1][y + 2], result); // 그림 대칭 회전
	}

	private static void getOrangeDown(int x, int y) {
		result = Math.max(maps[x][y] + maps[x][y + 1] + maps[x + 1][y + 1] + maps[x + 2][y + 1], result); // ㄱ
		result = Math.max(maps[x][y + 1] + maps[x][y] + maps[x + 1][y] + maps[x + 2][y], result); // ㄱ 대칭
		result = Math.max(maps[x][y] + maps[x + 1][y] + maps[x + 2][y] + maps[x + 2][y + 1], result); // ㄴ
		result = Math.max(maps[x][y + 1] + maps[x + 1][y + 1] + maps[x + 2][y + 1] + maps[x + 2][y], result); // ㄴ 대칭
	}

	private static void getOrangeRight(int x, int y) {
		result = Math.max(maps[x][y] + maps[x][y + 1] + maps[x][y + 2] + maps[x + 1][y + 2], result); // ㄱ
		result = Math.max(maps[x + 1][y] + maps[x][y] + maps[x][y + 1] + maps[x][y + 2], result); // ㄱ 대칭
		result = Math.max(maps[x][y] + maps[x + 1][y] + maps[x + 1][y + 1] + maps[x + 1][y + 2], result); // ㄴ
		result = Math.max(maps[x + 1][y] + maps[x + 1][y + 1] + maps[x + 1][y + 2] + maps[x][y + 2], result); // ㄴ 대칭
	}

	private static void getYellow(int x, int y) {
		result = Math.max(maps[x][y] + maps[x + 1][y + 1] + maps[x][y + 1] + maps[x + 1][y], result);
	}

	private static void getBlueByY(int x, int y) {
		result = Math.max(maps[x][y] + maps[x][y + 1] + maps[x][y + 2] + maps[x][y + 3], result);
	}

	private static void getBlueByX(int x, int y) {
		result = Math.max(maps[x][y] + maps[x + 1][y] + maps[x + 2][y] + maps[x + 3][y], result);
	}

}