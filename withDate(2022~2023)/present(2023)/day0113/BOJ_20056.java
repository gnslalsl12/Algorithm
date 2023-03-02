package day0113;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_20056 {
	static int[][] deltas = { { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 }, { 0, -1 }, { -1, -1 } };
	static int N, M, K;
	static Queue<Fball> FBQ = new LinkedList<>();
	static Merg[][] maps;

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		K = Integer.parseInt(tokens.nextToken());
		maps = new Merg[N][M];
		for (int i = 0; i < M; i++) {
			tokens = new StringTokenizer(read.readLine());
			int r = Integer.parseInt(tokens.nextToken());
			int c = Integer.parseInt(tokens.nextToken());
			int m = Integer.parseInt(tokens.nextToken());
			int s = Integer.parseInt(tokens.nextToken());
			int d = Integer.parseInt(tokens.nextToken());
			FBQ.add(new Fball(r, c, m, d, s));
		}
		while (K-- > 0) {
			int size = FBQ.size();
			while (size-- > 0) {

			}
		}

	}

	private static void getSplit(Fball temp) {
	}

	private static void getMove(Fball temp) {

	}

	private static class Merg {
		int m;
		int s;
		int count;
		char dirs;

		public Merg(int m, int s, int count, char dirs) {
			super();
			this.m = m;
			this.s = s;
			this.count = count;
			this.dirs = dirs;
		}

	}

	private static class Fball {
		int r;
		int c;
		int m;
		int d;
		int s;

		public Fball(int r, int c, int m, int d, int s) {
			this.r = r;
			this.c = c;
			this.m = m;
			this.d = d;
			this.s = s;
		}

	}

}
