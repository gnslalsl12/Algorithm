package day01115;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_9944_NoneClone {
	static int N, M, maps[], result, count;
	static int[][] deltas = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer tokens;
		String input = "";
		int test = 1;
		while ((input = read.readLine()) != null && !input.isEmpty()) {
			tokens = new StringTokenizer(input);
			N = Integer.parseInt(tokens.nextToken());
			M = Integer.parseInt(tokens.nextToken());
			maps = new int[N];
			result = Integer.MAX_VALUE;
			count = 0;
			for (int i = 0; i < N; i++) {
				String templine = read.readLine();
				for (int j = 0; j < M; j++) {
					if (templine.charAt(j) == '.') {
						maps[i] |= 1 << j;
						count++;
					}
				}
			}
			write.write("Case " + test++ + ": " + solv() + "\n");
		}
		write.close();
		read.close();
	}

	private static int solv() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if ((maps[i] & 1 << j) != 0) {
					maps[i] &= ~(1 << j);
					getSearch(i, j, 0, -5, count - 1);
					maps[i] |= 1 << j;
				}
			}
		}
		if (result == Integer.MAX_VALUE)
			result = -1;
		return result;
	}

	private static void getSearch(int x, int y, int count, int lastdir, int remaincount) {
		if (remaincount == 0) { // 남은 경로가 없음
			result = Math.min(result, count);
			return;
		}
		for (int dir = 0; dir < 4; dir++) {
			if (dir == lastdir || dir == (lastdir + 2) % 4) // 이전 방향/반대방향 제외
				continue;
			int nextx = x + deltas[dir][0];
			int nexty = y + deltas[dir][1];
			if (!isIn(nextx, nexty) || (maps[nextx] & 1 << nexty) == 0) // 바로 다음칸이 false
				continue;
			int tempcount = 0;
			while (isIn(nextx, nexty) && (maps[nextx] & 1 << nexty) != 0) { // 막힐 때까지 이동
				maps[nextx] &= ~(1 << nexty);
				tempcount++;
				nextx += deltas[dir][0];
				nexty += deltas[dir][1];
			}
			if (remaincount == 0) {
				result = Math.min(result, count);
				continue;
			}
			nextx -= deltas[dir][0]; // 막히기 바로 이전 위치가 마지막 이동 위치
			nexty -= deltas[dir][1];
			getSearch(nextx, nexty, count + 1, dir, remaincount - tempcount); // DFS 재귀
			while (nextx != x || nexty != y) { // 맵 돌려놓기
				maps[nextx] |= 1 << nexty;
				nextx -= deltas[dir][0];
				nexty -= deltas[dir][1];
			}
		}
	}

	private static boolean isIn(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < M;
	}

}