package SolvedAc.CLASS2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_15829 {

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		int L = Integer.parseInt(read.readLine());
		String temp = read.readLine();
		double result = 0;
		for (int i = 0; i < L; i++) {
			long n = (temp.charAt(i) - 'a' + 1);
			long r = 1;
			for(int j = 0; j < i; j++) {
				r *= 31;
				r %= 1234567891;
			}
			result += n*r;
		}
		System.out.println((long)(result%1234567891));
	}
}