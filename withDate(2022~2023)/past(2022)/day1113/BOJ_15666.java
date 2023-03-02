package day1113;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BOJ_15666 {
	static int N, M, nums[];
	static HashSet<String> Dictionary = new HashSet<>();
	static BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		tokens = new StringTokenizer(read.readLine());
		nums = new int[N];
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(tokens.nextToken());
		}
		Arrays.sort(nums);
		Comb(0, 0, "");
		write.close();
	}

	private static void Comb(int count, int start, String temp) throws IOException {
		if (count == M) {
			if (Dictionary.contains(temp))
				return;
			Dictionary.add(temp);
			write.write(temp + "\n");
			return;
		}

		for (int i = start; i < N; i++) {
			Comb(count + 1, i, temp + nums[i] + " ");
		}
	}

}
