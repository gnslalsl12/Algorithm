package CodePractice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class testBlank10 { 
	static int N, M;
	static ArrayList<Node> [] NodeList;
	static boolean [] visited;
	static int [] Dist;

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		tokens = new StringTokenizer(read.readLine());
		M = Integer.parseInt(tokens.nextToken());
		visited = new boolean[N+1];
		NodeList = new ArrayList[N+1];
		for(int i = 1; i <= M; i++) {
			NodeList[i] = new ArrayList<>();
		}
		Dist = new int[N+1];
		for(int i = 0; i< M; i++) {
			tokens = new StringTokenizer(read.readLine());
			int point = Integer.parseInt(tokens.nextToken());
			int line = Integer.parseInt(tokens.nextToken());
			int value = Integer.parseInt(tokens.nextToken());
			NodeList[point].add(new Node(line, value));
		}
		
		
	}
	
	private static void DIJKSTRA(int startpoint) {
		PriorityQueue<Node> PQ = new PriorityQueue<>();
		PQ.add(new Node(startpoint, 0));
		while(!PQ.isEmpty()) {
			Node temp  = PQ.poll();
			if(visited[temp.line])continue;
			visited[temp.line] = true;
			for(Node next : NodeList[temp.line]) {
				if(visited[next.line]) continue;
				if(Dist[next.line]> temp.tempvalue + next.tempvalue) {
					Dist[next.line]= temp.tempvalue + next.tempvalue;
					PQ.add(new Node(next.line, Dist[next.line]);
				}
			}
			
		}
	}
	
	private static class Node implements Comparable<Node>{
		int line;
		int tempvalue;
		public Node(int line, int tempvalue) {
			super();
			this.line = line;
			this.tempvalue = tempvalue;
		}
		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.tempvalue, o.tempvalue);
		}
		
	}

}
