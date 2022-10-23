package SolvedAc.CLASS2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_10816 {

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
//		StringBuilder sb = new StringBuilder();
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(tokens.nextToken());
		tokens = new StringTokenizer(read.readLine());
		int plus = 10000000;
		boolean[] check = new boolean[20000003];
		int[] count = new int[20000003];
		for (int i = 0; i < N; i++) {
			int temp = Integer.parseInt(tokens.nextToken());
			check[temp + plus] = true;
			count[temp + plus]++;
		}
		tokens = new StringTokenizer(read.readLine());
		int M = Integer.parseInt(tokens.nextToken());
		tokens = new StringTokenizer(read.readLine());
		for (int i = 0; i < M; i++) {
			int tempcheck = Integer.parseInt(tokens.nextToken());
			if(check[tempcheck + plus]) write.write(count[tempcheck + plus] + " ");
			else write.write("0 ");
		}
//		System.out.print(sb);
		write.write("\n");
		write.close();
	}
}