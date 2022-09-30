package day0930;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_17070_DynamicProgrammingVer {
	static int N;
	static int walls[];
	static int maps[][][];

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(read.readLine());

		maps = new int[N][N][3];
		walls = new int[N];
		maps[0][1][0] = 1;
		StringTokenizer tokens;
		for (int i = 0; i < N; i++) {
			tokens = new StringTokenizer(read.readLine());
			if (Integer.parseInt(tokens.nextToken()) == 1)
				walls[i] = 1;
			for (int j = 1; j < N; j++) {
				if (Integer.parseInt(tokens.nextToken()) == 0) {
					// 1번 파이프로 될 수 있는 거
					if (isIn(i, j - 1) && (walls[i] & 1 << j) == 0 && j >= 2) {
						if (j >= 2)
							maps[i][j][0] += maps[i][j - 1][0];
						if (i >= 1 && j >= 2)
							maps[i][j][0] += maps[i][j - 1][2];
					}
					// 2번 파이프로 될 수 있는 거
					if (isIn(i - 1, j) && (walls[i - 1] & 1 << j) == 0) {
						maps[i][j][1] = maps[i - 1][j][1];
						if (i >= 1 && j >= 2)
							maps[i][j][1] += maps[i - 1][j][2];
					}
					// 3번 파이프로 될 수 있는 거
					if (isIn(i - 1, j - 1) && (walls[i - 1] & 1 << (j - 1)) == 0 && (walls[i - 1] & 1 << (j)) == 0
							&& (walls[i] & 1 << (j - 1)) == 0 && j >= 2) {
						maps[i][j][2] = maps[i - 1][j - 1][2];
						maps[i][j][2] += maps[i - 1][j - 1][1];
						maps[i][j][2] += maps[i - 1][j - 1][0];
					}
				} else {
					walls[i] |= 1 << j;
				}
			}
		}
		System.out.println(maps[N - 1][N - 1][0] + maps[N - 1][N - 1][1] + maps[N - 1][N - 1][2]);
	}

	static boolean isIn(int i, int j) {
		return i >= 0 && j >= 0 && i < N && j < N;
	}

}
