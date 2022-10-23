package SolvedAc.CLASS2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_9012 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(read.readLine());
		StringBuilder sb = new StringBuilder();
		for (int test = 0; test < T; test++) {
			String temp = read.readLine();
			int count = 0;
			for (int i = 0; i < temp.length(); i++) {
				if(temp.charAt(i) == '(') count++;
				else {
					count--;
					if(count == -1) {
						sb.append("NO\n");
						break;
					}
				}
			}
			if(count != -1) {
				if(count == 0) sb.append("YES\n");
				else sb.append("NO\n");
			}
		}
		System.out.print(sb);
	}
}