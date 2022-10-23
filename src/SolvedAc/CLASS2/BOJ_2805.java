package SolvedAc.CLASS2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_2805 {
	static long M;
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Long.parseLong(tokens.nextToken());
		ArrayList<Long> Trees = new ArrayList<>();
		tokens = new StringTokenizer(read.readLine());
		long max = Long.MIN_VALUE;
		for (int i = 0; i < N; i++) {
			long temp = Long.parseLong(tokens.nextToken());
			Trees.add(temp);
			max = Math.max(max, temp);
		}
		max -= (M / N);
		long min = max - M;
		long cutline = 0;
		while (true) {
			cutline = (max + min) / 2;
			long remaincount = 0;
			for (int i = 0; i < N; i++) {
				if (Trees.get(i) <= cutline)
					continue; 
				remaincount += Trees.get(i) - cutline;
			}
			if (remaincount > M) { // 생각보다 많이 남음 =>높이를 올리자
				if (min == cutline + 1)
					break;
				min = cutline + 1;
			} else if (remaincount < M) { // 적게 남음 => 높이를 내리자
				if (max == cutline - 1)
					break;
				max = cutline - 1;
			} else
				break;
		}
		System.out.println(cutline);
	}
}