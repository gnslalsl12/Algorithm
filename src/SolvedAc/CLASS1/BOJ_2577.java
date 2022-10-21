package SolvedAc.CLASS1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_2577 {

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer tokens;
		int[] arrs = new int[10];
		int tempresult = 1;
		for (int i = 0; i < 3; i++) {
			tokens = new StringTokenizer(read.readLine());
			tempresult *= Integer.parseInt(tokens.nextToken());
		}
		String result = Integer.toString(tempresult);
		for (int i = 0; i < result.length(); i++) {
			arrs[(int) (result.charAt(i)) - 48]++;
		}
		for (int i = 0; i < arrs.length; i++) {
			write.write(arrs[i] + "\n");
		}
		write.close();
	}
}