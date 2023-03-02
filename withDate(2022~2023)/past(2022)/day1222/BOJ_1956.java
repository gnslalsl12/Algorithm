package day1222;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1956 {
	static int V, E;
	static long result;
	static ArrayList<Node>[] NodeList;

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		V = Integer.parseInt(tokens.nextToken());
		E = Integer.parseInt(tokens.nextToken());
		NodeList = new ArrayList[V + 1];
		for (int i = 1; i <= V; i++) {
			NodeList[i] = new ArrayList<>();
		}
		for (int i = 0; i < E; i++) {
			tokens = new StringTokenizer(read.readLine());
			int from = Integer.parseInt(tokens.nextToken());
			int to = Integer.parseInt(tokens.nextToken());
			int value = Integer.parseInt(tokens.nextToken());
			NodeList[from].add(new Node(to, value));
		}
		for (int i = 1; i <= V; i++) {
			Collections.sort(NodeList[i]);
		}
		result = Integer.MAX_VALUE;
		for (int i = 1; i <= V; i++) {
			getPRIM(i);
		}
		if (result == Integer.MAX_VALUE || E == 0)
			System.out.println(-1);
		else
			System.out.println(result);
	}

	private static void getPRIM(int startpoint) {
		boolean[] visits = new boolean[V + 1];
		PriorityQueue<Node> PQ = new PriorityQueue<>();
		PQ.add(new Node(startpoint, 0));
		long totalvalue = 0;
		while (!PQ.isEmpty()) {
			Node temp = PQ.poll();
			totalvalue += temp.value;
			visits[temp.dest] = true;
			for (Node next : NodeList[temp.dest]) {
				if (visits[next.dest]) { // 방문한 곳을 또 방문 (서클 생성됨)
//					if (next.dest == startpoint) { // 시작점으로 서클생성됨 => 원하는 거
						result = Math.min(result, totalvalue + next.value);
//					}
					return;
				}
				PQ.add(next);
			}
		}
	}

	private static class Node implements Comparable<Node> {
		int dest;
		int value;

		public Node(int dest, int value) {
			this.dest = dest;
			this.value = value;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.value, o.value);
		}

	}

}
