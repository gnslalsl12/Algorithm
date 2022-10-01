package day0828;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_11559 {
	static ArrayList<ArrayList<Character>> PuyoLine = new ArrayList<>();
	static int[][] deltas = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	static boolean [][] Allvisits;
	static boolean Bomb;
	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		for(int i = 0; i < 6; i++) {
			PuyoLine.add(new ArrayList<>());
		}	//리스트 초기화
		for(int i = 0; i < 12; i++) {
			String templine = read.readLine();
			for(int j = 0; j < 6; j++) {
				PuyoLine.get(j).add(templine.charAt(j));
			}
		}
		
		for(int i = 0; i < 6; i++) {
			Collections.reverse(PuyoLine.get(i));
		}
		
		int totalcount = 0;
		Bomb = false;		//연쇄가 일어났는가
		Allvisits = new boolean[6][12];	//한 번 돌 때 이어진 색 위치 BFS 중복 방지
		All : 
			for(int high = 0; high < 12; high++) {
				int DotCount = 0;
				int width = 0;
				for(width = 0; width < 6; width++) {
					if(!isIn(high, width)) continue;
					char nowcolor = PuyoLine.get(width).get(high);
					
					if(nowcolor == 'X') continue;	//지워야하는 색
					
					if(nowcolor == '.') {	//빈공간	(x 한 줄이 다 빈공간이면 그만 탐색하도록
						DotCount++;
						continue;
					}
					
					if(Allvisits[width][high]) continue;
					
					BFS(high, width);
				}
				if(DotCount == 6 || high == 11) {	//맨 윗자리까지 옴
					if(!Bomb) {//터진 게 없음
						break All;
					}
					totalcount++;	//연쇄 한 번
					//터진 게 있음
					for(int h = high-1; h >= 0; h--) {
						for(int w = 0; w < 6; w++) {
							if(PuyoLine.get(w).get(h) == 'X') {
								PuyoLine.get(w).remove(h);	//지우고
								PuyoLine.get(w).add('.');	//위에 채워주고
							}
							
						}
					}//삭제한 자리 채워줌
					high = -1;
					width = -1;
					Bomb = false;
					Allvisits = new boolean[6][12];	//전역 방문 초기화
					continue All;
				}
			}
		System.out.println(totalcount);
	}
	
	private static void BFS(int startx, int starty) {			//뭔가 지워짐 : true;
		boolean [][] visits = new boolean[6][12];
		Queue<dirXY> BFSQ = new LinkedList<>();
		BFSQ.add(new dirXY(startx,starty));
		Queue<dirXY> RemoveList = new LinkedList<>();	//삭제할 위치 임시 저장값(4개 이상일 떄만 삭제
		int colorcount = 0;
		
		while(!BFSQ.isEmpty()) {
			dirXY next = BFSQ.poll();
			colorcount++;
			RemoveList.add(new dirXY(next.x, next.y));
			Allvisits[next.y][next.x] = true;
			visits[next.y][next.x] = true;
			
			for(int dir = 0; dir < 4 ; dir++) {
				int tempx = next.x + deltas[dir][0];
				int tempy = next.y + deltas[dir][1];
				
				if(!isIn(tempx, tempy)) continue;	//범위 안
				if(visits[tempy][tempx] == true) continue;	//방문 X
				char tempcolor = PuyoLine.get(tempy).get(tempx);
				if(tempcolor == '.') continue;	//'.'XX	//색이 다르면 추가 X
				if(next.color == tempcolor) {
					visits[tempy][tempx] = true;
					Allvisits[tempy][tempx] = true;
					BFSQ.add(new dirXY(tempx, tempy));
				}
			}
		}//같은 색 BFS 완료
		if(colorcount < 4) return;
		//같은 색 4개 이상 찾음
		for(dirXY remove : RemoveList) {
			PuyoLine.get(remove.y).set(remove.x, 'X'); //위에서부터 지워짐
			Bomb = true;
		}
		return;
	}
	
	private static boolean isIn(int x, int y) {
		return x >= 0 && y >= 0 && y < 6 && x < 12;
	}
	
	private static class dirXY {
		int x;
		int y;
		char color;

		public dirXY(int x, int y) {
			super();
			this.x = x;
			this.y = y;
			this.color = PuyoLine.get(y).get(x);
		}
	}
}