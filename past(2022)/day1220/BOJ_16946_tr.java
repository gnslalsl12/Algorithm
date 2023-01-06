package day1220;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_16946_tr {
	static int N, M, maps[][];
	static Queue<dirXY> ZeroList = new LinkedList<>();
	static Queue<dirXY> WallList = new LinkedList<>();
	static int[][] deltas = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		maps = new int[N][M];
		for (int i = 0; i < N; i++) {
			String templine = read.readLine();
			for (int j = 0; j < M; j++) {
				if (templine.charAt(j) == '1') {
					maps[i][j] = -1;
					WallList.add(new dirXY(i, j));
				} else {
					maps[i][j] = 0;
					ZeroList.add(new dirXY(i, j));
				}
			}
		}
		int zsize = ZeroList.size();
		for (int i = 0; i < zsize; i++) {
			dirXY temp = ZeroList.poll();
			ZeroList.add(temp);
			if (maps[temp.x][temp.y] != 0)
				continue;
			search(temp);
		}
		int size = WallList.size();
		for (int i = 0; i < size; i++) {
			dirXY temp = WallList.poll();
			get(temp);
		}
		while (!WallList.isEmpty()) {
			dirXY temp = WallList.poll();
			maps[temp.x][temp.y] = 1 + temp.v;
		}
		while (!ZeroList.isEmpty()) {
			dirXY temp = ZeroList.poll();
			maps[temp.x][temp.y] = 0;
		}
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				write.write(maps[i][j] + "");
			}
			write.write("\n");
		}
		write.close();
	}

	private static void get(dirXY temp) {
		int v = 0;
		for (int dir = 0; dir < 4; dir++) {
			int nextx = temp.x + deltas[dir][0];
			int nexty = temp.y + deltas[dir][1];
			if (!isIn(nextx, nexty) || maps[nextx][nexty] == -1)
				continue;
			v += maps[nextx][nexty];
		}
		WallList.add(new dirXY(temp.x, temp.y, v));
	}

	private static void search(dirXY temp) {
		ArrayList<dirXY> arr = new ArrayList<>();
		arr.add(temp);
		maps[temp.x][temp.y] = 1;

		for (int i = 0; i < arr.size(); i++) {
			dirXY pop = arr.get(i);
			for (int dir = 0; dir < 4; dir++) {
				int nextx = pop.x + deltas[dir][0];
				int nexty = pop.y + deltas[dir][1];
				if (!isIn(nextx, nexty) || maps[nextx][nexty] != 0)
					continue;
				maps[nextx][nexty] = 1;
				arr.add(new dirXY(nextx, nexty));
				maps[temp.x][temp.y]++;
			}
		}
		for (dirXY d : arr) {
			maps[d.x][d.y] = maps[temp.x][temp.y];
		}
	}

	private static boolean isIn(int x, int y) {
		return x >= 0 && y >= 0 && x < N && y < M;
	}

	private static class dirXY {
		int x;
		int y;
		int v;

		public dirXY(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public dirXY(int x, int y, int v) {
			this.x = x;
			this.y = y;
			this.v = v;
		}

	}

}
