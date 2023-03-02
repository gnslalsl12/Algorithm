package day1005;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2636_re {
	static int N, M;
	static boolean[][] maps;
	static boolean [][] visits;
	static int[][] deltas = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	static Queue<int []> readyToM = new LinkedList<>();
	static int [] startpoint;
	static int cheesecount;
	static Queue<int []> BFSQ = new LinkedList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		maps = new boolean[N][M];
		visits = new boolean[N][M];
		cheesecount = 0;
		for (int i = 0; i < N; i++) {
			tokens = new StringTokenizer(read.readLine());
			for (int j = 0; j < M; j++) {
				if (tokens.nextToken().equals("1")) {
					cheesecount++;
					maps[i][j] = true;
				}
			}
		}
		startpoint = new int [] {0,0};
		int lastcheese = 0;
		int time = 0;
		while(cheesecount > 0) {
			time++;
			BFS(startpoint);
			lastcheese = cheesecount;
			Melt();
		}
		System.out.println(time);
		System.out.println(lastcheese);
	}
	private static void Melt() {
		while(!readyToM.isEmpty()) {
			int [] rm = readyToM.poll();
			if(!maps[rm[0]][rm[1]])	continue;	//앞에서 이미 치즈 녹은 위치임
			maps[rm[0]][rm[1]] = false;
			
			cheesecount--;
			BFSQ.add(rm);
		}
	}
	
	private static void BFS(int [] startpoint) {
		visits[startpoint[0]][startpoint[1]] = true;
		BFSQ.add(startpoint.clone());
		startpoint = null;
		while (!BFSQ.isEmpty()) {
			int temp[] =  BFSQ.poll();
			int x = temp[0];
			int y = temp[1];
			for(int dir = 0; dir < 4; dir++) {
				int tempx = x + deltas[dir][0];
				int tempy = y + deltas[dir][1];
				if(!isIn(tempx, tempy)) continue;
				if(visits[tempx][tempy]) continue;
				if(maps[tempx][tempy]) {//외곽 치즈 발견하면 arraylist 에 추가해주고 그대로 true로 남겨놓음
					readyToM.add(new int [] {tempx, tempy});
					visits[tempx][tempy] = true;;
					if(startpoint == null) {
						startpoint = new int [] {tempx, tempy};
					}
					continue;	
				}
				visits[tempx][tempy] = true;;
				BFSQ.add(new int [] {tempx, tempy});
			}

		}
	}
	private static boolean isIn(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < M;
	}
}
