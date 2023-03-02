package day0130;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_1240_노드사이의_거리 {
	static int N, M, INF = Integer.MAX_VALUE;
	static int[][] Dist;
	static int[][] DistRev;

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		Dist = new int[N + 1][2];
		DistRev = new int[N + 1][2];
		for (int i = 1; i < N; i++) {
			tokens = new StringTokenizer(read.readLine());
			int a = Integer.parseInt(tokens.nextToken());
			int b = Integer.parseInt(tokens.nextToken());
			int d = Integer.parseInt(tokens.nextToken());
			if (Dist[a][0] == 0) {
				Dist[a][0] = b;
				Dist[a][1] = d;
			} else {
				Dist[b][0] = a;
				Dist[b][1] = d;
			}
			if (DistRev[b][0] == 0) {
				DistRev[b][0] = a;
				DistRev[b][1] = d;
			} else {
				DistRev[a][0] = b;
				DistRev[a][1] = d;
			}
		}
		for (int i = 0; i < M; i++) {
			tokens = new StringTokenizer(read.readLine());
			int a = Integer.parseInt(tokens.nextToken());
			int b = Integer.parseInt(tokens.nextToken());
			int temp = get(a, b);
			write.write(temp + "\n");
		}
		write.close();
		read.close();
	}

	private static int get(int from, int to) {
		if (from == to)
			return 0;
		int result = 0;
		int next = from;
		boolean[] vis = new boolean[N + 1];
		vis[next] = true;
		while (true) {
			result += Dist[next][1];
			next = Dist[next][0];
			if (to == next)
				return result;
			if (vis[next])
				break;
			vis[next] = true;
		}
		result = 0;
		next = from;
		vis = new boolean[N + 1];
		vis[next] = true;
		while (true) {
			result += DistRev[next][1];
			next = DistRev[next][0];
			if (to == next)
				return result;
			if (vis[next])
				break;
			vis[next] = true;
		}
		return -1;
	}

}
