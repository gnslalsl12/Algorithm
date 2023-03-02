package day1014;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_1167 {
	static ArrayList<Node>[] NodeList;
	static int N;
	static int result = Integer.MIN_VALUE;
	static int [] startdist;

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		startdist = new int [N+1];
		NodeList = new ArrayList[N + 1];
		for (int i = 0; i <= N; i++) {
			NodeList[i] = new ArrayList<>();
		}
		for (int i = 0; i < N; i++) {
			tokens = new StringTokenizer(read.readLine());
			int nodefrom = Integer.parseInt(tokens.nextToken());
			while (true) {
				int to = Integer.parseInt(tokens.nextToken());
				if (to == -1)
					break;
				int dist = Integer.parseInt(tokens.nextToken());
				NodeList[nodefrom].add(new Node(to, (dist)));
			}
		}
		for (int i = 1; i <= N; i++) {
			prim(i);
		}
		System.out.println(result);
		System.out.println(Arrays.toString(startdist));
	}

	private static void prim(int startpoint) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(startpoint, 0));
		boolean[] visited = new boolean[N + 1];
		int tempresult = 0;
		while (!pq.isEmpty()) {
			Node temp = pq.poll();
			if (visited[temp.to])
				continue;
			visited[temp.to] = true;
			tempresult += temp.dist;
			pq = new PriorityQueue<>();
			for (Node pop : NodeList[temp.to]) {
				if (!visited[pop.to]) {
					pq.add(pop);
				}
			}
		}
		startdist[startpoint] = tempresult;
		result = Math.max(tempresult, result);
	}
	
	private static void dfs(int startpoitn) {
		Stack<Node> dfss = new Stack<>();
		
	}

	private static class Node implements Comparable<Node> {
		int to;
		int dist;

		public Node(int to, int dist) {
			super();
			this.to = to;
			this.dist = dist;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(o.dist, this.dist);
		}

		@Override
		public String toString() {
			return "Node [to=" + to + ", dist=" + dist + "]";
		}

	}

}
