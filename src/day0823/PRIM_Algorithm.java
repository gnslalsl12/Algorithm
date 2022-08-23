package day0823;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class PRIM_Algorithm {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	
	
	static int V;
	static int [][] graph;
	public static void main(String[] args) throws IOException {
//		input = new BufferedReader(new StringReader(instr));
		
		input = new BufferedReader(new InputStreamReader(System.in));
		V = Integer.parseInt(input.readLine());
		graph = new int [V][V];
		for(int r = 0; r< V ; r++) {
			tokens = new StringTokenizer(input.readLine());
			for(int c = 0; c< V; c++) {
				graph[r][c] = Integer.parseInt(tokens.nextToken());
			}
			
		}
		
//		for(int [] row : graph) {
//		System.out.println(Arrays.toString(row));
//		}
		//맵 입력 완료
		
//		PRIM1();
		PRIM2();
		
		
		
		
		
		
		
		
	}
	
	
	
	
	
	
	
	private static void PRIM1() {
		int totalCost = 0;
		//준비물 : 정점들의 방문 여부 기록, 각 정점의 최소 비용을 업데이트해두는 배열
		boolean [] visited = new boolean[V];
		int [] minCostTable = new int[V];
		
		//초기화 작업 ( 모든 노든느 비 MST그룹으로 간주하고, 그 노드들로 가는 비용을무한대로 초기화
		Arrays.fill(minCostTable, Integer.MAX_VALUE); //모든 값을 초기화시킴
		
		//임의의 출발점 설정하기 (비용 = 0, 아직 방문한 것은 아닌 상태)
		minCostTable[0] = 0;
		
		//모든 정점을 탐색해서 MST 구성해보기
		for(int v = 0; v< V; v++) {
			//가장 비용이 저렴한 정점 찾기
			int minCost = Integer.MAX_VALUE;
			int minCostVertex = -1;
			
			for(int i = 0; i < minCostTable.length ; i++) {
				
				// 미방문이면서 저렴한 애 찾기 ==> 이번 MST 그룹에 추가될 녀석
				/*if(!visited[i] && minCostTable[i] < minCost) {
					//찾았으면 그걸 출발점으로 갱신해주기
					minCost = minCostTable[i];
					minCostVertex = i;
							
				}*/			//PriorityQueue로 해보자!
				
				
				
			}
			
			// 방문 지점이 결정되었으니 ㄱㄱ
			visited[minCostVertex] = true; // 방문ㅌ처리
			
			//이제 간 상태니까 비용이 갱신됨
			totalCost += minCost;
			
			//새로운 애가 데려올 수 있는 애 찾아보기
			for(int i = 0; i <V ; i++) {
				//미방문 && 연결됨 && 갱신되는 비용이 더 저렴해야함
				if(!visited[i] && graph[minCostVertex][i] != 0 && graph[minCostVertex][i] < minCostTable[i]) {
					minCostTable[i] = graph[minCostVertex][i];
					
				}
				
			}
			
		}
		
		System.out.printf("최소 비용 : %d%n", totalCost); //구하는 최소 비용
		
	}
	
	



	//priorityQ를 쓰기 위해 가격을 기준으로 오름차순 정렬할 수 있도록 처리
	static class Node implements Comparable<Node>{
		int no;
		int cost;
		
		public Node(int no, int cost) {
			super();
			this.no = no;
			this.cost = cost;
		}
		
		@Override
		public String toString() {
			return "[ n = " + no + ", c = " + cost + "]";
		}
		
		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			return Integer.compare(this.cost, o.cost);
		}
		
	}
	
	private static void PRIM2() {
		int totalCost = 0;
		
		boolean [] visited = new boolean[V];
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		
		//초기화작업은 불필요 (배열이 아니니까)
		
		//임의의 출발점 설정 (비용 0, 아직 방문은 아님)
		pq.offer(new Node(0, 0));	//임의의 출발점(0번), 이떄의 비용은 0원
		int nodeCount = 0;
		while(!pq.isEmpty()) {
			//대장 데려오기	/////////////////////////////
			Node minCostHead = pq.poll();
			
			
			//할 일 하기	//////////////////////////////
			//이미 방문된 노드면 SKIP
			if(visited[minCostHead.no]) {
				continue;
			}
			nodeCount++;
			if(nodeCount == V) {	//다 탐색함
				break;
			}
			
			visited[minCostHead.no] = true;	//자식 탐색하기 내부에서 말한 거 떄문에 여기서 방문처리 함
			
			totalCost += minCostHead.cost;
			
			
			//자식 탐색하기	///////////////////////////////
			for(int i = 0; i < V; i++) {
				if(!visited[i] && graph[minCostHead.no][i] != 0) {
					//원래는 갱신비용이 작을 때만 넝헜지만 지금은 PQ는 알아서 작은 놈을 줄 거기 때문에
					//비교 안 해도 됨
					
					//시간복잡도를 위해 공간복잡도를 희생해야함 ( " 같은 노드 들어가는 건 어쩔 수 없음 " )
					
					pq.offer(new Node(i, graph[minCostHead.no][i]));
					//priorityQ라 지금 넣은 애가 바로 다음 방문 될 애는 아님
					// => 방문처리 X ==>> poll 할 때 방문처리 해야함
					
					
				}
				
				
			}
			
		}
		
		
		
		
		
		
		System.out.printf("최소 비용 : %d%n", totalCost); //구하는 최소 비용
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
//	static String instr = 

}




































