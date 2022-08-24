package day0824;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_16236_new {
	static int N;
	static int fishsize;
	static int [][] maps;
	static int[] shark;	//좌표랑 사이즈
	static ArrayList<Fish> Fishes = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		TotalEat = 0;
		TotalCount = 0;
		N = Integer.parseInt(tokens.nextToken());
		maps = new int [N][N];
		for(int i = 0; i < N ;i++) {
			tokens = new StringTokenizer(read.readLine());
			for(int j = 0; j < N; j ++) {
				maps[i][j] = Integer.parseInt(tokens.nextToken());
				if(maps[i][j] == 9) {
					maps[i][j] = 0;
					shark = new int[] {i,j,2};
				}else if(maps[i][j] != 0) {
					Fishes.add(new Fish(i,j,0));
				}
			}
		}
		
		fishsize = Fishes.size();
		while(fishsize > 0) {
			BFSdist();
			fishsize--;
		}
		System.out.println(TotalCount);
		
	}
	private static int[][] deltas = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	
	private static int XYtoInt(int x, int y) {
		return x*N + y;
	}
	
	private static void BFSdist() {
		PriorityQueue<Fish> PQ = new PriorityQueue<>(); 
		Queue<int[]> BFSQ = new LinkedList<int[]>();
		boolean [] visits = new boolean[N*N];
		BFSQ.add(new int [] {shark[0], shark[1], 0});	//마지막은 time
		
		while(!BFSQ.isEmpty()) {
			int [] temp = BFSQ.poll();
			for(int i = 0; i < 4 ; i++) {
				int tempx = temp[0] + deltas[i][0];
				int tempy = temp[1] + deltas[i][1];
				
				if(!isIn(tempx, tempy)) {
					continue;
				}
				if(visits[XYtoInt(tempx,tempy)] == true) {
					continue;
				}
				
				if(maps[tempx][tempy] > shark[2]) { //커
					continue;
				}
				
				visits[XYtoInt(tempx, tempy)] = true;
				if(maps[tempx][tempy] < shark[2] && maps[tempx][tempy] != 0) {	//나보다 작아
					PQ.add(new Fish(tempx, tempy, temp[2]+1));	//먹어야 할 물고기 좌표[2]와 걸리는 시간 = 거리
					continue;
				}
				visits[XYtoInt(tempx,tempy)] = true;
				BFSQ.add(new int [] {tempx, tempy, temp[2]+1});
				
			}
		}
		if(PQ.isEmpty()) return;
		//정렬이 돼써
		Fish tempfish = PQ.poll();
		shark[0] = tempfish.x;
		shark[1] = tempfish.y;
		maps[tempfish.x][tempfish.y] = 0;
		//위치 갱신
		TotalEat++;
		if(TotalEat == shark[2]) {
			shark[2] = TotalEat+1;
			TotalEat = 0;
		}//사이즈 갱신
		TotalCount += tempfish.dist;
		
	}
	static int TotalEat;
	static int TotalCount;
	
	private static boolean isIn(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}
	private static class Fish implements Comparable<Fish>{
		int x;
		int y;
		int dist;
		public Fish(int x, int y, int dist) {
			super();
			this.x = x;
			this.y = y;
			this.dist = dist;
		}
		@Override
		public int compareTo(Fish o) {
			if(this.dist == o.dist) {
				if(this.x == o.x) {
					return Integer.compare(this.y, o.y);
				}
				return Integer.compare(this.x, o.x);
			}
			return Integer.compare(this.dist, o.dist);
		}
	}
}
