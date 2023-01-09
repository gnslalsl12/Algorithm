package SolvedAc.CLASS2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ__2869 {

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		long A = Long.parseLong(tokens.nextToken());
		long B = Long.parseLong(tokens.nextToken());
		long V = Long.parseLong(tokens.nextToken());
		long tempremain = V - A;
		long day = tempremain / (A - B);
		V -= day * (A - B);
		while (true) {
			V -= A;
			if (V <= 0) {
				day++;
				break;
			}
			V += B;
			day++;
		}
		System.out.println(day);
	}
}
