package day1227;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1647 { // KRUSKAL
	static int N, M;
	static int[] parents;

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		parents = new int[N + 1];
		for (int i = 0; i <= N; i++) {
			parents[i] = i;
		}
		PriorityQueue<Node> PQ = new PriorityQueue<>();
		for (int i = 0; i < M; i++) {
			tokens = new StringTokenizer(read.readLine());
			int a = Integer.parseInt(tokens.nextToken());
			int b = Integer.parseInt(tokens.nextToken());
			int v = Integer.parseInt(tokens.nextToken());
			PQ.add(new Node(a, b, v));
		}
		N--;
		int totalvalue = 0;
		while (!PQ.isEmpty()) {
			Node temp = PQ.poll();
			int tempa = temp.A;
			int tempb = temp.B;
			int tempvalue = temp.value;
			if (isSameParents(tempa, tempb)) {
				continue;
			} else {
				N--;
				union(tempa, tempb);
				totalvalue += tempvalue;
			}
			if (N == 0) {
				totalvalue -= tempvalue;
				break;
			}
		}
		System.out.println(totalvalue);
	}

	private static int findParents(int x) {
		if (parents[x] == x)
			return x;
		return parents[x] = findParents(parents[x]);
	}

	private static boolean isSameParents(int a, int b) {
		int pa = findParents(a);
		int pb = findParents(b);
		if (pa == pb)
			return true;
		return false;
	}

	private static void union(int a, int b) {
		int pa = findParents(a);
		int pb = findParents(b);
		if (pa > pb) {
			parents[pa] = pb;
		} else {
			parents[pb] = pa;
		}
	}

	private static class Node implements Comparable<Node> {
		int A;
		int B;
		int value;

		public Node(int A, int B, int value) {
			this.A = A;
			this.B = B;
			this.value = value;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.value, o.value);
		}

	}

}
