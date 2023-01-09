package SolvedAc.CLASS2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ_1874 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(read.readLine());
		int num = 0;
		Stack<Integer> Solvs = new Stack<>();
		Solvs.push(num++);
		for (int i = 1; i <= N; i++) {
			int temp = Integer.parseInt(read.readLine());
			while (true) {
				int peek = Solvs.peek();
				if (temp > peek) {
					Solvs.push(num++);
					sb.append("+\n");
					continue;
				} else if (temp < peek) {
					System.out.println("NO");
					return;
				} else {
					int out = Solvs.pop();
					if (out == 0) {
						System.out.println("NO");
						return;
					}
					sb.append("-\n");
					break;
				}
			}
		}
		System.out.print(sb);
	}
}
