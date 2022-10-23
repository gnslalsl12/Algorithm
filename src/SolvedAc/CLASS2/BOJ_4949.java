package SolvedAc.CLASS2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ_4949 {

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while (true) {
			String templine = read.readLine();
			if(templine.length() == 1 && templine.charAt(0) == '.') break;
			Stack<Boolean> check = new Stack<>();
			boolean found = false;
			for (int i = 0; i < templine.length(); i++) {
				if (templine.charAt(i) == '(') {
					check.add(true);
				} else if (templine.charAt(i) == '[') {
					check.add(false);
				} else if (templine.charAt(i) == ')') {
					if (check.size() == 0 ||  !check.peek()) {
						sb.append("no\n");
						found = true;
						break;
					}
					check.pop();
				} else if (templine.charAt(i) == ']') {
					if (check.size() == 0 || check.peek()) {
						sb.append("no\n");
						found = true;
						break;
					}
					check.pop();
				}
			}
			if(!found) {
				if(check.size() == 0) sb.append("yes\n");
				else sb.append("no\n");
			}
		}
		System.out.print(sb);
	}
}