package SolvedAc.CLASS2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_4153 {

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens;
		StringBuilder sb = new StringBuilder();
		while (true) {
			tokens = new StringTokenizer(read.readLine());
			int [] arr= new int [3];
			arr[0] = Integer.parseInt(tokens.nextToken());
			arr[1] = Integer.parseInt(tokens.nextToken());
			arr[2] = Integer.parseInt(tokens.nextToken());
			if(arr[0] + arr[1]+  arr[2] == 0) break;
			int max = Math.max(arr[0], Math.max(arr[1], arr[2]));
			long tempmax = max;
			max *= max;
			for(int i = 0; i < 3; i++) {
				if(tempmax == arr[i]) continue;
				max -= arr[i]*arr[i];
			}
			if(max == 0) sb.append("right\n");
			else sb.append("wrong\n");
		}
		System.out.print(sb);
	}
}