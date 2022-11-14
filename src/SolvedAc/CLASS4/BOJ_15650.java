package SolvedAc.CLASS4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_15650 {
	static BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		int N = Integer.parseInt(tokens.nextToken());
		int M = Integer.parseInt(tokens.nextToken());
		Comb(N, M, 0, 1, 1);
		write.close();
	}

	private static void Comb(int N, int M, int count, int start, int sel) throws IOException {
		if (count == M) {
			for (int i = 1; i <= N; i++) {
				if ((sel & (1 << i)) != 0) {
					write.write(i + " ");
				}
			}
			write.write("\n");
			return;
		}

		for (int i = start; i <= N; i++) {
			sel |= 1 << i;
			Comb(N, M, count + 1, i + 1, sel);
			sel &= ~(1 << i);
		}

	}

}
