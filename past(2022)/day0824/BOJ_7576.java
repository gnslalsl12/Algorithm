package day0824;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_7576 {
	static int M, N;
	static int [][] maps;
	static ArrayList<dirXY> Tom = new ArrayList<>();
	private static int[][] deltas = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	static int TotalCount;
	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		
		M = Integer.parseInt(tokens.nextToken());	//y 개수
		N = Integer.parseInt(tokens.nextToken());	//x 개수
		maps = new int [N][M];
		for(int w = 0; w < N; w++) {
			tokens = new StringTokenizer(read.readLine());
			for(int l = 0; l < M; l++) {
				maps[w][l] = Integer.parseInt(tokens.nextToken());
				if(maps[w][l] == 1) {
					Tom.add(new dirXY(w, l, 0));
				}
			}
		}
		TotalCount = 0;
		BFS();
		breakall:
		for(int w = 0; w < N; w++) {
			for(int l = 0; l < M; l++) {
				if(maps[w][l] == 0) {
					TotalCount = -1;
					break breakall;
				}
			}
		}
		
		System.out.println(TotalCount);
		
	}
	
	private static void BFS() {
		Queue<dirXY> BFSQ = new LinkedList<>();
		for(dirXY temp : Tom) {
			BFSQ.add(temp);
		}
		
		while(!BFSQ.isEmpty()) {
			dirXY now = BFSQ.poll();
			
			for(int dir = 0; dir < 4; dir++) {
				dirXY next = new dirXY(now.x + deltas[dir][0], now.y + deltas[dir][1], now.time + 1);
				
				if(!isIn(next)) {	//범위 밖?
					continue;
				}

				if(getMap(next) == 0) {
					setMap(next, 1);	//방문처리~
					BFSQ.add(next);
				}else {	//썩은 토마토 / 그냥 토마토
					continue;
				}
			}
			TotalCount = Math.max(now.time, TotalCount);
		}
	}
	
	private static int getMap(dirXY input) {		//맵상의 값 가져오기
		return maps[input.x][input.y];
	}
	
	private static void setMap(dirXY input, int value) {	//맵 상에 값 넣어주기
		maps[input.x][input.y] = value;
	}
	
	private static boolean isIn(dirXY input) {
		return input.x >= 0 && input.x < N && input.y >= 0 && input.y < M;
	}
	
	private static class dirXY {
		int x;
		int y;
		int time;

		public dirXY(int x, int y, int time) {
			super();
			this.x = x;
			this.y = y;
			this.time = time;
		}
	}
	
	
	
}