package SolvedAc.CLASS3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1931 {

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		int N = Integer.parseInt(tokens.nextToken());
		PriorityQueue<Times> timetable = new PriorityQueue<>();
		for (int i = 0; i < N; i++) {
			tokens = new StringTokenizer(read.readLine());
			long start = Integer.parseInt(tokens.nextToken());
			long end = Integer.parseInt(tokens.nextToken());
			timetable.add(new Times(start, end));
		}
		long max = -1;
		int count = 0;
		while (!timetable.isEmpty()) {
			Times temp = timetable.poll();
			if (temp.start < max)
				continue;
			count++;
			max = temp.end;
		}
		System.out.println(count);
	}

	private static class Times implements Comparable<Times> {
		long start;
		long end;

		public Times(long start, long end) {
			super();
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(Times o) {
			if (this.end == o.end) {
				return Long.compare(this.start, o.start);
			}
			return Long.compare(this.end, o.end);
		}
	}
}
