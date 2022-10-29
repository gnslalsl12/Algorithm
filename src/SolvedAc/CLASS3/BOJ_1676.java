package SolvedAc.CLASS3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ_1676 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(read.readLine());
		long result = 1;
		int count = 0;
		while (T > 1) {
			result *= T--;
			while (result % 10 == 0) {
				count++;
				result /= 10;
			}
			String templine = Long.toString(result);
			for (int i = templine.length() - 1; i > 0; i--) {
				if (templine.charAt(i) == '0') {
					result %= (long) Math.pow(10, templine.length() - i);
					break;
				}
			}
		}
		write.write(count + "\n");
		read.close();
		write.close();
	}
}
