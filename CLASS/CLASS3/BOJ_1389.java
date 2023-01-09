package SolvedAc.CLASS3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1389 {
	static int N, M;
	static boolean[][] maps;
	static int resultone, min;

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		maps = new boolean[N + 1][N + 1];
		resultone = 0;
		min = Integer.MAX_VALUE;
		for (int i = 0; i < M; i++) {
			tokens = new StringTokenizer(read.readLine());
			int a = Integer.parseInt(tokens.nextToken());
			int b = Integer.parseInt(tokens.nextToken());
			maps[a][b] = maps[b][a] = true;
		}
		for (int i = 1; i <= N; i++) {
			bfs(i);
		}
		System.out.println(resultone);
	}

	private static void bfs(int startpoint) {
		Queue<Integer> BFSQ = new LinkedList<Integer>();
		BFSQ.add(startpoint);
		boolean[] visits = new boolean[N + 1];
		visits[startpoint] = true;
		int count = 0;
		int line = 1;
		int size = BFSQ.size();
		while (true) {
			if (size == 0)
				break;
			for (int i = 0; i < size; i++) {
				int temp = BFSQ.poll();
				for (int n = 1; n <= N; n++) {
					if (temp == n || visits[n])
						continue;
					if (maps[temp][n]) {
						visits[n] = true;
						BFSQ.add(n);
					}
				}
			}
			size = BFSQ.size();
			count += (line++) * size;
		}
		if (count < min) {
			min = count;
			resultone = startpoint;
		} else if (count == min) {
			resultone = Math.min(resultone, startpoint);
		}

	}

}
