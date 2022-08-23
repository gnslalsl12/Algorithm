package day0823;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class SWEA_PRIM2 {
	static int V, E;
	static ArrayList< Node >[] graph;
	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		int T = Integer.parseInt(tokens.nextToken());
		StringBuilder sb = new StringBuilder();
		
		for(int test = 1; test <= T; test++) {
			
			tokens = new StringTokenizer(read.readLine());
			V = Integer.parseInt(tokens.nextToken());
			E = Integer.parseInt(tokens.nextToken());
			graph = new ArrayList[V+1];
			for(int i = 0; i <= V; i++) {
				graph[i] = new ArrayList<>();
			}
			for(int i = 0; i < E; i++) {
				tokens = new StringTokenizer(read.readLine());
				int A = Integer.parseInt(tokens.nextToken());
				int B = Integer.parseInt(tokens.nextToken());
				int W = Integer.parseInt(tokens.nextToken());
				if(W == 0) continue;
				graph[A].add(new Node(B,W));
				graph[B].add(new Node(A,W));
			}
			
			long result = PRIM();

			sb.append("#" + test + " " + result + "\n");
			
		}
		System.out.print(sb);
	}

	private static long PRIM() {
		long totalCost = 0l;
		
		boolean [] visits = new boolean[V+1];
		
		PriorityQueue<Node> PQ = new PriorityQueue<>();
		
		PQ.add(new Node(1, 0));
		int nodeCount = 0;
		while(!PQ.isEmpty()) {
			Node temp = PQ.poll();
			
			if(visits[temp.no] == true) {	//현재 위치가 방문한 곳임
				continue;
			}
			visits[temp.no] = true ;	//현재 위치 방문처리 (다음에 다시 안 오게)
			
			if(nodeCount == V) {	//정점 하나씩 다 돌았니?
				break;
			}
			nodeCount++;
			
			totalCost += (long)temp.weight;	//오름차순이니까 현재 위치로 올 수 있는 가중치 중 가장 작은 거만 빼고 다 못 들어옴
//			System.out.println("??" + totalCost);
//			System.out.println(temp.no);
			for(int i = 0; i < graph[temp.no].size() ; i++) {
//				System.out.println("탐색 : " + graph[temp.no].get(i).no);
				if(!visits[graph[temp.no].get(i).no]) {
					//현재에서 갈 수 있는 목적지들의 번호가 방문 X이고, 목적지의 가중치가 0이 아닐 때
					PQ.offer(graph[temp.no].get(i));	//해당 목적지 노드를 추가
					
				}
			}
		}
		
		return totalCost;
		
	}

	private static class Node implements Comparable<Node>{
		int no;
		int weight;
		
		public Node(int to, int weight) {
			super();
			this.no = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			return Integer.compare(this.weight, o.weight);
		}
		
	}
	
}
