package day1014;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1043 {
	static int N, M;
	static int[] parent;
	static boolean[][] participate;

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		participate = new boolean[M][N + 1];
		tokens = new StringTokenizer(read.readLine());
		parent = new int[N + 1];
		for (int i = 0; i <= N; i++) {
			parent[i] = i;
		}
		int trues = Integer.parseInt(tokens.nextToken());
		for (int i = 0; i < trues; i++) {
			int temptrue = Integer.parseInt(tokens.nextToken());
			union(0, temptrue);
		}	
		for (int party = 0; party < M; party++) {
			tokens = new StringTokenizer(read.readLine());
			int peope = Integer.parseInt(tokens.nextToken());
			boolean containtrue = false;
			for (int p = 0; p < peope; p++) {
				int person = Integer.parseInt(tokens.nextToken());
				participate[party][person] = true;
				if (find(person) == 0)
					containtrue = true;
			}
			if (containtrue) {
				for (int n = 1; n <= N; n++) {
					if (participate[party][n]) {
						union(n, 0);
					}
				}
			}
		} // parent가 smallesttrue면 진실 말해야하는 사람
		boolean[] party = new boolean [M];
		for(int test = 0; test < M; test++) {
			for (int revparty = M - 1; revparty >= 0; revparty--) {
				boolean availtrue = true;
				for (int p = 1; p <= N; p++) {
					if (participate[revparty][p] && find(p) == 0) {
						availtrue = false;
						for (int n = 1; n <= N; n++) { 
							if (participate[revparty][n]) {
								union(n, 0);
							}
						}
					}
				}
				if(availtrue) party[revparty] = true;
				else party[revparty] = false;
			}
		}
		int count = 0;
		for(int i = 0; i < M; i++) {
			if(party[i]) count++;
		}
		System.out.println(count);
	}

	private static void print() {
		System.out.println("////진실을 아는 사람////");
		for (int i = 1; i <= N; i++) {
			if (find(i) == 0)
				System.out.printf("%d  ", i);
			else
				System.out.printf("0  ");
		}
		System.out.println();
		System.out.println("////파티 정보////");
		for (int i = 0; i < M; i++) {
			for (int j = 1; j <= N; j++) {
				if (participate[i][j])
					System.out.printf("%d ", j);
				System.out.printf("%d ", 0);

			}
			System.out.println();
		}
		System.out.println("///////////////////////");

	}

	private static int find(int x) {
		if (parent[x] == x)
			return x;
		return parent[x] = find(parent[x]);
	}

	private static void union(int x, int y) {
		x = find(x);
		y = find(y);
		if (x < y)
			parent[y] = x;
		else
			parent[x] = y;
	}

	private static boolean isSame(int x, int y) {
		return find(x) == find(y);
	}
}