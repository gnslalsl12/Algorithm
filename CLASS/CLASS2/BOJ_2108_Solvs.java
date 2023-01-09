package SolvedAc.CLASS2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ_2108_Solvs {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(read.readLine());
		int[] count = new int[8001];
		int result1 = 0; // 합계
		int result2 = -4001; // 중간값
		int result3 = 0; // 최빈값
		int result4 = 0; // 최대값 최소값 차이
		int max = -4001;
		int min = 4001;
		double sum = 0;
		for (int i = 0; i < N; i++) {
			int temp = Integer.parseInt(read.readLine());
			sum += temp;
			count[temp + 4000]++;
			max = Math.max(temp, max);
			min = Math.min(temp, min);
		}
		result1 = (int) Math.round(sum / N);
		result4 = max - min;
		int midcount = 0;
		int maxcount = 0;
		boolean second = false;
		for (int i = min + 4000; i <= max + 4000; i++) {
			if (count[i] == 0)
				continue;
			midcount += count[i];
			if (midcount >= (N / 2) + 1 && result2 == -4001) {
				result2 = i - 4000;
			}
			if (maxcount < count[i]) {
				maxcount = count[i];
				second = false;
				result3 = i - 4000;
			} else if (maxcount == count[i] && !second) {
				second = true;
				result3 = i - 4000;
			}
		}
		write.write(String.format("%d\n%d\n%d\n%d\n", result1, result2, result3, result4));
		write.close();
	}
}