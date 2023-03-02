package day1008;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class PRIMtest {
	static int total;
	static List<Node>[] bridgelist;
	static boolean[] visited;
	static int N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		tokens = new StringTokenizer(read.readLine());
		M = Integer.parseInt(tokens.nextToken());
		total = 0;
		bridgelist = new ArrayList[N + 1];
		visited = new boolean[N + 1];
		for (int i = 1; i <= N; i++) {
			bridgelist[i] = new ArrayList<>();
		}
		for (int i = 0; i < M; i++) {
			tokens = new StringTokenizer(read.readLine());
			int a = Integer.parseInt(tokens.nextToken());
			int b = Integer.parseInt(tokens.nextToken());
			int value = Integer.parseInt(tokens.nextToken());
			bridgelist[a].add(new Node(b, value));
			bridgelist[b].add(new Node(a, value));

		}
		prim(6);
		System.out.println(total);

	}

	static void prim(int startpoint) {
		PriorityQueue<Node> primPQ = new PriorityQueue<>();
		primPQ.add(new Node(startpoint, 0));
		while(!primPQ.isEmpty()) {
			Node temp = primPQ.poll();
			int nodeTo = temp.to;
			int nodeValue = temp.value;
			if(visited[nodeTo]) continue;
			visited[nodeTo] = true;
			total += nodeValue;
			for(Node nextpoint : bridgelist[nodeTo]) {	//목적지에서 나아갈 수 있는 그 목적지 하나씩
				if(!visited[nextpoint.to]) {
					primPQ.add(nextpoint);
				}
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
