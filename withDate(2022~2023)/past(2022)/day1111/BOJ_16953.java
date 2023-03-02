package day1111;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_16953 {
	static long A, B;

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		A = Integer.parseInt(tokens.nextToken());
		B = Integer.parseInt(tokens.nextToken());
		PriorityQueue<hoons> pq = new PriorityQueue<>();
		pq.add(new hoons(A, 1));
		int result = Integer.MAX_VALUE;
		while (!pq.isEmpty()) {
			hoons temp = pq.poll();

			if (temp.num * 2 == B || temp.num * 10 + 1 == B) {
				result = Math.min(result, temp.count + 1);
				break;
			}

			if (temp.num * 2 > B)
				continue;
			pq.add(new hoons(temp.num * 2, temp.count + 1));

			if (temp.num * 10 + 1 > B)
				continue;
			pq.add(new hoons(temp.num * 10 + 1, temp.count + 1));
		}
		if (result == Integer.MAX_VALUE)
			result = -1;
		System.out.println(result);
	}

	private static class hoons implements Comparable<hoons> {
		long num;
		int count;

		public hoons(long num, int count) {
			super();
			this.num = num;
			this.count = count;
		}

		@Override
		public int compareTo(hoons o) {
			return this.count == o.count ? Long.compare(o.num, this.num) : Integer.compare(this.count, o.count);
		}
	}
}