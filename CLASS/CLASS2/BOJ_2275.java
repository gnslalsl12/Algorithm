package SolvedAc.CLASS2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2275 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(read.readLine());
		for (int test = 0; test < T; test++) {
			int K = Integer.parseInt(read.readLine()); // 층
			int N = Integer.parseInt(read.readLine()); // 호수
			int[][] apt = new int[K + 1][N + 1];
			for (int i = 1; i <= N; i++) {
				apt[0][i] = i;
			}
			for (int line = 1; line <= K; line++) {
				int horcount = 0;
				for (int hs = 1; hs <= N; hs++) {
					horcount += apt[line - 1][hs];
					apt[line][hs] = horcount;
				}
			}
			System.out.println(apt[K][N]);
		}
	}
}