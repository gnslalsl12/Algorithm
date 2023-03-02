package day1009;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CodeForce_1722_B {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(read.readLine());
		for(int test = 0; test < T; test++) {
			int N = Integer.parseInt(read.readLine());
			String A = read.readLine();
			String B = read.readLine();
			boolean result = true;
			for(int i = 0; i < N; i++) {
				char a = A.charAt(i);
				char b = B.charAt(i);
				if(a == 'R' || b == 'R') {
					if(a != 'R' || b != 'R') {
						result = false;
						break;
					}
				}
			}
			if(result) System.out.println("YES");
			else System.out.println("NO");
		}
	}

}
