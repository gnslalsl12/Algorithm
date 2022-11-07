package day1107;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_15661 {
	static int N;
	static int[][] maps;
	static int min;
	static boolean [] visits;
	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		maps = new int[N][N];
		min = Integer.MAX_VALUE;
		visits = new boolean [1<<N];
		for (int i = 0; i < N; i++) {
			tokens = new StringTokenizer(read.readLine());
			for (int j = 0; j < N; j++) {
				maps[i][j] = Integer.parseInt(tokens.nextToken());
			}
		}
		// mapping
		subset(0, 0);
		System.out.println(min);
	}

	private static void subset(int count, int sel) {
		if (count == N) {
			if (sel == 0 || sel == ((1 << N) - 1))
				return;
			if(visits[sel] || visits[(1<<N) - 1 - sel]) return;
			visits[sel] = visits[(1<<N) - 1 - sel] = true;
			int Atot = 0;
			int Btot = 0;
			for (int i = 0; i < N; i++) {
				for (int j = i + 1; j < N; j++) {
					if ((sel & 1 << i) != 0 && (sel & 1 << j) != 0) {
						Atot += maps[i][j] + maps[j][i];
					} else if ((sel & 1 << i) == 0 && (sel & 1 << j) == 0) {
						Btot += maps[i][j] + maps[j][i];
					}
				}
			}
			min = Integer.min(min, (Math.abs(Atot - Btot)));
			return;
		}
		sel |= 1 << count;
		subset(count + 1, sel);
		sel &= ~(1 << count);
		subset(count + 1, sel);
	}

}
