package SolvedAc.CLASS1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_1546 {

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		int N = Integer.parseInt(tokens.nextToken());
		double[] arrs = new double[N];
		double max = 0;
		tokens = new StringTokenizer(read.readLine());
		for (int i = 0; i < N; i++) {
			arrs[i] = Integer.parseInt(tokens.nextToken());
			if (max < arrs[i]) {
				max = arrs[i];
			}
		}
		double result = 0;
		for (int i = 0; i < N; i++) {
			result += (arrs[i] / max) * 100;
		}
		write.write(result / (long) N + "\n");
		write.close();
	}
}