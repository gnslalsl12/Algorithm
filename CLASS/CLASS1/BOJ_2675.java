package SolvedAc.CLASS1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_2675 {

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		int N = Integer.parseInt(tokens.nextToken());
		for (int i = 0; i < N; i++) {
			tokens = new StringTokenizer(read.readLine());
			int R = Integer.parseInt(tokens.nextToken());
			String temp = tokens.nextToken();
			for (int j = 0; j < temp.length(); j++) {
				char tempchar = temp.charAt(j);
				for (int k = 0; k < R; k++) {
					write.write(tempchar);
				}
			}
			write.write("\n");
		}
		write.close();
	}
}