package day0824;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_16236 {
	static int N;
	static int [][] maps;
	static dirXY Shark;
	static ArrayList<dirXY> Fishes = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		maps = new int [N][N];
		for(int i = 0; i < N; i++) {
			tokens = new StringTokenizer(read.readLine());
			for(int j = 0; j < N; j++) {
				maps[i][j] = Integer.parseInt(tokens.nextToken());
				if(maps[i][j]  == 9) {	//상어임
					Shark = new dirXY(i,j,2);
				}else if(maps[i][j] != 0) {	//믈고기임
					Fishes.add(new dirXY(i,j,maps[i][j]));
				}
			}
		}
		DistFromShark();	//거리 계산해서 넣어주기
		
		Collections.sort(Fishes); //사이즈순 - 거리순 - 위에있는 - 왼쪽에 있는
		for(int i = 0; i < Fishes.size(); i++) {
			if(Fishes.get(i).size >= Shark.size) { //하나씩 뺴봤는데 더이상 먹을 수 있는 게 없음
				break;
			}
			System.out.println("목표 생선 : " );
			System.out.println(Fishes.get(i).toString());
			BFSShark(Fishes.get(i));//하나씩 해보기
			if(CouldEat) {//하나 먹음 (이동함)
				DistFromShark();
				Collections.sort(Fishes);
			}
			System.out.println("================");
			for(int[] m : maps) {
				for(int mm : m) {
					System.out.printf("%d ",mm);
				}
				System.out.println();
			}
			System.out.println();
			System.out.println("================");
		}
		
		System.out.println(Shark.time);
		
		
		
	}

	
	///////////////////////////////////
	private static void DistFromShark() {	//현재의 위치에서 거리 구해주기
		for(dirXY tempF : Fishes) {
			tempF.setDist(getDist(tempF));
		}
		
	}
	
	private static int getDist(dirXY f) {	//상어에서 해당 물고기까지 거리
		return Math.abs(Shark.x - f.x) + Math.abs(Shark.y - f.y);
	}
	///////////////////////////////////
	static int EatCount = 0;
	private static int[][] deltas = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	//지나온 경로는 -1로 처리
	
	static boolean CouldEat;
	
	private static boolean BFSShark(dirXY TargetFish) {	//가장 가까운 물고기한테 접근 가능한가
		if(Shark.size <= TargetFish.size) { //지금 먹을 수 없음
			return CouldEat = false;
		}
		System.out.println("BFS 시작");
		Queue<dirXY> BFSQ = new LinkedList<>();
		BFSQ.add(Shark);
		
		while(!BFSQ.isEmpty()) {
			System.out.println();
			System.out.println("현재 상어 : " + Shark.toString());
			
			
			
			dirXY now = BFSQ.poll();
			now.setVisits();
			
			
			maps[now.x][now.y] *= -1; //지나왔지만 먹진 않음
			for(int i = 0; i < 4; i++) {
				int nextx = now.x + deltas[i][0];
				int nexty = now.y + deltas[i][1];
				if(!isIn(nextx,nexty)) {	//범위 벗어남
					System.out.println("범위 벗어남");
					continue;
				}
				if(nextx == TargetFish.x && nexty == TargetFish.y) { //목적지에 도착
					System.out.println("목적지에 도착!!");
					maps[nextx][nexty] = 0;	//물고기 삭제
					EatCount++;
					Shark.setXY(nextx, nexty); //이동
					if(EatCount == Shark.size) {
						Shark.time += now.time;
						Shark.setSize(EatCount+1);// 크기 하나 중가
						EatCount = 0;
					}
					System.out.println("현재 상어 : " + Shark.toString());
					for(int ii = 0; ii < N; ii++) {
						for(int j = 0; j < N; j++) {
							if(maps[ii][j] == -1) {
								maps[ii][j] = 0;
							}
						}
					}
					return true;
				}
				
				if(maps[nextx][nexty] > Shark.size) { //나보다 큰 물고기면 지나갈 수 없음
					System.out.println("큰 물고기를 만나게됨 (위치 : " + nextx + ", " + nexty);
					continue;
					
				}
				if(maps[nextx][nexty] < 0) {	// 지나온 위치
					continue;
				}
				//나와 같은 물고기이고 지나갈 수 있음
				System.out.println("지나갈 수 있음, 위치 : " + nextx + ", " + nexty);
				BFSQ.add(new dirXY(nextx, nexty, now.size, now.time + 1));
				
			}
			
			
		}
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(maps[i][j] == -1) {
					maps[i][j] = 0;
				}
			}
		}
		return false;	//도달할 수 없음
	}
	
	private static int MakeXY(int x, int y) {
		return x*100 + y;
	}
	
	private static boolean isIn(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}
	
	private static class dirXY implements Comparable<dirXY>{
		int x;
		int y;
		int size;
		int dist;
		int time = 0;
		boolean [] visits;
		public dirXY(int x, int y, int size) {
			super();
			this.x = x;
			this.y = y;
			this.size = size;
		}
		public void setXY(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		
		public boolean[] getVisits() {
			return visits;
		}
		public void setVisits(boolean[] visits) {
			this.visits = visits;
		}
		public dirXY(int x, int y, int size, int dist, int time, boolean[] visits) {
			super();
			this.x = x;
			this.y = y;
			this.size = size;
			this.dist = dist;
			this.time = time;
			this.visits = visits;
		}
		public dirXY(int x, int y, int size, int time) {
			super();
			this.x = x;
			this.y = y;
			this.size = size;
			this.time = time;
		}
		public void setTime(int time) {
			this.time = time;
		}

		public void setSize(int size) {
			this.size = size;
		}


		public void setDist(int dist) {
			this.dist = dist;
		}
		@Override
		public int compareTo(dirXY o) {
			if(this.size == o.size) {
				if(this.dist == o.dist) {
					if(this.x == o.x) {
						return Integer.compare(this.y, o.y);	//가장 왼쪽에 있는
					}
					return Integer.compare(this.x, o.x);	//가장 위에있고
				}
				return this.dist - o.dist;	//거리가 가장 가깝고
			}
			return this.size - o.size;	//사이즈가 작은 거 부턴
		}
		@Override
		public String toString() {
			return "[x=" + x + ", y=" + y + ", size=" + size + ", 상어에서부터 =" + dist + ", time=" + time + "]";
		}
		
	}
	
	
	
}
