package day0808;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA암호문 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
			BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		for(int testcase = 1; testcase <= 10; testcase++) {
			StringBuilder sb = new StringBuilder();
			ArrayList<Integer> PW = new ArrayList<>(); 
			int n = Integer.parseInt(read.readLine());	//암호 길이
			StringTokenizer tokens = new StringTokenizer(read.readLine());	//암호값 
			for(int i = 0; i < n; i++) { 									//입력
				PW.add(i, Integer.parseInt(tokens.nextToken()));
			}//초기 PW값
			int test = Integer.parseInt(read.readLine());
			tokens = new StringTokenizer(read.readLine());
			int index = 0;
			for(int i = 0; i < test ; i++) {
				tokens.nextToken();	//I 버리기
				index = Integer.parseInt(tokens.nextToken());		//몇번쨰부터 추가?
				int count = Integer.parseInt(tokens.nextToken());	//몇 개 추가?
				for(int j = 0; j < count ; j++) {
					PW.add(index++,Integer.parseInt(tokens.nextToken()));
				}
			}
			System.out.printf("#%d ", testcase);
			for(int f = 0; f < 10; f++) {
				sb.append(PW.get(f)).append(" ");
			}
			System.out.println(sb);
		}
		
		
	}

}
