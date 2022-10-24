package SolvedAc.CLASS2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_18111 { // 하나씩 높이는 거(1초) 총 시간보다 하나씩 제거하는 거(2초) 시간 비교해서 하면됨(근데 남은 블록 없으면 제거)
	static int N, M, B;
	static int[] height;
	static int K = 257;

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		B = Integer.parseInt(tokens.nextToken());
		height = new int[257];
		for (int i = 0; i < N; i++) {
			tokens = new StringTokenizer(read.readLine());
			for (int j = 0; j < M; j++) {
				int h = Integer.parseInt(tokens.nextToken());
				height[h]++;
			}
		}
		int time = 0;
		int hstfloor = 0;
		while (true) {
			int[] hst = getHighest();
			int[] lst = getLoewst();
			int hstcount = hst[0];
			hstfloor = hst[1];
			int lstcount = lst[0];
			int lstfloor = lst[1];
//			System.out.println(Arrays.toString(hst));
//			System.out.println(Arrays.toString(lst));
//			System.out.println();
			if (hstfloor == lstfloor)
				break;
			if (lstcount > hstcount * 2 || B == 0) { // 파내는 게 더 짧아
				time += hstcount * 2;
				B += hstcount;
				height[hstfloor] = 0;
				continue;
			} else { // 쌓는 게 더 짧아 (같으면 쌓는 게 더좋아
				if (B < lstcount) { // 갖고있는 걸로 못 쌓음
					time += B;
					B = 0;
					height[lstfloor] -= B;
				} else { // 갖고있는 걸로 쌓을 수 있음
					time += lstcount;
					B -= lstcount;
					height[lstfloor] -= lstcount;
				}
				continue;
			}
		}
		System.out.println(time + " " + hstfloor);
	}

	private static int[] getLoewst() {
		int result = 0;
		int floor = 0;
		for (int i = 0; i < K; i++) {
			if (height[i] != 0) {
				result = height[i];
				floor = i;
				break;
			}
			if (i == K - 1)
				result = -1;
		}
		return new int[] { result, floor };
	}

	private static int[] getHighest() {
		int result = 0;
		int floor = 0;
		for (int i = K - 1; i >= 0; i--) {
			if (height[i] != 0) {
				result = height[i];
				floor = i;
				break;
			}
			if (i == 0)
				result = -1;
		}
		return new int[] { result, floor };
	}

}
