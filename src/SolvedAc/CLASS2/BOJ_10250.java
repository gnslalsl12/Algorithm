package SolvedAc.CLASS2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_10250 {

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(tokens.nextToken());
		for (int i = 0; i < T; i++) {
			tokens = new StringTokenizer(read.readLine());
			int H = Integer.parseInt(tokens.nextToken());
			int W = Integer.parseInt(tokens.nextToken());
			int N = Integer.parseInt(tokens.nextToken());
			int hs = N/H;
			if(N%H != 0) hs ++;
			int floor = N%H;
			if(floor == 0) floor = H;
			sb.append((floor * 100 + hs) + "\n");
		}
		System.out.print(sb);
	}
}