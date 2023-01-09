package SolvedAc.CLASS2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1966 {

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		int T = Integer.parseInt(tokens.nextToken());
		for (int test = 0; test < T; test++) {
			tokens = new StringTokenizer(read.readLine());
			int N = Integer.parseInt(tokens.nextToken());
			int IndexOfObj = Integer.parseInt(tokens.nextToken());
			tokens = new StringTokenizer(read.readLine());
			Queue<nums> prints = new LinkedList<>();
			PriorityQueue<Integer> check = new PriorityQueue<>(Collections.reverseOrder());
			for (int i = 0; i < N; i++) {
				int x = Integer.parseInt(tokens.nextToken());
				check.add(x);
				boolean f = false;
				if (i == IndexOfObj)
					f = true;
				prints.add(new nums(x, f));
			}
			int count = 0;
			while (!prints.isEmpty()) {
				nums temp = prints.poll();
				if (temp.x != check.peek()) {
					prints.add(temp);
					continue;
				} else {
					check.poll();
					count++;
					if (temp.f) {
						sb.append(count + "\n");
						break;
					}
				}
			}
		}
		System.out.print(sb);
	}

	private static class nums {
		int x;
		boolean f;

		public nums(int x, boolean f) {
			super();
			this.x = x;
			this.f = f;
		}
	}
}