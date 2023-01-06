package day1009;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_5643 {
	static int N, M;
	static boolean[][] maps;

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(read.readLine());
		for (int test = 1; test <= T; test++) {

			StringTokenizer tokens = new StringTokenizer(read.readLine());
			N = Integer.parseInt(tokens.nextToken());
			tokens = new StringTokenizer(read.readLine());
			M = Integer.parseInt(tokens.nextToken());
			maps = new boolean[N + 1][N + 1];
			for (int i = 0; i < M; i++) {
				tokens = new StringTokenizer(read.readLine());
				int a = Integer.parseInt(tokens.nextToken());
				int b = Integer.parseInt(tokens.nextToken());
				maps[a][b] = true;
			}

			FlydWshl();
			int result = 0;
			for (int i = 1; i <= N; i++) {
				int count = 0;
				for (int j = 1; j <= N; j++) {
					if (maps[i][j] || maps[j][i]) {
						count++;
					}
				}
				if (count == N - 1)
					result++;
			}
			System.out.println("#" + test + " " + result);
		}
	}

	private static void print() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (maps[i][j])
					System.out.printf("%d ", 1);
				else
					System.out.printf("%d ", 0);
			}
			System.out.println();
		}
	}

	private static void FlydWshl() {
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (maps[i][k] && maps[k][j]) {
						maps[i][j] = true;
					}
				}
			}
		}
	}

}
