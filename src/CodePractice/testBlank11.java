package CodePractice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class testBlank11 {
	static int N, M;
	static int[][] maps;
	static int INF = Integer.MAX_VALUE;
	static int totalresult;
	static int[] Dist;

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		tokens = new StringTokenizer(read.readLine());
		M = Integer.parseInt(tokens.nextToken());
		maps = new int[N + 1][N + 1];
		Dist = new int[N + 1];
		Arrays.fill(Dist, INF);
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (i == j)
					maps[i][j] = 0;
				else
					maps[i][j] = INF;
			}
		}
		int from = 0;
		for (int i = 0; i < M; i++) {
			tokens = new StringTokenizer(read.readLine());
			from = Integer.parseInt(tokens.nextToken());
			int to = Integer.parseInt(tokens.nextToken());
			int value = Integer.parseInt(tokens.nextToken());
			maps[from][to] = value;
		}
		totalresult = INF;
		BellmanFord(from);

		System.out.println(totalresult);
	}

	private static void BellmanFord(int start) {
		Dist[start] = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (maps[i][j] != INF) {// 갱신된 아이면 / 또는 시작점일 때
					Dist[j] = Math.min(Dist[j], Dist[i] + maps[i][j]);
					totalresult = Math.min(totalresult, Dist[j]);
				}
			}
		}
	}

}