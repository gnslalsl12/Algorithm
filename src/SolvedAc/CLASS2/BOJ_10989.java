package SolvedAc.CLASS2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ_10989 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(read.readLine());
		int[] count = new int[10000001];
		int max = -1;
		int min = 20000000;
		for (int i = 0; i < N; i++) {
			int temp = Integer.parseInt(read.readLine());
			max = Math.max(max, temp);
			min = Math.min(min, temp);
			count[temp]++;
		}
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		for (int i = min; i <= max; i++) {
			if (count[i] != 0) {
				for (int j = 0; j < count[i]; j++) {
					write.write(i + "\n");
				}
			}
		}
		write.close();
	}
}