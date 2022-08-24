package day0809;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA정사각방 {
	static int [][] maps;
	static int x,y;
	static int n;
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		int t=Integer.parseInt(read.readLine());
		
		
		
		
		for(int test=1;test<=t;test++) {
			n = Integer.parseInt(read.readLine());
			maps = new int [n][n];
			for(int ix = 0; ix<n; ix++) {
				StringTokenizer tokens = new StringTokenizer(read.readLine());
				for(int iy = 0; iy<n; iy++) {
					maps[ix][iy] = Integer.parseInt(tokens.nextToken());
					if(maps[ix][iy]==1) {
						x = ix;
						y = iy;
					}
				}
			}//maps
			nextnum = 0;
			found = new int[n*n+1];
			Search(1, x,y,0);
			
			while(nextnum <= n*n) {
				breakout :
				for(int xx = 0; xx<n; xx++) {
					for(int yy = 0; yy < n ; yy++) {
						if(maps[xx][yy] == nextnum) {
							x = xx;
							y = yy;
							break breakout;
						}
					}
				}
				Search(nextnum,x,y,0);
			}
			
			
			int ij=0;
			int max = Integer.MIN_VALUE;
			for(int i = found.length-1; i >=0; i--) {
				if(max<=found[i]) {
					ij = i;
					max = found[i];
				}
			}
			
			System.out.printf("#%d %d %d\n", test, ij, max);
			
		}
		

	}
	static int nextnum;
	static int[] found;
	static int[][] delta = {{1,0},{0,-1},{-1,0},{0,1}};
	private static void Search(int num, int dx, int dy, int count) { //count : 몇개 이동?
		if(nextnum > n*n) {
			return;
		}
		
		boolean notfound = true;
		
		for(int i = 0; i < 4; i++) {
			if(dx+delta[i][0]<n && dy+delta[i][1]<n && dx+delta[i][0]>=0 && dy+delta[i][1]>=0) {
				
				if(maps[dx][dy]+1 == maps[dx+delta[i][0]][dy+delta[i][1]]) {
					notfound = false;
					Search(num, dx+delta[i][0],dy+delta[i][1],count+1);
					break;
				}
			}
		}
		
		
		if(notfound) {
			found[num] = count+1;
			nextnum = num + count+1;
			return;
		}
				
				
		
		
	}

}
