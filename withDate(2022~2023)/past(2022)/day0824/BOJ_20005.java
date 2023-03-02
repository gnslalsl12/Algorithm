package day0824;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_20005 {
	static int M, N, P;
	static dirXY Boss;
	static ArrayList<dirXY> Players;
	static int [][] maps;
	private static int[][] deltas = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		
		M = Integer.parseInt(tokens.nextToken());
		N = Integer.parseInt(tokens.nextToken());
		P = Integer.parseInt(tokens.nextToken());
		maps = new int [M][N];
		Players = new ArrayList<>();
		for(int i = 0; i < M; i++) {
			String templine = read.readLine();
			for(int j = 0; j < N; j++) {
				char temp = templine.charAt(j);
				if(temp == '.') {
					maps[i][j] = 0;
				}else if(temp == 'B') {	//보스도 움직일 수 있는 곳임 ( 방문 체크로 거를 예정)
					maps[i][j] = 0;
					Boss = new dirXY('B', i,j,0);
				}else if(temp == 'X') {
					maps[i][j] = -1;	//이동 불가
				}else {
					maps[i][j] = 0;		//플레이도 일단 이동은 가능한 곳
					Players.add(new dirXY(temp,i,j,0));
				}
				
			}
		}
		for(int i = 0; i< P; i++) {
			tokens = new StringTokenizer(read.readLine());
			char alpha = tokens.nextToken().charAt(0);
			int power = Integer.parseInt(tokens.nextToken());
			for(int j = 0; j< P; j++) {
				if(Players.get(j).Alpha == alpha) {
					Players.get(j).setPower(power);	//파워 넣어줌
				}
			}
		}
		PriorityQueue<dirXY> FasterAndFaster = new PriorityQueue<>();
		for(dirXY players : Players) {
			BFS(players);	//시간을 측정해서
//			System.out.println(players.toString());
			FasterAndFaster.add(players);	//PQ에 넣어줌
//			Players.remove(players);	//빼줌
		}
		
		int BossHP = Integer.parseInt(read.readLine());
		int time = 0;
		int TotalDamage = 0;
		int Pcount = 0;
		while(BossHP > 0) {	//보스가 죽으면 나가
			if(FasterAndFaster.peek().time == time) {//도착하느 시간이면
				TotalDamage += FasterAndFaster.poll().power;
				Pcount++;
				if(FasterAndFaster.isEmpty()) {	//모두 도착하면 나가
					break;
				}
				continue;
			}
//			System.out.println(BossHP);
			BossHP -= TotalDamage;
			time ++;
			if(FasterAndFaster.isEmpty()) {	//모두 도착하면 나가
				break;
			}
		}
		System.out.println(Pcount);
		
		
	}
	private static boolean isIn(int x, int y) {
		return x >= 0 && x < M && y >= 0 && y < N;
	}

	
	private static void BFS(dirXY input) {
		Queue<dirXY> BFSQ = new LinkedList<>();
		
		BFSQ.add(input);
		
		while(!BFSQ.isEmpty()) {
			dirXY now = BFSQ.poll();
			
			for(int i = 0; i < 4 ; i++) {
				int nextx = now.x + deltas[i][0];
				int nexty = now.y + deltas[i][1];
				
				if(!isIn(nextx,nexty)) {
					continue;
				}
				
				if(maps[nextx][nexty] == -1) { //이동 불가
					continue;
				}
				
				if(nextx == Boss.x && nexty == Boss.y) { // 도착
					input.setTime(now.time+1);	//도착한 시간을 추가
					return;
				}
				
				if(maps[nextx][nexty] == 0) { //이동 가능
					BFSQ.add(new dirXY(now.Alpha,nextx, nexty, now.time+1));
				}
				
			}
			
			
			
			
		}
		
	}
	
	
	
	private static class dirXY implements Comparable<dirXY> {
		char Alpha;
		int x;
		int y;
		int time;
		int power;

		public dirXY(char alpha, int x, int y, int time) {
			super();
			this.Alpha = alpha;
			this.x = x;
			this.y = y;
			this.time = time;
		}

		public void setTime(int time) {
			this.time = time;
		}

		public void setPower(int power) {
			this.power = power;
		}

		@Override
		public int compareTo(dirXY o) {
			// TODO Auto-generated method stub
			return Integer.compare(this.time, o.time);
		}

		@Override
		public String toString() {
			return "플레이어 [이름=" + Alpha +/* ", x=" + x + ", y=" + y +*/ ", time=" + time + ", power=" + power + "]";
		}
		
		
		
	}
	
}
