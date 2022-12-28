package day1226;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1856 {
	static int V, E, maps[][];
	static int INF = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		V = Integer.parseInt(tokens.nextToken());
		E = Integer.parseInt(tokens.nextToken());
		maps = new int[V + 1][V + 1];
		for (int i = 1; i <= V; i++) {
			Arrays.fill(maps[i], INF);
		}
		for (int i = 0; i < E; i++) {
			tokens = new StringTokenizer(read.readLine());
			int from = Integer.parseInt(tokens.nextToken());
			int to = Integer.parseInt(tokens.nextToken());
			int value = Integer.parseInt(tokens.nextToken());
			maps[from][to] = value;
		}
		getFLDWS();
		int result = INF;
		for (int i = 1; i <= V; i++) {
			for (int j = 1; j <= V; j++) {
				if (i != j && maps[i][j] != INF && maps[j][i] != INF) {
					result = Math.min(result, maps[i][j] + maps[j][i]);
				}
			}
		}
		if (result == INF)
			result = -1;
		System.out.println(result);
	}

	private static void getFLDWS() {
		for (int i = 1; i <= V; i++) {
			for (int j = 1; j <= V; j++) {
				if (i == j)
					continue;
				for (int k = 1; k <= V; k++) {
					if (i == k || j == k)
						continue;
					if (maps[i][k] != INF && maps[k][j] != INF) {
						maps[i][j] = Math.min(maps[i][j], maps[i][k] + maps[k][j]);
					}
				}
			}
		}
	}

}
