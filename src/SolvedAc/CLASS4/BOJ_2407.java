package SolvedAc.CLASS4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class BOJ_2407 {

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		int n = Integer.parseInt(tokens.nextToken());
		int m = Integer.parseInt(tokens.nextToken());
		BigInteger result = new BigInteger("1");
		for (int i = 1; i <= m; i++) {
			result = result.multiply(new BigInteger(String.valueOf(n - i + 1)));
			result = result.divide(new BigInteger(String.valueOf(i)));
		}
		System.out.println(result);
	}
}
