package day1005;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2636 {
	static int N, M;
	static boolean[][] maps;
	static int[][] deltas = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	static int count;
	static int minuscount;
	static dirXY start;

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		maps = new boolean[N][M];
		count = 0;
		for (int i = 0; i < N; i++) {
			tokens = new StringTokenizer(read.readLine());
			for (int j = 0; j < M; j++) {
				maps[i][j] = Integer.parseInt(tokens.nextToken()) == 1 ? true : false;
				if (maps[i][j])
					count++;
			}
		}
		int time = 0;
		while (true) {
			time++;
			meltTemp();
			if (count == 0)
				break;
		}

		System.out.println(time);
		System.out.println(minuscount);
	}

	private static void meltTemp(dirXY start) {
		Queue<dirXY> BFSQ = new LinkedList<>();
		BFSQ.add(start);
		while (!BFSQ.isEmpty()) {
			dirXY temp = BFSQ.poll();
			for (int dir = 0; dir < 4; dir++) {
				int x = temp.x + deltas[dir][0];
				int y = temp.y + deltas[dir][1];
				if(!maps[x][y]) {
					if(dir == 0 || dir == 2) {
						BFSQ.add(new dirXY(temp.x + deltas[1][0], temp.y + deltas[1][1]));
						BFSQ.add(new dirXY(temp.x + deltas[3][0], temp.y + deltas[3][1]));
					}else if(dir ==1 || dir == 3) {
						BFSQ.add(new dirXY(temp.x + deltas[2]][0], temp.y + deltas[2][1]));
						BFSQ.add(new dirXY(temp.x + deltas[4]][0], temp.y + deltas[4][1]));
					}
				}
			}

		}
	}

	private static class dirXY {
		int x;
		int y;

		public dirXY(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

	}
}
