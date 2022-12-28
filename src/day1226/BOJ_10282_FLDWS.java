package day1226;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_10282_FLDWS {
	static int N, D, C, maps[][], count;
	static int INF = Integer.MAX_VALUE;
	static long resulttime;

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer tokens;
		int T = Integer.parseInt(read.readLine());
		for (int test = 1; test <= T; test++) {
			tokens = new StringTokenizer(read.readLine());
			N = Integer.parseInt(tokens.nextToken());
			D = Integer.parseInt(tokens.nextToken());
			C = Integer.parseInt(tokens.nextToken());
			resulttime = 0;
			count = 0;
			maps = new int[N + 1][N + 1];
			for (int i = 1; i <= N; i++) {
				Arrays.fill(maps[i], INF);
			}
			for (int i = 0; i < D; i++) {
				tokens = new StringTokenizer(read.readLine());
				int b = Integer.parseInt(tokens.nextToken());
				int a = Integer.parseInt(tokens.nextToken());
				int s = Integer.parseInt(tokens.nextToken());
				maps[a][b] = s;
			}
			getFLDWS();
			getBFS(C);
			write.write(count + " " + resulttime + " \n");
		}
		write.close();
	}

	private static void getBFS(int start) {
		PriorityQueue<temp> Q = new PriorityQueue<>();
		Q.add(new temp(start, 0));
		boolean visits[] = new boolean[N + 1];
		while (!Q.isEmpty()) {
			temp pop = Q.poll();
			if (visits[pop.to])
				continue;
			visits[pop.to] = true;
			resulttime += pop.value;
			count++;
			for (int i = 1; i <= N; i++) {
				if (i == pop.to || maps[pop.to][i] == INF)
					continue;
				Q.add(new temp(i, maps[pop.to][i]));
			}
		}
	}

	private static void getFLDWS() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (i == j)
					continue;
				for (int k = 1; k <= N; k++) {
					if (i == k || j == k)
						continue;
					if (maps[i][k] != INF && maps[k][j] != INF) {
						maps[i][j] = Math.min(maps[i][j], maps[i][k] + maps[k][j]);
					}
				}
			}
		}
	}

	private static class temp implements Comparable<temp> {
		int to;
		int value;

		public temp(int to, int value) {
			this.to = to;
			this.value = value;
		}

		@Override
		public int compareTo(temp o) {
			return Integer.compare(this.value, o.value);
		}

	}

}
