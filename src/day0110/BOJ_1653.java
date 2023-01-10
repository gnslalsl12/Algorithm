package day0110;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_1653 {
	static int N;
	static int[] weights;
	static int[] mult = { 5, 4, 3, 2, 1, 0, 1, 2, 3, 4, 5 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		N = Integer.parseInt(read.readLine());
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		weights = new int[N];
		for (int i = 0; i < N; i++) {
			weights[i] = Integer.parseInt(tokens.nextToken());
		}
		int k = Integer.parseInt(read.readLine());

	}

}
