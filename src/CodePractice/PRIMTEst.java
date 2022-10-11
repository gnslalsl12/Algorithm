package CodePractice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class PRIMTEst {
	static int N, M;
	static int totalresult = 0;
	static ArrayList<Node>[] BridgeList;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		tokens = new StringTokenizer(read.readLine());
		M = Integer.parseInt(tokens.nextToken());
		visited = new boolean[N + 1];
		BridgeList = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			BridgeList[i] = new ArrayList<>();
		}
		for (int i = 0; i < M; i++) {
			tokens = new StringTokenizer(read.readLine());
			int a = Integer.parseInt(tokens.nextToken());
			int b = Integer.parseInt(tokens.nextToken());
			int value = Integer.parseInt(tokens.nextToken());
			BridgeList[a].add(new Node(b, value));
			BridgeList[b].add(new Node(a, value));
		}
		prim(1);
		System.out.println(totalresult);
		
	}

	private static void prim(int startpoint) {
		PriorityQueue<Node> PQ = new PriorityQueue<>();
		PQ.add(new Node(startpoint, 0));
		while (!PQ.isEmpty()) {
			Node temp = PQ.poll();
			int nodeTo = temp.to;
			if (visited[nodeTo])
				continue;
			visited[nodeTo] = true;
			totalresult += temp.value;
			for (Node nextpoint : BridgeList[nodeTo]) {
				if (visited[nextpoint.to])
					continue;
				PQ.add(nextpoint);
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
