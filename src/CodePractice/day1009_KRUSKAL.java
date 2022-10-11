package CodePractice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
//7
//9
//1 2 29
//1 5 75
//2 3 35
//2 6 34
//3 4 7
//4 6 23
//4 7 13
//5 6 53
//6 7 25

public class day1009_KRUSKAL {
	static int N;
	static int [] parent;
	
	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		tokens = new StringTokenizer(read.readLine());
		int M = Integer.parseInt(tokens.nextToken());
		PriorityQueue<Node> NodeList = new PriorityQueue<>();
		parent = new int[N+1];
		for(int i = 1; i <= N; i++) {
			parent[i] = i;
		}
		for(int i = 0; i < M; i++) {
			tokens = new StringTokenizer(read.readLine());
			int to = Integer.parseInt(tokens.nextToken());
			int from = Integer.parseInt(tokens.nextToken());
			int value = Integer.parseInt(tokens.nextToken());
			NodeList.add(new Node(to, from, value));
		}
		
		int size = NodeList.size();
		int totalvalue = 0;
		for(int i = 0; i < M; i++) {
			Node temp = NodeList.poll();
			int parentTo = findingParent(temp.to);
			int parentFrom = findingParent(temp.from);
			if(isDiffParent(parentTo, parentFrom)) {
				totalvalue += temp.value;
				union(temp.to, temp.from);
			}
		}
		System.out.println(totalvalue);
	}
	
	private static void union(int x, int y) {
		int Xprt = findingParent(x);
		int Yprt = findingParent(y);	
		if(Xprt < Yprt) {
			parent[Xprt] = Yprt;						//이렇게 순수 x, y값을 써야하기 때문
		}else {
			parent[Yprt] = Xprt;
		}
	}
	
	private static boolean isDiffParent(int x, int y) {
		if(parent[x] == parent[y]) return false;
		return true;
	}
	
	private static int findingParent(int x) {
		if(parent[x] == x) return x;
		return parent[x] = findingParent(parent[x]);
	}
	
	private static class Node implements Comparable<Node>{
		int to;
		int from;
		int value;
		public Node(int to, int from, int value) {
			super();
			this.to = to;
			this.from = from;
			this.value = value;
		}
		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.value, o.value);
		}
		
	}

}
