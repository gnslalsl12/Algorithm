package SolvedAc.CLASS2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_10828 {
	private static Stack<Integer> st = new Stack<>();
private static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer tokens = new StringTokenizer(read.readLine());
		int N = Integer.parseInt(tokens.nextToken());
		for (int i = 0; i < N; i++) {
			tokens = new StringTokenizer(read.readLine());
			String temp = tokens.nextToken();
			if(temp.equals("push")) {
				push(Integer.parseInt(tokens.nextToken()));
			}else if(temp.equals("pop")) {
				pop();
			}else if(temp.equals("size")) {
				size();
			}else if(temp.equals("empty")) {
				empty();
			}else {
				top();
			}
		}
		System.out.print(sb);
	}
	
	private static void push(int x) {
		st.push(x);
		
	}
	
	private static void pop() {
		if(st.isEmpty()) sb.append(-1);
		else sb.append(st.pop());
		sb.append("\n");
	}
	
	private static void size() {
		sb.append(st.size());
		sb.append("\n");
	}
	
	private static void empty() {
		if(st.isEmpty()) sb.append(1);
		else sb.append(0);
		sb.append("\n");
	}
	
	private static void top() {
		if(st.isEmpty()) sb.append(-1);
		else sb.append(st.peek());
		sb.append("\n");
	}

}
