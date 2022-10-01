package day0903;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_17472 {
	static int N, M;
	static int [][] maps;
	static int[][] deltas = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	static ArrayList<ArrayList<dirXY>> IslandsXY = new ArrayList<>();
	static ArrayList<int []> selectedIsl;
	static ArrayList<ConIsl> SetBridgeIslands = new ArrayList<>();
	static int IslCount;
	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		maps = new int [N][M];
		for(int i = 0; i < N; i++) {
			tokens = new StringTokenizer(read.readLine());
			for(int j = 0; j < M; j++) {
				int n = Integer.parseInt(tokens.nextToken());
				if(n == 1) n = -1;
				maps[i][j] = n;
			}
		}//mapping
		
		IslCount = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(maps[i][j] == -1) NumberingIsland(i,j);	//섬들의 좌표 IslandsXY 완료
			}
		}
		
		selectedIsl = new ArrayList<>();
		//맵마다 번호 부여 OK
		Comb(0, 1, new int [2]);	//맵 조합 완료	(selectedIsl)
		
		//맵 두 개씩 뽑아서 연결 가능한 맵인지 판별
		for(int [] sel : selectedIsl) {
			CheckAvailConnect(sel[0], sel[1]);
		}
		int result = 0;
		for(int i = 0; i < selectedIsl.size(); i++) {
		}
		
		//맵 두 개마다 연결 가능한 경우를 SetbridgeIslands에 두 섬의 이름과 다리 길이로 저장함
		boolean [] IslCheck = new boolean [10];
		int bridgecount = 0;
		int IslandCount = 0;
		Collections.sort(SetBridgeIslands);
		if(SetBridgeIslands.size() != 0) {
			IslCheck[SetBridgeIslands.get(0).A] = true;
		}
		int[] rotatecheck = new int [100];
		while(!SetBridgeIslands.isEmpty()) {
			ConIsl Final = SetBridgeIslands.remove(0);
			if(IslCheck[Final.A] == false && IslCheck[Final.B] == false) {	//아직 떠중이 섬이면 뒤로 미룸
				if(rotatecheck[Final.A*10 + Final.B] > 10) continue;
				SetBridgeIslands.add(Final);
				rotatecheck[Final.A*10 + Final.B]++; 
				continue;
			}else if(IslCheck[Final.A] != IslCheck[Final.B]) {
				IslCheck[Final.A] = true;
				IslCheck[Final.B] = true; 
				bridgecount++;
				//둘 중 하나라도 연결 안 된 곳이면 추가
				result += Final.bridgelen;
				if(bridgecount == IslCount-1) break;
				Collections.sort(SetBridgeIslands);
			}
		}
		for(int i = 0; i < 10; i++) {
			if(IslCheck[i])	IslandCount++; 
		}
		if(IslandCount == IslCount) System.out.println(result);
		else System.out.println(-1);
	}
	
	private static void NumberingIsland(int x, int y) {
		ArrayList<dirXY> tempXYlist = new ArrayList<>();
		tempXYlist.add(new dirXY(x,y));
		IslCount++;
		maps[x][y] = IslCount;
		Queue<dirXY> BFSQ = new LinkedList<>();
		BFSQ.offer(new dirXY(x,y));
		
		while(!BFSQ.isEmpty()) {
			dirXY temp = BFSQ.poll();
			for(int dir = 0; dir < 4; dir++) {
				int nextx = temp.x + deltas[dir][0];
				int nexty = temp.y + deltas[dir][1];
				if(!isIn(nextx, nexty)) continue;
				if(maps[nextx][nexty] == -1) {	//이어진 섬 부분 발견
					maps[nextx][nexty] = IslCount;
					tempXYlist.add(new dirXY(nextx, nexty));
					BFSQ.offer(new dirXY(nextx, nexty));
				}
			}
		}
		IslandsXY.add(tempXYlist);
	}
	
	private static void CheckAvailConnect(int A, int B) {
		for(dirXY temp : IslandsXY.get(A-1)) {
			for(int dir = 0; dir < 4; dir++) {	//가로세로 4방향
				for(int len = 1; len < 10; len ++) {	//만날 때까지 계쏙
					int nextx = temp.x + deltas[dir][0]*len;
					int nexty = temp.y + deltas[dir][1]*len;
					if(!isIn(nextx, nexty)) break;	//범위 벗어나면 그만
					if(maps[nextx][nexty] == A) break;	//시작 섬이면 안돼
					if(maps[nextx][nexty] == 0) continue;	//바다여
					if(maps[nextx][nexty] == B) {	//원하는 지점을 만남
						if(len <= 2) break;
						SetBridgeIslands.add(new ConIsl(A, B, len-1));
						break;
					}
					else break;	//다른 섬을 만나면 그 경로는 안됨
				}
			}
		}
	}
	
	private static void Comb(int countnow, int startpoint, int [] Selected) {
		if(countnow == 2) {
			selectedIsl.add(Selected.clone());
			return;
		}
		for(int n = startpoint; n <= IslCount; n++) {
			Selected[countnow] = n;
			Comb(countnow+1, n+1, Selected);
		}
	}
	
	private static boolean isIn(int x, int y) {
		return x >= 0 && y >= 0 && x < N && y < M;
	}
	
	private static class ConIsl implements Comparable<ConIsl>{
		int A;
		int B;
		int bridgelen;
		public ConIsl(int a, int b, int bridgelen) {
			super();
			A = a;
			B = b;
			this.bridgelen = bridgelen;
		}
		@Override
		public int compareTo(ConIsl o) {
			return Integer.compare(this.bridgelen, o.bridgelen);
		}
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