package day1031;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_17085 {
	static int N, M;
	static int[] maps;
	static boolean[][] check;
	static int[][] deltas = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	static ArrayList<Holy> Hlist = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		Queue<dirXY> tempavail = new LinkedList<>();
		maps = new int[N];
		for (int i = 0; i < N; i++) {
			String temp = read.readLine();
			for (int j = 0; j < M; j++) {
				if (temp.charAt(j) == '#') {
					maps[i] |= 1 << j;
					tempavail.add(new dirXY(i, j));
				}
			}
		}
		while (!tempavail.isEmpty()) {
			getHoly(tempavail.poll());
		} // Hlist에 십자가 후보들 생성
		Collections.sort(Hlist);
		int result = -1;
		for (int i = 0; i < Hlist.size(); i++) {
			int[] biggermap = Hlist.get(i).tempmaps;
			for (int j = i + 1; j < Hlist.size(); j++) {
				int[] smallermap = Hlist.get(j).tempmaps;

				if (!AvailComb(biggermap, smallermap)) {
					continue;
				} else { // 가능한 십자가 경우
					int tempresult = Hlist.get(i).size * Hlist.get(j).size;
					result = Math.max(result, tempresult);
				}
			}
		}
		System.out.println(result);

	}

	private static boolean AvailComb(int[] biggermap, int[] smallermap) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (((biggermap[i] & 1 << j) != 0) && ((smallermap[i] & 1 << j) != 0)) {
					return false;
				}
			}
		}
		return true;
	}

	private static void getHoly(dirXY startpoint) {
		int len = 1;
		breakall: for (len = 1; len < 10; len++) {
			for (int dir = 0; dir < 4; dir++) {
				int nextx = startpoint.x + deltas[dir][0] * len;
				int nexty = startpoint.y + deltas[dir][1] * len;
				if (!isIn(nextx, nexty))
					break breakall;
				if ((maps[nextx] & 1 << nexty) == 0) { // false면
					break breakall;
				}
			}
		}
		int[] tempmaps = new int[N];
		for (int l = 0; l < len; l++) {
			for (int dir = 0; dir < 4; dir++) {
				int nextx = startpoint.x + deltas[dir][0] * l;
				int nexty = startpoint.y + deltas[dir][1] * l;

				tempmaps[nextx] |= 1 << nexty;
			}
			Hlist.add(new Holy(startpoint, l, tempmaps.clone()));
		}
	}

	private static boolean isIn(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < M;
	}

	private static class Holy implements Comparable<Holy> {
		dirXY startpoint;
		int size;
		int[] tempmaps;

		public Holy(dirXY startpoint, int len, int[] tempmaps) {
			super();
			this.startpoint = startpoint;
			this.size = (len * 4) + 1;
			this.tempmaps = tempmaps;
		}

		@Override
		public int compareTo(Holy o) {
			return Integer.compare(o.size, this.size);
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
