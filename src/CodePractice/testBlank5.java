package CodePractice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class testBlank5 {	//PRIM
	static int N, M;
	static int totalvalue;
	static ArrayList<Node> [] BridgeList;
	static boolean [] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		tokens = new StringTokenizer(read.readLine());
		M = Integer.parseInt(tokens.nextToken());
		visited = new boolean[N+1];
		BridgeList = new ArrayList[N+1];
		for(int i = 1; i <= N; i++) {
			BridgeList[i] = new ArrayList<>();
		}
		int A = 0;
		for(int i = 0; i < M; i++) {
			tokens = new StringTokenizer(read.readLine());
			A = Integer.parseInt(tokens.nextToken());
			int B = Integer.parseInt(tokens.nextToken());
			int value = Integer.parseInt(tokens.nextToken());
			BridgeList[A].add(new Node(B, value));
			BridgeList[B].add(new Node(A, value));
		}
		prim(A);
		System.out.println(totalvalue);

	}

	private static void prim(int startpoint) {
		PriorityQueue<Node> PQ = new PriorityQueue<>();
		PQ.add(new Node(startpoint, 0));
		while(!PQ.isEmpty()) {
			Node temp = PQ.poll();
			int point = temp.to;
			if(visited[point]) continue;
			visited[point] = true;
			totalvalue+=temp.value;
			for(Node pop : BridgeList[point]) {
				if(visited[pop.to]) continue;
				PQ.add(pop);
			}
		}
	}
	
	private static class Node implements Comparable<Node>{
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
