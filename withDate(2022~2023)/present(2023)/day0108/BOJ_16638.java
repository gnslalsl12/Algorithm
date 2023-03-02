package day0108;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ_16638 {
	static int N;
	static long result;
	static String[] InputArr;

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		N = Integer.parseInt(read.readLine());
		result = Long.MIN_VALUE;
		InputArr = read.readLine().split("");
		setBracket(new boolean[N], 1);
		write.write(result + "\n");
		write.close();
		read.close();
	}

	private static void setBracket(boolean[] sel, int count) { // 연산자 기준으로 먼저 계산할(괄호에 들어갈) 연산자 지정
		if (count >= N) {
			getCalcAll(sel);
			return;
		}

		if (count == 1) {
			setBracket(sel, count + 2);
			sel[count] = true;
			setBracket(sel, count + 2);
		} else {
			if (!sel[count - 2]) {
				sel[count] = true;
				setBracket(sel, count + 2);
			}
			sel[count] = false;
			setBracket(sel, count + 2);
		}

	}

	private static void getCalcAll(boolean[] sel) {
		String[] temp = InputArr.clone();
		getBracketCalc(temp, sel);
		getMultCalc(temp);
		getRemainPorM(temp);
	}

	private static String[] getBracketCalc(String[] input, boolean[] sel) {
		for (int i = 0; i < N; i++) {
			if (sel[i]) {
				int temp;
				if (input[i].equals("*"))
					temp = Integer.parseInt(input[i - 1]) * Integer.parseInt(input[i + 1]);
				else if (input[i].equals("+"))
					temp = Integer.parseInt(input[i - 1]) + Integer.parseInt(input[i + 1]);
				else
					temp = Integer.parseInt(input[i - 1]) - Integer.parseInt(input[i + 1]);
				input[i - 1] = Integer.toString(temp);
				input[i] = input[i + 1] = null;
			}
		}
		return input;
	}

	private static String[] getMultCalc(String[] input) {
		for (int i = 0; i < N; i++) {
			if (input[i] == null)
				continue;
			if (input[i].equals("*")) {
				int j = i;
				input[i] = null;
				while (input[j] == null)
					j--;
				int temp = Integer.parseInt(input[j]);
				while (input[i] == null)
					i++;
				temp *= Integer.parseInt(input[i]);
				input[i] = null;
				input[j] = Integer.toString(temp);
			}
		}
		return input;
	}

	private static void getRemainPorM(String[] input) {
		long tempresult = Integer.parseInt(input[0]);
		for (int i = 1; i < N; i++) {
			if (input[i] == null)
				continue;
			if (input[i].equals("+") || input[i].equals("-")) {
				boolean plus = true;
				if (input[i].equals("-"))
					plus = false;
				input[i] = null;
				while (input[i] == null)
					i++;
				if (plus)
					tempresult += Integer.parseInt(input[i]);
				else
					tempresult -= Integer.parseInt(input[i]);
			}
		}
		result = Long.max(result, tempresult);
	}

}
