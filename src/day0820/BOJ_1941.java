package day0820;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;

public class BOJ_1941 {	//BitMasking을 전체로 통합
	static BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
	static char[][] maps = new char[5][5];
	static ArrayList<dirXY> SomList = new ArrayList<>();
	static ArrayList<dirXY> YeonList = new ArrayList<>();
	static int SomCount;
	static int TotalCount;
	public static void main(String[] args) throws IOException, CloneNotSupportedException {
		SomCount = 0;
		TotalCount = 0;
		for(int i = 0; i < 5 ; i++) {
			String readex = read.readLine();
			for(int j = 0 ; j < 5 ; j++) {
				maps[i][j] = readex.charAt(j);
				if(maps[i][j] == 'S') {
					SomList.add(new dirXY(i, j, ++TotalCount));
				}else {
					YeonList.add(new dirXY(i,j,++TotalCount));
				}
			}
		}
		
		for(dirXY pop : SomList) {
			BFS(pop, 1<<pop.Number, 1, 1);
		}
//		BFS(SomList.get(0), 1<<SomList.get(0).Number, 1, 1);
		
		for(int t : CheckDup) {
			RollBackMap(t);
		}
		System.out.println(CheckDup.size());
		System.out.println(tempCheck.size());
		
	}
	
	static HashSet<Integer> CheckDup = new HashSet<>();
	static ArrayList<Integer> tempCheck = new ArrayList<>();
	static int [][] deltas = {{-1,0},{0,1},{1,0},{0,-1}};
	static ArrayList<int[]> tempResult;
	private static void BFS(dirXY startpoint, int BitMasking, int Scount, int totalcount) throws CloneNotSupportedException {
	
		if(totalcount == 7) {
			if(Scount >= 4) {
				System.out.println("이거 된다");
				CheckDup.add(BitMasking);
				tempCheck.add(BitMasking);
				return;
			}
			else {	//S가 4명 이하임
				return;
			}
		}
		
		for(int i = 0; i < 4; i++) {
			int tempx = startpoint.x + deltas[i][0];
			int tempy = startpoint.y + deltas[i][1];
			if(isIn(tempx,tempy)) {
				dirXY test = new dirXY(tempx, tempy, tempx*5 + tempy+1 );
				switch(getCharFromMap(test)) {
				case('Y'):{
					if((BitMasking & 1<<(test.Number)) == 0) {	//해당 number가 false이면
						BitMasking |= (1<<test.Number);
						BFS((dirXY)test.clone(),BitMasking, Scount, totalcount+1);
						BitMasking &= ~(1<<test.Number);
					}	//나중에 새로운 함수에서 true 해줄 거임
					break;
				}
				case('S'):{
					if((BitMasking & 1<<(test.Number)) == 0) {	//해당 number가 false이면
						BitMasking |= (1<<test.Number);
						RollBackMap(BitMasking);
						System.out.println(tempx + ", " + tempy);
						System.out.println("number : " + test.Number);
						System.out.println(Scount + 1);
						System.out.println("===================");
						BFS((dirXY)test.clone(), BitMasking, Scount+1, totalcount+1);
						BitMasking &= ~(1<<test.Number);
					}	//나중에 새로운 함수에서 true 해줄 거임
					break;
				}
				}
			}
		}
		
		
	}
	static StringBuilder sb = new StringBuilder();
	
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
		System.out.print(sb);

	}
	
	
	private static char getCharFromMap(dirXY A) {
		return maps[A.x][A.y];
	}
	
	
	private static boolean isIn(int tempx, int tempy) {
		return tempx >= 0 && tempx < 5 && tempy >= 0 && tempy < 5;
	}
	
	
	
	private static class dirXY implements Cloneable{
		int x;
		int y;
		int Number;
		public dirXY(int x, int y, int Number) {
			super();
			this.x = x;
			this.y = y;
			this.Number = Number;
		}
		@Override
		protected Object clone() throws CloneNotSupportedException {
			return super.clone();
		}
		
		
		
	}
}
