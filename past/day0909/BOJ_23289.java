package day0909;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_23289 {
	static int R, C, K;	//맵 크기 [R+1][C+1]		원하는 온도 K
	static int[][] deltas = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	static int [][] OriginMaps;
	static int W;
	static ArrayList<int[][]> BlowMapping = new ArrayList<>();	//바람의 템프 저장값
	static ArrayList<dirXY> WindBlowers = new ArrayList<>();
	static ArrayList<dirXY> CheckPoint = new ArrayList<>();
	static dirXY [][] Walls;
	public static void main(String[] args) throws IOException, CloneNotSupportedException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		R = Integer.parseInt(tokens.nextToken());
		C = Integer.parseInt(tokens.nextToken());
		K = Integer.parseInt(tokens.nextToken());
		OriginMaps = new int[R+1][C+1];
		
		for(int i = 1; i <= R; i++) {
			tokens = new StringTokenizer(read.readLine());
			for(int j = 1; j <= C; j++) {
				OriginMaps[i][j] = Integer.parseInt(tokens.nextToken());
				if(OriginMaps[i][j] == 5) {
					CheckPoint.add(new dirXY(i,j));
					OriginMaps[i][j] = 0;
				}	//체크포인트 확인
				else if(OriginMaps[i][j] != 0) {
					WindBlowers.add(new dirXY(i,j,OriginMaps[i][j],5));	//온풍기 추가
					OriginMaps[i][j] = 0;
				}
			}
		}
		
		W = Integer.parseInt(read.readLine());
		Walls = new dirXY[W][2];
		for(int i = 0; i < W; i++) {
			tokens = new StringTokenizer(read.readLine());
			int x = Integer.parseInt(tokens.nextToken());
			int y = Integer.parseInt(tokens.nextToken());
			int t = Integer.parseInt(tokens.nextToken());
			if(t == 0) {
				Walls[i] = new dirXY[] {new dirXY(x-1,y), new dirXY(x, y)};
			}else {
				Walls[i] = new dirXY[] {new dirXY(x,y), new dirXY(x, y+1)};
			}
		}
		int [][] BlowTempMap = new int [R+1][C+1];
		for(dirXY WB : WindBlowers) {
			BlowMapping.add(MakeBlow(WB));	//온풍기 하나당 맵 만들기
		}
		for(int n = 0; n < BlowMapping.size(); n++) {	//온풍기 맵 하나씩 더해주기
			for(int i = 1; i <= R; i++) {
				for(int j = 1; j <= C; j++) {
					BlowTempMap[i][j] += BlowMapping.get(n)[i][j];
				}
			}
		}
		
		int Chocolate = 1;
		for(Chocolate = 1; ; Chocolate++) {
			boolean FinalCheck = true;
			
			for(int i = 1; i <= R; i++) {		//온풍기 켜기
				for(int j = 1; j <= C; j++) {
					OriginMaps[i][j] += BlowTempMap[i][j];
				}
			}
			
			SpreadTemp();	//온도 흩뿌리기
			
			DescEdge();		//엣지 온도 -1

			//초콜릿 함냐
			
			for(dirXY Check : CheckPoint) {	//온도 체크하기
				if(OriginMaps[Check.x][Check.y] < K) {
					FinalCheck = false;
					break;
				}
			}
			if(Chocolate == 101) break;
			if(!FinalCheck) continue;
			break;
		}
		System.out.println(Chocolate);
	}
	
	private static void DescEdge() {		//가장자리 -1
		for(int i = 1; i <= R; i++) {
			for(int j = 1; j <= C; j++) {
				if(i == 1) {
					if(OriginMaps[i][j] == 0) continue;
					OriginMaps[i][j] -= 1;
				}else if(i == R) {
					if(OriginMaps[i][j] == 0) continue;
					OriginMaps[i][j] -= 1;
				}else if(j == 1 || j == C) {
					if(OriginMaps[i][j] == 0) continue;
					OriginMaps[i][j] -= 1;
				}
			}
		}
	}
	
	private static void SpreadTemp() {	//온도 흩뿌리기
		int [][] tempMap = new int [R+1][C+1];
		for(int i = 1; i <= R; i++) {
			for(int j = 1; j <= C; j++) {
				if(OriginMaps[i][j] != 0) {
					for(int dir = 0; dir < 4; dir++) {
						int searchx = i + deltas[dir][0];
						int searchy = j + deltas[dir][1];
						if(!isIn(searchx, searchy)) continue;	//범위 밖
						if(!MeetWall(new dirXY(i,j), new dirXY(searchx, searchy))) {
							continue;	//벽 만남
						}
						int gap = (OriginMaps[i][j] - OriginMaps[searchx][searchy])/4;	//gap > 0 : 현재 위치가 더 높다
						if(gap > 0) {
							tempMap[i][j] -= gap;
							tempMap[searchx][searchy] += gap;
						}
					}
				}
			}
		}
		for(int i = 1; i <= R; i++) {
			for(int j = 1; j <= C; j++) {
				OriginMaps[i][j] += tempMap[i][j];
				if(OriginMaps[i][j] < 0) OriginMaps[i][j] = 0;
			}
		}
	}
	
	private static int [][] MakeBlow(dirXY Blower) throws CloneNotSupportedException {
		int [][] MadeBlowMaps = new int [R+1][C+1];	//바람이 분 상태를 저장하는 맵
		boolean [][] visited = new boolean [R+1][C+1];
		Queue<dirXY> BFSQ = new LinkedList<>();
		visited[Blower.x][Blower.y]= true;
		
		if(Blower.wind == 1){	//바람의 시작점 세팅
			BFSQ.add(new dirXY(Blower.x, Blower.y + 1, Blower.wind, Blower.temp));
		}else if(Blower.wind == 2) {
			BFSQ.add(new dirXY(Blower.x, Blower.y - 1, Blower.wind, Blower.temp));
		}else if(Blower.wind == 3) {
			BFSQ.add(new dirXY(Blower.x - 1, Blower.y, Blower.wind, Blower.temp));
		}else if(Blower.wind == 4) {
			BFSQ.add(new dirXY(Blower.x + 1, Blower.y, Blower.wind, Blower.temp));
		}
		
		while(!BFSQ.isEmpty()) {
			dirXY temp = BFSQ.poll();
			MadeBlowMaps[temp.x][temp.y] = temp.temp; 
			if(temp.temp == 1) continue;		//다음으로 보낼 온도가 없음
			
			switch(temp.wind) {
			
			case(1):{	//오른쪽
				int nextx = temp.x;
				int nexty = temp.y;
				dirXY next = new dirXY(nextx, nexty, temp.wind, temp.temp - 1);
				
				//바로 오른쪽
				next.y = temp.y  + 1;
				if(isIn(next.x, next.y) && MeetWall(temp, next) && visited[next.x][next.y] == false ) {	//범위 안이고 벽이 없고 방문 안 한 곳
					visited[next.x][next.y] = true; 
					BFSQ.add( (dirXY) next.clone());
				}
				
				//오른쪽 위
				next.x = temp.x - 1;
				next.y = temp.y;
				if(isIn(next.x, next.y) && MeetWall(temp,next) && visited[next.x][next.y] == false ) {	//위로 가는 건 OK
					dirXY tempbefore = (dirXY) next.clone();
					next.y = next.y + 1;
					if(isIn(next.x, next.y) && MeetWall(tempbefore,next)) {	//위로 가서 오른쪽으로 가는 것도 OK
						visited[next.x][next.y] = true; 
						BFSQ.add( (dirXY) next.clone());
					}
				}
				
				//오른쪽 아래
				next.x = temp.x + 1;
				next.y = temp.y;
				if(isIn(next.x, next.y) && MeetWall(temp,next) && visited[next.x][next.y] == false ) {	//아래로 가는 건 OK
					dirXY tempbefore = (dirXY) next.clone();
					next.y = next.y + 1;
					if(isIn(next.x, next.y) && MeetWall(tempbefore,next)) {	//위로 가서 오른쪽으로 가는 것도 OK
						visited[next.x][next.y] = true; 
						BFSQ.add( (dirXY) next.clone());
					}
				}
				break;
			}
			case(2):{	//왼쪽
				int nextx = temp.x;
				int nexty = temp.y;
				dirXY next = new dirXY(nextx, nexty, temp.wind, temp.temp - 1);
				
				//바로 왼쪽
				next.y = temp.y  - 1;
				if(isIn(next.x, next.y) && MeetWall(temp, next) && visited[next.x][next.y] == false ) {	//범위 안이고 벽이 없고 방문 안 한 곳
					visited[next.x][next.y] = true; 
					BFSQ.add( (dirXY) next.clone());
				}
				
				//왼쪽 위
				next.x = temp.x - 1;
				next.y = temp.y;
				if(isIn(next.x, next.y) && MeetWall(temp,next) && visited[next.x][next.y] == false ) {	//위로 가는 건 OK
					dirXY tempbefore = (dirXY) next.clone();
					next.y = next.y - 1;
					if(isIn(next.x, next.y) && MeetWall(tempbefore,next)) {	//위로 가서 왼쪽으로 가는 것도 OK
						visited[next.x][next.y] = true; 
						BFSQ.add( (dirXY) next.clone());
					}
				}
				
				//왼쪽 아래
				next.x = temp.x + 1;
				next.y = temp.y;
				if(isIn(next.x, next.y) && MeetWall(temp,next) && visited[next.x][next.y] == false ) {	//아래로 가는 건 OK
					dirXY tempbefore = (dirXY) next.clone();
					next.y = next.y - 1;
					if(isIn(next.x, next.y) && MeetWall(tempbefore,next)) {	//위로 가서 왼쪽 가는 것도 OK
						visited[next.x][next.y] = true; 
						BFSQ.add( (dirXY) next.clone());
					}
				}
				break;
			}
			case(3):{	//위로
				int nextx = temp.x;
				int nexty = temp.y;
				dirXY next = new dirXY(nextx, nexty, temp.wind, temp.temp - 1);
				
				//바로 위로
				next.x = temp.x  - 1;
				if(isIn(next.x, next.y) && MeetWall(temp, next) && visited[next.x][next.y] == false ) {	//범위 안이고 벽이 없고 방문 안 한 곳
					visited[next.x][next.y] = true; 
					BFSQ.add( (dirXY) next.clone());
				}
				
				//왼쪽 위
				next.x = temp.x;
				next.y = temp.y - 1;
				if(isIn(next.x, next.y) && MeetWall(temp,next) && visited[next.x][next.y] == false ) {	//왼쪽 ok
					dirXY tempbefore = (dirXY) next.clone();
					next.x = next.x - 1;
					if(isIn(next.x, next.y) && MeetWall(tempbefore,next)) {	//왼쪽 후 위도 OK
						visited[next.x][next.y] = true; 
						BFSQ.add( (dirXY) next.clone());
					}
				}
				
				//오른쪾 위
				next.x = temp.x;
				next.y = temp.y + 1;
				if(isIn(next.x, next.y) && MeetWall(temp,next) && visited[next.x][next.y] == false ) {	//오른쪽
					dirXY tempbefore = (dirXY) next.clone();
					next.x = next.x - 1;
					if(isIn(next.x, next.y) && MeetWall(tempbefore,next)) {	//오른쪽 후 위
						visited[next.x][next.y] = true; 
						BFSQ.add( (dirXY) next.clone());
					}
				}
				break;
			}
			case(4):{	//아래로
				int nextx = temp.x;
				int nexty = temp.y;
				dirXY next = new dirXY(nextx, nexty, temp.wind, temp.temp - 1);
				
				//바로 아래
				next.x = temp.x  + 1;
				if(isIn(next.x, next.y) && MeetWall(temp, next) && visited[next.x][next.y] == false ) {	//범위 안이고 벽이 없고 방문 안 한 곳
					visited[next.x][next.y] = true; 
					BFSQ.add( (dirXY) next.clone());
				}
				
				//왼쪽 아래
				next.x = temp.x;
				next.y = temp.y - 1;
				if(isIn(next.x, next.y) && MeetWall(temp,next) && visited[next.x][next.y] == false ) {	//왼쪽 ok
					dirXY tempbefore = (dirXY) next.clone();
					next.x = next.x + 1;
					if(isIn(next.x, next.y) && MeetWall(tempbefore,next)) {	//왼쪽 후 아래도 OK
						visited[next.x][next.y] = true; 
						BFSQ.add( (dirXY) next.clone());
					}
				}
				
				//오른쪽 아래
				next.x = temp.x;
				next.y = temp.y + 1;
				if(isIn(next.x, next.y) && MeetWall(temp,next) && visited[next.x][next.y] == false ) {	//오른쪽
					dirXY tempbefore = (dirXY) next.clone();
					next.x = next.x + 1;
					if(isIn(next.x, next.y) && MeetWall(tempbefore,next)) {	//오른쪽 후 위
						visited[next.x][next.y] = true; 
						BFSQ.add( (dirXY) next.clone());
					}
				}
				break;
			}
			}
		}
		return MadeBlowMaps;
	}
	
	private static boolean isIn(int x, int y) {
		return x >= 1 && y >= 1 && x <= R && y <= C;
	}
	
	private static boolean MeetWall(dirXY startpoint, dirXY destpoint) {	//sp에서 dp로 가려는데 벽이 있는가? true/false
		dirXY [] temp = dirXYtoCheck(startpoint, destpoint);
		for(int i = 0; i < W; i++) {
			if(Walls[i][0].equals(temp[0]) && Walls[i][1].equals(temp[1])) return false;
		}
		return true;
	}
	
	private static dirXY[] dirXYtoCheck(dirXY A, dirXY B) {	//벽 체크용 정렬
		if(A.x == B.x) {
			if(A.y > B.y) {
				return new dirXY[] {B,A};
			}else {
				return new dirXY[] {A,B};
			}
		}else {
			if(A.x > B.x) {
				return new dirXY[] {B,A};
			}else {
				return new dirXY[] {A,B};
			}
		}
	}
	
	private static class dirXY implements Cloneable{
		int x;
		int y;
		int wind;
		int temp;

		public dirXY(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

		public dirXY(int x, int y, int wind, int temp) {
			super();
			this.x = x;
			this.y = y;
			this.wind = wind;
			this.temp = temp;
		}
		@Override
		protected Object clone() throws CloneNotSupportedException {
			return super.clone();
		}

		public boolean equals(dirXY o) {
			return this.x == o.x && this.y == o.y;
		}
	}
}