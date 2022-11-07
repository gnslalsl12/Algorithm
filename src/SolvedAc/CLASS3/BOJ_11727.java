package SolvedAc.CLASS3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_11727 {
	static long[] dparr;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(read.readLine());
		dparr = new long[N + 1];
		if(N == 1) {
			System.out.println(1);
			return;
		}
		if(N == 2) {
			System.out.println(3);
			return;
		}
		dparr[1] = 1;
		dparr[2] = 3;
		for (int i = 3; i <= N; i++) {
			dparr[i] = (dparr[i - 1] + dparr[i - 2] * 2) % 10007;
		}
		System.out.println(dparr[N]);
	}
	
}
