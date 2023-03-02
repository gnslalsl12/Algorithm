package SWEA;

import java.io.*;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class SWEA_문자열교집합 {
	static int N, M;
	static TreeSet<String> Book;

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(read.readLine());
		for (int test = 1; test <= T; test++) {
			StringTokenizer tokens = new StringTokenizer(read.readLine());
			N = Integer.parseInt(tokens.nextToken());
			M = Integer.parseInt(tokens.nextToken());
			int result = 0;
			Book = new TreeSet<>();
			tokens = new StringTokenizer(read.readLine());
			while (N-- > 0) {
				Book.add(tokens.nextToken());
			}
			tokens = new StringTokenizer(read.readLine());
			while (M-- > 0) {
				if (Book.contains(tokens.nextToken()))
					result++;
			}
			write.write("#" + test + " " + result + "\n");
		}
		write.close();
		read.close();
	}
}
