package SolvedAc.CLASS1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_10871 {

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		int N = Integer.parseInt(tokens.nextToken());
		int X = Integer.parseInt(tokens.nextToken());
		tokens = new StringTokenizer(read.readLine());
		for(int i = 0; i < N; i++) {
			int temp = Integer.parseInt(tokens.nextToken());
			if(X > temp) write.write(temp + " ");
		}
		write.write("\n");
		write.close();
	}
}