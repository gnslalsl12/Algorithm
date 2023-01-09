package SolvedAc.CLASS5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_2467 {
	static int N;
	private static PriorityQueue<LQD> PQ = new PriorityQueue<>();

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(read.readLine());
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		for (int i = 0; i < N; i++) {
			int temp = Integer.parseInt(tokens.nextToken());
			PQ.add(new LQD(temp));
		}
		int temp1 = PQ.poll().v;
		int temp2 = 0;
		int dif = Integer.MAX_VALUE;
		int result1 = 0;
		int result2 = 0;
		while (!PQ.isEmpty()) {
			temp2 = temp1;
			temp1 = PQ.poll().v;
			int tempdif = Math.abs(temp1 + temp2);
			if (tempdif < dif) {
				dif = tempdif;
				result1 = Math.min(temp1, temp2);
				result2 = Math.max(temp1, temp2);
			}
			if (tempdif == 0)
				break;
		}
		System.out.println(result1 + " " + result2);
	}

	private static class LQD implements Comparable<LQD> {
		int v;

		public LQD(int v) {
			this.v = v;
		}

		@Override
		public int compareTo(LQD o) {
			return Integer.compare(Math.abs(this.v), Math.abs(o.v));
		}

	}

}
