package SolvedAc.CLASS3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_7662 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(read.readLine());
		for (int test = 0; test < T; test++) {
			PriorityQueue<Integer> pq = new PriorityQueue<>();
			PriorityQueue<Integer> rpq = new PriorityQueue<>(Collections.reverseOrder());
			int k = Integer.parseInt(read.readLine());
			StringTokenizer tokens;
			for (int i = 0; i < k; i++) {
				tokens = new StringTokenizer(read.readLine());
				char order = tokens.nextToken().charAt(0);
				int number = Integer.parseInt(tokens.nextToken());
				if (order == 'I') {
					pq.add(number);
					rpq.add(number);
				} else {
					if (number == 1) {
						if (!rpq.isEmpty())
							rpq.poll();
					} else {
						if (!pq.isEmpty())
							pq.poll();
					}
				}
				if(pq.isEmpty() || rpq.isEmpty()) {
					pq.clear();
					rpq.clear();
				}
				System.out.println(order + " " +number);
				System.out.println(pq);
				System.out.println(rpq);
				System.out.println();
			}
			if(rpq.isEmpty() || pq.isEmpty()) {
				sb.append("EMPTY\n");
			}else {
				sb.append(String.format("%d %d\n", rpq.peek(), pq.peek()));
			}
		}
		System.out.print(sb);
	}
}