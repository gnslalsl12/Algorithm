package CodePractice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class testBlank2 {
	static int N;
	static int[] parent;

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		tokens = new StringTokenizer(read.readLine());
		int M = Integer.parseInt(tokens.nextToken());
		PriorityQueue<Node> NodeList = new PriorityQueue<>();
		parent = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			parent[i] = i;
		}
		for (int i = 0; i < M; i++) {
			tokens = new StringTokenizer(read.readLine());
			int to = Integer.parseInt(tokens.nextToken());
			int from = Integer.parseInt(tokens.nextToken());
			int value = Integer.parseInt(tokens.nextToken());
			NodeList.add(new Node(to, from, value));
		}
		int totalvalue = 0;
		while (!NodeList.isEmpty()) {
			Node temp = NodeList.poll();
			int x = temp.A;
			int y = temp.B;
			if (isDiffParent(x, y)) {
				totalvalue += temp.value;
				union(x, y);
			}
		}
		System.out.println(totalvalue);

	}

	private static boolean isDiffParent(int x, int y) {
		return find(x) != find(y);
	}

	private static void union(int x, int y) {
		if (find(x) < find(y)) {
			parent[find(y)] = find(x);
		} else {
			parent[find(x)] = find(y);
		}
	}

	private static int find(int x) {
		if (parent[x] == x)
			return x;
		return parent[x] = find(parent[x]);
	}

	private static class Node implements Comparable<Node> {
		int A;
		int B;
		int value;

		public Node(int to, int from, int value) {
			super();
			this.A = to;
			this.B = from;
			this.value = value;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.value, o.value);
		}

	}
}
