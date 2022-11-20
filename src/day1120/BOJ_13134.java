package day1120;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_13134 {
	static int N;
	static int totalcase = (int) Math.pow(3, 9);
	static int[] stcase = new int[totalcase + 1];
	static int TempSurvived;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(read.readLine());
		StringTokenizer tokens;
		for (int i = 0; i < N; i++) {
			tokens = new StringTokenizer(read.readLine());
			int templine = 0;
			for (int j = 0; j < 9; j++) {
				int temp = Integer.parseInt(tokens.nextToken()) - 1;
				templine = templine * 3 + temp;
			}
			stcase[templine]++;
		}
		int min = N + 1;
		int max = 0;
		for (int Tcase = 0; Tcase < totalcase; Tcase++) {
			TempSurvived = 0;
			subsetAvoidTseat(0, 0, Tcase);
			min = Math.min(TempSurvived, min);
			max = Math.max(TempSurvived, max);
		}
		System.out.println(min + " " + max);
	}

	private static void subsetAvoidTseat(int count, int meetcase, int Tcase) {
		if (count == 9) {
			TempSurvived += stcase[meetcase];
			return;
		}
		int tempT = Tcase % 3;
		Tcase /= 3;
		if (tempT != 0)
			subsetAvoidTseat(count + 1, meetcase * 3 + 0, Tcase);
		if (tempT != 1)
			subsetAvoidTseat(count + 1, meetcase * 3 + 1, Tcase);
		if (tempT != 2)
			subsetAvoidTseat(count + 1, meetcase * 3 + 2, Tcase);
	}

}
