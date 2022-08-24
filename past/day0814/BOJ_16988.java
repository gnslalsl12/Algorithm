package day0814;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_16988 {
	static int R, C;
	static int [][] maps;
	static ArrayList<Integer> NumofLands;
	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while(true) {
			StringTokenizer tokens = new StringTokenizer(read.readLine());
			C = Integer.parseInt(tokens.nextToken());
			R = Integer.parseInt(tokens.nextToken());
			maps = new int [R][C];
			if(R == 0) {
				break;
			}
			for(int i = 0 ; i < R ; i++) {
				tokens = new StringTokenizer(read.readLine());
				for(int j = 0 ; j < C ; j++) {
					maps[i][j] = Integer.parseInt(tokens.nextToken());
				}
			}
			int casecount = 0;
			for(int i = 0; i < R ; i++) {
				for(int j = 0; j < C ; j++) {
					if(maps[i][j] == 1) {
						maps[i][j] =  -1;
						BFSLands(new dirXYofLands(i, j));
						casecount++;
					}
				}
			}
			sb.append(casecount + "\n");
		}
		System.out.println(sb);
	}
	static int[][] deltas = {{1,0},{1,1},{0,1},{-1,1},{-1,0},{-1,-1},{0,-1},{1,-1}};
	static void BFSLands(dirXYofLands input) {
		Queue<dirXYofLands> BFSstack = new LinkedList<>();
		BFSstack.add(input);
		while(!BFSstack.isEmpty()) {
			dirXYofLands temp = BFSstack.poll();
			for(int d = 0; d < 8 ; d++) {
				int nextx = temp.x + deltas[d][0];
				int nexty = temp.y + deltas[d][1];
				if(isIn(nextx,nexty)) {
					if(maps[nextx][nexty] == 1) {
						BFSstack.add(new dirXYofLands(nextx, nexty));
					}
					maps[nextx][nexty] = -1; //이미 탐색한 좌표
				}
			}
		}
	}
	static boolean isIn(int x, int y) {
		if( x < R && x >= 0 && y < C && y >= 0 && maps[x][y] != -1) return true;
		else return false;
	}

}
class dirXYofLands{
	int x;
	int y;
	public dirXYofLands() {
		super();
	}
	public dirXYofLands(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
}