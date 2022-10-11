package day0929;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1463 {
	static int N;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(read.readLine());

		int[] stack = new int[N + 1];

		for (int i = N; i >= 1; i--) {
			if (i * 3 <= N) {
				if (stack[i] == 0) {
					stack[i] = stack[i * 3] + 1;
				} else {
					stack[i] = Math.min(stack[i * 3] + 1, stack[i]);
				}
			}

			if (i * 2 <= N) {
				if (stack[i] == 0) {
					stack[i] = stack[i * 2] + 1;
				} else {
					stack[i] = Math.min(stack[i * 2] + 1, stack[i]);
				}
			}

			if (i + 1 <= N) {
				if (stack[i] == 0) {
					stack[i] = stack[i + 1] + 1;
				} else {
					stack[i] = Math.min(stack[i + 1] + 1, stack[i]);
				}
			}
		}
		System.out.println(stack[1]);
	}
}