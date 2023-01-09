package SolvedAc.CLASS4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ_15657 {
	static int N, M;
	static ArrayList<Integer> nums = new ArrayList<>();
	static BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		tokens = new StringTokenizer(read.readLine());
		for (int i = 0; i < N; i++) {
			nums.add(Integer.parseInt(tokens.nextToken()));
		}
		Collections.sort(nums);
		Comb(0, 0, new int[M]);
		write.close();
	}

	private static void Comb(int count, int start, int[] sel) throws IOException {
		if (count == M) {
			for (int i = 0; i < M; i++) {
				write.write(sel[i] + " ");
			}
			write.write("\n");
			return;
		}
		for (int i = start; i < N; i++) {
			sel[count] = nums.get(i);
			Comb(count + 1, i, sel);
		}
	}
}
