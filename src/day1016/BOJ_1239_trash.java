package day1016;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1239_trash {
	static int N, M, X;
	static ArrayList<Node>[] NodeList;
	static int[] dists;

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		NodeList = new ArrayList[N + 1];
		dists = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			NodeList[i] = new ArrayList<>();
		}
		M = Integer.parseInt(tokens.nextToken());
		X = Integer.parseInt(tokens.nextToken());
		for (int i = 0; i < M; i++) {
			tokens = new StringTokenizer(read.readLine());
			int from = Integer.parseInt(tokens.nextToken());
			int to = Integer.parseInt(tokens.nextToken());
			int value = Integer.parseInt(tokens.nextToken());
			NodeList[from].add(new Node(to, value));
		}
		int result = -1;
		for (int i = 1; i <= N; i++) {
//			System.out.println(i + " 에서의 경로");
//			prim(i, X, false);
//			prim(X, i, true);
			DFS(i, i, X, 0, false, new boolean[N + 1]);
//			System.out.println(Arrays.toString(dists));
			DFS(X, X, i, 0, true, new boolean[N + 1]);
//			System.out.println(Arrays.toString(dists));
			result = Math.max(result, dists[i]);
		}
		System.out.println(result);
	}

	private static void DFS(int firstpoint, int startpoint, int endpoint, int tempdist, boolean back, boolean[] visited) {
		visited[startpoint] = true;
		if (startpoint == endpoint) {
			if (!back) {
				dists[firstpoint] += tempdist;
			} else {
				dists[endpoint] += tempdist;
			}
			return;
		}

		for (Node pop : NodeList[startpoint]) {
			if (visited[pop.to])
				continue;
			DFS(firstpoint, pop.to, endpoint, tempdist + pop.value, back, visited);
		}
	}

	private static void prim(int startpoint, int endpoint, boolean back) {
		PriorityQueue<Node> PrimP = new PriorityQueue<>();
		int dist = 0;
		boolean[] visited = new boolean[N + 1];
		PrimP.offer(new Node(startpoint, 0));
		while (!PrimP.isEmpty()) {
			Node temp = PrimP.poll();
			if (visited[temp.to])
				continue;
			dist += temp.value;
			visited[temp.to] = true;
			if (temp.to == endpoint) {
				if (back) {
					dists[endpoint] += dist;
				} else {
					dists[startpoint] += dist;
				}
				return;
			}
			for (Node pop : NodeList[temp.to]) {
				if (visited[pop.to])
					continue;
				PrimP.offer(pop);
			}
		}
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
