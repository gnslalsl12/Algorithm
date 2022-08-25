package day0825;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Dijkstra {
	
	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		int V = Integer.parseInt(read.readLine());
		
		int [][] adjMatrix = new int [V][V];
		
		for(int i = 0; i < V; i++) {
			StringTokenizer tokens = new StringTokenizer(read.readLine());
			for(int j = 0 ; j< V; j++) {
				adjMatrix[i][j] = Integer.parseInt(tokens.nextToken());
			}
		}
		
		//start -> end로의 최단경로 구해보기
		
		int start = 0; //출발지는 0으로 설정
		int end = V-1; //도착지는 마지막 정점
		//다익스트라 알고리즘에 필요한 자료구조
		int [] D = new int [V]; // 출발지에서 자신으로 오는데 소요되는 최소비용
		boolean [] visited = new boolean[V];
		
		Arrays.fill(D, Integer.MAX_VALUE);
		//출발정점 처리하기
		D[start] = 0;
		int minVertex = -1;
		int min;
		for(int i = 0; i < V; i++) {
			//Dijk 과정 1 : 미방문 정점 중 출발지에서 자기까지 비용이 최소인 정점 선택
			//방문해야하는 나머지 정점 중 출발지에서 가장 가까운 정점 찾기
			min = Integer.MAX_VALUE;
			for(int j = 0; j < V; j++) {
				if(!visited[j] && min > D[j]) { //출발지에서 직접 는 게 더 작다 : 
					min = D[j];
					minVertex = j;
				}
			}
			//minVertex : 출발지에서 가장 싸게 갈 수 있는 곳
			// min : 출발지에서 minVertex로 가는 비용
			
			
			//과정 2 : 방문처리
			visited[minVertex] = true;
			if(minVertex == end) break; //최소 비용 경로를 찾던 도중 목적지에 도착함 => 그만 탐색해도 됨
			//(문제가 start -> end 만 구하는 경우에만 추가하면됨. 모든 곳을 방문해야하면 X)
			
			
			//과정 3 : 선택된 정점을 경유지로 해서 미방문 정점들로 가는 비용을 따져보고
			// 기존 최적해보다 유리하면 갱신하기
			
			for(int j = 0; j<V ; j++) {
				if(!visited[j] && adjMatrix[minVertex][j] > 0 && D[j] > D[minVertex] + adjMatrix[minVertex][j]) {
					//방문하지 않았고, 연결돼있고, 출발지에서 바로 가는 비용보다 앞에서 찾은 minVertex 경유가 더 싸다 : 갱신
					D[j] = D[minVertex] + adjMatrix[minVertex][j];
					
				}
			}
			
		}
		System.out.println(D[end]);
		
	}

}
