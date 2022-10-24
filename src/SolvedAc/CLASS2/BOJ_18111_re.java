package SolvedAc.CLASS2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_18111_re {
	static int N, M, B;
	static int[] height = new int[258];
	static int resultheight, resulttime;
	static int min, max;

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		B = Integer.parseInt(tokens.nextToken());
		max = Integer.MIN_VALUE;
		min = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			tokens = new StringTokenizer(read.readLine());
			for (int j = 0; j < M; j++) {
				int h = Integer.parseInt(tokens.nextToken());
				height[h]++;
				max = Math.max(max, h);
				min = Math.min(min, h);
			}
		}
		resulttime = Integer.MAX_VALUE;
		resultheight = Integer.MIN_VALUE;
		for (int i = min; i <= max; i++) {
			getSolution(i);
		}
		System.out.println(resulttime + " " + resultheight);
	}

	private static void getSolution(int tempheight) {
		int timecount = 0;
		int tempB = B;
		for (int i = max; i > tempheight; i--) {
			int blockcount = (i - tempheight) * height[i];
			tempB += blockcount;
			timecount += 2 * blockcount;
		}
		for (int i = min; i < tempheight; i++) {
			int blockcount = (tempheight - i)*height[i];
			tempB -= blockcount;
			if (tempB < 0)
				return;
			timecount += blockcount;
		}
		if (resulttime >= timecount) { // 시간이 적게걸리면 갱신
			resulttime = timecount;
			if (resultheight <= tempheight) { // 높은 걸로
				resultheight = tempheight;
			}
		}
	}
}