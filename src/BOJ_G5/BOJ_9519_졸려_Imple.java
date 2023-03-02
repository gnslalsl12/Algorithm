package BOJ_G5;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ_9519_Á¹·Á_Imple {
	static int X, Len;
	static String input;
	static int[] ReturnIndex;

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		X = Integer.parseInt(read.readLine());
		input = read.readLine();
		solv();

	}

	private static void solv() {
		Len = input.length();
		ReturnIndex = new int[Len / 2];
		for (int i = 0; i < Len / 2; i++) {
			ReturnIndex[i] = i * 2 + 1;
		}
		while (X-- > 0) {
			getReturn();
		}
	}

	private static void getReturn() {

	}

}
