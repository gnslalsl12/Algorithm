package day1107;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_11047 {

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		int N = Integer.parseInt(tokens.nextToken());
		long K = Integer.parseInt(tokens.nextToken());
		PriorityQueue<Integer> coins = new PriorityQueue<>(Collections.reverseOrder());
		for (int i = 0; i < N; i++) {
			int temp = Integer.parseInt(read.readLine());
			if (K < temp)
				continue;
			coins.add(temp);
		}
		int count = 0;
		while (K > 0) {
			if(K == 0) break;
			int tempcoin = coins.poll();
			if (K >= tempcoin) {
				count += K / tempcoin;
				K %= tempcoin;
			}
		}
		System.out.println(count);
	}

}
