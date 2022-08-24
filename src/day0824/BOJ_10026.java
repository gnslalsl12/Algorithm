package day0824;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;

public class BOJ_10026 {
	static int N;
	static ArrayList<dirXY> Red = new ArrayList<>();
	static ArrayList<dirXY> Blue = new ArrayList<>();
	static ArrayList<dirXY> Green = new ArrayList<>();
	static char [][] maps;
	static boolean[] Visited;
	static int[][] deltas = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(read.readLine());
		maps = new char [N][N];
		for(int i =0; i<N; i++) {
			String templine = read.readLine();
			for(int j = 0; j< N; j++) {
				maps[i][j] = templine.charAt(j);
				if(maps[i][j] == 'R') {
					Red.add(new dirXY(i,j));
				}else if(maps[i][j] == 'B') {
					Blue.add(new dirXY(i,j));
				}else {
					Green.add(new dirXY(i,j));
				}
			}
		}
		int ThreeColor = 0;
		int TwoColor = 0;
		Visited = new boolean [N*N];
		for(dirXY R : Red) {
			ThreeColor += DFS(R, 'B', 'G');	//B가 아니고 G가 아닌 : Red
		}
		
		Visited = new boolean[N*N];
		for(dirXY B : Blue) {
			int a = DFS(B, 'R', 'G');			//R이 아니고 G가 아닌 : Blue
			ThreeColor += a;
			TwoColor += a;

		}
		
		Visited = new boolean[N*N];						//R이 아니고 B가 아닌 : Green
		for(dirXY G : Green) {
			ThreeColor += DFS(G, 'R', 'B');
		}
		
		Visited = new boolean[N*N];
		for(dirXY R : Red) {							//B가 아닌 : Green 또는 Red
			TwoColor += DFS(R, 'B', 'B');		
		}
		for(dirXY G : Green) {							//B가 아닌 : Green 또는 Red
			TwoColor += DFS(G, 'B', 'B');
		}
		
		System.out.printf("%d %d", ThreeColor, TwoColor);

		
	}
	
	private static int XYtoInt(int x, int y) {
		return x*N + y;
	}
	private static int DFS(dirXY startpoint, char ColorA, char ColorB) {
		if(Visited[XYtoInt(startpoint.x, startpoint.y)] == true) {
			return 0;
		}
		Stack<dirXY> DFSstack = new Stack<>();
		DFSstack.add(startpoint);
		
		while(!DFSstack.isEmpty()) {
			dirXY now = DFSstack.pop();
			Visited[XYtoInt(now.x,now.y)] = true; 
			for(int dir = 0; dir < 4; dir++) {
				int tempx = now.x + deltas[dir][0];
				int tempy = now.y + deltas[dir][1];
				
				if(!isIn(tempx, tempy)) {
					continue;
				}//범위 벗어남
				if(Visited[XYtoInt(tempx, tempy)] == true) {
					continue;
				}//이미 움직인 자리임
				
				if(maps[tempx][tempy] == ColorA || maps[tempx][tempy] == ColorB) {
					continue;
				}//찾는 색과 다른 색임
				//범위 안이고 아직 가지 않았고 내가 찾는 색임 : 
				DFSstack.push(new dirXY(tempx, tempy));
				Visited[XYtoInt(tempx, tempy)] = true;
			}
			
			
		}
		
		return 1;
	}
	
	private static boolean isIn(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
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
