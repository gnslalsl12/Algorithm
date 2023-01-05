package day1229;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1202 {
	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		int N = Integer.parseInt(tokens.nextToken());
		int K = Integer.parseInt(tokens.nextToken());
		PriorityQueue<Jew> Jlist = new PriorityQueue<>();
		for (int i = 0; i < N; i++) {
			tokens = new StringTokenizer(read.readLine());
			Jlist.add(new Jew(Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken())));
		}
		PriorityQueue<Integer> Blist = new PriorityQueue<>();
		for (int i = 0; i < K; i++) {
			Blist.add(Integer.parseInt(new StringTokenizer(read.readLine()).nextToken()));
		}
		PriorityQueue<Integer> tempJlist = new PriorityQueue<>(Collections.reverseOrder());
		long total = 0;
		while (!Blist.isEmpty()) {
			int tempB = Blist.poll();
			while (!Jlist.isEmpty()) {
				if (tempB < Jlist.peek().m)
					break;
				tempJlist.add(Jlist.poll().v);
			}
			if (!tempJlist.isEmpty())
				total += tempJlist.poll();
		}
		write.write(total + "\n");
		write.close();
		read.close();
	}

	private static class Jew implements Comparable<Jew> {
		int m;
		int v;

		public Jew(int m, int v) {
			this.m = m;
			this.v = v;
		}

		@Override
		public int compareTo(Jew o) {
			if (this.m == o.m) {
				return Integer.compare(o.v, this.v);
			}
			return Integer.compare(this.m, o.m);
		}

	}

}
