package day1007;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_사람네트워크2_FLW {
	static int T;
	static int N;
	static int[][] graph;
	static int MIN;
	static int INF = 987654321; // 곱하기 2 해도 int 범위를 벗어나지 않는 큰 수 (무한대 처리용)

	public static void main(String[] args) throws IOException {
		/*
		 * floyd는 비연결 노드에서 무한대 처리 해줘야함
		 *///
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
					if (graph[i][j] == 0)
						graph[i][j] = INF;
				}
			}
			MIN = Integer.MAX_VALUE;
			floyd();
			sb.append(String.format("#%d %d%n", test, MIN));

		}
		System.out.print(sb);
		

	}

	static void floyd() {
		for (int v = 0; v < N; v++) {
			for (int s = 0; s < N; s++) {
				for (int e = 0; e < N; e++) {
					// 출발지 -> 도착지 비용이 출발지 -> 경유지 + 경유지 + 도착지 보다 크면
					// 경유지를 갔다오는 게 유리
					if(graph[s][e] > graph[s][v] + graph[v][e]) {//도착지까지 바로 가는 게 더 크다
						graph[s][e] = graph[s][v] + graph[v][e];	//작은 값으로 갱신해줌
					}
					

				}
			}
		}
//		for(int [] row : graph) {
//			System.out.println(Arrays.toString(row));
//		}
		for(int i = 0; i < N; i++) {
			int sum = 0;
			for(int j = 0; j < N; j++) {
				if(i != j) {	//self는 제외
					sum += graph[i][j];
				}
				
			}
			MIN = Math.min(MIN, sum);
		}

	}
}