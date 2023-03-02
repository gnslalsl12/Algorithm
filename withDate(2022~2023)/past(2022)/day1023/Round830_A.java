package day1023;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Round830_A {

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		int testcase = Integer.parseInt(tokens.nextToken());
		for (int test = 0; test < testcase; test++) {
			tokens = new StringTokenizer(read.readLine());
			
			int T = Integer.parseInt(tokens.nextToken());
			long count = 0;
			tokens = new StringTokenizer(read.readLine());
			withGCD[] arr = new withGCD[T+1];
			for (int i = 1; i <= T; i++) {
				arr[i] = new withGCD(Long.parseLong(tokens.nextToken()), i);
			}
			System.out.println(Arrays.toString(arr));
		}

	}

	private static long getGCD(long x, long index) {
		long tempx = x;
		long tempy = index;
		while (true) {
			long max = Math.max(tempx, tempy);
			long min = Math.min(tempx, tempy);
			if (max % min != 0) {
				max %= min;
			} else {
				return min;
			}
			tempx = max;
			tempy = min;
		}
	}

	private static class withGCD {
		long num;
		long gcd;
		public withGCD(long num, int index) {
			super();
			this.num = num;
			this.gcd = getGCD(num, index);
		}
		@Override
		public String toString() {
			return "[num=" + num + ", gcd=" + gcd + "]";
		}
		

	}

}
