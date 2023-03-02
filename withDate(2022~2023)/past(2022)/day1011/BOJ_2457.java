package day1011;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ_2457 {
	static int N;
	static ArrayList<Flowers> Flist = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(read.readLine());
		StringTokenizer tokens;
		for (int i = 0; i < N; i++) {
			tokens = new StringTokenizer(read.readLine());
			int startM = Integer.parseInt(tokens.nextToken());
			int startD = Integer.parseInt(tokens.nextToken());
			int endM = Integer.parseInt(tokens.nextToken());
			int endD = Integer.parseInt(tokens.nextToken());
			if ((startM >= 12 && startD >= 1) || (endM <= 3 && endD <= 1))
				continue;
			Flist.add(new Flowers(startM, startD, endM, endD));
			System.out.println(Flist.get(i));
		}
		Collections.sort(Flist);

	}

	private static int getLen(int startM, int startD, int endM, int endD) {
		int result = 1;
		if (startM == 2) {
			result += 28 - startD;
		} else {
			switch (startM) {
			case (4):
			case (6):
			case (9):
			case (11): {
				result += 30 - startD;
				break;
			}
			default: {
				result += 31 - startD;
				break;
			}
			}
		}
		result += endD;
		for (int i = startM + 1; i < endM; i++) {
			if (i == 2) {
				result += 28;
			} else if (i == 4 || i == 6 || i == 9 || i == 11) {
				result += 30;
			} else {
				result += 31;
			}
		}
		return result;
	}

	private static class Flowers implements Comparable<Flowers> {
		int startM;
		int startD;
		int endM;
		int endD;
		int len;

		public Flowers(int startM, int startD, int endM, int endD) {
			super();
			this.startM = startM;
			this.startD = startD;
			this.endM = endM;
			this.endD = endD;
			this.len = getLen(startM, startD, endM, endD);
		}

		@Override
		public String toString() {
			return "[startM=" + startM + ", startD=" + startD + ", endM=" + endM + ", endD=" + endD + ", len=" + len
					+ "]";
		}

		@Override
		public int compareTo(Flowers o) {
			if (this.len == o.len) {
				if (this.startM == o.startM) {
					return Integer.compare(this.startD, o.startD);
				}
				return Integer.compare(this.startM, o.startM);
			}
			return Integer.compare(this.len, o.len);
		}

	}

}
