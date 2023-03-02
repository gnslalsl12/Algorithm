package day0127;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_21611 {
	static int N, M;
	static int[][] Maps;
	static Queue<int[]> MagicList = new LinkedList<>();
	static ArrayList<Integer> Gathered = new ArrayList<>();
	static Queue<Integer> Counted = new LinkedList<>();
	static int[][] deltas = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	static int XY;
	static int result = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		Maps = new int[N + 1][N + 1];
		XY = N / 2 + 1;
		for (int i = 1; i <= N; i++) {
			tokens = new StringTokenizer(read.readLine());
			for (int j = 1; j <= N; j++) {
				Maps[i][j] = Integer.parseInt(tokens.nextToken());
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

	private static void solv() {
		while (!MagicList.isEmpty()) {
			doBlizzard();
			getCircle();
			doExplode();
			if (MagicList.isEmpty())
				return;
			setBalls();
		}
	}

	private static void setBalls() {
		if (Counted.isEmpty())
			return;
		int turncount = 1;
		int dir = 3;
		int x = XY;
		int y = XY;
		while (true) {
			for (int i = 0; i < turncount; i++) {
				x += deltas[dir][0];
				y += deltas[dir][1];
				if (!isIn(x, y) || Counted.isEmpty())
					return;
				Maps[x][y] = Counted.poll();
			}
			if (dir % 2 == 0)
				turncount++;
			dir = (dir + 3) % 4;
		}
	}

	private static void doExplode() {
		int samecount;
		int beforenum;
		while (true) {
			samecount = 1;
			if (Gathered.isEmpty())
				return;
			beforenum = Gathered.get(0);
			boolean hasExpld = false;
			for (int i = 1; i < Gathered.size(); i++) {
				int temp = Gathered.get(i);
				if (beforenum == temp) {
					samecount++;
					continue;
				} else {
					if (samecount >= 4) {
						hasExpld = true;
						result += samecount * beforenum;
						int j;
						for (j = i - 1; j >= i - samecount; j--) {
							Gathered.remove(j);
						}
						if (Gathered.isEmpty())
							break;
						i = j + 1;
					}
					samecount = 1;
					beforenum = temp;
				}
			}
			if (!hasExpld)
				break;
		}
		if (samecount >= 4) {
			result += samecount * beforenum;
		}
		Counted = new LinkedList<>();
		if (Gathered.isEmpty())
			return;
		int size = Gathered.size();
		samecount = 1;
		beforenum = Gathered.get(0);
		for (int i = 1; i < size; i++) {
			int temp = Gathered.get(i);
			if (temp == beforenum)
				samecount++;
			else {
				Counted.add(samecount);
				Counted.add(beforenum);
				beforenum = temp;
				samecount = 1;
			}
		}
		Counted.add(samecount);
		Counted.add(beforenum);
	}

	private static void getCircle() {
		Gathered = new ArrayList<>();
		int turncount = 1;
		int dir = 3;
		int x = XY;
		int y = XY;
		while (true) {
			for (int i = 0; i < turncount; i++) {
				x += deltas[dir][0];
				y += deltas[dir][1];
				if (!isIn(x, y)) {
					return;
				}
				if (Maps[x][y] == 0)
					continue;
				Gathered.add(Maps[x][y]);
				Maps[x][y] = 0;
			}
			if (dir % 2 == 0)
				turncount++;
			dir = (dir + 3) % 4;
		}
	}

	private static void doBlizzard() {
		int[] temp = MagicList.poll();
		int x = XY;
		int y = XY;
		int[] delta = { 0, 0, 2, 3, 1 };
		int dir = delta[temp[0]];
		for (int i = 1; i <= temp[1]; i++) {
			x += deltas[dir][0];
			y += deltas[dir][1];
			Maps[x][y] = 0;
			if (!isIn(x, y))
				return;
		}
	}

	private static boolean isIn(int x, int y) {
		return x >= 1 && x <= N && y >= 1 && y <= N;
	}
}
