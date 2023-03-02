package day0910;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2636 {
	static int N, M;
	static boolean [][] maps;
	static int resultcount;
	static boolean [][] visited;
	static dirXY startpoint;
	static boolean FinalCheck;
	static boolean [][] tempmaps;
	static int[][] deltas = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		maps = new boolean[N][M];
		resultcount = 0;
		FinalCheck = false;
		visited = new boolean[N][M];
		tempmaps = new boolean[N][M];
		startpoint = new dirXY(-1, -1);
		for(int i = 0; i < N; i++) {
			tokens = new StringTokenizer(read.readLine());
			for(int j = 0; j < M; j++) {
				maps[i][j] = Integer.parseInt(tokens.nextToken()) == 1? true : false;
				if(maps[i][j] == false && startpoint.x == -1) {
					startpoint = new dirXY(i,j);
				}
			}
		}
		
		int timecount = 0;
		while(!FinalCheck) {
			BFSCheese(startpoint);
			timecount++;
			
			for(int i = 0 ; i < N; i++) {
				for(int j = 0 ; j < M; j++) {
					if(tempmaps[i][j]) {
						maps[i][j] = false;
						visited[i][j] = true;
					}
				}
			}
			System.out.println("map");
			System.out.println();
			for(int i = 0 ; i < N; i++) {
				for(int j = 0 ; j < M; j++) {
					if(maps[i][j]) {
						System.out.printf("%d ",1);
					}else {
						System.out.printf("%d ", 0);
						
					}
				}
				System.out.println();
			}
			System.out.println("visited");
			for(int i = 0 ; i < N; i++) {
				for(int j = 0 ; j < M; j++) {
					if(visited[i][j]) {
						System.out.printf("%d ",0);
					}else {
						System.out.printf("%d ", 1);
						
					}
				}
				System.out.println();
			}
			
		}
		System.out.println(timecount);
		System.out.println(resultcount);
	}
	
	private static void BFSCheese(dirXY start) {
		tempmaps = new boolean[N][M];
		Queue<dirXY> BFSQ = new LinkedList<>();
		int meltcount = 0;
		BFSQ.add(start);
		visited[start.x][start.y] = true; 
		System.out.println("시작 : " + start.x + ", " + start.y);
		System.out.println(visited[start.x][start.y]);
		while(!BFSQ.isEmpty()) {
			dirXY now = BFSQ.poll();
			System.out.println("now : " + now.toString());
			for(int dir = 0; dir < 4; dir++) {
				int nextx = now.x + deltas[dir][0];
				int nexty = now.y + deltas[dir][1];
				if(!isIn(nextx, nexty)) continue;
				if(visited[nextx][nexty]) continue;
				if(tempmaps[nextx][nexty]) continue;
				if(!maps[nextx][nexty]) {	//false 빈공간
					visited[nextx][nexty] = true;
					BFSQ.add(new dirXY(nextx, nexty));
					continue;
				}else {	//true 치즈
					//처음 발견한 치즈 => 다음에 무조건 빈공간
					meltcount ++;
					startpoint = new dirXY(nextx, nexty);
//					visited[nextx][nexty] = true;
					tempmaps[nextx][nexty] = true;	//녹일 공간 true
					continue;
				}
			}
		}
		if(meltcount == 0) {
			FinalCheck = true;
			return;
		}
		resultcount = meltcount;
	}
	
	private static boolean isIn(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < M;
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
