package day1031;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1034_re {
	static int N, M, K;
	static boolean[][] maps;
	static int [] offcount;
	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		maps = new boolean[N][M];
		offcount = new int [N];
		for (int i = 0; i < N; i++) {
			int count = 0;
			String temp = read.readLine();
			for (int j = 0; j < M; j++) {
				if (temp.charAt(j) == '1') {
					maps[i][j] = true;
				}else {
					count++;
				}
			}
			offcount[i] = count;
		}
		K = Integer.parseInt(read.readLine());
	}

}
