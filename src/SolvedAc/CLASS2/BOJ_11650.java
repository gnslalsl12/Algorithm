package SolvedAc.CLASS2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_11650 {

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		int N = Integer.parseInt(tokens.nextToken());
		PriorityQueue<dirXY> temp = new PriorityQueue<>();
		for (int i = 0; i < N; i++) {
			tokens = new StringTokenizer(read.readLine());
			temp.add(new dirXY(Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken())));
		}
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		while (!temp.isEmpty()) {
			write.write(temp.poll() + "\n");
		}
		write.close();
	}

	private static class dirXY implements Comparable<dirXY> {
		int x;
		int y;

		public dirXY(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return x + " " + y;
		}

		@Override
		public int compareTo(dirXY o) {
			if (this.y == o.y)
				return Integer.compare(this.x, o.x);
			return Integer.compare(this.y, o.y);
		}
	}
}