package day0126;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_21611 {
	static int N, M;
	static int[][] maps;
	static Queue<int[]> MagicList = new LinkedList<>();
	static int X;
	static int[][] deltas = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	static long result = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		X = N / 2 + 1;
		maps = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			tokens = new StringTokenizer(read.readLine());
			for (int j = 1; j <= N; j++) {
				maps[i][j] = Integer.parseInt(tokens.nextToken());
			}
		}
		for (int i = 0; i < M; i++) {
			tokens = new StringTokenizer(read.readLine());
			MagicList.add(new int[] { Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken()) });
		}
		solv();
		write.write(result + "\n");
		write.close();
		read.close();
	}

	private static void print() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				System.out.printf("%d ", maps[i][j]);
			}
			System.out.println();
		}
	}

	private static void solv() {
		while (!MagicList.isEmpty()) {
			doBlizzard(MagicList.poll());
			setBalls(doExplode(getCircle()));
		}
	}

	private static void setBalls(Queue<Integer> input) {
		int turncount = 1;
		int dir = 3;
		int x = X;
		int y = X;
		while (!input.isEmpty()) {
			for (int i = 0; i < turncount; i++) {
				x += deltas[dir][0];
				y += deltas[dir][1];
				if (!isIn(x, y))
					return;
				maps[x][y] = input.poll();
			}
			if (dir % 2 == 0)
				turncount++;
			dir = (dir + 3) % 4;
		}
	}

	private static Queue<Integer> doExplode(Queue<dirXY> input) {
		int samecount = 0;
		int beforenum = -1;
		Queue<Integer> SameBalls = new LinkedList<>();
		while (true) {
			dirXY temp = input.poll();
			if (temp.x == -1)
				break;
			if (beforenum == temp.num) {
				samecount++;
			} else {
				if (samecount < 4) {
					SameBalls.add(samecount);
					SameBalls.add(beforenum);
				} else {
					result += samecount * beforenum;
				}
				beforenum = temp.num;
				samecount = 1;
				continue;
			}
		}
		return SameBalls;
	}

	private static Queue<dirXY> getCircle() {
		Queue<dirXY> Tornado = new LinkedList<>();
		int x = X;
		int y = X;
		int turncount = 1;
		int dir = 3;
		boolean done = false;
		while (!done) {
			for (int i = 0; i < turncount; i++) {
				x += deltas[dir][0];
				y += deltas[dir][1];
				System.out.println(x + ", " + y);
				if (!isIn(x, y)) {
					System.out.println("xy : " + x + ", " + y);
					done = true;
					break;
				}
				if (maps[x][y] == 0)
					continue;
				maps[x][y] = 0;
				Tornado.add(new dirXY(x, y, maps[x][y]));
			}
			if (dir % 2 == 0) {
				turncount++;
			}
			dir = (dir + 3) % 4;
		}
		Tornado.add(new dirXY(-1, -1, -1));
		print();
		return Tornado;
	}

	private static boolean isIn(int x, int y) {
		return x > 0 && x <= N && y > 0 && y <= N;
	}

	private static void doBlizzard(int[] input) {
		int[] delta;
		if (input[0] == 1)
			delta = new int[] { -1, 0 };
		else if (input[0] == 2)
			delta = new int[] { 1, 0 };
		else if (input[0] == 3)
			delta = new int[] { 0, -1 };
		else
			delta = new int[] { 0, 1 };
		for (int i = 1; i <= input[1]; i++) {
			maps[X + delta[0] * i][X + delta[1] * i] = 0;
		}

	}

	private static class dirXY {
		int x;
		int y;
		int num;
		int count;

		public dirXY(int x, int y, int num) {
			this.x = x;
			this.y = y;
			this.num = num;
		}

		public dirXY(int num, int count) {
			super();
			this.num = num;
			this.count = count;
		}

	}

}
