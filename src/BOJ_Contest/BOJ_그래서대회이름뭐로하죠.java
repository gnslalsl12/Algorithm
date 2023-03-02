package BOJ_Contest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_그래서대회이름뭐로하죠 {
	static int N, M;
	static String input;

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		input = read.readLine();
		if (solv()) {
			write.write("YES\n" + input + "\n");
		} else {
			write.write("NO\n");
		}
		write.close();
		read.close();
	}

	private static boolean solv() {
		return getCut();
	}

	private static boolean getCut() {
		int len = input.length() - 1;
		char LastCons = '#';
		boolean FirstA = false;
		boolean SecondA = false;
		for (; len >= 0; len--) {
			char temp = input.charAt(len);
			if (LastCons == '#') {
				if (temp != 'A' && temp != 'E' && temp != 'I' && temp != 'O' && temp != 'U') {
					LastCons = temp;
					continue;
				}
				continue;
			} else if (!FirstA) {
				if (temp == 'A') {
					FirstA = true;
					continue;
				}
			} else if (!SecondA) {
				if (temp == 'A') {
					break;
				}
			}
		}
		if (len >= M - 3) {
			input = input.substring(0, M - 3);
			input += "AA" + LastCons;
			return true;
		} else {
			return false;
		}
	}

}
