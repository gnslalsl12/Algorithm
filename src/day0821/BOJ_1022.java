package day0821;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1022 {
	static BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static int N;
	static int r1,c1,r2,c2;
	public static void main(String[] args) throws IOException {
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		
		r1 = Integer.parseInt(tokens.nextToken());
		c1 = Integer.parseInt(tokens.nextToken());
		r2 = Integer.parseInt(tokens.nextToken());
		c2 = Integer.parseInt(tokens.nextToken());
		
		Queue<Integer> NumberList = new LinkedList<>();
		int maxLen = Integer.MIN_VALUE;
		for(int i = r1; i <= r2; i++) {
			for(int j = c1; j <= c2; j++) {
				int tempNum = getEdgeNum(i,j);			//좌표에 들어갈 숫자는 해당 좌표 최대 길이의 정사각형 소용돌이 내부 숫자라고 판단
				maxLen = Math.max(maxLen, Integer.toString(tempNum).length());	//길이가 가장 긴 숫자의 길이 구하기
				NumberList.add(tempNum);
			}
		}
		
		StringBuilder printFormat = new StringBuilder();	//출력 형식 맞출 sb
		printFormat.append("%");
		printFormat.append(Integer.toString(maxLen));
		printFormat.append("d ");
		
		for(int i = r1; i <= r2; i++) {
			for(int j = c1; j <= c2; j++) {
					sb.append(String.format(printFormat.toString(), NumberList.poll()));
			}
			if(i != r2) {
				sb.append("\n");
			}
		}
		
		System.out.print(sb);

	}
	
	private static int getEdgeNum(int r, int c) {
		if(r <= -1*c) {
			N = (Math.max(Math.abs(r), Math.abs(c)))*2;
			return getNumLU(r,c);
		}else {
			N = Math.max(Math.abs(r), Math.abs(c))*2 + 1;
			return getNumRD(r,c);
		}
	}
	
	private static int getNumLU(int r, int c){		//정사각형에서 사선 '/' 기준으로 왼쪽 위에 위치할 경우의 끝값
		return (int)(Math.pow(N, 2)) + 1 + r - c;
	}
	private static int getNumRD(int r, int c){		//정사각형에서 사선 '/' 기준으로 오른쪽 아래에 위치할 경우의 끝값
		int lineminus = 0;
		if(r < c) {
			lineminus = 4*N - 5 + 1;
		}
		return (int)(Math.pow(N, 2)) - lineminus + (c - r);
	}
}