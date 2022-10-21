package SolvedAc.CLASS1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_10818 {

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		int N = Integer.parseInt(tokens.nextToken());
		tokens = new StringTokenizer(read.readLine());
		int max= Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		for(int i = 0; i < N; i++) {
			int A = Integer.parseInt(tokens.nextToken());
			if(max < A) max = A;
			if(min > A) min = A;
		}
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		write.write(min + " " + max + "\n");
		write.close();
	}
}