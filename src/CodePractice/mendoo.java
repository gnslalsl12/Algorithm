package CodePractice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class mendoo {
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer tokens;
	static StringBuilder sb = new StringBuilder();
	static int T, D, W, K;
	static boolean[][] map;
	static boolean[] A;
	static boolean[] B;
	static int min;

	public static void main(String[] args) throws NumberFormatException, IOException {
		T = Integer.parseInt(input.readLine());
		for (int t = 1; t <= T; t++) {
			tokens = new StringTokenizer(input.readLine());
			D = Integer.parseInt(tokens.nextToken()); // 세로
			W = Integer.parseInt(tokens.nextToken()); // 가로
			K = Integer.parseInt(tokens.nextToken()); // 합격기준

			min = Integer.MAX_VALUE;
			A = new boolean[W];
			B = new boolean[W];
			Arrays.fill(B, true);
			map = new boolean[D][W]; // A: F, B: T
			for (int r = 0; r < D; r++) {
				tokens = new StringTokenizer(input.readLine());
				for (int c = 0; c < W; c++) {
					int type = Integer.parseInt(tokens.nextToken());
					if (type == 1)
						map[r][c] = true;
				}
			}

//          for(boolean[] i : map) {
//              System.out.println(Arrays.toString(i));
//          }
			//////// 입력끝

			subset(0, 0);
			sb.append("#" + t + " " + min + "\n");
		}
		System.out.println(sb);
	}

	private static void subset(int i, int cnt) {
		if (cnt > K )
			return;
		if (i == map.length && cnt <= K) {
			if (check()) {
				min = Math.min(min, cnt);
				return;
			}

		}

		boolean[] backup = map[i];
		map[i] = A;
		subset(i + 1, cnt + 1);
		map[i] = B;
		subset(i + 1, cnt + 1);
		map[i] = backup;
		subset(i + 1, cnt);
	}

	private static boolean check() {
		for (int c = 0; c < W; c++) {
			int checkCnt = 1;
			for (int r = 0; r < D - 1; r++) {
				if (checkCnt == K)
					continue;

				if (map[r][c] == map[r + 1][c]) {
					checkCnt++;
				} else {
					checkCnt = 1;
				}
			}
			if (checkCnt < K)
				return false;
		}
		return true;
	}

}