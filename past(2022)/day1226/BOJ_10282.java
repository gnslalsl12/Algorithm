package day1226;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_10282 {
	static int N, D, C;
	static ArrayList<Node>[] maps;
	static long time;
	static int count;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer tokens;
		int T = Integer.parseInt(read.readLine());
		for (int test = 1; test <= T; test++) {
			tokens = new StringTokenizer(read.readLine());
			N = Integer.parseInt(tokens.nextToken());
			D = Integer.parseInt(tokens.nextToken());
			C = Integer.parseInt(tokens.nextToken());
			maps = new ArrayList[N + 1];
			for (int i = 1; i <= N; i++) {
				maps[i] = new ArrayList<>();
			}
			time = 0;
			count = 0;
			for (int i = 0; i < D; i++) {
				tokens = new StringTokenizer(read.readLine());
				int to = Integer.parseInt(tokens.nextToken());
				int from = Integer.parseInt(tokens.nextToken());
				int sec = Integer.parseInt(tokens.nextToken());
				maps[from].add(new Node(to, sec));
			}
			getDIJK(C);
			write.write(count + " " + time + "\n");
		}
		write.close();
	}

	private static void getDIJK(int start) {
		boolean[] visits = new boolean[N + 1];
		PriorityQueue<Node> PQ = new PriorityQueue<>();
		long[] dists = new long[N + 1];
		Arrays.fill(dists, Integer.MAX_VALUE);
		dists[start] = 0;
		PQ.add(new Node(start, 0));
		while (!PQ.isEmpty()) {
			Node temp = PQ.poll();
			if (visits[temp.to])
				continue;
			visits[temp.to] = true;
			time = Math.max(temp.value, time);
			for (Node next : maps[temp.to]) {
				if (visits[next.to])
					continue;
				if (dists[next.to] > temp.value + next.value) {
					dists[next.to] = temp.value + next.value;
					PQ.add(new Node(next.to, dists[next.to]));
				}
			}
		}
		for (int i = 1; i <= N; i++) {
			if (visits[i])
				count++;
		}
	}

	private static class Node implements Comparable<Node> {
		int to;
		long value;

		public Node(int to, long value) {
			super();
			this.to = to;
			this.value = value;
		}

		@Override
		public int compareTo(Node o) {
			return Long.compare(this.value, o.value);
		}

	}

}
