package day1005;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_8111 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(read.readLine());
		for (int test = 0; test < T; test++) {
			int N = Integer.parseInt(read.readLine());
			long tempN = 0;
			boolean found = true;
			breakall: while (true) {
				tempN += N;
				found = true;
				if (tempN == 1 || tempN == 11 || tempN == 10)
					break;
				for (int i = 0; i < Long.toString(tempN).length(); i++) {
					String sN = Long.toString(tempN);
					if (sN.length() > 100) {
						found = false;
						break breakall;
					}
					if (sN.charAt(i) == '0' || sN.charAt(i) == '1') {
						continue;
					} else {
						found = false;
						break;
					}
				}
				if (found)
					break;

			}
			if(!found) {
				System.out.println("BRAK");
				continue;
			}
			System.out.println(tempN);

		}
	}

}
