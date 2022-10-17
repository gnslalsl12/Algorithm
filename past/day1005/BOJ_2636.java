package day1005;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2636 {
	static int N, M;
	static long[][] maps;
	static long [][] visits;
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
		if (M >= 50) {
			maps = new long[N][2];
			visits = new long[N][2];
		} else {
			maps = new long[N][1];
			visits = new long [N][1];
		}
		cheesecount = 0;
		for (int i = 0; i < N; i++) {
			tokens = new StringTokenizer(read.readLine());
			for (int j = 0; j < M; j++) {
				if (tokens.nextToken().equals("1")) {
					cheesecount++;
					if (j < 50) {
						maps[i][0] |= 1 << j;
					} else {
						maps[i][1] |= 1 << j;
					}
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
			if(!CheckCheese(rm[0], rm[1]))	continue;	//앞에서 이미 치즈 녹은 위치임
			setFalse(rm[0], rm[1]);
			
			cheesecount--;
			BFSQ.add(rm);
		}
	}
	
	private static void BFS(int [] startpoint) {
		setVisits(startpoint[0], startpoint[1]);
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
				if(CheckVisits(tempx, tempy)) continue;
				if(CheckCheese(tempx, tempy)) {//외곽 치즈 발견하면 arraylist 에 추가해주고 그대로 true로 남겨놓음
					if(CheckVisits(tempx, tempy)) continue;
					readyToM.add(new int [] {tempx, tempy});
					setVisits(tempx, tempy);
					if(startpoint == null) {
						startpoint = new int [] {tempx, tempy};
					}
					continue;	
				}
				setVisits(tempx, tempy);
				BFSQ.add(new int [] {tempx, tempy});
			}

		}
	}

	private static boolean isIn(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < M;
	}
	
	private static void setFalse(int x, int y) {
		if(y < 50) {
			maps[x][0] &= ~(1<<y);
		}else {
			maps[x][1] &= ~(1<<y);
		}
	}
	private static void setVisits(int x, int y) {
		if(y < 50) {
			visits[x][0] |= 1<<y;
		}else {
			visits[x][1] |= 1<<y;
		}
	}
	
	private static boolean CheckVisits(int x, int y) {
		if (y < 50) {
			return (visits[x][0] & (1 << y)) != 0 ? true : false;
		} else {
			return (visits[x][1] & (1 << y)) != 0 ? true : false;
		}
	}
	private static boolean CheckCheese(int x, int y) {
		if (y < 50) {
			return (maps[x][0] & (1 << y)) != 0 ? true : false;
		} else {
			return (maps[x][1] & (1 << y)) != 0 ? true : false;
		}
	}

}
