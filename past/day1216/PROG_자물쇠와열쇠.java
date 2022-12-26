package day1216;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class PROG_자물쇠와열쇠 { // https://school.programmers.co.kr/learn/courses/30/lessons/60059?language=java

	public static void main(String[] args) throws IOException {
		int[][] key = { { 0, 0, 0 }, { 1, 0, 0 }, { 0, 1, 1 } };
		int[][] lock = { { 1, 1, 1 }, { 1, 1, 0 }, { 1, 0, 1 } };
		boolean result = solution(key, lock);
		System.out.println(result);
	}

	public static boolean solution(int[][] key, int[][] lock) {
		boolean answer = false;
		int M = key[0].length;
		int N = lock[0].length;
		ArrayList<dirXY> keyset1 = new ArrayList<>();
		ArrayList<dirXY> keyset2 = new ArrayList<>();
		ArrayList<dirXY> keyset3 = new ArrayList<>();
		ArrayList<dirXY> keyset4 = new ArrayList<>();
		dirXY keystand = new dirXY(-1, -1);
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < M; j++) {
				if (key[i][j] == 1) {
					if (keystand.x == -1) {
						keystand = new dirXY(i, j);
						keyset1.add(keystand);
						keyset2.add(keystand);
						keyset3.add(keystand);
						keyset4.add(keystand);
					} else {
						int nextx = i - keystand.x;
						int nexty = j - keystand.y;
						keyset1.add(new dirXY(nextx, nexty));
						keyset2.add(new dirXY(nexty, -1 * nextx));
						keyset3.add(new dirXY(-1 * nextx, -1 * nexty));
						keyset4.add(new dirXY(-1 * nexty, nextx));
					}
				}
			}
		}//keyset 완성

		return answer;
	}

	public static boolean isIn(int x, int y, int N) {
		return x >= 0 && y >= 0 && x < N && y < N;
	}

	public static class dirXY {
		int x;
		int y;

		public dirXY(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}

}
