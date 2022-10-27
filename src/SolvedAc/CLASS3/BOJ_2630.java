package SolvedAc.CLASS3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2630 {
	static int N;
	static boolean[][] maps;
	static int wcount, bcount;

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(read.readLine());
		maps = new boolean[N][N];
		wcount = bcount = 0;
		StringTokenizer tokens;
		for (int i = 0; i < N; i++) {
			tokens = new StringTokenizer(read.readLine());
			for (int j = 0; j < N; j++) {
				if (tokens.nextToken().equals("1"))
					maps[i][j] = true;
			}
		} // mapping
		splitting(0, 0, N);
		System.out.println(wcount);
		System.out.println(bcount);
	}

	private static void splitting(int startX, int startY, int size) {
		boolean check = maps[startX][startY];
		if (size == 1) {
			if (check)
				bcount++;
			else
				wcount++;
			return;
		}
		for (int x = startX; x < startX + size; x++) {
			for (int y = startY; y < startY + size; y++) {
				if (check != maps[x][y]) {
					splitting(startX, startY, size / 2);
					splitting(startX + size / 2, startY, size / 2);
					splitting(startX, startY + size / 2, size / 2);
					splitting(startX + size / 2, startY + size / 2, size / 2);
					return;
				}
			}
		}
		if (check)
			bcount++;
		else
			wcount++;
	}
}
