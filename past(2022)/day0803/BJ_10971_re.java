package day0803;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_10971_re {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(read.readLine());
		int [][] maps = new int [n][n];
		for(int i = 0; i < n ; i++) {
			StringTokenizer tokens = new StringTokenizer(read.readLine());
			for(int j = 0; j < n ; j ++) {
				maps[i][j] = Integer.parseInt(tokens.nextToken());
			}
		}//맵 완성
		
		
		int [] lowesty = new int [n];
		for(int l = 0; l < n; l++) {
			int low = Integer.MAX_VALUE;
			for(int t = 0; t < n; t++) {
				if(maps[l][t] != 0 && maps[l][t] < low) { //낮은 놈 찾았을 때
					low = t;
					lowesty[l] = t; //가장 낮은 놈의 x -> y 의 y값을 넣어줌
				}
			}
			for(int k = 0; k < n ; k++) {
				
				maps[k][lowesty[l]] = Integer.MAX_VALUE; 		// x->y가 x라인의 가장 낮은값인 걸 알았으니
			}
															// y->x의 경우를 삭제해줌 (돌아갈 수 없음)
			
			
			
		}

		System.out.println(lowesty[0] + " : " + maps[0][lowesty[0]]);
		System.out.println(lowesty[1] + " : " + maps[1][lowesty[1]]);
		System.out.println(lowesty[2] + " : " + maps[2][lowesty[2]]);
		System.out.println(lowesty[3] + " : " + maps[3][lowesty[3]]);
		
		
	}

}
