package day0824;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_16236_re {
	static int [][] maps;
	static dirXYShark Shark;
	static int N;
	static ArrayList<dirXYFish> Fishes = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		
		N = Integer.parseInt(tokens.nextToken());
		maps = new int [N][N];
		for(int i = 0; i < N; i++) {
			tokens = new StringTokenizer(read.readLine());
			for(int j = 0; j < N; j++) {
				maps[i][j] = Integer.parseInt(tokens.nextToken());
				if(maps[i][j] == 9) {
					Shark = new dirXYShark(i,j,2);
				}else if(maps[i][j] != 0) {
					Fishes.add(new dirXYFish(i, j, maps[i][j]));
				}
			}
		}//물고기 정렬
		TotalEat = 0;
		
		Collections.sort(Fishes);
		breakall:
		//상어보다 사이즈가 작으 ㄴ것들만 골라서 거리순으로 정렬
		while(!Fishes.isEmpty()) {

			PriorityQueue<dirXYFish> tempPQ = new PriorityQueue<>();
			for(dirXYFish tempfish : Fishes) {
				if(tempfish.size >= Shark.size) {	//보려는 물고기가 지금 못 먹는 물고기들임
					break;
				}
				if(Shark.size > tempfish.size) {//사이즈가 작은 것들만 골라서
					tempfish.setDist(getDist(tempfish));	//상어로부터 거리 구해서 dist값 크래스에 넣어줌
					tempPQ.add(tempfish);
//					System.out.println("현재 볼 물고기 : ");
//					System.out.println(tempPQ.toString());
					Fishes.remove(tempfish);	//뺴내서 삭제
				}
				
				if(Fishes.isEmpty()) {
					break;
				}
			}
			//temppq에는 같은 사이즈 걷ㅅ들만
			
			while(!tempPQ.isEmpty()) {
				Ate = false;
				//가까운 것들부터 하나씩 BFS 돌려야함
				dirXYFish tempfish = tempPQ.poll();
				System.out.println("현재 상어 : ");
				System.out.println(Shark.toString());
				System.out.println("찾아가는 물고기 저정보 : ");
				System.out.println(tempfish.toString());
				System.out.println("++++++++++++++++++++++++++++");
				
				BFS(tempfish, Shark.x, Shark.y, new boolean [N*N], 0);
				if(!Ate) {	//작은 거 부터 먹을럤는데 못 먹ㅇ므
					break breakall;
				}
				if(Shark.size == TotalEat) { //상어 사이즈만큼 먹음
					Shark.size = TotalEat+1;
					TotalEat = 0;
				}
				
			}
			
			
		}
		System.out.println(TotalTime);
		
		
	}
	private static int XYtoInt(int x, int y) {
		return N*x + y;
	}
	static int TotalTime;
	static boolean Ate;
	static int TotalEat;
	private static int[][] deltas = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	static void BFS(dirXYFish input,int x, int y, boolean[] visits, int time) {
		if(x == input.x && y == input.y) {	//도달함
			TotalEat++; //하나 무금
			maps[x][y] = 0;	// 먹음 처리
			Shark.x = x;
			Shark.y = y; //상어 이동함
			Ate = true; //먹음
			TotalTime += time;
			return;
		}
		
		for(int i = 0; i< 4 ; i++) {
			int nextx = x + deltas[i][0];
			int nexty = y = deltas[i][1];
			System.out.println("탐색하려는 위치 : " + nextx + ", " + nexty);
			if(!isIn(nextx, nexty)) {
				continue;	//범위 밖
			}
			if(maps[nextx][nexty] > Shark.size) {	//현재 상어보다 크기가 큼 = > 지나갈 수 없음
				System.out.println(nextx + ", " + nexty + " 물고기는 나보다 커서 지나갈 수 없어!");
				continue;
			}
			if(visits[XYtoInt(nextx, nexty)] == true) {
				System.out.println(nextx + ", " + nexty + "는 이미 지나간 위치야");
				continue; //방문함
			}
			
			//방문하지 않았고 나보다 같거나 작고 범위 안임 => 지나갈 수 있음
			visits[XYtoInt(nextx, nexty)] = true;
			System.out.println(nextx + ", " + nexty + "갈 수이떠");
			BFS(input, nextx, nexty, visits, time + 1);
			
			visits[XYtoInt(nextx, nexty)] = false;
		}
		
		
		
	}
	private static boolean isIn(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}
	

	static class dirXYShark{
		int x;
		int y;
		int size;
		public dirXYShark(int x, int y, int size) {
			super();
			this.x = x;
			this.y = y;
			this.size = size;
		}
		@Override
		public String toString() {
			return "상어 :  [x=" + x + ", y=" + y + ", size=" + size + "]";
		}
		
		
		
		
		
	}
	
	static int getDist(dirXYFish fish) {
		return Math.abs(Shark.x - fish.x) + Math.abs(Shark.y - fish.y);
	}
	
	static class dirXYFish implements Comparable<dirXYFish>{
		int x;
		int y;
		int size;
		int dist;
		public dirXYFish(int x, int y, int size) {
			super();
			this.x = x;
			this.y = y;
			this.size = size;
		}
		public int getDist() {
			return dist;
		}
		public void setDist(int dist) {
			this.dist = dist;
		}
		@Override
		public int compareTo(dirXYFish o) {
			if(this.size == o.size) {
				if(this.dist == o.dist) {
					if(this.x == o.x) {
						return Integer.compare(this.y, o.y);
					}
					return Integer.compare(this.x, o.x);
				}
				return Integer.compare(this.dist, o.dist);
			}
			return Integer.compare(this.size, o.size);
		}
		@Override
		public String toString() {
			return "dirXYFish [x=" + x + ", y=" + y + ", 물고기 사이즈 =" + size + ", 상어로부터 거리=" + dist + "]";
		}
		
		
	}
}
