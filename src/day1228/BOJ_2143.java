package day1228;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_2143 {
	static int T, N, M;
	static int[] A, B;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		T = Integer.parseInt(read.readLine());
		N = Integer.parseInt(read.readLine());
		A = new int[N];
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(tokens.nextToken());
		}
		M = Integer.parseInt(read.readLine());
		B = new int[M];
		tokens = new StringTokenizer(read.readLine());
		for (int i = 0; i < N; i++) {
			B[i] = Integer.parseInt(tokens.nextToken());
		}

	}

}
