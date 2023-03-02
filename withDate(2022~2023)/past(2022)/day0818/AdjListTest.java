package day0818;

import java.util.Scanner;

public class AdjListTest {
	
	static class Node{
		int to;	//상대 정점 번호
		Node next;	//링크 필드 (뒤에 오는 놈)
		
		public Node(int to, Node next) {
			super();
			this.to = to;
			this.next = next;
		}
	}
	
	static Node[] adjList;
	static int N;
	static boolean visited[];
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();	//정점수
		int E = sc.nextInt(); //간선수
		
		adjList = new Node[N]; //헤드만 놓여있는 N개짜리 배열
		
		for(int i = 0; i < E ; i++) {
			int from = sc.nextInt();
			int to = sc.nextInt();
			
			//맨 앞에 추가하기 : 
			adjList[from] = new Node(to,adjList[from]); //현재 from에 새로운 노드
			adjList[to] = new Node(from, adjList[to]);
			
		}
		dfs(0);
		
	}
	private static void dfs(int cur) {
		
		visited[cur] = true;
		System.out.print((char)(cur + 'A'));	//알파벳으로 출력해보기
		//현재 정점의 인접 정점들에 큐에 넣어서 차후 탐색하도록 만들기
		for(Node temp = adjList[cur]; temp!= null; temp = temp.next) {
			if(!visited[temp.to]) {	//방문체크
				dfs(temp.to);
			}
		}
		
		
	}
}
