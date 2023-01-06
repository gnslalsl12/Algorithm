package day1016;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1238 {
	static int N, M, X;
	static int[][] maps;
	static int INF = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		X = Integer.parseInt(tokens.nextToken());
		maps = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			Arrays.fill(maps[i], INF);
		}
		for (int i = 0; i < M; i++) {
			tokens = new StringTokenizer(read.readLine());
			int from = Integer.parseInt(tokens.nextToken());
			int to = Integer.parseInt(tokens.nextToken());
			int value = Integer.parseInt(tokens.nextToken());
			maps[from][to] = value;
		}
		FloydWashall();
		int result = -1;
		for (int i = 1; i <= N; i++) {
			if(i == X) continue;
			result = Math.max(result, maps[i][X] + maps[X][i]);
		}
		System.out.println(result);
	}

	private static void FloydWashall() {
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (maps[i][k] == INF || maps[k][j] == INF)
						continue;
					maps[i][j] = Math.min(maps[i][j], maps[i][k] + maps[k][j]);
				}
			}
		}
	}
}