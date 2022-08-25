package day0825;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class TopologySortTest_위상정렬 {

	static class Node{
		int vertex;
		Node next;
		public Node(int vertex, Node next) {
			super();
			this.vertex = vertex;
			this.next = next;
		}
	}
	
	static int V, E;
	static Node[] adjList;
	static int[] inDegree;
	public static void main(String[] args) throws IOException {
		
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		V = Integer.parseInt(tokens.nextToken());
		E = Integer.parseInt(tokens.nextToken());
		
		adjList = new Node[V+1]; //각 정점별 인접 리스트 (번호가 1번부터라 V+1)
		
		inDegree = new int[V+1]; //각 정점별 진입차수
		
		for(int i = 0; i < E;i++) {
			tokens = new StringTokenizer(read.readLine());
			int from  = Integer.parseInt(tokens.nextToken());
			int to  = Integer.parseInt(tokens.nextToken());
			
			adjList[from] = new Node(to, adjList[from]);	//가중치 없는 유향 그래프
			inDegree[to]++;	//다음 과제의 진입차수 -1
		}
		ArrayList<Integer> list = topologySort();
		//위상정렬 끝
		
		if(list.size() == V) {
			for(Integer i : list) {
				System.out.print(i + " ");
			}
			System.out.println();
			
		}else {
			System.out.println("CYCLE 발생");
		}
		
	}
	
	private static ArrayList<Integer> topologySort(){
		ArrayList<Integer> list = new ArrayList<>();
		Queue<Integer> Q = new ArrayDeque<>();
		
		//진입 차수가 0인 정점 큐에 넣기
		for(int i = 1; i <= V; i++) {
			if(inDegree[i] == 0) Q.offer(i);
		}
		
		//BFS
		while(!Q.isEmpty()) {
			int current = Q.poll();
			list.add(current);
			
			for(Node temp = adjList[current] ; temp!= null ; temp = temp.next) {
				if(--inDegree[temp.vertex] == 0) Q.offer(temp.vertex);	//선행작업 끝ㄴ
			}
		}
		return list; //순서를 지키면서 담겨있던 애들이 들어있음
		
		
		
	}

}
