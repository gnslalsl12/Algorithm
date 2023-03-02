package BOJ_Contest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ_소수가아닌수 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(read.readLine());
		write.write(solv(N) + "\n");
		write.close();
		read.close();

	}

	private static int solv(int input) {
		while (input <= 1000000000) {
			if (getNum(input))
				break;
			input++;
		}
		return input;
	}

	private static boolean getNum(int input) {
		int tempN = (int) Math.sqrt(input);
		for (int i = 2; i <= tempN; i++) {
			if (input % i == 0)
				return true;
		}
		return false;
	}

}
