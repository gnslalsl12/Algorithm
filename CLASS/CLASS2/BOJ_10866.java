package SolvedAc.CLASS2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BOJ_10866 {

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(tokens.nextToken());
		Deque<Integer> dq = new LinkedList<>();
		for (int i = 0; i < N; i++) {
			tokens = new StringTokenizer(read.readLine());
			String temp = tokens.nextToken();
			if (temp.equals("push_front")) {
				int x = Integer.parseInt(tokens.nextToken());
				dq.addFirst(x);
			} else if (temp.equals("push_back")) {
				int x = Integer.parseInt(tokens.nextToken());
				dq.addLast(x);
			} else if (temp.equals("pop_front")) {
				if(dq.isEmpty()) {
					sb.append(-1 + "\n");
				}else {
					sb.append(dq.pollFirst() + "\n");
				}
			} else if (temp.equals("pop_back")) {
				if(dq.isEmpty()) {
					sb.append(-1 + "\n");
				}else {
					sb.append(dq.pollLast() + "\n");
				}
			} else if (temp.equals("size")) {
				sb.append(dq.size() + "\n");
			} else if (temp.equals("empty")) {
				if(dq.isEmpty()) {
					sb.append(1 + "\n");
				}else {
					sb.append(0 + "\n");
				}
			} else if (temp.equals("front")) {
				if(dq.isEmpty()) {
					sb.append(-1 + "\n");
				}else {
					sb.append(dq.peekFirst() + "\n");
				}
			} else {
				if(dq.isEmpty()) {
					sb.append(-1 + "\n");
				}else {
					sb.append(dq.peekLast() + "\n");
				}
			}
		}
		System.out.print(sb);
	}
}