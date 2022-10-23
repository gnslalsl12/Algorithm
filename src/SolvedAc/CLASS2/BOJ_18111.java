package SolvedAc.CLASS2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ_18111 {
	static int N, M, B;

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		N = Integer.parseInt(tokens.nextToken());
		M = Integer.parseInt(tokens.nextToken());
		B = Integer.parseInt(tokens.nextToken());
		ArrayList<Integer> grounds = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			tokens = new StringTokenizer(read.readLine());
			for (int j = 0; j < M; j++) {
				grounds.add(Integer.parseInt(tokens.nextToken()));
			}
		}
		int size = N * B;
		while (true) {
			int max = grounds.get(size - 1);
			Collections.sort(grounds);
			int pluscount = 0;
			for (int i = 0; i < size; i++) {
				if(grounds.get(i) == max) break;
				pluscount++;
			}
			
		}
	}

}
