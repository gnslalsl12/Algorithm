package SolvedAc.CLASS2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_2609 {

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		int N = Integer.parseInt(tokens.nextToken());
		int M = Integer.parseInt(tokens.nextToken());
		int tempmax = N;
		int tempmin = M;
		while (true) {
			int tempx = tempmax;
			int tempn = tempmin;
			tempmax = Math.max(tempx, tempn);
			tempmin = Math.min(tempx, tempn);
			if (tempmax % tempmin == 0) {
				break;
			}
			tempmax %= tempmin;
		}
		tempmax = tempmin * (N / tempmin) * (M / tempmin);
		write.write(String.format("%d\n%d\n", tempmin, tempmax));
		write.close();
	}
}