package SolvedAc.CLASS1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_2908 {

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));

		String A = tokens.nextToken();
		String B = tokens.nextToken();
		for (int i = 2; i >= 0; i--) {
			if (A.charAt(i) > B.charAt(i)) {
				B = A;
				break;
			} else if (A.charAt(i) < B.charAt(i)) {
				break;
			}
		}
		for (int i = 2; i >= 0; i--) {
			write.write(B.charAt(i));
		}
		write.write("\n");
		write.close();
	}
}