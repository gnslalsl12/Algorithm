package day1014;

import java.util.Scanner;

public class BOJ_2448 {
	static String gap = " ";
	static String star = "*";

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			String templine = "";
			for (int j = 1; j < n - i; j++) {
				templine = templine + gap;
			}
			sb.append(templine);
			if (i % 3 == 0) {
				sb.append(star);
			} else if (i % 3 == 1) {
				sb.append(star).append(gap).append(star);
			}else {
				sb.append(star).append(star).append(star).append(star).append(star);
			}
			
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
