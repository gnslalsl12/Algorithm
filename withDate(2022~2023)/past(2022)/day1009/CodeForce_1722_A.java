package day1009;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CodeForce_1722_A {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(read.readLine());
		for (int test = 0; test < T; test++) {
			int N = Integer.parseInt(read.readLine());
			String templine = read.readLine();
			if (N != 5) {
				System.out.println("NO");
				continue;
			}
			if (templine.contains("T") && templine.contains("i") && templine.contains("m") && templine.contains("u")
					&& templine.contains("r"))
				System.out.println("YES");
			else
				System.out.println("NO");
		}
	}
}
