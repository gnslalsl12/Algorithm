package day0929;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1149 {

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(read.readLine());
		int[] apt = new int[3];
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		for (int i = 0; i < 3; i++) {
			apt[i] = Integer.parseInt(tokens.nextToken());
		} // 처음 아파트 페인트값

		int[] tempplused = new int[3];
		
		for (int i = 1; i < N; i++) {
			tokens = new StringTokenizer(read.readLine());
			
			for (int j = 0; j < 3; j++) { // i번째 아파트에 j번째 색을 칠하려 할 때
				int temppluscolor = Integer.MAX_VALUE;
				
				for (int k = 0; k < 3; k++) { // 이전 아파트는 k번째 색을 칠한 상태야
					if (j == k) continue; // 이전 아파트와 같은 색이면 안돼
					temppluscolor = Math.min(temppluscolor, apt[k]); // 이전 아파트까지의 비용 합(직전이 같은 색이 아닌)
				}
				
				int newcolor = Integer.parseInt(tokens.nextToken()); // 새로운 아파트의 j번째 색
				tempplused[j] = temppluscolor + newcolor;	//이전 아파트의 최소 비용 + 현재 아파트에 칠할 색 비용
				
			}
			apt = tempplused.clone();	//새로 구한 총 비용 갱신
		}
		
		int result = Integer.MAX_VALUE;
		for (int i = 0; i < 3; i++) {
			result = Math.min(apt[i], result);
		}
		System.out.println(result);
	}
}