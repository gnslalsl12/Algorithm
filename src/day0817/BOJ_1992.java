package day0817;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_1992 {
	static int N;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(read.readLine());
		int [][] maps = new int [N][N];
		for(int i = 0; i < N ; i++) {
			String [] tempstr = read.readLine().split("");
			for(int j = 0; j < N ; j++) {
				maps[i][j] = Integer.parseInt(tempstr[j]);
			}
		}//매핑 완료
		QuadTree(maps);
		System.out.println(sb);

	}
	static void QuadTree(int [][] map) {
		if(map.length == 1) {
			sb.append(map[0][0]);
			return;
		}
		if(AllSame(map)) { //모두 같으면
			sb.append(map[0][0]);
			return;
		}else {//하나라도 다르면
			sb.append("(");
			Queue<int[][]> mapDivided = SquareDivider(map);
			for(int i = 0; i< 4; i++) {
				QuadTree(mapDivided.poll());
			}
			sb.append(")");
		}
	}
	//map을 4개로 나눠서 arrayl에 넣어주는 메서드
	static Queue<int[][]> SquareDivider(int [][] map){
		Queue<int[][]> Divided = new LinkedList<>();
		int len = map.length;
		int [] area = {0,len/2,len};
		int [][][] SelectedArea = {{{0,1},{0,1}},{{0,1},{1,2}},{{1,2},{0,1}},{{1,2},{1,2}}};
		for(int ar = 0; ar < 4; ar++) {
			int [][] exmap = new int [len/2][len/2];
			for(int i = area[SelectedArea[ar][0][0]], exi = 0 ; i < area[SelectedArea[ar][0][1]] ;exi++, i++) {	//3차원 delta 연습
				for(int j = area[SelectedArea[ar][1][0]], exj = 0 ; j < area[SelectedArea[ar][1][1]];exj++, j++) {
					exmap[exi][exj] = map[i][j];
				}
			}
			Divided.add(exmap);			
		}
		return Divided;
	}
	static boolean AllSame(int [][] map) {
		int temp = map[0][0];
		for(int i = 0; i < map.length; i++) {
			for(int j = 0; j < map.length; j++) {
				if(temp != map[i][j]) return false;
			}
		}
		return true;
	}
}