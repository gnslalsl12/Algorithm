package day1031;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1034 {
	static long[] maps;
	static int N, M, K;
	static int max;

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		maps = new long[M];
		for (int i = 0; i < N; i++) {
			String temp = read.readLine();
			for (int j = 0; j < M; j++) {
				if (temp.charAt(j) == '1') {
					maps[j] |= 1 << i;
				}
			}
		} // mapping 완료
		K = Integer.parseInt(read.readLine());
		if (K >= M)
			K = M;
		max = Integer.MIN_VALUE;
		System.out.println(M);
		System.out.println(K);
		Comb(0, new boolean [M], 0);
		System.out.println(max);
	}

	private static void print() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if ((maps[j] & 1 << i) != 0) {
					sb.append("1 ");
				} else {
					sb.append("0 ");
				}
			}
			sb.append("\n");
		}
		System.out.println(sb);
		System.out.println();
	}

	private static void setSol(boolean[] sel) {
		for (int i = 0; i < M; i++) {
			if (sel[i])
				switching(i);
		}
		int result = 0;
		for (int i = 0; i < N; i++) {
			int tempcount = 0;
			for (int j = 0; j < M; j++) {
				if ((maps[j] & (1 << i)) != 0) {
					tempcount++;
				}
			}
			if (tempcount == M)
				result++;
		}
		System.out.println("/////////////////////");
		System.out.println(Arrays.toString(sel));
		print();
		max = Math.max(result, max);
		for (int i = 0; i < M; i++) {
			if (sel[i])
				switching(i);
		}
	}

	private static void Comb(int count, boolean[] sel, int i) {
		if(count > K) return;
		if (i == M) {
			setSol(sel);
			return;
		}

		sel[i] = false;
		Comb(count, sel, i + 1);
		sel[i] = true;
		Comb(count + 1, sel, i + 1);

	}

	private static void switching(int line) {
		maps[line] = (maps[line] ^ Long.MAX_VALUE) % (1 << N);
	}

}
