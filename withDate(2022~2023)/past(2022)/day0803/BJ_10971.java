package day0803;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BJ_10971 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(read.readLine());
		
		int [][] maps = new int [n][n];
		ArrayList<Integer> lows = new ArrayList<>();
		for(int i = 0; i < n; i ++) {
			lows.add(Integer.MAX_VALUE);
		}
		int lowsj = n+1;
		for(int i = 0; i<n; i++) {

			int compare = Integer.MAX_VALUE;
			StringTokenizer token = new StringTokenizer(read.readLine());
			for(int j = 0; j<n ; j++) {
				if(lows.contains(i)) {
					maps[i][j] = Integer.MAX_VALUE;
					continue;
				}
				maps[i][j] = Integer.parseInt(token.nextToken()); //맵 만들기
				if(i == j) {
					maps[i][j] = Integer.MAX_VALUE;				//0이면 (갈 수 없음)
//					continue;
				}
				if(maps[i][j] < compare) {
					compare = maps[i][j];
					lowsj = j;							//i마다 가장 최소 비용의 목적지 j
				}
			}
//			maps[lowsj][i] = 99;
			System.out.println(lowsj + ", " + i + " : " + maps[lowsj][i]);
			lows.set(i, lowsj);
		}//맵 완성 (lows[i]는 i 줄에서 가장 작은 값을 가진 j
		int lowresult = Integer.MAX_VALUE;
		for(int i = 0; i < n; i++) {
			int result = 0;
			int y = i;
			for(int k = 0; k < n ; k++) {
				result += maps[y][lows.get(y)];
				y = lows.get(y);
				
			}
			result += maps[y][i];
			if(result < lowresult) {
				lowresult = result;
			}
		}
		System.out.println(lows.get(0));
		System.out.println(lows.get(1));
		System.out.println(lows.get(2));
		System.out.println(lows.get(3));
		System.out.println(maps[1][0]);
		System.out.println(lowresult);
		
		
		
		
	}

}
