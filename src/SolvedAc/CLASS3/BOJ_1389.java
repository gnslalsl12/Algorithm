package SolvedAc.CLASS3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1389 {
	static int N, M;
	static boolean[][] maps;
	static int[][] count;

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		maps = new boolean[N + 1][N + 1];
		count = new int[N + 1][N + 1];
		for (int i = 0; i < M; i++) {
			tokens = new StringTokenizer(read.readLine());
			int a = Integer.parseInt(tokens.nextToken());
			int b = Integer.parseInt(tokens.nextToken());
			maps[a][b] = maps[b][a] = true;
			count[a][b] = count[b][a] = 1;
		}

	}

	private static void FLUALA() {
		for (int f = 1; f <= N; f++) {
			for (int t = f + 1; t <= N; t++) {
				for (int k = 1; k <= N; k++) {
					if (k == t)
						continue;
					if (maps[f][k] && maps[k][t]) {
						maps[f][t] = maps[t][f] = true;
						count[f][t]++;
					}
				}
			}
		}
	}

}
