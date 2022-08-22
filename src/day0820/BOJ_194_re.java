package day0820;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;

public class BOJ_194_re {	//BitMasking을 전체로 통합
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
			boolean[] tempv = new boolean[26];
			tempv[pop.Number] = true;
			BFS(pop, tempv, 1, 1);
		}
		
//		for(int t : CheckDup) {
//			System.out.println(t);
//			RollBackMap(t);
//		}
		System.out.println(CheckDup.size());
		
		
		
	}
	
	static HashSet<boolean[]> CheckDup = new HashSet<>();
	static int [][] deltas = {{-1,0},{0,1},{1,0},{0,-1}};
	static ArrayList<int[]> tempResult;
	private static void BFS(dirXY startpoint, boolean[] visited, int Scount, int totalcount) throws CloneNotSupportedException {

		if(totalcount == 7) {
			if(Scount >= 4) {
				CheckDup.add(visited);
			}
			else {	//S가 4명 이하임
				return;
			}
		}
		
		for(int i = 0; i < 4; i++) {
			int tempx = startpoint.x + deltas[i][0];
			int tempy = startpoint.y + deltas[i][1];
			dirXY test = new dirXY(tempx, tempy, tempx*5 + tempy+1 );
			if(isIn(test)) {
				switch(getCharFromMap(test)) {
				case('Y'):{
					if(!visited[test.Number]) {	//해당 number가 false이면
						visited[test.Number] = true;
						BFS((dirXY)test.clone(),visited, Scount, totalcount+1);
					}	//나중에 새로운 함수에서 true 해줄 거임
					break;
				}
				case('S'):{
					if(!visited[test.Number]) {	//해당 number가 false이면
						visited[test.Number] = true;
						BFS((dirXY)test.clone(), visited, Scount+1, totalcount+1);
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
				if((BitM & 1<<((i*5)+j)) == 1) {
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
	
	
	private static char getCharFromMap(dirXY A) {
		return maps[A.x][A.y];
	}
	
	
	private static boolean isIn(dirXY A) {
		return A.x >= 0 && A.x < 5 && A.y >= 0 && A.y < 5;
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
