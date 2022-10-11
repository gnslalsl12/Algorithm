package day1011;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class BOJ_1338 {

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(read.readLine());
		int[] alpha = new int[26];
		for (int i = 0; i < N; i++) {
			String in = read.readLine();
			for (int c = 0; c < in.length(); c++) {
				char ch = in.charAt(c);
				alpha[ch - 'A'] += (int) Math.pow(10, in.length() - 1 - c);
			}
		}
		PriorityQueue<Integer> rs = new PriorityQueue<>(Collections.reverseOrder());
		for (int i = 0; i < 26; i++) {
			if (alpha[i] != 0) {
				rs.add(alpha[i]);
			}
		}
		int result = 0;
		int p = 9;
		while (!rs.isEmpty()) {
			result += (p--) * rs.poll();
		}
		System.out.println(result);
	}
}
