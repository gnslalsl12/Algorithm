package CodePractice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class testBlank3 { // KRUSKAL Pract
	static int N;
	static int parent[];

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		tokens = new StringTokenizer(read.readLine());
		int M = Integer.parseInt(tokens.nextToken());
		PriorityQueue<Node> NodeList = new PriorityQueue<>();
		for(int i = 0; i < M; i++) {
			tokens = new StringTokenizer(read.readLine());
			int A = Integer.parseInt(tokens.nextToken());
			int B = Integer.parseInt(tokens.nextToken());
			int Value = Integer.parseInt(tokens.nextToken());
			NodeList.add(new Node(A, B, Value));
		}
		parent = new int [N+1];
		for(int i = 1; i <= N; i++) {
			parent[i] = i;
		}
		int totalValue = 0;
		while(!NodeList.isEmpty()) {
			Node temp = NodeList.poll();
			int Aprt = find(temp.A);
			int Bprt = find(temp.B);
			if(Aprt != Bprt) {
				totalValue += temp.Value;
				union(Aprt, Bprt);
			}
		}
		System.out.println(totalValue);

	}
	
	private static void union(int Aprt, int Bprt) {
		if(Aprt < Bprt) {
			parent[Aprt] = Bprt;
		}else {
			parent[Bprt] = Aprt;
		}
	}
	
	private static int find(int x) {
		if(parent[x] == x) return x;
		return parent[x] = find(parent[x]);
	}

	private static class Node implements Comparable<Node>{
		int A;
		int B;
		int Value;
		public Node(int A, int B, int Value) {
			super();
			this.A = A;
			this.B = B;
			this.Value = Value;
		}
		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.Value, o.Value);
		}
		
	}
}
