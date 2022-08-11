package day0811;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ16988 {
	static int N;
	static int M;
	static int [][] maps;
	static int count2;
	public static void main(String[] args) throws IOException {
		
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		maps = new int [N][M];
		count2 = 0;
		
		for(int i = 0; i<N; i++) {
			tokens = new StringTokenizer(read.readLine());
			for(int j = 0; j < M ; j++) {
				maps[i][j] = Integer.parseInt(tokens.nextToken());
				if(maps[i][j] == 2) count2++;
			}
		}//maps 끝
		
		
		
		
	}
	//탐색한 2 => 3;
	static int[][] deltas = {{1,0},{0,1},{-1,0},{0,-1}};
	
	public static void SearchRotatefor2(int x, int y) {
		
		for(int i = 0 ; i<4 ; i++) {
			if(maps[x+deltas[i][0]][y+deltas[i][1]] == 2) {
				int nextx = x+deltas[i][0];
				int nexty = y+deltas[i][1];
				SearchRotatefor2(nextx,nexty);
				return;
			}
		}
		
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
