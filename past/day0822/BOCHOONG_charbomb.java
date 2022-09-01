package day0822;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOCHOONG_charbomb {
	static int [][] maps;
	static int n, p;
	static int [][] deltasA = {{-1,0},{0,1},{1,0},{0,-1}};
	static int [][] deltasB = {{-1,1},{1,1},{1,-1},{-1,-1}};
	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		int T = Integer.parseInt(tokens.nextToken());
		StringBuilder sb = new StringBuilder();
		
		for(int test = 1; test <= T; test++) {
			tokens = new StringTokenizer(read.readLine());
			n = Integer.parseInt(tokens.nextToken());
			p = Integer.parseInt(tokens.nextToken());
			maps = new int[n][n];
			for(int i = 0; i < n; i++) {
				tokens = new StringTokenizer(read.readLine());
				for(int j = 0; j < n ;j++) {
					maps[i][j] = Integer.parseInt(tokens.nextToken());
				}
			}
			int result = getCount();
			
			sb.append("#" + test + " " + result + "\n");
		}
		
		System.out.println(sb);
	}
	private static boolean isIn(int x, int y) {
		return x >= 0 && x < n && y >= 0 && y < n;
	}
	private static int getCount() {
		int maxone = 0;
		for(int i = 0; i < n ; i++) {
			for(int j = 0; j < n ; j++) {
				int getsumA = maps[i][j];
				int getsumB = maps[i][j];
				for(int k = 1; k <= p; k++) {
					for(int dir = 0; dir < 4; dir++) {
						if(isIn(i+deltasA[dir][0]*k,j+deltasA[dir][1]*k)) { 
							getsumA += maps[i+deltasA[dir][0]*k][j+deltasA[dir][1]*k];
						}
						if(isIn(i+deltasB[dir][0]*k,j+deltasB[dir][1]*k)) {
							getsumB += maps[i+deltasB[dir][0]*k][j+deltasB[dir][1]*k];
						}
						
					}
				}
				maxone = Math.max(maxone, Math.max(getsumA, getsumB));
			}
		}
		return maxone;
	}
}