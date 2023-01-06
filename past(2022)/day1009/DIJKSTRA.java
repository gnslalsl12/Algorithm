package day1009;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class DIJKSTRA { // DIJSTRA
	static int N, M;
	static ArrayList<Node>[] NodeList;
	static boolean[] visits;
	static int[] Dist;

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		visits = new boolean[N + 1];
		Dist = new int[N + 1];
		NodeList = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			NodeList[i] = new ArrayList<>();
			Dist[i] = Integer.MAX_VALUE;
		}
		for (int i = 0; i < M; i++) {
			tokens = new StringTokenizer(read.readLine());
			int point = Integer.parseInt(tokens.nextToken());
			int line = Integer.parseInt(tokens.nextToken());
			int value = Integer.parseInt(tokens.nextToken());
			NodeList[point].add(new Node(line, value));
		}

		DIJKSTRA(1);

		for (int i = 1; i <= N; i++) {
			System.out.println(Dist[i] == Integer.MAX_VALUE ? "INF" : Dist[i]);
		}

	}

	static void DIJKSTRA(int startpoint) {
		PriorityQueue<Node> PQ = new PriorityQueue<>();
		PQ.add(new Node(startpoint, 0));
		Dist[startpoint] = 0;
		while (!PQ.isEmpty()) {
			Node temp = PQ.poll();
			if (visits[temp.line])
				continue;
			visits[temp.line] = true;
			for (Node next : NodeList[temp.line]) {
				if (visits[next.line])
					continue;
				if (Dist[next.line] > temp.value + next.value) {
					Dist[next.line] = temp.value + next.value;
					PQ.add(new Node(next.line, Dist[next.line]));
				}
			}
		}
	}

	private static class Node implements Comparable<Node> {
		int line;
		int value;

		public Node(int line, int value) {
			super();
			this.line = line;
			this.value = value;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.value, o.value);
		}

	}
}
