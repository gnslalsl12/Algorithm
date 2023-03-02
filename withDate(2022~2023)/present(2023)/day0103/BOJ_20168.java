package day0103;

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
	static int[][] maps;
	static ArrayList<Node>[] NodeList;
	static int result;

	public static void main(String[] args) throws IOException { // 총 많이 내는데 개당 작은 거
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		A = Integer.parseInt(tokens.nextToken());
		B = Integer.parseInt(tokens.nextToken());
		C = Integer.parseInt(tokens.nextToken());
		NodeList = new ArrayList[N + 1];
		result = Integer.MAX_VALUE;
		for (int i = 1; i <= N; i++) {
			NodeList[i] = new ArrayList<>();
		}
		for (int i = 0; i < M; i++) {
			tokens = new StringTokenizer(read.readLine());
			int a = Integer.parseInt(tokens.nextToken());
			int b = Integer.parseInt(tokens.nextToken());
			int v = Integer.parseInt(tokens.nextToken());
			NodeList[a].add(new Node(b, v));
			NodeList[b].add(new Node(a, v));
		}
		int result = getDIJK(A);
		if (result == Integer.MAX_VALUE)
			result = -1;
		if (N == 1)
			result = 0;
		write.write(result + "\n");
		write.close();
		read.close();
	} 

	private static int getDIJK(int start) { // 경로 중 가장 큰 비용이 전체 모든 경로에서 가장 작은 경로의 최대 비용
		int[][] Dist = new int[N + 1][2]; // 0은 거기까지 가는데 총 비용, 1은 그 경로 중 최댓값
		PriorityQueue<Node> PQ = new PriorityQueue<>();
		PQ.add(new Node(start, 0));
		for (int i = 1; i <= N; i++) {
			Dist[i][0] = Integer.MAX_VALUE;
			Dist[i][1] = Integer.MAX_VALUE;
		}
		Dist[start][0] = 0;
//		Dist[B][1] = Integer.MAX_VALUE;
		while (!PQ.isEmpty()) {
			Node temp = PQ.poll();
			for (Node next : NodeList[temp.to]) {
				int tempsum = temp.value + next.value;
				if (next.to == start || tempsum > C)
					continue;
				
			}
		}
//		for (int[] t : Dist) {
//			System.out.println(Arrays.toString(t));
//		}
		return Dist[B][1];
	}

	private static class Node implements Comparable<Node> {
		int to;
		int value;

		public Node(int to, int value) {
			this.to = to;
			this.value = value;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.value, o.value);
		}

	}

}
