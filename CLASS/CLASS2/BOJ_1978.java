package SolvedAc.CLASS2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1978 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(read.readLine());
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		int result = 0;
		for(int i = 0; i < N; i++) {
			int temp = Integer.parseInt(tokens.nextToken());
			if(check(temp)) result++;
		}
		System.out.println(result);
	}
	private static boolean check(int input) {
		if(input == 1) return false;
		if (input ==2) return true;
		if(input % 2== 0) return false;
		for(int i = 3; i <= Math.sqrt(input); i+=2) {
			if(input%i == 0) return false;
		}
		return true;
	}
}
