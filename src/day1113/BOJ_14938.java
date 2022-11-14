package day1113;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_14938 {
	static int N, M, R;
	static int[] ItemValue;
	static int[][] maps;
	static int max = Integer.MIN_VALUE;

	public static void main(String[] args) throws IOException { // FWS
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		R = Integer.parseInt(tokens.nextToken());
		maps = new int[N + 1][N + 1];
		ItemValue = new int[N + 1];
		tokens = new StringTokenizer(read.readLine());
		for (int i = 1; i <= N; i++) {
			ItemValue[i] = Integer.parseInt(tokens.nextToken());
		}
		for (int i = 0; i < R; i++) {
			tokens = new StringTokenizer(read.readLine());
			int a = Integer.parseInt(tokens.nextToken());
			int b = Integer.parseInt(tokens.nextToken());
			int roadval = Integer.parseInt(tokens.nextToken());
			maps[a][b] = maps[b][a] = roadval;
		}
//		for (int[] t : maps) {
//			System.out.println(Arrays.toString(t));
//		}
		for (int i = 1; i <= N; i++) {
			floydWashall(i);
		}
		System.out.println(max);
	}

	private static void print() {
		for (int[] m : maps) {
			System.out.println(Arrays.toString(m));
		}
	}

	private static void floydWashall(int startpoint) {
		int totalcount = ItemValue[startpoint];
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (maps[startpoint][i] != 0 && maps[i][j] != 0 && j != startpoint) { // 연결됨
					if (maps[startpoint][j] != 0) {
						maps[startpoint][j] = Math.min(maps[startpoint][j], maps[startpoint][i] + maps[i][j]);
					} else {
						maps[startpoint][j] = maps[startpoint][i] + maps[i][j];
					}
				}

			}
		}
//		System.out.println();
//		System.out.println("시작점 : " + startpoint);
		for (int i = 1; i <= N; i++) {
			if (maps[startpoint][i] <= M && maps[startpoint][i] != 0) {
//				System.out.println("가능한 곳 : " + i + " , 거리 : " + maps[startpoint][i] + " , 아이템 : " + ItemValue[i]);
				totalcount += ItemValue[i];
//			} else {
//				System.out.println("안되는 곳 : " + i + " ==> 거리 : " + maps[startpoint][i]);
			}
		}
//		System.out.println("total " + totalcount);
		max = Math.max(max, totalcount);
	}
}
