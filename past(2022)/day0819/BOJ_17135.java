package day0819;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_17135 {
	static BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
	static int [][] maps;
	static int N,M,D;
	static int totalRemove;
	static ArrayList<int[]> LastlineSums;		//궁수 위치 조합
	public static void main(String[] args) throws IOException {
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		D = Integer.parseInt(tokens.nextToken());
		maps = new int[N+1][M];
		int[][] tempmaps= new int[N+1][M];
		ArrayList<Integer> firstOne = new ArrayList<>();
		for(int i = 0; i < N; i++) {
			tokens = new StringTokenizer(read.readLine());
			for(int j = 0; j < M; j++) {
				maps[i][j] = Integer.parseInt(tokens.nextToken());
				tempmaps[i][j] = maps[i][j];
			}
			if(maps[i][0] == 1) {
				firstOne.add(i);
			}
		}
		LastlineSums = new ArrayList<>();
		CombLastline(0,new boolean[M]); //궁수 배치도 작성
		int max = Integer.MIN_VALUE;
		for(int[] L : LastlineSums) {		//궁수 배치마다
			totalRemove = 0;
			
			for(int games = 0; games < N; games++) {
				for(int c = 0; c < M; c++) {
					maps[N][c] = L[c];	//전체 맵 작성해주기
				}
				for(int lasty = 0; lasty < M; lasty++) {
					if(maps[N][lasty] == 1) {		//궁수 하나마다 쏴주기
						Shoots(lasty);
					}
				}
				GameLoad();	// 궁수 하나씩 다 해줬으니 게임 로드
				
			}
			//게임 한 번 끝남
			max = Math.max(max, totalRemove);
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < M ; j++) {
					maps[i][j] = tempmaps[i][j]; //맵 초기화
				}
			}
		}
		System.out.println(max);
	}
	
	private static void CombLastline(int countnow, boolean[]visited) {	//궁수 위치 조합 내보내기
		if(countnow == M) {
			int count = 0;
			int [] Lastline = new int [M];
			for(int i = 0; i < M ;i++) {
				if(visited[i]) {
					Lastline[i] = 1;
					count++;
				}
				else {
					Lastline[i] = 0;
				}
			}
			if(count == 3) {
				LastlineSums.add(Lastline);				
			}
			return;
		}
		
		visited[countnow] = true;
		CombLastline(countnow+1, visited);
		visited[countnow] = false;
		CombLastline(countnow+1, visited);
	}
	
	private static void GameLoad() {	//한 칸씩 아래로 이동
		for(int i = N-1; i >= 1; i--) { //아래에서 위로 탐색
			for(int j = 0; j < M; j++) {
				if(maps[i-1][j] == -1) {
					maps[i-1][j] = 0;
				}
				int t = maps[i-1][j];
				maps[i][j] = t;
			}
		}
		for(int j2 = 0; j2 < M ;j2++) {
			maps[0][j2] = 0;
		}
	}

	private static void Shoots(int y) {	//거리에 들어오는 애는 죽임
		int [] FoundtoLeft = {-1,M+1};//못찾은 경우z
		int pastdist= Integer.MAX_VALUE;
		for(int i = 0; i <= N-1; i++) {	//위에서 부터 봐서
			for(int j = M-1; j >= 0 ;j--) {	//오른부터 봐서
				if(maps[i][j] == -1 || maps[i][j] == 1) {//처음 본 위치가 뭔가 있을 때 :
					int nowdist = AvailDist(N,y,i,j);
					if(nowdist <= D) {
						if(pastdist == Integer.MAX_VALUE) {//처음 본 적이면 : 
							pastdist = nowdist;
							FoundtoLeft[0] = i;
							FoundtoLeft[1] = j;
							continue;
						}else if(pastdist > nowdist) {	//거리가 더 작을 때는 무조건 변경
								pastdist = nowdist;
								FoundtoLeft[0] = i;
								FoundtoLeft[1] = j;
						}else if(pastdist == nowdist) { //r거리가 같을 때는 j값이 작을 때만 ㅗ변경
							if(FoundtoLeft[1] > j) {
								FoundtoLeft[0] = i;
								FoundtoLeft[1] = j;
							}//j가 더 크면 안 변경
							
						}
					}
				}				
			}
		}
		if(FoundtoLeft[0] == -1) return; //못찾음
		if(maps[FoundtoLeft[0]][FoundtoLeft[1]] == 1) {//사람 찾음
			maps[FoundtoLeft[0]][FoundtoLeft[1]] = -1; //죽임
			totalRemove++;
			return;
		}else if(maps[FoundtoLeft[0]][FoundtoLeft[1]] == -1) {//시체 찾음
			return;
		}
	}
	
	private static int AvailDist(int myx, int myy, int dx, int dy) {
		return Math.abs(myx-dx) + Math.abs(myy-dy);	//위치까지 거리가 D내인가?
	}
}