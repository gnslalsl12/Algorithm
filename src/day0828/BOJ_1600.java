package day0828;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1600 {
	
	static int K;		//0~30
	static int W,H;		//1~200
	static int [][] maps;
	static boolean [][][] visited;
	static int [][] deltasM = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	static int [][] deltasH = { { -2, 1 }, { -1, 2 }, { 1, 2 }, { 2, 1 }, { 2, -1 }, { 1, -2 }, { -1, -2 }, { -2, -1 } };
	static int ResultCount;
	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		K = Integer.parseInt(tokens.nextToken());
		tokens = new StringTokenizer(read.readLine());
		W = Integer.parseInt(tokens.nextToken());
		H = Integer.parseInt(tokens.nextToken());
		maps = new int [H][W];
		for(int i = 0; i < H; i ++) {
			tokens = new StringTokenizer(read.readLine());
			for(int j = 0; j< W; j++) {
				maps[i][j] = Integer.parseInt(tokens.nextToken());
			}
		}//mapping
		visited = new boolean [H][W][K + 1];
		ResultCount = -1;
		BFS();
		System.out.println(ResultCount);
	}
	
	//원숭이로 보내보고
	//아직 K 남았으면 말로도 보내보고
	private static void BFS() {
		Queue<dirXY> BFSQ = new LinkedList<>();
		dirXY startpoint = new dirXY(0,0,0,0);
		BFSQ.offer(startpoint);
		
		while(!BFSQ.isEmpty()) {
			dirXY temp = BFSQ.poll();
			if(CheckVisits(temp)) {
				continue;
			}
			PushVisits(temp);
			
			if(temp.x == H-1 && temp.y == W-1) {
				ResultCount = temp.TotalCount;
				return;
			}
			
			for(int dir = 0; dir < 4 ;dir ++) {
				dirXY nextM = new dirXY(temp.x + deltasM[dir][0], temp.y + deltasM[dir][1], temp.countK, temp.TotalCount + 1);
				if(!isIn(nextM)) {
					continue;
				}
				if(!CheckVisits(nextM)) {		//visited가 false
					BFSQ.offer(nextM);
				}
			}
			
			if(temp.countK < K) {
				for(int dir = 0; dir < 8; dir++) {
					dirXY nextH = new dirXY(temp.x + deltasH[dir][0], temp.y + deltasH[dir][1], temp.countK + 1, temp.TotalCount + 1);
					if(!isIn(nextH)) {
						continue;
					}
					if(!CheckVisits(nextH)) {	//visited가 false일 때
						BFSQ.offer(nextH);
					}
				}
			}
		}
	}
	
	private static boolean isIn(dirXY input) {
		return input.x >= 0 && input.x < H && input.y >= 0 && input.y < W && maps[input.x][input.y] != 1;
	}
	
	private static boolean CheckVisits(dirXY input) {
		return visited[input.x][input.y][input.countK];
	}
	private static void PushVisits(dirXY input) {
		visited[input.x][input.y][input.countK] =true;
	}
	
	private static class dirXY {
		int x;
		int y;
		int countK;
		int TotalCount;
		
		public dirXY(int x, int y, int countK, int TotalCount) {
			super();
			this.x = x;
			this.y = y;
			this.countK = countK;
			this.TotalCount = TotalCount;
		}
	}
}