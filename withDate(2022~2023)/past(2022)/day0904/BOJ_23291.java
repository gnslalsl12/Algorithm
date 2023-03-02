package day0904;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_23291 {
	static int N, K;
	static int [][] maps;
	static int plusmin;
	static int[][] deltas = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		
		N = Integer.parseInt(tokens.nextToken());
		K = Integer.parseInt(tokens.nextToken());
		maps = new int [N][N];
//		plusmin = Integer.MAX_VALUE;
		tokens = new StringTokenizer(read.readLine());
		for(int i = 0; i < N; i++) {
			maps[N-1][i] = Integer.parseInt(tokens.nextToken());
			plusmin = Math.min(maps[N-1][i], plusmin);
		}//매핑 완료
		
		System.out.println("==========초기 맵");
		for(int [] t : maps) {
			System.out.println(Arrays.toString(t));
		}
		int timecount = 0;
		while(getMaxMinGap() > K) {
			System.out.println("###########################################최소에 +1");
			AddSmallest(plusmin);	//최소에 +1
			System.out.println("###########################################오른쪽으로 말아가기");
			Rolling();			//오른쪽으로 말기
			System.out.println("###########################################d/5 더하기");
			BFSgetGAP();		//차이 /5 더해주기
			System.out.println("###########################################세로 기준 펼치기");
			UnRoll();			//세로 기준으로 펼치기
			System.out.println("###########################################접고 접기");
			GalaxyFold2();		//접고 접기
			System.out.println("###########################################d/5 더하기");
			BFSgetGAP();		//차이 /5 더해주기
			System.out.println("###########################################세로기준 펼치기");
			UnRoll();
			if(getMaxMinGap() <= K) break;
			timecount++;
		}
		System.out.println("==========펼쳐");
		for(int [] t : maps) {
			System.out.println(Arrays.toString(t));
		}
		System.out.println(timecount);
		
	}	
	
	
	private static void AddSmallest(int min) {	//가장 낮은 곳 +1
		for(int i = 0; i < N; i++) {
			if(maps[N-1][i] == min) maps[N-1][i]++;
		}
	}
	
	private static void Rolling() {
		int Wl = 1;
		int Hl = 1;
		int startpoint = 0;
		while(startpoint + 2*Wl < N) {
			System.out.println("startpoint : " + startpoint);
			System.out.println("Hl : " + Hl);
			System.out.println("Wl : " + Wl);
			int [][] temparray = new int [Hl][Wl];
			for(int i = 0; i < Hl; i++) {
				for(int j = 0; j < Wl; j++) {
					temparray[Hl - 1 - i][j] = maps[N-1 - i][startpoint + j];
					maps[N-1 - i][startpoint + j] = 0;
				}
			}
			System.out.println("temparray : ");
			for(int [] t : temparray){
				System.out.println(Arrays.toString(t));
			}
			
			
			int [][] rotatedtemparray = new int [Wl][Hl];
			//돌리기
			for(int i = 0; i < Hl; i++) {
				for(int j = 0; j < Wl; j++) {
					rotatedtemparray[j][Hl - 1 - i] = temparray[i][j];
				}
			}
			System.out.println("돌린 temparray : ");
			for(int [] t : rotatedtemparray){
				System.out.println(Arrays.toString(t));
			}
			
			for(int i = 0; i < Wl; i++) {
				for(int j = 0; j < Hl; j++) {
					maps[N-1 - Wl + i][startpoint + Wl +j] = rotatedtemparray[i][j];
				}
			}
			startpoint += Wl;
			
			if(Wl == Hl) {
				Hl++;
			}else {
				Wl++;
			}
			System.out.println("=============회전 한 번");
			for(int [] t : maps) {
				System.out.println(Arrays.toString(t));
			}
		}
	}
	
	private static void BFSgetGAP() {
		boolean [][] visited = new boolean[N][N+2];
		Queue<dirXY> BFSQ = new LinkedList<>();
		BFSQ.add(new dirXY(N-1,N-1));
		int [][] Plustemp = new int [N][N];
		while(!BFSQ.isEmpty()) {
			dirXY temp = BFSQ.poll();
			if(visited[temp.x][temp.y])continue; 
			visited[temp.x][temp.y]= true; 
			System.out.println("++++++++++++++++++++++++++++++하나씩 탐색하고 임시맵 설정");
			System.out.println(temp.toString());
			for(int dir = 0; dir < 4; dir++) {
				int nextx = temp.x + deltas[dir][0];
				int nexty = temp.y + deltas[dir][1];
				if(!isIn(nextx, nexty)) continue;	//범위 밖
				if(maps[nextx][nexty] == 0) continue;	//어항X
				if(visited[nextx][nexty]) continue;	//방문한 곳
				
				int gap = (maps[temp.x][temp.y] - maps[nextx][nexty])/5; //인접한 곳의 수 차이
				if(gap < 0) {	// 현재 위치가 더 작아
					Plustemp[nextx][nexty] += gap;
					Plustemp[temp.x][temp.y] -= gap;	//현재 위치에 더해줌 
				}else {
					Plustemp[nextx][nexty] += gap; 
					Plustemp[temp.x][temp.y] -= gap; 	//현재 위치에 뺴줌
				}//인접한 곳들 더할 값 저장해줌
				BFSQ.add(new dirXY(nextx,nexty));
			}
			for(int [] t : Plustemp) {
				System.out.println(Arrays.toString(t));
			}
			
			
		}
		System.out.println("=============더해줄 값");
		for(int [] t : Plustemp) {
			System.out.println(Arrays.toString(t));
		}
		System.out.println("=============어디에? 현재 맵에");
		for(int [] t : maps) {
			System.out.println(Arrays.toString(t));
		}
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				maps[i][j] += Plustemp[i][j];
			}
		}
		System.out.println("=============차이값 더하기");
		for(int [] t : maps) {
			System.out.println(Arrays.toString(t));
		}
	}

	private static void UnRoll() {
		Queue<Integer> tempUnRoll = new LinkedList<>();
		breakAll :
		for(int j = 0; j < N; j++) {
			for(int i = N-1; i >= 0; i--) {
				if(!tempUnRoll.isEmpty() && i == N-2 && maps[i][j] == 0) break breakAll;
				if(maps[i][j] != 0) {
					tempUnRoll.add(maps[i][j]);
					maps[i][j] = 0;
				}
			}
		}
//		System.out.println(tempUnRoll.toString());
		int set = 0;
		while(!tempUnRoll.isEmpty()) {
			maps[N-1][set++] = tempUnRoll.poll();
		}
		for(int [] t : maps) {
			System.out.println(Arrays.toString(t));
		}
	}
	
	private static void GalaxyFold2() {
		for(int i = 0; i < N/2; i++) {
			maps[N-2][N-1-i] = maps[N-1][i];
			maps[N-1][i] = 0;
		}
		for(int i = N/2; i < 3*N/4; i++) {
			maps[N-3][N-1 - (i - N/2)] = maps[N-2][i];
			maps[N-4][N-1 - (i - N/2)] = maps[N-1][i];
			maps[N-2][i] = 0;
			maps[N-1][i] = 0;
		}
		for(int [] t : maps) {
			System.out.println(Arrays.toString(t));
		}
	}
	
	private static int getMaxMinGap() {
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		for(int i = 0; i < N; i++) {
			int temp = maps[N-1][i];
			min = Integer.min(min, temp);
			max = Integer.max(max, temp);
		}
		System.out.println("최소값 " + min);
		System.out.println("최대값 " + max);
		plusmin = min;
		return max - min;
	}
	
	private static boolean isIn(int x, int y) {
		return x >= 0 && y >= 0 && x < N && y < N;
	}
	
	private static class dirXY {
		int x;
		int y;

		public dirXY(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "dirXY [x=" + x + ", y=" + y + "]";
		}
		
	}

}




















