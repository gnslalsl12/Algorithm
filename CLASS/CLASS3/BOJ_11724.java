package SolvedAc.CLASS3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_11724 {
	static int N, M;
	static boolean[][] maps;
	static int count;
	static boolean[] visits;

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		maps = new boolean[N + 1][N + 1];
		visits = new boolean [N+1];
		for (int i = 0; i < M; i++) {
			tokens = new StringTokenizer(read.readLine());
			int a = Integer.parseInt(tokens.nextToken());
			int b = Integer.parseInt(tokens.nextToken());
			maps[a][b] = maps[b][a] = true;
		}
		for (int i = 1; i <= N; i++) {
			if (visits[i])
				continue;
			BFS(i);
		}
		System.out.println(count);
	}

	private static void BFS(int startpoint) {
		Queue<Integer> BFSQ = new LinkedList<>();
		BFSQ.add(startpoint);
		visits[startpoint] = true;
		while (!BFSQ.isEmpty()) {
			int temp = BFSQ.poll();
			for (int i = 1; i <= N; i++) {
				if (i == temp || visits[i])
					continue;
				if (maps[i][temp]) {
					BFSQ.add(i);
					visits[i] = true;
				}
			}
		}
		count++;
	}
}
