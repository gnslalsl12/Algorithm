package day1007;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_사람네트워크2 {
	static int T;
	static int N;
	static int[][] graph;
	static int MIN;
	static int found;

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		T = Integer.parseInt(tokens.nextToken());
		for (int test = 1; test <= T; test++) {
			tokens = new StringTokenizer(read.readLine());
			N = Integer.parseInt(tokens.nextToken());
			graph = new int[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					graph[i][j] = Integer.parseInt(tokens.nextToken());
				}
			}
			MIN = Integer.MAX_VALUE;
			for (int n = 0; n < N; n++) {
				bfs(n);
			}
			sb.append(String.format("#%d %d%n", test, MIN));
		}
		System.out.print(sb);
	}

	static void bfs(int start) {
		Queue<Integer> BFSQ = new LinkedList<>();
		boolean[] visited = new boolean[N];
		BFSQ.offer(start);
		visited[start] = true;

		int dist = 1;
		int distSum = 0; // 각 노드를 만난 거리의 누적
		int found = 1;
		while (!BFSQ.isEmpty()) {
			int size = BFSQ.size();
			while (size-- > 0) {
				Integer head = BFSQ.poll();
				for (int c = 0; c < N; c++) {
					if (graph[head][c] == 1 && !visited[c]) { // 연결돼있고 방문 전이면
						BFSQ.offer(c);
						visited[c] = true;
						distSum += dist;
						found++;
						if(found == N) {
							MIN = Math.min(MIN, distSum);
							return;
						}
					}
				}

			}
			dist++;
		}
	}
}