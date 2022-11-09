package day1108;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1389 {
	static int N, M;
	static boolean[][] maps;
	static int mincount, result;

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		maps = new boolean[N + 1][N + 1];
		result = 0;
		mincount = Integer.MAX_VALUE;
		for (int i = 0; i < M; i++) {
			tokens = new StringTokenizer(read.readLine());
			int a = Integer.parseInt(tokens.nextToken());
			int b = Integer.parseInt(tokens.nextToken());
			maps[a][b] = maps[b][a] = true;
		}
		for (int i = 1; i <= N; i++) {
			bfs(i);
		}
		System.out.println(result);
	}

	private static void bfs(int startpoint) {
		System.out.println("애 : " + startpoint);
		Queue<Integer> BFSQ = new LinkedList<Integer>();
		boolean[] visits = new boolean[N + 1];
		BFSQ.add(startpoint);
		int tempcount = 0;
		int size = BFSQ.size();
		while (true) {

			for (int tc = 0; tc < size; tc++) {
				int temp = BFSQ.poll();
				if (visits[temp])
					continue;
				visits[temp] = true;
				System.out.println("돈다!");
				for (int i = 1; i <= N; i++) {
					if (i == temp || !maps[i][temp])
						continue;
					BFSQ.add(i);
				}
			}
			tempcount++;
			size = BFSQ.size();
			if(size == 0) break;
		}
		System.out.println("tempcount : " + tempcount);
		if (tempcount < mincount) {
			result = startpoint;
		} else if (tempcount == mincount) {
			result = Math.min(result, startpoint);
		}
	}

}
