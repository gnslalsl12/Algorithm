package day1107;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;

public class BOJ_11279 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(read.readLine());
		PriorityQueue<Hs> pq = new PriorityQueue<>();
		for (int i = 0; i < N; i++) {
			int temp = Integer.parseInt(read.readLine());
			if (temp == 0) {
				if (pq.isEmpty()) {
					write.write("0\n");
				} else {
					write.write(pq.poll().num + "\n");
				}
			} else {
				pq.add(new Hs(temp));
			}
		}
		write.close();
	}

	private static class Hs implements Comparable<Hs> {
		int num;
		int abs;

		public Hs(int num) {
			this.num = num;
			this.abs = (int) Math.abs(num);
		}

		@Override
		public int compareTo(Hs o) {
			if (this.abs == o.abs)
				return Integer.compare(this.num, o.num);
			return Integer.compare(this.abs, o.abs);
		}

	}

}
