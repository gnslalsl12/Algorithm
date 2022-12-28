package day1227;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_9466 {
	static int N;
	static ArrayList<student> list;
	static boolean[] vis;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(read.readLine());
		for (int test = 1; test <= T; test++) {
			list = new ArrayList<>();
			list.add(new student(0, -1));
			N = Integer.parseInt(read.readLine());
			vis = new boolean[N + 1];
			StringTokenizer tokens = new StringTokenizer(read.readLine());
			for (int i = 1; i <= N; i++) {
				list.add(new student(i, Integer.parseInt(tokens.nextToken())));
			}

		}

	}

	private static int findLikeDFS(int start) {
		
	}

	private static class student {
		int num;
		int like;

		public student(int num, int like) {
			this.num = num;
			this.like = like;
		}

	}

}
