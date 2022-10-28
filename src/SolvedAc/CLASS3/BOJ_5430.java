package SolvedAc.CLASS3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

public class BOJ_5430 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(read.readLine());
		continuefirst: for (int test = 0; test < T; test++) {
			StringBuilder tempsb = new StringBuilder();
			String orders = read.readLine();
			int N = Integer.parseInt(read.readLine());
			String templine = read.readLine();
			Deque<Integer> dq = new LinkedList<>();
			for (int i = 1; i < templine.length() - 1; i++) {
				if (templine.charAt(i) == ',')
					continue;
				dq.add(templine.charAt(i) - '0');
			}
			boolean reverse = false;
			for (int i = 0; i < orders.length(); i++) {
				if (orders.charAt(i) == 'R')
					reverse = !reverse;
				else {
					if (dq.isEmpty()){
						sb.append("error\n");
						continue continuefirst;
					}
					if (reverse) {
						dq.pollLast();
					} else {
						dq.pollFirst();
					}
				}
			}
			tempsb.append("[");
			if(dq.isEmpty()) {
				sb.append("error\n");
				continue;
			}
			while (!dq.isEmpty()) {
				if(reverse) {
					tempsb.append(dq.pollLast());
				}
				else {
					tempsb.append(dq.poll());
				}
				if (!dq.isEmpty()) {
					tempsb.append(",");
				}
			}
			tempsb.append("]\n");
			sb.append(tempsb);
		}
		System.out.print(sb);
	}
}