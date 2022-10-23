package SolvedAc.CLASS2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1929 {

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		int M = Integer.parseInt(tokens.nextToken());
		int N = Integer.parseInt(tokens.nextToken());
		StringBuilder sb = new StringBuilder();
		for (int i = M; i <= N; i++) {
			if (check(i))
				sb.append(i + "\n");
		}
		System.out.print(sb);
	}

	private static boolean check(int temp) {
		if(temp == 2) return true;
		if (temp % 2 == 0)
			return false;
		if (Math.sqrt(temp) % 1 == 0)
			return false;
		for (int i = 3; i <= (int) Math.sqrt(temp) + 1; i += 2) {
			if (temp % i == 0)
				return false;
		}
		return true;
	}
}