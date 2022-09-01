package day0824;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_3055 {
	static int R, C;
	static int [][] maps;
	private static int[][] deltas = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	static dirXY Dest, GSDC;
	
	public static void main(String[] args) throws IOException {	//맵상에 빈 공간 빼고 전부 -1, 물부터 변환, 고슴도치 변환
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		ArrayList<dirXY> Waters = new ArrayList<>();
		R = Integer.parseInt(tokens.nextToken());
		C = Integer.parseInt(tokens.nextToken());
		maps = new int[R][C];
		for(int i = 0; i < R; i++) {
			String templine = read.readLine();
			for(int j = 0; j < C; j++) {
				char temp = templine.charAt(j);
				if(temp == 'D'){
					Dest = new dirXY(i, j);
				}else if(temp == 'S') {
					GSDC = new dirXY(i, j);
				}else if(temp == '*') {
					Waters.add(new dirXY(i, j));
				}else if(temp == '.') {
					maps[i][j] = 0;
					continue;
				}
				maps[i][j] = -1;
			}
		}//맵 완료
		
		for(dirXY w : Waters) {
			WaterBFS(w);
		}
		
		int result = GSDCBFS(GSDC);
		
		if(result == -1) {
			System.out.println("KAKTUS");
		}else {
			System.out.println(result);
		}
	}

	private static boolean isIn(int x, int y) {
		return x >= 0 && x < R && y >= 0 && y < C;
	}
	private static void WaterBFS(dirXY push) {
		Queue<dirXY> BFSQ = new LinkedList<>();
		BFSQ.add(push);
		maps[push.x][push.y]= 1; 
		while(!BFSQ.isEmpty()) {
			dirXY now = BFSQ.poll();
			for(int i = 0; i < 4; i++) {
				int nextx = now.x + deltas[i][0];
				int nexty = now.y + deltas[i][1];
				if(!isIn(nextx, nexty)) {	//범위 밖
					continue;
				}
				if(maps[nextx][nexty] == -1) {	//가면 안되는 곳
					continue;
				}
				if(maps[nextx][nexty] != 0 && maps[nextx][nexty] <= maps[now.x][now.y]+1) {	//비어있지 않고 나보다 빠른 물
					continue;
				}
				//범위 안이고 비어있고 나보다 느린 물임
				maps[nextx][nexty] = maps[now.x][now.y]+ 1; 
				BFSQ.add(new dirXY(nextx,nexty));
			}
		}
	}
	
	private static int GSDCBFS(dirXY push) {
		Queue<dirXY> BFSQ = new LinkedList<>();
		BFSQ.add(push);
		maps[push.x][push.y]= 1;	//출발! 
		while(!BFSQ.isEmpty()) {
			dirXY now = BFSQ.poll();
			for(int i = 0; i < 4; i++) {
				int nextx = now.x + deltas[i][0];
				int nexty = now.y + deltas[i][1];
				
				if(!isIn(nextx, nexty)) {	//범위 밖
					continue;
				}
				if(nextx == Dest.x && nexty == Dest.y) {	//소굴 도착
					return maps[now.x][now.y]; 
				}
				
				if(maps[nextx][nexty] != 0 && maps[nextx][nexty] <= maps[now.x][now.y]+1) {	//비어있지 않고 나보다 빠른 물
					continue;
				}
				//범위 안이고 비어있고 나보다 느린 물임 => 움직여도 됨
				maps[nextx][nexty] = maps[now.x][now.y]+ 1; 
				BFSQ.add(new dirXY(nextx,nexty));
			}
		}
		return -1;
	}
	
	private static class dirXY{
		int x;
		int y;
		public dirXY(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
}
