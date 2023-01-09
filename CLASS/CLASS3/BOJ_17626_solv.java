package SolvedAc.CLASS3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_17626_solv {
	static int[] dpresult;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(read.readLine());

		dpresult = new int[N + 1];
		dpresult[1] = 1;
		dpresult[N] = solvdp(N);
		System.out.println(dpresult[N]);
	}

	private static int solvdp(int tempnum) {
		int tempmin = Integer.MAX_VALUE;
		int sqrt = (int) Math.sqrt(tempnum);
		for (int i = sqrt; i >= 1; i--) { // tempnum 이하의 제곱수들
			int tempdiff = tempnum - i*i;
			if (tempdiff == 0)
				return 1;
			if (dpresult[tempdiff] == 0) {
				dpresult[tempdiff] = solvdp(tempdiff);
			}
			tempmin = Math.min(dpresult[tempdiff], tempmin);
		}
		return tempmin + 1;

	}

}
