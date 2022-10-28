package SolvedAc.CLASS3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

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
			templine = templine.replace('[', ',');
			templine = templine.replace(']', ',');
			StringTokenizer token = new StringTokenizer(templine, ",");
			while(token.hasMoreTokens()) {
				dq.add(Integer.parseInt(token.nextToken()));
						
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
//			if(dq.isEmpty()) {
//				sb.append("error\n");
//				continue;
//			}
			tempsb.append("[");
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