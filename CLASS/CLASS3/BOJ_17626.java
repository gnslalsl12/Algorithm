package SolvedAc.CLASS3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_17626 {
	static int N;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(read.readLine());
		int tempN = (int) Math.sqrt(N);
		int tempcount = 5;
		int tempresult = 0;
		for (int i = tempN; i >= 1; i--) {
			tempresult = i * i;
			if (tempresult > N)
				continue;
			else if (tempresult == N) {
				tempcount = 1;
				break;
			}

			for (int j = i; j >= 1; j--) {
				tempresult += j * j;
				if (tempresult > N) {
					tempresult -= j * j;
					continue;
				} else if (tempresult == N) {
					tempcount = Math.min(tempcount, 2);
					tempresult -= j * j;
					break;
				}

				for (int k = j; k >= 1; k--) {
					tempresult += k * k;
					if (tempresult > N) {
						tempresult -= k * k;
						continue;
					} else if (tempresult == N) {
						tempcount = Math.min(tempcount, 3);
						tempresult -= k * k;
						break;
					}

					for (int l = k; l >= 1; l--) {
						tempresult += l * l;
						if (tempresult > N) {
							tempresult -= l * l;
							continue;
						} else if (tempresult == N) {
							tempresult -= l * l;
							tempcount = Math.min(tempcount, 4);
							break;
						}
						tempresult -= l * l;
					}
					tempresult -= k * k;
				}
				tempresult -= j * j;
			}
		}
		System.out.println(tempcount);
	}

}
