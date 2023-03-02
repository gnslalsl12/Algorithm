package day1016;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1504_trash { // 정점이 적으니 prim
	static int N;
	static ArrayList<Node>[] NodeList;

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		int E = Integer.parseInt(tokens.nextToken());
		NodeList = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			NodeList[i] = new ArrayList<>();
		}
		for (int i = 0; i < E; i++) {
			tokens = new StringTokenizer(read.readLine());
			int from = Integer.parseInt(tokens.nextToken());
			int to = Integer.parseInt(tokens.nextToken());
			int value = Integer.parseInt(tokens.nextToken());
			NodeList[from].add(new Node(to, value));
			NodeList[to].add(new Node(from, value));
		}
		tokens = new StringTokenizer(read.readLine());
		int v1 = Integer.parseInt(tokens.nextToken());
		int v2 = Integer.parseInt(tokens.nextToken());
		// inputing
		int fvtv = prim(v1, v2);
		int tov1 = prim(1, v1);
		int tov2 = prim(1, v2);
		int endfromv1 = prim(v1, N);
		int endfromv2 = prim(v2, N);
		int v1case = tov1 + fvtv + endfromv2;
		int v2case = tov2 + fvtv+ endfromv1;
		if(fvtv == -1 || (tov1 == -1 && tov2 == -1) || (endfromv1 == -1 && endfromv2 == -1)) System.out.println(-1);
		else System.out.println(Math.min(v1case, v2case));
	}

	private static int prim(int startpoint, int endpoint) {
		PriorityQueue<Node> ppq = new PriorityQueue<>();
		ppq.add(new Node(startpoint, 0));
		boolean[] visited = new boolean[N + 1];
		int dist = 0;
		if (startpoint == endpoint)
			return 0;
		boolean done = false;
		breakall: while (!ppq.isEmpty()) {
			Node temp = ppq.poll();
			if (visited[temp.to])
				continue;
			visited[temp.to] = true;
			dist += temp.value;
			if (temp.to == endpoint)
				break;
			for (Node pop : NodeList[temp.to]) {
				if (pop.to == endpoint) {
					done = true;
					dist += pop.value;
					break breakall;
				}
				if (visited[pop.to])
					continue;
				ppq.add(pop);
			}
		}
		if (!done)
			return -1;
		return dist;
	}

	private static class Node implements Comparable<Node> {
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