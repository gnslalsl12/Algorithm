package day0823_temp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_10103 {
	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		int T = Integer.parseInt(tokens.nextToken());
		int Asum = 100;
		int Bsum = 100;
		for(int i = 0; i < T; i++) {
			tokens = new StringTokenizer(read.readLine());
			int a = Integer.parseInt(tokens.nextToken());
			int b = Integer.parseInt(tokens.nextToken());
			if(a > b) {
				Bsum -= a;
			}else if(a < b) {
				Asum -= b;
			}
		}
		System.out.println(Asum);
		System.out.println(Bsum);
	}
}
