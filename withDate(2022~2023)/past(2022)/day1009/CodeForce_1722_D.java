package day1009;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CodeForce_1722_D {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(read.readLine());
		for (int test = 0; test < T; test++) {
			StringBuilder sb = new StringBuilder();
			int N = Integer.parseInt(read.readLine());
			String[] templine = read.readLine().split("");
			long tempresult = 0;
			for (int i = 0; i < N; i++) {
				if (templine[i].equals("L")) {
					tempresult += i;
				} else {
					tempresult += N - 1 - i;
				}
			}
			int index = 0;
			boolean done = false;
			for (int j = 0; j < N; j++) {
				if (!done) {

					for (int i = index; i < N / 2; i++) {
						if(index == N-1){
							done = true;
							break;
						}
						if (templine[i].equals("L")) {
							templine[i] = "R";
							if (i < N / 2) {
								tempresult -= i;
								tempresult += N - 1 - i;
							} else {
								tempresult -= N - 1 - i;
								tempresult += i;
							}
							break;
						} else if (templine[N - 1 - i].equals("R")) {
							templine[N - 1 - i] = "L";
							if (i < N / 2) {
								tempresult -= i;
								tempresult += N - 1 - i;
							} else {
								tempresult -= N - 1 - i;
								tempresult += i;
							}
							index = i + 1;
							break;
						}
					}
				}
				sb.append(tempresult);
				if (j < N - 1) {
					sb.append(" ");
				}
			}
			System.out.println(sb);
		}
	}

}
