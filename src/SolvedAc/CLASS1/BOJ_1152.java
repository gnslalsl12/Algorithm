package SolvedAc.CLASS1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_1152 {

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		int count = 0;
		while (tokens.hasMoreTokens()) {
			tokens.nextToken();
			count++;
		}
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		write.write(count + "\n");
		write.close();
	}
}