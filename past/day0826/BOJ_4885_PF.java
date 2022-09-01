package day0826;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_4885_PF {
	static int N;
	static int [][] maps;
	static int minimunRupee;
	private static int[][] deltas = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int test = 1;
		while(true) {
			StringTokenizer tokens = new StringTokenizer(read.readLine());
			N = Integer.parseInt(tokens.nextToken());
			if(N == 0) {
				System.out.print(sb);
				return;
			}
			maps = new int [N][N];
			for(int i = 0; i < N; i++) {
				tokens = new StringTokenizer(read.readLine());
				for(int j = 0; j < N; j++) {
					maps[i][j] = Integer.parseInt(tokens.nextToken());
				}
			}
			minimunRupee = 0;
			
			DIJKSTRA();
			sb.append(String.format("Problem %d: %d\n", test++, minimunRupee));
			
		}
	}
	
	static void DIJKSTRA() {
		//준비물 : 
		PriorityQueue<dirXY> PQ = new PriorityQueue<>();
		int [][] AccRupee = new int [N][N];
		
		//누적 루피 맵 초기화
		for(int [] row : AccRupee) {
			Arrays.fill(row, Integer.MAX_VALUE);
		}
		
		AccRupee[0][0] = 0;	//방문하면서 갱신할 거임
		PQ.offer(new dirXY(0, 0, maps[0][0])); //아직 방문 아님. 여기가 처음 방문할 곳이라는 뜼
		
		while(!PQ.isEmpty()) {
			//대장
			dirXY head = PQ.poll();
			
			//할일 - 방문처리 등...
			if(head.x == N-1 && head.y == N-1) {
				minimunRupee = head.tempRupee;
				return;
			}
			
			//자식 탐색
			for(int dir = 0; dir < 4; dir++) {
				int nextx = head.x + deltas[dir][0];
				int nexty = head.y + deltas[dir][1];
				
				if(isIn(nextx, nexty) && AccRupee[nextx][nexty] > head.tempRupee + maps[nextx][nexty]) {
											//저장된 누적비용보다 지금 새로 온 축적 비용이 더 싼가?
					AccRupee[nextx][nexty] = head.tempRupee + maps[nextx][nexty];
					PQ.add(new dirXY(nextx, nexty,  head.tempRupee + maps[nextx][nexty]));
				}
			}
		}
	}
	
	static boolean isIn(int x, int y) {
		return x>=0 && y>=0 && x<N && y<N;
	}
	
	//다익스트라 활용을 위해서 시작점에서 해당지점까지의 누적 비용 관리 및 비교 필요
	static class dirXY implements Comparable<dirXY>{
		int x;
		int y;
		int tempRupee; // 누적 비용
		public dirXY(int x, int y, int aw) {
			super();
			this.x = x;
			this.y = y;
			this.tempRupee = aw;
		}
		@Override
		public int compareTo(dirXY o) {
			return Integer.compare(this.tempRupee, o.tempRupee);
		}
	}
}