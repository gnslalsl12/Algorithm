package SolvedAc.CLASS2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ_2798 {
	static int N, M, mingap;
	static ArrayList<Integer> cards;
	static int result;

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		tokens = new StringTokenizer(read.readLine());
		result = 0;
		mingap = Integer.MAX_VALUE;
		cards = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			cards.add(Integer.parseInt(tokens.nextToken()));
		}
		comb(0, 0, new int[3]);
		write.write(result + "\n");
		write.close();
	}

	private static void comb(int start, int count, int[] sel) {
		if (count == 3) {
			setCard(sel);
			return;
		}
		for (int i = start; i < N; i++) {
			sel[count] = cards.get(i);
			comb(i + 1, count + 1, sel);
		}
	}

	private static void setCard(int[] sel) {
		int count = 0;
		for (int temp : sel) {
			count += temp;
		}
		if (count > M)
			return;
		if (mingap > Math.abs(M - count)) {
			mingap = M - count;
			result = count;
		}
		return;
	}
}