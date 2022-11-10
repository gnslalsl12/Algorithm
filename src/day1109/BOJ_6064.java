package day1109;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_6064 {
	static int M, N, x, y;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(read.readLine());
		for (int test = 1; test <= T; test++) {
			StringTokenizer tokens = new StringTokenizer(read.readLine());
			M = Integer.parseInt(tokens.nextToken());
			N = Integer.parseInt(tokens.nextToken());
			x = Integer.parseInt(tokens.nextToken()) - 1;
			y = Integer.parseInt(tokens.nextToken()) - 1;
			long year = 0;
			if (N + M + x + y == 4) {
				write.write("1\n");
				continue;
			}
			for (year = x; year < M * N; year += M) {
				if (year % N == y) {
					break;
				}
			}
			if (year >= M * N) {
				write.write("-1\n");
			} else {
				write.write((year + 1) + "\n");
			}
		}
		write.close();
	}

}
