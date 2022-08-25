package day0824;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_20005_re {
	static int M, N, P;
	static int [][] maps;
	static int[][] deltas = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	static boolean [] visits;
	static dirXY Boss;
	static ArrayList<dirXY> Players = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		
		M = Integer.parseInt(tokens.nextToken());
		N = Integer.parseInt(tokens.nextToken());
		P = Integer.parseInt(tokens.nextToken());
		maps = new int [M][N];
		for(int i = 0; i < M; i++) {
			String templine = read.readLine();
			for(int j = 0; j < N; j++) {
				if(templine.charAt(j) == 'B') {			//보스도 0
					maps[i][j] = 0;
					Boss = new dirXY(i,j);
				}else if(templine.charAt(j) == 'X') {	//지나갈 수 없는 곳은 -1
					maps[i][j] = -1;
				}else if(templine.charAt(j) == '.') {	//지가갈 수 있는 곳은 0
					maps[i][j] = 0;
				}else {									//플레이어도 밟고 갈 수 있으므로 0
					maps[i][j] = 0;
					Players.add(new dirXY(i,j,templine.charAt(j)));	//현재 플레이어들의 파워는 자신 이름의 고유 값
				}	//이름 선언
			}
		}
		
		for(int i = 0; i < P; i++) {
			tokens = new StringTokenizer(read.readLine());
			char name = tokens.nextToken().charAt(0);
			int pow = Integer.parseInt(tokens.nextToken());
			for(dirXY tempP : Players) {
				if(tempP.name == name) {
					tempP.setPower(pow);
				}
			}
		}//플레이어마다 이름 찾아서 클래스에 파워값 입력 완료
		
		
		int HP = Integer.parseInt(read.readLine());	//보스의 파워는 체력
		
		for(dirXY pop : Players) {
			visits = new boolean [N*M];
			BFSRE(pop);
		}	//플레이어마다 시간 넣어줌 (시간과 파워를 각자 갖고있음
		Collections.sort(Players);
		
		int Damage = 0;
		int playercount = 0;
		for(int time = 0; playercount < Players.size() ; ) {
			if(HP <= 0) {
				break;
			}
			if(Players.get(playercount).timecount == time) {
				Damage += Players.get(playercount).Power;
				playercount++;
				continue;
			}
			//현재 시간에는 더 들어오는 애가 없음
			HP -= Damage;
			time++;
			
		}
		
		System.out.println(playercount);

	}
	
	private static int XYtoInt(int x, int y) {	//좌표 visist 처리 간편화
		return N*x + y;
	}
	
	private static void BFSRE(dirXY input) {
		Queue<dirXY> BFSQ = new LinkedList<>();
		BFSQ.add(input);
		visits[XYtoInt(input.x, input.y)] = true;	//현재 위치 방문처리
		
		while(!BFSQ.isEmpty()){
			dirXY now = BFSQ.poll();
			for(int dir = 0; dir < 4; dir++) {
				int tempx = now.x + deltas[dir][0];
				int tempy = now.y + deltas[dir][1];
				
				if(!isIn(tempx, tempy)) {
					continue;
				}//범위 밖
				
				if(visits[XYtoInt(tempx, tempy)] == true) {
					continue;
				}//이미 지나옴
				
				if(maps[tempx][tempy] == -1) {
					continue;
				}//지나갈 수 없음
				if(tempx == Boss.x && tempy == Boss.y) { //보스에 도착
					input.setTimecount(now.timecount+1);
					return;
				}
				visits[XYtoInt(tempx, tempy)] = true;
				BFSQ.add(new dirXY(tempx, tempy, now.timecount+1, input.Power, input.name));
			}
		}
	}

	private static boolean isIn(int x, int y) {
		return x >= 0 && x < M && y >= 0 && y < N;
	}
	
	private static class dirXY implements Comparable<dirXY> {
		int x;
		int y;
		int Power = 0;
		int timecount = 0;
		char name;
		
		public dirXY(int x, int y, int timecount, int power, char name) {
			super();
			this.x = x;
			this.y = y;
			this.timecount = timecount;
			this.Power = power;
			this.name = name;
		}

		public dirXY(int x, int y, char name) {
			super();
			this.x = x;
			this.y = y;
			this.name = name;
		}

		public void setTimecount(int timecount) {
			this.timecount = timecount;
		}
		
		public void setPower(int power) {
			Power = power;
		}

		public dirXY(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(dirXY o) {
			return Integer.compare(this.timecount, o.timecount);
		}
	}

}
