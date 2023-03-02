package day0820;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

//S가 4개 포함인가?
//7개 각 위치(S, Y 모든 곳)에서 다들 연결 가능한 상태인가 (BFS)

public class BOJ_1941_Final {
	static BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
	static char[][] maps = new char[5][5];
	static ArrayList<Integer> BMlist = new ArrayList<>(); //BitMask를 저장해둬서 이걸 토대로 해당 경우의 수가 7명으로 가능한가 판별할 예정
	public static void main(String[] args) throws IOException {
		
		for(int i = 0; i < 5; i++) {
			String tempLine = read.readLine();
			for(int j = 0; j < 5; j++) {
				maps[i][j] = tempLine.charAt(j);
			}
		}
		
		
		//25개 중 7개 골라서 S가 4개 이상 포함된 놈만 BMlist에 추가
		Comb(25,7);	
		
		//뽑아낸 경우의 수들 중 서로 붙어있는 것들만 골라냄
		ArrayList<Integer> ConnectedBM = new ArrayList<>();
		for(int tempBM : BMlist) {
			if(BFS(tempBM)) {
				ConnectedBM.add(tempBM);
			}
		}
		System.out.println(ConnectedBM.size());
		
		
	}//main
	
	////////////////////////////////////////////////
	
	private static void Comb(int n, int r) {		//Comb 함수 쓰기 편하게
		CombInfo(0,0,0,n,r);
	}

	private static void CombInfo(int countnow, int BitMasking, int SelectedBM, int n, int r) {	//25개 중 S 4개 이상 포함된 걸로 7개 뽑기 조합
		if(countnow ==r) {
				//S가 4개 이상인가?
			int Scount = 0;
			int totalcount = 0;
			for(int c = 1; c <= n; c++) {
				if((SelectedBM & (1<<c)) != 0) { //c가 선택됐다면
					totalcount++;					//선택된 것만 갯수 새기
					if(CharInMap(c) == 'S') {	//S라면
						Scount++;
					}
					
				}//BitMasking으로 true 골라내기
				if(totalcount == r) break;
			}//1~25까지 BitMasking 탐색
			
			if(Scount >= 4) {
				BMlist.add(SelectedBM);
			}
			return;
		}
		
		for(int i = 1; i <= n; i++) {
			if((BitMasking & 1<<i) == 0) {		//i가 선택되지 않았을 때만
				BitMasking |= 1<<i;	//i를 true화
				SelectedBM |= 1<<i;					//고른 숫자들
				CombInfo(countnow + 1, BitMasking, SelectedBM, n, r);
				SelectedBM &= ~(1<<i);
			}
		}
	}
	
	private static char CharInMap(int nth) {	//1~25에서 숫자로 맵상의 문자 가져오기
		int x = ((nth-1)/5);
		int y = nth - 5*x - 1;
		return maps[x][y];
	}
	
	////////////////////////////////////////////////
	
	private static boolean BFS(int Bit) {		//연결됐는지 탐색 BFS
		Queue<Integer> BFSq = new LinkedList<>();
		ArrayList<Integer> BMnumbers = BMtoNumList(Bit);
		int tempBitmask = 0;
		BFSq.add(BMnumbers.get(0)); //일단 첫번째 숫자 넣어줌(제일 작은 숫자긴함)
		tempBitmask |= 1<<0;	//첫번쨰 숫자 Bitmask
		
		while(!BFSq.isEmpty()) {
			int temp = BFSq.poll();
			
			for(int i = 1; i < 7; i++) {
				int pop = BMnumbers.get(i);
				if(RightNext(temp, pop) && (tempBitmask & 1<<i) == 0) {	//붙어있으고 아직 선택되지 않은 숫자면 : 
					tempBitmask |= 1<<i;	//해당 숫자 sel
					BFSq.add(pop);			//que에 넣어줌
				}
			}
		}
		if(tempBitmask == 127) {//7개 숫자가 모두 선택됐다면
			return true;
		}else {
			return false;
		}
		
	}
	
	////////////////////////////////////////////////
	
	private static ArrayList<Integer> BMtoNumList(int BitM){	//Bit 를 이용해 숫자 1~25 중 선택된 숫자만 뽑아내기
		ArrayList<Integer> BMlist = new ArrayList<>();
		int count = 0;
		for(int i = 1; i <= 25; i++) {
			if((BitM & 1<<i) != 0) { //선택된 i
				count++;
				BMlist.add(i);
			}
			if(count == 7) break;
		}
		return BMlist;	//작은 숫자들부터 저장돼있음
	}
	
	private static boolean RightNext(int a, int b) {	//숫자 a와 b가 좌표상에 붙어있는가 판별
		if(Math.abs(a-b) == 1 && (a+b)%5 ==1){
			return false;
		}
		return Math.abs(a - b) == 1 || Math.abs(a - b) == 5;	//좌우 또는 상하
	}
	
	////////////////////////////////////////////////
	
}
