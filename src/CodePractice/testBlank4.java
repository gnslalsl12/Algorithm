package CodePractice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class testBlank4 { // PRIM
	static int totalvalue;
	static ArrayList<Node>[] BridgeList;
	static boolean[] visited;
	static int N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		tokens = new StringTokenizer(read.readLine());
		M = Integer.parseInt(tokens.nextToken());
		totalvalue = 0;
		BridgeList = new ArrayList[N + 1];
		visited = new boolean[N + 1];
		for(int i = 1; i <= N; i++) {
			BridgeList[i] = new ArrayList<>();
		}
		int A = 0;
		for (int i = 0; i < M; i++) {
			tokens = new StringTokenizer(read.readLine());
			A = Integer.parseInt(tokens.nextToken());
			int B = Integer.parseInt(tokens.nextToken());
			int value = Integer.parseInt(tokens.nextToken());
			BridgeList[A].add(new Node(B, value));
			BridgeList[B].add(new Node(A, value));
		}
		prim(6);
		System.out.println(totalvalue);
	}

	private static void prim(int nodepoint) {
		PriorityQueue<Node> PrimPQ = new PriorityQueue<>();
		PrimPQ.add(new Node(nodepoint, 0));
		while (!PrimPQ.isEmpty()) {
			Node temp = PrimPQ.poll();
			int tonode = temp.to;
			if (visited[tonode])
				continue;
			visited[tonode] = true;
			totalvalue += temp.value;
			for(Node avail : BridgeList[tonode]) {
				if(visited[avail.to]) continue;
				PrimPQ.add(avail);
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

		@Override
		public String toString() {
			return " [to=" + to + ", value=" + value + "]";
		}

	}
}
