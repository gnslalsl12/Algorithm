package day1031;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1034 {
	static long[] maps;
	static int N, M, K;
	static int max;
	static ArrayList<nums> arr;
	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		maps = new long[M];
		arr = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			String temp = read.readLine();
			for (int j = 0; j < M; j++) {
				if (temp.charAt(j) == '1') {
					maps[j] |= 1 << i;
				}
			}
		} // mapping 완료
		for (int i = 0; i < M; i++) {
			if(switching(i) > maps[i]) {
				arr.add(new nums(maps[i], i));
			}
		}	//전환했을 떄 켜진 게 더 많을 때
		Collections.sort(arr);
		K = Integer.parseInt(read.readLine());
		if (K > M) {
			if (M % 2 == 1) {
				if (K % 2 == 1) {
					K = M;
				} else {
					K = M + 1;
				}
			} else {
				if (K % 2 == 1) {
					K = M + 1;
				} else {
					K = M;
				}
			}
		}
//		System.out.println("K : " + K);
		max = Integer.MIN_VALUE;
//		System.out.println(M);
//		System.out.println(K);
		Comb(0, new boolean[M], 0);
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
		max = Math.max(result, max);
		for (int i = 0; i < M; i++) {
			if (sel[i])
				switching(i);
		}
	}

	private static void Comb(int count, boolean[] sel, int i) {
		if (count > K)
			return;
		if (i == M) {
			if (count % 2 != K % 2)
				return;
			setSol(sel);
			return;
		}

		sel[i] = false;
		Comb(count, sel, i + 1);
		sel[i] = true;
		Comb(count + 1, sel, i + 1);

	}

	private static long switching(int line) {
		return (maps[line] ^ Long.MAX_VALUE) % (1 << N);
	}

	private static class nums implements Comparable<nums> {
		long sum;
		int index;

		public nums(long sum, int index) {
			super();
			this.sum = sum;
			this.index = index;
		}

		@Override
		public int compareTo(nums o) {
			return Long.compare(this.sum, o.sum);
		}

	}

}
