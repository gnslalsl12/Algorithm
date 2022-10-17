package day1016;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_3067 {
	static int N;
	static ArrayList<Integer> Coins;
	static int[] result;
	static int M;

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb= new StringBuilder();
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		int T = Integer.parseInt(tokens.nextToken());
		for (int test = 0; test < T; test++) {
			tokens = new StringTokenizer(read.readLine());
			N = Integer.parseInt(tokens.nextToken());
			tokens = new StringTokenizer(read.readLine());
			Coins = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				Coins.add(Integer.parseInt(tokens.nextToken()));
			}
			tokens = new StringTokenizer(read.readLine());
			M = Integer.parseInt(tokens.nextToken());
			result = new int[M + 1];
			get();
			sb.append(result[M]).append("\n");
		}
		System.out.print(sb);
	}

	private static void get() {
		result [0] = 1;
		for(int i = 0; i < N; i++) {
			int coin = Coins.get(i);
			for(int value = coin; value <= M; value++) {
				result[value] += result[value - coin];
			}
		}
	}
}