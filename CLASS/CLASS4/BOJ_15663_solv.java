package SolvedAc.CLASS4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BOJ_15663_solv {
	static BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
	static int N, M, nums[], vis;
	static HashSet<String> Dictionary = new HashSet<>();

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		nums = new int[N];
		tokens = new StringTokenizer(read.readLine());
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(tokens.nextToken());
		}
		Arrays.sort(nums);
		perm(0, "");
		write.close();
	}

	private static void perm(int count, String temp) throws IOException {
		if (count == M) {
			if (Dictionary.contains(temp))
				return;
			Dictionary.add(temp);
			write.write(temp + "\n");
			return;
		}
		for (int i = 0; i < N; i++) {
			if ((vis & (1 << i)) != 0)
				continue;
			vis |= 1 << i;
			perm(count + 1, temp + nums[i] + " ");
			vis &= ~(1 << i);
		}

	}

}
