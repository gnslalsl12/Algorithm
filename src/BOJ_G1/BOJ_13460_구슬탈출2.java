package BOJ_G1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_13460_구슬탈출2 {
	static int N, M;
	static int[] Map;
	static int[][] deltas = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	static int[] Hole = new int[2];

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		Map = new int[N];
		int[] FirstRed = { 0, 0 };
		int[] FirstBlue = { 0, 0 };
		for (int i = 0; i < N; i++) {
			String templine = read.readLine();
			for (int j = 0; j < M; j++) {
				if (templine.charAt(j) != '#') {
					Map[i] |= 1 << j;
					if (templine.charAt(j) == 'R')
						FirstRed = new int[] { i, j };
					else if (templine.charAt(j) == 'B')
						FirstBlue = new int[] { i, j };
					else if (templine.charAt(j) == 'O') {
						Hole = new int[] { i, j };
						Map[i] &= ~(1 << j);
					}
				}
			}
		}
		write.write(solv(FirstRed, FirstBlue) + '\n');
		write.close();
		read.close();
	}

	private static String solv(int[] red, int[] blue) {
		return getBFS(red, blue) + "";
	}

	private static int getBFS(int[] red, int[] blue) {
		Queue<Status> BFSQ = new LinkedList<>();
		int result = 0;
		BFSQ.add(new Status(red, blue, -1, 0));
		breakall: while (!BFSQ.isEmpty()) {
			Status temp = BFSQ.poll();
			for (int dir = 0; dir < 4; dir++) {
				if (temp.lastDir == dir)
					continue;
				int[] NextRed = new int[2];
				int[] NextBlue = new int[2];
				if (dir == 0) {
					if (temp.red[0] < temp.blue[0])
						redFirst(temp.red, temp.blue, dir);
					else
						blueFirst(temp.red, temp.blue, dir);
				} else if (dir == 1) {
					if (temp.red[1] > temp.blue[1])
						redFirst(temp.red, temp.blue, dir);
					else
						blueFirst(temp.red, temp.blue, dir);
				} else if (dir == 2) {
					if (temp.red[0] > temp.blue[0])
						redFirst(temp.red, temp.blue, dir);
					else
						blueFirst(temp.red, temp.blue, dir);
				} else {
					if (temp.red[1] < temp.blue[1])
						redFirst(temp.red, temp.blue, dir);
					else
						blueFirst(temp.red, temp.blue, dir);
				}
				if (NextRed.equals(temp.red) && NextBlue.equals(temp.blue))
					continue; // 아무것도 안 움직였으면 continue;
				if (Arrays.equals(Hole, NextBlue)) // 일단 파란공이 들어갔으면 false
					continue;
				else if (Arrays.equals(Hole, NextRed)) { // 파란공 안 들어갔는데 빨간공 들어갔으면 성공
					result = temp.timecount + 1;
					break breakall;
				}
				NextRed[0] -= deltas[dir][0];
				NextRed[1] -= deltas[dir][1];
				NextBlue[0] -= deltas[dir][0];
				NextBlue[1] -= deltas[dir][1];
				BFSQ.add(new Status(NextRed, NextBlue, dir, temp.timecount + 1));
			}
		}
		return result;
	}

	private static void redFirst(int[] red, int[] blue, int dir) {
		red = getFarestDist(red, dir, blue);
		blue = getFarestDist(blue, dir, red);
	}

	private static void blueFirst(int[] red, int[] blue, int dir) {
		blue = getFarestDist(blue, dir, red);
		red = getFarestDist(red, dir, blue);
	}

	private static int[] getFarestDist(int[] ball, int dir, int[] otherball) {
		int x = ball[0];
		int y = ball[1];
		for (int dist = 1; dist < 10; dist++) {
			x += deltas[dir][0];
			y += deltas[dir][1];
			if ((Map[x] & (1 << y)) == 0 || Arrays.equals(ball, otherball)) {
				break;
			}
		}
		return new int[] { x, y };

	}

	private static class Status {
		int[] red;
		int[] blue;
		int lastDir;
		int timecount;

		public Status(int[] red, int[] blue, int lastDir, int timecount) {
			this.red = red.clone();
			this.blue = blue.clone();
			this.lastDir = lastDir;
			this.timecount = timecount;
		}

	}

}
