package day0112;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_2344 {
	static int N, M;
	static boolean[][] maps;
	static int count;
	static int[][] deltas = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	static int[] result;
	static int MAX;
	static int U = 0, R = 1, D = 2, L = 3;

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		maps = new boolean[N + 2][M + 2];
		MAX = 2 * N + 2 * M;
		result = new int[2 * N + 2 * M + 1];
		for (int i = 1; i <= N; i++) {
			tokens = new StringTokenizer(read.readLine());
			for (int j = 1; j <= M; j++) {
				if (tokens.nextToken().equals("1")) {
					maps[i][j] = true;
				}
			}
		}
		int[] point = { 0, 1 };
		while (point[1] <= M) {
			flashing(point, D);
			point[1]++;
		}
		point[0]++;
		while (point[0] <= N) {
			flashing(point, L);
			point[0]++;
		}
		point[1]--;
		while (point[1] >= 1) {
			flashing(point, U);
			point[1]--;
		}
		point[0]--;
		while (point[0] >= 1) {
			flashing(point, R);
			point[0]--;
		}
		for (int i = 1; i < result.length; i++)
			write.write(result[i] + " ");
		write.write("\n");
		write.close();
		read.close();
	}

	private static int getHoleNum(int[] hole) {
		count++;
		int result = 0;
		if (hole[0] == 0)
			result = MAX - (hole[1] - 1);
		else if (hole[1] == M + 1)
			result = MAX - M - (hole[0] - 1);
		else if (hole[0] == N + 1)
			result = N + hole[1];
		else if (hole[1] == 0)
			result = hole[0];
		return result;
	}

	private static void flashing(int[] enter, int dir) {
		int nextx = enter[0] + deltas[dir][0];
		int nexty = enter[1] + deltas[dir][1];
		while (true) {
			if (nextx == 0 || nextx == N + 1 || nexty == 0 || nexty == M + 1)
				break;
			if (maps[nextx][nexty]) {
				if (dir == R)
					dir = U;
				else if (dir == U)
					dir = R;
				else if (dir == L)
					dir = D;
				else if (dir == D)
					dir = L;
			}
			nextx += deltas[dir][0];
			nexty += deltas[dir][1];
		}
		result[getHoleNum(enter)] = getHoleNum(new int[] { nextx, nexty });
	}

}
