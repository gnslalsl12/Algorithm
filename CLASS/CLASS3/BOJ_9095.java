package SolvedAc.CLASS3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ_9095 {
	static int N;
	static int n1, n2, n3;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		N = Integer.parseInt(read.readLine());
		for (int i = 0; i < N; i++) {
			int count = 0;
			int temp = Integer.parseInt(read.readLine());
			n1 = temp;
			n2 = temp / 2;
			n3 = temp / 3;
			for (int a = 0; a <= n1; a++) {
				for (int b = 0; b <= n2; b++) {
					for (int c = 0; c <= n3; c++) {
						if (a + b * 2 + c * 3 == temp) {
							count += getPermCount(a, b, c);
						}
					}
				}
			}
			write.write(count + "\n");
		}
		read.close();
		write.close();
	}

	private static int getPermCount(int a, int b, int c) {
		int result = 1;
		int n = a + b + c;
		int div = 1;
		while (n > 0)
			result *= n--;
		while (a > 0)
			div *= a--;
		while (b > 0)
			div *= b--;
		while (c > 0)
			div *= c--;
		return result / div;
	}
}