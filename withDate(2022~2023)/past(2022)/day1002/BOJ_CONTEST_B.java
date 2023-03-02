package day1002;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_CONTEST_B {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(read.readLine());
		int P = Integer.parseInt(read.readLine());
		int result = P;
		if (N < 5) {
			System.out.println(result);
			return;
		}
		result = P - 500;
		if (N < 10) {
			if(result < 0 ) result = 0;
			System.out.println(result);
			return;
		}
		result = Math.min(result, (P / 10) * 9);
		if (N < 15) {
			if(result < 0 ) result = 0;
			System.out.println(result);
			return;
		}
		result = Math.min(result, P - 2000);
		if (N < 20) {
			if(result < 0 ) result = 0;
			System.out.println(result);
			return;
		}
		result = Math.min(result, (P / 100) * 75);
		if(result < 0 ) result = 0;
		System.out.println(result);
	}
}