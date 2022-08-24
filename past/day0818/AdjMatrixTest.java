package day0818;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class AdjMatrixTest {
	static int [][] adjMatrix;
	static int N;
	static boolean visited[];
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();	//정점수
		int E = sc.nextInt(); //간선수
		
		adjMatrix = new int[N][N];	//0으로 자동 초기화
		visited = new boolean[N];
		
		for(int i = 0 ; i < E ; i++) {
			int from = sc.nextInt();
			int to = sc.nextInt();
			adjMatrix[to][from] = adjMatrix[from][to] = 1; //무향그래프
		}
		
//		bfs();
		dfs(0);
	}
	
	
	private static void dfs(int cur) {
		
		visited[cur] = true;
		System.out.print((char)(cur + 'A'));	//알파벳으로 출력해보기
		//현재 정점의 인접 정점들에 큐에 넣어서 차후 탐색하도록 만들기
		for(int i = 0; i < N ; i++) {
			if(!visited[i] && adjMatrix[cur][i] != 0) {	//방문체크X && current에서 이동 가능해야함
				dfs(i);
			}
		}
	}
	
	
	static void bfs() {
		Queue<Integer> queue = new ArrayDeque<>();
		boolean[] visited = new boolean[N];	//방문관리배열
		visited [0] = true; //시작점
		queue.offer(0);
		while(!queue.isEmpty()) {
			int cur = queue.poll();
			System.out.print((char)(cur + 'A'));	//알파벳으로 출력해보기
			//현재 정점의 인접 정점들에 큐에 넣어서 차후 탐색하도록 만들기
			for(int i = 0; i < N ; i++) {
				if(!visited[i] && adjMatrix[cur][i] != 0) {	//방문체크X && current에서 이동 가능해야함
					visited[i] = true;
					queue.offer(i);
				}
			}
		}
		System.out.println();
	}
}