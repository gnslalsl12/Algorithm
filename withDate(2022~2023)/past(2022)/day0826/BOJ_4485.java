package day0826;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_4485 {	
	private static int [][] tempmaps;
	private static int[][] deltas = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	static int min;
	static int N;
	static int [][] maps;
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
			tempmaps = new int [N][N];
			for(int i = 0; i < N; i++) {
				Arrays.fill(tempmaps[i], Integer.MAX_VALUE);
			}
			dirXY startpoint = new dirXY(0,0,maps[0][0]);
			Search(startpoint);
			int tempmin = tempmaps[N-1][N-1];
			sb.append("Problem " + (test++) + ": " + tempmin + "\n");
		}
	}
	
//	private static void DFS(dirXY input) {
//		Stack<dirXY> DFSstack = new Stack<>();
//		
//		DFSstack.push(input);
//		
//		while(!DFSstack.isEmpty()) {
//			dirXY temp = DFSstack.pop();
//			
//			for(int i = 0; i < 4; i++) {
//				
//				int tempx = temp.x + deltas[i][0];
//				int tempy = temp.y + deltas[i][1];
//				
//				if(!isIn(tempx,tempy)) {
//					continue;
//				}
//				
//				if(tempmaps[tempx][tempy] <= temp.Ruppee + maps[tempx][tempy]) {
//					continue;
//				}
//				DFSstack.push(new dirXY(tempx, tempy, temp.Ruppee + maps[tempx][tempy]));
//			}
//		}
//		
//		
//	}
	
	
	
	private static void Search(dirXY input) {
		tempmaps[input.x][input.y] = input.Ruppee;
		if(input.x == N-1 && input.y == N-1) {
			return;
		}
		
		for(int i = 0; i < 4; i++) {
			
			int tempx = input.x + deltas[i][0];
			int tempy = input.y + deltas[i][1];
			
			if(!isIn(tempx,tempy)) {
				continue;
			}
			
			if(tempmaps[tempx][tempy] <= input.Ruppee + maps[tempx][tempy]) {
				continue;
			}
			Search(new dirXY(tempx, tempy,input.Ruppee + maps[tempx][tempy]));
		}
	}
	
	private static boolean isIn(int x, int y) {
		return x >= 0 && y >= 0 && x < N && y < N;
	}
	
	private static class dirXY {
		int x;
		int y;
		int Ruppee;
		public dirXY(int x, int y, int ruppee) {
			super();
			this.x = x;
			this.y = y;
			Ruppee = ruppee;
		}
	}
}