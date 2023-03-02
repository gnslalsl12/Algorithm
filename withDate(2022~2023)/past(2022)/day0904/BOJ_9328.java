package day0904;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_9328 {
	static int h, w;
	static char [][] maps;
	static Queue<Character> Keys;	//아직 주머니에만 넣어놓은 열쇠
	static ArrayList<dirXY> [] Locked;	//지도상에서 얻은 문 위치 ([알파벳값] 의 arraylist는 문 좌표를 저장한 arraylist)
	static boolean [] AvailUnlock;	//주머니에서 꺼내 손에 들고있는 열쇠 목록
	static int found;
	static boolean[][] visited;
	static int[][] deltas = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(tokens.nextToken());
		for(int test = 1; test <= T; test++) {
			tokens = new StringTokenizer(read.readLine());
			h = Integer.parseInt(tokens.nextToken());
			w = Integer.parseInt(tokens.nextToken());
			maps = new char [h+2][w+2];
			for(int i = 0; i < h+2; i++) {
				Arrays.fill(maps[i], '.');
			}
			visited = new boolean[h+2][w+2];	//외부에서 들어올 수 있는 입구를 탐색하기 위해 건물 외부에 빈 길을 만들어줌
			AvailUnlock = new boolean [26];
			found = 0;
			Keys = new LinkedList<>();	//주머니
			Locked = new ArrayList [26];		//대문자 알파벳 문의 위치들 저장 배열
			for(int i = 0; i < 26; i++) {
				Locked[i] = new ArrayList<dirXY>();
			}
			
			String templine = "";
			for(int i = 0; i < h; i++) {
				templine = read.readLine();
				for(int j = 0; j < w; j++) {
					maps[i+1][j+1] = templine.charAt(j);
					if((int)maps[i+1][j+1] >= 65 && (int)maps[i+1][j+1] <= 90 ) {	//대문자 문 발견
						int index = maps[i+1][j+1] - 'A';
						Locked[index].add(new dirXY(i,j));//해당 알파벳 대문의 문들 좌표
					}
				}
			}//매핑 & 문들 좌표 기억 완료
			templine = read.readLine();
			if(!templine.contentEquals("0")) {	//초기 소지 열쇠가 있을 때
				for(int i = 0; i < templine.length(); i++) {
					Keys.add(templine.charAt(i));
				}
			}
			//입력 처리 완료
			
			BFS(new dirXY(0,0));
			int tempfound = found;
			sb.append(tempfound + "\n");
		}
		System.out.println(sb);
	}

	private static void BFS(dirXY Enter) {
		Queue<dirXY> BFSQ = new LinkedList<>();
		BFSQ.add(Enter);
		visited[Enter.x][Enter.y] = true; 
		while(!BFSQ.isEmpty()) {
			dirXY temp = BFSQ.poll();
			
			while(!Keys.isEmpty()) {	//현재 위치에서 열쇠를 새로 얻거나 초기에 갖고있던 열쇠를 손에 쥐기 시작
				int tempkey = (Keys.poll() - 'a');
				AvailUnlock[tempkey] = true;	//손에 들기
				for(dirXY AvailUnlock : Locked[tempkey]) {	//혹시 잠겨있던 문 중에 내가 방금 손에 들게된 열쇠로 갈 수 있는 곳이 있나?
					if(visited[AvailUnlock.x][AvailUnlock.y]) BFSQ.add(AvailUnlock);	//이전에 방문했지만 못 들어간 문임 => BFS에 추가
					else continue;	//아직 방문하지 않은 열 수 있는 문 => 나중엔 가겠지~(다시 확인은 안 함)
				}
			}
			
			for(int dir = 0; dir < 4; dir++) {
				int nextx = temp.x + deltas[dir][0];
				int nexty = temp.y + deltas[dir][1];
				if(!isIn(nextx, nexty)) continue;	//범위 밖
				if(visited[nextx][nexty]) continue;	//방문
				if(maps[nextx][nexty] == '*') continue; //벽
				
				visited[nextx][nexty] = true;	//일단 방문처리
				if(maps[nextx][nexty] == '.') {	//빈공간
					BFSQ.add(new dirXY(nextx, nexty));
					continue;
				}
				
				int Scal = (int)maps[nextx][nexty];	//뭔가 있다!
				
				//소문자 열쇠 만남
				if(Scal >= 97 && Scal <= 122) {	
					if(AvailUnlock[Scal - 'a']) {		//이미 갖고있는 열쇠임
						BFSQ.add(new dirXY(nextx, nexty));
						continue;
					}
					//처음 얻는 열쇠
					Keys.add(maps[nextx][nexty]);
					BFSQ.add(new dirXY(nextx, nexty)); //열쇠 이후로 진행
					continue;
				
				}
				
				//대문자 문 만남
				else if(Scal >= 65 && Scal <= 90) {	
					if(AvailUnlock[Scal -'A']) {	//해당 문 열쇠를 갖고있으면
						BFSQ.add(new dirXY(nextx, nexty));	//해당 문 열고 진행
						continue;
					}else {		//문을 만났는데 열쇠가 없어!
						Locked[Scal - 'A'].add(new dirXY(nextx, nexty));
						//문을 못 열었지만 방문처리 해줌 => 나중에 열쇠 얻었을 때 여기를 다시 와야한다고 알 수 있음
						//진행 X
					}
				}else if(Scal == 36) {	//문서 찾음
					found++;
					BFSQ.add(new dirXY(nextx, nexty));
				}
			}
		}
	}
	
	private static boolean isIn(int x, int y) {
		return x >= 0 && y >= 0 && x < h+2 && y < w+2;
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