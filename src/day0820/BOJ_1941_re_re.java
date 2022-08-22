package day0820;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Stack;

public class BOJ_1941_re_re {
	static BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
	static char [][] maps = new char [5][5];
	static ArrayList<dirXY> SomList = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		for(int i = 0; i < 5 ; i++) {
			String tempread = read.readLine();
			for(int j = 0; j < 5 ; j++) {
				maps[i][j] = tempread.charAt(j);
				if(maps[i][j] == 'S') {
					SomList.add(new dirXY(i,j, getNum(i,j), 1<<(getNum(i,j)), 7));
				}
			}
		}//mapping 완료
		
		
		
		
		
		
		
		
		
	}
	static int People = 7;
	
	private static void MainMethod(dirXY startpoint) {
		Stack<dirXY> tempDFSstack = new Stack<>();
		
		
		for(dirXY pop : SomList) {
			if((startpoint.BitMasking & 1<<pop.num) == 0 && AvailGo(startpoint, pop)) {	//현재 startpoint에서 방문한 적이 없고 && 현재 잔여 인원보다 더 적게 먹을 때
				tempDFSstack.push(new dirXY(pop.x, pop.y, pop.num, (startpoint.BitMasking | 1<<pop.num), startpoint.remain - getDist(startpoint, pop)));
											//x		y		다음위치num		현재에서 다음위치 true시킨 BM		다음 위치까지 거리 뺀 getDIst	
			}
		}
		
		
	}
	
	private static void makeWay(dirXY from, dirXY to, Stack<dirXY> stack) {
		
		
		
	}
	
	private static boolean AvailGo(dirXY from, dirXY to) {
		return getDist(from, to) >= from.remain;
	}
	
	
	private static int getNum(int x, int y) {
		return x*5 + y + 1;
	}
	
	
	private static int getDist(dirXY from, dirXY to) {						//선택된 Y는 생략하고 세야함
		
		return Math.abs(from.x - to.x) + Math.abs(from.y - to.y);
	}
	
	
	private static class dirXY implements Cloneable, Comparable<dirXY>{
		int x;
		int y;
		int num;
		int BitMasking;
		int remain;
		public dirXY(int x, int y, int num, int BitMasking, int remain) {
			super();
			this.x = x;
			this.y = y;
			this.num = num;
			this.BitMasking = BitMasking;
			this.remain = remain;
		}
		public dirXY() {
			super();
		}
		@Override
		protected dirXY clone() throws CloneNotSupportedException {
			// TODO Auto-generated method stub
			return (dirXY)super.clone();
		}
		@Override
		public int compareTo(dirXY o) {
			// TODO Auto-generated method stub
			return Math.abs(this.x - o.x) + Math.abs(this.y - o.y);
		}
	}
	
}
