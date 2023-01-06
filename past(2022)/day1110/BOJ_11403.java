package day1110;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_11403 {
	static int N;
	static boolean[][] maps;

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(read.readLine());
		maps = new boolean[N][N];
		StringTokenizer tokens;
		for (int i = 0; i < N; i++) {
			tokens = new StringTokenizer(read.readLine());
			for (int j = 0; j < N; j++) {
				if (tokens.nextToken().equals("1")) {
					maps[i][j] = true;
				}
			}
		}
		floydwashall();
		print();
	}

	private static void print() throws IOException {
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (maps[i][j]) {
					write.write("1");
				} else {
					write.write("0");
				}
				if (j == N - 1) {
					write.write("\n");
				} else {
					write.write(" ");
				}
			}
		}
		write.flush();
	}

	private static void floydwashall() throws IOException {
		for (int c = 0; c < N; c++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (maps[i][c] && maps[c][j]) {
						maps[i][j] = true;
					}
				}
			}
		}
	}
}
