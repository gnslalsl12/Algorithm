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
			x = Integer.parseInt(tokens.nextToken());
			y = Integer.parseInt(tokens.nextToken());
			long i = 0;
			for (i = M + x; i <= M*N; i += M) {
				if (i % N == y) {
					break;
				}
			}
			if (i > M*N) {
				write.write("-1\n");
			} else {
				write.write(i + "\n");
			}
		}
		write.close();

	}

}
