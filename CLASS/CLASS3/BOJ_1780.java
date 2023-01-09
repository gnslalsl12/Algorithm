package SolvedAc.CLASS3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_1780 {
	static int[][] maps;
	static int[] counts = new int[3];

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		int N = Integer.parseInt(tokens.nextToken());
		maps = new int[N][N];
		for (int i = 0; i < N; i++) {
			tokens = new StringTokenizer(read.readLine());
			for (int j = 0; j < N; j++) {
				maps[i][j] = Integer.parseInt(tokens.nextToken());
			}
		}
		div9(0, 0, N);
		for (int i = 0; i < 3; i++) {
			write.write(counts[i] + "\n");
		}
		read.close();
		write.close();
	}

	private static void div9(int startindexX, int startindexY, int size) {
		int base = maps[startindexX][startindexY];
		if (size == 1) {
			counts[base + 1]++;
			return;
		}
		boolean same = true;
		breakall: for (int i = startindexX; i < startindexX + size; i++) {
			for (int j = startindexY; j < startindexY + size; j++) {
				if (maps[i][j] != base) {
					same = false;
					break breakall;
				}
			}
		}
		if (same) {
			counts[base + 1]++;
		} else {
			div9(startindexX, startindexY, size / 3);
			div9(startindexX + size / 3, startindexY, size / 3);
			div9(startindexX + 2 * (size / 3), startindexY, size / 3);
			div9(startindexX, startindexY + size / 3, size / 3);
			div9(startindexX + size / 3, startindexY + size / 3, size / 3);
			div9(startindexX + 2 * (size / 3), startindexY + size / 3, size / 3);
			div9(startindexX, startindexY + 2 * (size / 3), size / 3);
			div9(startindexX + size / 3, startindexY + 2 * (size / 3), size / 3);
			div9(startindexX + 2 * (size / 3), startindexY + 2 * (size / 3), size / 3);
		}
	}
}
