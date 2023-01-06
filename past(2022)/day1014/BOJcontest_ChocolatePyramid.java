package day1014;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJcontest_ChocolatePyramid {

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		int T = Integer.parseInt(tokens.nextToken());
		for (int test = 0; test < T; test++) {
			tokens = new StringTokenizer(read.readLine());
			int r = Integer.parseInt(tokens.nextToken());
			int c = Integer.parseInt(tokens.nextToken());
			int dis = 0;
			int small = 0;
			int big = 0;
			if(r > c) {
				small  =c;
				big = r;
				dis = r-c;
			}else {
				small = r;
				big = c;
				dis = c-r;
			}
			int dark = 2*((small*(small+1)*(2*small+1)/6) + dis*(small*(small+1)/2)) - (small*(small+1)/2) - (big*(big+1)/2) + (dis*(dis+1)/2); 
			sb.append(String.format("%d %d\n",dark+small, dark));
		}
		System.out.print(sb);
	}
}