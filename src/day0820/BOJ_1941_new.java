package day0820;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Stack;

public class BOJ_1941_new {
	static char [][] maps = new char [5][5];
	static StringBuilder sb = new StringBuilder();
	static ArrayList<dirXY> SLists = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		
		for(int i = 0; i < 5; i++) {
			String ex = read.readLine();
			for(int j = 0; j < 5; j++) {
				maps[i][j] = ex.charAt(j);
				if(maps[i][j] == 'S') {
					int scount = N(i,j);
					SLists.add(new dirXY(i, j, scount, 1<<scount, 1, 1));	//시작한 곳 출발일 테니 기본 비트마스킹 1<<Scount
				}
			}
		}
		
		for(dirXY get : SLists) {
//			System.out.println("==========");
//			System.out.println(get.x + ", " + get.y);
//			System.out.println(get.Number);
			DFS(get);
		}
		
		System.out.println(BitMaskingResult.size());
		
		
		
		
	}
	
	static HashSet<Integer> BitMaskingResult = new HashSet<>();
	static int[][] deltas = {{-1,0},{0,1},{1,0},{0,-1}};
	
	private static void DFS(dirXY input) {
		Stack<dirXY> DFSStack = new Stack<>();
		DFSStack.push(input);
		
		while(!DFSStack.isEmpty()) {
			dirXY pop = DFSStack.pop();
//			System.out.println("********");
//			System.out.println("남은 stack : " + DFSStack.size());
//			System.out.println("totalcount : " + pop.TotalCount);
//			System.out.println("Scount : " + pop.SCount);
//			
//			System.out.println("새로운 Spop 시작: " + pop.Number);
//			System.out.println("********");
			if(pop.TotalCount == 7) {
				RollBackMap(pop.BitMasking);
				if(pop.SCount >= 4) {
					BitMaskingResult.add(pop.BitMasking);
				}
				else {
					continue;
				}
			}
			
			
			for(int i = 0; i < 4; i ++) {
				int tempx = pop.x + deltas[i][0];
				int tempy = pop.y + deltas[i][1];
				if(isIn(tempx,tempy)) {	//범위 안에 들어오는 영역이면
					int Number = N(tempx, tempy);
//					System.out.println(Number);
					if(maps[tempx][tempy] == 'S' && ((pop.BitMasking & (1<<Number)) == 0)) {	//소연이이고 아직 선택되지 않은 소연이일 때
//						System.out.println("~~~새로운 S");
						dirXY next = new dirXY(tempx, tempy, Number, (pop.BitMasking | 1<<Number), pop.TotalCount+1 , pop.SCount+1);
						DFSStack.push(next);
						continue;
					}
					else if(maps[tempx][tempy] == 'S' && ((pop.BitMasking & (1<<Number)) != 0)) {	//소연이이고 선택됐으면
//						System.out.println("~~이미 간 S");
//						dirXY next = new dirXY(pop.x, pop.y, pop.Number, pop.BitMasking, pop.TotalCount , pop.SCount);
//						DFSStack.push(next);
						continue;//선택이 됐지만 해당 경로 로도 다시 탐색해봐야함
					}
//					System.out.println(maps[tempx][tempy]);
					if(maps[tempx][tempy] == 'Y' && ((pop.BitMasking & (1<<Number)) != 0)) { //연주이고 선택됐으면
//						System.out.println("Y인데 선택됨");
//						dirXY next = new dirXY(pop.x, pop.y, pop.Number, pop.BitMasking, pop.TotalCount , pop.SCount);
//						DFSStack.push(next);
						continue;
					}
					else if(maps[tempx][tempy] == 'Y' && ((pop.BitMasking & (1<<Number)) == 0)) { //연주이고 선택되지 않았으면
//						System.out.println("새로운 Y!!");
						dirXY next = new dirXY(tempx, tempy, Number, (pop.BitMasking | 1<<Number), pop.TotalCount+1 , pop.SCount);
						DFSStack.push(next);
						continue;
					}//모든 조건 if
				}// for(isIn)
			}// for(deltas)
		}// while(empty)
	}// DFS
	
	private static void RollBackMap(int BitM) {
		sb = new StringBuilder();
		for(int i = 0; i < 5; i++) {
			for(int j = 1; j <= 5; j++) {
				if((BitM & 1<<((i*5)+j)) != 0) {
					sb.append(1 + " ");
				}else {
					sb.append(0 + " ");
				}
			}
			sb.append("\n");
		}
		System.out.println(sb);
		System.out.println("===================");
	}
	
	private static int N(int x, int y) {
		return x*5 + y+1;
	}
	
	private static boolean isIn(int tempx, int tempy) {
		return tempx>=0 && tempx<5 && tempy>=0 && tempy<5;
	}
	
	private static class dirXY{
		int x;
		int y;
		int Number;
		int BitMasking;
		int TotalCount;
		int SCount;
		public dirXY(int x, int y, int number, int bitMasking, int totalCount, int sCount) {
			super();
			this.x = x;
			this.y = y;
			Number = number;
			BitMasking = bitMasking;
			TotalCount = totalCount;
			SCount = sCount;
		}
		
	}

}
