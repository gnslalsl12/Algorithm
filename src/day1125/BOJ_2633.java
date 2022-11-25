package day1125;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2633 {
	static dirXY rob;
	static dirXY dest;
	static ArrayList<dirXY> tempV;
	static int num = 10;
	static int[][] maps = new int[101][101];
	static int[][] countmaps = new int[101][101];
	static int[][] deltas = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		rob = new dirXY(Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken()));
		tokens = new StringTokenizer(read.readLine());
		dest = new dirXY(Integer.parseInt(tokens.nextToken()), Integer.parseInt(tokens.nextToken()));
		int v = Integer.parseInt(read.readLine());
		for (int i = 1; i <= v; i++) {
			tokens = new StringTokenizer(read.readLine());
			tempV = new ArrayList<>();
			for (int j = 0; j < 4; j++) {
				int x = Integer.parseInt(tokens.nextToken());
				int y = Integer.parseInt(tokens.nextToken());
				tempV.add(new dirXY(x, y));
			}
			setL();
		}
		for (int[] t : countmaps) {
			Arrays.fill(t, Integer.MAX_VALUE);
		}
	}

	private static void bfs() {
		Queue<dirXY> Q = new LinkedList<>();
		Q.add(rob);
		countmaps[rob.x][rob.y] = 0;
		while (!Q.isEmpty()) {
			dirXY temp = Q.poll();
			if (countmaps[temp.x][temp.y] < temp.count)
				continue;

			for (int dir = 0; dir < 4; dir++) {
				int nextx = temp.x + deltas[dir][0];
				int nexty = temp.y + deltas[dir][1];
				int tempcount = temp.dir == dir ? temp.count : temp.count + 1;
				if (maps[nextx][nexty] != 0) { // 벽만남
					if (Math.abs(maps[nextx][nexty] - maps[temp.x][temp.y]) == 1) { // 같이 연결된 벽임 => 따라가
						if (dir == temp.dir) {
							Q.add(new dirXY(nextx, nexty, temp.count, dir)); // 같은 방향으로 따라감
						} else {
							Q.add(new dirXY(nextx, nexty, temp.count + 1, dir)); // 다른 방향으로 따라감
						}
					}
				} else {

				}
				if (countmaps[nextx][nexty] < tempcount)
					continue;
			}

		}
	}

	private static void setL() {
		dirXY first = tempV.get(0);
		dirXY second = tempV.get(1);
		dirXY third = tempV.get(2);
		dirXY fourth = tempV.get(3);
		PriorityQueue<dirXY> tempQ = new PriorityQueue<>();
		if (first.x < third.x) {
			if (first.y < third.y) { // 1
				for (int x = first.x; x <= second.x; x++) {
					tempQ.add(new dirXY(x, first.y));
					if (x <= fourth.x) {
						tempQ.add(new dirXY(x, fourth.y));
						for (int y = first.y + 1; y < fourth.y; y++) {
							maps[x][y] = -1;
						}
					}
					if (x >= fourth.x) {
						tempQ.add(new dirXY())
						maps[x][third.y] = num++;
					}
				}
				for (int y = first.y; y <= third.y; y++) {
					maps[second.x][y] = num++;
					if (y <= fourth.y)
						maps[first.x][y] = num++;
					if (y >= fourth.y)
						maps[fourth.x][y] = num++;
				}
			} else { // 3
				for (int x = second.x; x <= third.x; x++) {
					maps[x][second.y] = num++;
					if (x <= fourth.x)
						maps[x][first.y] = num++;
					if (x >= fourth.x)
						maps[x][fourth.y] = num++;
				}
				for (int y = second.y; y <= first.y; y++) {
					maps[second.x][y] = num++;
					if (y <= fourth.y)
						maps[third.x][y] = num++;
					if (y >= fourth.y)
						maps[fourth.x][y] = num++;
				}
			}
		} else {
			if (first.y < third.y) { // 2
				for (int x = third.x; x <= second.x; x++) {
					maps[x][third.y] = num++;
					if (x <= fourth.x)
						maps[x][fourth.y] = num++;
					if (x >= fourth.x)
						maps[x][first.y] = num++;
				}
				for (int y = first.y; y <= second.y; y++) {
					maps[first.x][y] = num++;
					if (y <= fourth.y)
						maps[fourth.x][y] = num++;
					if (y >= fourth.y)
						maps[third.x][y] = num++;
				}
			} else { // 4
				for (int x = third.x; x <= first.x; x++) {
					maps[x][second.y] = num++;
					if (x <= fourth.x)
						maps[x][third.y] = num++;
					if (x >= fourth.x)
						maps[x][fourth.y] = num++;
				}
				for (int y = third.y; y <= second.y; y++) {
					maps[third.x][y] = num++;
					if (y <= fourth.y)
						maps[fourth.x][y] = num++;
					if (y >= fourth.y)
						maps[first.x][y] = num++;
				}
			}
		}

	}

	private static class dirXY implements Comparable<dirXY> {
		int x;
		int y;
		int count;
		int dir;

		public dirXY(int x, int y) {
			this.x = x;
			this.y = y;
			this.count = 0;
			this.dir = -1;
		}

		public dirXY(int x, int y, int count, int dir) {
			this.x = x;
			this.y = y;
			this.count = count;
			this.dir = dir;
		}

		@Override
		public int compareTo(dirXY o) {
			return this.x == o.x ? Integer.compare(this.y, o.y) : Integer.compare(this.x, o.x);
		}

	}

}
