package day0130;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Prog_¾ç±Ã´ëÈ¸ {
	static int[] staticinfo, Rbag, result;
	static int staticN;
	static int statictotal;
	static int staticP;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter write = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(read.readLine());
		int[] info = new int[11];
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		for (int i = 0; i <= 10; i++) {
			info[i] = Integer.parseInt(tokens.nextToken());
		}
		System.out.println(Arrays.toString(solution(N, info)));
	}

	private static int[] solution(int N, int[] info) {
		staticinfo = info;
		staticN = N;
		statictotal = -1;
		staticP = 0;
		result = new int[11];
		for (int i = 0; i <= 10; i++) {
			if (info[i] > 0) {
				if (info[i] == N)
					return new int[] { -1 };
				staticP += 10 - i;
			}
		}
		Rbag = getRbag(info);
		subSet(0, new boolean[11]);
		return result;
	}

	private static void subSet(int count, boolean[] sel) {
		if (count == 11) {
			int tempcount = 0, temptotal = 0;
			int tempP = staticP;
			for (int i = 0; i < 10; i++) {
				if (sel[i]) {
					tempcount += Rbag[i];
					if (tempcount > staticN)
						return;
					temptotal += 10 - i;
					if (staticinfo[i] > 0)
						tempP -= (10 - i);
				}
			}
			int dep = temptotal - tempP;
			if (statictotal <= dep) {
				statictotal = dep;
//				for (int less = 10; less >= 0; less--) {
//					if (sel[less]) {
//						break;
//					}
//					if (result[less] != 0) {
//						return;
//					}
//				}
				result = new int[11];
				for (int i = 0; i < 10; i++) {
					if (sel[i])
						result[i] = Rbag[i];
				}
				result[10] = staticN - tempcount;
			}
			return;
		}
		sel[count] = true;
		subSet(count + 1, sel);
		sel[count] = false;
		subSet(count + 1, sel);
	}

	private static int[] getRbag(int[] info) {
		int[] Rbag = new int[11];
		for (int i = 0; i < 10; i++) {
			Rbag[i] = info[i] + 1;
		}
		Rbag[10] = info[10] - 1;
		return Rbag;
	}

}
