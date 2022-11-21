package CodePractice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class umjii {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder output = new StringBuilder();

	static int T;
	static int D, W, K;
	static final int A = 0;
	static final int B = 1;
	static int[][] Map;
	static int[][] mapCopy;
	static boolean isEnd;
	static int result;

	public static boolean isRight() {
		for (int w = 0; w < W; w++) {
			int count = 1;
			for (int d = 1; d < D; d++) {
				if (Map[d][w] == Map[d - 1][w]) {
					count++;
					if (count == K) { // 이 세로줄은 기준에 부합함!
						break;
					}
				} else {
					count = 1;
				}
				if (d == D - 1)
					return false;
			}
			// k기준에 맞지 않는 세로방향이 있음
		}
		return true;
	}

	public static void combi(int nth, int[] choosedIdx, int[] choosedDrug, int start, int a, int b) {
		if (isEnd) {
			return;
		}
		if (a + b >= K)
			return;
		if (nth == D) {
			// choosed에 맞춰 map 보호필름 바꾸고
			for (int d = 0, i = 0; d < D; d++) {
				if (i < choosedIdx.length && choosedIdx[i] == d && choosedDrug[i] != -1) {
					Arrays.fill(Map[d], choosedDrug[i]);
					i++;
				} else {
					Map[d] = mapCopy[d].clone();
				}
			}

			// 모든 세로방향이 k기준에 맞는지 확인하고
			if (isRight()) {
				result = a + b;
				isEnd = true; // 기준에 맞음! 끝!
			}

			return;
		}


			choosedDrug[nth] = -1;
			combi(nth + 1, choosedIdx, choosedDrug, i + 1, a, b);

			choosedDrug[nth] = A;
			combi(nth + 1, choosedIdx, choosedDrug, i + 1, a + 1, b);

			choosedDrug[nth] = B;
			combi(nth + 1, choosedIdx, choosedDrug, i + 1, a, b + 1);

	}

	public static void main(String[] args) throws IOException {
		T = Integer.parseInt(input.readLine());
		for (int t = 1; t <= T; t++) {
			tokens = new StringTokenizer(input.readLine());
			D = Integer.parseInt(tokens.nextToken());
			W = Integer.parseInt(tokens.nextToken());
			K = Integer.parseInt(tokens.nextToken());
			Map = new int[D][W];
			mapCopy = new int[D][W];
			for (int d = 0; d < D; d++) {
				tokens = new StringTokenizer(input.readLine());
				for (int w = 0; w < W; w++) {
					Map[d][w] = Integer.parseInt(tokens.nextToken());
					mapCopy[d][w] = Map[d][w];
				}
			} // 입력

			// 조합으로 A또는 B로 바꿀 줄 조합 만들고
			// 각 열이 K 기준에 부합하는지 확인해서
			// 기준에 부합하면 약품투입 최솟값 갱신
			if (isRight()) {
				output.append(String.format("#%d %d\n", t, 0));
			} else {
				isEnd = false;
				int[] choosedIdx = new int[D];
				int[] choosedDrug = new int[D];

				combi(0, choosedIdx, choosedDrug, 0, 0, 0);

				if (!isEnd) {
					output.append(String.format("#%d %d\n", t, K));
				} else {

					output.append(String.format("#%d %d\n", t, result));
				}
			}
		}

		System.out.println(output);
	}
}
