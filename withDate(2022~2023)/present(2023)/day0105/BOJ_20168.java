package day0105;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_20168 {
	static int N, M, A, B, C;
	static ArrayList<Node>[] NodeList;

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		A = Integer.parseInt(tokens.nextToken());
		B = Integer.parseInt(tokens.nextToken());
		C = Integer.parseInt(tokens.nextToken());
		NodeList = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++)
			NodeList[i] = new ArrayList<>();
		for (int i = 0; i < M; i++) {
			tokens = new StringTokenizer(read.readLine());
			int a = Integer.parseInt(tokens.nextToken());
			int b = Integer.parseInt(tokens.nextToken());
			int v = Integer.parseInt(tokens.nextToken());
			NodeList[a].add(new Node(b, v, v));
			NodeList[b].add(new Node(a, v, v));
		}
		write.write(getDIJK(A) + "\n");
		write.close();
		read.close();
	}

	private static int getDIJK(int start) {
		PriorityQueue<Node> PQ = new PriorityQueue<>();
		PQ.add(new Node(start, 0, 0));
		int result = Integer.MAX_VALUE;
		while (!PQ.isEmpty()) {
			Node temp = PQ.poll();
			for (Node next : NodeList[temp.to]) {
				int tempsum = temp.value + next.value;
				if (tempsum > C || temp.vis[next.to])
					continue;
				if (next.to == B) {
					result = Math.min(Math.max(temp.maxvalue, next.maxvalue), result);
					continue;
				} else {
					PQ.add(new Node(next.to, tempsum, Math.max(temp.maxvalue, next.maxvalue), temp.vis.clone()));
				}
			}
		}
		if (result == Integer.MAX_VALUE)
			result = -1;
		return result;
	}

	private static class Node implements Comparable<Node> {
		int to;
		int value;
		int maxvalue;
		boolean[] vis;

		public Node(int to, int value, int maxvalue) {
			this.to = to;
			this.value = value;
			this.maxvalue = maxvalue;
			this.vis = new boolean[N + 1];
			this.vis[to] = true;
		}

		public Node(int to, int value, int maxvalue, boolean[] vis) {
			this.to = to;
			this.value = value;
			this.maxvalue = maxvalue;
			this.vis = vis;
			this.vis[to] = true;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(o.value, this.value);
		}

	}

}
