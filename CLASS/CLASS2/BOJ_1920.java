package SolvedAc.CLASS2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1920 {

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		int N = Integer.parseInt(tokens.nextToken());
		PriorityQueue<Long> Nums = new PriorityQueue<>();
		tokens = new StringTokenizer(read.readLine());
		for (int i = 0; i < N; i++) {
			Nums.add(Long.parseLong(tokens.nextToken()));
		}
		tokens = new StringTokenizer(read.readLine());
		int M = Integer.parseInt(tokens.nextToken());
		boolean[] outs = new boolean[M];
		PriorityQueue<nums> Sets = new PriorityQueue<>();
		tokens = new StringTokenizer(read.readLine());
		for (int i = 0; i < M; i++) {
			Sets.add(new nums(Long.parseLong(tokens.nextToken()), i));
		}
		Long before = Sets.peek().num + 1;
		for (int i = 0; i < M; i++) {
			nums check = Sets.poll();
			if(before == check.num) {
				outs[check.index] = true;
				continue;
			}
			before = check.num;
			if (Nums.isEmpty()) {
				outs[check.index] = false;
			} else {
				while (!Nums.isEmpty()) {
					Long to = Nums.peek();
					if (to == check.num) {
						outs[check.index] = true;
						break;
					}
					if (Nums.isEmpty())
						outs[check.index] = false;
					Nums.poll();
				}
			}
		}
		for (int i = 0; i < M; i++) {
			if (outs[i])
				System.out.println(1);
			else
				System.out.println(0);
		}
	}

	private static class nums implements Comparable<nums> {
		Long num;
		int index;

		public nums(Long num, int index) {
			super();
			this.num = num;
			this.index = index;
		}

		@Override
		public int compareTo(nums o) {
			return Long.compare(this.num, o.num);
		}

	}
}