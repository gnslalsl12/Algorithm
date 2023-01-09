package SolvedAc.CLASS2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ_10773 {

	public static void main(String[] args) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(read.readLine());
		Stack<Integer> nums = new Stack<>();
		long result = 0;
		for (int i = 0; i < N; i++) {
			int temp = Integer.parseInt(read.readLine());
			if (temp == 0) {
				if (nums.size() != 0)
					nums.pop();
			} else
				nums.add(temp);
		}
		while(!nums.isEmpty()) {
			result += nums.pop();
		}
		System.out.println(result);
	}
}