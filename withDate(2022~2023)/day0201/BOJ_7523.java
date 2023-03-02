package day0201;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_7523 {
	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		int T = Integer.parseInt(tokens.nextToken());
		for (int Scenario = 1; Scenario <= T; Scenario++) {
			tokens = new StringTokenizer(read.readLine());
			long N = Integer.parseInt(tokens.nextToken());
			long M = Integer.parseInt(tokens.nextToken());
			N = N * (N - 1L) / 2L;
			M = M * (M + 1L) / 2L;
			M -= N;
			write.write("Scenario #");
			write.write(Scenario + "");
			write.write(":\n");
			write.write(M + "");
			write.write("\n");
			if (Scenario < T)
				write.write("\n");
		}
		write.close();
		read.close();
	}
}
