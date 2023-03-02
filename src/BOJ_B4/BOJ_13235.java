package BOJ_B4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ_13235 {

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		String input = read.readLine();
		if (solv(input))
			write.write("true\n");
		else
			write.write("false\n");
		write.close();
		read.close();
	}

	private static boolean solv(String input) {
		int len = input.length();
		int midlen = len / 2;
		for (int i = 0; i < midlen; i++) {
			if (input.charAt(i) != input.charAt(len - i - 1)) {
				return false;
			}
		}
		return true;
	}

}
