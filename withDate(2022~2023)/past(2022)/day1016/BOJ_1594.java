package day1016;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1594 {
	static int N, E;
	static ArrayList<Node>[] NodeList;
	static boolean found = false;
	static int[][] results = new int[4][4];
	static int INF = Integer.MAX_VALUE;
	static int done = 0;
	static int result = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		E = Integer.parseInt(tokens.nextToken());
		NodeList = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			NodeList[i] = new ArrayList<>();
		}
		for (int i = 0; i < E; i++) {
			tokens = new StringTokenizer(read.readLine());
			int a = Integer.parseInt(tokens.nextToken());
			int b = Integer.parseInt(tokens.nextToken());
			int value = Integer.parseInt(tokens.nextToken());
			NodeList[a].add(new Node(b, value));
			NodeList[b].add(new Node(a, value));
		}
		tokens = new StringTokenizer(read.readLine());
		int v1 = Integer.parseInt(tokens.nextToken());
		int v2 = Integer.parseInt(tokens.nextToken());
		for (int i = 0; i < 4; i++) {
			Arrays.fill(results[i], INF);
		}
		found = false;
		DFS(v1, v2, 0, new boolean[N + 1], 1, 2);
		found = false;
		DFS(1, v1, 0, new boolean[N + 1], 0, 1);
		found = false;
		DFS(1, v2, 0, new boolean[N + 1], 0, 2);
		found = false;
		DFS(v1, N, 0, new boolean[N + 1], 1, 3);
		found = false;
		DFS(v2, N, 0, new boolean[N + 1], 2, 3);
		
//		print();
		prim(0,3);
		System.out.println(result);

	}
	
	private static void print() {
		System.out.println();
		for(int i = 0; i < 4; i++) {
			for(int j = 0 ; j< 4; j++) {
				if(results[i][j] == INF) System.out.printf("& ");
				else System.out.printf("%d ", results[i][j]);
			}
			System.out.println();
		}
	}
	
	private static void prim(int startpoint, int endpoint) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(startpoint, 0));
		boolean [] visited = new boolean [4];
		int dist = 0;
		while(!pq.isEmpty()) {
			Node temp = pq.poll();
			if(visited[temp.to])continue;
			visited[temp.to] = true;
			dist += temp.value;
			if(temp.to == endpoint) {
				result = dist;
				return;
			}
			for(int dest = 0; dest < 4; dest++) {
				if(dest == temp.to) continue;
				if(visited[dest]) continue;
				pq.offer(new Node(dest, results[temp.to][dest]));
			}
		}
		
		
	}

	private static void Floyd() {
		for (int k = 0; k < 4; k++) {
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					if(results[i][k] != INF && results[k][j] != INF) {
						results[i][j] = results[i][k] + results[k][j];
					}
				}
			}
		}
	}

	private static void DFS(int startpoint, int endpoint, int tempvalue, boolean[] visited, int rindex1, int rindex2) {
		if (startpoint == endpoint) {
			results[rindex1][rindex2] = tempvalue;
			results[rindex2][rindex1] = tempvalue;
			found = true;
			return;
		}
		if (found) {
			return;
		}
		for (Node pop : NodeList[startpoint]) {
			if (visited[pop.to])
				continue;
			visited[pop.to] = true;
			DFS(pop.to, endpoint, tempvalue + pop.value, visited, rindex1, rindex2);
			visited[pop.to] = false;
		}
	}

	private static class Node implements Comparable<Node>{
		int to;
		int value;

		public Node(int to, int value) {
			super();
			this.to = to;
			this.value = value;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.value, o.value);
		}

	}

}
