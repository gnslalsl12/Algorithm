package day0903;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
//바이러스 상하좌우 좌표와
//문이라 막을 수 있는 곳의 좌표를 저장하고
//조합으로 3개만 뽑아서 경우의 수 ㄱㄱ
public class BOJ_14502 {
	static int N, M;
	static int [][] maps;
	static ArrayList<dirXY> AvailSetWall;
	static ArrayList<dirXY> Viruses;
	static ArrayList<dirXY []> Selected;
	static int removecount;
	static int[][] deltas = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		AvailSetWall = new ArrayList<>();
		Selected = new ArrayList<>();
		Viruses = new ArrayList<>();
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		maps = new int [N][M];
		for(int i = 0; i < N; i++) {
			tokens = new StringTokenizer(read.readLine());
			for(int j = 0; j < M; j++) {
				maps[i][j] = Integer.parseInt(tokens.nextToken());
				if(maps[i][j] == 0) AvailSetWall.add(new dirXY(i,j));
				else if (maps[i][j] == 2) Viruses.add(new dirXY(i,j));
			}
		}//mapping 완료
		int TotalEmpty = AvailSetWall.size();
		Comb(0,0,new int [3]);
		int maxv = Integer.MIN_VALUE;
		for(dirXY [] Choosed : Selected) {
			removecount = 0;
			for(int i = 0; i < 3; i++) {
				maps[Choosed[i].x][Choosed[i].y] = 1;
			}//벽으로 막아보기
			boolean [][] visits = new boolean [N][M];
			for(dirXY V : Viruses) {
				BFSVirus(V, visits);
			}
			if(TotalEmpty - removecount < maxv) {
			}
			maxv = Integer.max(maxv, TotalEmpty - removecount);
			for(int i = 0; i < 3; i++) {
				maps[Choosed[i].x][Choosed[i].y] = 0;
			}//벽 치우기
		}
		System.out.println(maxv-3);
	}

	private static void BFSVirus(dirXY input, boolean [][] visited) {
		Queue<dirXY> BFSQ = new LinkedList<>();
		BFSQ.add(input);
		visited[input.x][input.y] = true; 
		while(!BFSQ.isEmpty()) {
			dirXY temp = BFSQ.poll();
			for(int dir = 0; dir < 4; dir++) {
				int nextx = temp.x + deltas[dir][0];
				int nexty = temp.y + deltas[dir][1];
				
				if(!isIn(nextx, nexty)) continue;
				if(visited[nextx][nexty]) continue;
				if(maps[nextx][nexty] != 0) continue;	//빈공간만 탐색
				
				removecount++;	//지우는 빈공간
				visited[nextx][nexty] = true;
				BFSQ.add(new dirXY(nextx, nexty));
			}
		}
	}
	
	private static void Comb(int countnow, int startpoint, int sel[]) {
		if(countnow == 3) {
			dirXY [] SelectedXY = new dirXY[3];
			for(int s = 0; s < 3; s++) {
				SelectedXY[s] = AvailSetWall.get(sel[s]);
			}
			Selected.add(SelectedXY);
			return;
		}
		for(int i = startpoint; i < AvailSetWall.size(); i++) {
			sel[countnow] = i;
			Comb(countnow+1, startpoint+1, sel);
		}
	}
	
	private static boolean isIn(int x, int y) {
		return x >= 0 && y >= 0 && x < N && y < M;
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
