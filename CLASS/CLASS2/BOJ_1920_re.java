package SolvedAc.CLASS2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ_1920_re {
	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(tokens.nextToken());
		ArrayList<Long> Nums = new ArrayList<>();
		tokens = new StringTokenizer(read.readLine());
		for (int i = 0; i < N; i++) {
			Nums.add(Long.parseLong(tokens.nextToken()));
		}
		tokens = new StringTokenizer(read.readLine());
		Collections.sort(Nums);
		int M = Integer.parseInt(tokens.nextToken());
		tokens = new StringTokenizer(read.readLine());
		for (int i = 0; i < M; i++) {
			Long temp = Long.parseLong(tokens.nextToken());
			int mindex = 0;
			int madex = N - 1;
			int searchdex = 0;
			while (true) {
				if (mindex > madex) {
					sb.append("0\n");
					break;
				}
				searchdex = (mindex + madex) / 2;
				long check = Nums.get(searchdex);
				if (check > temp) { // 범위를 아래로 줄이자
					madex = searchdex - 1;
				} else if (check < temp) { // 범위를 올리자
					mindex = searchdex + 1;
				} else {
					sb.append("1\n");
					break;
				}
			}
		}
		System.out.print(sb);
	}
}