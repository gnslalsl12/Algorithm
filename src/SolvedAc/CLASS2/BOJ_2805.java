package SolvedAc.CLASS2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ_2805 {
	static long M;
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Long.parseLong(tokens.nextToken());
		ArrayList<Long> Trees = new ArrayList<>();
		tokens = new StringTokenizer(read.readLine());
		for (int i = 0; i < N; i++) {
			Trees.add(Long.parseLong(tokens.nextToken()));
		}
		Collections.sort(Trees);
		long max = Trees.get(N - 1);
		long min = Trees.get(0);
		long cutline = 0;
		while (true) {
			cutline = (max + min) / 2;
			System.out.println(String.format("%d , %d => %d", min, max, cutline));
			long remaincount = 0;
			for (int i = 0; i < N; i++) {
				if(Trees.get(i) <= cutline) continue;
				remaincount += Trees.get(i) - cutline;
			}
			System.out.println(remaincount);
			if (remaincount >= M) { // 생각보다 많이 남음 =>높이를 올리자
				min = cutline;
			} else { // 적게 남음 => 높이를 내리자
				max = cutline -1;
			}
			if (min >= max)
				break;
		}
		System.out.println(cutline);
	}

}
