package SolvedAc.CLASS3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ_9461 {
	static long[] tra;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(read.readLine());
		tra = new long[101];
		tra[1] = tra[2] = tra[3] = 1;
		for (int test = 1; test <= T; test++) {
			int temp = Integer.parseInt(read.readLine());
			if (temp <= 3) {
				write.write("1\n");
				continue;
			}
			if (tra[temp] == 0) {
				get(temp);
			}
			write.write(tra[temp] + "\n");
		}
		write.close();
	}

	private static void get(int temp) {
		for (int i = 3; i <= temp; i++) {
			if (tra[i] != 0) {
				continue;
			}
			tra[i] = tra[i - 2] + tra[i - 3];
		}
	}
}
