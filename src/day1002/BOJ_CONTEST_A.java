package day1002;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_CONTEST_A {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(read.readLine());
		StringBuilder sb = new StringBuilder();
		System.out.println("int a;");
		for (int i = 1; i <= N; i++) {
			sb.append("int ");
			for (int j = 1; j <= i; j++) {
				sb.append("*");
			}
			sb.append("ptr");
			if (i == 1) {
				sb.append(" = &a;\n");
			} else if (i == 2) {
				sb.append("2 = &ptr;\n");
			} else {
				sb.append(i).append(" = &ptr").append(i - 1).append(";\n");
			}
		}
		System.out.print(sb);
	}
}
