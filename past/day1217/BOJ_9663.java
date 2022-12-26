package day1217;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_9663 {
	static int N;
	static long result;
	static boolean[] lineX, lineY, lineXY, lineYX;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(read.readLine());
		lineXY = new boolean[N * 2 + 10];
		lineYX = new boolean[N * 2 + 10];
		lineY = new boolean[N];
		lineX = new boolean[N];
		comb(0, new int[N], 0);
		System.out.println(result);
	}

	private static void comb(int start, int[] sel, int count) {
		if (count == N) {
			result++;
			return;
		}
		for (int i = start; i < N * N; i++) {
			if (count != getX(i))
				return;
			if (count != 0) {
				if (lineX[getX(i)] || lineY[getY(i)] || lineXY[getX(i) - getY(i) + N] || lineYX[getX(i) + getY(i)])
					continue;
			}
			sel[count] = i;
			lineX[getX(i)] = true;
			lineY[getY(i)] = true;
			lineXY[getX(i) - getY(i) + N] = true;
			lineYX[getX(i) + getY(i)] = true;
			comb(i + (N - getY(i)), sel, count + 1);
			lineX[getX(i)] = false;
			lineY[getY(i)] = false;
			lineXY[getX(i) - getY(i) + N] = false;
			lineYX[getX(i) + getY(i)] = false;
		}
	}

	private static int getX(int num) {
		return num / N;
	}

	private static int getY(int num) {
		return num % N;
	}
}