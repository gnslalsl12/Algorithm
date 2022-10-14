package day1014;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1043 {
	static int N, M;
	static int parent[];
	static boolean[][] partys;

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		parent = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			parent[i] = i;
		}
		boolean[] musttrue = new boolean[N + 1];
		tokens = new StringTokenizer(read.readLine());
		int truepeople = Integer.parseInt(tokens.nextToken());
		if (truepeople > 0) {
			int p = Integer.parseInt(tokens.nextToken());
			musttrue[p] = true;
			for (int i = 1; i < truepeople; i++) {
				int k = Integer.parseInt(tokens.nextToken());
				musttrue[k] = true;
				if (!isSame(p, k)) {
					union(p, k);
				}
			}

		}
		partys = new boolean[M][N + 1];
		for (int party = 0; party < M; party++) {
			tokens = new StringTokenizer(read.readLine());
			int participate = Integer.parseInt(tokens.nextToken());
			if (participate > 0) {
				int firstp = Integer.parseInt(tokens.nextToken());
				partys[party][firstp] = true;
				for (int pct = 1; pct < participate; pct++) {
					int nextp = Integer.parseInt(tokens.nextToken());
					partys[party][nextp] = true;
					if (!isSame(firstp, nextp)) {
						union(firstp, nextp);
					}
				}

			}
		}
		
		
		for (int i = 1; i <= N; i++) {
			if (musttrue[parent[i]] || musttrue[i]) {
				musttrue[i] = musttrue[parent[i]] = true;
			}
		}
		for (int i = 1; i <= N; i++) {
			if (musttrue[parent[i]] || musttrue[i]) {
				musttrue[i] = musttrue[parent[i]] = true;
			}
		}
		int count = M;
		for (int party = 0; party < M; party++) {
			for (int person = 1; person <= N; person++) {
				if (partys[party][person] && musttrue[person]) {
					count--;
//					System.out.println("거짓 불가능 : " + (party + 1));
					break;
				}
			}
		}
//		System.out.println(Arrays.toString(musttrue));
//		System.out.println(parent[1]);
//		System.out.println(parent[2]);
//		System.out.println(parent[3]);
//		System.out.println();
		;
//		for (boolean[] p : partys) {
//			System.out.println(Arrays.toString(p));
//		}
		System.out.println(count);
	}

	private static void union(int x, int y) {
		int px = find(x);
		int py = find(y);
		if (px < py) {
			parent[py] = px;
		} else {
			parent[px] = py;
		}
	}

	private static int find(int x) {
		if (parent[x] == x)
			return x;
		return find(parent[x]);
	}

	private static boolean isSame(int x, int y) {
		if (find(x) == find(y))
			return true;
		return false;
	}

}
