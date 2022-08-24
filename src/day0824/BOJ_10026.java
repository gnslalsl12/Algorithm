package day0824;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
//적록색약은 BITMASKING

public class BOJ_10026 {
	static int N;
	static char [][] maps;
	static ArrayList<dirXY> R = new ArrayList<>();
	static ArrayList<dirXY> G = new ArrayList<>();
	static ArrayList<dirXY> B = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		
		N = Integer.parseInt(tokens.nextToken());
		maps = new char [N][N];
		for(int i = 0; i < N; i++) {
			String templine = read.readLine();
			for(int j = 0; j < N; j++) {
				maps[i][j] = templine.charAt(j);
				if(maps[i][j] == 'R') {
					R.add(new dirXY(i,j));
				}
				else if(maps[i][j] == 'G') {
					G.add(new dirXY(i,j));
				}
				else if(maps[i][j] == 'B') {
					B.add(new dirXY(i,j));
				}
			}
		}
		visited = new boolean[N][N];
		visitedGR = new boolean[N][N];
		//mapping 완료
		int Rresult = 0;
		int Gresult = 0;
		int Bresult = 0;
		for(dirXY temp : R) {
			Rresult += BFSN(temp, 'R');
		}
		for(dirXY temp : G) {
			Gresult += BFSN(temp, 'G');
		}
		for(dirXY temp : B) {
			Bresult = BFSN(temp, 'B');
		}
		
		int RGresult = 0;
		for(dirXY temp : R) {
			RGresult += BFSGR(temp);
		}
		for(dirXY temp : G) {
			RGresult += BFSGR(temp);
		}
		System.out.println((Rresult + Gresult + Bresult + 1) + " " + (Bresult + RGresult + 1));
		
	}
	private static int[][] deltas = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	private static boolean isIn(int x, int y) {
		return x>=0 && x < N && y >= 0 && y< N;
	}
	static boolean [][] visited;
	static boolean [][] visitedGR;
	
	private static int BFSGR(dirXY input) {	//적록 같이 보는 거
		if(visitedGR[input.x][input.y]== true) {
			return 0;
		}
		Queue<dirXY> BFSQ = new LinkedList<>();
		BFSQ.add(input);
		visitedGR[input.x][input.y]= true; 
		while(!BFSQ.isEmpty()) {
			dirXY now = BFSQ.poll();
			for(int i = 0; i < 4; i++) {
				int tempx = now.x + deltas[i][0];
				int tempy = now.y + deltas[i][1];
				
				if(!isIn(tempx, tempy)) {
					continue;
				}
				
				if(maps[tempx][tempy] == 'B') { 
					continue;
				}
				if(visitedGR[tempx][tempy] == true) {
					continue;
				}
				
				BFSQ.add(new dirXY(tempx, tempy));
				visitedGR[tempx][tempy] = true;
			}
		}
		return 1;
	}
	
	private static int BFSN(dirXY input, char RGB) {
		if(visited[input.x][input.y]== true) {
			return 0;
		} 
		Queue<dirXY> BFSQ = new LinkedList<>();
		BFSQ.add(input);
		visited[input.x][input.y]= true; 
		while(!BFSQ.isEmpty()) {
			dirXY now = BFSQ.poll();
			for(int i = 0; i < 4; i++) {
				int tempx = now.x + deltas[i][0];
				int tempy = now.y + deltas[i][1];
				if(!isIn(tempx, tempy)) {
					continue;
				}
				if(maps[tempx][tempy] != RGB) { //다른 애
					continue;
				}
				if(visited[tempx][tempy] == true) {
					continue;
				}
				BFSQ.add(new dirXY(tempx, tempy));
				visited[tempx][tempy] = true;
			}
		}
		return 1;
	}
	
	private static class dirXY {
		int x;
		int y;

		public dirXY(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}
}
