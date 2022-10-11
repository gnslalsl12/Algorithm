package CodePractice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class testBlank9 {
	static int N;
	static int[] parent;
/*
7
9
1 2 29
1 5 75
2 3 35
2 6 34
3 4 7
4 6 23
4 7 13
5 6 53
6 7 25
*/
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
			int from = Integer.parseInt(tokens.nextToken());
			int to = Integer.parseInt(tokens.nextToken());
			int value = Integer.parseInt(tokens.nextToken());
			NodeList.add(new Node(from, to, value));
		}
		int size = NodeList.size();
		int totalvalue = 0;
		for(int i = 0; i < size; i++) {
			Node tempNode = NodeList.poll();
			int from = tempNode.from;
			int to = tempNode.to;
			if(!isSameParent(from, to)) {
				totalvalue+= tempNode.value;
				union(from, to);
			}
		}
		System.out.println(totalvalue);

	}
	
	private static void union(int x, int y) {
		if(find(x) < find(y)) {
			parent[find(y)] = find(x);
		}else {
			parent[find(x)] = find(y);
		}
	}
	

	private static boolean isSameParent(int x, int y) {
		return parent[x] == parent[y];
	}

	private static int find(int x) {
		if (parent[x] == x)
			return x;
		return parent[x] = find(parent[x]);
	}

	private static class Node implements Comparable<Node> {
		int from;
		int to;
		int value;

		public Node(int from, int to, int value) {
			super();
			this.from = from;
			this.to = to;
			this.value = value;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.value, o.value);
		}

	}

}
