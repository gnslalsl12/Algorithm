package day0126;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_25797 {
	static int N, K;
	static int[] maps = new int[10];
	static ArrayList<dirXY> NumList = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		for (int i = 0; i < N; i++) {
			tokens = new StringTokenizer(read.readLine());
			for (int j = 0; j < N; j++) {
				if (tokens.nextToken().equals("0"))
					maps[i] |= 1 << j;
			}
		}
		K = Integer.parseInt(tokens.nextToken());
		for (int i = 0; i < K; i++) {
			for (int j = 0; j < K; j++) {
				int x = Integer.parseInt(tokens.nextToken()) - 1;
				int y = Integer.parseInt(tokens.nextToken()) - 1;
				int k = Integer.parseInt(tokens.nextToken());
				NumList.add(new dirXY(x, y, k));
			}
		}
	}

	private static class dirXY {
		int x;
		int y;
		int k;

		public dirXY(int x, int y, int k) {
			super();
			this.x = x;
			this.y = y;
			this.k = k;
		}

	}

}
